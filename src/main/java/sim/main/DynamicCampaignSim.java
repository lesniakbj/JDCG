package sim.main;

import gen.main.CampaignGenerator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Mission;
import sim.domain.UnitGroup;

public class DynamicCampaignSim {
    private static final Logger log = LogManager.getLogger(DynamicCampaignSim.class);
    private static final Random randomGen = new Random();

    private GlobalSimSettings simSettings;
    private CampaignSettings campaignSettings;

    private List<UnitGroup> campaignGroups;
    private ObjectiveManager campaignObjectiveManager;
    private MissionManager campaignMissionManager;

    private Mission currentlySelectedMission;
    private Date currentCampaignDate;

    public DynamicCampaignSim() {
        this.simSettings = new GlobalSimSettings();
        this.campaignSettings = new CampaignSettings();
        this.campaignGroups = new ArrayList<>();
        this.campaignObjectiveManager = new ObjectiveManager();
        this.campaignMissionManager = new MissionManager();
        this.currentlySelectedMission = null;
        this.currentCampaignDate = new Date();
    }

    public GlobalSimSettings getSimSettings() {
        return simSettings;
    }

    public void setSimSettings(GlobalSimSettings simSettings) {
        this.simSettings = simSettings;
    }

    public CampaignSettings getCampaignSettings() {
        return campaignSettings;
    }

    public void setCampaignSettings(CampaignSettings campaignSettings) {
        this.campaignSettings = campaignSettings;
    }

    public Date getCurrentCampaignDate() {
        return currentCampaignDate;
    }

    public void setCurrentCampaignDate(Date currentCampaignDate) {
        this.currentCampaignDate = currentCampaignDate;
    }

    public MissionManager getCampaignMissionManager() {
        return campaignMissionManager;
    }

    public void setCampaignMissionManager(MissionManager campaignMissionManager) {
        this.campaignMissionManager = campaignMissionManager;
    }

    public ObjectiveManager getCampaignObjectiveManager() {
        return campaignObjectiveManager;
    }

    public void setCampaignObjectiveManager(ObjectiveManager campaignObjectiveManager) {
        this.campaignObjectiveManager = campaignObjectiveManager;
    }

    public List<UnitGroup> getCampaignGroups() {
        return campaignGroups;
    }

    public void setCampaignGroups(List<UnitGroup> campaignGroups) {
        this.campaignGroups = campaignGroups;
    }

    public Mission getCurrentlySelectedMission() {
        return currentlySelectedMission;
    }

    public void setCurrentlySelectedMission(Mission currentlySelectedMission) {
        this.currentlySelectedMission = currentlySelectedMission;
    }

    public static Random getRandomGen() {
        return randomGen;
    }

    public void stepSimulation() {
        log.debug("Stepping sim...");
        int minutesToStep = simSettings.getMinutesPerSimulationStep();

        // Step the current simulation time
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentCampaignDate);
        cal.add(Calendar.MINUTE, minutesToStep);
        this.currentCampaignDate = cal.getTime();

        // Step all of the sim objects
        //  1) Sim all existing missions
        //  2) Generate new missions
    }

    public void generateNewCampaign() {
        log.debug("Generating a new campaign...");
        CampaignGenerator gen = new CampaignGenerator(campaignSettings);
        campaignMissionManager.addMission(new Mission());
    }
}
