package dcsgen.file.mission.domain.trigger;

import dcsgen.file.mission.domain.FilePart;

import java.util.ArrayList;
import java.util.List;

public class MissionTriggers implements FilePart {
    private static final String HEADER = "[\"trig\"] = ";
    private static final String ACTION_SUBHEADER = "[\"actions\"] =";
    private static final String EVENT_SUBHEADER = "[\"events\"] = ";
    private static final String CUSTOM_SUBHEADER = "[\"custom\"] = ";
    private static final String FUNC_SUBHEADER = "[\"func\"] = ";
    private static final String FLAG_SUBHEADER = "[\"flag\"] = ";
    private static final String CONDITIONS_SUBHEADER = "[\"conditions\"] = ";
    private static final String CUST_STARTUP_SUBHEADER = "[\"customStartup\"] = ";
    private static final String STARTUP_SUBHEADER = "[\"funcStartup\"] = ";

    private List<Trigger> triggers;

    public MissionTriggers() {
        this.triggers = new ArrayList<>();
    }

    @Override
    public List<String> getFileParts() {
        List<String> parts = new ArrayList<>();
        parts.add("\t" + HEADER);
        parts.add("\t" + OPEN_BRACE);

        // Trigger Actions
        parts.add("\t\t" + ACTION_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        for(Trigger t : triggers) {
            parts.add("\t\t\t" + getTriggerActionsString(t));
        }
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        // Events (always empty for now)
        parts.add("\t\t" + EVENT_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        // Custom (always empty for now)
        parts.add("\t\t" + CUSTOM_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        // Trigger functions
        parts.add("\t\t" + FUNC_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        for(Trigger t : triggers) {
            parts.add("\t\t\t" + getTriggerFunctionString(t));
        }
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        // Trigger flags
        parts.add("\t\t" + FLAG_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        for(Trigger t : triggers) {
            parts.add("\t\t\t" + getTriggerFlagsString(t));
        }
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        // Trigger conditions
        parts.add("\t\t" + CONDITIONS_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        for(Trigger t : triggers) {
            parts.add("\t\t\t" + getTriggerConditionsString(t));
        }
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        // Custom startup (always empty for now)
        parts.add("\t\t" + CUST_STARTUP_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        // Trigger Startups
        parts.add("\t\t" + STARTUP_SUBHEADER);
        parts.add("\t\t" + OPEN_BRACE);
        for(Trigger t : triggers) {
            parts.add("\t\t\t" + getTriggerStartupString(t));
        }
        parts.add("\t\t" + CLOSE_BRACE_COMMA);

        parts.add("\t" + CLOSE_BRACE_COMMA);
        return parts;
    }

    private String getTriggerStartupString(Trigger t) {
        return "";
    }

    private String getTriggerConditionsString(Trigger t) {
        return "";
    }

    private String getTriggerFlagsString(Trigger t) {
        return "";
    }

    private String getTriggerFunctionString(Trigger t) {
        return "";
    }

    private String getTriggerActionsString(Trigger t) {
        return "";
    }
}
