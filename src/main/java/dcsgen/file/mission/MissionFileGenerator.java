package dcsgen.file.mission;

import dcsgen.file.DCSFileGenerator;
import sim.domain.enums.MissionStartType;
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MissionFileGenerator implements DCSFileGenerator {
    private static final String MISSION_HEADER = "mission = ";

    // Main Headers
    private static final String REQUIRED_MODULES_HEADER = "[\"requiredModules\"] = ";
    private static final String DATE_HEADER = "[\"date\"] = ";
    private static final String TRIGGERS_HEADER = "[\"trig\"] = ";
    private static final String RESULT_HEADER = "[\"result\"] = ";

    // Sub Headers
    private static final String ACTIONS_SUB_HEADER = "[\"actions\"] = ";
    private static final String EVENTS_SUB_HEADER = "[\"events\"] = ";
    private static final String CUSTOM_SUB_HEADER = "[\"custom\"] = ";
    private static final String FUNC_SUB_HEADER = "[\"func\"] = ";
    private static final String FLAG_SUB_HEADER = "[\"flag\"] = ";
    private static final String CONDITIONS_SUB_HEADER = "[\"conditions\"] = ";
    private static final String CUSTOM_START_SUB_HEADER = "[\"customStartup\"] = ";
    private static final String STARTUP_SUB_HEADER = "[\"funcStartup\"] = ";

    private static final String OPEN_BRACE = "{";
    private static final String CLOSE_BRACE = "}";
    private static final String CLOSE_BRACE_COMMA = "},";

    // Find all of the missions/ground units within a certain radius of this mission's final WP
    // Waypoint firstWp = mission.getNextWaypoint();
    // Waypoint missionWp = mission.getMissionWaypoint();

    @Override
    public List<String> generateFileString(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        List<String> fileLines = new LinkedList<>();
        // Add the default header and first brace
        fileLines.add(MISSION_HEADER);
        fileLines.add(OPEN_BRACE);

        // Add the Required Modules section
        addRequiredModulesSection(fileLines, mission);

        // Add the Date Section
        addDateSection(fileLines, mission);

        // Add the Triggers Section
        addTriggersSection(fileLines, mission);

        // Add the Results Section
        addResultsSection(fileLines, mission);

        // Add the closing brace
        fileLines.add(CLOSE_BRACE);
        return fileLines;
    }

    private void addResultsSection(List<String> fileLines, Mission mission) {
        // Add the data to the file lines
        fileLines.add("\t" + RESULT_HEADER);
        fileLines.add("\t" + OPEN_BRACE);
        fileLines.add("\t" + CLOSE_BRACE_COMMA);
    }

    private void addTriggersSection(List<String> fileLines, Mission mission) {
        // Add the data to the file lines
        fileLines.add("\t" + TRIGGERS_HEADER);
        fileLines.add("\t" + OPEN_BRACE);
        addTriggersSubSection(fileLines, mission);
        fileLines.add("\t" + CLOSE_BRACE_COMMA);
    }

    private void addTriggersSubSection(List<String> fileLines, Mission mission) {
        fileLines.add("\t\t" + ACTIONS_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);

        fileLines.add("\t\t" + EVENTS_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);

        fileLines.add("\t\t" + CUSTOM_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);

        fileLines.add("\t\t" + FUNC_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);

        fileLines.add("\t\t" + FLAG_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);

        fileLines.add("\t\t" + CONDITIONS_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);

        fileLines.add("\t\t" + CUSTOM_START_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);

        fileLines.add("\t\t" + STARTUP_SUB_HEADER);
        fileLines.add("\t\t" + OPEN_BRACE);
        fileLines.add("\t\t" + CLOSE_BRACE_COMMA);
    }

    private void addDateSection(List<String> fileLines, Mission mission) {
        // Get the numeric day, year, month
        Calendar cal = Calendar.getInstance();
        // cal.setTime(mission.getCurrentCampaignDate());
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        // Add the data to the file lines
        fileLines.add("\t" + DATE_HEADER);
        fileLines.add("\t" + OPEN_BRACE);
        fileLines.add("\t\t" + "[\"Day\"] = " + day);
        fileLines.add("\t\t" + "[\"Year\"] = " + year);
        fileLines.add("\t\t" + "[\"Month\"] = " + month);
        fileLines.add("\t" + CLOSE_BRACE_COMMA);
    }

    private void addRequiredModulesSection(List<String> fileLines, Mission mission) {
        fileLines.add("\t" + REQUIRED_MODULES_HEADER);
        fileLines.add("\t" + OPEN_BRACE);
        fileLines.add("\t" + CLOSE_BRACE_COMMA);
    }
}
