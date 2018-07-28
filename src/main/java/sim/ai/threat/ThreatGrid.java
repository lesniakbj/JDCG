package sim.ai.threat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.AIActionType;
import sim.domain.enums.AircraftType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.SimUnit;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.manager.CoalitionManager;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ThreatGrid {
    private static final Logger log = LogManager.getLogger(ThreatGrid.class);

    private static final int[][] NEIGHBOURS = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

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
                cell.setParentGrid(this);
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

    public void ignoreEmptyCells() {
        for(int x = 0; x < numCellsX; x++) {
            for (int y = 0; y < numCellsY; y++) {
                ThreatGridCell searchCell = threatGrid[x][y];
                if(cellHasNoNeighbors(searchCell) && searchCell.getThreatLevel() == 0.0) {
                    searchCell.setIgnoreDuringThreatCalculations(true);
                }
            }
        }
    }


    public boolean cellHasThreat(ThreatGridCell cell) {
        return cellHasThreatNeighbors(cell) && cell.getThreatLevel() > 0.0;
    }

    public List<ThreatGridCell> getCellsGreaterThan(double value) {
        List<ThreatGridCell> cells = new ArrayList<>();
        for (ThreatGridCell[] aThreatGrid : threatGrid) {
            for (ThreatGridCell anAThreatGrid : aThreatGrid) {
                if (anAThreatGrid.getThreatLevel() > value) {
                    cells.add(anAThreatGrid);
                }
            }
        }
        return cells;
    }

    public List<ThreatGridCell> getCellsLessThan(double value) {
        List<ThreatGridCell> cells = new ArrayList<>();
        for (ThreatGridCell[] aThreatGrid : threatGrid) {
            for (ThreatGridCell anAThreatGrid : aThreatGrid) {
                if (anAThreatGrid.getThreatLevel() < value) {
                    cells.add(anAThreatGrid);
                }
            }
        }
        return cells;
    }

    public ThreatGridCell getCellFromLocation(Point2D.Double missionLocation) {
        return getCellFromLocation(missionLocation.getX(), missionLocation.getY());
    }

    public ThreatGridCell getCellFromLocation(double x, double y) {
        for (ThreatGridCell[] aThreatGrid : threatGrid) {
            for (ThreatGridCell anAThreatGrid : aThreatGrid) {
                if(anAThreatGrid.contains(x, y)) {
                    return anAThreatGrid;
                }
            }
        }
        return null;
    }

    public List<List<AIAction>> generateAllPossibleMoves(CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {
        // Get the list of all available aircraft
        List<UnitGroup<AirUnit>> airUnits = friendlyCoalitionManager.getCoalitionAirGroups();
        if(airUnits.isEmpty()) {
            return new ArrayList<>();
        }

        // Get the threat grids for defensive cells
        List<ThreatGridCell> defensiveCells = getCellsGreaterThan(0.0);

        // Get the other threats that are present
        List<ThreatGridCell> offensiveCells = getCellsLessThan(0.0);

        // Generate a list of ALL moves for ALL available units
        List<List<AIAction>> actionLists = new ArrayList<>();
        for (UnitGroup<AirUnit> airUnit : airUnits) {
            List<AIAction> allUnitActions = findActionsForUnit(airUnit, friendlyCoalitionManager, enemyCoalitionManager, defensiveCells, offensiveCells);
            actionLists.add(allUnitActions);
        }

        return actionLists;
    }

    private boolean cellHasNoNeighbors(ThreatGridCell searchCell) {
        double totalThreatToCell = 0.0;
        int x = searchCell.getX();
        int y = searchCell.getY();
        for(int[] offset : NEIGHBOURS) {
            int offX = x + offset[0];
            int offY = y + offset[1];
            if (offX > 0 && offY > 0 && offX < numCellsX && offY < numCellsY) {
                ThreatGridCell cell = threatGrid[offX][offY];
                if(cell.getThreatLevel() != 0.0) {
                    totalThreatToCell += cell.getThreatLevel();
                }
            }
        }
        return totalThreatToCell == 0.0;
    }

    private boolean cellHasThreatNeighbors(ThreatGridCell cell) {
        double totalThreatToCell = 0.0;
        int x = cell.getX();
        int y = cell.getY();
        for(int[] offset : NEIGHBOURS) {
            int offX = x + offset[0];
            int offY = y + offset[1];
            if (offX > 0 && offY > 0 && offX < numCellsX && offY < numCellsY) {
                ThreatGridCell offsetCell = threatGrid[offX][offY];
                if(offsetCell.getThreatLevel() != 0.0) {
                    totalThreatToCell += offsetCell.getThreatLevel();
                }
            }
        }
        return totalThreatToCell < 0.0;
    }

    private List<AIAction> findActionsForUnit(UnitGroup<AirUnit> airUnitGroup, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager, List<ThreatGridCell> defensiveCells, List<ThreatGridCell> offensiveCells) {
        // Check the type of each Air Group so we can figure out what it is capable of...
        if(airUnitGroup.getNumberOfUnits() == 0) {
            AIAction action = new AIAction();
            action.setActionGroup(null);
            action.setCell(null);
            action.setType(AIActionType.NOTHING);
            action.setMainObjective(null);
            return new ArrayList<>(Collections.singletonList(action));
        }

        // Variables...
        List<AIActionType> actionsToGenerate = new ArrayList<>();
        AircraftType aircraftType = airUnitGroup.getGroupUnits().get(0).getAircraftType();
        List<SubTaskType> aircraftSubTasks = aircraftType.getPossibleTasks();

        // Add some default actions to generate
        if(aircraftSubTasks.contains(SubTaskType.CAS) || aircraftSubTasks.contains(SubTaskType.GROUND_STRIKE)) {
            actionsToGenerate.add(AIActionType.ATTACK_AIRBASE_STRUCTURE);
            actionsToGenerate.add(AIActionType.ATTACK_GROUND_UNIT);
            actionsToGenerate.add(AIActionType.DEFEND_AIR_DEFENCE);
            actionsToGenerate.add(AIActionType.DEFEND_AIRBASE_STRUCTURE);
            actionsToGenerate.add(AIActionType.DEFEND_GROUND_UNIT);
        }

        if(aircraftSubTasks.contains(SubTaskType.CAP) || aircraftSubTasks.contains(SubTaskType.ESCORT) || aircraftSubTasks.contains(SubTaskType.INTERCEPT)) {
            actionsToGenerate.add(AIActionType.INTERCEPT_FLIGHT);
            actionsToGenerate.add(AIActionType.DEFEND_AIR_DEFENCE);
            actionsToGenerate.add(AIActionType.DEFEND_AIRBASE_STRUCTURE);
            actionsToGenerate.add(AIActionType.DEFEND_GROUND_UNIT);
        }

        if(aircraftSubTasks.contains(SubTaskType.TRANSPORT) || aircraftSubTasks.contains(SubTaskType.AIRLIFT)) {
            actionsToGenerate.add(AIActionType.TRANSPORT_AIR_DEFENCE);
            actionsToGenerate.add(AIActionType.TRANSPORT_GROUND_UNIT);
        }

        if(aircraftSubTasks.contains(SubTaskType.SEAD) || aircraftSubTasks.contains(SubTaskType.DEAD)) {
            actionsToGenerate.add(AIActionType.ATTACK_AIR_DEFENCE);
        }

        if(aircraftSubTasks.contains(SubTaskType.RECON) || aircraftSubTasks.contains(SubTaskType.BDA)) {
            actionsToGenerate.add(AIActionType.RECON);
        }

        if(aircraftSubTasks.contains(SubTaskType.BOMBER)) {
            actionsToGenerate.add(AIActionType.STRATEGIC_BOMBING);
        }

        // Add other actions that
        actionsToGenerate.add(AIActionType.NOTHING);
        return generateAllPossibleMovesOfType(airUnitGroup, friendlyCoalitionManager, enemyCoalitionManager, actionsToGenerate, offensiveCells, defensiveCells);
    }

    private List<AIAction> generateAllPossibleMovesOfType(UnitGroup<AirUnit> airUnitGroup, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager, List<AIActionType> actionsToGenerate, List<ThreatGridCell> offensiveCells, List<ThreatGridCell> defensiveCells) {
        // Friendly Information
        List<Airfield> friendlyAirfields = friendlyCoalitionManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> friendlyAirDefences = friendlyCoalitionManager.getCoalitionAirDefences();
        List<UnitGroup<GroundUnit>> friendlyGroundUnits = friendlyCoalitionManager.getCoalitionFrontlineGroups();

        // Enemy Information
        List<Airfield> enemyAirfields = enemyCoalitionManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> enemyAirDefences = enemyCoalitionManager.getCoalitionAirDefences();
        List<UnitGroup<GroundUnit>> enemyGroundUnits = enemyCoalitionManager.getCoalitionFrontlineGroups();
        List<UnitGroup<AirUnit>> enemyActiveAirGroups = enemyCoalitionManager.getMissionManager().getPlannedMissions().stream().filter(Mission::isActive).map(Mission::getMissionAircraft).collect(Collectors.toList());

        List<AIAction> actions = new ArrayList<>();
        for(AIActionType actionType : actionsToGenerate) {
            // Add possible defensive actions
            switch (actionType) {
                case DEFEND_AIRBASE_STRUCTURE:
                    addAllAirfieldLocations(actions, actionType, defensiveCells, airUnitGroup, friendlyAirfields);
                    break;
                case DEFEND_AIR_DEFENCE:
                case TRANSPORT_AIR_DEFENCE:
                    addAllLocations(actions, actionType, defensiveCells, airUnitGroup, friendlyAirDefences);
                    break;
                case DEFEND_GROUND_UNIT:
                case TRANSPORT_GROUND_UNIT:
                    addLocalThreatLocations(actions, actionType, defensiveCells, airUnitGroup, friendlyGroundUnits);
                    break;
                case INTERCEPT_FLIGHT:
                    addAllLocations(actions, actionType, defensiveCells, airUnitGroup, enemyActiveAirGroups);
                    break;
            }

            // Add possible attacking actions
            switch (actionType) {
                case ATTACK_AIRBASE_STRUCTURE:
                case STRATEGIC_BOMBING:
                    addAllAirfieldLocations(actions, actionType, offensiveCells, airUnitGroup, enemyAirfields);
                    break;
                case ATTACK_AIR_DEFENCE:
                    addAllLocations(actions, actionType, offensiveCells, airUnitGroup, enemyAirDefences);
                    break;
                case ATTACK_GROUND_UNIT:
                    addLocalThreatLocations(actions, actionType, offensiveCells, airUnitGroup, enemyGroundUnits);
                    break;
                case INTERCEPT_FLIGHT:
                    addAllLocations(actions, actionType, offensiveCells, airUnitGroup, enemyActiveAirGroups);
                    break;
            }

            // Add default actions
            if(actionType == AIActionType.NOTHING) {
                actions.add(generateNewAIAction(actionType, null, airUnitGroup));
            }
        }
        return actions;
    }

    private <T extends SimUnit> void addLocalThreatLocations(List<AIAction> actions, AIActionType actionType, List<ThreatGridCell> cells, UnitGroup<AirUnit> airUnitGroup, List<UnitGroup<T>> location) {
        for(ThreatGridCell cell : cells) {
            if(cellHasThreat(cell)) {
                addAllLocationsInner(actions, actionType, cell, airUnitGroup, location);
            }
        }
    }

    private void addAllAirfieldLocations(List<AIAction> actions, AIActionType actionType, List<ThreatGridCell> cells, UnitGroup<AirUnit> airUnitGroup, List<Airfield> airfields) {
        for(ThreatGridCell cell : cells) {
            for (Airfield a : airfields) {
                if (cell.contains(a.getAirfieldType().getAirfieldMapPosition())) {
                    actions.add(generateNewAIAction(actionType, cell, airUnitGroup));
                }
            }
        }
    }

    private <T extends SimUnit> void addAllLocations(List<AIAction> actions, AIActionType actionType, List<ThreatGridCell> cells, UnitGroup<AirUnit> airUnitGroup, List<UnitGroup<T>> location) {
        for(ThreatGridCell cell : cells) {
            addAllLocationsInner(actions, actionType, cell, airUnitGroup, location);
        }
    }

    private <T extends SimUnit> void addAllLocationsInner(List<AIAction> actions, AIActionType actionType, ThreatGridCell cell, UnitGroup<AirUnit> airUnitGroup, List<UnitGroup<T>> location) {
        for (UnitGroup<T> loc : location) {
            if (cell.contains(loc.getMapXLocation(), loc.getMapYLocation())) {
                actions.add(generateNewAIAction(actionType, cell, airUnitGroup));
            }
        }
    }

    private AIAction generateNewAIAction(AIActionType actionType, ThreatGridCell cell, UnitGroup<AirUnit> airUnitGroup) {
        AIAction action = new AIAction();
        action.setType(actionType);
        action.setCell(cell);
        action.setMainObjective(null);
        action.setActionGroup(airUnitGroup);
        return action;
    }


    private List<List<AIAction>> cartesianProduct(int idx, List<List<AIAction>> lists) {
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
