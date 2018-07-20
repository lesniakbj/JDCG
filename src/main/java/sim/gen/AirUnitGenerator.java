package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.FactionSide;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.main.DynamicCampaignSim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirUnitGenerator {
    private static final Logger log = LogManager.getLogger(AirUnitGenerator.class);

    private Map<FactionSide, Double> overallForceStrength;
    private double aircraftCost;
    private double helicopterCost;

    public AirUnitGenerator(Map<FactionSide, Double> overallForceStrength, double aircraftCost, double helicopterCost) {
        this.overallForceStrength = overallForceStrength;
        this.aircraftCost = aircraftCost;
        this.helicopterCost = helicopterCost;
    }

    public List<UnitGroup<AirUnit>> generateAircraftGroups(List<Airfield> airfields, FactionSide side) {
        log.debug("I was told its time to generate aircraft with the rest of my points...");
        int percentHelicopters = 10;
        boolean isHelicopter = DynamicCampaignSim.getRandomGen().nextInt(100) + 1 < percentHelicopters;


        return new ArrayList<>();
    }
}
