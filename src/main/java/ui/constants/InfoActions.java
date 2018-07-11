package ui.constants;

public enum InfoActions implements UIAction {
    GOALS("Campaign Goals"),
    NONE("None");

    private String name;

    InfoActions(String name) {
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
