package dcsgen.file.mission.domain.mission;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class MissionCoalitions implements FilePart {
    private static final String HEADER = "[\"coalitions\"] = ";
    private static final String NEUTRAL_SUBHEADER = "[\"neutrals\"] = ";
    private static final String BLUE_SUBHEADER = "[\"blue\"] = ";
    private static final String RED_SUBHEADER = "[\"red\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        parts.add("\t\t" + NEUTRAL_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + BLUE_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t\t" + RED_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }
}
