package gen.domain;

import gen.domain.enums.AirfieldType;
import gen.domain.enums.MapType;
import java.util.List;

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
}
