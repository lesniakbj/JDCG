package sim.domain.unit.air;

import java.util.Date;
import sim.domain.enums.AircraftType;

public class Helicopter extends AirUnit {

    public Helicopter(AircraftType selectedType) {
        super.setAircraftType(selectedType);
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
        return "{Helicopter}";
    }
}
