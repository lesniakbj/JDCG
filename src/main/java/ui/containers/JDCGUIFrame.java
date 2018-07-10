package ui.containers;

import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import sim.main.CampaignSettings;
import ui.constants.FileActions;
import ui.constants.UIAction;
import ui.constants.ViewActions;

public class JDCGUIFrame extends JFrame {
    // Singleton for the Main GUI Frame
    private static JDCGUIFrame instance;

    // Main GUI Constants
    private static final int WIDTH = 1600;
    private static final int PADDING = 200;

    private JDCGUIFrame() {
        // Init local elements
        initLocalElements();
        this.pack();

        // Set the general characteristics of the frame
        this.setBackground(Color.GRAY);
        this.setTitle("JDCG - Java DCS Campaign Generator");
        this.setSize(WIDTH + PADDING, ((int) (WIDTH * MAP_IMAGE_HEIGHT_RATIO)) + PADDING);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initLocalElements() {
        // Set the main layout of this frame
        this.setLayout(new BorderLayout());
        addMainMenu();

        this.add(new JLabel("Please Open or Start a New Campaign...", SwingConstants.CENTER), BorderLayout.CENTER);
    }

    private void addMainMenu() {
        JMenuBar mainMenuBar = new JMenuBar();
        ActionListener menuItemListener = new MenuItemListener();

        // Menus
        mainMenuBar.add(constructMenu("File", FileActions.values(), menuItemListener));
        mainMenuBar.add(constructMenu("View", ViewActions.values(), menuItemListener));

        this.setJMenuBar(mainMenuBar);
    }

    private JMenu constructMenu(String menuLabel, UIAction[] values, ActionListener listener) {
        JMenu menu = new JMenu(menuLabel);
        for(UIAction action : values) {
            if(!action.toString().equalsIgnoreCase("NONE")) {
                JMenuItem item = new JMenuItem(action.getUIName());
                item.addActionListener(listener);
                menu.add(item);
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
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCmd = e.getActionCommand();

            // Handle File Menu Items
            FileActions fileAction = (FileActions) tryParseAction(actionCmd, FileActions.values(), FileActions.NONE);
            switch (fileAction) {
                case NEW:
                    JDialog newDialog = createNewCampaignDialogPanel();
                    newDialog.setTitle("New Campaign Setup");
                    newDialog.setResizable(false);
                    newDialog.setLocationRelativeTo(null);
                    newDialog.setModal(true);
                    newDialog.setVisible(true);
                    CampaignSettings settings = ((NewCampaignPanel)newDialog.getContentPane().getComponent(0)).getCampaignSettings();

                    // If the settings are complete, we can proceed with populating the main portions of the frame
                    if(settings.isComplete()) {

                    }
                case OPEN:
                case OPEN_RECENT:
                case EXIT:
                    break;
            }


            // Handle View Action Items
            ViewActions viewAction = (ViewActions) tryParseAction(actionCmd, ViewActions.values(), ViewActions.NONE);
            switch (viewAction) {
                case AIRFIELD_LIST:
                case PILOT_LIST:
                case SQUADRON_LIST:
                    break;
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

        private UIAction tryParseAction(String actionCommand, UIAction[] values, UIAction defaultValue) {
            return UIAction.fromUIName(actionCommand, values, defaultValue);
        }
    }
}
