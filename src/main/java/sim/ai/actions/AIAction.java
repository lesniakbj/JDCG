package sim.ai.actions;

import sim.ai.threat.ThreatGridCell;

public class AIAction {
    private AIActionType type;
    private ThreatGridCell cell;

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
}
