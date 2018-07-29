package dcsgen.file.mission.domain;

import sim.domain.enums.MissionStartType;

import java.util.LinkedList;
import java.util.List;

public class MissionStartTime implements FilePart {
    private static final String HEADER = "[\"start_time\"] = ";

    private long startTimeSeconds;

    public MissionStartTime(long startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + startTimeSeconds + ",");
        return parts;
    }
}