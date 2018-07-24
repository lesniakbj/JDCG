package ui.containers.menu;

import sim.domain.enums.AircraftType;
import sim.domain.enums.CampaignType;
import sim.domain.enums.ConflictEraType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.enums.MapType;
import sim.domain.enums.SquadronType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.global.Coalition;
import sim.domain.unit.global.GameMap;
import sim.settings.CampaignSettings;
import ui.util.SpringUtilities;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sim.domain.enums.FactionSideType.BLUEFOR;
import static sim.domain.enums.FactionSideType.NEUTRAL;
import static sim.domain.enums.FactionSideType.REDFOR;
import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;
import static ui.util.ImageScaleUtil.NORMAL_IMAGE_RATIO;
import static ui.util.ImageScaleUtil.tryLoadImage;

public class NewCampaignPanel extends JPanel {
    // Private data used when creating the campaign
    private NewCampaignPanel self;
    private NewCampaignOverviewPanel overviewPanel;
    private CampaignSettings campaignSettings;
    private Map<FactionSideType, JTable> factionTables;
    private JComboBox<String> squadronBox;

    private static final int MAP_WIDTH = 550;
    private static final String[] FACTION_COLUMNS = {"FactionType Name", "FactionType Strength"};

    public NewCampaignPanel() {
        self = this;

        // Create the campaign settings
        campaignSettings = new CampaignSettings();

        // Create the layout
        this.setLayout(new BorderLayout());

        JTabbedPane pane = new JTabbedPane();
        JPanel mapPanel = createMapPanel();
        pane.addTab("Map Selection", mapPanel);
        pane.addTab("Factions", createFactionsPanel());
        pane.addTab("Era & Campaign Type", createEraPanel());
        pane.addTab("Squadron & Coalition", createSquadronPanel());
        pane.addTab("Campaign Overview & Confirmation", createCampaignOverviewPanel());

        // Create the pane action listener to save state on changes
        pane.addChangeListener(new PanelChangeListener());

        this.add(pane, BorderLayout.NORTH);
        setPreferredSize(new Dimension(1200, 1072));
    }

    private JPanel createMapPanel() {
        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(400, 400));
        mapPanel.setLayout(new GridLayout(2, 2));

        // For each map, create a panel and add it to the map panel
        MapSelectionListener mapSelectionListener = new MapSelectionListener();
        for(MapType map : MapType.values()) {
            // Create the panel and add basic labels
            MapSelectionPanel mapImagePanel = new MapSelectionPanel();
            mapImagePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            mapImagePanel.setLayout(new BorderLayout());
            mapImagePanel.add(new JLabel(map.getMapName(), SwingConstants.CENTER), BorderLayout.NORTH);

            // Load the image for the map
            BufferedImage mapImage = tryLoadImage("/map/" + map.getMapName().replace(" ", "_") + "_map.png");
            Image scaled = mapImage.getScaledInstance(MAP_WIDTH, (int)(MAP_WIDTH * MAP_IMAGE_HEIGHT_RATIO), Image.SCALE_SMOOTH);
            mapImagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

            // Add listeners and add it to the panel
            mapSelectionListener.addWatchedPanel(mapImagePanel);
            mapImagePanel.addMouseListener(mapSelectionListener);
            mapImagePanel.setMap(map);
            mapPanel.add(mapImagePanel);
        }

