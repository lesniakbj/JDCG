package sim.settings;

import sim.domain.TaskGenerationSetting;
import sim.domain.enums.MajorTaskType;
import sim.domain.enums.MissionStartType;

import java.util.ArrayList;
import java.util.List;

public class GlobalSimSettings {
    private MissionStartType missionStartType;
    private int minutesPerSimulationStep;
    private boolean generateMissionsOnMissionWaypoint;
    private List<TaskGenerationSetting> taskRatios;

    public GlobalSimSettings() {
        this.missionStartType = MissionStartType.PARKING_HOT;
        this.minutesPerSimulationStep = 1;
        this.generateMissionsOnMissionWaypoint = false;

        taskRatios = new ArrayList<>();
        for(int i = 0; i < MajorTaskType.values().length; i++) {
            taskRatios.add(new TaskGenerationSetting(MajorTaskType.values()[i], 25, i));
        }
    }

    public GlobalSimSettings(GlobalSimSettings simSettings) {
        this.missionStartType = simSettings.getMissionStartType();
        this.minutesPerSimulationStep = simSettings.getMinutesPerSimulationStep();
        this.generateMissionsOnMissionWaypoint = simSettings.isGenerateMissionsOnMissionWaypoint();
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

    public boolean isGenerateMissionsOnMissionWaypoint() {
        return generateMissionsOnMissionWaypoint;
    }

    public List<TaskGenerationSetting> getTaskRatios() {
        return taskRatios;
    }

    public void setTaskRatios(List<TaskGenerationSetting> taskRatios) {
        this.taskRatios = taskRatios;
    }
}
