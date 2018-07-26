package sim.gen.air;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.AIActionType;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.gen.mission.AirUnitMissionGenerator;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

public class PackageGenerator {
    private static final Logger log = LogManager.getLogger(PackageGenerator.class);

    private AirUnitMissionGenerator missionGenerator;

    public PackageGenerator() {
        this.missionGenerator = missionGenerator;
    }

    public List<List<Mission>> generateMissionPackages(CampaignSettings campaignSettings, List<AIAction> actions, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager, Date currentCampaignDate) {
        // Friendly Information
        List<Airfield> friendlyAirfields = friendlyCoalitionManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> friendlyAirDefences = friendlyCoalitionManager.getCoalitionAirDefences();
        List<UnitGroup<GroundUnit>> friendlyGroundUnits = friendlyCoalitionManager.getCoalitionFrontlineGroups();

        // Enemy Information
        List<Airfield> enemyAirfields = enemyCoalitionManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> enemyAirDefences = enemyCoalitionManager.getCoalitionAirDefences();
        List<UnitGroup<GroundUnit>> enemyGroundUnits = enemyCoalitionManager.getCoalitionFrontlineGroups();
        List<UnitGroup<AirUnit>> enemyActiveAirGroups = enemyCoalitionManager.getMissionManager().getPlannedMissions().stream().filter(Mission::isActive).map(Mission::getMissionAircraft).collect(Collectors.toList());

        // For each action, generate a package of missions
        List<List<Mission>> allPackageMissions = new ArrayList<>();
        for(AIAction action : actions) {
            UnitGroup<AirUnit> g = action.getActionGroup();
            Airfield startingAirfield = friendlyAirfields.stream().filter(a -> a.getAirfieldType().getAirfieldMapPosition().getX() == g.getMapXLocation() && a.getAirfieldType().getAirfieldMapPosition().getY() == g.getMapYLocation()).findFirst().orElse(null);

            if(startingAirfield == null || g.getNumberOfUnits() == 0 || action.getType().equals(AIActionType.NOTHING)) {
                continue;
            }

            List<UnitGroup<AirUnit>> units = friendlyCoalitionManager.getCoalitionAirGroupsMap().get(startingAirfield);
            units.remove(g);

            List<UnitGroup<AirUnit>> supportingUnits = getSupportingUnitsForMissionType(action.getType(), g, units);
            for(UnitGroup<AirUnit> support : supportingUnits) {
                units.remove(support);
            }


            // Create a list of missions for all of the units that we gathered for the package
            allPackageMissions.addAll(generateMissionsForPackage(action.getType(), g, supportingUnits));

            // When we plan a mission for a group, remove it from the airfield,
            // that it was stationed at. This means that it can not be used again
            // for future missions until it returns.
            friendlyCoalitionManager.updateCoalitionAirGroups(startingAirfield, units);
        }

        return allPackageMissions;
    }

    private List<List<Mission>> generateMissionsForPackage(AIActionType type, UnitGroup<AirUnit> g, List<UnitGroup<AirUnit>> supportingUnits) {
        return new ArrayList<>();
    }

    // Select other aircraft to support this mission
    // If the mission is not Intercept or a Helicopter:
    //     - Select CAP/Escort planes
    //     - Send CAP/Escort to location 5-10 minutes BEFORE mission flight
    //     - Have CAP/Escort stay on station for duration of mission
    private List<UnitGroup<AirUnit>> getSupportingUnitsForMissionType(AIActionType type, UnitGroup<AirUnit> missionGroup, List<UnitGroup<AirUnit>> units) {
        List<UnitGroup<AirUnit>> supportUnits = new ArrayList<>();
        boolean isHelicopter = missionGroup.getNumberOfUnits() != 0 && missionGroup.getGroupUnits().get(0).getAircraftType().isHelicopter();
        switch (type) {
            // Try to find 2-4 supporting units (ie. other CAP flights, Escort, etc)
            case ATTACK_AIRBASE_STRUCTURE:
            case ATTACK_AIR_DEFENCE:
            case ATTACK_GROUND_UNIT:
                supportUnits = findSupportingUnitsForGroundAttack(missionGroup, isHelicopter, units);
                break;
            // Check the type of flight, and add CAP/CAS as needed
            case DEFEND_AIRBASE_STRUCTURE:
            case DEFEND_AIR_DEFENCE:
            case DEFEND_GROUND_UNIT:
                supportUnits = findSupportingUnitsForGroundDefence(missionGroup, isHelicopter, units);
                break;
            // Check the size of flight, ensure 2 planes
            case INTERCEPT_FLIGHT:
                supportUnits = findSupportingUnitsForIntercept(missionGroup, isHelicopter, units);
                break;
            // Always ensure an Escort of 2 planes
            case TRANSPORT_AIR_DEFENCE:
            case TRANSPORT_GROUND_UNIT:
                supportUnits = findSupportingUnitsForEscort(missionGroup, isHelicopter, units);
                break;
            // Get the maximum number of escorts we can
            case STRATEGIC_BOMBING:
                supportUnits = findSupportingUnitsForStrategicBombing(missionGroup, isHelicopter, units);
                break;
            // Goes alone
            case RECON:
                break;
        }
        return supportUnits;
    }

