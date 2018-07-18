package ui.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.GameMap;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.MunitionType;
import sim.main.CoalitionManager;
import sim.main.DynamicCampaignSim;
import sim.util.MathUtil;
import ui.util.DrawUtil;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;
import static ui.util.ImageScaleUtil.tryLoadImage;

public class CampaignPanel extends JPanel {
    private static final Logger log = LogManager.getLogger(CampaignPanel.class);

    // Displays
    private static BufferedImage mapImage;
    private JPanel campaignActions;
    private JPanel campaignImage;
    private JPanel campaignStatus;
    private JPanel campaignPlannedActions;

    // Data
    private JDCGUIFrame hostFrame;
    private JButton joinFlightButton;
    private JLabel campaignDateLabel;
    private JLabel campaignSortiesLabel;
    private JLabel campaignEnemyStatusLabel;
    private JLabel campaignFriendlyStatusLabel;
    private JLabel campaignObjectivesLabel;
    private List<ActiveMissionPanel> campaignActiveMissions;

    // Settings
    private DynamicCampaignSim campaign;

    CampaignPanel(JDCGUIFrame hostFrame, DynamicCampaignSim campaign) {
        this.hostFrame = hostFrame;
        campaignActiveMissions = new ArrayList<>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(hostFrame.getCalculatedWidth(), hostFrame.getCalculatedHeight()));
        this.campaign = campaign;

        // Create the panel that will hold the image used as the campaign map
        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border bevel = BorderFactory.createLoweredBevelBorder();
        int imageWidth = hostFrame.getCalculatedWidth() - 350;
        int imageHeight = (int) (imageWidth * MAP_IMAGE_HEIGHT_RATIO);

        // Create the panel that will hold the actions that can be done the campaign
        campaignActions = new JPanel(new BorderLayout());
        loadCampaignActions(padding, bevel);

        // Create the panel that will show everything that is currently in progress
        campaignPlannedActions = new JPanel(new BorderLayout());
        loadActiveMissions(imageWidth, imageHeight, padding, bevel);

        // Create the panel that will show the campaign status
        campaignStatus = new JPanel();
        loadCampaignStatusPanel(imageWidth, imageHeight, padding, bevel);

        // Load the image (this includes all campaign objects)
        campaignImage = new JPanel(new BorderLayout());
        loadCampaignImage(imageWidth, imageHeight, padding, bevel);

