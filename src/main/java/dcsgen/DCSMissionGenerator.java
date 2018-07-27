package dcsgen;

import dcsgen.util.ZipUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.MissionStartType;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.manager.CoalitionManager;
import sun.misc.IOUtils;

public class DCSMissionGenerator {
    private static final Logger log = LogManager.getLogger(DCSMissionGenerator.class);

    private static final String SAVE_PATH = System.getProperty("user.home") + "\\Saved Games\\Java DCS Campaign Generator";

    public void generateMission(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("I am supposed to generate a mission!");

        // All of the files needed to generate the mission
        List<File> missionFiles = new ArrayList<>();

        // Generate the various mission files and add them to the list of files
        missionFiles.add(createMissionFile(mission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createOptionsFile(mission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createWarehousesFile(mission, blueforCoalition, redforCoalition, missionStartType));
        missionFiles.add(createI10NFolder(mission, blueforCoalition, redforCoalition, missionStartType));

        // At the end, zip everything up and call it a .miz file
        ZipUtil.zipFiles(SAVE_PATH + "\\generated_mission.miz", missionFiles);
    }

    private File createMissionFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Mission File");

        // Find all of the missions/ground units within a certain radius of this mission's final WP
        Waypoint firstWp = mission.getNextWaypoint();
        Waypoint missionWp = mission.getMissionWaypoint();

        return new File("mission");
    }

    private File createOptionsFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Options File");
        return new File("options");
    }

    private File createWarehousesFile(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS Warehouses File");
        return new File("warehouses");
    }

    private File createI10NFolder(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("Creating DCS I10N Folder");
        String folder = "l10n\\DEFAULT\\";
        return new File(folder + "test");
    }
}
