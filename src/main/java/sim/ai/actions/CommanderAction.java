package sim.ai.actions;

import sim.domain.unit.UnitGroup;

import java.util.List;
import java.util.Map;

public class CommanderAction {
    Map<UnitGroup, AIAction> actionMap;

    public Map<UnitGroup, AIAction> getActionMap() {
        return actionMap;
    }

    public void setActionMap(Map<UnitGroup, AIAction> actionList) {
        this.actionMap = actionList;
    }
}
