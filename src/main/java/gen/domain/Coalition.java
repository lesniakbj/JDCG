package gen.domain;

import java.util.List;

public class Coalition {
    private List<Faction> factionList;

    public Coalition(List<Faction> factionList) {
        this.factionList = factionList;
    }

    @Override
    public String toString() {
        return "Coalition{" +
                "factionList=" + factionList +
                '}';
    }

    public List<Faction> getFactionList() {
        return factionList;
    }
}
