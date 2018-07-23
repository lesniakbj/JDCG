package sim.ai.threat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.AIActionType;
import sim.ai.actions.CommanderAction;
import sim.ai.actions.GenerationResult;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.Structure;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.manager.CoalitionManager;


public class ThreatGrid {
    private static final Logger log = LogManager.getLogger(ThreatGrid.class);

    private ThreatGridCell[][] threatGrid;
    private int x;
    private int y;
    private int w;
    private int h;
    private int cellWidth;
    private int numCellsX;
    private int numCellsY;

    public ThreatGrid(int x, int y, int w, int h, int cellWidth) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cellWidth = cellWidth;
        this.numCellsX = w / cellWidth;
        this.numCellsY = h / cellWidth;

        this.threatGrid = new ThreatGridCell[numCellsX][numCellsY];
        for(int xvar = 0; xvar < threatGrid.length; xvar++) {
            for(int yvar = 0; yvar < threatGrid[xvar].length; yvar++) {
                ThreatGridCell cell = new ThreatGridCell();
                cell.setThreatLevel(0.0);
                cell.setX(xvar);
                cell.setY(yvar);
                cell.setMapX(x + (xvar * cellWidth));
                cell.setMapY(y + (yvar * cellWidth));
                cell.setIgnoreDuringThreatCalculations(false);
                threatGrid[xvar][yvar] = cell;
            }
        }
    }

    public ThreatGridCell[][] getThreatGrid() {
        return threatGrid;
    }

    public void setThreatGrid(ThreatGridCell[][] threatGrid) {
        this.threatGrid = threatGrid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getNumCellsX() {
        return numCellsX;
    }

    public void setNumCellsX(int numCellsX) {
        this.numCellsX = numCellsX;
    }

    public int getNumCellsY() {
        return numCellsY;
    }

    public void setNumCellsY(int numCellsY) {
        this.numCellsY = numCellsY;
    }

    public static List<GenerationResult> generateAllPossibleMoves(ThreatGrid currentThreatGrid, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {
        // Get the list of all available aircraft
        List<UnitGroup<AirUnit>> airUnits = friendlyCoalitionManager.getCoalitionAirGroups();
        if(airUnits.isEmpty()) {
            return new ArrayList<>();
        }

        // Get the list of all targets available
        Map<Airfield, List<UnitGroup<GroundUnit>>> airfields = enemyCoalitionManager.getCoalitionPointDefenceGroundUnits();
        List<Structure> airfieldStructures = airfields.keySet().stream().flatMap(a -> a.getCriticalStructures().stream()).collect(Collectors.toList());
        List<UnitGroup<GroundUnit>> airfieldGroundGroups = airfields.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        List<UnitGroup<GroundUnit>> groundGroups = enemyCoalitionManager.getCoalitionFrontlineGroups();
        List<UnitGroup<AirDefenceUnit>> defenceGroups = enemyCoalitionManager.getCoalitionAirDefences();
        List<UnitGroup<AirUnit>> airGroupsInProgress = enemyCoalitionManager.getCoalitionMissionManager().getPlannedMissions().stream().map(Mission::getMissionAircraft).collect(Collectors.toList());

        // Generate a list of all possible missions for each aircraft
        log.debug("Air Units Available: " + airUnits.size());
        log.debug("Enemy Airfields: " + airfields.size());
        log.debug("Enemy Airfields Structures: " + airfieldStructures.size());
        log.debug("Enemy Airfields Ground Groups: " + airfieldGroundGroups.size());
        log.debug("Enemy Ground Groups: " + groundGroups.size());
        log.debug("Enemy Air Defence Groups: " + defenceGroups.size());
        log.debug("Enemy Air Groups: " + airGroupsInProgress.size());

        // Generate a list of ALL moves for ALL available units
         List<List<AIAction>> actionLists = new ArrayList<>();
         for(int i = 0; i < airUnits.size(); i++) {
             List<AIAction> allUnitActions = findActionsForUnit(airUnits.get(i), currentThreatGrid, airfieldStructures, airfieldGroundGroups, groundGroups, defenceGroups, airGroupsInProgress);
             actionLists.add(allUnitActions);
         }

        // We then want to generate a Cartesian Product of all generations, so that we have all combinations together.
        actionLists = cartesianProduct(0, actionLists);
        // actionLists.addAll(cartesianProduct(0, actionLists));
        log.debug(actionLists);

        // Finally, compute the score of the board given each moveset in the Cartesian Product, this will
        // be done after evaluating the action list and determining the effect on the board score

        // GenerationResult res = new GenerationResult(new CommanderAction(), currentThreatGrid);
        return new ArrayList<>();
    }

    private static List<AIAction> findActionsForUnit(UnitGroup<AirUnit> airUnitUnitGroup, ThreatGrid currentThreatGrid, List<Structure> airfieldStructures, List<UnitGroup<GroundUnit>> airfieldGroundGroups, List<UnitGroup<GroundUnit>> groundGroups, List<UnitGroup<AirDefenceUnit>> defenceGroups, List<UnitGroup<AirUnit>> airGroupsInProgress) {
        ArrayList<AIAction> actions = new ArrayList<>();
        AIAction action = new AIAction();
        action.setActionGroup(null);
        action.setCell(null);
        action.setType(AIActionType.NOTHING);
        action.setMainObjective(null);
        actions.add(action);
        return actions;
    }

    private static List<List<AIAction>> cartesianProduct(int idx, List<List<AIAction>> lists) {
        List<List<AIAction>> actions = new ArrayList<>();
        if (idx == lists.size()) {
            actions.add(new ArrayList<>());
            return actions;
        }

        for(AIAction action : lists.get(idx)) {
            for(List<AIAction> actionList : cartesianProduct(idx + 1, lists)) {
                actionList.add(action);
                actions.add(actionList);
            }
        }
        return actions;
    }

    @Override
    public String toString() {
        return "{\"ThreatGrid\":{"
                + "\"threatGrid\":" + Arrays.deepToString(threatGrid)
                + ", \"x\":\"" + x + "\""
                + ", \"y\":\"" + y + "\""
                + ", \"w\":\"" + w + "\""
                + ", \"h\":\"" + h + "\""
                + "}}";
    }
}
