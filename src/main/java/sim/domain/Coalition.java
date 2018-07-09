package sim.domain;

import sim.domain.statics.Faction;

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
}
