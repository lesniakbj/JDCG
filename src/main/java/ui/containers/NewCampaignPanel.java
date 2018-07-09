package ui.containers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sim.domain.Coalition;
import sim.domain.GameMap;
import sim.domain.statics.*;
import ui.listeners.CoalitionItemListener;
import ui.listeners.EraSelectionListener;
import ui.listeners.MapSelectionListener;
import ui.listeners.MoveFactionActionListener;
import ui.listeners.PanelChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import ui.listeners.SquadronSelectionListener;

import static ui.util.ImageScaleUtil.*;

public class NewCampaignPanel extends JPanel {
    private static MapSelectionListener mapSelectionListener;
    private static MoveFactionActionListener buttonListener;
    private static EraSelectionListener eraSelectionListener;
    private static CoalitionItemListener coalitionSelectionListener;
    private static SquadronSelectionListener squadronSelectionListener;
    private static NewCampaignOverviewPanel overviewPanel;

    // Static data that is updated by the panels

    private static final int MAP_WIDTH = 550;
    private static final String[] FACTION_COLUMNS = {"Faction Name", "Faction Strength"};

    public NewCampaignPanel() {
        // Create the layout
        this.setLayout(new BorderLayout());

        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Map", createMapPanel());
        pane.addTab("Factions", createFactionsPanel());
        pane.addTab("Era", createEraPanel());
        pane.addTab("Squadron", createSquadronPanel());
        pane.addTab("Campaign Overview", createCampaignOverviewPanel());

        // Create the pane action listener to save state on changes
        pane.addChangeListener(new PanelChangeListener(mapSelectionListener, buttonListener, eraSelectionListener, coalitionSelectionListener, squadronSelectionListener, overviewPanel));

        this.add(pane, BorderLayout.NORTH);
    }

    private JPanel createCampaignOverviewPanel() {
        overviewPanel = new NewCampaignOverviewPanel();
        return overviewPanel;
    }

