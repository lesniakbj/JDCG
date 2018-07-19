package ui.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AircraftType;
import sim.domain.enums.MunitionType;
import sim.domain.unit.air.Mission;
import ui.util.SpringUtilities;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ui.util.ImageScaleUtil.tryLoadImage;

public class FlightLoadoutPanel extends JPanel {
    private static final Logger log = LogManager.getLogger(FlightLoadoutPanel.class);

    private JDialog flightLoadoutDialog;
    private Mission flightMission;
    private Map<Integer,MunitionType> flightLoadout;
    private Map<JComboBox,Integer> loadoutSelectors;
    private LoadoutBoxSelectionListener listener;

    public FlightLoadoutPanel(Mission flightMission, JDialog flightLoadoutDialog) {
        this.flightLoadoutDialog = flightLoadoutDialog;
        this.flightMission = flightMission;
        this.flightLoadout = new HashMap<>();
        this.loadoutSelectors = new HashMap<>();
        this.listener = new LoadoutBoxSelectionListener();

        setLayout(new BorderLayout());

        assert flightMission != null;
        AircraftType type = flightMission.getMissionAircraft().getGroupUnits().get(0).getAircraftType();
        Map<Integer, List<MunitionType>> validConfigs = type.getStationMunitions();

        // Load the image associated with the loadout
        log.debug(type.name().replace(" ", "_"));
        BufferedImage mapImage = tryLoadImage("/aircraftstations/" + type.name().replace(" ", "_") + ".jpg");
        Image scaled = mapImage.getScaledInstance(400, 150, Image.SCALE_SMOOTH);
        JPanel image = new JPanel();
        image.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

        int rows = 0;
        JPanel munitionTable = new JPanel(new SpringLayout());
        Map<Integer,MunitionType> alreadySelected = flightMission.getMissionMunitions();
        for(Map.Entry<Integer, List<MunitionType>> entry : validConfigs.entrySet()) {
            int station = entry.getKey();
            JLabel label = new JLabel("Pylon " + station + ":");
            String[] values = entry.getValue().stream().map(MunitionType::getMunitionName).toArray(String[]::new);
            JComboBox<String> comboBox = new JComboBox<>(values);

            if(alreadySelected != null && alreadySelected.get(station) != null) {
                comboBox.setSelectedItem(alreadySelected.get(station).getMunitionName());
                flightLoadout.put(station, alreadySelected.get(station));
            } else {
                comboBox.setSelectedIndex(-1);
            }

            munitionTable.add(label);
            munitionTable.add(comboBox);
            comboBox.addActionListener(listener);
            loadoutSelectors.put(comboBox, station);
            rows++;
        }
        SpringUtilities.makeCompactGrid(munitionTable, rows, 2, 10, 10,10, 6);

        JPanel buttons = new JPanel();
        JButton accept = new JButton("Accept");
        JButton cancel = new JButton("Cancel");
        accept.addActionListener(l -> flightLoadoutDialog.dispose());
        cancel.addActionListener(l -> {
            flightLoadout = flightMission.getMissionMunitions();
            flightLoadoutDialog.dispose();
        });
        buttons.add(accept);
        buttons.add(cancel);

        add(image, BorderLayout.NORTH);
        add(munitionTable, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    public Map<Integer,MunitionType> getFlightLoadout() {
        return flightLoadout;
    }

    private class LoadoutBoxSelectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = ((JComboBox)e.getSource());
            String selectedName = (String)box.getSelectedItem();

            if(selectedName != null && !selectedName.isEmpty()) {
                MunitionType mt = MunitionType.fromName(selectedName);
                Integer station = loadoutSelectors.get(box);

                log.debug(String.format("Setting station: %d/%s", station, mt));
                flightLoadout.put(station, mt);
            }
        }
    }
}
