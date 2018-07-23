package sim.domain.unit.air;

import sim.domain.enums.AircraftType;
import sim.domain.enums.MajorTaskType;
import sim.domain.unit.SimUnit;

public abstract class AirUnit extends SimUnit {
    private MajorTaskType assignedTaskType;
    private AircraftType aircraftType;

    public MajorTaskType getAssignedTaskType() {
        return assignedTaskType;
    }

    public void setAssignedTaskType(MajorTaskType assignedTaskType) {
        this.assignedTaskType = assignedTaskType;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }
}

