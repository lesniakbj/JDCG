package ui.containers;

import ui.constants.FileActions;
import ui.constants.UIAction;
import ui.constants.ViewActions;
import ui.listeners.MenuItemListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;

public class JDCGUIFrame extends JFrame {
    // Singleton for the Main GUI Frame
    private static JDCGUIFrame instance;

    // Main GUI Elements
    private static JMenuBar mainMenuBar;
    private static ActionListener menuItemListener;

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
        mainMenuBar = new JMenuBar();
        menuItemListener = new MenuItemListener();

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
}
