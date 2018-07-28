package dcsgen.file.mission;

import dcsgen.file.DCSFileGenerator;
import dcsgen.file.mission.domain.FilePart;

import java.util.LinkedList;
import java.util.List;

import static dcsgen.file.mission.domain.FilePart.CLOSE_BRACE;
import static dcsgen.file.mission.domain.FilePart.OPEN_BRACE;

public class MissionFileGenerator implements DCSFileGenerator {
    private static final String MISSION_HEADER = "mission = ";

    @Override
    public List<String> generateFileString(List<FilePart> fileParts) {
        List<String> fileLines = new LinkedList<>();
        fileLines.add(MISSION_HEADER);
        fileLines.add(OPEN_BRACE);

        // Add all of the parts
        for(FilePart p : fileParts) {
            if(p.getFileParts() != null) {
                fileLines.addAll(p.getFileParts());
            }
        }

        // Add the closing brace
        fileLines.add(CLOSE_BRACE);
        return fileLines;
    }
}
