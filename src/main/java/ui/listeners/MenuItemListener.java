package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import sim.main.CampaignState;
import ui.constants.FileActions;
import ui.constants.UIAction;
import ui.constants.ViewActions;
import ui.containers.NewCampaignPanel;

public class MenuItemListener implements ActionListener {
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
                        CampaignState.clearState();
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
