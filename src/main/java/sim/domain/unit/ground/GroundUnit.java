package sim.domain.unit.ground;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.GroundUnitType;
import sim.domain.unit.SimUnit;

public abstract class GroundUnit extends SimUnit {
    private GroundUnitType type;

    public GroundUnitType getType() {
        return type;
    }

    public void setType(GroundUnitType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.JSON_STYLE, true, true);
    }
}
