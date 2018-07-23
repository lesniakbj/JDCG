package sim.ai.threat;

public class ThreatGridCell {
    private int x;
    private int y;
    private int mapX;
    private int mapY;
    private double threatLevel;
    private boolean ignoreDuringThreatCalculations;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(double threatLevel) {
        this.threatLevel = threatLevel;
    }

    public boolean isIgnoreDuringThreatCalculations() {
        return ignoreDuringThreatCalculations;
    }

    public void setIgnoreDuringThreatCalculations(boolean ignoreDuringThreatCalculations) {
        this.ignoreDuringThreatCalculations = ignoreDuringThreatCalculations;
    }

    @Override
    public String toString() {
        return "{\"ThreatGridCell\":{"
                + "\"x\":\"" + x + "\""
                + ", \"y\":\"" + y + "\""
                + ", \"threatLevel\":\"" + threatLevel + "\""
                + ", \"ignoreDuringThreatCalculations\":\"" + ignoreDuringThreatCalculations + "\""
                + "}}";
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }
}
