package ui.containers;

import javax.swing.JPanel;
import sim.domain.enums.MapType;

public class MapSelectionPanel extends JPanel {
    private MapType map;

    public MapType getMap() {
        return map;
    }

    public void setMap(MapType map) {
        this.map = map;
    }
}
