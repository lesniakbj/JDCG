package ui.constants;

public enum FileActions implements UIAction {
    NEW("New Campaign"),
    OPEN("Open Campaign"),
    OPEN_RECENT("Open Recent"),
    EXIT("Exit"),
    NONE("None");

    private String name;

    FileActions(String name) {
        this.name = name;
    }

    @Override
    public String getUIName() {
        return name;
    }
}
