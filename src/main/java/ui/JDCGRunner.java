package ui;

import ui.containers.JDCGUIFrame;

import javax.swing.*;

public class JDCGRunner {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // Parse any arguments...

        // Start the GUI portion of the program in it's own thread
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        JDCGUIFrame mainUIFrame = JDCGUIFrame.getInstance();
        mainUIFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
