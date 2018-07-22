package ui.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;
import sim.main.GlobalSimSettings;
import sim.save.JSONUtil;
import ui.constants.CoalitionActions;
import ui.constants.FileActions;
import ui.constants.InfoActions;
import ui.constants.MissionActions;
import ui.constants.UIAction;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;

public class JDCGUIFrame extends JFrame {
    private static final Logger log = LogManager.getLogger(JDCGUIFrame.class);

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
    private DynamicCampaignSim campaign;
    private File saveFile;

    private JDCGUIFrame() {
        // Init local elements
        initLocalElements();
        pack();

        // Setup the window closing listener
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to leave the Campaign without saving?");
                log.debug(option);
                switch(option) {
                    case JOptionPane.OK_OPTION:
                        dispose();
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            }
        });

        // Set the general characteristics of the frame
        setBackground(Color.GRAY);
        setTitle("JDCG - Java DCS Campaign Generator");
        setPreferredSize(new Dimension(calculatedWidth, calculatedHeight));
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

        campaignWindow = new CampaignPanel(this);
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
        mainMenuBar.add(constructMenu("Info", InfoActions.values(), menuItemListener));

        setJMenuBar(mainMenuBar);
    }

    private JMenu constructMenu(String menuLabel, UIAction[] values, ActionListener listener) {
        JMenu menu = new JMenu(menuLabel);
        RecentSaveMouseListener mouseListener = new RecentSaveMouseListener();
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
                    menuItem.addActionListener(mouseListener);
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

    void refreshUiElements() {
        // instance.pack();
        instance.validate();
        instance.repaint();
    }

    int getCalculatedWidth() {
        return calculatedWidth;
    }

    int getCalculatedHeight() {
        return calculatedHeight;
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
                case SAVE:
                    handleSaveCampaignMenu();
                    break;
                case SAVE_AS:
                    handleSaveAsCampaignMenu();
                    break;
                case EXIT:
                    handleExitCampaignMenu();
                    break;
            }

            // Handle Coalition Action Items
            CoalitionActions coalitionAction = (CoalitionActions) tryParseAction(actionCmd, CoalitionActions.values(), CoalitionActions.NONE);
            switch (coalitionAction) {
                case AIRFIELDS:
                case MUNITIONS:
                case SQUADRONS:
                case PILOTS:
                case AIRCRAFT:
                    break;
            }

            // Handle Mission Action Items
            MissionActions missionAction = (MissionActions) tryParseAction(actionCmd, MissionActions.values(), MissionActions.NONE);
            switch (missionAction) {
                case MISSION_PLANNER:
                    break;
            }

            // Handle Info Action Items
            InfoActions infoAction = (InfoActions) tryParseAction(actionCmd, InfoActions.values(), InfoActions.NONE);
            switch (infoAction) {
                case GOALS:
                    break;
                case PREFERENCES:
                    handleCampaignSimulationPreferencesMenu();
                    break;
            }
        }

        private void handleExitCampaignMenu() {
            int selected = JOptionPane.showConfirmDialog(fileChooser, "Are you sure you would like to exit without saving?");
            switch (selected) {
                case JOptionPane.OK_OPTION:
                    instance.dispose();
                    break;
                case JOptionPane.NO_OPTION:
                    if(saveFile != null) {
                        handleSaveCampaignMenu();
                    } else {
                        handleSaveAsCampaignMenu();
                    }
                    instance.dispose();
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
            }
        }

        private void handleCampaignSimulationPreferencesMenu() {
            if(campaign == null) {
                log.debug("Campaing is null, abort..");
                JOptionPane.showMessageDialog(null, "Please start a campaign before adjusting Simulation Settings!");
                return;
            }

            JDialog newDialog = createCampaignSimulationPreferencesPanel();
            newDialog.setTitle("Global Simulation Settings");
            newDialog.setResizable(false);
            newDialog.setLocationRelativeTo(null);
            newDialog.setModal(true);
            newDialog.setVisible(true);
            GlobalSimSettings settings = ((SimSettingsPanel)newDialog.getContentPane().getComponent(0)).getSimulationSettings();

            // If the settings are complete, we can proceed with populating the main portions of the frame
            campaign.setSimSettings(settings);
        }

        private JDialog createCampaignSimulationPreferencesPanel() {
            JDialog simPreferencesDialog = new JDialog();

            JPanel panel = new SimSettingsPanel();
            simPreferencesDialog.add(panel);
            simPreferencesDialog.pack();
            simPreferencesDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            return simPreferencesDialog;
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
                campaign = new DynamicCampaignSim();
                campaign.setCampaignSettings(settings);
                campaign.generateNewCampaign();

                instance.remove(campaignWindow);
                campaignWindow = new CampaignPanel(instance, campaign);
                instance.add(campaignWindow, BorderLayout.CENTER);
                refreshUiElements();
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
                saveFile = fileChooser.getSelectedFile();
                try {
                    DynamicCampaignSim loadedCampaign = JSONUtil.fromJson(new String(Files.readAllBytes(saveFile.toPath())), DynamicCampaignSim.class);
                    if(loadedCampaign == null) {
                        JOptionPane.showMessageDialog(fileChooser, "Error attempting to load file! Please try again.", "File Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    saveRecentSavesFile();

                    // Reload all UI elements
                    campaign = loadedCampaign;
                    instance.remove(campaignWindow);
                    campaignWindow = new CampaignPanel(instance, campaign);
                    instance.add(campaignWindow, BorderLayout.CENTER);
                    refreshUiElements();
                    JOptionPane.showMessageDialog(fileChooser, "Campaign has been successfully loaded!");
                }catch (IOException ignored) {}
            }
        }

        private void handleSaveAsCampaignMenu() {
            int saveValue = fileChooser.showSaveDialog(instance);
            if(saveValue == JFileChooser.APPROVE_OPTION) {
                saveFile = fileChooser.getSelectedFile();
                saveCampaign();
                saveRecentSavesFile();
                JOptionPane.showMessageDialog(fileChooser, "Campaign has been successfully saved!");
            }
        }

        private void handleSaveCampaignMenu() {
            if(saveFile == null) {
                handleSaveAsCampaignMenu();
                return;
            }

            saveCampaign();
            saveRecentSavesFile();
            JOptionPane.showMessageDialog(fileChooser, "Campaign has been successfully saved!");
        }

        private void saveCampaign() {
            if(saveFile != null) {
                String json = JSONUtil.fromDomain(campaign);
                String filePath = saveFile.getAbsolutePath();

                if (!filePath.contains(".jdcg")) {
                    saveFile = new File(saveFile.getAbsolutePath() + ".jdcg");
                }

                try {
                    Files.write(saveFile.toPath(), json.getBytes());
                } catch (IOException ignored) {}
            }
        }

        private void saveRecentSavesFile() {
            // Add this recent save to the list, and re-save the list
            RecentSaveMouseListener mouseListener = new RecentSaveMouseListener();
            if(saveFile != null) {
                recentSaves.add(saveFile.getAbsolutePath());

                try {
                    Files.write(Paths.get(SAVE_PATH + RECENT_SAVE_FILE), recentSaves.stream().collect(Collectors.joining("\n")).getBytes());
                } catch (IOException ignored) {}

                recentSavesMenu.removeAll();
                for (String recentSave : recentSaves) {
                    JMenuItem menuItem = new JMenuItem(recentSave);
                    menuItem.addActionListener(mouseListener);
                    recentSavesMenu.add(menuItem);
                }
            }
        }

        private UIAction tryParseAction(String actionCommand, UIAction[] values, UIAction defaultValue) {
            return UIAction.fromUIName(actionCommand, values, defaultValue);
        }
    }

    private class RecentSaveMouseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DynamicCampaignSim loadedCampaign = null;
            try {
                saveFile = new File(e.getActionCommand());
                loadedCampaign = JSONUtil.fromJson(new String(Files.readAllBytes(saveFile.toPath())), DynamicCampaignSim.class);
            } catch (IOException ignored) {}

            if(loadedCampaign == null) {
                JOptionPane.showMessageDialog(instance, "Error attempting to load file! Please try again.", "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Reload all UI elements
            campaign = loadedCampaign;
            instance.remove(campaignWindow);
            campaignWindow = new CampaignPanel(instance, campaign);
            instance.add(campaignWindow, BorderLayout.CENTER);
            refreshUiElements();
            JOptionPane.showMessageDialog(instance, "Campaign has been successfully loaded!");
        }
    }
}
