package ui.containers;

import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;
import static ui.util.ImageScaleUtil.tryLoadImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import gen.domain.Airfield;
import gen.domain.GameMap;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaign;
import sim.save.JSONUtil;
import ui.constants.FileActions;
import ui.constants.MissionActions;
import ui.constants.UIAction;
import ui.constants.CoalitionActions;

public class JDCGUIFrame extends JFrame {
    // Singleton for the Main GUI Frame
    private static JDCGUIFrame instance;

    // Main GUI Constants
    private static final String SAVE_PATH = System.getProperty("user.home") + "\\Saved Games\\Java DCS Campaign Generator";
    private static final String RECENT_SAVE_FILE = "\\recentsaves.txt";
    private static final int WIDTH = 1600;
    private static final int PADDING = 200;

    // Variables used for frame calculation
    private int calculatedWidth = WIDTH + PADDING;
    private int calculatedHeight = ((int) (WIDTH * MAP_IMAGE_HEIGHT_RATIO)) + PADDING;

    // Other GUI Components
    private Set<String> recentSaves;
    private JMenu recentSavesMenu;
    private JPanel campaignWindow;

    // Main Campaign State
    private DynamicCampaign campaign;

    private JDCGUIFrame() {
        // Init local elements
        initLocalElements();
        pack();

        // Set the general characteristics of the frame
        setBackground(Color.GRAY);
        setTitle("JDCG - Java DCS Campaign Generator");
        setSize(calculatedWidth, calculatedHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initLocalElements() {
        // Load the settings file to see what our last recent saves were
        recentSaves = new HashSet<>();
        loadRecentSaves();
        
        // Set the main layout of this frame
        setLayout(new BorderLayout());
        addMainMenu();

        campaignWindow = new CampaignPanel();
        add(campaignWindow, BorderLayout.CENTER);
    }

    private void loadRecentSaves() {
        // Verify the path exists
        Path path = Paths.get(SAVE_PATH);
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ignored) {}
        }

        // Check for the recent saves file
        if(!Files.exists(Paths.get(SAVE_PATH + RECENT_SAVE_FILE))) {
            return;
        }

        // Read the file, and save it in the list
        try (Stream<String> stream = Files.lines(Paths.get(SAVE_PATH + RECENT_SAVE_FILE))) {
            recentSaves = stream.collect(Collectors.toSet());
        } catch (IOException ignored) {}
    }

    private void addMainMenu() {
        JMenuBar mainMenuBar = new JMenuBar();
        ActionListener menuItemListener = new MenuItemListener();

        // Menus
        mainMenuBar.add(constructMenu("File", FileActions.values(), menuItemListener));
        mainMenuBar.add(constructMenu("Coalition", CoalitionActions.values(), menuItemListener));
        mainMenuBar.add(constructMenu("Mission", MissionActions.values(), menuItemListener));

        setJMenuBar(mainMenuBar);
    }

    private JMenu constructMenu(String menuLabel, UIAction[] values, ActionListener listener) {
        JMenu menu = new JMenu(menuLabel);
        for(UIAction action : values) {
            if(!(action.toString().equalsIgnoreCase("NONE") || action.toString().equalsIgnoreCase("OPEN_RECENT"))) {
                JMenuItem item = new JMenuItem(action.getUIName());
                item.addActionListener(listener);
                menu.add(item);
            }

            if (action.toString().equalsIgnoreCase("OPEN_RECENT")) {
                recentSavesMenu = new JMenu(action.getUIName());

                // Load the recent saves into the menu here, and parse it for the menu
                for(String recentSave : recentSaves) {
                    JMenuItem menuItem = new JMenuItem(recentSave);
                    recentSavesMenu.add(menuItem);
                }

                menu.add(recentSavesMenu);
            }

            // Add a separator if we are supposed to
            if(action.hasSeparator(action)) {
                menu.addSeparator();
            }
        }
        return menu;
    }

    public static JDCGUIFrame getInstance() {
        if(instance == null) {
            instance = new JDCGUIFrame();
        }
        return instance;
    }

    private class MenuItemListener implements ActionListener {
        private JFileChooser fileChooser = new JFileChooser(SAVE_PATH);

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCmd = e.getActionCommand();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Java DCS Campaign Files (*.jdcg)", "jdcg"));

