package dcsgen.file.mission.domain;

import sim.domain.enums.FactionType;

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

    private List<FactionType> blueFactions;
    private List<FactionType> redFactions;

    public CoalitionDetails(List<FactionType> blue, List<FactionType> red) {
        this.blueFactions = blue;
        this.redFactions = red;
    }

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        addCoalition(parts, BLUE_SUBHEADER, blueFactions, "blue");
        addCoalition(parts, RED_SUBHEADER, redFactions, "red");

        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }

    private void addCoalition(List<String> parts, String subheader, List<FactionType> factions, String side) {
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
        addFactionDetails(parts, factions);
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + CLOSE_BRACE_COMMA);
    }

    private void addFactionDetails(List<String> parts, List<FactionType> factions) {
        for(int i = 0; i < factions.size(); i++) {
            parts.add("\t\t\t\t" + "[" + (i + 1) + "] = ");
            parts.add("\t\t\t\t" + OPEN_BRACE);
            parts.add("\t\t\t\t\t" + "[\"id\"] = " + factions.get(i).getDcsFactionId() + ",");
            parts.add("\t\t\t\t\t" + "[\"name\"] = " + factions.get(i).getDcsFactionName() + ",");

            // If groups != null, add the groups
            if(true) {
                addPlaneGroups(parts);
            }

            parts.add("\t\t\t\t" + CLOSE_BRACE);
        }
    }

    private void addPlaneGroups(List<String> parts) {
        parts.add("\t\t\t\t\t" + "[\"plane\"] = ");
        parts.add("\t\t\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t\t\t\t" + "[\"group\"] = ");
        parts.add("\t\t\t\t\t\t" + OPEN_BRACE);
        addGroup(parts);
        parts.add("\t\t\t\t\t\t" + CLOSE_BRACE);

        parts.add("\t\t\t\t\t" + CLOSE_BRACE);
    }

    private void addGroup(List<String> parts) {
        parts.add("\t\t\t\t\t\t\t" + "[1] = ");
        parts.add("\t\t\t\t\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t\t\t\t\t" + CLOSE_BRACE);
    }
}
