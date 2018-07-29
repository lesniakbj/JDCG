package dcsgen.file.mission.domain;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class MissionTheatre implements FilePart {
    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + "[\"theatre\"] = \"PersianGulf\",");
        return parts;
    }
}
