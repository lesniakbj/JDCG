package dcsgen.file.mission.domain.mission.trigger;

public enum TriggerActionType {
    ACTIVATE_GROUP("a_activate_group"),
    TEXT_DELAY("a_out_text_delay_s"),
    CLEAR_FLAG("a_clear_flag"),
    SET_FLAG("a_set_flag"),
    DISABLE_TRIGGER("mission.trig.func");

    private String luaCommand;

    TriggerActionType(String luaCommand) {
    }
}
