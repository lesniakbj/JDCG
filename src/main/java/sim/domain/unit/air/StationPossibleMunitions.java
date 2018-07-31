package sim.domain.unit.air;

import sim.domain.enums.MunitionType;
import java.util.List;

public class StationPossibleMunitions {
    private MunitionType munitionType;
    private List<Integer> possibleConfigurations;

    public MunitionType getMunitionType() {
        return munitionType;
    }

    public void setMunitionType(MunitionType munitionType) {
        this.munitionType = munitionType;
    }

    public List<Integer> getValidConfigurations() {
        return possibleConfigurations;
    }

    public void setValidConfigurations(List<Integer> validConfigurations) {
        this.possibleConfigurations = validConfigurations;
    }

    @Override
    public String toString() {
        return "{\"StationPossibleMunitions\":{"
                + "\"munitionType\":\"" + munitionType + "\""
                + ", \"possibleConfigurations\":" + possibleConfigurations
                + "}}";
    }
}
