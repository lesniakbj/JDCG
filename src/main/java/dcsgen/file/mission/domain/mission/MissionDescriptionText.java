package dcsgen.file.mission.domain.mission;

import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

public class MissionDescriptionText implements FilePart {
    private static final String HEADER = "[\"descriptionText\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + "\"DictKey_descriptionText_1\",");
        return parts;
    }
}
