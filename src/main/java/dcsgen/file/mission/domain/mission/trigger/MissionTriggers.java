package dcsgen.file.mission.domain.mission.trigger;

import dcsgen.file.mission.domain.FilePart;

import java.util.List;

public class MissionTriggers implements FilePart {
    private List<Trigger> actions;

    @Override
    public List<String> getFileParts() {
        return null;
    }
}
