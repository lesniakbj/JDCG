package sim.gen.air;

import static sim.domain.enums.StaticLists.DEFAULT_LOADOUTS;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.CommanderAction;
import sim.ai.actions.GenerationResult;
import sim.ai.command.AICommander;
import sim.ai.command.RandomAICommander;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.ThreatGridCell;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Aircraft;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

public class ATOGenerator {
    private static final Logger log = LogManager.getLogger(ATOGenerator.class);

    private AICommander aiCommander;

    private static final int TIME_BETWEEN_PLANNING_MINUTES = 5;

    public ATOGenerator() {
        aiCommander = new RandomAICommander();
    }

    public void generateTestMissionForCoalition(CampaignSettings campaign, CoalitionManager coalitionManager, Date date) {
        List<Waypoint> generatedWaypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition(), AirfieldType.KHASAB.getAirfieldMapPosition(), SubTaskType.INTERCEPT, MapType.PERSIAN_GULF);

        UnitGroup.Builder<AirUnit> aircraftBuilder = new UnitGroup.Builder<>();
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

    public List<AIAction> generateATO(CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager, List<AIAction> lastActionsTaken, Date currentCampaignDate) {
        // Next action...
        ThreatGrid currentThreatGrid = friendlyCoalitionManager.getMissionManager().getThreatGrid();

        // Have our AI commander determine the commands we are to take
        return aiCommander.generateCommanderActions(currentThreatGrid, currentCampaignDate, friendlyCoalitionManager, enemyCoalitionManager);
    }
}
