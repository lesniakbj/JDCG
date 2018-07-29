package dcsgen.file.mission.domain;

import dcsgen.file.mission.domain.FilePart;

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
