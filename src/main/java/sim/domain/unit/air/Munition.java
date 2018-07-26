package sim.domain.unit.air;

import sim.domain.enums.MunitionType;

public class Munition {
    private MunitionType munitionType;
    private int totalLoaded;

    public Munition(MunitionType munitionType, int totalLoaded) {
        this.munitionType = munitionType;
        this.totalLoaded = totalLoaded;
    }

    public MunitionType getMunitionType() {
        return munitionType;
    }

    public void setMunitionType(MunitionType munitionType) {
        this.munitionType = munitionType;
    }

    public int getTotalLoaded() {
        return totalLoaded;
    }

    public void setTotalLoaded(int totalLoaded) {
        this.totalLoaded = totalLoaded;
    }
}
