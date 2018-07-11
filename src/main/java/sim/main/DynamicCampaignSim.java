package sim.main;

import gen.main.CampaignGenerator;
import java.util.Calendar;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DynamicCampaignSim {
    private static final Logger log = LogManager.getLogger(DynamicCampaignSim.class);

    private GlobalSimSettings simSettings;
    private CampaignSettings campaignSettings;

    private ObjectiveManager campaignObjectiveManager;
    private MissionManager campaignMissionManager;
    private Date currentCampaignDate;

    public DynamicCampaignSim() {
        this.simSettings = new GlobalSimSettings();
        this.campaignSettings = new CampaignSettings();
        this.campaignObjectiveManager = new ObjectiveManager();
        this.campaignMissionManager = new MissionManager();
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
    }
}
