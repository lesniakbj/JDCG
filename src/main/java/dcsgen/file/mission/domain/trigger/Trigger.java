package dcsgen.file.mission.domain.trigger;

import java.util.List;

public class Trigger {
    private int triggerId;
    private List<TriggerAction> actions;
    private TriggerFunction func;
    private boolean flag;
    private TriggerCondition condition;
    private TriggerFunction startupFunction;

    public int getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(int triggerId) {
        this.triggerId = triggerId;
    }

    public List<TriggerAction> getActions() {
        return actions;
    }

    public void setActions(List<TriggerAction> actions) {
        this.actions = actions;
    }

    public TriggerFunction getFunc() {
        return func;
    }

    public void setFunc(TriggerFunction func) {
        this.func = func;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public TriggerCondition getCondition() {
        return condition;
    }

    public void setCondition(TriggerCondition condition) {
        this.condition = condition;
    }

    public TriggerFunction getStartupFunction() {
        return startupFunction;
    }

    public void setStartupFunction(TriggerFunction startupFunction) {
        this.startupFunction = startupFunction;
    }
}
