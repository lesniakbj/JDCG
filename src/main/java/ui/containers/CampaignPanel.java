package ui.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.GameMap;
import sim.domain.Mission;
import sim.domain.enums.AirfieldType;
import sim.main.DynamicCampaignSim;
import sim.util.MathUtil;
import ui.util.DrawUtil;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static ui.util.ImageScaleUtil.MAP_IMAGE_HEIGHT_RATIO;
import static ui.util.ImageScaleUtil.tryLoadImage;

class CampaignPanel extends JPanel {
    private static final Logger log = LogManager.getLogger(CampaignPanel.class);

    // Displays
    private JPanel campaignActions;
    private JPanel campaignImage;
    private JPanel campaignStatus;
    private JPanel campaignPlannedActions;

    // Data
    private JDCGUIFrame hostFrame;
    private JLabel campaignDateLabel;
    private JLabel campaignSortiesLabel;
    private JLabel campaignTargetsLabel;
    private JLabel campaignObjectivesLabel;
    private List<ActiveMissionPanel> campaignActiveMissions;


    // Background Sim
    private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> scheduledFuture;
    private boolean simRunning;

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
        BufferedImage mapImage = tryLoadImage("/map/" + campaign.getCampaignSettings().getSelectedMap().getMapName().replace(" ", "_") + "_map.png");
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
        for(Mission mission : campaign.getCampaignMissionManager().getActiveMissions()) {
            ActiveMissionPanel sampleMissionPanel = new ActiveMissionPanel(campaign, mission);
            sampleMissionPanel.addMouseListener(new ActiveMissionClickListener());
            missionPanel.add(sampleMissionPanel);
            campaignActiveMissions.add(sampleMissionPanel);
        }
        campaignPlannedActions.add(missionPanel, BorderLayout.CENTER);

        // Load the actions we can do to those missions
        JPanel missionActionButtonPanel = new JPanel();
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
        missionActionButtonPanel.add(clearMissionButton);
        missionActionButtonPanel.add(new JButton("Recall Flight"));
        campaignPlannedActions.add(missionActionButtonPanel, BorderLayout.SOUTH);
        campaignPlannedActions.repaint();
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
        campaignDateLabel = new JLabel(String.format("Date: %s", campaign.getCurrentCampaignDate()));
        campaignSortiesLabel = new JLabel(String.format("Active Sorties: %d", campaign.getCampaignMissionManager().getActiveMissions().size()));
        campaignTargetsLabel = new JLabel(String.format("Priority Targets: %d", campaign.getCampaignObjectiveManager().getMainObjectiveList().size()));
        campaignObjectivesLabel = new JLabel(String.format("Critical Objectives Remaining: %d", campaign.getCampaignObjectiveManager().getMainObjectiveList().size()));
        campaignStatus.add(Box.createHorizontalGlue());
        campaignStatus.add(campaignDateLabel);
        campaignStatus.add(Box.createHorizontalGlue());
        campaignStatus.add(new JSeparator(SwingConstants.VERTICAL));
        campaignStatus.add(campaignSortiesLabel);
        campaignStatus.add(Box.createHorizontalGlue());
        campaignStatus.add(new JSeparator(SwingConstants.VERTICAL));
        campaignStatus.add(campaignTargetsLabel);
        campaignStatus.add(Box.createHorizontalGlue());
        campaignStatus.add(new JSeparator(SwingConstants.VERTICAL));
        campaignStatus.add(campaignObjectivesLabel);
        campaignStatus.add(Box.createHorizontalGlue());
        campaignStatus.add(new JSeparator(SwingConstants.VERTICAL));
        JPanel buttonContainer = new JPanel();
        JButton stepSimButton = new JButton("Step Simulation");
        stepSimButton.addActionListener(l -> {
            campaign.stepSimulation();
            updateSimulationGUI(imageWidth, imageHeight, padding, bevel);
        });
        JButton runSim = new JButton("Run Simulation");
        runSim.addActionListener(l -> {
            simRunning = true;
            scheduledFuture = exec.scheduleAtFixedRate((Runnable) () -> {
                log.debug("Running task...");
                log.debug(simRunning);
                if(simRunning) {
                    campaign.stepSimulation();
                    updateSimulationGUI(imageWidth, imageHeight, padding, bevel);
                }
            }, 0, 500, TimeUnit.MILLISECONDS);
        });
        JButton stopSim = new JButton("Stop Simulation");
        stopSim.addActionListener(l -> {
            simRunning = false;
            scheduledFuture.cancel(false);
        });
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonContainer.add(stepSimButton);
        buttonContainer.add(runSim);
        buttonContainer.add(stopSim);
        campaignStatus.add(buttonContainer);
        campaignStatus.add(Box.createHorizontalGlue());
    }

    private void updateSimulationGUI(int imageWidth, int imageHeight, Border padding, Border bevel) {
        // Refresh the UI
        loadCampaignImage(imageWidth, imageHeight, padding, bevel);
        hostFrame.refreshUiElements();
        updateCampaignStatusLabels();
        hostFrame.refreshUiElements();
        loadActiveMissions(imageWidth, imageHeight, padding, bevel);
        hostFrame.refreshUiElements();
        loadCampaignActions(padding, bevel);
        hostFrame.refreshUiElements();
    }

    private void updateCampaignStatusLabels() {
        campaignDateLabel.setText(String.format("Date: %s", campaign.getCurrentCampaignDate()));
        campaignSortiesLabel.setText(String.format("Active Sorties: %d", campaign.getCampaignMissionManager().getActiveMissions().size()));
        campaignTargetsLabel.setText(String.format("Priority Targets: %d", campaign.getCampaignObjectiveManager().getMainObjectiveList().size()));
        campaignObjectivesLabel.setText(String.format("Critical Objectives Remaining: %d", campaign.getCampaignObjectiveManager().getMainObjectiveList().size()));
    }


    private BufferedImage addCampaignObjects(BufferedImage image) {
        Graphics2D g = (Graphics2D)image.getGraphics();

        // First draw any of the missions
        DrawUtil.setNormalStroke(g.getStroke());
        DrawUtil.drawCampaignAirbases(campaign, g);
        DrawUtil.drawWarfareFront(campaign, g);
        DrawUtil.drawCampaignUnitGroups(campaign, g);
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
            Point2D.Double destPair = AirfieldType.LAUGHLIN_AIRPORT.getAirfieldMapPosition();
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