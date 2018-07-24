package sim.ai.actions;

import java.util.List;

public class CommanderAction {
    List<AIAction> actionMap;

    public List<AIAction> getActionMap() {
        return actionMap;
    }

    public void setActionMap(List<AIAction> actionList) {
        this.actionMap = actionList;
    }
}
