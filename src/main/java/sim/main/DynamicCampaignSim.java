package sim.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.enums.FactionSide;
import sim.gen.CampaignGenerator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DynamicCampaignSim {
    private static final Logger log = LogManager.getLogger(DynamicCampaignSim.class);
    private static final Random randomGen = new Random();

    private GlobalSimSettings simSettings;
    private CampaignSettings campaignSettings;

    private Map<FactionSide, List<Point2D.Double>> warfareFront;
    private CoalitionManager blueforCoalitionManager;
    private CoalitionManager redforCoalitionManager;

    private Mission currentlySelectedMission;
    private Date currentCampaignDate;

    public DynamicCampaignSim() {
        this.simSettings = new GlobalSimSettings();
        this.campaignSettings = new CampaignSettings();
        this.warfareFront = new LinkedHashMap<>();
        this.blueforCoalitionManager = new CoalitionManager(new ArrayList<>(), new ObjectiveManager(), new MissionManager());
        this.redforCoalitionManager = new CoalitionManager(new ArrayList<>(), new ObjectiveManager(), new MissionManager());
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
        return campaignSettings.getPlayerSelectedSide().equals(FactionSide.BLUEFOR) ? blueforCoalitionManager.getMissionManager() : redforCoalitionManager.getMissionManager();
    }

    public ObjectiveManager getCampaignObjectiveManager() {
        return campaignSettings.getPlayerSelectedSide().equals(FactionSide.BLUEFOR) ? blueforCoalitionManager.getCoalitionObjectiveManager() : redforCoalitionManager.getCoalitionObjectiveManager();
    }

    public List<UnitGroup> getCampaignGroups() {
        return campaignSettings.getPlayerSelectedSide().equals(FactionSide.BLUEFOR) ? blueforCoalitionManager.getCoalitionGroups() : redforCoalitionManager.getCoalitionGroups();
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

    public CoalitionManager getBlueforCoalitionManager() {
        return blueforCoalitionManager;
    }

    public CoalitionManager getRedforCoalitionManager() {
        return redforCoalitionManager;
    }

    public Map<FactionSide, List<Point2D.Double>> getWarfareFront() {
        return warfareFront;
    }

    public void stepSimulation() {
        log.debug("Stepping sim...");
        int minutesToStep = 5; //simSettings.getMinutesPerSimulationStep();

        // Step the current simulation time
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentCampaignDate);
        cal.add(Calendar.MINUTE, minutesToStep);
        this.currentCampaignDate = cal.getTime();

        // Step all of the sim objects
        //  1) Sim all existing missions
        //  2) Generate new missions
        for(Mission m : blueforCoalitionManager.getCoalitionMissionManager().getActiveMissions()) {
            log.debug(m);
            m.setMinutesPerUpdate(minutesToStep);
            m.updateStep();

            if(m.isMissionComplete()) {
                blueforCoalitionManager.getCoalitionMissionManager().getActiveMissions().remove(m);
            }
        }
    }

    public void generateNewCampaign() {
        log.debug("Generating a new campaign...");
        CampaignGenerator gen = new CampaignGenerator(campaignSettings);

        // First, generate the airbases that are going to be assigned to each team based on the settings
        Map<FactionSide, List<Airfield>> generatedAirfields = gen.generateAirfieldMap();
        warfareFront = gen.generateWarfareFront();
        generatedAirfields = gen.adjustAirfieldsIfNeeded(warfareFront, generatedAirfields);
        blueforCoalitionManager.setCoalitionAirfields(generatedAirfields.get(FactionSide.BLUEFOR));
        redforCoalitionManager.setCoalitionAirfields(generatedAirfields.get(FactionSide.REDFOR));

        // Then, generate all of the static ground units that exist within this campaign

        // Then, generate all of the ground groups that exist with this campaign

        // Then, generate all of the AAA/SAM groups that exist within this campaign

        // Then, generate all of the AirForce groups that exist within this campaign

        // This is a test....
        // blueforCoalitionManager.getCoalitionMissionManager().addMission(new Mission());
        // blueforCoalitionManager.getCoalitionMissionManager().addMission(new Mission(2));
    }
}
