package sim.domain.unit.air;

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
    public void setCurrentCampaignDate(Date date) {

    }

    @Override
    public boolean shouldGenerateMission() {
        return false;
    }


    @Override
    public String toString() {
        return "{\"Aircraft\":"
                + super.toString()
                + ", \"aircraftType\":\"" + super.getAircraftType() + "\""
                + "}";
    }
}
