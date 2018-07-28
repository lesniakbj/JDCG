package dcsgen.file.mission.domain;

import java.util.List;

public interface FilePart {
    String OPEN_BRACE = "{";
    String CLOSE_BRACE = "}";
    String CLOSE_BRACE_COMMA = "},";

    List<String> getFileParts();
}
