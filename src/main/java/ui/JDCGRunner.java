package ui;

import javax.swing.SwingUtilities;
import ui.containers.JDCGUIFrame;

public class JDCGRunner {
    public static void main(String[] args) {
        // Parse any arguments...

        // Start the GUI
        SwingUtilities.invokeLater(JDCGUIFrame::getInstance);
    }
}
