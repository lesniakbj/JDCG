package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class VersionText implements FilePart {
    private static final String HEADER = "[\"version\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + 15 + ",");
        return parts;
    }
}