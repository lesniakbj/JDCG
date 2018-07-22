package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.Aircraft;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.main.CampaignSettings;
import sim.main.CoalitionManager;
import sim.main.DynamicCampaignSim;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static sim.domain.enums.StaticLists.DEFAULT_LOADOUTS;

public class MissionGenerator {
    private static final Logger log = LogManager.getLogger(MissionGenerator.class);

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

    public void updateAndGenerate(CampaignSettings campaignSettings, CoalitionManager blueforCoalitionManager, CoalitionManager redforCoalitionManager) {
        // We need to know the player's Coalition Manager so we can generate missions for their groups more intelligently
        FactionSideType playerSide = campaignSettings.getPlayerSelectedSide() == FactionSideType.BLUEFOR ? FactionSideType.BLUEFOR : FactionSideType.REDFOR;
        CoalitionManager playerManager = campaignSettings.getPlayerSelectedSide() == FactionSideType.BLUEFOR ? blueforCoalitionManager : redforCoalitionManager;
        CoalitionManager enemyManager = playerManager.equals(blueforCoalitionManager) ? redforCoalitionManager : blueforCoalitionManager;

        boolean playerFirst = DynamicCampaignSim.getRandomGen().nextBoolean();
        if(playerFirst) {
            updateAndGenerateForCoalition(campaignSettings, playerManager, enemyManager, playerSide);
            updateAndGenerateForCoalition(campaignSettings, enemyManager, playerManager, playerSide);
        } else {
            updateAndGenerateForCoalition(campaignSettings, enemyManager, playerManager, playerSide);
            updateAndGenerateForCoalition(campaignSettings, playerManager, enemyManager, playerSide);
        }

        // Currently running missions for each side
        List<Mission> playerMissions = playerManager.getCoalitionMissionManager().getPlannedMissions();
        List<Mission> enemyMissions = enemyManager.getCoalitionMissionManager().getPlannedMissions();
        log.debug(playerMissions);
        log.debug(enemyMissions);
    }

    private void updateAndGenerateForCoalition(CampaignSettings campaignSettings, CoalitionManager friendlyManager, CoalitionManager enemyManager, FactionSideType playerSide) {
        // Check if we even need to generate new missions

        // Check to see what tasks percentages we need to generate

        // Roll dice and generate a task if we need to, and if the threat level is
        // deemed acceptable
        generateTestMissionForCoalition(campaignSettings, friendlyManager, campaignSettings.getCurrentCampaignDate());
    }
}