        return mapPanel;
    }

    private JPanel createFactionsPanel() {
        // Create the faction tables map
        factionTables = new HashMap<>();

        // Create the container panel and layout
        JPanel factionContainer = new JPanel();
        factionContainer.setLayout(new BorderLayout());

        // Create the button panel
        JPanel buttonPanel = generateButtonPanel();

        // Create the default faction panel
        JPanel factionPanel = generateNeutralFactionTable();

        // Create the Left and Right (Blufor/Opfor) panel
        JPanel bluforPanel = generateFactionTable(BLUEFOR);
        JPanel redforPanel = generateFactionTable(REDFOR);

        // Add the panel to the containers
        JPanel upperHalf = new JPanel();
        upperHalf.setLayout(new BorderLayout());
        upperHalf.add(bluforPanel, BorderLayout.WEST);
        upperHalf.add(buttonPanel, BorderLayout.CENTER);
        upperHalf.add(redforPanel, BorderLayout.EAST);

        factionContainer.add(upperHalf, BorderLayout.NORTH);
        factionContainer.add(factionPanel, BorderLayout.SOUTH);
        return factionContainer;
    }

    private JPanel createEraPanel() {
        JPanel eraPanel = new JPanel();
        eraPanel.setLayout(new BorderLayout());

        // Create the image that is associated with the selected JComboBox item
        JPanel eraImagePanel = new JPanel();
        BufferedImage mapImage = tryLoadImage("/era/" + ConflictEraType.MODERN.getEraName().replace(" ", "_") + ".jpg");
        Image scaled = mapImage.getScaledInstance(MAP_WIDTH * 2, (int)((MAP_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
        eraImagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

        // Create the JComboBox of Eras
        JPanel boxPanel = new JPanel();
        JComboBox<String> eraBox = new JComboBox<>(Stream.of(ConflictEraType.values()).map(ConflictEraType::getEraName).toArray(String[]::new));
        JComboBox<String> campaignType = new JComboBox<>(Stream.of(CampaignType.values()).map(CampaignType::getCampaignTypeName).toArray(String[]::new));
        boxPanel.add(eraBox);
        boxPanel.add(campaignType);

        // Create the associated label
        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("<html><body style='width:600px'>" + ConflictEraType.MODERN.getEraDescription() + CampaignType.OFFENSIVE.getCharacteristics() + "</html>", SwingConstants.CENTER);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 200, 0));
        labelPanel.add(label);

        // Add action listener
        EraSelectionListener eraSelectionListener = new EraSelectionListener(eraBox, label, eraImagePanel, campaignType);
        eraBox.addActionListener(eraSelectionListener);
        campaignType.addActionListener(eraSelectionListener);

        eraPanel.add(eraImagePanel, BorderLayout.NORTH);
        eraPanel.add(boxPanel, BorderLayout.CENTER);
        eraPanel.add(labelPanel, BorderLayout.SOUTH);
        return eraPanel;
    }

    private JPanel createSquadronPanel() {
        JPanel squadronPanel = new JPanel();
        squadronPanel.setLayout(new BorderLayout());

        // Create the image that is associated with the selected JComboBox item
        JPanel squadronImagePanel = new JPanel();
        BufferedImage mapImage = tryLoadImage("/squadron/" + SquadronType.NONE.name().replace(" ", "_") + ".jpg");
        Image scaled = mapImage.getScaledInstance(MAP_WIDTH * 2, (int)((MAP_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
        squadronImagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

        // Create the JComboBox of Squadrons
        JPanel squadronBoxPanel = new JPanel();
        squadronBox = new JComboBox<>(Stream.of(SquadronType.values()).map(SquadronType::getSquadronName).toArray(String[]::new));
        squadronBoxPanel.add(squadronBox);

        // Information about the selected Squadron
        JPanel squadronInfoPanel = new JPanel();
        squadronInfoPanel.setLayout(new SpringLayout());
        JLabel squadronNameLabel = new JLabel("Squadron Name: ");
        JLabel squadronName = new JLabel(SquadronType.NONE.getSquadronName());
        JLabel squadronTasksLabel = new JLabel("Squadron Tasks: ");
        JLabel squadronTasks = new JLabel(SquadronType.NONE.getTaskList().stream().map(SubTaskType::getTaskName).collect(Collectors.joining(", ")));
        JLabel squadronAircraftLabel = new JLabel("Squadron Aircraft: ");
        JLabel squadronAircraft = new JLabel(SquadronType.NONE.getAircraftTypes().stream().map(AircraftType::getAircraftName).collect(Collectors.joining(", ")));
        JLabel squadronEraLabel = new JLabel("Squadron Active Eras: ");
        JLabel squadronEra = new JLabel(SquadronType.NONE.getEra().stream().map(ConflictEraType::getEraName).collect(Collectors.joining(", ")));
        squadronInfoPanel.add(squadronNameLabel);
        squadronInfoPanel.add(squadronName);
        squadronInfoPanel.add(squadronTasksLabel);
        squadronInfoPanel.add(squadronTasks);
        squadronInfoPanel.add(squadronAircraftLabel);
        squadronInfoPanel.add(squadronAircraft);
        squadronInfoPanel.add(squadronEraLabel);
        squadronInfoPanel.add(squadronEra);
        SpringUtilities.makeCompactGrid(squadronInfoPanel, 4, 2, 435, 10,10, 6);

        // Add the Squadron Selection Listener
        SquadronSelectionListener squadronSelectionListener = new SquadronSelectionListener(squadronImagePanel, squadronName, squadronTasks, squadronAircraft, squadronEra);
        squadronBox.addActionListener(squadronSelectionListener);

        // Create the RadioButtons of Team (Bluefor/Redfor)
        CoalitionItemListener coalitionSelectionListener = new CoalitionItemListener();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JRadioButton blueforButton = new JRadioButton("BLUEFOR");
        JRadioButton redforButton = new JRadioButton("REDFOR");
        blueforButton.setSelected(true);
        blueforButton.addItemListener(coalitionSelectionListener);
        redforButton.addItemListener(coalitionSelectionListener);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(blueforButton);
        buttonGroup.add(redforButton);
        buttonPanel.add(blueforButton);
        buttonPanel.add(redforButton);

        // Add the container
        JPanel outerContainerPanel = new JPanel();
        outerContainerPanel.setLayout(new BorderLayout());
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(buttonPanel, BorderLayout.NORTH);
        containerPanel.add(squadronBoxPanel, BorderLayout.CENTER);
        outerContainerPanel.add(containerPanel, BorderLayout.NORTH);
        outerContainerPanel.add(squadronInfoPanel, BorderLayout.CENTER);

        squadronPanel.add(squadronImagePanel, BorderLayout.NORTH);
        squadronPanel.add(outerContainerPanel, BorderLayout.CENTER);
        return squadronPanel;
    }

    private JPanel createCampaignOverviewPanel() {
        overviewPanel = new NewCampaignOverviewPanel();
        return overviewPanel;
    }

    private JPanel generateFactionTable(FactionSideType side) {
        // Load the list of factions for display
        List<FactionType> defaultFactionTypeList;
        if(side.equals(BLUEFOR)) {
            defaultFactionTypeList = Arrays.stream(FactionType.values()).filter((factionType) -> factionType.equals(FactionType.USA)).collect(Collectors.toList());
        } else {
            defaultFactionTypeList = Arrays.stream(FactionType.values()).filter((factionType) -> factionType.equals(FactionType.RUSSIA)).collect(Collectors.toList());
        }

        // Create the panel
        String[][] data = generateColumnData(defaultFactionTypeList);
        DefaultTableModel tableModel = new DefaultTableModel(data, FACTION_COLUMNS);
        JTable factionTable = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(factionTable.getModel());
        sorter.setSortsOnUpdates(true);
        factionTable.setRowSorter(sorter);
        factionTables.put(side, factionTable);

        // Create a scroll pane for the data and add it
        JScrollPane scrollPane = new JScrollPane(factionTable);

        JPanel factionPanel = new JPanel();
        factionPanel.setLayout(new BorderLayout());
        factionPanel.add(new JLabel(side.toString(), SwingConstants.CENTER), BorderLayout.NORTH);
        factionPanel.add(scrollPane, BorderLayout.CENTER);

        return factionPanel;
    }

    private JPanel generateButtonPanel() {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        MoveFactionActionListener buttonListener = new MoveFactionActionListener();
        List<JButton> buttons = new ArrayList<>();
        for(FactionMove move : FactionMove.values()) {
            JButton button = new JButton(move.getLabel());
            button.addActionListener(buttonListener);
            buttons.add(button);
        }

        buttonPanel.add(buttons.get(0), FlowLayout.LEFT);
        buttonPanel.add(buttons.get(1), FlowLayout.CENTER);
        buttonPanel.add(buttons.get(2), FlowLayout.RIGHT);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0));
        buttonContainer.add(buttonPanel, BorderLayout.SOUTH);
        return buttonContainer;
    }

    private JPanel generateNeutralFactionTable() {
        // Load the list of factions for display
        List<FactionType> defaultFactionTypeList = Arrays.stream(FactionType.values()).filter((factionType) -> factionType != FactionType.USA && factionType
                != FactionType.RUSSIA && factionType != FactionType.ALL).collect(Collectors.toList());

        // Create the panel
        String[][] data = generateColumnData(defaultFactionTypeList);
        DefaultTableModel tableModel = new DefaultTableModel(data, FACTION_COLUMNS);
        JTable neutralFactionTable = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(neutralFactionTable.getModel());
        sorter.setSortsOnUpdates(true);
        neutralFactionTable.setRowSorter(sorter);
        factionTables.put(NEUTRAL, neutralFactionTable);

        // Create a scroll pane for the data and add it
        JScrollPane scrollPane = new JScrollPane(neutralFactionTable);

        JPanel factionPanel = new JPanel();
        factionPanel.setLayout(new BorderLayout());
        factionPanel.add(new JLabel("Neutral", SwingConstants.CENTER), BorderLayout.NORTH);
        factionPanel.add(scrollPane, BorderLayout.CENTER);

        return factionPanel;
    }

    private String[][] generateColumnData(List<FactionType> factionTypeList) {
        String[][] data = new String[factionTypeList.size()][2];
        for(int i = 0; i < factionTypeList.size(); i++) {
            FactionType factionType = factionTypeList.get(i);
            data[i][0] = factionType.getDcsFactionName();
            data[i][1] = factionType.getOverallStrength().getFactionStrength();
        }
        return data;
    }

    public CampaignSettings getCampaignSettings() {
        return campaignSettings;
    }

    public enum FactionMove {
        LEFT("<<"),
        RETURN("Return to Neutral"),
        RIGHT(">>");

        private String label;

        FactionMove(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public static FactionMove fromName(String factionMove) {
            for(FactionMove faction : FactionMove.values()) {
                if(faction.getLabel().equalsIgnoreCase(factionMove)) {
                    return faction;
                }
            }
            return RETURN;
        }
    }

    private class PanelChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            // Update the overview panel
            overviewPanel.setSelectedFactions(campaignSettings.getCoalitionBySide(campaignSettings.getPlayerSelectedSide()));
            overviewPanel.setMapSelection(campaignSettings.getSelectedMap());
            overviewPanel.setSelectedEra(campaignSettings.getSelectedEra());
            overviewPanel.setCampaignType(campaignSettings.getSelectedCampaignType());
            overviewPanel.setSelectedSide(campaignSettings.getPlayerSelectedSide());
            overviewPanel.setSelectedSquadron(campaignSettings.getSelectedSquadron());
            overviewPanel.repaint();
        }
    }

    private class MapSelectionListener implements MouseListener {
        private List<MapSelectionPanel> watchedPanels;
        private MapSelectionPanel selectedPanel;

        @Override
        public void mouseClicked(MouseEvent e) {
            MapSelectionPanel clickedPanel = (MapSelectionPanel) e.getSource();
            if(clickedPanel == selectedPanel) {
                return;
            }

            selectedPanel = clickedPanel;

            // Show the selected panel by applying an outline
            Border padding = BorderFactory.createEmptyBorder(9, 9, 9, 9);
            Border outline = BorderFactory.createLoweredBevelBorder();
            selectedPanel.setBorder(BorderFactory.createCompoundBorder(padding, outline));

            // Remove the outlines from any other panels
            Border normalPadding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            watchedPanels.stream().filter((panel) -> panel != selectedPanel).forEach((panel) -> panel.setBorder(normalPadding));

            // Set our campaign setting for the map
            campaignSettings.setMapSelection(getSelectedMap());
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        void addWatchedPanel(JPanel panel) {
            if(watchedPanels == null) {
                watchedPanels = new ArrayList<>();
            }
            watchedPanels.add((MapSelectionPanel)panel);
        }

        MapType getSelectedMap() {
            if(selectedPanel != null) {
                return selectedPanel.getMap();
            }
            return MapType.CAUCASUS;
        }
    }

    private class MoveFactionActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Parse the direction of the movement
            NewCampaignPanel.FactionMove move = NewCampaignPanel.FactionMove.fromName(e.getActionCommand());

            // Check to see if the selection is coming from the Neutral Table
            int selectedRow =  factionTables.get(NEUTRAL).getSelectedRow();
            if(selectedRow != -1) {
                // Parse the row before we move it
                String selectedNeutralFaction = (String) factionTables.get(NEUTRAL).getValueAt(selectedRow, 0);
                FactionType parsedFactionType = FactionType.fromName(selectedNeutralFaction);

                // Move the row
                DefaultTableModel model = (DefaultTableModel) factionTables.get(NEUTRAL).getModel();
                model.removeRow(selectedRow);
                String[] data = {parsedFactionType.getDcsFactionName(), parsedFactionType.getOverallStrength().getFactionStrength()};
                switch (move) {
                    case LEFT:
                        ((DefaultTableModel) factionTables.get(FactionSideType.BLUEFOR).getModel()).addRow(data);
                        break;
                    case RIGHT:
                        ((DefaultTableModel) factionTables.get(FactionSideType.REDFOR).getModel()).addRow(data);
                        break;
                    case RETURN:
                    default:
                        break;
                }
            }

            // Otherwise check to see if it is one of the periphery tables
            JTable blueforSide = factionTables.get(FactionSideType.BLUEFOR);
            selectedRow =  blueforSide.getSelectedRow();
            if(selectedRow != -1) {
                // Parse the row before we move it
                String selectedFaction = (String) blueforSide.getValueAt(selectedRow, 0);
                FactionType parsedFactionType = FactionType.fromName(selectedFaction);

                // Move the row
                DefaultTableModel model = (DefaultTableModel) blueforSide.getModel();
                model.removeRow(selectedRow);
                String[] data = {parsedFactionType.getDcsFactionName(), parsedFactionType.getOverallStrength().getFactionStrength()};
                switch (move) {
                    case RIGHT:
                        ((DefaultTableModel) factionTables.get(FactionSideType.REDFOR).getModel()).addRow(data);
                        break;
                    case RETURN:
                        ((DefaultTableModel) factionTables.get(NEUTRAL).getModel()).addRow(data);
                        break;
                    case LEFT:
                    default:
                        break;
                }
            }

            // Otherwise check to see if it is one of the periphery tables
            JTable redforSide = factionTables.get(FactionSideType.REDFOR);
            selectedRow =  redforSide.getSelectedRow();
            if(selectedRow != -1) {
                // Parse the row before we move it
                String selectedFaction = (String) redforSide.getValueAt(selectedRow, 0);
                FactionType parsedFactionType = FactionType.fromName(selectedFaction);

                // Move the row
                DefaultTableModel model = (DefaultTableModel) redforSide.getModel();
                model.removeRow(selectedRow);
                String[] data = {parsedFactionType.getDcsFactionName(), parsedFactionType.getOverallStrength().getFactionStrength()};
                switch (move) {
                    case LEFT:
                        ((DefaultTableModel) factionTables.get(FactionSideType.BLUEFOR).getModel()).addRow(data);
                        break;
                    case RETURN:
                        ((DefaultTableModel) factionTables.get(NEUTRAL).getModel()).addRow(data);
                        break;
                    case RIGHT:
                    default:
                        break;
                }
            }

            // Set the settings for everything
            campaignSettings.setBlueforCoalition(new Coalition(getCoalitionFactions(BLUEFOR)));
            campaignSettings.setRedforCoalition(new Coalition(getCoalitionFactions(REDFOR)));
            campaignSettings.setNeutralCoalition(new Coalition(getCoalitionFactions(NEUTRAL)));
        }

        private List<FactionType> getCoalitionFactions(FactionSideType side) {
            JTable table = getFactionTable(side);
            List<FactionType> factionTypes = new ArrayList<>();
            for(int i = 0; i < table.getModel().getRowCount(); i++) {
                factionTypes.add(FactionType.fromName(table.getModel().getValueAt(i, 0).toString()));
            }
            return factionTypes;
        }

        JTable getFactionTable(FactionSideType side) {
            return factionTables.get(side);
        }
    }

    private class EraSelectionListener implements ActionListener {
        private JComboBox<String> comboBox;
        private JLabel descriptionLabel;
        private JPanel imagePanel;
        private JComboBox<String> campaignTypeBox;

        private static final int MAP_WIDTH = 550;

        EraSelectionListener(JComboBox<String> comboBox, JLabel descriptionLabel, JPanel imagePanel, JComboBox<String> campaignTypeBox) {
            this.comboBox = comboBox;
            this.descriptionLabel = descriptionLabel;
            this.imagePanel = imagePanel;
            this.campaignTypeBox = campaignTypeBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ConflictEraType selectedEra = ConflictEraType.fromName((String)comboBox.getSelectedItem());
            CampaignType selectedType = CampaignType.fromName((String)campaignTypeBox.getSelectedItem());
            campaignSettings.setSelectedEra(ConflictEraType.fromName((String)comboBox.getSelectedItem()));
            campaignSettings.setSelectedCampaignType(CampaignType.fromName((String)campaignTypeBox.getSelectedItem()));

            // Set the description
            descriptionLabel.setText("<html><body style='width:600px'>" + selectedEra.getEraDescription() + selectedType.getCharacteristics() + "</html>");

            // Set the image
            BufferedImage mapImage = tryLoadImage("/era/" + selectedEra.getEraName().replace(" ", "_") + ".jpg");
            Image scaled = mapImage.getScaledInstance(MAP_WIDTH * 2, (int)((MAP_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
            imagePanel.removeAll();
            imagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);
            imagePanel.repaint();
        }
    }

    private class CoalitionItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // Get the faction side that was selected, as well as the factions associated with that side
            FactionSideType side = FactionSideType.valueOf(((JRadioButton)e.getSource()).getText());
            Coalition coalition = campaignSettings.getCoalitionBySide(side);
            List<FactionType> coalitionFactionTypes = coalition.getFactionTypeList();

            // Go through the Factions and get the selectable squadrons
            List<SquadronType> validSquadrons = new ArrayList<>();
            for(SquadronType sqd : SquadronType.values()) {
                for(FactionType factionType : coalitionFactionTypes) {
                    if(sqd.getFactionTypeUser().equals(factionType) && sqd.getEra().contains(campaignSettings.getSelectedEra())) {
                        validSquadrons.add(sqd);
                    }
                }
            }
            validSquadrons.add(SquadronType.NONE);
            validSquadrons.add(SquadronType.NONE_HELICOPTER);

            // Assign the squadrons to the drop down box
            DefaultComboBoxModel model = (DefaultComboBoxModel)squadronBox.getModel();
            model.removeAllElements();
            for(SquadronType squadronType : validSquadrons) {
                model.addElement(squadronType.getSquadronName());
            }
            squadronBox.setModel(model);

            // Set the side
            campaignSettings.setPlayerSelectedSide(side);
        }
    }

    private class SquadronSelectionListener implements ActionListener {
        private JPanel squadronImagePanel;
        private JLabel squadronName;
        private JLabel squadronTasks;
        private JLabel squadronAircraft;
        private JLabel squadronActiveEras;

        private static final int IMG_WIDTH = 550;

        SquadronSelectionListener(JPanel squadronImagePanel, JLabel squadronName, JLabel squadronTasks, JLabel squadronAircraft, JLabel squadronActiveEras) {
            this.squadronImagePanel = squadronImagePanel;
            this.squadronName = squadronName;
            this.squadronTasks = squadronTasks;
            this.squadronAircraft = squadronAircraft;
            this.squadronActiveEras = squadronActiveEras;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SquadronType selectedSquadron = SquadronType.fromName((String)squadronBox.getSelectedItem());
            campaignSettings.setSelectedSquadron(selectedSquadron);

            if(selectedSquadron != null) {
                BufferedImage mapImage = tryLoadImage("/squadron/" + selectedSquadron.name().replace(" ", "_") + ".jpg");
                Image scaled = mapImage.getScaledInstance(IMG_WIDTH * 2, (int) ((IMG_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
                squadronImagePanel.removeAll();
                squadronImagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

                // Update the squadron status
                squadronName.setText(selectedSquadron.getSquadronName());
                squadronTasks.setText(selectedSquadron.getTaskList().stream().map(SubTaskType::getTaskName).collect(Collectors.joining(", ")));
                squadronAircraft.setText(selectedSquadron.getAircraftTypes().stream().map(AircraftType::getAircraftName).collect(Collectors.joining(", ")));
                squadronActiveEras.setText(selectedSquadron.getEra().stream().map(ConflictEraType::getEraName).collect(Collectors.joining(", ")));
            }
            squadronImagePanel.repaint();
        }
    }

    private class NewCampaignOverviewPanel extends JPanel {
        private final String[] labels = {"Map Selected: ", "Campaign Era Selected: ", "Campaign Type Selected: ", "Selected Coalition: ", "Selected Squadron: ", "Squadron Tasks: ", "Squadron Aircraft: ", "Era Aircraft: ", "Selected Aircraft: "};
        private Map<String, JLabel> labelMapping;
        private JComboBox<String> aircraftSelector;
        private Coalition selectedFactions;

        NewCampaignOverviewPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Main Campaign Data
            JPanel container = new JPanel();
            container.setLayout(new GridLayout(9, 2, 10, 6));
            labelMapping = new HashMap<>();
            for(int i = 0; i < labels.length - 1; i++ ) {
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT);
                JLabel data = new JLabel();
                data.setPreferredSize(new Dimension(50, (int)data.getPreferredSize().getHeight()));
                label.setLabelFor(data);
                container.add(label);
                container.add(data);
                labelMapping.put(labels[i], data);
            }
            container.add(new JLabel(labels[labels.length - 1], SwingConstants.RIGHT));
            aircraftSelector = new JComboBox<>();
            aircraftSelector.setPrototypeDisplayValue("text here");
            container.add(aircraftSelector);

            // Campaign Start Button
            JPanel containerPanel = new JPanel();
            JButton startCampaignButton = new JButton("Start Campaign");
            containerPanel.add(startCampaignButton);
            startCampaignButton.addActionListener((e) -> {
                campaignSettings.setSelectedAircraft(AircraftType.fromName((String)aircraftSelector.getSelectedItem()));
                campaignSettings.setComplete(true);
                (SwingUtilities.getWindowAncestor(self)).dispose();
            });

            add(container);
            add(containerPanel);
        }

        public void setMapSelection(GameMap selectedMap) {
            labelMapping.get(labels[0]).setText(selectedMap.getMapName());
        }

        public void setSelectedEra(ConflictEraType selectedEra) {
            labelMapping.get(labels[1]).setText(selectedEra.getEraName());
        }

        public void setCampaignType(CampaignType selectedCampaignType) {
            labelMapping.get(labels[2]).setText(selectedCampaignType.getCampaignTypeName());
        }

        public void setSelectedSide(FactionSideType playerSelectedSide) {
            labelMapping.get(labels[3]).setText(playerSelectedSide.name());
        }

        public void setSelectedSquadron(SquadronType selectedSquadron) {
            labelMapping.get(labels[4]).setText(selectedSquadron.name());
            setSelectedSquadronTask(selectedSquadron.getTaskList());
            setSquardronAircraft(selectedSquadron.getAircraftTypes());
            List<AircraftType> eraCraft = setEraAircraft(selectedSquadron.getAircraftTypes());
            setSelectedAircraft(eraCraft);
        }

        private void setSelectedSquadronTask(List<SubTaskType> squadronTaskTypes) {
            String tasks = squadronTaskTypes.stream().map(SubTaskType::getTaskName).collect(Collectors.joining(", "));
            labelMapping.get(labels[5]).setText(tasks);
        }

        private void setSquardronAircraft(List<AircraftType> aircraft) {
            String tasks = aircraft.stream().map(AircraftType::getAircraftName).collect(Collectors.joining(", "));
            labelMapping.get(labels[6]).setText(tasks);
        }

        private List<AircraftType> setEraAircraft(List<AircraftType> aircraft) {
            // Filter based on Era
            ConflictEraType era = ConflictEraType.fromName(labelMapping.get(labels[1]).getText());
            List<FactionType> factionTypes = selectedFactions.getFactionTypeList();
            List<AircraftType> eraCraft = new ArrayList<>();
            if(era != null) {
                eraCraft = aircraft.stream().filter((craft) -> craft.getAircraftEras().contains(era)).collect(Collectors.toList());
            }

            // Filter based on Faction
            List<AircraftType> removed = new ArrayList<>();
            for(AircraftType aircraftType : eraCraft) {
                boolean foundMatch = false;
                for(FactionType type : factionTypes) {
                    if(aircraftType.getUsers().contains(type)) {
                        foundMatch = true;
                        break;
                    }
                }

                if(!foundMatch) {
                    removed.add(aircraftType);
                }
            }
            eraCraft.removeAll(removed);

            if(eraCraft.isEmpty()) {
                labelMapping.get(labels[7]).setText("Please choose another Era or Set of Factions");
            } else {
                labelMapping.get(labels[7]).setText(eraCraft.stream().map(AircraftType::getAircraftName).collect(Collectors.joining(", ")));
            }

            return eraCraft;
        }

        private void setSelectedAircraft(List<AircraftType> selectedAircraft) {
            if(selectedAircraft.isEmpty()) {
                aircraftSelector.removeAllItems();
                aircraftSelector.addItem("Please choose another Era or Set of Factions");
            } else {
                aircraftSelector.removeAllItems();
                aircraftSelector.setModel(new DefaultComboBoxModel<>(selectedAircraft.stream().filter(AircraftType::isPlayerFlyable).map(AircraftType::getAircraftName).toArray(String[]::new)));
            }
            campaignSettings.setSelectedAircraft(AircraftType.fromName((String)aircraftSelector.getSelectedItem()));
        }

        public void setSelectedFactions(Coalition selectedFactions) {
            this.selectedFactions = selectedFactions;
        }
    }
}
