package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.GroundUnit;
import sim.domain.UnitGroup;
import sim.domain.enums.ArmorGroundUnit;
import sim.domain.enums.FactionSide;
import sim.domain.enums.UnarmedGroundUnit;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;

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
                UnitGroup.Builder<GroundUnit> b = new UnitGroup.Builder<>();
                b.setMapXLocation(loc.getX() + (DynamicCampaignSim.getRandomGen().nextInt(6) * (xNeg ? -1 : 1)))
                        .setMapYLocation(loc.getY() + (DynamicCampaignSim.getRandomGen().nextInt(6) * (yNeg ? -1 : 1)))
                        .setSide(side)
                        .setSpeed(0.0)
                        .setDirection((side == FactionSide.BLUEFOR) ? 0.0 : 180.0)
                        .setUnits(new ArrayList<>(Arrays.asList(new UnarmedGroundUnit(), new ArmorGroundUnit(), new ArmorGroundUnit(), new UnarmedGroundUnit())));
                UnitGroup<GroundUnit> g = b.build();

                airfieldDefenceGroups.add(g);
                totalGenerated += 1;
            }
            // .. finally, add it to the map.
            returnMap.put(field, airfieldDefenceGroups);
        }

        // Calculate the cost of the units
        double current = overallForceStrength.get(side);
        double cost = groundUnitCost * totalGenerated;
        log.debug(current);
        log.debug(cost);
        overallForceStrength.put(side, current - cost);

        return returnMap;
    }
}
