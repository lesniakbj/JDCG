package sim.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;

import java.util.List;
import java.util.Map;

public class CoalitionManager {
    private static final Logger log = LogManager.getLogger(CoalitionManager.class);

    private List<Airfield> coalitionAirfields;
    private List<UnitGroup<GroundUnit>> coalitionFrontlineGroups;
    private ObjectiveManager coalitionObjectiveManager;
    private MissionManager coalitionMissionManager;

    // Convienently mapped unit groups
    Map<Airfield,List<UnitGroup<GroundUnit>>> coalitionPointDefenceGroundUnits;

    public CoalitionManager(List<UnitGroup<GroundUnit>> coalitionGroups, ObjectiveManager coalitionObjectiveManager, MissionManager coalitionMissionManager) {
        this.coalitionFrontlineGroups = coalitionGroups;
        this.coalitionObjectiveManager = coalitionObjectiveManager;
        this.coalitionMissionManager = coalitionMissionManager;
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
        log.debug("Setting point defences");
        this.coalitionPointDefenceGroundUnits = coalitionPointDefenceGroundUnits;
    }

    public Map<Airfield, List<UnitGroup<GroundUnit>>> getCoalitionPointDefenceGroundUnits() {
        log.debug("Getting point defences");
        return coalitionPointDefenceGroundUnits;
    }
}
