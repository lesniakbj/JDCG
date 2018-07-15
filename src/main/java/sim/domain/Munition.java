package sim.domain;

import sim.domain.enums.MunitionType;

public class Munition {
    private MunitionType munitionType;

    public Munition(MunitionType munitionType) {
        this.munitionType = munitionType;
    }

    public MunitionType getMunitionType() {
        return munitionType;
    }

    public void setMunitionType(MunitionType munitionType) {
        this.munitionType = munitionType;
    }
}
