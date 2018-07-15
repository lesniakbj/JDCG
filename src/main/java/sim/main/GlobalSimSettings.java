package sim.main;

import sim.domain.enums.MissionStartType;

public class GlobalSimSettings {
    private MissionStartType missionStartType;
    private int minutesPerSimulationStep;

    public GlobalSimSettings() {
        this.missionStartType = MissionStartType.PARKING_HOT;
        this.minutesPerSimulationStep = 5;
    }

    public int getMinutesPerSimulationStep() {
        return minutesPerSimulationStep;
    }

    public void setMinutesPerSimulationStep(int minutesPerSimulationStep) {
        this.minutesPerSimulationStep = minutesPerSimulationStep;
    }

    public MissionStartType getMissionStartType() {
        return missionStartType;
    }

    public void setMissionStartType(MissionStartType missionStartType) {
        this.missionStartType = missionStartType;
    }
}
