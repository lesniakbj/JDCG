package dcsgen.file;

import dcsgen.file.mission.domain.FilePart;

import java.util.List;

public interface DCSFileGenerator {
    List<String> generateFileString(List<FilePart> fileParts);
}
