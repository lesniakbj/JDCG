package sim.gen;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.CampaignType;
import sim.domain.enums.FactionSide;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.ArmedShipGroundUnit;
import sim.domain.unit.ground.ArmorGroundUnit;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.UnarmedGroundUnit;
import sim.domain.unit.ground.UnarmedShipGroundUnit;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;
import sim.util.mask.CaucasusWaterMask;
import sim.util.mask.NevadaWaterMask;
import sim.util.mask.NormandyWaterMask;
import sim.util.mask.PersianGulfWaterMask;

public class GroundUnitGenerator {
    private static final Logger log = LogManager.getLogger(GroundUnitGenerator.class);

    private final double groundUnitCost;
    private final Map<FactionSide, Double> overallForceStrength;

    public GroundUnitGenerator(Map<FactionSide, Double> overallForceStrength, double groundUnitCost) {
        this.groundUnitCost = groundUnitCost;
        this.overallForceStrength = overallForceStrength;
    }

    public Map<Airfield, List<UnitGroup<GroundUnit>>> generatePointDefenceUnits(CampaignSettings campaignSettings, Map<FactionSide, List<Airfield>> generatedAirfields, FactionSide side) {
        List<Airfield> airfields = generatedAirfields.get(side);
        Map<Airfield, List<UnitGroup<GroundUnit>>> returnMap = new HashMap<>();

        int totalGenerated = 0;
        for(Airfield field : airfields) {
            List<UnitGroup<GroundUnit>> airfieldDefenceGroups = new ArrayList<>();

            // Here we want to generate a set of ground groups per field
            int groupsPerFields = 4 + (DynamicCampaignSim.getRandomGen().nextInt(5));
            groupsPerFields = field.isHomeAirfield() ? groupsPerFields * 4 : groupsPerFields;
            for(int i = 0; i < groupsPerFields; i++) {
                boolean xNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
                boolean yNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
                Point2D.Double loc = field.getAirfieldType().getAirfieldMapPosition();
                double mapX = loc.getX() + (DynamicCampaignSim.getRandomGen().nextInt(6) * (xNeg ? -1 : 1));
                double mapY = loc.getY() + (DynamicCampaignSim.getRandomGen().nextInt(6) * (yNeg ? -1 : 1));
                double dir = (side == FactionSide.BLUEFOR) ? 0.0 : 180.0;

                List<GroundUnit> groundUnits = generateGroundUnitsForAirfield();
                UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
                UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                                                .setSpeed(0.0).setDirection(dir).setUnits(groundUnits).build();

                airfieldDefenceGroups.add(g);
                totalGenerated += groundUnits.size();
            }

            // .. finally, add it to the map.
            returnMap.put(field, airfieldDefenceGroups);
        }

        // Calculate the cost of the units
        double current = overallForceStrength.get(side);
        double cost = groundUnitCost * totalGenerated;
        log.debug(side + " Strength: " + current);
        log.debug("Cost: " + cost);
        overallForceStrength.put(side, current - cost);

        return returnMap;
    }

