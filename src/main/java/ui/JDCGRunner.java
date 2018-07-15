package ui;

import ui.containers.JDCGUIFrame;

import javax.swing.SwingUtilities;

public class JDCGRunner {
    public static void main(String[] args) {
        // Parse any arguments...

        // Start the GUI
        SwingUtilities.invokeLater(JDCGUIFrame::getInstance);
    }
}
