package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class MissionGoals implements FilePart {
    private static final String HEADER = "[\"goals\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);
        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }
}
