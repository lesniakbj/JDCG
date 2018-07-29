package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class NeutralTaskText implements FilePart {
    private static final String HEADER = "[\"descriptionNeutralsTask\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + "\"DictKey_descriptionNeutralsTask_4\",");
        return parts;
    }
}
