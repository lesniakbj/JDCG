package ui;

import ui.containers.JDCGUIFrame;

public class JDCGRunner {
    public static void main(String[] args) {
        // Parse any arguments...

        // Start the GUI portion of the program in it's own thread
        JDCGUIFrame mainUIFrame = JDCGUIFrame.getInstance();
    }
}
