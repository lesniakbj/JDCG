package sim.gen.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.CommanderAction;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.Aircraft;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static sim.domain.enums.StaticLists.DEFAULT_LOADOUTS;

public class ATOGenerator {
    private static final Logger log = LogManager.getLogger(ATOGenerator.class);

    public void generateTestMissionForCoalition(CampaignSettings campaign, CoalitionManager coalitionManager, Date date) {
        List<Waypoint> generatedWaypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition(), AirfieldType.KHASAB.getAirfieldMapPosition(), SubTaskType.INTERCEPT, MapType.PERSIAN_GULF);

        UnitGroup.Builder<Aircraft> aircraftBuilder = new UnitGroup.Builder<>();
        aircraftBuilder.setUnits(Arrays.asList(new Aircraft(AircraftType.FA_18C_LOT20), new Aircraft(AircraftType.FA_18C_LOT20)))
            .setSide(FactionSideType.BLUEFOR)
            .setMapLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition());

        Mission.Builder builder = new Mission.Builder();
        builder.setMissionMap(campaign.getSelectedMap().getMapType())
            .setMissionType(SubTaskType.CAS)
            .setMissionAircraft(aircraftBuilder.build())
            .setMissionWaypoints(generatedWaypoints)
            .setUpcomingMissionDate(date, 60)
            .setIsClientMission(false)
            .setPlayerAircraftNumber(0)
            .setMissionComplete(false)
            .setUpdateRate(1)
            .setShouldGenerateMission(false)
            .setStartingAirfield(AirfieldType.AL_DHAFRA_AIRBASE)
            .setTimeOnStation(30)
            .setMissionMunitions(DEFAULT_LOADOUTS.get(AircraftType.FA_18C_LOT20).get(SubTaskType.INTERCEPT));

        coalitionManager.getCoalitionMissionManager().addMission(builder.build());
    }

    public CommanderAction generateATO(CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {
        return new CommanderAction();
    }

    /*
    public void updateAndGenerate(CampaignSettings campaignSettings, GlobalSimSettings simSettings, CoalitionManager blueforCoalitionManager, CoalitionManager redforCoalitionManager) {
        generateATOForCoalition(campaignSettings, simSettings, blueforCoalitionManager, redforCoalitionManager, FactionSideType.BLUEFOR);
        generateATOForCoalition(campaignSettings, simSettings, redforCoalitionManager, blueforCoalitionManager, FactionSideType.REDFOR);
    }

    private void generateATOForCoalition(CampaignSettings campaignSettings, GlobalSimSettings simSettings, CoalitionManager friendlyManager, CoalitionManager enemyManager, FactionSideType side) {
        log.debug("Creating ATO for: " + side);
        // ATO Generation takes the following parameters:
        //  1) Critical Targets that need to be Attacked
        //  2) Critical Threats to Defensive Targets
        //  3) The available air groups and their airfields
        //  4) Player playable groups (will prefer to try to generate user playable missions)
        //  5) Whether we have support assets available
        //  6) The currently running missions

        // Critical Targets
        List<GroundUnit> criticalAttackTargets = enemyManager.getCoalitionAirfields().stream().map(Airfield::getCriticalStructures).flatMap(Collection::stream).collect(Collectors.toList());
        // criticalAttackTargets.addAll(getCriticalGroundUnits());

        // Critical Threats
        List<Airfield> coalitionAirfields = friendlyManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> coalitionAirDefences = friendlyManager.getCoalitionAirDefences();
        List<Structure> criticalDefenceTargets = coalitionAirfields.stream().map(Airfield::getCriticalStructures).flatMap(Collection::stream).collect(Collectors.toList());
        List<UnitGroup<AirUnit>> criticalAirThreats = findCriticalAirThreats(coalitionAirfields, coalitionAirDefences, criticalDefenceTargets, enemyManager);

        // Available Air Groups
        Map<Airfield, List<UnitGroup<AirUnit>>> availableGroups = coalitionAirfields.stream().collect(Collectors.toMap(a -> a, Airfield::getStationedAircraft));
        List<UnitGroup<AirUnit>> playableGroups = coalitionAirfields.stream().map(Airfield::getStationedAircraft).flatMap(Collection::stream).filter(UnitGroup::isPlayerGeneratedGroup).collect(Collectors.toList());

        // Currently running missions
        List<Mission> runningMissions = friendlyManager.getCoalitionMissionManager().getPlannedMissions();

        // Generate the ATO for the friendly manager
        generateATO(friendlyManager, criticalAttackTargets, criticalAirThreats, availableGroups, playableGroups, runningMissions);
    }

    private void generateATO(CoalitionManager friendlyManager, List<GroundUnit> criticalAttackTargets, List<UnitGroup<AirUnit>> criticalAirThreats, Map<Airfield, List<UnitGroup<AirUnit>>> availableGroups, List<UnitGroup<AirUnit>> playableGroups, List<Mission> runningMissions) {
        log.debug("Attack Targets: " + criticalAttackTargets);
        log.debug("Critical Air Threats: " + criticalAirThreats);
        log.debug("Available Groups: " + availableGroups);
        log.debug("Player Available Groups: " + playableGroups);
        log.debug("Running Missions: " + runningMissions);
    }

    private List<UnitGroup<AirUnit>> findCriticalAirThreats(List<Airfield> coalitionAirfields, List<UnitGroup<AirDefenceUnit>> coalitionAirDefences, List<Structure> criticalDefenceTargets, CoalitionManager enemyManager) {
        log.debug("Looking for enemy aircraft that are a threat to: " + coalitionAirfields);
        log.debug("Looking for enemy aircraft that are a threat to: " + criticalDefenceTargets);
        log.debug("Threat of critical target is reduced by: " + coalitionAirDefences);
        log.debug("Looking at the aircraft from enemy: " + enemyManager.getCoalitionAirGroups());
        return new ArrayList<>();
    }
    */
}
