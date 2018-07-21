package sim.domain.unit.air;

import sim.domain.enums.MajorTaskType;
import sim.domain.unit.SimUnit;

public abstract class AirUnit extends SimUnit {
    private MajorTaskType assignedTaskType;

    public MajorTaskType getAssignedTaskType() {
        return assignedTaskType;
    }

    public void setAssignedTaskType(MajorTaskType assignedTaskType) {
        this.assignedTaskType = assignedTaskType;
    }
}

