package ui.containers;

import gen.domain.enums.MapType;

import javax.swing.JPanel;

public class MapSelectionPanel extends JPanel {
    private MapType map;

    public MapType getMap() {
        return map;
    }

    public void setMap(MapType map) {
        this.map = map;
    }
}
