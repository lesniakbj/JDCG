package sim.gen.ground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.CampaignType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.enums.MunitionType;
import sim.domain.unit.air.MunitionStockpile;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.global.GameMap;
import sim.domain.unit.ground.Structure;
import sim.settings.CampaignSettings;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AirfieldGenerator {
    private static final Logger log = LogManager.getLogger(AirfieldGenerator.class);

    private AirfieldType blueHomeAirfield;
    private AirfieldType redHomeAirfield;
    private Map<FactionSideType, Double> overallForceStrength;
    private double munitionCost;

    public AirfieldGenerator(Map<FactionSideType, Double> overallForceStrength, double munitionCost) {
        this.munitionCost = munitionCost;
        this.overallForceStrength = overallForceStrength;
    }

    public Map<FactionSideType,List<Airfield>> generateAirfields(CampaignSettings campaignSettings, int numStartingAirfields) {
        log.debug("Generating airfields for each coalition...");
        CampaignType campaignType = campaignSettings.getSelectedCampaignType();
        GameMap map = campaignSettings.getSelectedMap();
        List<AirfieldType> mapAirfields = map.getAirfieldTypes();

        // Increase the number of starting airfields if the map has a large number of them
        if(mapAirfields.size() > 18) {
            log.debug("Large Map, granting more airfields to start...");
            numStartingAirfields += 1;
        }

        // Assign the home airfields
        String dbg = !map.getMapType().equals(MapType.NORMANDY) ? "south" : "north";
        log.debug("Bluefor starts to the " + dbg);
        if(!map.getMapType().equals(MapType.NORMANDY)) {
            // Whichever airfield is furthest south is the bluefor home airfield
            blueHomeAirfield = mapAirfields.stream().max(Comparator.comparing((a) -> a.getAirfieldMapPosition().getY())).orElse(null);
            redHomeAirfield = mapAirfields.stream().min(Comparator.comparing((a) -> a.getAirfieldMapPosition().getY())).orElse(null);
        } else {
            redHomeAirfield = mapAirfields.stream().max(Comparator.comparing((a) -> a.getAirfieldMapPosition().getY())).orElse(null);
            blueHomeAirfield = mapAirfields.stream().min(Comparator.comparing((a) -> a.getAirfieldMapPosition().getY())).orElse(null);
        }

        // Assign the rest based on campaign type:
        //      1) DEFENSIVE - Closest 3 airbases to bluefor belong to bluefor, rest to redfor
        //      2) OFFENSIVE - Closest 3 airbases to redfor belong to redfor, rest to bluefor
        //      3) AOW - Closest 3 airbases to bluefor belong to bluefor, rest to neutral
        Map<FactionSideType, List<AirfieldType>> factionAirfields = generateFactionAirfields(campaignType, mapAirfields, blueHomeAirfield, redHomeAirfield, numStartingAirfields);

        // Using all of the assigned AirfieldTypes, generate actual Airfields that will be used during the campaign
        Map<FactionSideType, List<Airfield>> airfieldList = generateRealAirfields(factionAirfields, blueHomeAirfield, redHomeAirfield);

        log.debug(airfieldList);
        return airfieldList;
    }

    public Map<FactionSideType,List<Airfield>> adjustAirfieldsToGeneratedFront(Map<FactionSideType, List<Point2D.Double>> warfareFront, Map<FactionSideType, List<Airfield>> generatedAirfields) {
        log.debug("Adjusting ownership of airfields if they fall within the warfare front....");
        FactionSideType side = warfareFront.get(FactionSideType.BLUEFOR) == null ? FactionSideType.REDFOR : FactionSideType.BLUEFOR;
        FactionSideType other = warfareFront.get(FactionSideType.BLUEFOR) == null ? FactionSideType.BLUEFOR : FactionSideType.REDFOR;
        List<Point2D.Double> pts = warfareFront.get(side);

        // Create the rectangle to sample for airports
        assert pts.size() == 4;
        log.debug(pts);
        double width = pts.get(2).getX() - pts.get(0).getX();
        double height = pts.get(1).getY() - pts.get(0).getY();
        Rectangle2D.Double rect = new Rectangle2D.Double(pts.get(0).getX(), pts.get(0).getY(), width, height);

        // Sample airfields to see if they fall within the rect
        List<Airfield> ourAirfields = generatedAirfields.get(side);
        List<Airfield> enemyAirfields = generatedAirfields.get(other);
        List<Airfield> changedAirfields = new ArrayList<>();
        for(Airfield airfield : enemyAirfields) {
            if(rect.contains(airfield.getAirfieldType().getAirfieldMapPosition())) {
                log.debug("Adjusting owner of airfield...");
                airfield.setOwnerSide(side);
                ourAirfields.add(airfield);
                changedAirfields.add(airfield);
            }
        }
        enemyAirfields.removeAll(changedAirfields);

        // Return the changed airfield state
        Map<FactionSideType,List<Airfield>> factionAirfields = new HashMap<>();
        factionAirfields.put(side, ourAirfields);
        factionAirfields.put(other, enemyAirfields);

        // Generate the munitions for the airfields
        generateAirfieldMunitions(factionAirfields);

        return factionAirfields;
    }

    private void generateAirfieldMunitions(Map<FactionSideType, List<Airfield>> generatedAirfields) {
        log.debug("Generating standard munition sets for each airfield...");
        double blueStrength = overallForceStrength.get(FactionSideType.BLUEFOR);
        List<Airfield> blueFields = generatedAirfields.get(FactionSideType.BLUEFOR);
        double redStrength = overallForceStrength.get(FactionSideType.REDFOR);
        List<Airfield> redFields = generatedAirfields.get(FactionSideType.REDFOR);

        log.debug("Before Generating Blue Airfield Munitions: " + blueStrength);
        log.debug("Before Generating Red Airfield Munitions: " + redStrength);

        blueStrength = addStock(blueStrength, blueFields, blueHomeAirfield, 100, 20, FactionSideType.BLUEFOR);
        redStrength = addStock(redStrength, redFields, redHomeAirfield, 100, 20, FactionSideType.REDFOR);

        log.debug("After Generating Blue Airfield Munitions: " + blueStrength);
        log.debug("After Generating Red Airfield Munitions: " + redStrength);
        overallForceStrength.put(FactionSideType.BLUEFOR, blueStrength);
        overallForceStrength.put(FactionSideType.REDFOR, redStrength);
    }

    private double addStock(double strength, List<Airfield> airfields, AirfieldType homeField, int homeTotal, int frontFieldTotal, FactionSideType side) {
        int added;
        for(Airfield type : airfields) {
            if(type.getAirfieldType().equals(homeField)) {
                added = addMunitionsToAirfield(side, type, homeTotal);
                strength -= (added * munitionCost);
            } else {
                added = addMunitionsToAirfield(side, type, frontFieldTotal);
                strength -= (added * munitionCost);
            }
        }

        return Math.floor(strength);
    }

    private Map<FactionSideType,List<Airfield>> generateRealAirfields(Map<FactionSideType, List<AirfieldType>> factionAirfields, AirfieldType blueHomeAirfield, AirfieldType redHomeAirfield) {
        log.debug("Creating campaign Airfield objects");
        Map<FactionSideType, List<Airfield>> airfieldList = new HashMap<>();
        for(Map.Entry<FactionSideType, List<AirfieldType>> airfieldEntry : factionAirfields.entrySet()) {
            List<Airfield> convertedAirfields = new ArrayList<>();
            for(AirfieldType airfieldType : airfieldEntry.getValue()) {
                Airfield airfield = new Airfield();
                airfield.setOwnerSide(airfieldEntry.getKey());
                airfield.setAirfieldType(airfieldType);

                // Critical structures get added during ground unit creation
                boolean isHomeAirfield = airfieldType.equals(blueHomeAirfield) || airfieldType.equals(redHomeAirfield);
                airfield.setCriticalStructures(generateAirfieldCriticalStructures(airfieldType, isHomeAirfield));

                // Add home airfield flag
                airfield.setHomeAirfield(isHomeAirfield);

                convertedAirfields.add(airfield);
            }
            airfieldList.put(airfieldEntry.getKey(), convertedAirfields);
        }
        return airfieldList;
    }

    private List<Structure> generateAirfieldCriticalStructures(AirfieldType airfieldType, boolean isHomeAirfield) {
        log.debug("Generating structures");
        int minStructures = 3;
        int total = minStructures + DynamicCampaignSim.getRandomGen().nextInt(3);

        if(isHomeAirfield) {
            total += 3;
        }

        List<Structure> structures = new ArrayList<>();
        for(int i = 0; i < total; i++) {
            boolean xNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
            boolean yNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
            Structure s = new Structure();
            s.setMapXLocation(airfieldType.getAirfieldMapPosition().getX() + (DynamicCampaignSim.getRandomGen().nextInt(5) * (xNeg ? -1 : 1)));
            s.setMapYLocation(airfieldType.getAirfieldMapPosition().getY() + (DynamicCampaignSim.getRandomGen().nextInt(5) * (yNeg ? -1 : 1)));
            s.setSpeedMilesPerHour(0.0);
            s.setDirection(0.0);
            structures.add(s);
        }

        log.debug("Number of Structures Generated: " + structures.size());
        return structures;
    }

    private Map<FactionSideType,List<AirfieldType>> generateFactionAirfields(CampaignType campaignType, List<AirfieldType> mapAirfields, AirfieldType blueforHomeAirfield, AirfieldType redforHomeAirfield, int numStartingAirfields) {
        Map<FactionSideType, List<AirfieldType>> factionAirfields = new HashMap<>();
        List<AirfieldType> closestAirfields;
        List<AirfieldType> others;
        switch(campaignType) {
            case OFFENSIVE:
                log.debug("Generating OFFENSIVE campaign airfields, airfields to REDFOR");
                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, redforHomeAirfield);
                closestAirfields.add(redforHomeAirfield);
                factionAirfields.put(FactionSideType.REDFOR, closestAirfields);

                others = new ArrayList<>(mapAirfields);
                others.removeAll(closestAirfields);
                factionAirfields.put(FactionSideType.BLUEFOR, others);
                break;
            case DEFENSIVE:
                log.debug("Generating DEFENSIVE campaign airfields, airfields to BLUEFOR");
                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, blueforHomeAirfield);
                closestAirfields.add(blueforHomeAirfield);
                factionAirfields.put(FactionSideType.BLUEFOR, closestAirfields);

                others = new ArrayList<>(mapAirfields);
                others.removeAll(closestAirfields);
                factionAirfields.put(FactionSideType.REDFOR, others);
                break;
            case ALL_OUT_WAR:
                log.debug("Generating ALL OUT WAR campaign airfields");
                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, blueforHomeAirfield);
                closestAirfields.add(blueforHomeAirfield);
                factionAirfields.put(FactionSideType.BLUEFOR, closestAirfields);

                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, redforHomeAirfield);
                closestAirfields.add(redforHomeAirfield);
                factionAirfields.put(FactionSideType.REDFOR, closestAirfields);
                break;
        }
        return factionAirfields;
    }

    private List<AirfieldType> findClosestAirfields(int numToFind, List<AirfieldType> mapAirfields, AirfieldType airfield) {
        ArrayList<AirfieldType> closestAirfields = new ArrayList<>();
        List<AirfieldType> sortedByDistance = mapAirfields.stream().filter(ma -> !ma.equals(airfield)).sorted(Comparator.comparing(ma -> ma.getDistanceTo(airfield))).collect(Collectors.toList());
        for(int i = 0; i < numToFind; i++) {
            closestAirfields.add(sortedByDistance.get(i));
        }
        return closestAirfields;
    }

    private int addMunitionsToAirfield(FactionSideType side, Airfield type, int totalToGen) {
        List<MunitionStockpile> stockpile = new ArrayList<>();
        int totalAdded = 0;
        for(MunitionType t : MunitionType.getMunitionsForSide(side)) {
            if(!t.isStockInStockpiles()) {
                continue;
            }
            stockpile.add(new MunitionStockpile(t, totalToGen));
            totalAdded += totalToGen;
        }
        type.setMunitionStockpile(stockpile);
        return totalAdded;
    }
}
