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
    private MaxGeneratedId maxGeneratedId;
    private PictureFileNameN pictureFileNameN;
    private GroundControl groundControl;
    private MissionGoals missionGoals;
    private MissionWeather missionWeather;
    private MissionTheatre missionTheatre;
    private TriggerLocations triggerLocations;
    private MapLocation mapLocation;
    private MissionCoalitions missionCoalitions;
    private MissionDescriptionText missionDescriptionText;
    private PictureFileNameR pictureFileNameR;
    private NeutralTaskText neutralTaskText;
    private BlueTaskText blueTaskText;
    private RedTaskText redTaskText;
    private PictureFileNameB pictureFileNameB;
    private CoalitionDetails coalitionDetails;
    private SortieText sortieText;
    private VersionText versionText;
    private TrigrulesSection trigrulesSection;
    private CurrentKeyText currentKeyText;
    private MissionStartTime missionStartTime;
    private ForcedOptions forcedOptions;
    private AircraftFailures aircraftFailures;

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

    public MaxGeneratedId getMaxGeneratedId() {
        return maxGeneratedId;
    }

    public void setMaxGeneratedId(MaxGeneratedId maxGeneratedId) {
        this.maxGeneratedId = maxGeneratedId;
    }

    public PictureFileNameN getPictureFileNameN() {
        return pictureFileNameN;
    }

    public void setPictureFileNameN(PictureFileNameN pictureFileName) {
        this.pictureFileNameN = pictureFileName;
    }

    public MapLocation getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(MapLocation mapLocation) {
        this.mapLocation = mapLocation;
    }

    public MissionCoalitions getMissionCoalitions() {
        return missionCoalitions;
    }

    public void setMissionCoalitions(MissionCoalitions missionCoalitions) {
        this.missionCoalitions = missionCoalitions;
    }

    public MissionDescriptionText getMissionDescriptionText() {
        return missionDescriptionText;
    }

    public void setMissionDescriptionText(MissionDescriptionText missionDescriptionText) {
        this.missionDescriptionText = missionDescriptionText;
    }

    public PictureFileNameR getPictureFileNameR() {
        return pictureFileNameR;
    }

    public void setPictureFileNameR(PictureFileNameR pictureFileNameR) {
        this.pictureFileNameR = pictureFileNameR;
    }

    public NeutralTaskText getNeutralTaskText() {
        return neutralTaskText;
    }

    public void setNeutralTaskText(NeutralTaskText neutralTaskText) {
        this.neutralTaskText = neutralTaskText;
    }

    public BlueTaskText getBlueTaskText() {
        return blueTaskText;
    }

    public void setBlueTaskText(BlueTaskText blueTaskText) {
        this.blueTaskText = blueTaskText;
    }

    public RedTaskText getRedTaskText() {
        return redTaskText;
    }

    public void setRedTaskText(RedTaskText redTaskText) {
        this.redTaskText = redTaskText;
    }

    public PictureFileNameB getPictureFileNameB() {
        return pictureFileNameB;
    }

    public void setPictureFileNameB(PictureFileNameB picturefileNameB) {
        this.pictureFileNameB = picturefileNameB;
    }

    public CoalitionDetails getCoalitionDetails() {
        return coalitionDetails;
    }

    public void setCoalitionDetails(CoalitionDetails coalitionDetails) {
        this.coalitionDetails = coalitionDetails;
    }

    public SortieText getSortieText() {
        return sortieText;
    }

    public void setSortieText(SortieText sortieText) {
        this.sortieText = sortieText;
    }

    public VersionText getVersionText() {
        return versionText;
    }

    public void setVersionText(VersionText versionText) {
        this.versionText = versionText;
    }

    public TrigrulesSection getTrigrulesSection() {
        return trigrulesSection;
    }

    public void setTrigrulesSection(TrigrulesSection trigrulesSection) {
        this.trigrulesSection = trigrulesSection;
    }

    public CurrentKeyText getCurrentKeyText() {
        return currentKeyText;
    }

    public void setCurrentKeyText(CurrentKeyText currentKeyText) {
        this.currentKeyText = currentKeyText;
    }

    public MissionStartTime getMissionStartTime() {
        return missionStartTime;
    }

    public void setMissionStartTime(MissionStartTime missionStartTime) {
        this.missionStartTime = missionStartTime;
    }

    public ForcedOptions getForcedOptions() {
        return forcedOptions;
    }

    public void setForcedOptions(ForcedOptions forcedOptions) {
        this.forcedOptions = forcedOptions;
    }

    public AircraftFailures getAircraftFailures() {
        return aircraftFailures;
    }

    public void setAircraftFailures(AircraftFailures aircraftFailures) {
        this.aircraftFailures = aircraftFailures;
    }

    public List<FilePart> getFileLines() {
        List<FilePart> parts = new LinkedList<>();
        parts.add(requiredModules);
        parts.add(missionDate);
        parts.add(missionTriggers);
        parts.add(missionResults);
        parts.add(maxGeneratedId);
        parts.add(pictureFileNameN);
        parts.add(groundControl);
        parts.add(missionGoals);
        parts.add(missionWeather);
        parts.add(missionTheatre);
        parts.add(triggerLocations);
        parts.add(mapLocation);
        parts.add(missionCoalitions);
        parts.add(missionDescriptionText);
        parts.add(pictureFileNameR);
        parts.add(neutralTaskText);
        parts.add(blueTaskText);
        parts.add(redTaskText);
        parts.add(pictureFileNameB);
        parts.add(coalitionDetails);
        parts.add(sortieText);
        parts.add(versionText);
        parts.add(trigrulesSection);
        parts.add(currentKeyText);
        parts.add(missionStartTime);
        parts.add(forcedOptions);
        parts.add(aircraftFailures);
       return parts;
    }
}
