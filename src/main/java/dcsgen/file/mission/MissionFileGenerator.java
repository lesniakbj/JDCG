package dcsgen.file.mission;

import dcsgen.file.DCSFileGenerator;
import dcsgen.file.mission.domain.FilePart;
import sim.domain.enums.MissionStartType;
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static dcsgen.file.mission.domain.FilePart.CLOSE_BRACE;
import static dcsgen.file.mission.domain.FilePart.CLOSE_BRACE_COMMA;
import static dcsgen.file.mission.domain.FilePart.OPEN_BRACE;

public class MissionFileGenerator implements DCSFileGenerator {
    private static final String MISSION_HEADER = "mission = ";

    // Find all of the missions/ground units within a certain radius of this mission's final WP
    // Waypoint firstWp = mission.getNextWaypoint();
    // Waypoint missionWp = mission.getMissionWaypoint();

    @Override
    public List<String> generateFileString(List<FilePart> fileParts) {
        List<String> fileLines = new LinkedList<>();
        fileLines.add(MISSION_HEADER);
        fileLines.add(OPEN_BRACE);

        // Add all of the parts
        for(FilePart p : fileParts) {
            if(p.getFileParts() != null) {
                fileLines.addAll(p.getFileParts());
            }
        }

        // Add the closing brace
        fileLines.add(CLOSE_BRACE);
        return fileLines;
    }
}
