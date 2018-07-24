package sim.ai.threat;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ThreatGridCell {
    private int x;
    private int y;
    private int mapX;
    private int mapY;
    private double threatLevel;
    private boolean ignoreDuringThreatCalculations;
    private ThreatGrid parentGrid;

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

    public boolean contains(double x, double y) {
        Rectangle2D.Double cellBounds = new Rectangle2D.Double(getMapX(), getMapY(), parentGrid.getCellWidth(), parentGrid.getCellWidth());
        return cellBounds.contains(x, y);
    }

    public boolean contains(Point2D.Double pos) {
        return contains(pos.getX(), pos.getY());
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

    public void setParentGrid(ThreatGrid parentGrid) {
        this.parentGrid = parentGrid;
    }

    public ThreatGrid getParentGrid() {
        return parentGrid;
    }

}
