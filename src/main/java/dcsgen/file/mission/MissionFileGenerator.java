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
import dcsgen.translate.group.DCSUnitGroup;
import dcsgen.translate.mission.DCSMissionData;
import dcsgen.translate.unit.DCSUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public DCSMissionFile getMissionFileFromMission(DCSMissionData mission) {
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

        // Translate the DCSMissionDate object to DCSFileDomain objects, this should be done in a single pass....

        List<FactionType> blueFactions = mission.getCampaignSettings().getCoalitionBySide(FactionSideType.BLUEFOR).getFactionTypeList();
        List<FactionType> redFactions = mission.getCampaignSettings().getCoalitionBySide(FactionSideType.REDFOR).getFactionTypeList();
        List<FactionType> neutralFactions = mission.getCampaignSettings().getCoalitionBySide(FactionSideType.NEUTRAL).getFactionTypeList();
        blueFactions.sort(Comparator.comparing(FactionType::getDcsFactionName));
        redFactions.sort(Comparator.comparing(FactionType::getDcsFactionName));
        neutralFactions.sort(Comparator.comparing(FactionType::getDcsFactionName));
        List<DCSUnitGroup> groups = parseUnitGroups(mission);

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
        translatedMission.setMissionCoalitions(getMissionCoalitions(blueFactions, redFactions, neutralFactions));
        translatedMission.setMissionDescriptionText(getMissionDescription(mission));
        translatedMission.setPictureFileNameR(getMissionPictureR(mission));
        translatedMission.setNeutralTaskText(getMissionNeutralTaskText(mission));
        translatedMission.setBlueTaskText(getMissionBlueTaskText(mission));
        translatedMission.setRedTaskText(getMissionRedTaskText(mission));
        translatedMission.setPictureFileNameB(getPictureFileNameB(mission));
        translatedMission.setCoalitionDetails(getCoalitionDetails(blueFactions, redFactions));
        translatedMission.setSortieText(getSortieText(mission));
        translatedMission.setVersionText(getVersionText(mission));
        translatedMission.setTrigrulesSection(getTrigrulesSection(mission));
        translatedMission.setCurrentKeyText(getCurrentKeyText(mission));
        translatedMission.setMissionStartTime(getMissionStartTime(mission));
        translatedMission.setForcedOptions(getForcedOptions(mission));
        translatedMission.setAircraftFailures(getAircraftFailures(mission));
        return translatedMission;
    }

    private List<DCSUnitGroup> parseUnitGroups(DCSMissionData mission) {
        List<UnitGroup<GroundUnit>> gUnits = mission.getGroundUnits();
        List<UnitGroup<AirDefenceUnit>> aUnits = mission.getAirDefenceUnits();
        List<UnitGroup<AirUnit>> iUnits = mission.getLatentInterceptors();
        List<Mission> missions = mission.getMissions();
        Mission playerMission = mission.getPlayerMission();

        // Return List...
        List<DCSUnitGroup> groups = new ArrayList<>();

        // Various checking...
        for(UnitGroup<GroundUnit> g : gUnits) {
            // Create the Group
            DCSUnitGroup group = new DCSUnitGroup();

            // Get each unit in the group
            List<DCSUnit> groupUnits = new ArrayList<>();
            for(GroundUnit gu : g.getGroupUnits()) {
                DCSUnit unit = new DCSUnit();
                unit.setType("GROUND_UNIT");
                groupUnits.add(unit);
            }
            group.setUnits(groupUnits);
        }

        return groups;
    }

    private MissionCoalitions getMissionCoalitions(List<FactionType> blueFactions, List<FactionType> redFactions, List<FactionType> neutralFactions) {
        return new MissionCoalitions(blueFactions, redFactions, neutralFactions);
    }

    private AircraftFailures getAircraftFailures(DCSMissionData mission) {
        return new AircraftFailures();
    }

    private ForcedOptions getForcedOptions(DCSMissionData mission) {
        return new ForcedOptions();
    }

    private CurrentKeyText getCurrentKeyText(DCSMissionData mission) {
        return new CurrentKeyText();
    }

    private TrigrulesSection getTrigrulesSection(DCSMissionData mission) {
        return new TrigrulesSection();
    }

    private VersionText getVersionText(DCSMissionData mission) {
        return new VersionText();
    }

    private SortieText getSortieText(DCSMissionData mission) {
        return new SortieText();
    }

    private CoalitionDetails getCoalitionDetails(List<FactionType> blueFactions, List<FactionType> redFactions) {
        return new CoalitionDetails(blueFactions, redFactions);
    }

    private BlueTaskText getMissionBlueTaskText(DCSMissionData mission) {
        return new BlueTaskText();
    }

    private RedTaskText getMissionRedTaskText(DCSMissionData mission) {
        return new RedTaskText();
    }

    private PictureFileNameB getPictureFileNameB(DCSMissionData mission) {
        return new PictureFileNameB();
    }

    private NeutralTaskText getMissionNeutralTaskText(DCSMissionData mission) {
        return new NeutralTaskText();
    }

    private PictureFileNameR getMissionPictureR(DCSMissionData mission) {
        return new PictureFileNameR();
    }

    private MissionDescriptionText getMissionDescription(DCSMissionData mission) {
        return new MissionDescriptionText();
    }

    private MapLocation getMapLocation(DCSMissionData mission) {
        return new MapLocation();
    }

    private TriggerLocations getTriggerLocations(DCSMissionData mission) {
        return new TriggerLocations();
    }

    private MissionTheatre getMissionTheatre(DCSMissionData mission) {
        return new MissionTheatre();
    }

    private MissionWeather getMissionWeather(DCSMissionData mission) {
        return new MissionWeather();
    }

    private PictureFileNameN getPictureFileNameN(DCSMissionData mission) {
        return new PictureFileNameN();
    }

    private MaxGeneratedId getMissionMaxGeneratedId(DCSMissionData mission) {
        return new MaxGeneratedId();
    }

    private MissionGoals getMissionGoals(DCSMissionData mission) {
        return new MissionGoals();
    }

    private GroundControl getMissionGroundControl(DCSMissionData mission) {
        return new GroundControl();
    }

    private MissionResults getMissionResults(DCSMissionData mission) {
        return new MissionResults();
    }

    private RequiredModules getRequiredModules(DCSMissionData mission) {
        return new RequiredModules();
    }

    private MissionDate getMissionDate(DCSMissionData mission) {
        // Get the numeric day, year, month
        Calendar cal = Calendar.getInstance();
        // cal.setTime(mission.getCurrentCampaignDate());
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return new MissionDate(day, year, month);
    }

    private MissionStartTime getMissionStartTime(DCSMissionData mission) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(mission.getPlayerMission().getPlannedMissionDate());
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        long totalSeconds = TimeUnit.HOURS.toSeconds(hour) + TimeUnit.MINUTES.toSeconds(minute) + seconds;
        return new MissionStartTime(totalSeconds);
    }

    private MissionTriggers getMissionTriggers(DCSMissionData mission) {
        return new MissionTriggers();
    }
}
