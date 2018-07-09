package ui.containers;

import sim.domain.statics.CampaignType;
import sim.domain.statics.ConflictEra;
import sim.domain.statics.Faction;
import sim.domain.statics.FactionSide;
import sim.domain.statics.MapConstants;
import ui.listeners.EraSelectionListener;
import ui.listeners.MapSelectionListener;
import ui.listeners.MoveFactionActionListener;
import ui.listeners.PanelChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;
import static ui.util.ImageScaleUtil.NORMAL_IMAGE_RATIO;
import static ui.util.ImageScaleUtil.tryLoadImage;

public class NewCampaignPanel extends JPanel {
    private static MapSelectionListener mapSelectionListener;
    private static MoveFactionActionListener buttonListener;
    private static EraSelectionListener eraSelectionListener;

    private static final int MAP_WIDTH = 550;
    private static final String[] FACTION_COLUMNS = {"Faction Name", "Faction Strength"};

    public NewCampaignPanel() {
        this.setLayout(new BorderLayout());

        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Map", createMapPanel());
        pane.addTab("Factions", createFactionsPanel());
        pane.addTab("Era", createEraPanel());
        pane.addTab("Squadron", createSquadronPanel());
        pane.addTab("Campaign Overview", new JPanel());

        // Create the pane action listener to save state on changes
        pane.addChangeListener(new PanelChangeListener(mapSelectionListener, buttonListener, eraSelectionListener));

        this.add(pane, BorderLayout.NORTH);
    }

    private Component createSquadronPanel() {
        return new JPanel();
    }

    private JPanel createEraPanel() {
        JPanel eraPanel = new JPanel();
        eraPanel.setLayout(new BorderLayout());

        // Create the image that is associated with the selected JComboBox item
        JPanel eraImagePanel = new JPanel();
        BufferedImage mapImage = tryLoadImage("/" + ConflictEra.MODERN.getEraName().replace(" ", "_") + ".jpg");
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
            BufferedImage mapImage = tryLoadImage("/" + map.getMapName().replace(" ", "_") + "_map.png");
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
        JPanel bluforPanel = generateFactionTable(FactionSide.BLUFOR);
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
        List<Faction> defaultFactionList = List.of(Faction.values());

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