    private Component createSquadronPanel() {
        JPanel squadronPanel = new JPanel();
        squadronPanel.setLayout(new BorderLayout());

        // Create the image that is associated with the selected JComboBox item
        JPanel squadronImagePanel = new JPanel();
        BufferedImage mapImage = tryLoadImage("/squadron/" + SquadronType.NONE.getSquadronName().replace(" ", "_") + ".png");
        Image scaled = mapImage.getScaledInstance(MAP_WIDTH * 2, (int)((MAP_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
        squadronImagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

        // Create the JComboBox of Squadrons
        JPanel squadronBoxPanel = new JPanel();
        JComboBox<String> squadronBox = new JComboBox<>(Stream.of(SquadronType.values()).map(SquadronType::getSquadronName).toArray(String[]::new));
        squadronBoxPanel.add(squadronBox);

        // Add the Squadron Selection Listener
        squadronSelectionListener = new SquadronSelectionListener(squadronBox, squadronImagePanel);
        squadronBox.addActionListener(squadronSelectionListener);

        // Create the RadioButtons of Team (Bluefor/Redfor)
        coalitionSelectionListener = new CoalitionItemListener(squadronBox);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JRadioButton blueforButton = new JRadioButton("BLUEFOR");
        JRadioButton redforButton = new JRadioButton("REDFOR");
        blueforButton.addItemListener(coalitionSelectionListener);
        redforButton.addItemListener(coalitionSelectionListener);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(blueforButton);
        buttonGroup.add(redforButton);
        buttonPanel.add(blueforButton);
        buttonPanel.add(redforButton);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(buttonPanel, BorderLayout.NORTH);
        containerPanel.add(squadronBoxPanel, BorderLayout.CENTER);

        squadronPanel.add(squadronImagePanel, BorderLayout.NORTH);
        squadronPanel.add(containerPanel, BorderLayout.CENTER);
        return squadronPanel;
    }

    private JPanel createEraPanel() {
        JPanel eraPanel = new JPanel();
        eraPanel.setLayout(new BorderLayout());

        // Create the image that is associated with the selected JComboBox item
        JPanel eraImagePanel = new JPanel();
        BufferedImage mapImage = tryLoadImage("/era/" + ConflictEra.MODERN.getEraName().replace(" ", "_") + ".jpg");
        Image scaled = mapImage.getScaledInstance(MAP_WIDTH * 2, (int)((MAP_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
        eraImagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);

        // Create the JComboBox of Eras
        JPanel boxPanel = new JPanel();
        JComboBox<String> eraBox = new JComboBox<>(Stream.of(ConflictEra.values()).map(ConflictEra::getEraName).toArray(String[]::new));
        JComboBox<String> campaignType = new JComboBox<>(Stream.of(CampaignType.values()).map(CampaignType::getCampaignTypeName).toArray(String[]::new));
        boxPanel.add(eraBox);
        boxPanel.add(campaignType);

        // Create the associated label
        JPanel labelPanel = new JPanel();
        JLabel label = new JLabel("<html><body style='width:600px'>" + ConflictEra.MODERN.getEraDescription() + CampaignType.OFFENSIVE.getCharacteristics() + "</html>", SwingConstants.CENTER);
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 200, 0));
        labelPanel.add(label);

        // Add action listener
        eraSelectionListener = new EraSelectionListener(eraBox, label, eraImagePanel, campaignType);
        eraBox.addActionListener(eraSelectionListener);
        campaignType.addActionListener(eraSelectionListener);

        eraPanel.add(eraImagePanel, BorderLayout.NORTH);
        eraPanel.add(boxPanel, BorderLayout.CENTER);
        eraPanel.add(labelPanel, BorderLayout.SOUTH);
        return eraPanel;
    }

    private JPanel createMapPanel() {
        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(400, 400));
        mapPanel.setLayout(new GridLayout(2, 2));

        // For each map, create a panel and add it to the map panel
        mapSelectionListener = new MapSelectionListener();
        for(MapConstants map : MapConstants.values()) {
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
        // Create the container panel and layout
        JPanel factionContainer = new JPanel();
        factionContainer.setLayout(new BorderLayout());

        // Create the button panel
        JPanel buttonPanel = generateButtonPanel();

        // Create the default faction panel
        JPanel factionPanel = generateNeutralFactionTable();

        // Create the Left and Right (Blufor/Opfor) panel
        JPanel bluforPanel = generateFactionTable(FactionSide.BLUEFOR);
        JPanel redforPanel = generateFactionTable(FactionSide.REDFOR);

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

    private JPanel generateFactionTable(FactionSide side) {
        // Create the panel
        String[][] data = new String[0][0];
        DefaultTableModel tableModel = new DefaultTableModel(data, FACTION_COLUMNS);
        JTable factionTable = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(factionTable.getModel());
        sorter.setSortsOnUpdates(true);
        factionTable.setRowSorter(sorter);
        buttonListener.setFactionTable(factionTable, side);

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

        buttonListener = new MoveFactionActionListener();
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
        List<Faction> defaultFactionList = Arrays.asList(Faction.values());

        // Create the panel
        String[][] data = generateColumnData(defaultFactionList);
        DefaultTableModel tableModel = new DefaultTableModel(data, FACTION_COLUMNS);
        JTable neutralFactionTable = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(neutralFactionTable.getModel());
        sorter.setSortsOnUpdates(true);
        neutralFactionTable.setRowSorter(sorter);
        buttonListener.setNeutralFactionTable(neutralFactionTable);

        // Create a scroll pane for the data and add it
        JScrollPane scrollPane = new JScrollPane(neutralFactionTable);

        JPanel factionPanel = new JPanel();
        factionPanel.setLayout(new BorderLayout());
        factionPanel.add(new JLabel("Neutral", SwingConstants.CENTER), BorderLayout.NORTH);
        factionPanel.add(scrollPane, BorderLayout.CENTER);

        return factionPanel;
    }

    private String[][] generateColumnData(List<Faction> factionList) {
        String[][] data = new String[factionList.size()][2];
        for(int i = 0; i < factionList.size(); i++) {
            Faction faction = factionList.get(i);
            data[i][0] = faction.getDcsFactionName();
            data[i][1] = faction.getOverallStrength().getFactionStrength();
        }
        return data;
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
            return null;
        }
    }
}
