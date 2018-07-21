package sim.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;

import java.util.List;
import java.util.Map;

public class CoalitionManager {
    private static final Logger log = LogManager.getLogger(CoalitionManager.class);

    // Campaign Data
    private List<Airfield> coalitionAirfields;
    private List<UnitGroup<GroundUnit>> coalitionFrontlineGroups;
    private Map<Airfield,List<UnitGroup<GroundUnit>>> coalitionPointDefenceGroundUnits;
    private List<UnitGroup<AirDefenceUnit>> coalitionAirDefences;
    private List<UnitGroup<AirUnit>> coalitionAirGroups;
    private List<UnitGroup<AirUnit>> coalitionPlayerAirGroups;

    // Campaign Managers
    private ObjectiveManager coalitionObjectiveManager;
    private MissionManager coalitionMissionManager;

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

    public void setCoalitionAirGroups(List<UnitGroup<AirUnit>> coalitionAirGroups) {
        this.coalitionAirGroups = coalitionAirGroups;
    }

    public List<UnitGroup<AirUnit>> getCoalitionAirGroups() {
        return coalitionAirGroups;
    }

    public void setCoalitionPlayerAirGroups(List<UnitGroup<AirUnit>> coalitionPlayerAirGroups) {
        this.coalitionPlayerAirGroups = coalitionPlayerAirGroups;
    }

    public List<UnitGroup<AirUnit>> getCoalitionPlayerAirGroups() {
        return coalitionPlayerAirGroups;
    }
}
