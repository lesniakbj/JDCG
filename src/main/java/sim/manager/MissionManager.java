package sim.manager;

import sim.ai.threat.ThreatGrid;
import sim.domain.unit.air.Mission;

import java.util.ArrayList;
import java.util.List;

public class MissionManager {
    private List<Mission> plannedMissions;
    private ThreatGrid threatGrid;
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

    public ThreatGrid getThreatGrid() {
        return threatGrid;
    }

    public void setThreatGrid(ThreatGrid threatGrid) {
        this.threatGrid = threatGrid;
    }

    @Override
    public String toString() {
        return "{\"MissionManager\":{"
                + "\"plannedMissions\":" + plannedMissions
                + ", \"threatGrid\":" + threatGrid
                + ", \"hasAWACSActive\":\"" + hasAWACSActive + "\""
                + ", \"hasTankerActive\":\"" + hasTankerActive + "\""
                + "}}";
    }
}
