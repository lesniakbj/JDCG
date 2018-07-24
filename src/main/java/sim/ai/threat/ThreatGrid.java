package sim.ai.threat;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.AIActionType;
import sim.domain.enums.AircraftType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.manager.CoalitionManager;


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

    private void setThreatCellsToIgnore() {
        for(int x = 0; x < numCellsX; x++) {
            for (int y = 0; y < numCellsY; y++) {
                ThreatGridCell searchCell = threatGrid[x][y];
                if(cellHasNoNeighbors(searchCell) && searchCell.getThreatLevel() == 0.0) {
                    searchCell.setIgnoreDuringThreatCalculations(true);
                }
            }
        }
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

        List<AIAction> actions;
        List<AIActionType> actionsToGenerate = new ArrayList<>(Collections.singletonList(AIActionType.NOTHING));
        AircraftType aircraftType = airUnitGroup.getGroupUnits().get(0).getAircraftType();
        List<SubTaskType> aircraftSubTasks = aircraftType.getPossibleTasks();
        if(aircraftType.isHelicopter()) {
            if(aircraftSubTasks.contains(SubTaskType.TRANSPORT) || aircraftSubTasks.contains(SubTaskType.AIRLIFT)) {
                actionsToGenerate.add(AIActionType.TRANSPORT_AIR_DEFENCE);
                actionsToGenerate.add(AIActionType.TRANSPORT_GROUND_UNIT);
            }

            if(aircraftSubTasks.contains(SubTaskType.CAS) || aircraftSubTasks.contains(SubTaskType.GROUND_STRIKE)) {
                actionsToGenerate.add(AIActionType.ATTACK_AIR_DEFENCE);
                actionsToGenerate.add(AIActionType.ATTACK_AIRBASE_STRUCTURE);
                actionsToGenerate.add(AIActionType.ATTACK_GROUND_UNIT);
                actionsToGenerate.add(AIActionType.DEFEND_AIR_DEFENCE);
                actionsToGenerate.add(AIActionType.DEFEND_AIRBASE_STRUCTURE);
                actionsToGenerate.add(AIActionType.DEFEND_GROUND_UNIT);
            }
            
            actions = generateAllPossibleMovesOfType(airUnitGroup, friendlyCoalitionManager, enemyCoalitionManager, actionsToGenerate, offensiveCells, defensiveCells);
        } else {
            if(aircraftSubTasks.contains(SubTaskType.TRANSPORT) || aircraftSubTasks.contains(SubTaskType.AIRLIFT)) {
                actionsToGenerate.add(AIActionType.TRANSPORT_GROUND_UNIT);
                actionsToGenerate.add(AIActionType.TRANSPORT_AIR_DEFENCE);
            }

            if(aircraftSubTasks.contains(SubTaskType.CAP) || aircraftSubTasks.contains(SubTaskType.ESCORT) || aircraftSubTasks.contains(SubTaskType.INTERCEPT)) {
                actionsToGenerate.add(AIActionType.ATTACK_AIR_DEFENCE);
                actionsToGenerate.add(AIActionType.ATTACK_AIRBASE_STRUCTURE);
                actionsToGenerate.add(AIActionType.ATTACK_GROUND_UNIT);
                actionsToGenerate.add(AIActionType.DEFEND_AIR_DEFENCE);
                actionsToGenerate.add(AIActionType.DEFEND_AIRBASE_STRUCTURE);
                actionsToGenerate.add(AIActionType.DEFEND_GROUND_UNIT);
            }

            if(aircraftSubTasks.contains(SubTaskType.CAS) || aircraftSubTasks.contains(SubTaskType.GROUND_STRIKE)) {
                actionsToGenerate.add(AIActionType.INTERCEPT_FLIGHT);
                actionsToGenerate.add(AIActionType.DEFEND_AIR_DEFENCE);
                actionsToGenerate.add(AIActionType.DEFEND_AIRBASE_STRUCTURE);
                actionsToGenerate.add(AIActionType.DEFEND_GROUND_UNIT);
            }

            if(aircraftSubTasks.contains(SubTaskType.BOMBER)) {
                actionsToGenerate.add(AIActionType.STRATEGIC_BOMBING);
            }

            actions = generateAllPossibleMovesOfType(airUnitGroup, friendlyCoalitionManager, enemyCoalitionManager, actionsToGenerate, offensiveCells, defensiveCells);
        }

        return actions;
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

        //TODO: Fix this .getCoalitionAirGroups() ie, make it so we can track in progress flights
        List<UnitGroup<AirUnit>> enemyActiveAirGroups = enemyCoalitionManager.getCoalitionAirGroups();

        List<AIAction> actions = new ArrayList<>();
        for(AIActionType actionType : actionsToGenerate) {
            // Add possible defensive actions
            for(ThreatGridCell cell : defensiveCells) {
                // Defend Air Bases
                if(actionType == AIActionType.DEFEND_AIRBASE_STRUCTURE) {
                    addAllAirfieldLocations(actions, actionType, cell, airUnitGroup, friendlyAirfields);
                }

                // Defend Air Defense
                if(actionType == AIActionType.DEFEND_AIR_DEFENCE) {
                    addAllUnitGroups(actions, actionType, cell, airUnitGroup, friendlyAirDefences);
                }

                // Ground Units
                if(actionType == AIActionType.DEFEND_GROUND_UNIT) {
                    addAllGroundUnitGroups(actions, actionType, cell, airUnitGroup, friendlyGroundUnits);
                }

                // Intercept
                if(actionType == AIActionType.INTERCEPT_FLIGHT) {
                    addAllInterceptGroups(actions, actionType, cell, airUnitGroup, enemyActiveAirGroups);
                }
            }

            // Add possible attacking actions
            for(ThreatGridCell cell : offensiveCells) {
                // Airbases
                if(actionType == AIActionType.ATTACK_AIRBASE_STRUCTURE) {
                    addAllAirfieldLocations(actions, actionType, cell, airUnitGroup, enemyAirfields);
                }

                // Air Defenses
                if(actionType == AIActionType.ATTACK_AIR_DEFENCE) {
                    addAllUnitGroups(actions, actionType, cell, airUnitGroup, enemyAirDefences);
                }

                // Ground Units
                if(actionType == AIActionType.ATTACK_GROUND_UNIT) {
                    addAllGroundUnitGroups(actions, actionType, cell, airUnitGroup, enemyGroundUnits);
                }
            }

        }
        return actions;
    }

    private void addAllInterceptGroups(List<AIAction> actions, AIActionType actionType, ThreatGridCell cell, UnitGroup<AirUnit> airUnitGroup, List<UnitGroup<AirUnit>> activeAirGroups) {
        for (UnitGroup<AirUnit> g : activeAirGroups) {
            if(cell.contains(g.getMapXLocation(), g.getMapYLocation())) {
                actions.add(generateNewAIAction(actionType, cell, airUnitGroup));
            }
        }
    }

    private void addAllGroundUnitGroups(List<AIAction> actions, AIActionType actionType, ThreatGridCell cell, UnitGroup<AirUnit> airUnitGroup, List<UnitGroup<GroundUnit>> groundUnits) {
        for (UnitGroup<GroundUnit> g : groundUnits) {
            if(cell.contains(g.getMapXLocation(), g.getMapYLocation())) {
                actions.add(generateNewAIAction(actionType, cell, airUnitGroup));
            }
        }
    }

    private void addAllUnitGroups(List<AIAction> actions, AIActionType actionType, ThreatGridCell cell, UnitGroup<AirUnit> airUnitGroup, List<UnitGroup<AirDefenceUnit>> airDefences) {
        for(UnitGroup<AirDefenceUnit> g : airDefences) {
            if(cell.contains(g.getMapXLocation(), g.getMapYLocation())) {
                actions.add(generateNewAIAction(actionType, cell, airUnitGroup));
            }
        }
    }

    private void addAllAirfieldLocations(List<AIAction> actions, AIActionType actionType, ThreatGridCell cell, UnitGroup<AirUnit> airUnitGroup, List<Airfield> airfields) {
        for(Airfield a : airfields) {
            if(cell.contains(a.getAirfieldType().getAirfieldMapPosition())) {
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

    private List<ThreatGridCell> getCellsGreaterThan(double value) {
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

    private List<ThreatGridCell> getCellsLessThan(double value) {
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
