package ui.constants;

public enum InfoActions implements UIAction {
    GOALS("Campaign Goals"),
    PREFERENCES("Campaign Preferences"),
    NONE("None");

    private String name;

    InfoActions(String name) {
        this.name = name;
    }

    public boolean hasSeparator(UIAction action) {
        return action.equals(GOALS);
    }

    @Override
    public String getUIName() {
        return name;
    }
}
