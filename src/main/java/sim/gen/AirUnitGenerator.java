package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AircraftType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.global.Coalition;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AirUnitGenerator {
    private static final Logger log = LogManager.getLogger(AirUnitGenerator.class);

    private Map<FactionSideType, Double> overallForceStrength;
    private double aircraftCost;
    private double helicopterCost;

    // We want the roles to be distributed in a certain way, so less assets of a certain type
    // are generated (making them more valuable). The following values were derived from
    // the US Armed forces Aircraft makeup, and adjusted for generation.
    private static final double FIGHTER = 35.8;
    private static final double ATTACK = 5.9;
    private static final double RECON = 5.6;
    private static final double REFUELING = 12.9;
    private static final double TRANSPORT = 9.8;
    private static final double AWACS = 3.4;
    private static final double BOMBER = 3.2;
    private static final double HELI_TRANSPORT = 18.1;
    private static final double HELI_ATTACK = 5.3;

    public AirUnitGenerator(Map<FactionSideType, Double> overallForceStrength, double aircraftCost, double helicopterCost) {
        this.overallForceStrength = overallForceStrength;
        this.aircraftCost = aircraftCost;
        this.helicopterCost = helicopterCost;
    }

    public List<UnitGroup<AirUnit>> generateAircraftGroups(CampaignSettings campaignSettings, List<Airfield> airfields, FactionSideType side) {
        // Check to see if we are generating aircraft for the users side
        AircraftType selectedType = null;
        if (campaignSettings.getPlayerSelectedSide().equals(side)) {
            selectedType = campaignSettings.getSelectedAircraft();
        }

        // Get the aircraft set to use for generation, filtered by country and era
        Coalition coalition = campaignSettings.getCoalitionBySide(side);
        List<FactionType> coalitionFactions = coalition.getFactionTypeList();
        List<AircraftType> eraAircraftTypes = AircraftType.getAircraftByEra(campaignSettings.getSelectedEra());
        List<AircraftType> factionAircraftTypes = coalitionFactions.stream().map(AircraftType::getAircraftByFactionType).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        factionAircraftTypes.retainAll(eraAircraftTypes);
        Set<AircraftType> finalValidAircraft = new HashSet<>(factionAircraftTypes);
        log.debug("Generating using the following aircraft: " + finalValidAircraft.stream().map(AircraftType::getAircraftName).collect(Collectors.joining(", ")));

        // Generate aircraft for the side
        double percentHelicopters = HELI_TRANSPORT + HELI_ATTACK;
        List<UnitGroup<AirUnit>> generatedUnits;
        if(selectedType != null) {
            generatedUnits = generateUnitsForSelectedSide(percentHelicopters, airfields, finalValidAircraft,  selectedType, side);
        } else {
            generatedUnits = generateUnits(percentHelicopters, airfields, finalValidAircraft, side);
        }

        return generatedUnits;
    }

    private List<UnitGroup<AirUnit>> generateUnits(double percentHelicopters, List<Airfield> airfields, Set<AircraftType> validAircraftTypes, FactionSideType side) {
        log.debug("I was told its time to generate units for the enemy");
        boolean isHelicopter = DynamicCampaignSim.getRandomGen().nextInt(100) + 1 < percentHelicopters;
        double pointsToUse = overallForceStrength.get(side);
        log.debug("Pts: " + pointsToUse);

        // Assume that we are generating all aircraft, as they cost more, giving us
        // an lower bound for the units we can create
        double iterations = pointsToUse / aircraftCost;
        log.debug("Iterations: " + iterations);

        return new ArrayList<>();
    }

    private List<UnitGroup<AirUnit>> generateUnitsForSelectedSide(double percentHelicopters, List<Airfield> airfields, Set<AircraftType> validAircraftTypes, AircraftType selectedType, FactionSideType side) {
        log.debug("I was told its time to generate units for friendlies");
        boolean isHelicopter = DynamicCampaignSim.getRandomGen().nextInt(100) + 1 < percentHelicopters;
        double pointsToUse = overallForceStrength.get(side);
        log.debug("Pts: " + pointsToUse);

        // Assume that we are generating all aircraft, as they cost more, giving us
        // an lower bound for the units we can create
        double iterations = pointsToUse / aircraftCost;
        log.debug("Iterations: " + iterations);

        // Generate the major "squadrons", ie. groups of planes that fill the same role.


        return new ArrayList<>();
    }
}
