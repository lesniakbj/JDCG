package dcsgen.file.mission.domain.mission;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class MissionStartTime implements FilePart {
    private static final String HEADER = "[\"start_time\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + 28800 + ",");
        return parts;
    }
}