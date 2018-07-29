package dcsgen.file.mission.domain;

import sim.util.IDGenerator;

import java.util.LinkedList;
import java.util.List;

public class MaxGeneratedId implements FilePart {
    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        // Not correct, this is Sim ID, we need to generate an associated DCS ID
        parts.add("\t[\"maxDictId\"] = " + IDGenerator.getCurrentId() + ",");
        return parts;
    }
}
