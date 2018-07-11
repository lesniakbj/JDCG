package ui.constants;

public enum FileActions implements UIAction {
    NEW("New Campaign"),
    OPEN("Open Campaign"),
    OPEN_RECENT("Open Recent"),
    SAVE("Save Campaign"),
    SAVE_AS("Save As..."),
    EXIT("Exit"),
    NONE("None");

    private String name;

    FileActions(String name) {
        this.name = name;
    }

    public boolean hasSeparator(UIAction action) {
        return action.equals(NEW) || action.equals(OPEN_RECENT) || action.equals(SAVE_AS);
    }

    @Override
    public String getUIName() {
        return name;
    }
}
