package ui.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.List;

public class ActiveMissionPanel extends JPanel {
    private static final Logger log = LogManager.getLogger(ActiveMissionPanel.class);

    private Mission plannedMission;
    private boolean isSelected = false;

    private static final Border DEFAULT_BORDER = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createRaisedBevelBorder());

    public ActiveMissionPanel(DynamicCampaignSim campaign, Mission plannedMission) {
        // Set the layout and the border, if the mission was selected, indicate that
        this.plannedMission = plannedMission;
        setLayout(new BorderLayout());
        setBorder(DEFAULT_BORDER);

        if(plannedMission.equals(campaign.getCurrentlySelectedMission())) {
            setSelected();
        }

        JPanel topHalfContainer = new JPanel(new BorderLayout());
        JPanel topHalf = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel topHalf2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topHalf.add(new JLabel("Type: " + plannedMission.getMissionType().getTaskName()));
        List<AirUnit> aircraftList = plannedMission.getMissionAircraft().getGroupUnits();
        topHalf2.add(new JLabel("Aircraft: " + aircraftList.get(0).getAircraftType().getAircraftName() + "(" + aircraftList.size() + ")"));
        topHalfContainer.add(topHalf, BorderLayout.NORTH);
        topHalfContainer.add(topHalf2, BorderLayout.CENTER);


        JPanel bottomHalf = new JPanel(new FlowLayout(FlowLayout.CENTER));
        SimpleDateFormat sdf = new SimpleDateFormat(campaign.getCampaignSettings().getDateFormat());
        bottomHalf.add(new JLabel("Date: " + sdf.format(plannedMission.getPlannedMissionDate())));


        JPanel clientMissionPanel = new JPanel();
        clientMissionPanel.setLayout(new BoxLayout(clientMissionPanel, BoxLayout.X_AXIS));
        clientMissionPanel.add(Box.createHorizontalGlue());
        clientMissionPanel.add(new ClientComponent());

        setPreferredSize(new Dimension(300, 98));
        setMaximumSize(new Dimension(300, 98));
        add(topHalfContainer, BorderLayout.NORTH);
        add(bottomHalf, BorderLayout.CENTER);
        add(clientMissionPanel, BorderLayout.SOUTH);
    }

    public void setSelected() {
        setBorder(BorderFactory.createCompoundBorder(DEFAULT_BORDER, BorderFactory.createLineBorder(Color.GREEN)));
        isSelected = true;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Mission getPlannedMission() {
        return plannedMission;
    }

    public void unselect() {
        setBorder(DEFAULT_BORDER);
        isSelected = false;
    }

    public void setClientMission(boolean clientMission) {
        plannedMission.setClientMission(clientMission);

        int totalPlanes = plannedMission.getMissionAircraft().getGroupUnits().size();
        if(totalPlanes > 1) {
            String total = JOptionPane.showInputDialog(String.format("Which slot would you like to occupy?: 1 - %d Available", totalPlanes));
            int parsed = Integer.parseInt(total);
            plannedMission.setPlayerAircraft(parsed);
        } else {
            plannedMission.setPlayerAircraft(1);
        }
    }

    public boolean isClientMission() {
        return plannedMission.isClientMission();
    }

    private class ClientComponent extends JComponent {
        ClientComponent() {
            setPreferredSize(new Dimension(10, 10));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if(plannedMission.isClientMission()) {
                int planeNum = plannedMission.getPlayerAircraft();
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setColor(Color.RED);
                graphics2D.fillRect(0, 0, 10, 10);
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString(String.format("%d", planeNum), 2, 10);
                graphics2D.dispose();
            }
        }
    }
}
