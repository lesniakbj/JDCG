package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.GameMap;
import sim.domain.MunitionStockpile;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.CampaignType;
import sim.domain.enums.FactionSide;
import sim.domain.enums.MapType;
import sim.domain.enums.MunitionType;
import sim.main.CampaignSettings;

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
    private Map<FactionSide, Integer> overallForceStrength;
    private double munitionCost;

    public AirfieldGenerator(Map<FactionSide, Integer> overallForceStrength, double munitionCost) {
        this.munitionCost = munitionCost;
        this.overallForceStrength = overallForceStrength;
    }

    public Map<FactionSide,List<Airfield>> generateAirfields(CampaignSettings campaignSettings, Map<FactionSide, Integer> overallForceStrength, int numStartingAirfields) {
        CampaignType campaignType = campaignSettings.getSelectedCampaignType();
        GameMap map = campaignSettings.getSelectedMap();
        List<AirfieldType> mapAirfields = map.getAirfieldTypes();

        // Increase the number of starting airfields if the map has a large number of them
        if(mapAirfields.size() > 18) {
            numStartingAirfields += 1;
        }

        // Assign the home airfields
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
        Map<FactionSide, List<AirfieldType>> factionAirfields = generateFactionAirfields(campaignType, mapAirfields, blueHomeAirfield, redHomeAirfield, numStartingAirfields);

        // Using all of the assigned AirfieldTypes, generate actual Airfields that will be used during the campaign
        Map<FactionSide, List<Airfield>> airfieldList = generateRealAirfields(factionAirfields);

        log.debug(airfieldList);
        return airfieldList;
    }

    public Map<FactionSide,List<Airfield>> adjustAirfieldsToGeneratedFront(Map<FactionSide, List<Point2D.Double>> warfareFront, Map<FactionSide, List<Airfield>> generatedAirfields) {
        FactionSide side = warfareFront.get(FactionSide.BLUEFOR) == null ? FactionSide.REDFOR : FactionSide.BLUEFOR;
        FactionSide other = warfareFront.get(FactionSide.BLUEFOR) == null ? FactionSide.BLUEFOR : FactionSide.REDFOR;
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
            if(airfield.getAirfieldType().equals(AirfieldType.KHASAB)) {
                log.debug(rect);
                log.debug(airfield.getAirfieldType().getAirfieldMapPosition());
            }

            if(rect.contains(airfield.getAirfieldType().getAirfieldMapPosition())) {
                airfield.setOwnerSide(side);
                ourAirfields.add(airfield);
                changedAirfields.add(airfield);
            }
        }
        enemyAirfields.removeAll(changedAirfields);

        // Return the changed airfield state
        Map<FactionSide,List<Airfield>> factionAirfields = new HashMap<>();
        factionAirfields.put(side, ourAirfields);
        factionAirfields.put(other, enemyAirfields);

        // Generate the munitions for the airfields
        generateAirfieldMunitions(factionAirfields);

        return factionAirfields;
    }

    public Map<FactionSide,List<Airfield>> generateAirfieldMunitions(Map<FactionSide,List<Airfield>> generatedAirfields) {
        double blueStrength = overallForceStrength.get(FactionSide.BLUEFOR);
        List<Airfield> blueFields = generatedAirfields.get(FactionSide.BLUEFOR);
        double redStrength = overallForceStrength.get(FactionSide.REDFOR);
        List<Airfield> redFields = generatedAirfields.get(FactionSide.REDFOR);

        for(Airfield type : blueFields) {
            // Stock 4x the munitions when it's the home airfield
            if(type.getAirfieldType().equals(blueHomeAirfield)) {
                addMunitionsToAirfield(type, 200);
                blueStrength -= (200 * munitionCost);
            } else {
                addMunitionsToAirfield(type, 50);
                blueStrength -= (50 * munitionCost);
            }
        }

        for(Airfield type : redFields) {
            // Stock 4x the munitions when it's the home airfield
            if(type.equals(redHomeAirfield)) {
                addMunitionsToAirfield(type, 200);
                redStrength -= (200 * munitionCost);
            } else {
                addMunitionsToAirfield(type, 50);
                redStrength -= (50 * munitionCost);
            }
        }

        return generatedAirfields;
    }

    private Map<FactionSide,List<Airfield>> generateRealAirfields(Map<FactionSide, List<AirfieldType>> factionAirfields) {
        Map<FactionSide, List<Airfield>> airfieldList = new HashMap<>();
        for(Map.Entry<FactionSide, List<AirfieldType>> airfieldEntry : factionAirfields.entrySet()) {
            List<Airfield> convertedAirfields = new ArrayList<>();
            for(AirfieldType airfieldType : airfieldEntry.getValue()) {
                Airfield airfield = new Airfield();
                airfield.setOwnerSide(airfieldEntry.getKey());
                airfield.setAirfieldType(airfieldType);

                // Critical structures get added during ground unit creation
                airfield.setCriticalStructures(null);

                // Filter out munitions that don't belong to your side?

                convertedAirfields.add(airfield);
            }
            airfieldList.put(airfieldEntry.getKey(), convertedAirfields);
        }
        return airfieldList;
    }

    private Map<FactionSide,List<AirfieldType>> generateFactionAirfields(CampaignType campaignType, List<AirfieldType> mapAirfields, AirfieldType blueforHomeAirfield, AirfieldType redforHomeAirfield, int numStartingAirfields) {
        Map<FactionSide, List<AirfieldType>> factionAirfields = new HashMap<>();
        List<AirfieldType> closestAirfields;
        List<AirfieldType> others;
        switch(campaignType) {
            case OFFENSIVE:
                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, redforHomeAirfield);
                closestAirfields.add(redforHomeAirfield);
                factionAirfields.put(FactionSide.REDFOR, closestAirfields);

                others = new ArrayList<>(mapAirfields);
                others.removeAll(closestAirfields);
                factionAirfields.put(FactionSide.BLUEFOR, others);
                break;
            case DEFENSIVE:
                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, blueforHomeAirfield);
                closestAirfields.add(blueforHomeAirfield);
                factionAirfields.put(FactionSide.BLUEFOR, closestAirfields);

                others = new ArrayList<>(mapAirfields);
                others.removeAll(closestAirfields);
                factionAirfields.put(FactionSide.REDFOR, others);
                break;
            case ALL_OUT_WAR:
                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, blueforHomeAirfield);
                closestAirfields.add(blueforHomeAirfield);
                factionAirfields.put(FactionSide.BLUEFOR, closestAirfields);

                closestAirfields = findClosestAirfields(numStartingAirfields, mapAirfields, redforHomeAirfield);
                closestAirfields.add(redforHomeAirfield);
                factionAirfields.put(FactionSide.REDFOR, closestAirfields);
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

    private void addMunitionsToAirfield(Airfield type, int totalToGen) {
        List<MunitionStockpile> stockpile = new ArrayList<>();
        for(MunitionType t : MunitionType.values()) {
            stockpile.add(new MunitionStockpile(t, totalToGen));
        }
        type.setMunitionStockpile(stockpile);
    }
}
