package sim.domain.unit.air;

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
        return "{\"Station\":{"
                + "\"stationNumber\":\"" + stationNumber + "\""
                + ", \"possibleMunitions\":" + possibleMunitions
                + "}}";
    }
}
