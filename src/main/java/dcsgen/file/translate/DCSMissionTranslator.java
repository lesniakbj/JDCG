package dcsgen.file.translate;

import dcsgen.file.mission.domain.RequiredModules;
import dcsgen.file.mission.domain.mission.AircraftFailures;
import dcsgen.file.mission.domain.mission.BlueTaskText;
import dcsgen.file.mission.domain.mission.CoalitionDetails;
import dcsgen.file.mission.domain.mission.CurrentKeyText;
import dcsgen.file.mission.domain.mission.DCSMission;
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
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;

import java.util.Calendar;
import java.util.Date;

public class DCSMissionTranslator {

    public DCSMission translateSimMissionToDCSMission(Mission mission, CoalitionManager blueforCoalitionManager, CoalitionManager redforColaitionManager) {
        DCSMission translatedMission = new DCSMission();
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
        translatedMission.setMissionCoalitions(getMissionCoalitions(mission, blueforCoalitionManager, redforColaitionManager));
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

    private AircraftFailures getAircraftFailures(Mission mission) {
        return new AircraftFailures();
    }

    private ForcedOptions getForcedOptions(Mission mission) {
        return new ForcedOptions();
    }

    private MissionStartTime getMissionStartTime(Mission mission) {
        return new MissionStartTime();
    }

    private CurrentKeyText getCurrentKeyText(Mission mission) {
        return new CurrentKeyText();
    }

    private TrigrulesSection getTrigrulesSection(Mission mission) {
        return new TrigrulesSection();
    }

    private VersionText getVersionText(Mission mission) {
        return new VersionText();
    }

    private SortieText getSortieText(Mission mission) {
        return new SortieText();
    }

    private CoalitionDetails getCoalitionDetails(Mission mission) {
        return new CoalitionDetails();
    }

    private BlueTaskText getMissionBlueTaskText(Mission mission) {
        return new BlueTaskText();
    }

    private RedTaskText getMissionRedTaskText(Mission mission) {
        return new RedTaskText();
    }

    private PictureFileNameB getPictureFileNameB(Mission mission) {
        return new PictureFileNameB();
    }

    private NeutralTaskText getMissionNeutralTaskText(Mission mission) {
        return new NeutralTaskText();
    }

    private PictureFileNameR getMissionPictureR(Mission mission) {
        return new PictureFileNameR();
    }

    private MissionDescriptionText getMissionDescription(Mission mission) {
        return new MissionDescriptionText();
    }

    private MissionCoalitions getMissionCoalitions(Mission mission, CoalitionManager blueforCoalitionManager, CoalitionManager redforColaitionManager) {
        return new MissionCoalitions();
    }

    private MapLocation getMapLocation(Mission mission) {
        return new MapLocation();
    }

    private TriggerLocations getTriggerLocations(Mission mission) {
        return new TriggerLocations();
    }

    private MissionTheatre getMissionTheatre(Mission mission) {
        return new MissionTheatre();
    }

    private MissionWeather getMissionWeather(Mission mission) {
        return new MissionWeather();
    }

    private PictureFileNameN getPictureFileNameN(Mission mission) {
        return new PictureFileNameN();
    }

    private MaxGeneratedId getMissionMaxGeneratedId(Mission mission) {
        return new MaxGeneratedId();
    }

    private MissionGoals getMissionGoals(Mission mission) {
        return new MissionGoals();
    }

    private GroundControl getMissionGroundControl(Mission mission) {
        return new GroundControl();
    }

    private MissionResults getMissionResults(Mission mission) {
        return new MissionResults();
    }

    private RequiredModules getRequiredModules(Mission mission) {
        return new RequiredModules();
    }

    private MissionDate getMissionDate(Mission mission) {
        // Get the numeric day, year, month
        Calendar cal = Calendar.getInstance();
        // cal.setTime(mission.getCurrentCampaignDate());
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return new MissionDate(day, year, month);
    }

    private MissionTriggers getMissionTriggers(Mission mission) {
        return new MissionTriggers();
    }

}
