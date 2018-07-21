package sim.domain.enums;

public enum TaskType {
    AIRLIFT("Munition Airlift"),
    AWACS("AWACS"),
    BDA("Battle Damage Assessment"),
    CAP("CAP"),
    CAS("CAS"),
    DEAD("DEAD"),
    ESCORT("Escort"),
    FAC("Forward Air Controller"),
    INTERDICTION("Ground Interdiction"),
    GROUND_STRIKE("Ground Strike"),
    INTERCEPT("Intercept"),
    LOW_LEVEL_STRIKE("Low Level Strike"),
    RECON("Reconnaissance"),
    REFUELING("Refueling"),
    SEAD("SEAD"),
    STEALTH("Stealth"),
    TRANSPORT("Troop Transport");

    private String taskName;

    TaskType(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}
