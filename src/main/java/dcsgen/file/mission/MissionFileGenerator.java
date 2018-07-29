package dcsgen.file.mission;

import dcsgen.file.DCSFileGenerator;
import dcsgen.file.mission.domain.AircraftFailures;
import dcsgen.file.mission.domain.BlueTaskText;
import dcsgen.file.mission.domain.CoalitionDetails;
import dcsgen.file.mission.domain.CurrentKeyText;
import dcsgen.file.mission.domain.DCSMissionFile;
import dcsgen.file.mission.domain.FilePart;
import dcsgen.file.mission.domain.ForcedOptions;
import dcsgen.file.mission.domain.GroundControl;
import dcsgen.file.mission.domain.MapLocation;
import dcsgen.file.mission.domain.MaxGeneratedId;
import dcsgen.file.mission.domain.MissionCoalitions;
import dcsgen.file.mission.domain.MissionDate;
import dcsgen.file.mission.domain.MissionDescriptionText;
import dcsgen.file.mission.domain.MissionGoals;
import dcsgen.file.mission.domain.MissionResults;
import dcsgen.file.mission.domain.MissionStartTime;
import dcsgen.file.mission.domain.MissionTheatre;
import dcsgen.file.mission.domain.MissionWeather;
import dcsgen.file.mission.domain.NeutralTaskText;
import dcsgen.file.mission.domain.PictureFileNameB;
import dcsgen.file.mission.domain.PictureFileNameN;
import dcsgen.file.mission.domain.PictureFileNameR;
import dcsgen.file.mission.domain.RedTaskText;
import dcsgen.file.mission.domain.RequiredModules;
import dcsgen.file.mission.domain.SortieText;
import dcsgen.file.mission.domain.TrigrulesSection;
import dcsgen.file.mission.domain.VersionText;
import dcsgen.file.mission.domain.trigger.MissionTriggers;
import dcsgen.file.mission.domain.trigger.TriggerLocations;
import dcsgen.translate.DCSMission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static dcsgen.file.mission.domain.FilePart.CLOSE_BRACE;
import static dcsgen.file.mission.domain.FilePart.OPEN_BRACE;

public class MissionFileGenerator implements DCSFileGenerator {
    private static final Logger log = LogManager.getLogger(MissionFileGenerator.class);

    private static final String MISSION_HEADER = "mission = ";

    @Override
    public List<String> generateFileString(List<FilePart> fileParts) {
        List<String> fileLines = new LinkedList<>();
        fileLines.add(MISSION_HEADER);
        fileLines.add(OPEN_BRACE);

        // Add all of the parts
        for(FilePart p : fileParts) {
            if(p != null && p.getFileParts() != null) {
                fileLines.addAll(p.getFileParts());
            }
        }

        // Add the closing brace
        fileLines.add(CLOSE_BRACE);
        return fileLines;
    }

    public DCSMissionFile getMissionFileFromMission(DCSMission mission) {
        if(mission == null) {
            return  new DCSMissionFile();
        }

        log.debug("Mission to generate with the following params: ");
        log.debug("Total Ground Units: " + mission.getGroundUnits().size());
        log.debug("Total Air Defence Units: " + mission.getAirDefenceUnits().size());
        log.debug("Total Interceptors Units: " + mission.getLatentInterceptors().size());
        log.debug("Total Missions: " + mission.getMissions().size());
        log.debug("Mission Type: " + mission.getPlayerMission().getMissionType());
        log.debug("Mission Airfield: " + mission.getPlayerMission().getStartingAirfield());
        log.debug("Mission Aircraft: " + mission.getPlayerMission().getMissionAircraft());

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
