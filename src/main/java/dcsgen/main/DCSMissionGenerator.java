package dcsgen.main;

import dcsgen.file.mission.MissionFileGenerator;
import dcsgen.file.mission.domain.DCSMissionFile;
import dcsgen.file.options.OptionsFileGenerator;
import dcsgen.file.warehouses.WarehousesFileGenerator;
import dcsgen.translate.DCSMission;
import dcsgen.translate.DCSMissionTranslator;
import dcsgen.util.ZipUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
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

    private DCSMissionTranslator translator;
    private MissionFileGenerator missionFileGenerator;
    private OptionsFileGenerator optionsFileGenerator;
    private WarehousesFileGenerator warehousesFileGenerator;

    public DCSMissionGenerator() {
        this.translator = new DCSMissionTranslator();
        this.missionFileGenerator = new MissionFileGenerator();
        this.optionsFileGenerator = new OptionsFileGenerator();
        this.warehousesFileGenerator = new WarehousesFileGenerator();
    }

    public void generateMission(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("I am supposed to generate a mission!");

        // All of the files needed to generate the mission
        List<File> missionFiles = new ArrayList<>();

        // Translate the mission to a DCSMission (easier to produce the file from here)
        DCSMission dcsMission = translator.translateSimMissionToDCSMission(mission, blueforCoalition, redforCoalition, missionStartType);
        DCSMissionFile missionFile = missionFileGenerator.getMissionFileFromMission(dcsMission);

        // Generate the various mission files and add them to the list of files
        missionFiles.add(createMissionFile(missionFile, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createOptionsFile(dcsMission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createWarehousesFile(dcsMission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createI10NFolderDictionary(mission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createI10NFolderMapResource(mission, blueforCoalition, redforCoalition, missionStartType));

        // At the end, zip everything up and call it a .miz file
        ZipUtil.zipFiles(SAVE_PATH, SAVE_PATH + "\\generated_mission.miz", missionFiles);
    }

    private File createMissionFile(DCSMissionFile mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Mission File");

        // Generate the string that will be the mission file
        List<String> fileLines = missionFileGenerator.generateFileString(mission.getFileLines());

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\mission");
        writeFile(missionFile, fileLines);

        return missionFile;
    }

    private File createOptionsFile(DCSMission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Options File");

        // Generate the string that will be the mission file
        List<String> fileLines = optionsFileGenerator.generateFileString(null);

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\options");
        writeFile(missionFile, fileLines);

        return new File(SAVE_PATH + "\\options");
    }

    private File createWarehousesFile(DCSMission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Warehouses File");

        // Generate the string that will be the warehouses file
        List<String> fileLines = warehousesFileGenerator.generateFileString(null);

        // Write the file
        File missionFile = new File(SAVE_PATH + "\\warehouses");
        writeFile(missionFile, fileLines);

        return new File(SAVE_PATH + "\\warehouses");
    }

    private File createI10NFolderDictionary(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS I10N Folder dictionary");

        File dictionaryFile = new File(SAVE_PATH + "\\l10n\\DEFAULT\\dictionary");
        writeFile(dictionaryFile, Collections.singletonList("Test"));

        return dictionaryFile;
    }

    private File createI10NFolderMapResource(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS I10N Folder map resource");

        File dictionaryFile = new File(SAVE_PATH + "\\l10n\\DEFAULT\\mapResource");
        writeFile(dictionaryFile, Collections.singletonList("Test"));

        return dictionaryFile;
    }

    private void writeFile(File missionFile, List<String> dataLines) {
        if(dataLines == null) {
            dataLines = Collections.emptyList();
        }

        Path path = Paths.get(missionFile.getAbsolutePath());
        try {
            Files.createDirectories(Paths.get(missionFile.getParent()));
            Files.write(path, dataLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DynamicCampaignSim sim = new DynamicCampaignSim();
        sim.generateNewCampaign();
        for(int i = 0; i < 5; i++) {
            sim.stepSimulation();
        }

        // Get the first mission generate on bluefor
        Mission m =  sim.getBlueforCoalitionManager().getMissionManager().getPlannedMissions().get(0);
        CoalitionManager b =  sim.getBlueforCoalitionManager();
        CoalitionManager r =  sim.getRedforCoalitionManager();

        DCSMissionGenerator gen = new DCSMissionGenerator();
        gen.generateMission(m, b, r, MissionStartType.PARKING_COLD);
    }
}
