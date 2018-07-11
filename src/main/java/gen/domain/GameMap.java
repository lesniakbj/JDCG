package gen.domain;

import java.util.List;

public class GameMap {
    private MapConstants mapSelection;
    private List<Airfield> airfields;

    public GameMap(MapConstants mapSelection) {
        this.mapSelection = mapSelection;
        this.airfields = Airfield.getAirfieldsForMap(mapSelection);
    }

    public void changeMap(MapConstants mapSelection) {
        this.mapSelection = mapSelection;
        this.airfields = Airfield.getAirfieldsForMap(mapSelection);
    }

    public String getMapName() {
        return mapSelection.getMapName();
    }

    public List<Airfield> getAirfields() {
        return airfields;
    }

    @Override
    public String toString() {
        return "Map{" +
                "mapSelection=" + mapSelection +
                ", airfields=" + airfields +
                '}';
    }
}
