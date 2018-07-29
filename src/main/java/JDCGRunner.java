import sim.campaign.DynamicCampaignSim;
import sim.settings.CampaignSettings;
import sim.settings.GlobalSimSettings;
import ui.containers.JDCGUIFrame;

import javax.swing.SwingUtilities;

public class JDCGRunner {
    public static void main(String[] args) {
        // Parse any arguments...
        boolean useGUI = false;
        if(args == null || args.length == 0) {
            useGUI = true;
        }

        // Start the GUI
        if(useGUI) {
            SwingUtilities.invokeLater(JDCGUIFrame::getInstance);
        } else {
            CampaignSettings campaignSettings = new CampaignSettings();
            GlobalSimSettings simSettings = new GlobalSimSettings();
            DynamicCampaignSim sim = new DynamicCampaignSim();

            // Get the settings


            // Set the settings
            sim.setSimSettings(simSettings);
            sim.setCampaignSettings(campaignSettings);

            // Generate and loop
            sim.generateNewCampaign();
            while(!sim.isSimRunning()) {
                // Step the sim
                sim.stepSimulation();

                // Respond to the stepped simulation
            }
        }
    }
}
