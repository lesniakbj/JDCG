package dcsgen.file.mission.domain;

import dcsgen.file.mission.domain.FilePart;

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