package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.FactionSide;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.ArmorGroundUnit;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.UnarmedGroundUnit;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;
import sim.util.mask.PersianGulfWaterMask;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private List<GroundUnit> generateGroundUnitsForAirfield() {
        return new ArrayList<>(Arrays.asList(new UnarmedGroundUnit(), new ArmorGroundUnit(), new ArmorGroundUnit(), new UnarmedGroundUnit()));
    }

    public List<UnitGroup<GroundUnit>> generateFrontlineUnits(CampaignSettings campaignSettings, List<Point2D.Double> warfareFront, List<Point2D.Double> safeArea, FactionSide side, double groundUnitCost) {
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
        Path2D.Double waterMask = new PersianGulfWaterMask();

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
        List<UnitGroup<GroundUnit>> groundGroups = new ArrayList<>();
        for(int i = 0; i < targetIterations; i++) {
            boolean isWaterUnit = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1) < percentOfWaterUnits;
            double mapX = xLeft + (DynamicCampaignSim.getRandomGen().nextInt((int) (xRight - xLeft)));
            double mapY = warfareFront.get(0).getY() - (DynamicCampaignSim.getRandomGen().nextInt(25) * (genSouth ? -1 : 1));
            double dir = genSouth ? 0.0 : 180.0;

            if(isWaterUnit) {
                log.debug("This is supposed to be in water");
                if(waterMask.contains(mapX, mapY)) {
                    List<GroundUnit> groundUnits = generateGroundUnitsForAirfield();
                    UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
                    UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                            .setSpeed(0.0).setDirection(dir).setUnits(groundUnits).build();
                    groundGroups.add(g);
                }
            } else {
                while(waterMask.contains(mapX, mapY)) {
                    mapY = mapY - (10 * (genSouth ? -1 : 1));
                }
                mapY = mapY - DynamicCampaignSim.getRandomGen().nextInt(25) * (genSouth ? -1 : 1);

                List<GroundUnit> groundUnits = generateGroundUnitsForAirfield();
                UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
                UnitGroup<GroundUnit> g = b.setMapXLocation(mapX).setMapYLocation(mapY).setSide(side)
                        .setSpeed(0.0).setDirection(dir).setUnits(groundUnits).build();
                groundGroups.add(g);
            }
        }

        return groundGroups;
    }
}
