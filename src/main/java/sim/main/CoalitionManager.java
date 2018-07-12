package sim.main;

import sim.domain.Airfield;
import sim.domain.UnitGroup;

import java.util.List;

public class CoalitionManager {
    private List<Airfield> coalitionAirfields;
    private List<UnitGroup> coalitionGroups;
    private ObjectiveManager coalitionObjectiveManager;
    private MissionManager coalitionMissionManager;

    public CoalitionManager(List<UnitGroup> coalitionGroups, ObjectiveManager coalitionObjectiveManager, MissionManager coalitionMissionManager) {
        this.coalitionGroups = coalitionGroups;
        this.coalitionObjectiveManager = coalitionObjectiveManager;
        this.coalitionMissionManager = coalitionMissionManager;
    }

    public List<Airfield> getCoalitionAirfields() {
        return coalitionAirfields;
    }

    public void setCoalitionAirfields(List<Airfield> coalitionAirfields) {
        this.coalitionAirfields = coalitionAirfields;
    }

    public MissionManager getMissionManager() {
        return coalitionMissionManager;
    }

    public List<UnitGroup> getCoalitionGroups() {
        return coalitionGroups;
    }

    public void setCoalitionGroups(List<UnitGroup> coalitionGroups) {
        this.coalitionGroups = coalitionGroups;
    }

    public ObjectiveManager getCoalitionObjectiveManager() {
        return coalitionObjectiveManager;
    }

    public void setCoalitionObjectiveManager(ObjectiveManager coalitionObjectiveManager) {
        this.coalitionObjectiveManager = coalitionObjectiveManager;
    }

    public MissionManager getCoalitionMissionManager() {
        return coalitionMissionManager;
    }

    public void setCoalitionMissionManager(MissionManager coalitionMissionManager) {
        this.coalitionMissionManager = coalitionMissionManager;
    }
}