    private List<UnitGroup<AirUnit>> findSupportingUnitsForStrategicBombing(UnitGroup<AirUnit> missionGroup, boolean isHelicopter, List<UnitGroup<AirUnit>> units) {
        return new ArrayList<>();
    }

    private List<UnitGroup<AirUnit>> findSupportingUnitsForGroundDefence(UnitGroup<AirUnit> missionGroup, boolean isHelicopter, List<UnitGroup<AirUnit>> units) {
        boolean isGroundStrike = missionGroup.getNumberOfUnits() != 0 && missionGroup.getGroupUnits().get(0).getAircraftType().getPossibleTasks().contains(SubTaskType.GROUND_STRIKE);
        boolean isCAS = missionGroup.getNumberOfUnits() != 0 && missionGroup.getGroupUnits().get(0).getAircraftType().getPossibleTasks().contains(SubTaskType.CAS);

        List<UnitGroup<AirUnit>> air = findUnitsOfSubType(units, isHelicopter, Arrays.asList(SubTaskType.ESCORT, SubTaskType.CAP));
        List<UnitGroup<AirUnit>> ground = findUnitsOfSubType(units, isHelicopter, Arrays.asList(SubTaskType.GROUND_STRIKE, SubTaskType.CAS));
        List<UnitGroup<AirUnit>> groups = new ArrayList<>();
        if(isGroundStrike || isCAS) {
            groups.addAll(findNumberOfUnits(2 + DynamicCampaignSim.getRandomGen().nextInt(3), air));
            groups.addAll(findNumberOfUnits(1, ground));
        } else {
            groups.addAll(findNumberOfUnits(2 + DynamicCampaignSim.getRandomGen().nextInt(3), ground));
            groups.addAll(findNumberOfUnits(1, air));
        }

        return groups;
    }

    private List<UnitGroup<AirUnit>> findSupportingUnitsForGroundAttack(UnitGroup<AirUnit> missionGroup, boolean isHelicopter, List<UnitGroup<AirUnit>> units) {
        // Try to find 2-4 supporting units (ie. other CAS flights, Escort, etc)
        List<UnitGroup<AirUnit>> escorts = findUnitsOfSubType(units, isHelicopter, Collections.singletonList(SubTaskType.ESCORT));
        List<UnitGroup<AirUnit>> groundStrikers = findUnitsOfSubType(units, isHelicopter, Arrays.asList(SubTaskType.GROUND_STRIKE, SubTaskType.CAS));
        List<UnitGroup<AirUnit>> groups = new ArrayList<>(escorts);
        groups.addAll(groundStrikers);

        return findNumberOfUnits(2 + DynamicCampaignSim.getRandomGen().nextInt(3), groups);
    }

    private List<UnitGroup<AirUnit>> findSupportingUnitsForEscort(UnitGroup<AirUnit> missionGroup, boolean isHelicopter, List<UnitGroup<AirUnit>> units) {
        List<UnitGroup<AirUnit>> matchedUnits = findUnitsOfSubType(units, isHelicopter, Collections.singletonList(SubTaskType.ESCORT));
        return findNumberOfUnits(1, matchedUnits);
    }

    private List<UnitGroup<AirUnit>> findSupportingUnitsForIntercept(UnitGroup<AirUnit> missionGroup, boolean isHelicopter, List<UnitGroup<AirUnit>> units) {
        if(missionGroup.getNumberOfUnits() >= 2) {
            return new ArrayList<>();
        }

        List<UnitGroup<AirUnit>> matchedUnits = findUnitsOfSubType(units, isHelicopter, Collections.singletonList(SubTaskType.INTERCEPT));
        return findNumberOfUnits(1, matchedUnits);
    }

    private List<UnitGroup<AirUnit>> findNumberOfUnits(int number, List<UnitGroup<AirUnit>> matchedUnits) {
        List<UnitGroup<AirUnit>> units = new ArrayList<>();
        for(int i = 0; i < number && i < matchedUnits.size(); i++) {
            units.add(matchedUnits.get(DynamicCampaignSim.getRandomGen().nextInt(matchedUnits.size())));
        }
        return units;
    }

    private List<UnitGroup<AirUnit>> findUnitsOfSubType(List<UnitGroup<AirUnit>> units, boolean isHelicopter, List<SubTaskType> subTaskTypes) {
        List<UnitGroup<AirUnit>> groups = new ArrayList<>();
        for(UnitGroup<AirUnit> unit : units) {
            List<AirUnit> availableTypes = unit.getGroupUnits().stream()
                                                                .filter(a -> a.getAircraftType().isHelicopter() == isHelicopter)
                                                                .filter(a -> a.getAircraftType().getPossibleTasks().containsAll(subTaskTypes))
                                                                .collect(Collectors.toList());
            if(!availableTypes.isEmpty()) {
                groups.add(unit);
            }
        }
        return groups;
    }
}
