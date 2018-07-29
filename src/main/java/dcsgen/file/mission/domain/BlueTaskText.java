package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class BlueTaskText implements FilePart {
    private static final String HEADER = "[\"descriptionBlueTask\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + "\"DictKey_descriptionBlueTask_3\",");
        return parts;
    }
}
