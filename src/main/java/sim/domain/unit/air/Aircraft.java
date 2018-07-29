package sim.domain.unit.air;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.AircraftType;

import java.util.Date;

public class Aircraft extends AirUnit {

    public Aircraft(AircraftType aircraftType) {
        super.setAircraftType(aircraftType);
    }

    @Override
    public void updateStep() {
        // Update here
    }

    @Override
    public void setCurrentCampaignDate(Date date) { }

    @Override
    public boolean shouldGenerateMission() {
        return false;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}
