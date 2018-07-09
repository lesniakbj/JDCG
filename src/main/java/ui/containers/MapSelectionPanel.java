package ui.containers;

import sim.domain.statics.MapConstants;

import javax.swing.JPanel;

public class MapSelectionPanel extends JPanel {
    private MapConstants map;

    public MapConstants getMap() {
        return map;
    }

    public void setMap(MapConstants map) {
        this.map = map;
    }
}
