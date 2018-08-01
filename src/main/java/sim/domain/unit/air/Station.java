package sim.domain.unit.air;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Station {
    private int stationNumber;
    private List<StationPossibleMunitions> possibleMunitions;

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public List<StationPossibleMunitions> getPossibleMunitions() {
        return possibleMunitions;
    }

    public void setPossibleMunitions(List<StationPossibleMunitions> possibleMunitions) {
        this.possibleMunitions = possibleMunitions;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}
