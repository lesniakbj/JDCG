package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.CampaignType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.ArmedShipGroundUnit;
import sim.domain.unit.ground.ArmorGroundUnit;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.UnarmedGroundUnit;
import sim.domain.unit.ground.UnarmedShipGroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.domain.unit.ground.defence.ArtilleryAirDefenceUnit;
import sim.domain.unit.ground.defence.MissileAirDefenceUnit;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;
import sim.util.mask.MaskFactory;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroundUnitGenerator {
    private static final Logger log = LogManager.getLogger(GroundUnitGenerator.class);

    private final Map<FactionSideType, Double> overallForceStrength;
    private double groundUnitCost;
    private double aaaCost;
    private double samCost;


    public GroundUnitGenerator(Map<FactionSideType, Double> overallForceStrength, double groundUnitCost, double aaaCost, double samCost) {
        this.overallForceStrength = overallForceStrength;
        this.groundUnitCost = groundUnitCost;
        this.aaaCost = aaaCost;
        this.samCost = samCost;
    }

    public Map<Airfield, List<UnitGroup<GroundUnit>>> generatePointDefenceUnits(CampaignSettings campaignSettings, Map<FactionSideType, List<Airfield>> generatedAirfields, FactionSideType side) {
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
                double dir = (side == FactionSideType.BLUEFOR) ? 0.0 : 180.0;

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

    public List<UnitGroup<GroundUnit>> generateFrontlineUnits(CampaignSettings campaignSettings, List<Point2D.Double> warfareFront, List<Point2D.Double> safeArea, FactionSideType side, List<Airfield> airfields) {
        // The campaign type is used to define generation areas
        CampaignType type = campaignSettings.getSelectedCampaignType();

        // Is the group supposed to be generated to the South or North?
        boolean genSouth = (side == FactionSideType.BLUEFOR);
        if(campaignSettings.getSelectedMap().getMapType().equals(MapType.NORMANDY)) {
            genSouth = (side == FactionSideType.REDFOR);
        }

        // We want to use 1/3 of our remaining points to generate the ground groups
        double targetCost = overallForceStrength.get(side) / 3;

        // Generation control variables
        int maxUnitsPerGroup = 6;
        int maxUnits = (int) (targetCost / groundUnitCost);
        int targetIterations = maxUnits / maxUnitsPerGroup;

        // How many of them should end up in water
        // Where should we NOT generate units on the map?
        int percentOfWaterUnits = 10;
        MapType mapType = campaignSettings.getSelectedMap().getMapType();
        Path2D.Double waterMask = MaskFactory.getWaterMask(mapType);
        Path2D.Double exclusionMask = MaskFactory.getExclusionMask(mapType);

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
                unitsGenerated = generateWaterUnit(waterMask, exclusionMask, mapX, mapY, groundGroups, dir, side);
            } else {
                int generateCloseToFront = DynamicCampaignSim.getRandomGen().nextInt(100) + 1;

                if(!type.equals(CampaignType.ALL_OUT_WAR)) {
                    if (generateCloseToFront < 35) {
                        unitsGenerated = generateUnitCloseToFront(waterMask, exclusionMask, mapX, mapY, groundGroups, dir, side, genSouth);
                    } else {
                        unitsGenerated = generateGeneralAreaUnit(type, waterMask, exclusionMask, groundGroups, dir, side, safeArea, airfields);
                    }
                } else {
                    unitsGenerated = generateGeneralAreaUnit(type, waterMask, exclusionMask, groundGroups, dir, side, safeArea, airfields);
                }
            }

            // If we generated that unit, we want to update the cost figures
            if(unitsGenerated != 0) {
                totalCost += unitsGenerated * groundUnitCost;
            }
        }

        // Update the cost map here
        double currentStrength = overallForceStrength.get(side);
        log.debug(side + " Strength: " + currentStrength);
        log.debug("Cost: " + totalCost);
        overallForceStrength.put(side, currentStrength - totalCost);

        return groundGroups;
    }

    public List<UnitGroup<AirDefenceUnit>> generateAirDefenceUnits(CampaignSettings campaignSettings, List<UnitGroup<GroundUnit>> groundUnits, List<Airfield> airfields, FactionSideType side) {
        // We want to use 1/2 of our remaining points to generate the ground groups
        double targetCost = overallForceStrength.get(side) / 2;

        // Calculate loop and generation control variables
        int maxUnitsPerGroup = 2;
        int maxUnits = (int) (targetCost / samCost);
        int targetSAMIterations = (maxUnits / maxUnitsPerGroup) / 4;
        int targetAAAIterations = (maxUnits / maxUnitsPerGroup) - targetSAMIterations;
        log.debug("Going to generate: " + maxUnits);

        // Get the water mask to prevent generation in the water
        Path2D.Double waterMask = MaskFactory.getWaterMask(campaignSettings.getSelectedMap().getMapType());

        // Generation loop
        List<UnitGroup<AirDefenceUnit>> airDefenceGroups = new ArrayList<>();
        try {
            airDefenceGroups.addAll(generateAirDefenceGroups(ArtilleryAirDefenceUnit.class, targetAAAIterations, waterMask, groundUnits, airfields, side, true, aaaCost));
            airDefenceGroups.addAll(generateAirDefenceGroups(MissileAirDefenceUnit.class, targetSAMIterations, waterMask, groundUnits, airfields, side, false, samCost));
        }  catch (IllegalAccessException | InstantiationException e) {
            log.debug(e);
        }

        return airDefenceGroups;
    }

    private List<UnitGroup<AirDefenceUnit>> generateAirDefenceGroups(Class<? extends AirDefenceUnit> airDefenceClass, int iterations, Path2D.Double waterMask, List<UnitGroup<GroundUnit>> groundUnits, List<Airfield> airfields, FactionSideType side, boolean isAAA, double cost) throws IllegalAccessException, InstantiationException {
        List<UnitGroup<AirDefenceUnit>> airDefenceGroups = new ArrayList<>();

        double total = 0;
        for(int i = 0; i < iterations; i++) {
            // 75% ground unit / 25% airfield split if AAA, 75% Airfield / 25% ground unit if SAM
            int airfieldPercent = 75;
            if(isAAA) {
                airfieldPercent = 25;
            }
            boolean isAtAirfield = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1) < airfieldPercent;

            // Get the coordinates for this group
            double x, y;
            boolean xNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
            boolean yNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
            do {
                if (isAtAirfield) {
                    Airfield airfield = airfields.get(DynamicCampaignSim.getRandomGen().nextInt(airfields.size()));
                    x = airfield.getAirfieldType().getAirfieldMapPosition().getX() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (xNeg ? -1 : 1));
                    y = airfield.getAirfieldType().getAirfieldMapPosition().getY() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (yNeg ? -1 : 1));
                } else {
                    UnitGroup<GroundUnit> groundGroup = groundUnits.get(DynamicCampaignSim.getRandomGen().nextInt(groundUnits.size()));
                    x = groundGroup.getMapXLocation() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (xNeg ? -1 : 1));
                    y = groundGroup.getMapYLocation() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (yNeg ? -1 : 1));
                }
            } while (waterMask.contains(x, y));

            List<AirDefenceUnit> units = generateAirDefenceUnit(airDefenceClass, 2);
            UnitGroup.Builder<AirDefenceUnit> b = new UnitGroup.Builder<>();
            UnitGroup<AirDefenceUnit> ad = b.setMapXLocation(x).setMapYLocation(y).setSide(side)
                    .setSpeed(0.0).setDirection(0.0).setUnits(units).build();
            airDefenceGroups.add(ad);
            total += units.size();
        }

        double current = overallForceStrength.get(side);
        log.debug(side + " Points: " + current);
        log.debug("Cost: " + (total * cost));
        overallForceStrength.put(side, current - (total * cost));
        return airDefenceGroups;
    }

    private int generateGeneralAreaUnit(CampaignType type, Path2D.Double waterMask, Path2D.Double exclusionMask, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSideType side, List<Point2D.Double> safeArea, List<Airfield> airfields) {
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
                notInSafeArea = (side == FactionSideType.REDFOR) != safePath.contains(mapX, mapY);
            } else if(isDefensive){
                notInSafeArea = (side == FactionSideType.REDFOR) == safePath.contains(mapX, mapY);
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

    private int generateUnitCloseToFront(Path2D.Double waterMask, Path2D.Double exclusionMask, double mapX, double mapY, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSideType side, boolean genSouth) {
        // While we are in the water mask, move away from the front line until we find a valid location,
        // we only want to search in the Y direction for 150 miles, otherwise we'll search the X direction
        Point2D.Double point = generateXYFromMask(mapY, mapX, waterMask, genSouth);
        if (exclusionMask.contains(point)) {
            point = generateXYFromMask(point.getX(), point.getY(), exclusionMask, genSouth);
        }

        List<GroundUnit> groundUnits = generateGroundUnitsForAirfield();
        UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
        UnitGroup<GroundUnit> g = b.setMapXLocation(point.getX()).setMapYLocation(point.getY()).setSide(side)
                .setSpeed(0.0).setDirection(dir).setUnits(groundUnits).build();
        groundGroups.add(g);
        return groundUnits.size();
    }

    private Point2D.Double generateXYFromMask(double mapY, double mapX, Path2D.Double mask, boolean genSouth) {

        double startY = mapY;
        int density = 20;
        int maxDistance = 125;
        int currentDistance = maxDistance;
        while (mask.contains(mapX, mapY)) {
            mapY = mapY - (10 * (genSouth ? -1 : 1));

            currentDistance -= 10;
            if(currentDistance <= 0) {
                break;
            }
        }

        if(currentDistance <= 0) {
            while(mask.contains(mapX, mapY)) {
                mapY = startY - (DynamicCampaignSim.getRandomGen().nextInt(maxDistance) * (genSouth ? -1 : 1));
                mapX = mapX + 10;
            }
        } else {
            mapY = mapY - DynamicCampaignSim.getRandomGen().nextInt(density) * (genSouth ? -1 : 1);
        }

        return new Point2D.Double(mapX, mapY);
    }

    private int generateWaterUnit(Path2D.Double waterMask, Path2D.Double exclusionMask, double mapX, double mapY, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSideType side) {
        // If the point we generated is within water, generate a water group
        if(waterMask.contains(mapX, mapY) && !exclusionMask.contains(mapX, mapY)) {
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

    private List<AirDefenceUnit> generateAirDefenceUnit(Class<? extends AirDefenceUnit> clazz, int amount) throws IllegalAccessException, InstantiationException {
        List<AirDefenceUnit> list = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            list.add(clazz.newInstance());
        }
        return list;
    }
}
