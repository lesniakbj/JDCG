package dcsgen.file.mission.domain.trigger;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class TriggerLocations implements FilePart {
    private static final String HEADER = "[\"triggers\"] = ";
    private static final String ZONES_SUBHEADER = "[\"zones\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);
        parts.add("\t\t" + ZONES_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);
        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }
}
