package ui.constants;

public interface UIAction {
    String getUIName();

    static UIAction fromUIName(String actionCommand, UIAction[] values, UIAction defaultValue) {
        for(UIAction action : values) {
            if(actionCommand.equalsIgnoreCase(action.getUIName())) {
                return action;
            }
        }

        return defaultValue;
    }

    boolean hasSeparator(UIAction action);
}
