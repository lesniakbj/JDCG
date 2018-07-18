package sim.main;

import sim.domain.SimUnit;

import java.util.ArrayList;
import java.util.List;

public class ObjectiveManager {
    private List<SimUnit> mainObjectiveList;
    private List<SimUnit> secondaryObjectiveList;

    public ObjectiveManager() {
        this.mainObjectiveList = new ArrayList<>();
        this.secondaryObjectiveList = new ArrayList<>();
    }

    public List<SimUnit> getMainObjectiveList() {
        return mainObjectiveList;
    }

    public void setMainObjectiveList(List<SimUnit> mainObjectiveList) {
        this.mainObjectiveList = mainObjectiveList;
    }

    public List<SimUnit> getSecondaryObjectiveList() {
        return secondaryObjectiveList;
    }

    public void setSecondaryObjectiveList(List<SimUnit> secondaryObjectiveList) {
        this.secondaryObjectiveList = secondaryObjectiveList;
    }
}
