package sim.domain;

import sim.domain.statics.Airfield;
import sim.domain.statics.MapConstants;

import java.util.List;

public class Map {
    private MapConstants mapSelection;
    private List<Airfield> airfields;

    public Map(MapConstants mapSelection) {
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
