package sim.ai.threat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class ThreatGrid {
    private static final Logger log = LogManager.getLogger(ThreatGrid.class);

    private ThreatGridCell[][] threatGrid;
    private int x;
    private int y;
    private int w;
    private int h;
    private int cellWidth;
    private int numCellsX;
    private int numCellsY;

    public ThreatGrid(int x, int y, int w, int h, int cellWidth) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cellWidth = cellWidth;
        this.numCellsX = w / cellWidth;
        this.numCellsY = h / cellWidth;

        this.threatGrid = new ThreatGridCell[numCellsX][numCellsY];
        for(int xvar = 0; xvar < threatGrid.length; xvar++) {
            for(int yvar = 0; yvar < threatGrid[xvar].length; yvar++) {
                ThreatGridCell cell = new ThreatGridCell();
                cell.setThreatLevel(0.0);
                cell.setX(xvar);
                cell.setY(yvar);
                cell.setMapX(x + (xvar * cellWidth));
                cell.setMapY(y + (yvar * cellWidth));
                cell.setIgnoreDuringThreatCalculations(false);
                threatGrid[xvar][yvar] = cell;
            }
        }
    }

    public ThreatGridCell[][] getThreatGrid() {
        return threatGrid;
    }

    public void setThreatGrid(ThreatGridCell[][] threatGrid) {
        this.threatGrid = threatGrid;
    }

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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getNumCellsX() {
        return numCellsX;
    }

    public void setNumCellsX(int numCellsX) {
        this.numCellsX = numCellsX;
    }

    public int getNumCellsY() {
        return numCellsY;
    }

    public void setNumCellsY(int numCellsY) {
        this.numCellsY = numCellsY;
    }

    @Override
    public String toString() {
        return "{\"ThreatGrid\":{"
                + "\"threatGrid\":" + Arrays.deepToString(threatGrid)
                + ", \"x\":\"" + x + "\""
                + ", \"y\":\"" + y + "\""
                + ", \"w\":\"" + w + "\""
                + ", \"h\":\"" + h + "\""
                + "}}";
    }
}
