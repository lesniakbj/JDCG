package sim.ai.actions;

import sim.ai.threat.ThreatGridCell;
import sim.domain.unit.SimUnit;

public class AIAction {
    private AIActionType type;
    private ThreatGridCell cell;
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
}
