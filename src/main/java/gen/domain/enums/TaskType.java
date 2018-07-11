package gen.domain.enums;

public enum TaskType {
    CAS("CAS"),
    CAP("CAP"),
    SEAD("SEAD"),
    DEAD("DEAD"),
    ESCORT("Escort"),
    GROUND_STRIKE("Ground Strike"),
    INTERCEPT("Intercept");

    private String taskName;

    TaskType(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}