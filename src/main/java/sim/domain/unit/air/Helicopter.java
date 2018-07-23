package sim.domain.unit.air;

import java.util.Date;
import sim.domain.enums.AircraftType;

public class Helicopter extends AirUnit {
    private AircraftType selectedType;

    public Helicopter(AircraftType selectedType) {
        this.selectedType = selectedType;
    }

    @Override
    public void updateStep() {

    }

    @Override
    public void setCurrentCampaignDate(Date date) {

    }

    @Override
    public boolean shouldGenerateMission() {
        return false;
    }

    @Override
    public String toString() {
        return "{\"Helicopter\":"
                + super.toString()
                + ", \"selectedType\":\"" + selectedType + "\""
                + "}";
    }
}
