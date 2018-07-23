package sim.manager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.CommanderAction;
import sim.ai.threat.gen.ThreatGridGenerator;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.gen.air.ATOGenerator;

public class CoalitionManager {
    private static final Logger log = LogManager.getLogger(CoalitionManager.class);

    // Campaign Data
    private List<Airfield> coalitionAirfields;
    private List<UnitGroup<GroundUnit>> coalitionFrontlineGroups;
    private Map<Airfield,List<UnitGroup<GroundUnit>>> coalitionPointDefenceGroundUnits;
    private List<UnitGroup<AirDefenceUnit>> coalitionAirDefences;
    private List<AIAction> lastActionsTaken;

    // Campaign Managers
    private ObjectiveManager coalitionObjectiveManager;
    private MissionManager coalitionMissionManager;
    private ATOGenerator commander;


    public CoalitionManager(List<UnitGroup<GroundUnit>> coalitionGroups, ObjectiveManager coalitionObjectiveManager, MissionManager coalitionMissionManager) {
        this.coalitionFrontlineGroups = coalitionGroups;
        this.coalitionObjectiveManager = coalitionObjectiveManager;
        this.coalitionMissionManager = coalitionMissionManager;
        this.commander = new ATOGenerator();
    }

    public List<Airfield> getCoalitionAirfields() {
        return coalitionAirfields;
    }

    public void setCoalitionAirfields(List<Airfield> coalitionAirfields) {
        this.coalitionAirfields = coalitionAirfields;
    }

    public MissionManager getMissionManager() {
        return coalitionMissionManager;
    }

    public List<UnitGroup<GroundUnit>> getCoalitionFrontlineGroups() {
        return coalitionFrontlineGroups;
    }

    public void setCoalitionFrontlineGroups(List<UnitGroup<GroundUnit>> coalitionGroups) {
        this.coalitionFrontlineGroups = coalitionGroups;
    }

    public ObjectiveManager getCoalitionObjectiveManager() {
        return coalitionObjectiveManager;
    }

    public void setCoalitionObjectiveManager(ObjectiveManager coalitionObjectiveManager) {
        this.coalitionObjectiveManager = coalitionObjectiveManager;
    }

    public MissionManager getCoalitionMissionManager() {
        return coalitionMissionManager;
    }

    public void setCoalitionMissionManager(MissionManager coalitionMissionManager) {
        this.coalitionMissionManager = coalitionMissionManager;
    }

    public void setCoalitionPointDefenceGroundUnits(Map<Airfield,List<UnitGroup<GroundUnit>>> coalitionPointDefenceGroundUnits) {
        this.coalitionPointDefenceGroundUnits = coalitionPointDefenceGroundUnits;
    }

    public Map<Airfield, List<UnitGroup<GroundUnit>>> getCoalitionPointDefenceGroundUnits() {
        return coalitionPointDefenceGroundUnits;
    }

    public void setCoalitionAirDefences(List<UnitGroup<AirDefenceUnit>> coalitionAirDefences) {
        this.coalitionAirDefences = coalitionAirDefences;
    }

    public List<UnitGroup<AirDefenceUnit>> getCoalitionAirDefences() {
        return coalitionAirDefences;
    }

    public List<UnitGroup<AirUnit>> getCoalitionAirGroups() {
        return coalitionAirfields.stream().map(Airfield::getStationedAircraft).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<UnitGroup<AirUnit>> getCoalitionPlayerAirGroups() {
        return coalitionAirfields.stream().map(Airfield::getStationedAircraft).flatMap(Collection::stream).filter(UnitGroup::isPlayerGeneratedGroup).collect(Collectors.toList());
    }

    public void update(CoalitionManager enemyCoalitionManager, int minutesToStep) {
        // Update the Threat Grid
        log.debug("Updating threat grid...");
        ThreatGridGenerator threatGridGenerator = new ThreatGridGenerator();
        log.debug(coalitionMissionManager.getThreatGrid());
        threatGridGenerator.populateThreatGridValues(coalitionMissionManager.getThreatGrid(), this, enemyCoalitionManager);
        log.debug(coalitionMissionManager.getThreatGrid());

        // Let the AI tell us our next action
        log.debug("Running AI / Decision Process...");
        List<AIAction> actions = commander.generateATO(this, enemyCoalitionManager, lastActionsTaken);
        // The AI will generate a CommanderAction, which is a list of
        // groups and what the AI said for each group to do. From there,
        // we need to execute what the.

        // Respond to that action
        log.debug("Running process that was decided upon...");
        // Might be something like this...
        // missionGenerator.updateAndGenerate(campaignSettings, simSettings, this, enemyCoalitionManager);

        lastActionsTaken = actions;
    }
}
