package sim.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.threat.gen.ThreatGridGenerator;
import sim.domain.enums.AirfieldType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.gen.air.ATOGenerator;
import sim.gen.air.PackageGenerator;
import sim.gen.mission.AirUnitMissionGenerator;
import sim.settings.CampaignSettings;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private PackageGenerator packageGenerator;
    private AirfieldType coalitionHomeAirfield;


    public CoalitionManager(List<UnitGroup<GroundUnit>> coalitionGroups, ObjectiveManager coalitionObjectiveManager, MissionManager coalitionMissionManager) {
        this.coalitionFrontlineGroups = coalitionGroups;
        this.coalitionObjectiveManager = coalitionObjectiveManager;
        this.coalitionMissionManager = coalitionMissionManager;
        this.commander = new ATOGenerator();
        this.packageGenerator = new PackageGenerator();
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

    public Map<Airfield, List<UnitGroup<AirUnit>>> getCoalitionAirGroupsMap() {
        return coalitionAirfields.stream().collect(Collectors.toMap(a -> a, Airfield::getStationedAircraft));
    }

    public void setCoalitionHomeAirfield(AirfieldType coalitionHomeAirfield) {
        this.coalitionHomeAirfield = coalitionHomeAirfield;
    }

    public void updateCoalitionAirGroups(Airfield field, List<UnitGroup<AirUnit>> unitGroups) {
        Airfield a = coalitionAirfields.stream().filter(af -> af.getAirfieldType().equals(field.getAirfieldType())).findFirst().orElse(null);
        if(a == null) {
            return;
        }
        coalitionAirfields.remove(a);
        a.setStationedAircraft(unitGroups);
        coalitionAirfields.add(a);
    }

    public List<UnitGroup<AirUnit>> getCoalitionPlayerAirGroups() {
        return coalitionAirfields.stream().map(Airfield::getStationedAircraft).flatMap(Collection::stream).filter(UnitGroup::isPlayerGeneratedGroup).collect(Collectors.toList());
    }

    public AirfieldType getCoalitionHomeAirfield() {
        return coalitionHomeAirfield;
    }

    public void update(CampaignSettings campaignSettings, CoalitionManager enemyCoalitionManager, int minutesToStep) {
        // Update the Threat Grid
        log.debug("Updating threat grid...");
        ThreatGridGenerator threatGridGenerator = new ThreatGridGenerator();
        threatGridGenerator.populateThreatGridValues(coalitionMissionManager.getThreatGrid(), this, enemyCoalitionManager);

        // Using the mission state, and last update time, go through a set state transitions to
        // determine our next action. This will fully determine the missions.
        List<List<Mission>> packages = commander.generateAllMissions(this, enemyCoalitionManager, campaignSettings);

        /*
        // Let the AI tell us our next action
        // The AI will generate a CommanderAction, which is a list of
        // groups and what the AI said for each group to do. From there,
        // we need to execute what was created.
        log.debug("Running AI / Decision Process...");
        List<AIAction> actions = commander.generateATO(this, enemyCoalitionManager, lastActionsTaken, campaignSettings.getCurrentCampaignDate());
        log.debug("Actions to run: " + actions.size());

        // Respond to that list of actions
        log.debug("Running process that was decided upon...");
        List<List<Mission>> missionPackages = packageGenerator.generateMissionPackages(campaignSettings, actions, this, enemyCoalitionManager, campaignSettings.getCurrentCampaignDate());
        */

        // Test to plan AirUnit removal from airbases when on missions
        if(coalitionMissionManager.getPlannedMissions().size() < 10) {
            AirUnitMissionGenerator.generateTestMissionForCoalition(campaignSettings, this,campaignSettings.getCurrentCampaignDate());
        }

        // lastActionsTaken = actions;
    }
}
