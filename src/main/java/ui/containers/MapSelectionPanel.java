package ui.containers;

import javax.swing.JPanel;
import sim.domain.statics.MapConstants;

public class MapSelectionPanel extends JPanel {
    private MapConstants map;

    public MapConstants getMap() {
        return map;
    }

    public void setMap(MapConstants map) {
        this.map = map;
    }
}