            // Handle File Menu Items
            FileActions fileAction = (FileActions) tryParseAction(actionCmd, FileActions.values(), FileActions.NONE);
            switch (fileAction) {
                case NEW:
                    handleNewCampaignMenu();
                    break;
                case OPEN:
                    handleOpenCampaignMenu();
                    break;
                case OPEN_RECENT:
                    break;
                case SAVE:
                    handleSaveCampaignMenu();
                    break;
                case EXIT:
                    break;
            }

            // Handle View Action Items
            CoalitionActions coalitionAction = (CoalitionActions) tryParseAction(actionCmd, CoalitionActions.values(), CoalitionActions.NONE);
            switch (coalitionAction) {
                case AIRFIELDS:
                case MUNITIONS:
                case SQUADRONS:
                case PILOTS:
                case AIRCRAFT:
                    break;
            }


            // Handle View Action Items
            MissionActions missionAction = (MissionActions) tryParseAction(actionCmd, MissionActions.values(), MissionActions.NONE);
            switch (missionAction) {
                case GOALS:
                case MISSION_PLANNER:
                    break;
            }
        }

        private void handleNewCampaignMenu() {
            JDialog newDialog = createNewCampaignDialogPanel();
            newDialog.setTitle("New Campaign Setup");
            newDialog.setResizable(false);
            newDialog.setLocationRelativeTo(null);
            newDialog.setModal(true);
            newDialog.setVisible(true);
            CampaignSettings settings = ((NewCampaignPanel)newDialog.getContentPane().getComponent(0)).getCampaignSettings();

            // If the settings are complete, we can proceed with populating the main portions of the frame
            if(settings.isComplete()) {
                // Create a campaign with the parsed settings
                campaign = new DynamicCampaign();
                campaign.setCampaignSettings(settings);

                instance.remove(campaignWindow);
                campaignWindow = new CampaignPanel(settings);
                instance.add(campaignWindow, BorderLayout.CENTER);
                instance.pack();
                instance.repaint();
            }
        }

