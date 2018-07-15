package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Aircraft;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.Waypoint;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.MapType;
import sim.domain.enums.TaskType;
import sim.exception.InvalidMissionException;
import sim.main.CoalitionManager;
import sim.main.DynamicCampaignSim;

import java.util.Arrays;
import java.util.List;

import static sim.domain.enums.StaticLists.DEFAULT_LOADOUTS;

public class MissionGenerator {
    private static final Logger log = LogManager.getLogger(MissionGenerator.class);

    public void generateTestMissionForCoalition(DynamicCampaignSim campaign, CoalitionManager coalitionManager) {
        List<Waypoint> generatedWaypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getX(), AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getY(),
                AirfieldType.KHASAB.getAirfieldMapPosition().getX(),  AirfieldType.KHASAB.getAirfieldMapPosition().getY(), TaskType.INTERCEPT, MapType.PERSIAN_GULF);

        Mission.Builder builder = new Mission.Builder();
        builder.setMissionMap(campaign.getCampaignSettings().getSelectedMap().getMapType());
        builder.setMissionType(TaskType.INTERCEPT);
        builder.setMissionAircraft(new UnitGroup<>(Arrays.asList(new Aircraft(AircraftType.FA_18C_LOT20), new Aircraft(AircraftType.FA_18C_LOT20))));
        builder.setMissionWaypoints(generatedWaypoints);
        builder.setUpcomingMissionDate(campaign.getCampaignSettings().getCurrentCampaignDate(), 20);
        builder.setIsClientMission(false);
        builder.setPlayerAircraftNumber(0);
        builder.setMissionComplete(false);
        builder.setUpdateRate(campaign.getSimSettings().getMinutesPerSimulationStep());
        builder.setShouldGenerateMission(false);
        builder.setStartingAirfield(AirfieldType.AL_DHAFRA_AIRBASE);
        builder.setMissionMunitions(DEFAULT_LOADOUTS.get(AircraftType.FA_18C_LOT20).get(TaskType.INTERCEPT));

        try {
            Mission m = builder.build();
            coalitionManager.getCoalitionMissionManager().addMission(m);
        } catch (InvalidMissionException e) {
            log.debug("Invalid mission generated!");
            log.debug(e);
        }
    }
}
