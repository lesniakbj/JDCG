package dcsgen.file.mission.domain;

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
