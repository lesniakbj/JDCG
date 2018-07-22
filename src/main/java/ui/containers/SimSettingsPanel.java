package ui.containers;

import sim.domain.enums.MissionStartType;
import sim.main.GlobalSimSettings;
import ui.util.SpringUtilities;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import java.util.Arrays;

public class SimSettingsPanel extends JPanel {
    private GlobalSimSettings simulationSettings;

    private JComboBox<String> missionStartType;
    private JTextField missionStep;
    private JCheckBox generateMissions;

    SimSettingsPanel(GlobalSimSettings simSettings) {
        // Create the campaign settings
        simulationSettings = new GlobalSimSettings(simSettings);

        // Create the layout
        this.setLayout(new BorderLayout());

        JPanel options = new JPanel();

        JPanel innerOptions = new JPanel(new SpringLayout());
        innerOptions.add(new JLabel("Mission Start Type:"));
        String[] startTypes = Arrays.stream(MissionStartType.values()).map(Enum::name).toArray(String[]::new);
        missionStartType = new JComboBox<>(new DefaultComboBoxModel<>(startTypes));
        missionStartType.setSelectedItem(simSettings.getMissionStartType().name());
        missionStartType.addActionListener(l -> simulationSettings.setMissionStartType(MissionStartType.valueOf((String)missionStartType.getSelectedItem())));
        innerOptions.add(missionStartType);
        innerOptions.add(new JLabel("Simulation Step Length (Minutes):"));
        missionStep = new JTextField(simSettings.getMinutesPerSimulationStep());
        missionStep.addActionListener(l -> simulationSettings.setMinutesPerSimulationStep(Integer.parseInt(missionStep.getText())));
        missionStep.setText(String.valueOf(simSettings.getMinutesPerSimulationStep()));
        innerOptions.add(missionStep);
        innerOptions.add(new JLabel("Generate All Friendly Missions On Mission Waypoint?:"));
        generateMissions = new JCheckBox();
        generateMissions.setSelected(simSettings.getGenerateMissionsOnMissionWaypoint());
        generateMissions.addActionListener((l) -> simulationSettings.setGenerateMissionsOnMissionWaypoint(generateMissions.isSelected()));
        innerOptions.add(generateMissions);
        SpringUtilities.makeCompactGrid(innerOptions, 3, 2, 10, 10,10, 6);

        options.add(innerOptions);
        this.add(options, BorderLayout.CENTER);
    }

    public GlobalSimSettings getSimulationSettings() {
        return simulationSettings;
    }

    public void setSimulationSettings(GlobalSimSettings simulationSettings) {
        this.simulationSettings = simulationSettings;
    }
}
