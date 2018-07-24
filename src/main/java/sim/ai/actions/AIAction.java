package sim.ai.actions;

import sim.ai.threat.ThreatGridCell;
import sim.domain.unit.SimUnit;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;

public class AIAction {
    private AIActionType type;
    private ThreatGridCell cell;
    private UnitGroup<AirUnit> actionGroup;
    private SimUnit mainObjective;

    public AIActionType getType() {
        return type;
    }

    public void setType(AIActionType type) {
        this.type = type;
    }

    public ThreatGridCell getCell() {
        return cell;
    }

    public void setCell(ThreatGridCell cell) {
        this.cell = cell;
    }

    public SimUnit getMainObjective() {
        return mainObjective;
    }

    public void setMainObjective(SimUnit mainObjective) {
        this.mainObjective = mainObjective;
    }

    public UnitGroup<AirUnit> getActionGroup() {
        return actionGroup;
    }

    public void setActionGroup(UnitGroup<AirUnit> actionGroup) {
        this.actionGroup = actionGroup;
    }

    @Override
    public String toString() {
        return "{\"AIAction\":{"
                + "\"type\":\"" + type + "\""
                + ", \"cell\":" + cell
                + ", \"actionGroup\":" + actionGroup
                + ", \"mainObjective\":" + mainObjective
                + "}}";
    }
}
