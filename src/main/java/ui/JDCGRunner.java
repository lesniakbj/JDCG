package ui;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import ui.containers.JDCGUIFrame;

public class JDCGRunner {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // Parse any arguments...

        // Start the GUI portion of the program in it's own thread
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        JDCGUIFrame mainUIFrame = JDCGUIFrame.getInstance();
        mainUIFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
