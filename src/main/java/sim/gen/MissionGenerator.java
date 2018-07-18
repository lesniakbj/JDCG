package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Aircraft;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.Waypoint;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSide;
import sim.domain.enums.MapType;
import sim.domain.enums.TaskType;
import sim.main.CoalitionManager;
import sim.main.DynamicCampaignSim;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static sim.domain.enums.StaticLists.DEFAULT_LOADOUTS;

public class MissionGenerator {
    private static final Logger log = LogManager.getLogger(MissionGenerator.class);

    public void generateTestMissionForCoalition(DynamicCampaignSim campaign, CoalitionManager coalitionManager, Date date) {
        List<Waypoint> generatedWaypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition(), AirfieldType.KHASAB.getAirfieldMapPosition(), TaskType.INTERCEPT, MapType.PERSIAN_GULF);

        UnitGroup.Builder<Aircraft> aircraftBuilder = new UnitGroup.Builder<>();
        aircraftBuilder.setUnits(Arrays.asList(new Aircraft(AircraftType.FA_18C_LOT20), new Aircraft(AircraftType.FA_18C_LOT20)))
            .setSide(FactionSide.BLUEFOR)
            .setMapLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition());

        Mission.Builder builder = new Mission.Builder();
        builder.setMissionMap(campaign.getCampaignSettings().getSelectedMap().getMapType())
            .setMissionType(TaskType.INTERCEPT)
            .setMissionAircraft(aircraftBuilder.build())
            .setMissionWaypoints(generatedWaypoints)
            .setUpcomingMissionDate(date, 60)
            .setIsClientMission(false)
            .setPlayerAircraftNumber(0)
            .setMissionComplete(false)
            .setUpdateRate(campaign.getSimSettings().getMinutesPerSimulationStep())
            .setShouldGenerateMission(false)
            .setStartingAirfield(AirfieldType.AL_DHAFRA_AIRBASE)
            .setMissionMunitions(DEFAULT_LOADOUTS.get(AircraftType.FA_18C_LOT20).get(TaskType.INTERCEPT));

        coalitionManager.getCoalitionMissionManager().addMission(builder.build());
    }

    public void generateTestRedforMissionForCoalition(DynamicCampaignSim campaign, CoalitionManager coalitionManager, Date date) {
        List<Waypoint> generatedWaypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.LAR_AIRBASE.getAirfieldMapPosition(), AirfieldType.ABU_MUSA_ISLAND_AIRPORT.getAirfieldMapPosition(), TaskType.INTERCEPT, MapType.PERSIAN_GULF);

        UnitGroup.Builder<Aircraft> aircraftBuilder = new UnitGroup.Builder<>();
        aircraftBuilder.setUnits(Arrays.asList(new Aircraft(AircraftType.SU_27), new Aircraft(AircraftType.SU_27)))
            .setSide(FactionSide.REDFOR)
            .setMapLocation(AirfieldType.LAR_AIRBASE.getAirfieldMapPosition());

        Mission.Builder builder = new Mission.Builder();
        builder.setMissionMap(campaign.getCampaignSettings().getSelectedMap().getMapType())
            .setMissionType(TaskType.CAS)
            .setMissionAircraft(aircraftBuilder.build())
            .setMissionWaypoints(generatedWaypoints)
            .setUpcomingMissionDate(date, 15)
            .setIsClientMission(false)
            .setPlayerAircraftNumber(0)
            .setMissionComplete(false)
            .setUpdateRate(campaign.getSimSettings().getMinutesPerSimulationStep())
            .setShouldGenerateMission(false)
            .setStartingAirfield(AirfieldType.LAR_AIRBASE)
            .setMissionMunitions(DEFAULT_LOADOUTS.get(AircraftType.FA_18C_LOT20).get(TaskType.INTERCEPT));

        coalitionManager.getCoalitionMissionManager().addMission(builder.build());
    }
}
