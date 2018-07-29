package ui.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AirfieldType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.WeaponStation;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.global.GameMap;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.manager.CoalitionManager;
import sun.awt.ScrollPaneWheelScroller;
import ui.containers.menu.FlightLoadoutPanel;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
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
    private JScrollPane scrollMissionPanel;
    private int yMissionScroll;
    private List<ActiveMissionPanel> campaignActiveMissions;
    private boolean drawThreatGrid;

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
        loadCampaignActions(imageWidth, imageHeight, padding, bevel);

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
        missionPanel.setLayout(new BoxLayout(missionPanel, BoxLayout.Y_AXIS));
        List<Mission> sorted = campaign.getCampaignMissionManager().getPlannedMissions().stream().sorted(Comparator.comparing(Mission::getPlannedMissionDate)).collect(Collectors.toList());
        for(Mission mission : sorted) {
            ActiveMissionPanel sampleMissionPanel = new ActiveMissionPanel(campaign, mission);
            sampleMissionPanel.addMouseListener(new ActiveMissionClickListener());
            missionPanel.add(sampleMissionPanel);
            campaignActiveMissions.add(sampleMissionPanel);
        }
        scrollMissionPanel = new JScrollPane(missionPanel);
        scrollMissionPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMissionPanel.getVerticalScrollBar().addMouseListener(new ScrollPaneListener());
        scrollMissionPanel.getVerticalScrollBar().addMouseWheelListener(new ScrollPaneWheelListener());
        campaignPlannedActions.add(scrollMissionPanel, BorderLayout.CENTER);

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
            scrollMissionPanel.getVerticalScrollBar().setValue(yMissionScroll);
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
            List<WeaponStation> loadout = ((FlightLoadoutPanel)dialog.getContentPane().getComponent(0)).getFlightLoadout();
            if(loadout == null) {
                log.debug("No loadout!");
            }
            log.debug("Setting loadout: " + loadout);
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

    private void loadCampaignActions(int imageWidth, int imageHeight, Border padding, Border bevel) {
        // Load the actions that we can perform during the campaign
        campaignActions.removeAll();
        campaignActions.setBorder(BorderFactory.createCompoundBorder(padding, bevel));
        JPanel buttonPanel = new JPanel();
        JButton planMissionButton = new JButton("Mission Planner");
        JButton viewThreatGrid = new JButton("View Threat Grid");
        viewThreatGrid.addActionListener(l -> {
            drawThreatGrid = !drawThreatGrid;
            updateSimulationGUI(imageWidth, imageHeight, padding, bevel);
        });
        buttonPanel.add(planMissionButton);
        buttonPanel.add(viewThreatGrid);
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
        long airDefencesRed = getTotalAirDefenceUnits(campaign.getRedforCoalitionManager().getCoalitionAirDefences());
        long airDefencesBlue = getTotalAirDefenceUnits(campaign.getBlueforCoalitionManager().getCoalitionAirDefences());
        long aircraftRed = getTotalAirUnits(campaign.getRedforCoalitionManager().getCoalitionAirGroups()) + getTotalAirUnits(campaign.getRedforCoalitionManager().getCoalitionPlayerAirGroups());
        long aircraftBlue = getTotalAirUnits(campaign.getBlueforCoalitionManager().getCoalitionAirGroups()) + getTotalAirUnits(campaign.getBlueforCoalitionManager().getCoalitionPlayerAirGroups());
        int total = campaign.getRedforCoalitionManager().getCoalitionAirfields().stream().mapToInt(a -> a.getCriticalStructures().size()).sum();
        campaignEnemyStatusLabel = new JLabel(String.format("Enemy Status: %d/%d/%d", groundTargets, airDefencesRed, aircraftRed));
        campaignFriendlyStatusLabel = new JLabel(String.format("Friendly Status: %d/%d/%d", groundUnits, airDefencesBlue, aircraftBlue));
        campaignObjectivesLabel = new JLabel(String.format("Critical Objectives Remaining: %d", total));
        campaignStatus.add(Box.createHorizontalGlue());
        addHorizontalComponent(campaignDateLabel);
        addHorizontalComponent(campaignSortiesLabel);
        addHorizontalComponent(campaignEnemyStatusLabel);
        addHorizontalComponent(campaignFriendlyStatusLabel);
        addHorizontalComponent(campaignObjectivesLabel);
        JPanel buttonContainer = new JPanel();
        JButton stepSimButton = new JButton("Step Simulation");
        JButton runSim = new JButton("Run Simulation");
        stepSimButton.addActionListener(l -> {
            campaign.stepSimulation();
            if(!campaign.isAllowRun()) {
                JOptionPane.showMessageDialog(null, "Please play the generated mission to continue!", "Play Mission", JOptionPane.INFORMATION_MESSAGE);
            }
            stepSimButton.setEnabled(campaign.isAllowRun());
            runSim.setEnabled(campaign.isAllowRun());
            updateSimulationGUI(imageWidth, imageHeight, padding, bevel);
        });
        runSim.addActionListener(l -> {
            if(campaign.isSimRunning()) {
                campaign.setSimRunning(false);
                runSim.setText("Run Simulation");
            } else {
                campaign.setSimRunning(true);
                ScheduledFuture future = campaign.runSimulation(this, imageWidth, imageHeight, padding, bevel, runSim);
                runSim.setText("Stop Simulation");
            }
            if(!campaign.isAllowRun()) {
                JOptionPane.showMessageDialog(null, "Please play the generated mission to continue!", "Play Mission", JOptionPane.INFORMATION_MESSAGE);
            }
            stepSimButton.setEnabled(campaign.isAllowRun());
            runSim.setEnabled(campaign.isAllowRun());
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
        loadCampaignActions(imageWidth, imageHeight, padding, bevel);
        hostFrame.refreshUiElements();
        scrollMissionPanel.getVerticalScrollBar().setValue(yMissionScroll);
    }

    private void updateCampaignStatusLabels() {
        // Date
        SimpleDateFormat sdf = new SimpleDateFormat(campaign.getCampaignSettings().getDateFormat());
        campaignDateLabel.setText(String.format("Date: %s", sdf.format(campaign.getCurrentCampaignDate())));

        // Planned missions
        campaignSortiesLabel.setText(String.format("Planned Sorties: %d", campaign.getCampaignMissionManager().getPlannedMissions().size()));

        // Campaign status
        long enemyCount = getTotalGroundUnits(campaign.getRedforCoalitionManager());
        long count = getTotalGroundUnits(campaign.getBlueforCoalitionManager());
        long airDefencesRed = getTotalAirDefenceUnits(campaign.getRedforCoalitionManager().getCoalitionAirDefences());
        long airDefencesBlue = getTotalAirDefenceUnits(campaign.getBlueforCoalitionManager().getCoalitionAirDefences());
        long aircraftRed = getTotalAirUnits(campaign.getRedforCoalitionManager().getCoalitionAirGroups()) + getTotalAirUnits(campaign.getRedforCoalitionManager().getCoalitionPlayerAirGroups());
        long aircraftBlue = getTotalAirUnits(campaign.getBlueforCoalitionManager().getCoalitionAirGroups()) + getTotalAirUnits(campaign.getBlueforCoalitionManager().getCoalitionPlayerAirGroups());
        campaignEnemyStatusLabel.setText(String.format("Enemy Status: %d/%d/%d", enemyCount, airDefencesRed, aircraftRed));
        campaignFriendlyStatusLabel.setText(String.format("Friendly Status: %d/%d/%d", count, airDefencesBlue, aircraftBlue));

        // Critical Objectives
        int total = campaign.getRedforCoalitionManager().getCoalitionAirfields().stream().mapToInt(a -> a.getCriticalStructures().size()).sum();
        campaignObjectivesLabel.setText(String.format("Critical Objectives Remaining: %d", total));
    }

    private long getTotalAirDefenceUnits(List<UnitGroup<AirDefenceUnit>> coalitionAirDefences) {
        return coalitionAirDefences.stream().map(UnitGroup::getNumberOfUnits).mapToLong(i -> i).sum();
    }

    private long getTotalAirUnits(List<UnitGroup<AirUnit>> coalitionAircraft) {
        return coalitionAircraft.stream().map(UnitGroup::getNumberOfUnits).mapToLong(i -> i).sum();
    }

    private long getTotalGroundUnits(CoalitionManager manager) {
        long pointDefenceUnits = manager.getCoalitionPointDefenceGroundUnits().entrySet().stream()
                                            .map(Map.Entry::getValue)
                                            .flatMap(Collection::stream)
                                            .map(UnitGroup::getNumberOfUnits)
                                            .mapToLong(i -> i).sum();

        long otherGroundUnits = manager.getCoalitionFrontlineGroups().stream().map(UnitGroup::getNumberOfUnits)
                                                        .mapToLong(i -> i).sum();
        return pointDefenceUnits + otherGroundUnits;
    }

    private BufferedImage addCampaignObjects(BufferedImage image) {
        Graphics2D g = (Graphics2D)image.getGraphics();
        DrawUtil.setNormalStroke(g.getStroke());

        // Draw all of the group units
        DrawUtil.drawCampaignUnitGroups(campaign, g);
        DrawUtil.drawCampaignAirDefences(campaign, g);

        // Draw everything related to airbases
        DrawUtil.drawCampaignAirbaseStructures(campaign, g);
        DrawUtil.drawCampaignAirbases(campaign, g);

        // Draw where the battle is happening (generation debugging)
        // DrawUtil.drawWarfareFront(campaign, g);
        // DrawUtil.drawWaterMask(campaign, g);
        // DrawUtil.drawExclusionMask(campaign, g);
        if(drawThreatGrid) {
            DrawUtil.drawThreatGrid(campaign, g);
        }

        // Draw everything related to missions
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
                if(isWithinThreshold(mouseX, mouseY, x, y, 5)) {
                    clickedAirfieldTypes.add(airfieldType);
                }
            }

            // For debug, get the distance between where we clicked (if on an object), and where the AbuNair airfield is
            for(AirfieldType airfieldType : clickedAirfieldTypes) {
                Airfield field = campaign.getAllAirfields().stream().filter(a -> a.getAirfieldType().equals(airfieldType)).findFirst().orElse(null);
                List<UnitGroup<GroundUnit>> fieldUnits = campaign.getAllAirfieldGroundGroups().get(field);
                // boolean airbaseHasBeenReconned = something();
                if(field != null && !campaign.getCampaignSettings().getPlayerSelectedSide().equals(field.getOwnerSide())) {
                    JOptionPane.showMessageDialog(null, "You need to recon this Airbase to get information related to it!", "Airfield Information", JOptionPane.INFORMATION_MESSAGE);
                    log.debug(field);
                    log.debug(fieldUnits);
                }
            }
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
            scrollMissionPanel.getVerticalScrollBar().setValue(yMissionScroll);
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

    private class ScrollPaneListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            log.debug("Clicked");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            log.debug("Pressed");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            log.debug("Released");
            yMissionScroll = scrollMissionPanel.getVerticalScrollBar().getValue();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            log.debug("Entered");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            log.debug("Exit");
        }
    }

    private class ScrollPaneWheelListener implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            yMissionScroll = scrollMissionPanel.getVerticalScrollBar().getValue();
        }
    }
}