package sim.domain.unit.air;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.AircraftType;
import sim.domain.enums.MajorTaskType;
import sim.domain.unit.SimUnit;

import java.util.List;

public abstract class AirUnit extends SimUnit {
    private MajorTaskType assignedTaskType;
    private AircraftType aircraftType;
    private List<WeaponStation> weaponStations;

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

    public List<WeaponStation> getWeaponStations() {
        return weaponStations;
    }

    public void setWeaponStations(List<WeaponStation> weaponStations) {
        this.weaponStations = weaponStations;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}

