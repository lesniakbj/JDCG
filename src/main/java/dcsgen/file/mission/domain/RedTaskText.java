package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class RedTaskText implements FilePart {
    private static final String HEADER = "[\"descriptionRedTask\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + "\"DictKey_descriptionRedTask_2\",");
        return parts;
    }
}
