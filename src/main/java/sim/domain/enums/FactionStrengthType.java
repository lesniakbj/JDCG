package sim.domain.enums;

public enum FactionStrengthType {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private String factionStrength;

    FactionStrengthType(String factionStrength) {
        this.factionStrength = factionStrength;
    }

    public String getFactionStrength() {
        return factionStrength;
    }

    @Override
    public String toString() {
        return "FactionStrengthType{" +
                "factionStrength='" + factionStrength + '\'' +
                '}';
    }
}
