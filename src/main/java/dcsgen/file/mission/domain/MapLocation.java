package dcsgen.file.mission.domain;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class MapLocation implements FilePart {
    private static final String HEADER = "[\"map\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);
        parts.add("\t\t" + "[\"centerY\"] = -35610,");
        parts.add("\t\t" + "[\"zoom\"] = 1000000,");
        parts.add("\t\t" + "[\"centerX\"] = 67350,");
        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }
}
