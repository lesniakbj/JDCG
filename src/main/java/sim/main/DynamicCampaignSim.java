package sim.main;

import java.util.Date;

public class DynamicCampaignSim {
    private GlobalSimSettings simSettings;
    private CampaignSettings campaignSettings;

    private MissionManager campaignMissionManager;
    private Date currentCampaignDate;

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

    public void stepSimulation() {
        System.out.println("Stepping sim...");
    }
}
