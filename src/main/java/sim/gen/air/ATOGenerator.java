package sim.gen.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.AIActionType;
import sim.ai.actions.CommanderAction;
import sim.ai.actions.GenerationResult;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.ThreatGridCell;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.Aircraft;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static sim.domain.enums.StaticLists.DEFAULT_LOADOUTS;

public class ATOGenerator {
    private static final Logger log = LogManager.getLogger(ATOGenerator.class);

    private static final int MEMORY_DEPTH = 50;
    private static final int[][] NEIGHBOURS = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

    private List<GenerationResult> generationMemory;

    public  ATOGenerator() {
        generationMemory = new LinkedList<>();
    }

    public void generateTestMissionForCoalition(CampaignSettings campaign, CoalitionManager coalitionManager, Date date) {
        List<Waypoint> generatedWaypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition(), AirfieldType.KHASAB.getAirfieldMapPosition(), SubTaskType.INTERCEPT, MapType.PERSIAN_GULF);

        UnitGroup.Builder<Aircraft> aircraftBuilder = new UnitGroup.Builder<>();
        aircraftBuilder.setUnits(Arrays.asList(new Aircraft(AircraftType.FA_18C_LOT20), new Aircraft(AircraftType.FA_18C_LOT20)))
            .setSide(FactionSideType.BLUEFOR)
            .setMapLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition());

        Mission.Builder builder = new Mission.Builder();
        builder.setMissionMap(campaign.getSelectedMap().getMapType())
            .setMissionType(SubTaskType.CAS)
            .setMissionAircraft(aircraftBuilder.build())
            .setMissionWaypoints(generatedWaypoints)
            .setUpcomingMissionDate(date, 60)
            .setIsClientMission(false)
            .setPlayerAircraftNumber(0)
            .setMissionComplete(false)
            .setUpdateRate(1)
            .setShouldGenerateMission(false)
            .setStartingAirfield(AirfieldType.AL_DHAFRA_AIRBASE)
            .setTimeOnStation(30)
            .setMissionMunitions(DEFAULT_LOADOUTS.get(AircraftType.FA_18C_LOT20).get(SubTaskType.INTERCEPT));

        coalitionManager.getCoalitionMissionManager().addMission(builder.build());
    }

    public CommanderAction generateATO(CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager, CommanderAction lastActionsTaken) {
        // Next action...
        CommanderAction nextActions = new CommanderAction();
        ThreatGrid currentThreatGrid = friendlyCoalitionManager.getMissionManager().getThreatGrid();

        // Add the actions we've taken and the grid results to our memory bank
        if(lastActionsTaken != null && generationMemory.size() <= 50) {
            generationMemory.add(new GenerationResult(lastActionsTaken, currentThreatGrid));
            log.debug(generationMemory);
        }

        // If we've done nothing yet, let's try a random action...
        if(generationMemory.size() == 0) {
            Map<UnitGroup, AIAction> actionMap = new HashMap<>();
            nextActions.setActionMap(actionMap);
        }

        // When considering actions, ignore all cells that are 0.0 and are not touching a cell that has a score
        setThreatCellsToIgnore(currentThreatGrid);

        // The goal of the ATO AI is to create the maximum score on the Threat Grid (a negative score on the grid
        // indicates a higher threat to this AI commander). So, first we need to sum up the score on the grid,
        // and see how that score is.

        return nextActions;
    }

    private void setThreatCellsToIgnore(ThreatGrid currentThreatGrid) {
        for(int x = 0; x < currentThreatGrid.getNumCellsX(); x++) {
            for (int y = 0; y < currentThreatGrid.getNumCellsY(); y++) {
                ThreatGridCell searchCell = currentThreatGrid.getThreatGrid()[x][y];
                if(cellHasNoNeighbors(searchCell, currentThreatGrid) && searchCell.getThreatLevel() == 0.0) {
                    searchCell.setIgnoreDuringThreatCalculations(true);
                }
            }
        }
    }

    private boolean cellHasNoNeighbors(ThreatGridCell searchCell, ThreatGrid currentThreatGrid) {
        double totalThreatToCell = 0.0;
        int x = searchCell.getX();
        int y = searchCell.getY();
        for(int[] offset : NEIGHBOURS) {
            int offX = x + offset[0];
            int offY = y + offset[1];
            if (offX > 0 && offY > 0 && offX < currentThreatGrid.getNumCellsX() && offY < currentThreatGrid.getNumCellsY()) {
                ThreatGridCell cell = currentThreatGrid.getThreatGrid()[offX][offY];
                if(cell.getThreatLevel() != 0.0) {
                    totalThreatToCell += cell.getThreatLevel();
                }
            }
        }
        return totalThreatToCell == 0.0;
    }

    /*
    public void updateAndGenerate(CampaignSettings campaignSettings, GlobalSimSettings simSettings, CoalitionManager blueforCoalitionManager, CoalitionManager redforCoalitionManager) {
        generateATOForCoalition(campaignSettings, simSettings, blueforCoalitionManager, redforCoalitionManager, FactionSideType.BLUEFOR);
        generateATOForCoalition(campaignSettings, simSettings, redforCoalitionManager, blueforCoalitionManager, FactionSideType.REDFOR);
    }

    private void generateATOForCoalition(CampaignSettings campaignSettings, GlobalSimSettings simSettings, CoalitionManager friendlyManager, CoalitionManager enemyManager, FactionSideType side) {
        log.debug("Creating ATO for: " + side);
        // ATO Generation takes the following parameters:
        //  1) Critical Targets that need to be Attacked
        //  2) Critical Threats to Defensive Targets
        //  3) The available air groups and their airfields
        //  4) Player playable groups (will prefer to try to generate user playable missions)
        //  5) Whether we have support assets available
        //  6) The currently running missions

        // Critical Targets
        List<GroundUnit> criticalAttackTargets = enemyManager.getCoalitionAirfields().stream().map(Airfield::getCriticalStructures).flatMap(Collection::stream).collect(Collectors.toList());
        // criticalAttackTargets.addAll(getCriticalGroundUnits());

        // Critical Threats
        List<Airfield> coalitionAirfields = friendlyManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> coalitionAirDefences = friendlyManager.getCoalitionAirDefences();
        List<Structure> criticalDefenceTargets = coalitionAirfields.stream().map(Airfield::getCriticalStructures).flatMap(Collection::stream).collect(Collectors.toList());
        List<UnitGroup<AirUnit>> criticalAirThreats = findCriticalAirThreats(coalitionAirfields, coalitionAirDefences, criticalDefenceTargets, enemyManager);

        // Available Air Groups
        Map<Airfield, List<UnitGroup<AirUnit>>> availableGroups = coalitionAirfields.stream().collect(Collectors.toMap(a -> a, Airfield::getStationedAircraft));
        List<UnitGroup<AirUnit>> playableGroups = coalitionAirfields.stream().map(Airfield::getStationedAircraft).flatMap(Collection::stream).filter(UnitGroup::isPlayerGeneratedGroup).collect(Collectors.toList());

        // Currently running missions
        List<Mission> runningMissions = friendlyManager.getCoalitionMissionManager().getPlannedMissions();

        // Generate the ATO for the friendly manager
        generateATO(friendlyManager, criticalAttackTargets, criticalAirThreats, availableGroups, playableGroups, runningMissions);
    }

    private void generateATO(CoalitionManager friendlyManager, List<GroundUnit> criticalAttackTargets, List<UnitGroup<AirUnit>> criticalAirThreats, Map<Airfield, List<UnitGroup<AirUnit>>> availableGroups, List<UnitGroup<AirUnit>> playableGroups, List<Mission> runningMissions) {
        log.debug("Attack Targets: " + criticalAttackTargets);
        log.debug("Critical Air Threats: " + criticalAirThreats);
        log.debug("Available Groups: " + availableGroups);
        log.debug("Player Available Groups: " + playableGroups);
        log.debug("Running Missions: " + runningMissions);
    }

    private List<UnitGroup<AirUnit>> findCriticalAirThreats(List<Airfield> coalitionAirfields, List<UnitGroup<AirDefenceUnit>> coalitionAirDefences, List<Structure> criticalDefenceTargets, CoalitionManager enemyManager) {
        log.debug("Looking for enemy aircraft that are a threat to: " + coalitionAirfields);
        log.debug("Looking for enemy aircraft that are a threat to: " + criticalDefenceTargets);
        log.debug("Threat of critical target is reduced by: " + coalitionAirDefences);
        log.debug("Looking at the aircraft from enemy: " + enemyManager.getCoalitionAirGroups());
        return new ArrayList<>();
    }
    */
}
