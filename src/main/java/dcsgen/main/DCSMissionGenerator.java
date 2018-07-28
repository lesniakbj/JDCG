package dcsgen.main;

import dcsgen.file.DCSFileGenerator;
import dcsgen.file.mission.MissionFileGenerator;
import dcsgen.file.mission.domain.mission.GroundControl;
import dcsgen.file.mission.domain.mission.MissionDate;
import dcsgen.file.mission.domain.mission.MissionGoals;
import dcsgen.file.mission.domain.mission.MissionResults;
import dcsgen.file.mission.domain.mission.MissionTheatre;
import dcsgen.file.mission.domain.mission.MissionWeather;
import dcsgen.file.mission.domain.mission.trigger.MissionTriggers;
import dcsgen.file.mission.domain.RequiredModules;
import dcsgen.file.mission.domain.mission.trigger.TriggerLocations;
import dcsgen.file.options.OptionsFileGenerator;
import dcsgen.file.warehouses.WarehousesFileGenerator;
import dcsgen.util.ZipUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.MissionStartType;
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DCSMissionGenerator {
    private static final Logger log = LogManager.getLogger(DCSMissionGenerator.class);
    private static final String SAVE_PATH = System.getProperty("user.home") + "\\Saved Games\\Java DCS Campaign Generator";

    private DCSFileGenerator missionFileGenerator;
    private DCSFileGenerator optionsFileGenerator;
    private DCSFileGenerator warehousesFileGenerator;

    public DCSMissionGenerator() {
        this.missionFileGenerator = new MissionFileGenerator();
        this.optionsFileGenerator = new OptionsFileGenerator();
        this.warehousesFileGenerator = new WarehousesFileGenerator();
    }

    public void generateMission(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("I am supposed to generate a mission!");

        // All of the files needed to generate the mission
        List<File> missionFiles = new ArrayList<>();

        // Generate the various mission files and add them to the list of files
        missionFiles.add(createMissionFile(mission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createOptionsFile(mission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createWarehousesFile(mission, blueforCoalition, redforCoalition, missionStartType));
        // missionFiles.add(createI10NFolder(mission, blueforCoalition, redforCoalition, missionStartType));

        // At the end, zip everything up and call it a .miz file
        ZipUtil.zipFiles(SAVE_PATH + "\\generated_mission.miz", missionFiles);
    }

    private File createMissionFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Mission File");

        // Maybe before generating the file string, do processing to get everything that
        // generator would need?
        RequiredModules requiredModules = getRequiredModules(mission);
        MissionDate missionDate = getMissionDate(mission);
        MissionTriggers missionTriggers = getMissionTriggers(mission);
        MissionResults missionResults = getMissionResults(mission);
        GroundControl groundControl = getMissionGroundControl(mission);
        MissionGoals missionGoals = getMissionGoals(mission);
        MissionWeather missionWeather = getMissionWeather(mission);
        MissionTheatre missionTheatre = getMissionTheatre(mission);
        TriggerLocations triggerLocations = getTriggerLocations(missionTriggers, mission);
        int maxGeneratedId = getMaxGeneratedId(mission);

        // Generate the string that will be the mission file
        List<String> fileLines = missionFileGenerator.generateFileString(Arrays.asList(requiredModules, missionDate, missionTriggers,
                missionResults, groundControl, missionGoals, missionWeather, missionTheatre, triggerLocations));

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\mission");
        writeFile(missionFile, fileLines);

        return missionFile;
    }

    private File createOptionsFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Options File");

        // Generate the string that will be the mission file
        List<String> fileLines = optionsFileGenerator.generateFileString(null);

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\options");
        writeFile(missionFile, fileLines);

        return new File(SAVE_PATH + "\\options");
    }

    private File createWarehousesFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Warehouses File");

        // Generate the string that will be the warehouses file
        List<String> fileLines = warehousesFileGenerator.generateFileString(null);

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\warehouses");
        writeFile(missionFile, fileLines);

        return new File(SAVE_PATH + "\\warehouses");
    }

    private File createI10NFolder(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS I10N Folder");
        String folder = SAVE_PATH + "\\l10n\\DEFAULT\\";
        return new File(folder + "test");
    }

    private int getMaxGeneratedId(Mission mission) {
        return 0;
    }

    private MissionResults getMissionResults(Mission mission) {
        return new MissionResults();
    }

    private GroundControl getMissionGroundControl(Mission mission) {
        return new GroundControl();
    }

    private MissionGoals getMissionGoals(Mission mission) {
        return new MissionGoals();
    }

    private MissionWeather getMissionWeather(Mission mission) {
        return new MissionWeather();
    }

    private MissionTheatre getMissionTheatre(Mission mission) {
        return new MissionTheatre();
    }

    private TriggerLocations getTriggerLocations(MissionTriggers missionTriggers, Mission mission) {
        return new TriggerLocations();
    }

    private MissionTriggers getMissionTriggers(Mission mission) {
        return new MissionTriggers();
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

    private void writeFile(File missionFile, List<String> dataLines) {
        if(dataLines == null) {
            dataLines = Collections.emptyList();
        }

        Path path = Paths.get(missionFile.getAbsolutePath());
        try {
            Files.write(path, dataLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DCSMissionGenerator gen = new DCSMissionGenerator();
        gen.generateMission(null, null, null, null);
    }
}
