package dcsgen.file.mission.domain.mission;

import dcsgen.file.mission.domain.FilePart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GroundControl implements FilePart {
    private static final String HEADER = "[\"groundControl\"] = ";
    private static final String PILOT_VEHICLES_SUBHEADER = "[\"isPilotControlVehicles\"] = false,";
    private static final String ROLES_SUBHEADER = "[\"roles\"] = ";

    private static final String ARTILLERY_ROLE = "[\"artillery_commander\"] = ";
    private static final String INSTRUCTOR_ROLE = "[\"instructor\"] = ";
    private static final String OBSERVER_ROLE = "[\"observer\"] = ";
    private static final String FAC_ROLE = "[\"forward_observer\"] = ";

    private static final String NEUTRAL_COUNT = "[\"neutrals\"] = 0,";
    private static final String BLUE_COUNT = "[\"blue\"] = 0,";
    private static final String RED_COUNT = "[\"red\"] = 0,";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        parts.add("\t\t" + PILOT_VEHICLES_SUBHEADER);

        parts.add("\t\t" + ROLES_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);

        parts.add("\t\t\t" + ARTILLERY_ROLE);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.addAll(getRoleCount());
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t\t" + INSTRUCTOR_ROLE);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.addAll(getRoleCount());
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t\t" + OBSERVER_ROLE);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.addAll(getRoleCount());
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t\t" + FAC_ROLE);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.addAll(getRoleCount());
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + CLOSE_BRACE_COMMA);
        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }

    private List<String> getRoleCount() {
        List<String> parts = new ArrayList<>();
        parts.add("\t\t\t\t" + NEUTRAL_COUNT);
        parts.add("\t\t\t\t" + BLUE_COUNT);
        parts.add("\t\t\t\t" + RED_COUNT);
        return parts;
    }
}
