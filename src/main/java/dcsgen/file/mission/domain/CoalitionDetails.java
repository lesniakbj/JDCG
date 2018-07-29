package dcsgen.file.mission.domain;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class CoalitionDetails implements FilePart {
    private static final String HEADER = "[\"coalition\"] = ";

    private static final String BLUE_SUBHEADER = "[\"blue\"] = ";
    private static final String RED_SUBHEADER = "[\"red\"] = ";

    private static final String BULLSEYE = "[\"bullseye\"] = ";
    private static final String NAV_POINTS = "[\"nav_points\"] = ";
    private static final String NAME = "[\"name\"] = ";
    private static final String COUNTRIES = "[\"country\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        addCoalition(parts, BLUE_SUBHEADER, "blue");
        addCoalition(parts, RED_SUBHEADER, "red");

        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }

    private void addCoalition(List<String> parts, String subheader, String side) {
        parts.add("\t\t" + subheader);
        parts.add("\t\t" + OPEN_BRACE);

        parts.add("\t\t\t" + BULLSEYE);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t\t" + "[\"y\"] = 0,");
        parts.add("\t\t\t\t" + "[\"x\"] = 0,");
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t\t" + NAV_POINTS);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t\t" + NAME + "\"" + side + "\",");

        parts.add("\t\t\t" + COUNTRIES);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + CLOSE_BRACE_COMMA);
    }
}
