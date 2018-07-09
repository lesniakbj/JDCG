package ui.constants;

public enum ViewActions implements UIAction {
    AIRFIELD_LIST("Airfield List"),
    SQUADRON_LIST("Group List"),
    PILOT_LIST("Pilot List"),
    NONE("None");

    private String name;

    ViewActions(String name) {
        this.name = name;
    }

    @Override
    public String getUIName() {
        return name;
    }
}
