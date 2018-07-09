package ui.containers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import sim.domain.GameMap;
import sim.domain.statics.AircraftType;
import sim.domain.statics.CampaignType;
import sim.domain.statics.ConflictEra;
import sim.domain.statics.FactionSide;
import sim.domain.statics.SquadronType;
import sim.domain.statics.Task;
import ui.util.SpringUtilities;

public class NewCampaignOverviewPanel extends JPanel {
    private static final String[] labels = {"Map Selected: ", "Campaign Era Selected: ", "Campaign Type Selected: ", "Selected Coalition: ", "Selected Squadron: ", "Squadron Tasks: ", "Aircraft: "};
    private Map<String, JLabel> labelMapping;

    public NewCampaignOverviewPanel() {
        setLayout(new SpringLayout());

        labelMapping = new HashMap<>();
        for(String labelString : labels) {
            JLabel label = new JLabel(labelString);
            JLabel data = new JLabel();
            label.setLabelFor(data);
            add(label);
            add(data);
            labelMapping.put(labelString, data);
        }

        SpringUtilities.makeCompactGrid(this, 7, 2, 400, 300,10, 6);
    }

    public void setMapSelection(GameMap selectedMap) {
        labelMapping.get(labels[0]).setText(selectedMap.getMapName());
    }

    public void setSelectedEra(ConflictEra selectedEra) {
        labelMapping.get(labels[1]).setText(selectedEra.getEraName());
    }

    public void setCampaignType(CampaignType selectedCampaignType) {
        labelMapping.get(labels[2]).setText(selectedCampaignType.getCampaignTypeName());
    }

    public void setSelectedSide(FactionSide playerSelectedSide) {
        labelMapping.get(labels[3]).setText(playerSelectedSide.name());
    }

    public void setSelectedSquadron(SquadronType selectedSquadron) {
        labelMapping.get(labels[4]).setText(selectedSquadron.name());
        setSelectedSquadronTask(selectedSquadron.getTaskList());
        setSelectedAircraft(selectedSquadron.getAircraftTypes());
    }

    public void setSelectedSquadronTask(List<Task> squadronTasks) {
        String tasks = squadronTasks.stream().map(Task::getTaskName).collect(Collectors.joining(", "));
        labelMapping.get(labels[5]).setText(tasks);
    }

    public void setSelectedAircraft(List<AircraftType> aircraft) {
        String tasks = aircraft.stream().map(AircraftType::getAircraftName).collect(Collectors.joining(", "));
        labelMapping.get(labels[6]).setText(tasks);
    }
}
