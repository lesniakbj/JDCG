package sim.main;

import dcsgen.DCSMissionGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.GroundUnit;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.enums.FactionSide;
import sim.gen.CampaignGenerator;
import sim.gen.MissionGenerator;
import ui.containers.CampaignPanel;

import javax.swing.border.Border;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DynamicCampaignSim {
    private static final Logger log = LogManager.getLogger(DynamicCampaignSim.class);
    private static final Random randomGen = new Random();

    // Campaign settings
    private GlobalSimSettings simSettings;
    private CampaignSettings campaignSettings;

    // Campaign data
    private Map<FactionSide, List<Point2D.Double>> warfareFront;
    private CoalitionManager blueforCoalitionManager;
    private CoalitionManager redforCoalitionManager;

    // Selected data
    private Mission currentlySelectedMission;
    private boolean generateMission;

    // Background Sim
    private transient ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    private transient ScheduledFuture<?> scheduledFuture;
    private transient boolean simRunning;

    public DynamicCampaignSim() {
        this.simSettings = new GlobalSimSettings();
        this.campaignSettings = new CampaignSettings();
        this.warfareFront = new LinkedHashMap<>();
        this.blueforCoalitionManager = new CoalitionManager(new ArrayList<>(), new ObjectiveManager(), new MissionManager());
        this.redforCoalitionManager = new CoalitionManager(new ArrayList<>(), new ObjectiveManager(), new MissionManager());
        this.currentlySelectedMission = null;
        this.generateMission = false;
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
        return campaignSettings.getCurrentCampaignDate();
    }

    public void setCurrentCampaignDate(Date currentCampaignDate) {
        campaignSettings.setCurrentCampaignDate(currentCampaignDate);
    }

    public MissionManager getCampaignMissionManager() {
        return campaignSettings.getPlayerSelectedSide().equals(FactionSide.BLUEFOR) ? blueforCoalitionManager.getMissionManager() : redforCoalitionManager.getMissionManager();
    }

    public ObjectiveManager getCampaignObjectiveManager() {
        return campaignSettings.getPlayerSelectedSide().equals(FactionSide.BLUEFOR) ? blueforCoalitionManager.getCoalitionObjectiveManager() : redforCoalitionManager.getCoalitionObjectiveManager();
    }

    public List<UnitGroup<GroundUnit>> getCampaignFrontlineGroups() {
        return campaignSettings.getPlayerSelectedSide().equals(FactionSide.BLUEFOR) ? blueforCoalitionManager.getCoalitionFrontlineGroups() : redforCoalitionManager.getCoalitionFrontlineGroups();
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
        int minutesToStep = simSettings.getMinutesPerSimulationStep();

        // Step the current simulation time
        Calendar cal = Calendar.getInstance();
        cal.setTime(campaignSettings.getCurrentCampaignDate());
        cal.add(Calendar.MINUTE, minutesToStep);
        campaignSettings.setCurrentCampaignDate(cal.getTime());

        // Step all of the sim objects
        stepMissions(minutesToStep);

        // Generate new missions

    }

    private void stepMissions(int minutesToStep) {
        // Gather all of the planned/running missions
        List<Mission> criticalMissions = new ArrayList<>();
        List<Mission> allMissions = new ArrayList<>();
        allMissions.addAll(blueforCoalitionManager.getCoalitionMissionManager().getPlannedMissions());
        allMissions.addAll(redforCoalitionManager.getCoalitionMissionManager().getPlannedMissions());
        Collections.shuffle(allMissions);

        // Simulate each of the gathered missions
        List<Mission> completedMissions = new ArrayList<>();
        for(Mission m : allMissions) {
            // Update the mission
            m.setCurrentCampaignDate(campaignSettings.getCurrentCampaignDate());
            m.setMinutesPerUpdate(minutesToStep);
            m.updateStep();

            // Check if we need to generate a DCS mission
            if(m.shouldGenerateMission() && m.getMissionAircraft().getSide().equals(FactionSide.BLUEFOR)) {
                criticalMissions.add(m);
            }

            // Check if we need to sim the results of this mission locally
            if(m.onObjectiveWaypoint()) {
                // Simulate the mission...
                MissionSimulator.simulateMission(m, campaignSettings, blueforCoalitionManager, redforCoalitionManager);
            }

            // If the mission is complete, remove it from the active missions
            if(m.isMissionComplete()) {
                // Remove the element
                completedMissions.add(m);
            }
        }
        blueforCoalitionManager.getCoalitionMissionManager().getPlannedMissions().removeAll(completedMissions);
        redforCoalitionManager.getCoalitionMissionManager().getPlannedMissions().removeAll(completedMissions);

        // If we determine that we need to generate a mission, generate it and then alert the user
        generateMission = !criticalMissions.isEmpty();
        if(generateMission) {
            DCSMissionGenerator gen = new DCSMissionGenerator();
            gen.generateMission(criticalMissions.get(0), blueforCoalitionManager, redforCoalitionManager, simSettings.getMissionStartType());
            setSimRunning(false);
        }

        // Have the campaign managers analyze the situation and plan new flights if needed / can
        // This is a test....
        MissionGenerator missionGenerator = new MissionGenerator();
        if(blueforCoalitionManager.getCoalitionMissionManager().getPlannedMissions().size() < 8) {
            missionGenerator.generateTestMissionForCoalition(this, blueforCoalitionManager, getCurrentCampaignDate());
        }
        if(redforCoalitionManager.getCoalitionMissionManager().getPlannedMissions().size() < 8) {
            missionGenerator.generateTestRedforMissionForCoalition(this, redforCoalitionManager, getCurrentCampaignDate());
        }
    }

    public void generateNewCampaign() {
        log.debug("Generating a new campaign...");
        CampaignGenerator gen = new CampaignGenerator(campaignSettings);

        // Generate the date of the campaign
        campaignSettings.setCurrentCampaignDate(gen.generateCampaignDate());

        // First, generate the airbases that are going to be assigned to each team based on the settings of the campaign
        Map<FactionSide, List<Airfield>> generatedAirfields = gen.generateAirfieldMap();
        warfareFront = gen.generateWarfareFront(generatedAirfields);
        generatedAirfields = gen.adjustAirfieldsIfNeeded(warfareFront, generatedAirfields);
        blueforCoalitionManager.setCoalitionAirfields(generatedAirfields.get(FactionSide.BLUEFOR));
        redforCoalitionManager.setCoalitionAirfields(generatedAirfields.get(FactionSide.REDFOR));

        // Then, generate all of the static ground units that exist within this campaign (aka air fields)
        Map<Airfield, List<UnitGroup<GroundUnit>>> generatedPointDefenceUnitsBlue = gen.generatePointDefenceUnits(generatedAirfields, FactionSide.BLUEFOR);
        Map<Airfield, List<UnitGroup<GroundUnit>>> generatedPointDefenceUnitsRed = gen.generatePointDefenceUnits(generatedAirfields, FactionSide.REDFOR);
        blueforCoalitionManager.setCoalitionPointDefenceGroundUnits(generatedPointDefenceUnitsBlue);
        redforCoalitionManager.setCoalitionPointDefenceGroundUnits(generatedPointDefenceUnitsRed);

        // Then, generate all of the ground groups that exist with this campaign (aka front line units)
        //        blueforCoalitionManager.setCoalitionFrontLineGroundUnits(UnitGroup<GroundUnit> generatedAirfields.get(FactionSide.BLUEFOR));
        //        redforCoalitionManager.setCoalitionFrontLineGroundUnits(UnitGroup<GroundUnit> generatedAirfields.get(FactionSide.REDFOR));

        // Then, generate all of the AAA/SAM groups that exist within this campaign (mostly airfields, occasionally behind front lines)
        //        blueforCoalitionManager.setCoalitionAirDefences(UnitGroup<AirDefence> generatedAirfields.get(FactionSide.BLUEFOR));
        //        redforCoalitionManager.setCoalitionAirDefences(UnitGroup<AirDefence> generatedAirfields.get(FactionSide.REDFOR));

        // Then, generate all of the AirForce groups that exist within this campaign
        //        blueforCoalitionManager.setCoalitionAirGroups(generatedAirfields.get(FactionSide.BLUEFOR));
        //        redforCoalitionManager.setCoalitionAirGroups(generatedAirfields.get(FactionSide.REDFOR));
    }

    public void runSimulation(CampaignPanel campaignPanel, int imageWidth, int imageHeight, Border padding, Border bevel) {
        scheduledFuture = exec.scheduleAtFixedRate(() -> {
            log.debug("Running task...");
            log.debug(simRunning);
            if(simRunning) {
                stepSimulation();
                campaignPanel.updateSimulationGUI(imageWidth, imageHeight, padding, bevel);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public void setSimRunning(boolean simRunning) {
        this.simRunning = simRunning;

        if(!simRunning && scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public boolean isSimRunning() {
        return simRunning;
    }
}
