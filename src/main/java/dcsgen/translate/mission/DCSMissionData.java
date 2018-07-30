package dcsgen.translate.mission;

import dcsgen.translate.group.DCSUnitGroup;
import dcsgen.translate.unit.DCSUnit;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.settings.CampaignSettings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DCSMissionData {
    private DCSMissionIDGenerator idGenerator;

    // Sim Data
    private Mission playerMission;
    private List<Mission> missions;
    private List<UnitGroup<GroundUnit>> groundUnits;
    private List<UnitGroup<AirDefenceUnit>> airDefenceUnits;
    private List<UnitGroup<AirUnit>> latentInterceptors;
    private CampaignSettings campaignSettings;

    // DCS Data
    private Date currentCampaignDate;
    private List<FactionType> blueFactions;
    private List<FactionType> redFactions;
    private List<FactionType> neutralFactions;

    public DCSMissionData() {
        this.idGenerator = new DCSMissionIDGenerator();
        this.groundUnits = new ArrayList<>();
        this.airDefenceUnits = new ArrayList<>();
        this.latentInterceptors = new ArrayList<>();
        this.missions = new ArrayList<>();
    }

    public void addGroundUnits(List<UnitGroup<GroundUnit>> groundUnits) {
        this.groundUnits.addAll(groundUnits);
    }

    public void addAirDefenceUnits(List<UnitGroup<AirDefenceUnit>> airDefenceUnits) {
        this.airDefenceUnits.addAll(airDefenceUnits);
    }

    public void addAirInterceptUnits(List<UnitGroup<AirUnit>> latentInterceptors) {
        this.latentInterceptors.addAll(latentInterceptors);
    }

    public void addMissions(List<Mission> missions) {
        this.missions.addAll(missions);
    }

    public void addPlayerMissions(Mission playerMission) {
        this.playerMission = playerMission;
    }

    public Mission getPlayerMission() {
        return playerMission;
    }

    public void setPlayerMission(Mission playerMission) {
        this.playerMission = playerMission;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<UnitGroup<GroundUnit>> getGroundUnits() {
        return groundUnits;
    }

    public void setGroundUnits(List<UnitGroup<GroundUnit>> groundUnits) {
        this.groundUnits = groundUnits;
    }

    public List<UnitGroup<AirDefenceUnit>> getAirDefenceUnits() {
        return airDefenceUnits;
    }

    public void setAirDefenceUnits(List<UnitGroup<AirDefenceUnit>> airDefenceUnits) {
        this.airDefenceUnits = airDefenceUnits;
    }

    public List<UnitGroup<AirUnit>> getLatentInterceptors() {
        return latentInterceptors;
    }

    public void setLatentInterceptors(List<UnitGroup<AirUnit>> latentInterceptors) {
        this.latentInterceptors = latentInterceptors;
    }

    public void setCampaignSettings(CampaignSettings campaignSettings) {
        this.campaignSettings = campaignSettings;
    }

    public CampaignSettings getCampaignSettings() {
        return campaignSettings;
    }

    public List<FactionType> getBlueFactions() {
        return blueFactions;
    }

    public void setBlueFactions(List<FactionType> blueFactions) {
        this.blueFactions = blueFactions;
    }

    public List<FactionType> getRedFactions() {
        return redFactions;
    }

    public void setRedFactions(List<FactionType> redFactions) {
        this.redFactions = redFactions;
    }

    public List<FactionType> getNeutralFactions() {
        return neutralFactions;
    }

    public void setNeutralFactions(List<FactionType> neutralFactions) {
        this.neutralFactions = neutralFactions;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    public void generate() {
        generateCampaignDate();
        generateCampaignTheatre();
        generateFactionLists();
        generateDCSGroups();
        generateDCSTriggers();
        generateDCSWeather();
    }

    private void generateCampaignTheatre() {
    }

    private void generateDCSWeather() {
    }

    private void generateDCSTriggers() {
    }

    private void generateCampaignDate() {
        this.currentCampaignDate = playerMission.getCurrentCampaignDate();
    }

    private void generateDCSGroups() {
        List<DCSUnitGroup> groups = parseUnitGroups();
    }

    private void generateFactionLists() {
        blueFactions = campaignSettings.getCoalitionBySide(FactionSideType.BLUEFOR).getFactionTypeList();
        redFactions = campaignSettings.getCoalitionBySide(FactionSideType.REDFOR).getFactionTypeList();
        neutralFactions = campaignSettings.getCoalitionBySide(FactionSideType.NEUTRAL).getFactionTypeList();
        blueFactions.sort(Comparator.comparing(FactionType::getDcsFactionName));
        redFactions.sort(Comparator.comparing(FactionType::getDcsFactionName));
        neutralFactions.sort(Comparator.comparing(FactionType::getDcsFactionName));
    }

    private List<DCSUnitGroup> parseUnitGroups() {
        List<UnitGroup<GroundUnit>> gUnits = getGroundUnits();
//      List<UnitGroup<AirDefenceUnit>> aUnits = mission.getAirDefenceUnits();
//      List<UnitGroup<AirUnit>> iUnits = mission.getLatentInterceptors();
//      List<Mission> missions = mission.getMissions();
//      Mission playerMission = mission.getPlayerMission();

        // Mission Start time
        Calendar cal = Calendar.getInstance();
        cal.setTime(playerMission.getPlannedMissionDate());
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        long totalSeconds = TimeUnit.HOURS.toSeconds(hour) + TimeUnit.MINUTES.toSeconds(minute) + seconds;

        // Return List...
        List<DCSUnitGroup> groups = new ArrayList<>();

        // Various checking...
        for(UnitGroup<GroundUnit> g : groundUnits) {
            // Create the Group
            DCSUnitGroup group = new DCSUnitGroup();
            group.setCommunication(true);
            group.setControlled(false);
            group.setHidden(false);
            group.setStartTimeSeconds(totalSeconds);
            group.setTask(null);

            // Get each unit in the group
            List<DCSUnit> groupUnits = new ArrayList<>();
            for(GroundUnit gu : g.getGroupUnits()) {
                DCSUnit unit = new DCSUnit();
                unit.setType("GROUND_UNIT");
                unit.setAltitude(0);
                unit.setHardpointRacks(false);
                unit.setSpeed(0);
                unit.setHeading(0);
                groupUnits.add(unit);
            }
            group.setUnits(groupUnits);
        }

        return groups;
    }

    public Date getCurrentCampaignDate() {
        return currentCampaignDate;
    }

    public void setCurrentCampaignDate(Date currentCampaignDate) {
        this.currentCampaignDate = currentCampaignDate;
    }
}
