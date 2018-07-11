package ui.constants;

public enum CoalitionActions implements UIAction {
    AIRFIELDS("Airfields"),
    MUNITIONS("Munition Stockpiles"),
    SQUADRONS("Squadrons"),
    PILOTS("Pilots"),
    AIRCRAFT("Aircraft"),
    NONE("None");

    private String name;

    CoalitionActions(String name) {
        this.name = name;
    }

    public boolean hasSeparator(UIAction action) {
        return action.equals(MUNITIONS) || action.equals(PILOTS);
    }

    @Override
    public String getUIName() {
        return name;
    }
}
