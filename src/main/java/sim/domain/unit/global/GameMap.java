package sim.domain.unit.global;

import java.util.List;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.MapType;

public class GameMap {
    private MapType mapSelection;
    private List<AirfieldType> airfieldTypes;

    public GameMap(MapType mapSelection) {
        this.mapSelection = mapSelection;
        this.airfieldTypes = AirfieldType.getAirfieldsForMap(mapSelection);
    }

    public void changeMap(MapType mapSelection) {
        this.mapSelection = mapSelection;
        this.airfieldTypes = AirfieldType.getAirfieldsForMap(mapSelection);
    }

    public String getMapName() {
        return mapSelection.getMapName();
    }

    public List<AirfieldType> getAirfieldTypes() {
        return airfieldTypes;
    }

    @Override
    public String toString() {
        return "Map{" +
                "mapSelection=" + mapSelection +
                ", airfieldTypes=" + airfieldTypes +
                '}';
    }

    public MapType getMapType() {
        return mapSelection;
    }
}
