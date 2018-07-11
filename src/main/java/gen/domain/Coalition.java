package gen.domain;

import gen.domain.enums.FactionType;
import java.util.List;

public class Coalition {
    private List<FactionType> factionTypeList;

    public Coalition(List<FactionType> factionTypeList) {
        this.factionTypeList = factionTypeList;
    }

    @Override
    public String toString() {
        return "Coalition{" +
                "factionTypeList=" + factionTypeList +
                '}';
    }

    public List<FactionType> getFactionTypeList() {
        return factionTypeList;
    }
}
