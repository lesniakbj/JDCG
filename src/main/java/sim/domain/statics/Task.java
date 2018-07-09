package sim.domain.statics;

public enum Task {
    CAS("CAS"),
    CAP("CAP"),
    SEAD("SEAD"),
    DEAD("DEAD"),
    ESCORT("Escort"),
    GROUND_STRIKE("Ground Strike"),
    INTERCEPT("Intercept");

    private String taskName;

    Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}
