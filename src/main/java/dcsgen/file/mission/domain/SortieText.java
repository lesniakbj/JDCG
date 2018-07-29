package dcsgen.file.mission.domain;

import java.util.LinkedList;
import java.util.List;

public class SortieText implements FilePart {
    private static final String HEADER = "[\"sortie\"] = ";

    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER + "\"DictKey_sortie_5\",");
        return parts;
    }
}
