package sim.ai.actions;

import java.util.List;
import java.util.Map;
import sim.domain.unit.UnitGroup;

public class CommanderAction {
    List<AIAction> actionMap;

    public List<AIAction> getActionMap() {
        return actionMap;
    }

    public void setActionMap(List<AIAction> actionList) {
        this.actionMap = actionList;
    }
}
