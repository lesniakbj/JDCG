package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AircraftType;
import sim.domain.enums.FactionSide;
import sim.domain.enums.FactionType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.global.Coalition;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<UnitGroup<AirUnit>> generateAircraftGroups(CampaignSettings campaignSettings, List<Airfield> airfields, FactionSide side) {
        // Check to see if we are generating aircraft for the users side
        AircraftType selectedType = null;
        if (campaignSettings.getPlayerSelectedSide().equals(side)) {
            selectedType = campaignSettings.getSelectedAircraft();
        }

        // Get the aircraft set to use for generation
        Coalition faction = campaignSettings.getCoalitionBySide(side);
        List<FactionType> types = faction.getFactionTypeList();
        Set<AircraftType> validTypes = new HashSet<>();
        for(FactionType type : types) {
            List<AircraftType> aircraftTypes = AircraftType.getAircraftByFactionType(type);
            validTypes.addAll(aircraftTypes);
        }
        log.debug("Generating using the following aircraft: " + validTypes.stream().map(AircraftType::getAircraftName).collect(Collectors.joining(",")));

        // Generate aircraft for the side
        int percentHelicopters = 10;
        List<UnitGroup<AirUnit>> generatedUnits;
        if(selectedType != null) {
            generatedUnits = generateUnitsForSelectedSide(percentHelicopters, airfields, validTypes,  selectedType, side);
        } else {
            generatedUnits = generateUnits(percentHelicopters, airfields, validTypes, side);
        }

        return generatedUnits;
    }

    private List<UnitGroup<AirUnit>> generateUnits(int percentHelicopters, List<Airfield> airfields, Set<AircraftType> validAircraftTypes, FactionSide side) {
        log.debug("I was told its time to generate units for the enemy");
        boolean isHelicopter = DynamicCampaignSim.getRandomGen().nextInt(100) + 1 < percentHelicopters;

        return new ArrayList<>();
    }

    private List<UnitGroup<AirUnit>> generateUnitsForSelectedSide(int percentHelicopters, List<Airfield> airfields, Set<AircraftType> validAircraftTypes, AircraftType selectedType, FactionSide side) {
        log.debug("I was told its time to generate units for friendlies");
        boolean isHelicopter = DynamicCampaignSim.getRandomGen().nextInt(100) + 1 < percentHelicopters;

        return new ArrayList<>();
    }
}
