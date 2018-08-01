package sim.domain.unit.air;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.SubTaskType;

import java.util.List;

public class Loadout {
    private SubTaskType subTaskType;
    private List<WeaponStation> weaponStations;

    public SubTaskType getSubTaskType() {
        return subTaskType;
    }

    public void setSubTaskType(SubTaskType subTaskType) {
        this.subTaskType = subTaskType;
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
