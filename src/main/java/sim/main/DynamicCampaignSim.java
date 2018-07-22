package sim.main;

import dcsgen.DCSMissionGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.CampaignType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.Mission;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.gen.CampaignGenerator;
import sim.gen.MissionGenerator;
import ui.containers.CampaignPanel;

import javax.swing.border.Border;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DynamicCampaignSim {
    private static final Logger log = LogManager.getLogger(DynamicCampaignSim.class);
    private static final Random randomGen = new Random();

    // Campaign settings
    private GlobalSimSettings simSettings;
    private CampaignSettings campaignSettings;

    // Campaign data
    private Map<FactionSideType, List<Point2D.Double>> warfareFront;
    private CoalitionManager blueforCoalitionManager;
    private CoalitionManager redforCoalitionManager;

    // Generators
    private MissionGenerator missionGenerator;

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
        this.missionGenerator = new MissionGenerator();
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
        return campaignSettings.getPlayerSelectedSide().equals(FactionSideType.BLUEFOR) ? blueforCoalitionManager.getMissionManager() : redforCoalitionManager.getMissionManager();
    }

    public ObjectiveManager getCampaignObjectiveManager() {
        return campaignSettings.getPlayerSelectedSide().equals(FactionSideType.BLUEFOR) ? blueforCoalitionManager.getCoalitionObjectiveManager() : redforCoalitionManager.getCoalitionObjectiveManager();
    }

    public List<UnitGroup<GroundUnit>> getCampaignFrontlineGroups() {
        return campaignSettings.getPlayerSelectedSide().equals(FactionSideType.BLUEFOR) ? blueforCoalitionManager.getCoalitionFrontlineGroups() : redforCoalitionManager.getCoalitionFrontlineGroups();
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

    public List<Airfield> getAllAirfields() {
        List<Airfield> airfields = new ArrayList<>();
        airfields.addAll(blueforCoalitionManager.getCoalitionAirfields());
        airfields.addAll(redforCoalitionManager.getCoalitionAirfields());
        return airfields;
    }

    public Map<Airfield, List<UnitGroup<GroundUnit>>> getAllAirfieldGroundGroups() {
        Map<Airfield, List<UnitGroup<GroundUnit>>> airfieldGroups = new HashMap<>();
        airfieldGroups.putAll(blueforCoalitionManager.getCoalitionPointDefenceGroundUnits());
        airfieldGroups.putAll(redforCoalitionManager.getCoalitionPointDefenceGroundUnits());
        return airfieldGroups;
    }

    public CoalitionManager getBlueforCoalitionManager() {
        return blueforCoalitionManager;
    }

    public CoalitionManager getRedforCoalitionManager() {
        return redforCoalitionManager;
    }

    public Map<FactionSideType, List<Point2D.Double>> getWarfareFront() {
        return warfareFront;
    }

    /**
     * Is used to generate a new DCS Dynamic Campaign. Goes through the following
     * process to generate the campaign:
     *  1) Generate the date based on the campaign era
     *  2) Generate the starting airbases for each side, as well as a "warfare" front, which
     *     defines where the combat is taking place.
     *  3) Generate static ground units (aka. units that are defending airbases)
     *  4) Generate ground units around the warfare front
     *  5) Generate a set of air defences for both airbases and some of the generated troops
     *  6) Generate the air forces for each side
     *
     * This will produce a campaign in which we can generate missions for the player to
     * participate in. Further, we can generate missions in which we can simulate what is going
     * on, but this will happen during when we step the simulation.
     */
    public void generateNewCampaign() {
        log.debug("Generating a new campaign with the following settings...");
        log.debug("Campaign Settings: " + campaignSettings);
        CampaignGenerator gen = new CampaignGenerator(campaignSettings);

        // Generate the date of the campaign
        log.debug("Generating the current campaign date...");
        campaignSettings.setCurrentCampaignDate(gen.generateCampaignDate());

        // First, generate the airbases that are going to be assigned to each team based on the settings of the campaign
        log.debug("Generating the current campaign airfields...");
        Map<FactionSideType, List<Airfield>> generatedAirfields = gen.generateAirfieldMap();
        warfareFront = gen.generateWarfareFront(generatedAirfields);
        generatedAirfields = gen.adjustAirfieldsIfNeeded(warfareFront, generatedAirfields);
        blueforCoalitionManager.setCoalitionAirfields(generatedAirfields.get(FactionSideType.BLUEFOR));
        redforCoalitionManager.setCoalitionAirfields(generatedAirfields.get(FactionSideType.REDFOR));

        // Then, generate all of the static ground units that exist within this campaign (aka air fields)
        log.debug("Generating the current campaign airfield defence units...");
        blueforCoalitionManager.setCoalitionPointDefenceGroundUnits(gen.generatePointDefenceUnits(generatedAirfields, FactionSideType.BLUEFOR));
        redforCoalitionManager.setCoalitionPointDefenceGroundUnits(gen.generatePointDefenceUnits(generatedAirfields, FactionSideType.REDFOR));

        // Then, generate all of the ground groups that exist with this campaign (aka front line units)
        log.debug("Generating the current campaign front line units...");
        List<Point2D.Double> generationLine = getWarfareGenerationLine(warfareFront);
        List<Point2D.Double> safeArea = warfareFront.get(FactionSideType.BLUEFOR) == null ? warfareFront.get(FactionSideType.REDFOR) : warfareFront.get(FactionSideType.BLUEFOR);
        List<UnitGroup<GroundUnit>> blueGroundUnits = gen.generateFrontlineGroundUnits(generationLine, safeArea, FactionSideType.BLUEFOR, blueforCoalitionManager.getCoalitionAirfields());
        List<UnitGroup<GroundUnit>> redGroundUnits = gen.generateFrontlineGroundUnits(generationLine, safeArea, FactionSideType.REDFOR, redforCoalitionManager.getCoalitionAirfields());
        blueforCoalitionManager.setCoalitionFrontlineGroups(blueGroundUnits);
        redforCoalitionManager.setCoalitionFrontlineGroups(redGroundUnits);

        // Then, generate all of the AAA/SAM groups that exist within this campaign (mostly airfields, occasionally behind front lines)
        log.debug("Generating the current campaign air defence units...");
        blueforCoalitionManager.setCoalitionAirDefences(gen.generateAirDefences(blueGroundUnits, blueforCoalitionManager.getCoalitionAirfields(), FactionSideType.BLUEFOR));
        redforCoalitionManager.setCoalitionAirDefences(gen.generateAirDefences(redGroundUnits, redforCoalitionManager.getCoalitionAirfields(), FactionSideType.REDFOR));

        // Then, generate all of the AirForce groups that exist within this campaign
        log.debug("Generating the current campaign air units...");
        gen.generateAirGroups(blueforCoalitionManager.getCoalitionAirfields(), FactionSideType.BLUEFOR);
        gen.generateAirGroups(redforCoalitionManager.getCoalitionAirfields(), FactionSideType.REDFOR);
        log.debug("After generation, strengths are: " + gen.getOverallForceStrength());
        log.debug(blueforCoalitionManager.getCoalitionAirGroups());
        log.debug(blueforCoalitionManager.getCoalitionPlayerAirGroups());
        log.debug(redforCoalitionManager.getCoalitionAirGroups());
        log.debug(redforCoalitionManager.getCoalitionPlayerAirGroups());
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

        // Have the campaign managers analyze the situation and plan new flights if needed
        missionGenerator.updateAndGenerate(campaignSettings, blueforCoalitionManager, redforCoalitionManager);
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
            boolean missionShouldGen = m.shouldGenerateMission();
            boolean missionOnSide = m.getMissionAircraft().getSide().equals(campaignSettings.getPlayerSelectedSide());
            boolean generateSettingOn = simSettings.getGenerateMissionsOnMissionWaypoint();
            if(missionShouldGen && missionOnSide && generateSettingOn) {
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
    }

    private List<Point2D.Double> getWarfareGenerationLine(Map<FactionSideType, List<Point2D.Double>> warfareFront) {
        // Get the actual warfare front
        List<Point2D.Double> front = warfareFront.get(FactionSideType.BLUEFOR) == null ? warfareFront.get(FactionSideType.REDFOR) : warfareFront.get(FactionSideType.BLUEFOR);

        // Find the South horizontal line of the front
        List<Point2D.Double> line;
        if(campaignSettings.getSelectedCampaignType().equals(CampaignType.OFFENSIVE) || campaignSettings.getSelectedMap().getMapType().equals(MapType.NORMANDY)) {
            double maxY = front.stream().mapToDouble(Point2D.Double::getY).max().orElse(0.0);
            line = front.stream().filter(d -> d.getY() == maxY).collect(Collectors.toList());
        } else {
            double minY = front.stream().mapToDouble(Point2D.Double::getY).min().orElse(0.0);
            line = front.stream().filter(d -> d.getY() == minY).collect(Collectors.toList());
        }

        return line;
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
