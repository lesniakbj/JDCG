package ui.containers;

import sim.main.GlobalSimSettings;

import javax.swing.JPanel;

public class SimSettingsPanel extends JPanel {
    private GlobalSimSettings simulationSettings;

    public GlobalSimSettings getSimulationSettings() {
        return simulationSettings;
    }

    public void setSimulationSettings(GlobalSimSettings simulationSettings) {
        this.simulationSettings = simulationSettings;
    }
}
