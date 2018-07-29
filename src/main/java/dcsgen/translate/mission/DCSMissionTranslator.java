package dcsgen.translate.mission;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.ThreatGridCell;
import sim.domain.enums.AircraftType;
import sim.domain.enums.MissionStartType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DCSMissionTranslator {
    private static final Logger log = LogManager.getLogger(DCSMissionTranslator.class);

    public DCSMissionData translateSimMissionToDCSMission(Mission mission, CoalitionManager blueforCoalitionManager, CoalitionManager redforColaitionManager, CampaignSettings campaignSettings, MissionStartType missionStartType) {
        // Get the general mission parameters (type, location, aircraft)
        Waypoint missionWp = mission.getMissionWaypoint();
        Airfield startingAirfield = blueforCoalitionManager.getCoalitionAirfields().stream().filter(a -> a.getAirfieldType().equals(mission.getStartingAirfield())).findFirst().orElse(null);
        SubTaskType missionType = mission.getMissionType();
        UnitGroup<AirUnit> missionAircraft = mission.getMissionAircraft();
        assert missionAircraft.getGroupUnits().size() > 0;
        AircraftType aircraftType = missionAircraft.getGroupUnits().get(0).getAircraftType();

        // Abort if we have a mission the player can't accomplish
        boolean noAirfield = startingAirfield == null;
        boolean notPlayerTask = Arrays.asList(SubTaskType.AWACS, SubTaskType.BDA, SubTaskType.BOMBER, SubTaskType.REFUELING, SubTaskType.REFUELING).contains(missionType);
        if(noAirfield || notPlayerTask || !aircraftType.isPlayerFlyable()) {
            log.debug("Unable to generate a player mission of that type");
            return null;
        }

        // Get the cells that we will use to generate ground units
        ThreatGrid grid = blueforCoalitionManager.getMissionManager().getThreatGrid();
        List<ThreatGridCell> searchCells = findCellsAlongRoute(grid, startingAirfield, missionWp);
        log.debug("Searching: " + searchCells.size());

        // Get supporting Aircraft, and friendly ground units
        log.debug("Getting Support Units");
        List<Mission> supportMissions = getSupportingMissions(mission, startingAirfield, blueforCoalitionManager.getMissionManager().getPlannedMissions());
        List<UnitGroup<GroundUnit>> friendlyGroundUnits = findGroundUnitsAlongRoute(searchCells, blueforCoalitionManager.getCoalitionFrontlineGroups());
        List<UnitGroup<GroundUnit>> friendlyAirfieldUnits = findAirfieldGroundUnitsAlongRoute(searchCells, blueforCoalitionManager.getCoalitionPointDefenceGroundUnits());
        List<UnitGroup<AirDefenceUnit>> friendlyAirDefences = findAirDefenceUnitsAlongRoute(searchCells, blueforCoalitionManager.getCoalitionAirDefences());

        // Search for threats, friendlies, target
        log.debug("Getting Enemy Units");
        List<UnitGroup<GroundUnit>> enemyGroundUnits = findGroundUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionFrontlineGroups());
        List<UnitGroup<GroundUnit>> enemyAirfieldUnits = findAirfieldGroundUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionPointDefenceGroundUnits());
        List<UnitGroup<AirDefenceUnit>> enemyAirDefences = findAirDefenceUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionAirDefences());
        List<UnitGroup<AirUnit>> enemyAirIntercept = findInterceptUnitsAlongRoute(searchCells, missionWp, redforColaitionManager.getCoalitionAirfields());

        // Find any currently flying air groups near our path
        List<Mission> enemyAirUnits = findEnemyAirUnitsAlongRoute(searchCells, redforColaitionManager.getMissionManager().getPlannedMissions());

        // Add the friendly details
        DCSMissionData dcsMissionData = new DCSMissionData();
        dcsMissionData.setCampaignSettings(campaignSettings);

        dcsMissionData.addGroundUnits(friendlyGroundUnits);
        dcsMissionData.addGroundUnits(friendlyAirfieldUnits);
        dcsMissionData.addAirDefenceUnits(friendlyAirDefences);
        dcsMissionData.addMissions(supportMissions);
        dcsMissionData.addPlayerMissions(mission);

        // Add the enemy details
        dcsMissionData.addGroundUnits(enemyGroundUnits);
        dcsMissionData.addGroundUnits(enemyAirfieldUnits);
        dcsMissionData.addAirDefenceUnits(enemyAirDefences);
        dcsMissionData.addAirInterceptUnits(enemyAirIntercept);
        dcsMissionData.addMissions(enemyAirUnits);

        return dcsMissionData;
    }

    private List<UnitGroup<AirUnit>> findInterceptUnitsAlongRoute(List<ThreatGridCell> searchCells, Waypoint missionWp, List<Airfield> coalitionAirfields) {
        // Find any airfields within our path
        List<Airfield> closestAirfields = new ArrayList<>();
        for(ThreatGridCell cell : searchCells) {
            for(Airfield field : coalitionAirfields) {
                if(cell.contains(field.getAirfieldType().getAirfieldMapPosition())) {
                    closestAirfields.add(field);
                }
            }
        }

        // Sort, then get the closest 2 airfields to check for intercepts as well
        List<Airfield> searchAirfields = coalitionAirfields.stream().sorted((a1, a2) -> {
            Double dist1 = a1.getAirfieldType().getAirfieldMapPosition().distance(missionWp.getLocationX(), missionWp.getLocationY());
            Double dist2 = a2.getAirfieldType().getAirfieldMapPosition().distance(missionWp.getLocationX(), missionWp.getLocationY());
            return dist1.compareTo(dist2);
        }).collect(Collectors.toList());
        for(int i = 0; i < 2 && i < searchAirfields.size(); i++) {
            Airfield field = searchAirfields.get(i);
            if(!closestAirfields.contains(field)) {
                closestAirfields.add(field);
            }
        }

        // Debug our searched airfields
        log.debug("Searching airfields for intercepts: " + closestAirfields.size());

        // From the Airfield, see if there are any aircraft available for intercepts
        return findInterceptorsFromAirfields(closestAirfields);
    }

    private List<UnitGroup<AirUnit>> findInterceptorsFromAirfields(List<Airfield> closestAirfields) {
        List<UnitGroup<AirUnit>> interceptors = new ArrayList<>();
        for(Airfield field : closestAirfields) {
            for(UnitGroup<AirUnit> unitGroup : field.getStationedAircraft()) {
                if(unitGroup.getNumberOfUnits() > 0) {
                    List<AirUnit> units = unitGroup.getGroupUnits();
                    AirUnit unit = units.get(0);
                    if(!unit.getAircraftType().isHelicopter() && unit.getAircraftType().getPossibleTasks().contains(SubTaskType.INTERCEPT)) {
                        interceptors.add(unitGroup);
                    }
                }
            }
        }
        log.debug("Found " + interceptors.size() + " interception groups along route...");
        return interceptors;
    }

    private List<Mission> getSupportingMissions(Mission mission, Airfield startingAirfield, List<Mission> plannedMissions) {
        Date startTime = mission.getPlannedMissionDate();
        Date startWindow = getDateWindow(startTime, -15);
        Date endWindow = getDateWindow(startTime, 15);
        List<Mission> airfieldMissions = plannedMissions.stream().filter(m -> m.getStartingAirfield().equals(startingAirfield.getAirfieldType())).collect(Collectors.toList());
        airfieldMissions.remove(mission);
        log.debug("Found " + airfieldMissions.size() + " support missions...");
        return airfieldMissions.stream().filter(m -> m.getPlannedMissionDate().after(startWindow) && m.getPlannedMissionDate().before(endWindow)).collect(Collectors.toList());
    }

    private Date getDateWindow(Date startTime, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    private List<Mission> findEnemyAirUnitsAlongRoute(List<ThreatGridCell> searchCells, List<Mission> coalitionAirGroups) {
        List<Mission> activeMissions = coalitionAirGroups.stream().filter(Mission::isActive).collect(Collectors.toList());
        List<Mission> missions = new ArrayList<>();
        for(ThreatGridCell cell : searchCells) {
            for(Mission m : activeMissions) {
                UnitGroup<AirUnit> unit = m.getMissionAircraft();
                if(cell.contains(unit.getMapXLocation(), unit.getMapYLocation())) {
                    missions.add(m);
                }
            }
        }
        log.debug("Found " + missions.size() + " active missions along route...");
        return missions;
    }

    private List<UnitGroup<GroundUnit>> findAirfieldGroundUnitsAlongRoute(List<ThreatGridCell> searchCells, Map<Airfield, List<UnitGroup<GroundUnit>>> coalitionPointDefenceGroundUnits) {
        List<UnitGroup<GroundUnit>> units = new ArrayList<>();
        for(Map.Entry<Airfield, List<UnitGroup<GroundUnit>>> airfieldEntry : coalitionPointDefenceGroundUnits.entrySet()) {
            for(ThreatGridCell cell : searchCells) {
                if(cell.contains(airfieldEntry.getKey().getAirfieldType().getAirfieldMapPosition())) {
                    units.addAll(airfieldEntry.getValue());
                }
            }
        }
        log.debug("Found " + units.size() + " airfield unit groups along route...");
        return units;
    }

    private List<UnitGroup<AirDefenceUnit>> findAirDefenceUnitsAlongRoute(List<ThreatGridCell> searchCells, List<UnitGroup<AirDefenceUnit>> coalitionAirDefences) {
        List<UnitGroup<AirDefenceUnit>> units = new ArrayList<>();
        for(UnitGroup<AirDefenceUnit> unitGroup : coalitionAirDefences) {
            for(ThreatGridCell cell : searchCells) {
                if(cell.contains(unitGroup.getMapXLocation(), unitGroup.getMapYLocation())) {
                    units.add(unitGroup);
                }
            }
        }
        log.debug("Found " + units.size() + " air defence unit groups along route...");
        return units;
    }

    private List<UnitGroup<GroundUnit>> findGroundUnitsAlongRoute(List<ThreatGridCell> searchCells, List<UnitGroup<GroundUnit>> coalitionFrontlineGroups) {
        List<UnitGroup<GroundUnit>> units = new ArrayList<>();
        for(UnitGroup<GroundUnit> unitGroup : coalitionFrontlineGroups) {
            for(ThreatGridCell cell : searchCells) {
                if(cell.contains(unitGroup.getMapXLocation(), unitGroup.getMapYLocation())) {
                    units.add(unitGroup);
                }
            }
        }
        log.debug("Found " + units.size() + " unit groups along route...");
        return units;
    }

    private List<ThreatGridCell> findCellsAlongRoute(ThreatGrid grid, Airfield startingAirfield, Waypoint missionWp) {
        List<ThreatGridCell> routeCells = new ArrayList<>();
        Line2D.Double line = new Line2D.Double(startingAirfield.getAirfieldType().getAirfieldMapPosition().getX(), startingAirfield.getAirfieldType().getAirfieldMapPosition().getY(), missionWp.getLocationX(), missionWp.getLocationY());
        for(int x = 0; x < grid.getNumCellsX(); x++) {
            for(int y = 0; y < grid.getNumCellsY(); y++) {
                ThreatGridCell cell = grid.getThreatGrid()[x][y];
                if(cell.intersects(line)) {
                    routeCells.add(cell);
                }
            }
        }
        return routeCells;
    }
}
