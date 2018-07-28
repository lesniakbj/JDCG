package dcsgen.file.translate;

import dcsgen.file.mission.domain.RequiredModules;
import dcsgen.file.mission.domain.mission.AircraftFailures;
import dcsgen.file.mission.domain.mission.BlueTaskText;
import dcsgen.file.mission.domain.mission.CoalitionDetails;
import dcsgen.file.mission.domain.mission.CurrentKeyText;
import dcsgen.file.mission.domain.mission.DCSMission;
import dcsgen.file.mission.domain.mission.DCSMissionFile;
import dcsgen.file.mission.domain.mission.ForcedOptions;
import dcsgen.file.mission.domain.mission.GroundControl;
import dcsgen.file.mission.domain.mission.MapLocation;
import dcsgen.file.mission.domain.mission.MaxGeneratedId;
import dcsgen.file.mission.domain.mission.MissionCoalitions;
import dcsgen.file.mission.domain.mission.MissionDate;
import dcsgen.file.mission.domain.mission.MissionDescriptionText;
import dcsgen.file.mission.domain.mission.MissionGoals;
import dcsgen.file.mission.domain.mission.MissionResults;
import dcsgen.file.mission.domain.mission.MissionStartTime;
import dcsgen.file.mission.domain.mission.MissionTheatre;
import dcsgen.file.mission.domain.mission.MissionWeather;
import dcsgen.file.mission.domain.mission.NeutralTaskText;
import dcsgen.file.mission.domain.mission.PictureFileNameB;
import dcsgen.file.mission.domain.mission.PictureFileNameN;
import dcsgen.file.mission.domain.mission.PictureFileNameR;
import dcsgen.file.mission.domain.mission.RedTaskText;
import dcsgen.file.mission.domain.mission.SortieText;
import dcsgen.file.mission.domain.mission.TrigrulesSection;
import dcsgen.file.mission.domain.mission.VersionText;
import dcsgen.file.mission.domain.mission.trigger.MissionTriggers;
import dcsgen.file.mission.domain.mission.trigger.TriggerLocations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.threat.ThreatGridCell;
import sim.domain.enums.MissionStartType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.manager.CoalitionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DCSMissionTranslator {
    private static final Logger log = LogManager.getLogger(DCSMissionTranslator.class);

    public DCSMission translateSimMissionToDCSMission(Mission mission, CoalitionManager blueforCoalitionManager, CoalitionManager redforColaitionManager, MissionStartType missionStartType) {
        // Get the general mission parameters (type, location, aircraft type)
        Waypoint start = mission.getNextWaypoint();
        Waypoint missionWp = mission.getMissionWaypoint();
        Airfield startingAirfield = blueforCoalitionManager.getCoalitionAirfields().stream().filter(a -> a.getAirfieldType().equals(mission.getStartingAirfield())).findFirst().orElse(null);
        SubTaskType missionType = mission.getMissionType();
        UnitGroup<AirUnit> missionAircraft = mission.getMissionAircraft();

        // Search for threats, friendlies, target
        ThreatGridCell currentCell = blueforCoalitionManager.getMissionManager().getThreatGrid().getCellFromLocation(missionAircraft.getMapXLocation(), missionAircraft.getMapYLocation());
        List<ThreatGridCell> searchCells = findCellsAlongRoute(currentCell, missionWp);
        List<UnitGroup<GroundUnit>> enemyGroundUnits = findEnemyGroundUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionFrontlineGroups());
        List<UnitGroup<GroundUnit>> enemyAirfieldUnits = findEnemyGroundUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionFrontlineGroups());
        List<UnitGroup<GroundUnit>> enemyAirDefence = findEnemyGroundUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionFrontlineGroups());
        List<UnitGroup<GroundUnit>> enemyAirUnits = findEnemyGroundUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionFrontlineGroups());
        List<UnitGroup<GroundUnit>> enemyAirIntercept = findEnemyGroundUnitsAlongRoute(searchCells, redforColaitionManager.getCoalitionFrontlineGroups());

        log.debug("Creating mission around the following: " + start);
        log.debug("Creating mission around the following: " + missionWp);
        log.debug("Creating mission around the following: " + startingAirfield);
        log.debug("Creating mission around the following: " + missionType);
        log.debug("Creating mission around the following: " + missionAircraft);
        log.debug("Creating mission around the following: " + currentCell);

        return new DCSMission();
    }

    public DCSMissionFile getMissionFileFromMission(DCSMission mission) {
        DCSMissionFile translatedMission = new DCSMissionFile();
        translatedMission.setRequiredModules(getRequiredModules(mission));
        translatedMission.setMissionDate(getMissionDate(mission));
        translatedMission.setMissionTriggers(getMissionTriggers(mission));
        translatedMission.setMissionResults(getMissionResults(mission));
        translatedMission.setMaxGeneratedId(getMissionMaxGeneratedId(mission));
        translatedMission.setPictureFileNameN(getPictureFileNameN(mission));
        translatedMission.setGroundControl(getMissionGroundControl(mission));
        translatedMission.setMissionGoals(getMissionGoals(mission));
        translatedMission.setMissionWeather(getMissionWeather(mission));
        translatedMission.setMissionTheatre(getMissionTheatre(mission));
        translatedMission.setTriggerLocations(getTriggerLocations(mission));
        translatedMission.setMapLocation(getMapLocation(mission));
        translatedMission.setMissionCoalitions(getMissionCoalitions(mission));
        translatedMission.setMissionDescriptionText(getMissionDescription(mission));
        translatedMission.setPictureFileNameR(getMissionPictureR(mission));
        translatedMission.setNeutralTaskText(getMissionNeutralTaskText(mission));
        translatedMission.setBlueTaskText(getMissionBlueTaskText(mission));
        translatedMission.setRedTaskText(getMissionRedTaskText(mission));
        translatedMission.setPictureFileNameB(getPictureFileNameB(mission));
        translatedMission.setCoalitionDetails(getCoalitionDetails(mission));
        translatedMission.setSortieText(getSortieText(mission));
        translatedMission.setVersionText(getVersionText(mission));
        translatedMission.setTrigrulesSection(getTrigrulesSection(mission));
        translatedMission.setCurrentKeyText(getCurrentKeyText(mission));
        translatedMission.setMissionStartTime(getMissionStartTime(mission));
        translatedMission.setForcedOptions(getForcedOptions(mission));
        translatedMission.setAircraftFailures(getAircraftFailures(mission));
        return translatedMission;
    }

    private List<UnitGroup<GroundUnit>> findEnemyGroundUnitsAlongRoute(List<ThreatGridCell> searchCells, List<UnitGroup<GroundUnit>> coalitionFrontlineGroups) {
        return new ArrayList<>();
    }

    private List<ThreatGridCell> findCellsAlongRoute(ThreatGridCell currentCell, Waypoint missionWp) {
        return new ArrayList<>();
    }

    private AircraftFailures getAircraftFailures(DCSMission mission) {
        return new AircraftFailures();
    }

    private ForcedOptions getForcedOptions(DCSMission mission) {
        return new ForcedOptions();
    }

    private MissionStartTime getMissionStartTime(DCSMission mission) {
        return new MissionStartTime();
    }

    private CurrentKeyText getCurrentKeyText(DCSMission mission) {
        return new CurrentKeyText();
    }

    private TrigrulesSection getTrigrulesSection(DCSMission mission) {
        return new TrigrulesSection();
    }

    private VersionText getVersionText(DCSMission mission) {
        return new VersionText();
    }

    private SortieText getSortieText(DCSMission mission) {
        return new SortieText();
    }

    private CoalitionDetails getCoalitionDetails(DCSMission mission) {
        return new CoalitionDetails();
    }

    private BlueTaskText getMissionBlueTaskText(DCSMission mission) {
        return new BlueTaskText();
    }

    private RedTaskText getMissionRedTaskText(DCSMission mission) {
        return new RedTaskText();
    }

    private PictureFileNameB getPictureFileNameB(DCSMission mission) {
        return new PictureFileNameB();
    }

    private NeutralTaskText getMissionNeutralTaskText(DCSMission mission) {
        return new NeutralTaskText();
    }

    private PictureFileNameR getMissionPictureR(DCSMission mission) {
        return new PictureFileNameR();
    }

    private MissionDescriptionText getMissionDescription(DCSMission mission) {
        return new MissionDescriptionText();
    }

    private MissionCoalitions getMissionCoalitions(DCSMission mission) {
        return new MissionCoalitions();
    }

    private MapLocation getMapLocation(DCSMission mission) {
        return new MapLocation();
    }

    private TriggerLocations getTriggerLocations(DCSMission mission) {
        return new TriggerLocations();
    }

    private MissionTheatre getMissionTheatre(DCSMission mission) {
        return new MissionTheatre();
    }

    private MissionWeather getMissionWeather(DCSMission mission) {
        return new MissionWeather();
    }

    private PictureFileNameN getPictureFileNameN(DCSMission mission) {
        return new PictureFileNameN();
    }

    private MaxGeneratedId getMissionMaxGeneratedId(DCSMission mission) {
        return new MaxGeneratedId();
    }

    private MissionGoals getMissionGoals(DCSMission mission) {
        return new MissionGoals();
    }

    private GroundControl getMissionGroundControl(DCSMission mission) {
        return new GroundControl();
    }

    private MissionResults getMissionResults(DCSMission mission) {
        return new MissionResults();
    }

    private RequiredModules getRequiredModules(DCSMission mission) {
        return new RequiredModules();
    }

    private MissionDate getMissionDate(DCSMission mission) {
        // Get the numeric day, year, month
        Calendar cal = Calendar.getInstance();
        // cal.setTime(mission.getCurrentCampaignDate());
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return new MissionDate(day, year, month);
    }

    private MissionTriggers getMissionTriggers(DCSMission mission) {
        return new MissionTriggers();
    }

}
