package ui.util;

import sim.domain.enums.FactionSide;
import sim.domain.enums.WaypointType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.Waypoint;
import sim.main.DynamicCampaignSim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

public class DrawUtil {
    private static final Logger log = LogManager.getLogger(DrawUtil.class);

    private static final Color BLUEFOR_COLOR = new Color(52, 138, 236, 200);
    private static final Color REDFOR_COLOR = new Color(255, 0, 0, 200);;
    private static final Color ACCENT_COLOR = new Color(229, 225, 24, 217);

    private static final int GUTTER_HEIGHT = 48;

    public static void drawCampaignSelectedMission(DynamicCampaignSim campaign, Graphics2D g) {
        if(campaign.getCurrentlySelectedMission() != null) {
            Mission mission = campaign.getCurrentlySelectedMission();
            UnitGroup missionGroup = mission.getMissionAircraft();

            // Set our colors and other visual elements up based on the faction
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            Stroke normal = g.getStroke();
            Color mainColor = missionGroup.getSide().equals(FactionSide.BLUEFOR) ? BLUEFOR_COLOR : REDFOR_COLOR;

            int pointX = (int)missionGroup.getMapXLocation();
            int pointY = (int)missionGroup.getMapYLocation();
            double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
            double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

            // Draw the package
            //g.setColor(mainColor);
            //g.drawPolygon((int)(pointX * scaleX) - 10, (int)(pointY * scaleY) - 10,20, 20);
            //g.setColor(Color.black);
            //g.drawPolygon((int)(pointX * scaleX) - 10, (int)(pointY * scaleY) - 10,20, 20);

            // Draw the packages waypoints' if it is selected
            if(campaign.getCurrentlySelectedMission() != null && campaign.getCurrentlySelectedMission().equals(mission)) {
                Waypoint firstWaypoint = mission.getMissionWaypoints().get(0);
                double lastWaypointLocationX = firstWaypoint.getLocationX();
                double lastWaypointLocationY = firstWaypoint.getLocationY() - GUTTER_HEIGHT;

                for (Waypoint waypoint : mission.getMissionWaypoints()) {
                    double waypointX = waypoint.getLocationX();
                    double waypointY = waypoint.getLocationY() - GUTTER_HEIGHT;

                    if(waypoint.getWaypointType().equals(WaypointType.MISSION)) {
                        g.setColor(REDFOR_COLOR);
                    } else {
                        g.setColor(mainColor);
                    }

                    g.fillOval((int) (waypointX * scaleX) - 10, (int) (waypointY * scaleY) - 10, 20, 20);
                    g.setColor(ACCENT_COLOR);
                    g.drawOval((int) (waypointX * scaleX) - 10, (int) (waypointY * scaleY) - 10, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawString(waypoint.getWaypointType().name(), (int) (waypointX * scaleX) - 20, (int) (waypointY * scaleY) - 20);

                    g.setColor(ACCENT_COLOR);
                    g.setStroke(dashed);
                    g.drawLine((int) (lastWaypointLocationX * scaleX), (int) (lastWaypointLocationY * scaleY), (int) (waypointX * scaleX),
                            (int) (waypointY * scaleY));
                    g.setStroke(normal);

                    lastWaypointLocationX = waypointX;
                    lastWaypointLocationY = waypointY;
                }

                g.setColor(ACCENT_COLOR);
                g.setStroke(dashed);
                double lastY = firstWaypoint.getLocationY() - GUTTER_HEIGHT;
                log.debug(lastY);
                log.debug((int)(lastY * scaleY));
                g.drawLine((int) (lastWaypointLocationX * scaleX), (int) (lastWaypointLocationY * scaleY), (int) (firstWaypoint.getLocationX() * scaleX),
                        (int)(lastY * scaleY) - GUTTER_HEIGHT);

            }
        }
    }

    public static void drawCampaignAirbases(DynamicCampaignSim campaign, Graphics2D g) {
        List<Airfield> airfields = campaign.getBlueforCoalitionManager().getCoalitionAirfields();
        airfields.addAll(campaign.getRedforCoalitionManager().getCoalitionAirfields());
        for(Airfield airfield : airfields) {
            Color color = (airfield.getOwnerSide().equals(FactionSide.BLUEFOR)) ? BLUEFOR_COLOR : REDFOR_COLOR;

            int pointX = airfield.getAirfieldType().getAirfieldMapPosition().getKey().intValue();
            int pointY = airfield.getAirfieldType().getAirfieldMapPosition().getValue().intValue() - GUTTER_HEIGHT;
            double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
            double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

            g.setColor(color);
            g.fillRect((int)(pointX * scaleX) - 10, (int)(pointY * scaleY) - 10,20, 20);
            g.setColor(Color.black);
            g.drawRect((int)(pointX * scaleX) - 10, (int)(pointY * scaleY) - 10,20, 20);
        }
    }
}
