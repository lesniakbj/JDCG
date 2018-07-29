package dcsgen.translate.mission;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.settings.CampaignSettings;

import java.util.ArrayList;
import java.util.List;

public class DCSMissionData {
    private DCSMissionIDGenerator idGenerator;
    private Mission playerMission;
    private List<Mission> missions;
    private List<UnitGroup<GroundUnit>> groundUnits;
    private List<UnitGroup<AirDefenceUnit>> airDefenceUnits;
    private List<UnitGroup<AirUnit>> latentInterceptors;
    private CampaignSettings campaignSettings;

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}
