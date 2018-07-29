package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class MaxGeneratedId implements FilePart {
    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t[\"maxDictId\"] = " + 5 + ",");
        return parts;
    }
}
