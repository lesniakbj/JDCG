package sim.domain;

import java.util.List;
import sim.domain.statics.Airfield;
import sim.domain.statics.MapConstants;

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

    @Override
    public String toString() {
        return "Map{" +
                "mapSelection=" + mapSelection +
                ", airfields=" + airfields +
                '}';
    }
}
