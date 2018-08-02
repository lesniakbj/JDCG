package sim.gen.ground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AirDefenceUnitType;
import sim.domain.enums.AirDefenceWeaponType;
import sim.domain.enums.CampaignType;
import sim.domain.enums.ConflictEraType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.enums.GroundUnitType;
import sim.domain.enums.MapType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.global.Coalition;
import sim.domain.unit.ground.ArmedShipGroundUnit;
import sim.domain.unit.ground.ArmorGroundUnit;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.UnarmedGroundUnit;
import sim.domain.unit.ground.UnarmedShipGroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.domain.unit.ground.defence.ArtilleryAirDefenceUnit;
import sim.domain.unit.ground.defence.MissileAirDefenceUnit;
import sim.settings.CampaignSettings;
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
import java.util.stream.Collectors;

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

                List<GroundUnit> groundUnits = generateGroundUnitsForAirfield(campaignSettings, side);
                UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
                UnitGroup<GroundUnit> g = b.setUnits(groundUnits).setMapXLocation(mapX).setMapYLocation(mapY)
                                                .setSide(side).setPlayerGeneratedGroup(false)
                                                .setSpeed(0.0).setDirection(dir).build();

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
                unitsGenerated = generateWaterUnit(campaignSettings, waterMask, exclusionMask, mapX, mapY, groundGroups, dir, side);
            } else {
                int generateCloseToFront = DynamicCampaignSim.getRandomGen().nextInt(100) + 1;

                if(!type.equals(CampaignType.ALL_OUT_WAR)) {
                    if (generateCloseToFront < 35) {
                        unitsGenerated = generateUnitCloseToFront(campaignSettings, waterMask, exclusionMask, mapX, mapY, groundGroups, dir, side, genSouth);
                    } else {
                        unitsGenerated = generateGeneralAreaUnit(campaignSettings, type, waterMask, exclusionMask, groundGroups, dir, side, safeArea, airfields);
                    }
                } else {
                    unitsGenerated = generateGeneralAreaUnit(campaignSettings, type, waterMask, exclusionMask, groundGroups, dir, side, safeArea, airfields);
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
            airDefenceGroups.addAll(generateAirDefenceGroups(campaignSettings, ArtilleryAirDefenceUnit.class, targetAAAIterations, waterMask, groundUnits, airfields, side, true, aaaCost));
            airDefenceGroups.addAll(generateAirDefenceGroups(campaignSettings, MissileAirDefenceUnit.class, targetSAMIterations, waterMask, groundUnits, airfields, side, false, samCost));
        }  catch (IllegalAccessException | InstantiationException e) {
            log.debug(e);
        }

        return airDefenceGroups;
    }

    private List<UnitGroup<AirDefenceUnit>> generateAirDefenceGroups(CampaignSettings campaignSettings, Class<? extends AirDefenceUnit> airDefenceClass, int iterations, Path2D.Double waterMask, List<UnitGroup<GroundUnit>> groundUnits, List<Airfield> airfields, FactionSideType side, boolean isAAA, double cost) throws IllegalAccessException, InstantiationException {
        List<UnitGroup<AirDefenceUnit>> airDefenceGroups = new ArrayList<>();

        double total = 0;
        for(int i = 0; i < iterations; i++) {
            // 75% ground unit / 25% airfield split if AAA, 75% Airfield / 25% ground unit if SAM
            int airfieldPercent = 75;
            if(isAAA) {
                airfieldPercent = 25;
            }
            boolean isAtAirfield = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1) < airfieldPercent;



            // Get the coordinates and units for this group
            double x, y;
            boolean xNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
            boolean yNeg = DynamicCampaignSim.getRandomGen().nextBoolean();
            Airfield airfield = null;
            do {
                if (isAtAirfield) {
                    airfield = airfields.get(DynamicCampaignSim.getRandomGen().nextInt(airfields.size()));
                    x = airfield.getAirfieldType().getAirfieldMapPosition().getX() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (xNeg ? -1 : 1));
                    y = airfield.getAirfieldType().getAirfieldMapPosition().getY() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (yNeg ? -1 : 1));
                } else {
                    UnitGroup<GroundUnit> groundGroup = groundUnits.get(DynamicCampaignSim.getRandomGen().nextInt(groundUnits.size()));
                    x = groundGroup.getMapXLocation() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (xNeg ? -1 : 1));
                    y = groundGroup.getMapYLocation() - (DynamicCampaignSim.getRandomGen().nextInt(3) * (yNeg ? -1 : 1));
                }
            } while (waterMask.contains(x, y));

            List<AirDefenceUnit> units = generateAirDefenceUnit(airDefenceClass,  side, campaignSettings, airfield);
            log.debug("Following AD Units generated: " + units);
            UnitGroup.Builder<AirDefenceUnit> b = new UnitGroup.Builder<>();
            UnitGroup<AirDefenceUnit> ad = b.setMapXLocation(x).setMapYLocation(y).setSide(side)
                                            .setPlayerGeneratedGroup(false).setSpeed(0.0)
                                            .setDirection(0.0).setUnits(units).build();
            if(ad != null) {
                airDefenceGroups.add(ad);
                total += units.size();
            }
        }

        double current = overallForceStrength.get(side);
        log.debug(side + " Points: " + current);
        log.debug("Cost: " + (total * cost));
        overallForceStrength.put(side, current - (total * cost));
        return airDefenceGroups;
    }

    private int generateGeneralAreaUnit(CampaignSettings campaignSettings, CampaignType type, Path2D.Double waterMask, Path2D.Double exclusionMask, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSideType side, List<Point2D.Double> safeArea, List<Airfield> airfields) {
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

        List<GroundUnit> groundUnits = generateGroundUnitsForFront(campaignSettings, side);
        UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
        UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                                    .setPlayerGeneratedGroup(false).setSpeed(0.0)
                                    .setDirection(dir).setUnits(groundUnits).build();
        groundGroups.add(g);

        return groundUnits.size();
    }

    private int generateUnitCloseToFront(CampaignSettings campaignSettings, Path2D.Double waterMask, Path2D.Double exclusionMask, double mapX, double mapY, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSideType side, boolean genSouth) {
        // While we are in the water mask, move away from the front line until we find a valid location,
        // we only want to search in the Y direction for 150 miles, otherwise we'll search the X direction
        Point2D.Double point = generateXYFromMask(mapY, mapX, waterMask, genSouth);
        if (exclusionMask.contains(point)) {
            point = generateXYFromMask(point.getX(), point.getY(), exclusionMask, genSouth);
        }

        List<GroundUnit> groundUnits = generateGroundUnitsForFront(campaignSettings, side);
        log.debug("FIX THIS");
        UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
        UnitGroup<GroundUnit> g = b.setMapXLocation(point.getX()).setMapYLocation(point.getY()).setSide(side)
                                    .setPlayerGeneratedGroup(false).setSpeed(0.0)
                                    .setDirection(dir).setUnits(groundUnits).build();
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

    private int generateWaterUnit(CampaignSettings campaignSettings, Path2D.Double waterMask, Path2D.Double exclusionMask, double mapX, double mapY, List<UnitGroup<GroundUnit>> groundGroups, double dir, FactionSideType side) {
        // If the point we generated is within water, generate a water group
        if(waterMask.contains(mapX, mapY) && !exclusionMask.contains(mapX, mapY)) {
            List<GroundUnit> groundUnits = generateWaterUnits(campaignSettings, side);
            UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
            UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                                        .setPlayerGeneratedGroup(false).setSpeed(0.0)
                                        .setDirection(dir).setUnits(groundUnits).build();
            groundGroups.add(g);
            return groundUnits.size();
        }
        return 0;
    }

    private List<GroundUnit> generateGroundUnitsForAirfield(CampaignSettings campaignSettings, FactionSideType side) {
        // Since we're at an airfield, we will generally try to opt to generate
        // units that are unarmed support units, and minimally some armor.
        log.debug("FIX ME");
        Coalition sideCoalition = campaignSettings.getCoalitionBySide(side);
        List<GroundUnitType> validTypes = Arrays.stream(GroundUnitType.values()).filter(gut -> {
            for(FactionType ft : sideCoalition.getFactionTypeList()) {
                if(gut.getFactions().contains(ft)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        log.debug("Generating support units for an airfield for: " + side);
        log.debug("Generating support units for an airfield for: " + validTypes);
        return new ArrayList<>(Arrays.asList(new UnarmedGroundUnit(), new ArmorGroundUnit(), new ArmorGroundUnit(), new UnarmedGroundUnit()));
    }

    private List<GroundUnit> generateWaterUnits(CampaignSettings campaignSettings, FactionSideType side) {
        // We will prefer to generate a group that has 1-2 Medium/Small combat ships
        // with 1-2 support ships along with it. We will occasionally (5% or less)
        // generate a water group that has a number of Larger or CV vessels.
        log.debug("FIX ME");
        Coalition sideCoalition = campaignSettings.getCoalitionBySide(side);
        List<GroundUnitType> validTypes = Arrays.stream(GroundUnitType.values()).filter(gut -> {
            for(FactionType ft : sideCoalition.getFactionTypeList()) {
                if(gut.getFactions().contains(ft)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        log.debug("Generating water units for: " + side);
        log.debug("Generating  water units for: " + validTypes);
        return new ArrayList<>(Arrays.asList(new UnarmedShipGroundUnit(), new ArmedShipGroundUnit(), new ArmedShipGroundUnit(), new UnarmedShipGroundUnit()));
    }

    private List<GroundUnit> generateGroundUnitsForFront(CampaignSettings campaignSettings, FactionSideType side) {
        // Since we're not at an airfield, we will generally try to opt to generate
        // units that are armed support units, and minimally some unarmed support.
        log.debug("FIX ME");
        Coalition sideCoalition = campaignSettings.getCoalitionBySide(side);
        List<GroundUnitType> validTypes = Arrays.stream(GroundUnitType.values()).filter(gut -> {
            for(FactionType ft : sideCoalition.getFactionTypeList()) {
                if(gut.getFactions().contains(ft)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        log.debug("Generating support units for: " + side);
        log.debug("Generating  support units for: " + validTypes);
        return new ArrayList<>(Arrays.asList(new UnarmedGroundUnit(), new ArmorGroundUnit(), new ArmorGroundUnit(), new UnarmedGroundUnit()));
    }

    private List<AirDefenceUnit> generateAirDefenceUnit(Class<? extends AirDefenceUnit> clazz, FactionSideType side, CampaignSettings campaignSettings, Airfield airfield) throws IllegalAccessException, InstantiationException {
        // Get the valid units we can generate for era and factions
        ConflictEraType selectedEra = campaignSettings.getSelectedEra();
        Coalition sideCoalition = campaignSettings.getCoalitionBySide(side);
        List<AirDefenceUnitType> validTypes = AirDefenceUnitType.getTypesByEra(selectedEra);
        validTypes = validTypes.stream().filter(t -> {
            for(FactionType ft : t.getFactions()) {
                if(sideCoalition.getFactionTypeList().contains(ft)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        // Create the type (AAA or SAM)
        List<AirDefenceUnit> list = new ArrayList<>();
        if(clazz.isAssignableFrom(ArtilleryAirDefenceUnit.class)) {
            validTypes = validTypes.stream().filter(t -> t.getWeaponType().equals(AirDefenceWeaponType.AAA)).collect(Collectors.toList());

            if (!validTypes.isEmpty()) {
                AirDefenceUnitType type = validTypes.get(DynamicCampaignSim.getRandomGen().nextInt(validTypes.size()));

                // For AAA create groups of size 2
                for (int i = 0; i < 2; i++) {
                    ArtilleryAirDefenceUnit unit = (ArtilleryAirDefenceUnit) clazz.newInstance();
                    unit.setUnitType(type);
                    list.add(unit);
                }
            }
        } else {
            validTypes = validTypes.stream().filter(t -> !t.getWeaponType().equals(AirDefenceWeaponType.AAA)).collect(Collectors.toList());

            if(!validTypes.isEmpty()) {

                // Since it's a SAM, see if we're generating an IR or RADAR SAM (much lower prob of Radar)
                double radarChance = 15;
                boolean isAirfield = (airfield != null);
                boolean isHomeAirfield = (airfield != null) && airfield.isHomeAirfield();
                if(isAirfield) {
                    radarChance = 50;
                }

                // If it's the Home Airfield, create the most powerful SAM group we can
                if(isHomeAirfield) {
                    radarChance = 80;
                    boolean isRadarSAM = DynamicCampaignSim.getRandomGen().nextInt(100) < radarChance;

                    // Generate a SAM Battery of the best SAM's for this side
                    if(isRadarSAM) {
                        validTypes = validTypes.stream().filter(t -> t.getWeaponType().equals(AirDefenceWeaponType.RADAR_MISSILE)).collect(Collectors.toList());
                        list.addAll(AirDefenceBatteryGenerator.getLongRangeBatteryFrom(validTypes, 2));
                    } else {
                        validTypes = validTypes.stream().filter(t -> t.getWeaponType().equals(AirDefenceWeaponType.IR_MISSILE)).collect(Collectors.toList());
                        list.addAll(AirDefenceBatteryGenerator.getIRBatteryFrom(validTypes, 2));
                    }
                } else {
                    // Generate a group of units based on the type
                    boolean isRadarSAM = DynamicCampaignSim.getRandomGen().nextInt(100) < radarChance;

                    // Generate a medium range IR battery for this side
                    if(isRadarSAM) {
                        validTypes = validTypes.stream().filter(t -> t.getWeaponType().equals(AirDefenceWeaponType.RADAR_MISSILE)).collect(Collectors.toList());
                        list.addAll(AirDefenceBatteryGenerator.getMediumRangeBatteryFrom(validTypes, 1 + DynamicCampaignSim.getRandomGen().nextInt(2)));
                    } else {
                        validTypes = validTypes.stream().filter(t -> t.getWeaponType().equals(AirDefenceWeaponType.IR_MISSILE)).collect(Collectors.toList());
                        list.addAll(AirDefenceBatteryGenerator.getIRBatteryFrom(validTypes, 2));
                    }
                }
            }
        }

        // If its a SAM type, choose a group and create all the support vehicles too

        return list;
    }
}
