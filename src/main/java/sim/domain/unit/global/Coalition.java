package sim.domain.unit.global;

import java.util.List;
import sim.domain.enums.FactionType;

public class Coalition {
    private List<FactionType> factionTypeList;

    public Coalition(List<FactionType> factionTypeList) {
        this.factionTypeList = factionTypeList;
    }

    @Override
    public String toString() {
        return "{\"Coalition\":{"
                + "\"factionTypeList\":" + factionTypeList
                + "}}";
    }

    public List<FactionType> getFactionTypeList() {
        return factionTypeList;
    }
}