        private JDialog createNewCampaignDialogPanel() {
            JDialog newCampaignDialog = new JDialog();

            JPanel panel = new NewCampaignPanel();
            newCampaignDialog.add(panel);
            newCampaignDialog.pack();
            newCampaignDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            newCampaignDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to leave Campaign Creation without saving?");
                    switch(option) {
                        case JOptionPane.OK_OPTION:
                            newCampaignDialog.dispose();
                            break;
                        case JOptionPane.NO_OPTION:
                        case JOptionPane.CANCEL_OPTION:
                            break;
                    }
                }
            });

            return newCampaignDialog;
        }

        private void handleOpenCampaignMenu() {
            int openValue = fileChooser.showOpenDialog(instance);
            if(openValue == JFileChooser.APPROVE_OPTION) {
                File chosenFile = fileChooser.getSelectedFile();
                try {
                    DynamicCampaign loadedCampaign = JSONUtil.fromJson(new String(Files.readAllBytes(chosenFile.toPath())), DynamicCampaign.class);
                    if(loadedCampaign == null) {
                        JOptionPane.showMessageDialog(fileChooser, "Error attempting to load file! Please try again.", "File Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Add this recent open to the list, and re-save the list
                    recentSaves.add(chosenFile.getAbsolutePath());
                    Files.write(Paths.get(SAVE_PATH + RECENT_SAVE_FILE), recentSaves.stream().collect(Collectors.joining("\n")).getBytes());
                    recentSavesMenu.removeAll();
                    for(String recentSave : recentSaves) {
                        JMenuItem menuItem = new JMenuItem(recentSave);
                        recentSavesMenu.add(menuItem);
                    }

                    // Reload all UI elements
                    campaign = loadedCampaign;
                    instance.remove(campaignWindow);
                    campaignWindow = new CampaignPanel(campaign.getCampaignSettings());
                    instance.add(campaignWindow, BorderLayout.CENTER);
                    instance.pack();
                    instance.repaint();
                    JOptionPane.showMessageDialog(fileChooser, "Campaign has been successfully loaded!");
                }catch (IOException ignored) {}
            }
        }

        private void handleSaveCampaignMenu() {
            String json = JSONUtil.fromDomain(campaign);
            int saveValue = fileChooser.showSaveDialog(instance);
            if(saveValue == JFileChooser.APPROVE_OPTION) {
                File savedFile = fileChooser.getSelectedFile();
                try {
                    String filePath = savedFile.getAbsolutePath();
                    if(!filePath.contains(".jdcg")) {
                        savedFile = new File(savedFile.getAbsolutePath() + ".jdcg");
                    }
                    Files.write(savedFile.toPath(), json.getBytes());

                    // Add this recent save to the list, and re-save the list
                    recentSaves.add(savedFile.getAbsolutePath());
                    Files.write(Paths.get(SAVE_PATH + RECENT_SAVE_FILE), recentSaves.stream().collect(Collectors.joining("\n")).getBytes());
                    recentSavesMenu.removeAll();
                    for(String recentSave : recentSaves) {
                        JMenuItem menuItem = new JMenuItem(recentSave);
                        recentSavesMenu.add(menuItem);
                    }

                    JOptionPane.showMessageDialog(fileChooser, "Campaign has been successfully saved!");
                } catch (IOException ignored) {}
            }
        }

        private UIAction tryParseAction(String actionCommand, UIAction[] values, UIAction defaultValue) {
            return UIAction.fromUIName(actionCommand, values, defaultValue);
        }
    }

    private class CampaignPanel extends JPanel {
        // Displays
        private JPanel campaignActions;
        private JPanel campaignImage;
        private JPanel campaignStatus;
        private JPanel campaignPlannedActions;

        // Settings
        private CampaignSettings campaignSettings;

        CampaignPanel() {
            setLayout(new BorderLayout());
            setSize(calculatedWidth, calculatedHeight);
            add(new JLabel("Please open or start a new campaign...", SwingConstants.CENTER), BorderLayout.CENTER);
        }

        CampaignPanel(CampaignSettings campaignSettings) {
            setLayout(new BorderLayout());
            setSize(calculatedWidth, calculatedHeight);
            this.campaignSettings = campaignSettings;

            // Create the panel that will hold the image used as the campaign map
            Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            Border bevel = BorderFactory.createLoweredBevelBorder();
            campaignImage = new JPanel();
            BufferedImage mapImage = tryLoadImage("/map/" + campaignSettings.getSelectedMap().getMapName().replace(" ", "_") + "_map.png");
            int imageWidth = calculatedWidth - 350;
            int imageHeight = (int) (imageWidth * MAP_IMAGE_HEIGHT_RATIO);
            Image scaled = mapImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            JLabel campaignImageLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
            campaignImageLabel.addMouseListener(new MapClickListener());
            campaignImage.add(campaignImageLabel, BorderLayout.CENTER);
            campaignImage.setBorder(BorderFactory.createCompoundBorder(padding, bevel));


            // Create the panel that will hold the actions that can be done the campaign
            campaignActions = new JPanel();
            campaignActions.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
            campaignActions.add(new JLabel("Player Actions (New Mission, View Missions, Etc..)", SwingConstants.LEFT));

            // Create the panel that will show everything that is currently in progress
            campaignPlannedActions = new JPanel();
            campaignPlannedActions.setSize(calculatedWidth - imageWidth, imageHeight);
            campaignPlannedActions.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
            campaignPlannedActions.add(new JLabel("This will be the generated missions each plane is attempting...)", SwingConstants.LEFT));

            // Create the panel that will show the campaign status
            campaignStatus = new JPanel();
            campaignStatus.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
            campaignStatus.add(new JLabel("This will show status of the campaign, such as date, strength, etc...", SwingConstants.LEFT));

            add(campaignActions, BorderLayout.NORTH);
            add(campaignImage, BorderLayout.WEST);
            add(campaignPlannedActions, BorderLayout.EAST);
            add(campaignStatus, BorderLayout.SOUTH);
        }

        private class MapClickListener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // Determine what was clicked
                //      1) Check Airfields First
                //      2) Check Aircraft Second
                //      3) Check Ground Units Third
                //      4) Return a list of ALL found
                GameMap map = campaignSettings.getSelectedMap();
                List<Airfield> airfields = map.getAirfields();
                List<Airfield> clickedAirfields = new ArrayList<>();
                for(Airfield airfield : airfields) {
                    double x = airfield.getAirfieldMapPosition().getKey();
                    double y = airfield.getAirfieldMapPosition().getValue();
                    if(isWithinThreshold(mouseX, mouseY, x, y, 10)) {
                        clickedAirfields.add(airfield);
                    }
                }

                System.out.println("Total clicked objects: " + clickedAirfields.size());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            private boolean isWithinThreshold(int mouseX, int mouseY, double destX, double destY, int threshold) {
                boolean xIsValid = (mouseX >= destX - threshold && mouseX <= destX + threshold);
                boolean yIsValid = (mouseY >= destY - threshold && mouseY <= destY + threshold);
                return xIsValid && yIsValid;
            }
        }
    }
}