        // Add everything that was loaded to the panel
        add(campaignActions, BorderLayout.NORTH);
        add(campaignImage, BorderLayout.WEST);
        add(campaignPlannedActions, BorderLayout.EAST);
        add(campaignStatus, BorderLayout.SOUTH);
    }

    CampaignPanel(JDCGUIFrame hostFrame) {
        this.hostFrame = hostFrame;
        campaignActiveMissions = new ArrayList<>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(hostFrame.getCalculatedWidth(), hostFrame.getCalculatedHeight()));
    }

    private void loadCampaignImage(int imageWidth, int imageHeight, Border padding, Border bevel) {
        // Load the image, and alter it with any of the campaign entities
        campaignImage.removeAll();
        mapImage = tryLoadImage("/map/" + campaign.getCampaignSettings().getSelectedMap().getMapName().replace(" ", "_") + "_map.png");
        mapImage = addCampaignObjects(mapImage);
        Image scaled = mapImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        JLabel campaignImageLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
        campaignImageLabel.setVerticalAlignment(JLabel.CENTER);
        campaignImageLabel.addMouseListener(new MapClickListener());
        campaignImage.add(campaignImageLabel, BorderLayout.CENTER);
        campaignImage.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
    }

    private void loadActiveMissions(int imageWidth, int imageHeight, Border padding, Border bevel) {
        // Load all of the current missions
        campaignPlannedActions.removeAll();
        campaignPlannedActions.setPreferredSize(new Dimension(hostFrame.getCalculatedWidth() - imageWidth, imageHeight));
        campaignPlannedActions.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
        campaignPlannedActions.add(new JLabel("<html><u>Active Missions</u></html>", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel missionPanel = new JPanel();
        List<Mission> sorted = campaign.getCampaignMissionManager().getPlannedMissions().stream().sorted(Comparator.comparing(Mission::getPlannedMissionDate)).collect(Collectors.toList());
        for(Mission mission : sorted) {
            ActiveMissionPanel sampleMissionPanel = new ActiveMissionPanel(campaign, mission);
            sampleMissionPanel.addMouseListener(new ActiveMissionClickListener());
            missionPanel.add(sampleMissionPanel);
            campaignActiveMissions.add(sampleMissionPanel);
        }
        campaignPlannedActions.add(missionPanel, BorderLayout.CENTER);

        // Load the actions we can do to those missions
        JPanel missionActionButtonPanel = new JPanel(new BorderLayout());
        JPanel topHalf = new JPanel();
        JPanel bottomHalf = new JPanel();
        if(joinFlightButton == null) {
            joinFlightButton = new JButton("Join");
        } else {
            joinFlightButton = new JButton(joinFlightButton.getText());
        }
        joinFlightButton.addActionListener(l -> {
            ActiveMissionPanel clientMission = null;
            if(joinFlightButton.getText().equalsIgnoreCase("Join")) {
                for (ActiveMissionPanel mission : campaignActiveMissions) {
                    if (mission.isSelected()) {
                        clientMission = mission;
                    }
                }

                if(clientMission == null) {
                    return;
                }

                clientMission.setClientMission(true);
                joinFlightButton.setText("Leave");
            } else {
                for (ActiveMissionPanel mission : campaignActiveMissions) {
                    if (mission.isClientMission()) {
                        clientMission = mission;
                    }
                }

                if(clientMission == null) {
                    return;
                }

                clientMission.setClientMission(false);
                joinFlightButton.setText("Join");
            }

            loadCampaignImage(imageWidth, imageHeight, padding, bevel);
            hostFrame.refreshUiElements();
        });
        JButton recallFlight = new JButton("Recall");
        recallFlight.addActionListener(l -> {
            log.debug("I should tell the campaign manager I want to recall");
        });
        JButton flightLoadout = new JButton("Loadout");
        flightLoadout.addActionListener(l -> {
            log.debug("I should tell the campaign manager I want to alter this flight's loadout");
            if(campaign.getCurrentlySelectedMission() == null) {
                JOptionPane.showMessageDialog(this, "You must select a flight to adjust loadouts");
                return;
            }
            JDialog dialog = createFlightLoadoutPanel();
            dialog.setTitle("Flight Loadout Setup");
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
            Map<Integer,MunitionType> loadout = ((FlightLoadoutPanel)dialog.getContentPane().getComponent(0)).getFlightLoadout();
            if(loadout == null) {
                log.debug("No loadout!");
            }
            campaign.getCurrentlySelectedMission().setMissionMunitions(loadout);
        });
        JButton clearMissionButton = new JButton("Clear Selection");
        clearMissionButton.addActionListener(l -> {
            for(ActiveMissionPanel mission : campaignActiveMissions) {
                if(mission.isSelected()) {
                    mission.unselect();
                }
            }
            campaign.setCurrentlySelectedMission(null);
            loadCampaignImage(imageWidth, imageHeight, padding, bevel);
            hostFrame.refreshUiElements();
        });
        topHalf.add(joinFlightButton);
        topHalf.add(recallFlight);
        topHalf.add(flightLoadout);
        bottomHalf.add(clearMissionButton);
        missionActionButtonPanel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        missionActionButtonPanel.add(topHalf, BorderLayout.CENTER);
        missionActionButtonPanel.add(bottomHalf, BorderLayout.SOUTH);
        campaignPlannedActions.add(missionActionButtonPanel, BorderLayout.SOUTH);
        campaignPlannedActions.repaint();
    }

    private JDialog createFlightLoadoutPanel() {
        JDialog flightLoadoutDialog = new JDialog();

        JPanel panel = new FlightLoadoutPanel(campaign.getCurrentlySelectedMission(), flightLoadoutDialog);
        flightLoadoutDialog.add(panel);
        flightLoadoutDialog.pack();

        return flightLoadoutDialog;
    }

    private void loadCampaignActions(Border padding, Border bevel) {
        // Load the actions that we can perform during the campaign
        campaignActions.removeAll();
        campaignActions.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
        JPanel buttonPanel = new JPanel();
        JButton planMissionButton = new JButton("Mission Planner");
        JButton someOtherAction = new JButton("Some Other Action");
        buttonPanel.add(planMissionButton);
        buttonPanel.add(someOtherAction);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        campaignActions.add(buttonPanel, BorderLayout.WEST);
    }

    private void loadCampaignStatusPanel(int imageWidth, int imageHeight, Border padding, Border bevel)  {
        campaignStatus.setLayout(new BoxLayout(campaignStatus, BoxLayout.X_AXIS));
        campaignStatus.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
        SimpleDateFormat sdf = new SimpleDateFormat(campaign.getCampaignSettings().getDateFormat());
        campaignDateLabel = new JLabel(String.format("Date: %s", sdf.format(campaign.getCurrentCampaignDate())));
        campaignSortiesLabel = new JLabel(String.format("Planned Sorties: %d", campaign.getCampaignMissionManager().getPlannedMissions().size()));
        long groundTargets = getTotalGroundUnits(campaign.getRedforCoalitionManager());
        long groundUnits = getTotalGroundUnits(campaign.getBlueforCoalitionManager());
        long airDefences = 0;
        long aircraft = 0;
        campaignEnemyStatusLabel = new JLabel(String.format("Enemy Status: %d/%d/%d", groundTargets, airDefences, aircraft));
        campaignFriendlyStatusLabel = new JLabel(String.format("Friendly Status: %d/%d/%d", groundUnits, airDefences, aircraft));
        campaignObjectivesLabel = new JLabel(String.format("Critical Objectives Remaining: %d", campaign.getCampaignObjectiveManager().getMainObjectiveList().size()));
        campaignStatus.add(Box.createHorizontalGlue());
        addHorizontalComponent(campaignDateLabel);
        addHorizontalComponent(campaignSortiesLabel);
        addHorizontalComponent(campaignEnemyStatusLabel);
        addHorizontalComponent(campaignFriendlyStatusLabel);
        addHorizontalComponent(campaignObjectivesLabel);
        JPanel buttonContainer = new JPanel();
        JButton stepSimButton = new JButton("Step Simulation");
        stepSimButton.addActionListener(l -> {
            campaign.stepSimulation();
            updateSimulationGUI(imageWidth, imageHeight, padding, bevel);
        });
        JButton runSim = new JButton("Run Simulation");
        runSim.addActionListener(l -> {
            if(campaign.isSimRunning()) {
                campaign.setSimRunning(false);
                runSim.setText("Run Simulation");
            } else {
                campaign.setSimRunning(true);
                campaign.runSimulation(this, imageWidth, imageHeight, padding, bevel);
                runSim.setText("Stop Simulation");
            }
        });
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonContainer.add(stepSimButton);
        buttonContainer.add(runSim);
        campaignStatus.add(buttonContainer);
        campaignStatus.add(Box.createHorizontalGlue());
    }

    private void addHorizontalComponent(JComponent component) {
        campaignStatus.add(component);
        campaignStatus.add(Box.createHorizontalGlue());
        campaignStatus.add(new JSeparator(SwingConstants.VERTICAL));
    }

    public void updateSimulationGUI(int imageWidth, int imageHeight, Border padding, Border bevel) {
        // Refresh the UI
        loadCampaignImage(imageWidth, imageHeight, padding, bevel);
        hostFrame.refreshUiElements();
        updateCampaignStatusLabels();
        loadActiveMissions(imageWidth, imageHeight, padding, bevel);
        loadCampaignActions(padding, bevel);
        hostFrame.refreshUiElements();
    }

    private void updateCampaignStatusLabels() {
        // Date
        SimpleDateFormat sdf = new SimpleDateFormat(campaign.getCampaignSettings().getDateFormat());
        campaignDateLabel.setText(String.format("Date: %s", sdf.format(campaign.getCurrentCampaignDate())));

        // Planned missions
        campaignSortiesLabel.setText(String.format("Panned Sorties: %d", campaign.getCampaignMissionManager().getPlannedMissions().size()));

        // Campaign status
        long enemyCount = getTotalGroundUnits(campaign.getRedforCoalitionManager());
        long count = getTotalGroundUnits(campaign.getBlueforCoalitionManager());
        long airDefences = 0;
        long aircraft = 0;
        campaignEnemyStatusLabel.setText(String.format("Enemy Status: %d/%d/%d", enemyCount, airDefences, aircraft));
        campaignFriendlyStatusLabel.setText(String.format("Friendly Status: %d/%d/%d", count, airDefences, aircraft));

        // Critical Objectives
        campaignObjectivesLabel.setText(String.format("Critical Objectives Remaining: %d", campaign.getCampaignObjectiveManager().getMainObjectiveList().size()));
    }

    private long getTotalGroundUnits(CoalitionManager manager) {
        return manager.getCoalitionPointDefenceGroundUnits().entrySet().stream()
                        .map(Map.Entry::getValue)
                        .flatMap(Collection::stream)
                        .map(UnitGroup::getNumberOfUnits)
                        .mapToLong(i -> i).sum();
    }


    private BufferedImage addCampaignObjects(BufferedImage image) {
        Graphics2D g = (Graphics2D)image.getGraphics();

        // First draw any of the missions
        DrawUtil.setNormalStroke(g.getStroke());
        DrawUtil.drawCampaignUnitGroups(campaign, g);
        DrawUtil.drawCampaignAirbases(campaign, g);
        DrawUtil.drawWarfareFront(campaign, g);
        DrawUtil.drawCampaignSelectedMission(campaign, g);
        DrawUtil.drawActiveMissions(campaign, g);

        g.dispose();
        return image;
    }

    private class MapClickListener implements MouseListener {
        private final Logger log = LogManager.getLogger(MapClickListener.class);

        @Override
        public void mouseClicked(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            log.debug("Mouse Click: " + mouseX + " " + mouseY);

            // Determine what was clicked
            //      1) Check Airfields First
            //      2) Check Aircraft Second
            //      3) Check Ground Units Third
            //      4) Return a list of ALL found
            GameMap map = campaign.getCampaignSettings().getSelectedMap();
            List<AirfieldType> airfieldTypes = map.getAirfieldTypes();
            List<AirfieldType> clickedAirfieldTypes = new ArrayList<>();
            for(AirfieldType airfieldType : airfieldTypes) {
                double x = airfieldType.getAirfieldMapPosition().getX();
                double y = airfieldType.getAirfieldMapPosition().getY();
                if(isWithinThreshold(mouseX, mouseY, x, y, 15)) {
                    clickedAirfieldTypes.add(airfieldType);
                }
            }

            // For debug, get the distance between where we clicked (if on an object), and where the AbuNair airfield is
            for(AirfieldType airfieldType : clickedAirfieldTypes) {
                debugDistancesAndAngle(airfieldType);
            }
        }

        private void debugDistancesAndAngle(AirfieldType airfieldType) {
            Point2D.Double destPair = AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition();
            Point2D.Double sourcePair = airfieldType.getAirfieldMapPosition();
            double dist = MathUtil.getDistance(destPair, sourcePair);
            double angle = MathUtil.getAngleNorthFace(destPair, sourcePair);
            log.debug(String.format("Distance from %s to AL_DHAFRA_AIRBASE: %f px, %f mi, %f deg", airfieldType.name(), dist, airfieldType.getMap().scaleDistance(dist), angle));
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        private boolean isWithinThreshold(int mouseX, int mouseY, double destX, double destY, int threshold) {
            boolean xIsValid = (mouseX >= destX - threshold && mouseX <= destX + threshold);
            boolean yIsValid = (mouseY >= destY - threshold && mouseY <= destY + threshold);
            return xIsValid && yIsValid;
        }
    }

    private class ActiveMissionClickListener implements MouseListener {
        private final Logger log = LogManager.getLogger(MapClickListener.class);

        @Override
        public void mouseClicked(MouseEvent e) {
            ActiveMissionPanel panel = (ActiveMissionPanel) e.getSource();
            campaign.setCurrentlySelectedMission(panel.getPlannedMission());

            // Deactivate other panels
            for(ActiveMissionPanel mission : campaignActiveMissions) {
                if(mission.isSelected()) {
                    mission.unselect();
                }
            }

            // Indicate that we selected a mission with an outline
            if(!panel.isSelected()) {
                panel.setSelected();
            }

            // Since we selected a mission, reload the image panel
            Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            Border bevel = BorderFactory.createLoweredBevelBorder();
            int imageWidth = hostFrame.getCalculatedWidth() - 350;
            int imageHeight = (int) (imageWidth * MAP_IMAGE_HEIGHT_RATIO);
            loadCampaignImage(imageWidth, imageHeight, padding, bevel);
            hostFrame.refreshUiElements();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}