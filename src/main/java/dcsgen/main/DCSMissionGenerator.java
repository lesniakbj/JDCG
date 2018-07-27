package dcsgen.main;

import dcsgen.file.DCSFileGenerator;
import dcsgen.file.mission.MissionFileGenerator;
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
import java.util.Collections;
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

        // Generate the string that will be the mission file
        List<String> fileLines = missionFileGenerator.generateFileString(mission, blueforCoalition, redforCoalition, missionStartType);

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\mission");
        writeFile(missionFile, fileLines);

        return missionFile;
    }

    private File createOptionsFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Options File");

        // Generate the string that will be the mission file
        List<String> fileLines = optionsFileGenerator.generateFileString(mission, blueforCoalition, redforCoalition, missionStartType);

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\options");
        writeFile(missionFile, fileLines);

        return new File(SAVE_PATH + "\\options");
    }

    private File createWarehousesFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Warehouses File");

        // Generate the string that will be the warehouses file
        List<String> fileLines = warehousesFileGenerator.generateFileString(mission, blueforCoalition, redforCoalition, missionStartType);

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
