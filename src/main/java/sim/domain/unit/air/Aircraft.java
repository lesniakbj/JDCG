package sim.domain.unit.air;

import java.util.Date;
import sim.domain.enums.AircraftType;
import sim.domain.unit.SimUnit;

public class Aircraft extends SimUnit {
    private AircraftType aircraftType;

    public Aircraft(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
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

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftType=" + aircraftType +
                '}';
    }
}