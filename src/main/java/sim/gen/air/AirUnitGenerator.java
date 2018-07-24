package sim.gen.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AircraftType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.enums.MajorTaskType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Aircraft;
import sim.domain.unit.air.Helicopter;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.global.Coalition;
import sim.settings.CampaignSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
    private static final double FIGHTER = .358;
    private static final double ATTACK = .059;
    private static final double RECON = .056;
    private static final double REFUELING = .129;
    private static final double TRANSPORT = .098;
    private static final double AWACS = .034;
    private static final double BOMBER = .032;
    private static final double HELI_TRANSPORT = .181;
    private static final double HELI_ATTACK = .053;

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
        return generateUnits(airfields, finalValidAircraft,  selectedType, side);
    }

    private List<UnitGroup<AirUnit>> generateUnits(List<Airfield> airfields, Set<AircraftType> validAircraftTypes, AircraftType selectedType, FactionSideType side) {
        // Get the total number of points that we can possibly use for generation
        double pointsToUse = overallForceStrength.get(side);

        // Return list
        List<UnitGroup<AirUnit>> airUnits = new ArrayList<>();

        // Ensure we generate aircraft of the type that the User is playing as (always 8 or 16 aircraft)
        List<UnitGroup<AirUnit>> playerAircraft = new ArrayList<>();
        if(selectedType != null) {
            log.debug("Generating player aircraft... " + selectedType);
            playerAircraft = createAirGroupsOfType(validAircraftTypes, selectedType, side);
            airUnits.addAll(playerAircraft);
        }

        // Assume that we are generating all aircraft (not helicopters), as they cost more, giving
        // us a upper bound for the # units we can create
        double totalAircraft = pointsToUse / aircraftCost;

        // Generate every type for the campaign
        double totalFighter = Math.floor(totalAircraft * FIGHTER);
        List<UnitGroup<AirUnit>> fighters = createAirGroupsOfType(validAircraftTypes, totalFighter, MajorTaskType.FIGHTER, Arrays.asList(SubTaskType.CAP, SubTaskType.INTERCEPT), side);
        airUnits.addAll(fighters);

        double totalAttack = Math.floor(totalAircraft * ATTACK);
        List<UnitGroup<AirUnit>> attack = createAirGroupsOfType(validAircraftTypes, totalAttack, MajorTaskType.ATTACK, Arrays.asList(SubTaskType.CAS, SubTaskType.GROUND_STRIKE), side);
        airUnits.addAll(attack);

        double totalRecon = Math.floor(totalAircraft * RECON);
        List<UnitGroup<AirUnit>> recon = createAirGroupsOfType(validAircraftTypes, totalRecon, MajorTaskType.RECON, Collections.singletonList(SubTaskType.RECON), side);
        airUnits.addAll(recon);

        double totalRefuel = Math.floor(totalAircraft * REFUELING);
        List<UnitGroup<AirUnit>> refuel = createAirGroupsOfType(validAircraftTypes, totalRefuel, MajorTaskType.REFUELING, Collections.singletonList(SubTaskType.REFUELING), side);
        airUnits.addAll(refuel);

        double totalTransport = Math.floor(totalAircraft * TRANSPORT);
        List<UnitGroup<AirUnit>> transport = createAirGroupsOfType(validAircraftTypes, totalTransport, MajorTaskType.TRANSPORT, Arrays.asList(SubTaskType.TRANSPORT, SubTaskType.AIRLIFT), side);
        airUnits.addAll(transport);

        double totalAwacs = Math.floor(totalAircraft * AWACS);
        List<UnitGroup<AirUnit>> awacs = createAirGroupsOfType(validAircraftTypes, totalAwacs, MajorTaskType.AWACS, Collections.singletonList(SubTaskType.AWACS), side);
        airUnits.addAll(awacs);

        double totalBomber = Math.floor(totalAircraft * BOMBER);
        List<UnitGroup<AirUnit>> bomber = createAirGroupsOfType(validAircraftTypes, totalBomber, MajorTaskType.BOMBER, Collections.singletonList(SubTaskType.BOMBER), side);
        airUnits.addAll(bomber);

        double totalAttackHeli = Math.floor(totalAircraft * HELI_ATTACK);
        List<UnitGroup<AirUnit>> attackHeli = createAirGroupsOfType(validAircraftTypes, totalAttackHeli, MajorTaskType.HELI_ATTACK, Arrays.asList(SubTaskType.CAS, SubTaskType.GROUND_STRIKE), side);
        airUnits.addAll(attackHeli);

        double totalTransportHeli = Math.floor(totalAircraft * HELI_TRANSPORT);
        List<UnitGroup<AirUnit>> transportHeli = createAirGroupsOfType(validAircraftTypes, totalTransportHeli, MajorTaskType.HELI_TRANSPORT, Arrays.asList(SubTaskType.TRANSPORT, SubTaskType.AIRLIFT), side);
        airUnits.addAll(transportHeli);

        // Calculate the remaining cost
        double totalCost = 0;
        totalCost += playerAircraft.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += fighters.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += attack.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += recon.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += refuel.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += transport.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += awacs.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += bomber.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * aircraftCost;
        totalCost += attackHeli.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * helicopterCost;
        totalCost += transportHeli.stream().mapToInt(UnitGroup::getNumberOfUnits).sum() * helicopterCost;
        overallForceStrength.put(side, (pointsToUse - totalCost));

        // Assign the units to their various airfields...
        assignToAirfields(airfields, airUnits);
        log.debug(airUnits);

        return airUnits;
    }

    private void assignToAirfields(List<Airfield> airfields, List<UnitGroup<AirUnit>> airUnits) {
        long totalGroups = airUnits.size();
        long totalAirfields = airfields.size();
        int total = (int)Math.floor(totalGroups / totalAirfields);

        log.debug("Assigning " + totalGroups + " Aircraft Groups to " + totalAirfields + " Airfields");
        for(Airfield airfield : airfields) {
            for(int i = 0; i < total; i++) {
                UnitGroup<AirUnit> group = airUnits.remove(DynamicCampaignSim.getRandomGen().nextInt(airUnits.size()));
                group.setMapXLocation(airfield.getAirfieldType().getAirfieldMapPosition().getX());
                group.setMapYLocation(airfield.getAirfieldType().getAirfieldMapPosition().getY());
                airfield.addAircraftGroup(group);
            }
        }

        while(!airUnits.isEmpty()) {
            log.debug("Assigning spare groups");
            Airfield airfield = airfields.get(DynamicCampaignSim.getRandomGen().nextInt(airfields.size()));
            UnitGroup<AirUnit> group = airUnits.remove(DynamicCampaignSim.getRandomGen().nextInt(airUnits.size()));
            group.setMapXLocation(airfield.getAirfieldType().getAirfieldMapPosition().getX());
            group.setMapYLocation(airfield.getAirfieldType().getAirfieldMapPosition().getY());
            airfield.addAircraftGroup(group);
        }
    }

    private List<UnitGroup<AirUnit>> createAirGroupsOfType(Set<AircraftType> validAircraftTypes, AircraftType selectedType, FactionSideType side) {
        List<UnitGroup<AirUnit>> groups = new ArrayList<>();
        if (!validAircraftTypes.contains(selectedType)){
            log.debug("Couldn't create player aircraft!");
            return groups;
        }

        // Create 4 groups of 4 units each
        for(int n = 0; n < 4; n++) {
            List<AirUnit> airUnits = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Aircraft aircraft = new Aircraft(selectedType);
                airUnits.add(aircraft);
            }

            UnitGroup.Builder<AirUnit> b = new UnitGroup.Builder<>();
            b.setUnits(airUnits)
                    .setDirection(0.0)
                    .setGenerate(false)
                    .setPlayerGeneratedGroup(true)
                    .setSide(side)
                    .setSpeed(0.0)
                    .setMapXLocation(-1.0)
                    .setMapYLocation(-1.0);

            log.debug("Building player aircraft");
            groups.add(b.build());
        }

        return groups;
    }

    private List<UnitGroup<AirUnit>> createAirGroupsOfType(Set<AircraftType> validAircraftTypes, double totalToCreate, MajorTaskType generalTaskType, List<SubTaskType> requiredSpecificTaskTypes, FactionSideType side) {
        // We always want to create an even number of groups
        int actualToCreate = (int)totalToCreate;
        actualToCreate = (actualToCreate % 2 != 0) ? actualToCreate - 1 : actualToCreate;
        log.debug("Total to create of " + generalTaskType + ": " + actualToCreate);

        // Generate the standard group size based on the type of
        // units that we are creating; if we pass in a 0, that means
        // the group size can shift between 2 and 4 for that type.
        // The default group size will always be 2.
        List<UnitGroup<AirUnit>> createdAirGroups = new ArrayList<>();
        int groupSize = 2;
        switch (generalTaskType) {
            case FIGHTER:
                groupSize = 0;
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, false);
                break;
            case ATTACK:
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, false);
                break;
            case RECON:
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, false);
                break;
            case REFUELING:
                groupSize = 1;
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, false);
                break;
            case TRANSPORT:
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, false);
                break;
            case AWACS:
                groupSize = 1;
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, false);
                break;
            case BOMBER:
                groupSize = 1;
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, false);
                break;
            case HELI_ATTACK:
                groupSize = 0;
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, true);
                break;
            case HELI_TRANSPORT:
                createdAirGroups = createAirGroupsOfTypeWithGroupSize(validAircraftTypes, actualToCreate, generalTaskType, requiredSpecificTaskTypes, groupSize, side, true);
                break;
        }

        return createdAirGroups;
    }

    private List<UnitGroup<AirUnit>> createAirGroupsOfTypeWithGroupSize(Set<AircraftType> validAircraftTypes, int actualToCreate, MajorTaskType generalTaskType, List<SubTaskType> requiredSpecificTaskTypes, int groupSize, FactionSideType side, boolean isHelicopter) {
        boolean genSize = false;
        if(groupSize == 0) {
            genSize = true;
        }

        List<UnitGroup<AirUnit>> generatedGroups = new ArrayList<>();
        while(actualToCreate >= 0) {
            List<AircraftType> types = getAircraftTypesForTasks(validAircraftTypes, requiredSpecificTaskTypes, isHelicopter);
            AircraftType selectedType = types.get(DynamicCampaignSim.getRandomGen().nextInt(types.size()));

            if(genSize) {
                groupSize = (DynamicCampaignSim.getRandomGen().nextBoolean()) ? 4 : 2;
            }

            // Only create the group if we have the resources to
            if(actualToCreate - groupSize > 0) {
                List<AirUnit> airUnits = new ArrayList<>();
                for (int i = 0; i < groupSize; i++) {
                    AirUnit aircraft = isHelicopter ? new Helicopter(selectedType) : new Aircraft(selectedType);
                    aircraft.setAssignedTaskType(generalTaskType);
                    airUnits.add(aircraft);
                }

                UnitGroup.Builder<AirUnit> b = new UnitGroup.Builder<>();
                b.setUnits(airUnits)
                        .setDirection(0.0)
                        .setGenerate(false)
                        .setPlayerGeneratedGroup(false)
                        .setSide(side)
                        .setSpeed(0.0)
                        .setMapXLocation(-1.0)
                        .setMapYLocation(-1.0);

                generatedGroups.add(b.build());
            }

            // Always subtract the cost, so we can break the loop
            actualToCreate -= groupSize;
        }
        return generatedGroups;
    }

    private List<AircraftType> getAircraftTypesForTasks(Set<AircraftType> validAircraftTypes, List<SubTaskType> requiredSpecificTaskTypes, boolean isHelicopter) {
        List<AircraftType> types = new ArrayList<>();
        for(AircraftType aircraft : validAircraftTypes) {
            if(aircraft.getPossibleTasks().containsAll(requiredSpecificTaskTypes) && aircraft.isHelicopter() == isHelicopter) {
                types.add(aircraft);
            }
        }
        return types;
    }
}
