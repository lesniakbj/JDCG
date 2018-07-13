package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import ui.containers.JDCGUIFrame;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class JDCGRunner {
    public static void main(String[] args) {
        // Parse any arguments...

        // Start the GUI portion of the program in it's own thread
        JDCGUIFrame mainUIFrame = JDCGUIFrame.getInstance();
    }
}
