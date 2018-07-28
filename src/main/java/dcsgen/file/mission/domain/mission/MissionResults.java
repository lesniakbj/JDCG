package dcsgen.file.mission.domain.mission;

import dcsgen.file.mission.domain.FilePart;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MissionResults implements FilePart {
    private static final String HEADER = "[\"result\"] = ";
    private static final String OFFLINE_SUBHEDER = "[\"offline\"] = ";
    private static final String TOTAL_SUBHEDER = "[\"total\"] = ";
    private static final String BLUE_SUBHEDER = "[\"blue\"] = ";
    private static final String RED_SUBHEDER = "[\"red\"] = ";

    private static final String LOGIC_CONDITIONS = "[\"conditions\"] = ";
    private static final String LOGIC_ACTIONS = "[\"actions\"] = ";
    private static final String LOGIC_FUNCTIONS = "[\"func\"] = ";
    @Override
    public List<String> getFileParts() {
        List<String> parts = new LinkedList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        parts.add("\t\t" + OFFLINE_SUBHEDER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.addAll(logicBlock());
        parts.add("\t\t" + CLOSE_BRACE);

        parts.add("\t\t" + TOTAL_SUBHEDER + "0,");

        parts.add("\t\t" + BLUE_SUBHEDER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.addAll(logicBlock());
        parts.add("\t\t" + CLOSE_BRACE);

        parts.add("\t\t" + RED_SUBHEDER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.addAll(logicBlock());
        parts.add("\t\t" + CLOSE_BRACE);

        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }

    private List<String> logicBlock() {
        List<String> parts = new LinkedList<>();
        parts.add("\t\t\t" + LOGIC_CONDITIONS);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);
        parts.add("\t\t\t" + LOGIC_ACTIONS);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);
        parts.add("\t\t\t" + LOGIC_FUNCTIONS);
        parts.add("\t\t\t" + OPEN_BRACE);
        parts.add("\t\t\t" + CLOSE_BRACE_COMMA);
        return parts;
    }
}
