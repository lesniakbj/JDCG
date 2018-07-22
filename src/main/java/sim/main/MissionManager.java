package sim.main;

import sim.domain.unit.air.Mission;

import java.util.ArrayList;
import java.util.List;

public class MissionManager {
    private List<Mission> plannedMissions;
    private boolean hasAWACSActive;
    private boolean hasTankerActive;

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

    public boolean isHasAWACSActive() {
        return hasAWACSActive;
    }

    public void setHasAWACSActive(boolean hasAWACSActive) {
        this.hasAWACSActive = hasAWACSActive;
    }

    public boolean isHasTankerActive() {
        return hasTankerActive;
    }

    public void setHasTankerActive(boolean hasTankerActive) {
        this.hasTankerActive = hasTankerActive;
    }
}
