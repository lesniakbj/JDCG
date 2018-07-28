package dcsgen.file.mission.domain.mission;

import dcsgen.file.mission.domain.FilePart;
import dcsgen.file.mission.domain.RequiredModules;
import dcsgen.file.mission.domain.mission.trigger.MissionTriggers;
import dcsgen.file.mission.domain.mission.trigger.TriggerLocations;

import java.util.LinkedList;
import java.util.List;

public class DCSMission {
    private RequiredModules requiredModules;
    private MissionDate missionDate;
    private MissionTriggers missionTriggers;
    private MissionResults missionResults;
    private GroundControl groundControl;
    private MissionGoals missionGoals;
    private MissionWeather missionWeather;
    private MissionTheatre missionTheatre;
    private TriggerLocations triggerLocations;

    public RequiredModules getRequiredModules() {
        return requiredModules;
    }

    public void setRequiredModules(RequiredModules requiredModules) {
        this.requiredModules = requiredModules;
    }

    public MissionDate getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(MissionDate missionDate) {
        this.missionDate = missionDate;
    }

    public MissionTriggers getMissionTriggers() {
        return missionTriggers;
    }

    public void setMissionTriggers(MissionTriggers missionTriggers) {
        this.missionTriggers = missionTriggers;
    }

    public MissionResults getMissionResults() {
        return missionResults;
    }

    public void setMissionResults(MissionResults missionResults) {
        this.missionResults = missionResults;
    }

    public GroundControl getGroundControl() {
        return groundControl;
    }

    public void setGroundControl(GroundControl groundControl) {
        this.groundControl = groundControl;
    }

    public MissionGoals getMissionGoals() {
        return missionGoals;
    }

    public void setMissionGoals(MissionGoals missionGoals) {
        this.missionGoals = missionGoals;
    }

    public MissionWeather getMissionWeather() {
        return missionWeather;
    }

    public void setMissionWeather(MissionWeather missionWeather) {
        this.missionWeather = missionWeather;
    }

    public MissionTheatre getMissionTheatre() {
        return missionTheatre;
    }

    public void setMissionTheatre(MissionTheatre missionTheatre) {
        this.missionTheatre = missionTheatre;
    }

    public TriggerLocations getTriggerLocations() {
        return triggerLocations;
    }

    public void setTriggerLocations(TriggerLocations triggerLocations) {
        this.triggerLocations = triggerLocations;
    }

    public List<FilePart> getFileLines() {
        List<FilePart> parts = new LinkedList<>();
        parts.add(requiredModules);
        parts.add(missionDate);
        parts.add(missionTriggers);
        parts.add(missionResults);
        parts.add(groundControl);
        return parts;
    }
}
