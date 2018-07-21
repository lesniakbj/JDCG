package sim.main;

import sim.domain.enums.MissionStartType;

public class GlobalSimSettings {
    private MissionStartType missionStartType;
    private int minutesPerSimulationStep;
    private boolean generateMissionsOnMissionWaypoint;

    public GlobalSimSettings() {
        this.missionStartType = MissionStartType.PARKING_HOT;
        this.minutesPerSimulationStep = 5;
        this.generateMissionsOnMissionWaypoint = true;
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

    public void setGenerateMissionsOnMissionWaypoint(boolean generateMissionsOnMissionWaypoint) {
        this.generateMissionsOnMissionWaypoint = generateMissionsOnMissionWaypoint;
    }

    public boolean getGenerateMissionsOnMissionWaypoint() {
        return generateMissionsOnMissionWaypoint;
    }
}
