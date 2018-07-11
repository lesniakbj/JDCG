package ui.constants;

public enum MissionActions implements UIAction {
    MISSION_PLANNER("Mission Planner"),
    GOALS("Campaign Goals"),
    NONE("None");

    private String name;

    MissionActions(String name) {
        this.name = name;
    }

    public boolean hasSeparator(UIAction action) {
        return false;
    }

    @Override
    public String getUIName() {
        return name;
    }
}
