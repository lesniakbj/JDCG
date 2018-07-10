package ui.containers;

import javax.swing.JPanel;
import gen.domain.MapConstants;

public class MapSelectionPanel extends JPanel {
    private MapConstants map;

    public MapConstants getMap() {
        return map;
    }

    public void setMap(MapConstants map) {
        this.map = map;
    }
}
