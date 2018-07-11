package sim.main;

import java.util.Date;

public class DynamicCampaignSim {
    private GlobalSimSettings simSettings;
    private CampaignSettings campaignSettings;

    private MissionManager campaignMissionManager;
    private Date currentCampaignDate;

    public DynamicCampaignSim() {
        this.simSettings = new GlobalSimSettings();
        this.campaignSettings = new CampaignSettings();
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

    public void stepSimulation() {
        System.out.println("Stepping sim...");
    }
}
