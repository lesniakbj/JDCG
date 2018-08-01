package sim.domain.unit.air;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class DefaultLoadouts {
    private List<Loadout> defaultLoadouts;

    public List<Loadout> getDefaultLoadouts() {
        return defaultLoadouts;
    }

    public void setDefaultLoadouts(List<Loadout> defaultLoadouts) {
        this.defaultLoadouts = defaultLoadouts;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}
