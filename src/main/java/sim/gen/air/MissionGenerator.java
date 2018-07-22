package sim.gen.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.TaskGenerationSetting;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MajorTaskType;
import sim.domain.enums.MapType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Aircraft;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.settings.CampaignSettings;
import sim.manager.CoalitionManager;
import sim.main.DynamicCampaignSim;
import sim.settings.GlobalSimSettings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static sim.domain.enums.StaticLists.DEFAULT_LOADOUTS;

public class MissionGenerator {
    private static final Logger log = LogManager.getLogger(MissionGenerator.class);

    private static final int MAX_PLANNABLE_MISSIONS = 25;

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

    public void updateAndGenerate(CampaignSettings campaignSettings, GlobalSimSettings simSettings, CoalitionManager blueforCoalitionManager, CoalitionManager redforCoalitionManager) {
        updateAndGenerateForCoalition(campaignSettings, simSettings, blueforCoalitionManager, redforCoalitionManager);
        updateAndGenerateForCoalition(campaignSettings, simSettings, redforCoalitionManager, blueforCoalitionManager);
    }

    private void updateAndGenerateForCoalition(CampaignSettings campaignSettings, GlobalSimSettings simSettings, CoalitionManager friendlyManager, CoalitionManager enemyManager) {
        // Check if we even can to generate new missions
        List<Mission> currentMissions = friendlyManager.getCoalitionMissionManager().getPlannedMissions();
        if(currentMissions.size() == MAX_PLANNABLE_MISSIONS) {
            log.debug("Max planned mission threshold hit, wait for missions to finish");
            return;
        }

        // Check to see if there are any threats we need to respond to

        // Generate Tanker/AWACS missions
        if(!friendlyManager.getCoalitionMissionManager().isHasAWACSActive()) {
            log.debug("Time to generate an AWACS mission");
        }

        if(!friendlyManager.getCoalitionMissionManager().isHasTankerActive()) {
            log.debug("Time to generate a Tanker mission");
        }

        // Otherwise, get the generation ratios and generate missions based on that
        List<TaskGenerationSetting> taskRatios = simSettings.getTaskRatios().stream().sorted(Comparator.comparingInt(TaskGenerationSetting::getGenerationPriority)).collect(Collectors.toList());
        log.debug(taskRatios);
        for(TaskGenerationSetting task : taskRatios) {
            boolean shouldGenerateMission;
            switch (task.getTaskType()) {
                // The following get created on their own...
                case REFUELING:
                case AWACS:
                    continue;
                // Only plan on "strategic missions"
                case BOMBER:
                    continue;
                // Special case, plan these whenever there is an available slot (100%)
                case HELI_TRANSPORT:
                case HELI_ATTACK:
                    shouldGenerateMission = true;
                    break;
                default:
                    shouldGenerateMission = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1) < task.getGenerationChance();
                    break;
            }
            log.debug("Mission of " + task.getTaskType().name() + " should be generated?: " + shouldGenerateMission);

            if(shouldGenerateMission) {
                List<Mission> missions = generatePackageForTypeForCoalition(campaignSettings, simSettings, friendlyManager, enemyManager, task.getTaskType());
            }
        }
    }

    private List<Mission> generatePackageForTypeForCoalition(CampaignSettings campaignSettings, GlobalSimSettings simSettings, CoalitionManager friendlyManager, CoalitionManager enemyManager, MajorTaskType taskType) {
        List<UnitGroup<AirUnit>> availableAircraft = friendlyManager.getCoalitionAirGroups();
        log.debug("I have these aircraft to make a mission with: " + availableAircraft);
        return null;
    }
}
