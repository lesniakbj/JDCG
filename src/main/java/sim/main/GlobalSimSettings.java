package sim.main;

public class GlobalSimSettings {
    private int minutesPerSimulationStep;

    public GlobalSimSettings() {
        this.minutesPerSimulationStep = 10;
    }

    public int getMinutesPerSimulationStep() {
        return minutesPerSimulationStep;
    }

    public void setMinutesPerSimulationStep(int minutesPerSimulationStep) {
        this.minutesPerSimulationStep = minutesPerSimulationStep;
    }
}
