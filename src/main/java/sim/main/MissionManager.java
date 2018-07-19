package sim.main;

import java.util.ArrayList;
import java.util.List;
import sim.domain.unit.air.Mission;

public class MissionManager {
    private List<Mission> plannedMissions;

    public MissionManager() {
        this.plannedMissions = new ArrayList<>();
    }

    public void addMission(Mission mission) {
        if(plannedMissions == null) {
            plannedMissions = new ArrayList<>();
        }
        plannedMissions.add(mission);
    }

    public List<Mission> getPlannedMissions() {
        return plannedMissions;
    }

    public void setPlannedMissions(List<Mission> plannedMissions) {
        this.plannedMissions = plannedMissions;
    }
}