    public List<UnitGroup<GroundUnit>> generateFrontlineUnits(CampaignSettings campaignSettings, List<Point2D.Double> warfareFront, List<Point2D.Double> safeArea, FactionSide side, List<Airfield> airfields, double groundUnitCost) {
        // The campaign type is used to define generation areas
        CampaignType type = campaignSettings.getSelectedCampaignType();

        // Is the group supposed to be generated to the South or North?
        boolean genSouth = (side == FactionSide.BLUEFOR);

        // We want to use 1/3 of our remaining points to generate the ground groups
        double targetCost = overallForceStrength.get(side) / 3;

        // Generation control variables
        int maxUnitsPerGroup = 6;
        int maxUnits = (int) (targetCost / groundUnitCost);
        int targetIterations = maxUnits / maxUnitsPerGroup;

        // How many of them should end up in water
        int percentOfWaterUnits = 10;
        Path2D.Double waterMask;
        switch(campaignSettings.getSelectedMap().getMapType()) {
            case PERSIAN_GULF:
                waterMask = new PersianGulfWaterMask();
                break;
            case CAUCASUS:
                waterMask = new CaucasusWaterMask();
                break;
            case NORMANDY:
                waterMask = new NormandyWaterMask();
                break;
            case NEVADA:
                waterMask = new NevadaWaterMask();
                break;
            default:
                waterMask = new PersianGulfWaterMask();
        }

        // Do various bounds calculations
        assert warfareFront.size() == 2;
        boolean left = warfareFront.get(0).getX() < warfareFront.get(1).getX();
        double xLeft = left ? warfareFront.get(0).getX() : warfareFront.get(1).getX();
        double xRight = left ? warfareFront.get(1).getX() : warfareFront.get(0).getX();

        // Overall cost debugging
        log.debug(side + " Strength: " + overallForceStrength.get(side));
        log.debug("Cost for Side: " + targetCost);
        log.debug("Front: " + warfareFront);

        // Generate our number of groups
        double totalCost = 0;
        List<UnitGroup<GroundUnit>> groundGroups = new ArrayList<>();
        for(int i = 0; i < targetIterations; i++) {
            // Check how we will be generating this group
            boolean isWaterUnit = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1) < percentOfWaterUnits;
            double mapX = xLeft + (DynamicCampaignSim.getRandomGen().nextInt((int) (xRight - xLeft)));
            double mapY = warfareFront.get(0).getY() - (DynamicCampaignSim.getRandomGen().nextInt(25) * (genSouth ? -1 : 1));
            double dir = genSouth ? 0.0 : 180.0;

            // Generate the unit and return the number generated
            int unitsGenerated;
            if(isWaterUnit) {
                unitsGenerated = generateWaterUnit(waterMask, mapX, mapY, groundGroups, dir, side);
            } else {
                int generateCloseToFront = DynamicCampaignSim.getRandomGen().nextInt(100) + 1;

                if(!type.equals(CampaignType.ALL_OUT_WAR)) {
                    if (generateCloseToFront < 35) {
                        unitsGenerated = generateUnitCloseToFront(waterMask, mapX, mapY, groundGroups, dir, side, genSouth);
                    } else {
                        unitsGenerated = generateGeneralAreaUnit(type, waterMask, groundGroups, dir, side, safeArea, airfields);
                    }
                } else {
                    unitsGenerated = generateGeneralAreaUnit(type, waterMask, groundGroups, dir, side, safeArea, airfields);
                }
            }

            // If we generated that unit, we want to update the cost figures
            if(unitsGenerated != 0) {
                log.debug("We generated a group of " + unitsGenerated + " units!");
                totalCost += unitsGenerated * groundUnitCost;
            }
        }

        // Update the cost map here
        double currentStrength = overallForceStrength.get(side);
        overallForceStrength.put(side, currentStrength - totalCost);

