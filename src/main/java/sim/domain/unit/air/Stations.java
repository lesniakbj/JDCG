package sim.domain.unit.air;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}
