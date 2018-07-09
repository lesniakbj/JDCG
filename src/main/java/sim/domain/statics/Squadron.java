package sim.domain.statics;

import java.util.Arrays;
import java.util.List;

public enum Squadron {
    NONE("None", Arrays.asList(ConflictEra.MODERN, ConflictEra.GULF_WAR), Arrays.asList(Task.CAP, Task.CAS, Task.GROUND_STRIKE), Arrays.asList(Aircraft.A_10_C));

    private String squadronName;
    private List<ConflictEra> era;
    private List<Task> tasks;
    private List<Aircraft> aircraft;

    Squadron(String squadronName, List<ConflictEra> era, List<Task> tasks, List<Aircraft> aircraft) {
        this.squadronName = squadronName;
        this.era = era;
        this.tasks = tasks;
        this.aircraft = aircraft;
    }


    public List<Task> getTaskList() {
        return tasks;
    }

    public List<Aircraft> getAircraftTypes() {
        return aircraft;
    }
}