        return groundGroups;
    }

    private int generateGeneralAreaUnit(CampaignType type, Path2D.Double waterMask, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSide side, List<Point2D.Double> safeArea, List<Airfield> airfields) {
        // Generate the bounding area that we want to keep units within if possible
        double x = safeArea.get(0).getX();
        double y = safeArea.get(0).getY();
        double w = safeArea.get(2).getX() - x;
        double h = safeArea.get(1).getY() - y;
        Rectangle2D.Double safePath = new Rectangle2D.Double(x, y, w, h);

        // We want to generate this group somewhere around an airfield
        int airfield = DynamicCampaignSim.getRandomGen().nextInt(airfields.size());
        double startX = airfields.get(airfield).getAirfieldType().getAirfieldMapPosition().getX() - 50;
        double startY = airfields.get(airfield).getAirfieldType().getAirfieldMapPosition().getY() - 50;
        Ellipse2D.Double bound = new Ellipse2D.Double();
        bound.setFrame(startX, startY, 100, 100);

        // For a number of attempts, attempt to place this unit in a valid location
        boolean isOffensive = (type.equals(CampaignType.OFFENSIVE));
        boolean isDefensive = (type.equals(CampaignType.DEFENSIVE));
        boolean unitInWater, notInArea, notInSafeArea;
        int maxPlaceAttempts = 200;
        double mapX, mapY;
        do {
            // Get a location near the spot we want to generate
            mapX = startX + DynamicCampaignSim.getRandomGen().nextInt((int) bound.getWidth());
            mapY = startY + DynamicCampaignSim.getRandomGen().nextInt((int) bound.getHeight());

            // Ensure that this location is a valid location
            unitInWater = waterMask.contains(mapX, mapY);
            notInArea = !bound.contains(mapX, mapY);
            if(isOffensive) {
                notInSafeArea = (side == FactionSide.REDFOR) != safePath.contains(mapX, mapY);
            } else if(isDefensive){
                notInSafeArea = (side == FactionSide.REDFOR) == safePath.contains(mapX, mapY);
            } else {
                notInSafeArea = false;
            }

            // Only attempt to generate for so many tries
            maxPlaceAttempts -= 1;
            if(maxPlaceAttempts == 0) {
                break;
            }
        } while (unitInWater || notInArea || notInSafeArea);

        // If we never found a location, return
        if(maxPlaceAttempts == 0) {
            return 0;
        }

        List<GroundUnit> groundUnits = generateGroundUnitsForAirfield();
        UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
        UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                .setSpeed(0.0).setDirection(dir).setUnits(groundUnits).build();
        groundGroups.add(g);

        return groundUnits.size();
    }

    private int generateUnitCloseToFront(Path2D.Double waterMask, double mapX, double mapY, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSide side, boolean genSouth) {
        // While we are in the water mask, move away from the front line until we find a valid location,
        // we only want to search in the Y direction for 150 miles, otherwise we'll search the X direction
        double startY = mapY;
        int maxDistance = 225;
        while (waterMask.contains(mapX, mapY)) {
            mapY = mapY - (10 * (genSouth ? -1 : 1));

            maxDistance -= 10;
            if(maxDistance == 0) {
                break;
            }
        }

        if(maxDistance == 0) {
            mapY = startY - (DynamicCampaignSim.getRandomGen().nextInt(maxDistance)* (genSouth ? -1 : 1));
            while(waterMask.contains(mapX, mapY)) {
                mapX = mapX + 10;
            }
        } else {
            mapY = mapY - DynamicCampaignSim.getRandomGen().nextInt(25) * (genSouth ? -1 : 1);
        }

        List<GroundUnit> groundUnits = generateGroundUnitsForAirfield();
        UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
        UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                .setSpeed(0.0).setDirection(dir).setUnits(groundUnits).build();
        groundGroups.add(g);
        return groundUnits.size();
    }

    private int generateWaterUnit(Path2D.Double waterMask, double mapX, double mapY, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSide side) {
        // If the point we generated is within water, generate a water group
        if(waterMask.contains(mapX, mapY)) {
            List<GroundUnit> groundUnits = generateWaterUnits();
            UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
            UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                    .setSpeed(0.0).setDirection(dir).setUnits(groundUnits).build();
            groundGroups.add(g);
            return groundUnits.size();
        }
        return 0;
    }

    private List<GroundUnit> generateGroundUnitsForAirfield() {
        return new ArrayList<>(Arrays.asList(new UnarmedGroundUnit(), new ArmorGroundUnit(), new ArmorGroundUnit(), new UnarmedGroundUnit()));
    }

    private List<GroundUnit> generateWaterUnits() {
        return new ArrayList<>(Arrays.asList(new UnarmedShipGroundUnit(), new ArmedShipGroundUnit(), new ArmedShipGroundUnit(), new UnarmedShipGroundUnit()));
    }
}
