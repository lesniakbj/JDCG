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

    private static final int TIME_BETWEEN_PLANNING_MINUTES = 120;
    private static final int[][] NEIGHBOURS = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};


    public ATOGenerator() {
        aiCommander = new RandomAICommander(TIME_BETWEEN_PLANNING_MINUTES);
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

        // When considering actions, ignore all cells that are 0.0 and are not touching a cell that has a score
        setThreatCellsToIgnore(currentThreatGrid);

        // Have our AI commander determine the commands we are to take
        return aiCommander.generateCommanderActions(currentThreatGrid, currentCampaignDate, friendlyCoalitionManager, enemyCoalitionManager);
    }

    private void setThreatCellsToIgnore(ThreatGrid currentThreatGrid) {
        for(int x = 0; x < currentThreatGrid.getNumCellsX(); x++) {
            for (int y = 0; y < currentThreatGrid.getNumCellsY(); y++) {
                ThreatGridCell searchCell = currentThreatGrid.getThreatGrid()[x][y];
                if(cellHasNoNeighbors(searchCell, currentThreatGrid) && searchCell.getThreatLevel() == 0.0) {
                    searchCell.setIgnoreDuringThreatCalculations(true);
                }
            }
        }
    }

    private boolean cellHasNoNeighbors(ThreatGridCell searchCell, ThreatGrid currentThreatGrid) {
        double totalThreatToCell = 0.0;
        int x = searchCell.getX();
        int y = searchCell.getY();
        for(int[] offset : NEIGHBOURS) {
            int offX = x + offset[0];
            int offY = y + offset[1];
            if (offX > 0 && offY > 0 && offX < currentThreatGrid.getNumCellsX() && offY < currentThreatGrid.getNumCellsY()) {
                ThreatGridCell cell = currentThreatGrid.getThreatGrid()[offX][offY];
                if(cell.getThreatLevel() != 0.0) {
                    totalThreatToCell += cell.getThreatLevel();
                }
            }
        }
        return totalThreatToCell == 0.0;
    }
}
