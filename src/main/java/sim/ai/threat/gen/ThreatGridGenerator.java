package sim.ai.threat.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.threat.ThreatCalculator;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.ThreatGridCell;
import sim.domain.enums.FactionSideType;
import sim.manager.CoalitionManager;

import java.awt.geom.Rectangle2D;

public class ThreatGridGenerator {
    private static final Logger log = LogManager.getLogger(ThreatGridGenerator.class);

    private ThreatCalculator threatCalculator;

    public ThreatGridGenerator() {
        threatCalculator = new ThreatCalculator();
    }

    public ThreatGrid generateThreatGridForCoalition(CoalitionManager friendlyCoalition, CoalitionManager enemyCoalition, Rectangle2D.Double threatGridBounds, FactionSideType side) {
        int x = (int)threatGridBounds.getX();
        int y = (int)threatGridBounds.getY();
        int width = (int)threatGridBounds.getWidth();
        int height = (int)threatGridBounds.getHeight();
        int cellWidth = 30;

        int adjustedWidth = 0;
        int needsXAdjust = (width % cellWidth);
        while(needsXAdjust != 0) {
            width++;
            adjustedWidth++;
            needsXAdjust = (width % cellWidth);
        }
        x = x - (adjustedWidth / 2);

        int adjustedHeight = 0;
        int needsYAdjust = (height % cellWidth);
        while(needsYAdjust != 0) {
            height++;
            adjustedHeight++;
            needsYAdjust = (height % cellWidth);
        }
        y = y - (adjustedHeight / 2);

        ThreatGrid grid = new ThreatGrid(x, y, width, height, cellWidth);
        return populateThreatGridValues(grid, friendlyCoalition, enemyCoalition);
    }

    public ThreatGrid populateThreatGridValues(ThreatGrid grid, CoalitionManager friendlyCoalition, CoalitionManager enemyCoalition) {
        for(int x = 0; x < grid.getNumCellsX(); x++) {
            for(int y = 0; y < grid.getNumCellsY(); y++) {
                ThreatGridCell cell = grid.getThreatGrid()[x][y];
                Rectangle2D.Double cellBounds = new Rectangle2D.Double(cell.getMapX(), cell.getMapY(), grid.getCellWidth(), grid.getCellWidth());
                double threatLevel = threatCalculator.calculateThreatLevel(cellBounds, friendlyCoalition, enemyCoalition);
                cell.setThreatLevel(threatLevel);
            }
        }
        return grid;
    }
}
