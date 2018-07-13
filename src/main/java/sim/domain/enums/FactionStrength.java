package sim.domain.enums;

public enum FactionStrength {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private String factionStrength;

    FactionStrength(String factionStrength) {
        this.factionStrength = factionStrength;
    }

    public String getFactionStrength() {
        return factionStrength;
    }

    @Override
    public String toString() {
        return "FactionStrength{" +
                "factionStrength='" + factionStrength + '\'' +
                '}';
    }
}
