package dcsgen.file.mission.domain;

import sim.domain.enums.FactionType;

import java.util.LinkedList;
import java.util.List;

public class MissionCoalitions implements FilePart {
    private static final String HEADER = "[\"coalitions\"] = ";
    private static final String NEUTRAL_SUBHEADER = "[\"neutrals\"] = ";
    private static final String BLUE_SUBHEADER = "[\"blue\"] = ";
    private static final String RED_SUBHEADER = "[\"red\"] = ";

    private List<FactionType> blueFactions;
    private List<FactionType> redFactions;
    private List<FactionType> whiteFactions;

    public MissionCoalitions(List<FactionType> blue, List<FactionType> red, List<FactionType> neutral) {
        this.blueFactions = blue;
        this.redFactions = red;
        this.whiteFactions = neutral;
    }

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        parts.add("\t\t" + BLUE_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        addFactionIds(parts, blueFactions);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + NEUTRAL_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        addFactionIds(parts, whiteFactions);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + RED_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        addFactionIds(parts, redFactions);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }

    private void addFactionIds(List<String> parts, List<FactionType> factions) {
        for(int i = 0; i < factions.size(); i++) {
            parts.add("\t\t\t" + "[" + (i + 1) + "] = " + factions.get(i).getDcsFactionId() + ",");
        }
    }
}
