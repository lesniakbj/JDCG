package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class CurrentKeyText implements FilePart {
    private static final String HEADER = "[\"currentKey\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + 62 + ",");
        return parts;
    }
}