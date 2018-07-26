package ui.containers.menu;

import static ui.util.ImageScaleUtil.tryLoadImage;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AircraftType;
import sim.domain.enums.MunitionType;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Munition;
import sim.domain.unit.air.WeaponStation;
import ui.util.SpringUtilities;

public class FlightLoadoutPanel extends JPanel {
    private static final Logger log = LogManager.getLogger(FlightLoadoutPanel.class);

    private JDialog flightLoadoutDialog;
    private Mission flightMission;
    private List<WeaponStation> flightLoadout;
    private Map<JComboBox,Integer> loadoutSelectors;
    private Map<JComboBox,JComboBox> totalLoadoutSelectors;
    private LoadoutBoxSelectionListener listener;

    public FlightLoadoutPanel(Mission flightMission, JDialog flightLoadoutDialog) {
        this.flightLoadoutDialog = flightLoadoutDialog;
        this.flightMission = flightMission;
        this.flightLoadout = new ArrayList<>();
        this.loadoutSelectors = new HashMap<>();
        this.listener = new LoadoutBoxSelectionListener();

        setLayout(new BorderLayout());

        assert flightMission != null;
        AircraftType type = flightMission.getMissionAircraft().getGroupUnits().get(0).getAircraftType();
        Map<Integer, List<Munition>> validConfigs = type.getStationMunitions();
        log.debug("Valid munitions: " + validConfigs);

        // Load the image associated with the loadout
        log.debug(type.name().replace(" ", "_"));
        BufferedImage mapImage = tryLoadImage("/aircraftstations/" + type.name().replace(" ", "_") + ".jpg");
        Image scaled = mapImage.getScaledInstance(400, 150, Image.SCALE_SMOOTH);
        JPanel image = new JPanel();
        image.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

        int rows = 0;
        JPanel munitionTable = new JPanel(new SpringLayout());
        List<WeaponStation> alreadySelected = flightMission.getMissionMunitions();
        for(Map.Entry<Integer, List<Munition>> entry : validConfigs.entrySet()) {
            int station = entry.getKey();
            JLabel label = new JLabel("Pylon " + station + ":");
            String[] values = entry.getValue().stream().map(Munition::getMunitionType).map(MunitionType::getMunitionName).toArray(String[]::new);
            JComboBox<String> comboBox = new JComboBox<>(values);
            JComboBox<Integer> totalComboBox = new JComboBox<>();

            if(alreadySelected != null && containsStationMunition(station, alreadySelected)) {
                WeaponStation selected = getStationByNumber(station, alreadySelected);
                comboBox.setSelectedItem(selected.getMunitionType().getMunitionName());
                WeaponStation ws = alreadySelected.get(station);
                Integer[] ints = IntStream.rangeClosed(1, ws.getTotalLoaded()).boxed().toArray(Integer[]::new);
                totalComboBox = new JComboBox<>(ints);
                totalComboBox.setSelectedItem(selected.getTotalLoaded());
                flightLoadout.add(selected);
            } else {
                comboBox.setSelectedIndex(-1);
            }

            munitionTable.add(label);
            munitionTable.add(comboBox);
            munitionTable.add(totalComboBox);
            comboBox.addActionListener(listener);
            loadoutSelectors.put(comboBox, station);
            totalLoadoutSelectors.put(comboBox, totalComboBox);
            rows++;
        }
        SpringUtilities.makeCompactGrid(munitionTable, rows, 3, 10, 10,10, 6);

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

    private WeaponStation getStationByNumber(int station, List<WeaponStation> alreadySelected) {
        for(WeaponStation s : alreadySelected) {
            if(s.getStationNumber() == station) {
                return s;
            }
        }
        return null;
    }

    private boolean containsStationMunition(int station, List<WeaponStation> alreadySelected) {
        for(WeaponStation s : alreadySelected) {
            if(s.getStationNumber() == station) {
                return true;
            }
        }
        return false;
    }

    public List<WeaponStation> getFlightLoadout() {
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
                JComboBox max = totalLoadoutSelectors.get(box);

                log.debug(String.format("Setting station: %d/%s", station, mt));
                removeStationLoadout(station);
                flightLoadout.add(new WeaponStation(station, new Munition(mt, 1)));
            }
        }

        private void removeStationLoadout(Integer station) {
            WeaponStation remove = null;
            for(WeaponStation s : flightLoadout) {
                if(s.getStationNumber() == station) {
                    remove = s;
                }
            }

            if(remove != null) {
                flightLoadout.remove(remove);
            }
        }
    }
}
