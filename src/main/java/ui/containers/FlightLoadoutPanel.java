package ui.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Mission;
import sim.domain.Munition;
import sim.domain.enums.AircraftType;
import sim.domain.enums.MunitionType;
import ui.util.SpringUtilities;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightLoadoutPanel extends JPanel {
    private static final Logger log = LogManager.getLogger(FlightLoadoutPanel.class);

    private Mission flightMission;
    private Map<Integer, Munition> flightLoadout;

    public FlightLoadoutPanel(Mission flightMission) {
        this.flightMission = flightMission;
        setLayout(new SpringLayout());

        assert flightMission != null;
        AircraftType type = flightMission.getMissionAircraft().getGroupUnits().get(0).getAircraftType();
        Map<Integer, List<MunitionType>> validConfigs = type.getStationMunitions();

        int rows = 0;
        for(Map.Entry<Integer, List<MunitionType>> entry : validConfigs.entrySet()) {
            JLabel label = new JLabel("Pylon " + entry.getKey() + ":");
            String[] values = entry.getValue().stream().map((m) -> m.getMunitionName()).collect(Collectors.toList()).toArray(new String[0]);
            JComboBox comboBox = new JComboBox(values);
            add(label);
            add(comboBox);
            rows++;
        }
        SpringUtilities.makeCompactGrid(this, rows, 2, 10, 10,10, 6);
    }

    public Map<Integer,Munition> getFlightLoadout() {
        return flightLoadout;
    }
}
