package sim.domain;

import sim.domain.enums.AircraftType;

import java.util.Date;

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
