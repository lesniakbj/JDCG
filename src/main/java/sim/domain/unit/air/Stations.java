package sim.domain.unit.air;

import java.util.List;

public class Stations {
    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }

    public void gsetStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "{\"Stations\":{"
                + "\"stations\":" + stations
                + "}}";
    }
}
