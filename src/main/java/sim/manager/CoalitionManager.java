package sim.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;

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

    public List<UnitGroup<AirUnit>> getCoalitionAirGroups() {
        return coalitionAirfields.stream().map(Airfield::getStationedAircraft).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<UnitGroup<AirUnit>> getCoalitionPlayerAirGroups() {
        return coalitionAirfields.stream().map(Airfield::getStationedAircraft).flatMap(Collection::stream).filter(UnitGroup::isPlayerGeneratedGroup).collect(Collectors.toList());
    }
}