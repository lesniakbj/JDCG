package sim.domain.unit.air;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.MunitionType;

public class WeaponStation {
    private int stationNumber;
    private Munition munition;

    public WeaponStation(int stationNumber, Munition munition) {
        this.stationNumber = stationNumber;
        this.munition = munition;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public MunitionType getMunitionType() {
        return munition.getMunitionType();
    }

    public void setMunitionType(MunitionType munitionType) {
        this.munition.setMunitionType(munitionType);
    }

    public int getTotalLoaded() {
        return this.munition.getTotalLoaded();
    }

    public void setTotalLoaded(int totalLoaded) {
        this.munition.setTotalLoaded(totalLoaded);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}
