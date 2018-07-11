package sim.main;

import gen.domain.enums.MissionStartType;

public class GlobalSimSettings {
    private MissionStartType missionStartType;
    private int minutesPerSimulationStep;

    public GlobalSimSettings() {
        this.missionStartType = MissionStartType.PARKING_HOT;
        this.minutesPerSimulationStep = 10;
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
