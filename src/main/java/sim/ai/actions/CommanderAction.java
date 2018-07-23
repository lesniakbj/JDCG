package sim.ai.actions;

import sim.domain.unit.UnitGroup;

import java.util.List;
import java.util.Map;

public class CommanderAction {
    List<Map<UnitGroup, AIAction>> actionList;

    public List<Map<UnitGroup, AIAction>> getActionList() {
        return actionList;
    }

    public void setActionList(List<Map<UnitGroup, AIAction>> actionList) {
        this.actionList = actionList;
    }
}
