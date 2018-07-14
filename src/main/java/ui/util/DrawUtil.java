package ui.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Aircraft;
import sim.domain.Airfield;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.Waypoint;
import sim.domain.enums.FactionSide;
import sim.domain.enums.WaypointType;
import sim.main.DynamicCampaignSim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

;

public class DrawUtil {
    private static final Logger log = LogManager.getLogger(DrawUtil.class);

    private static final Color BLUEFOR_COLOR = new Color(52, 138, 236, 200);
    private static final Color REDFOR_COLOR = new Color(255, 0, 0, 200);;
    private static final Color ACCENT_COLOR = new Color(229, 225, 24, 217);
    private static final Stroke DASHED = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    private static final Stroke WIDE = new BasicStroke(5);

    private static Stroke normalStroke;

    private static final int GUTTER_HEIGHT = 48;

    public static void drawCampaignSelectedMission(DynamicCampaignSim campaign, Graphics2D g) {
        if(campaign.getCurrentlySelectedMission() != null) {
            Mission mission = campaign.getCurrentlySelectedMission();
            UnitGroup<Aircraft> missionGroup = mission.getMissionAircraft();

            // Set our colors and other visual elements up based on the faction
            Color mainColor = missionGroup.getSide().equals(FactionSide.BLUEFOR) ? BLUEFOR_COLOR : REDFOR_COLOR;

            // Get the locations of relevant objects
            int pointX = (int)missionGroup.getMapXLocation();
            int pointY = (int)missionGroup.getMapYLocation() - GUTTER_HEIGHT;
            double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
            double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

            // Draw the packages waypoints' if it is selected
            if(campaign.getCurrentlySelectedMission() != null && campaign.getCurrentlySelectedMission().equals(mission)) {
                double lastWaypointLocationX = pointX;
                double lastWaypointLocationY = pointY;

                for (Waypoint waypoint : mission.getMissionWaypoints()) {
                    double waypointX = waypoint.getLocationX();
                    double waypointY = waypoint.getLocationY() - GUTTER_HEIGHT;
                    double x = (waypointX * scaleX) - 10;
                    double y = (waypointY * scaleY) - 10;

                    if(waypoint.getWaypointType().equals(WaypointType.MISSION)) {
                        g.setColor(REDFOR_COLOR);
                    } else {
                        g.setColor(mainColor);
                    }

                    // Draw the waypoint
                    Ellipse2D waypointMarker = new Ellipse2D.Double(x, y, 20, 20);
                    g.setStroke(normalStroke);
                    g.fill(waypointMarker);
                    g.setColor(ACCENT_COLOR);
                    g.draw(waypointMarker);
                    g.setColor(Color.BLACK);
                    g.drawString(waypoint.getWaypointType().name(), (int)(x - 10), (int)(y - 10));

                    // Draw the connecting line
                    int lastX = (int) (lastWaypointLocationX * scaleX);
                    int lastY = (int) (lastWaypointLocationY * scaleY);
                    int curX = (int) (waypointX * scaleX);
                    int curY = (int) (waypointY * scaleY);
                    g.setColor(ACCENT_COLOR);
                    g.setStroke(DASHED);
                    g.drawLine(lastX, lastY, curX, curY);
                    g.setStroke(normalStroke);

                    lastWaypointLocationX = waypointX;
                    lastWaypointLocationY = waypointY;
                }
            }
        }
    }

    public static void drawCampaignAirbases(DynamicCampaignSim campaign, Graphics2D g) {
        List<Airfield> airfields = campaign.getBlueforCoalitionManager().getCoalitionAirfields();
        airfields.addAll(campaign.getRedforCoalitionManager().getCoalitionAirfields());
        for(Airfield airfield : airfields) {
            Color color = (airfield.getOwnerSide().equals(FactionSide.BLUEFOR)) ? BLUEFOR_COLOR : REDFOR_COLOR;

            double pointX = airfield.getAirfieldType().getAirfieldMapPosition().getX();
            double pointY = airfield.getAirfieldType().getAirfieldMapPosition().getY() - GUTTER_HEIGHT;
            double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
            double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

            g.setStroke(normalStroke);
            g.setColor(color);
            g.fillRect((int)(pointX * scaleX) - 10, (int)(pointY * scaleY) - 10,20, 20);
            g.setColor(Color.black);
            g.drawRect((int)(pointX * scaleX) - 10, (int)(pointY * scaleY) - 10,20, 20);
        }
    }

    public static void drawActiveMissions(DynamicCampaignSim campaign, Graphics2D g) {
        for(Mission mission : campaign.getCampaignMissionManager().getActiveMissions()) {
            UnitGroup<Aircraft> missionGroup = mission.getMissionAircraft();
            Color mainColor = missionGroup.getSide().equals(FactionSide.BLUEFOR) ? BLUEFOR_COLOR : REDFOR_COLOR;

            double pointX = missionGroup.getMapXLocation();
            double pointY = missionGroup.getMapYLocation() - GUTTER_HEIGHT;
            double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
            double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();
            double x = (pointX * scaleX) - 5;
            double y = (pointY * scaleY) - 5;

            // Create the package shape at the correct position
            Triangle triangle = new Triangle(new Point2D.Double(x, y - 10), new Point2D.Double(x + 10, y + 10), new Point2D.Double(x - 10, y + 10));
            Rectangle2D.Double backLine = new Rectangle2D.Double(x, y + 4, 0, 6);

            // Rotate the image to fit the direction of the mission
            double angle = mission.getDirectionNextWaypoint();
            Rectangle bounds = triangle.getBounds();
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle), bounds.getX() + (bounds.getWidth() / 2), bounds.getY() + (bounds.getHeight() / 2));
            Shape rotatedTriangle  = triangle.createTransformedShape(transform);
            Shape rotatedLine  = transform.createTransformedShape(backLine);

            // Draw the package
            g.setStroke(normalStroke);
            g.setColor(mainColor);
            g.fill(rotatedTriangle);
            g.setColor(Color.black);
            g.draw(rotatedTriangle);
            g.draw(rotatedLine);
        }
    }

    public static void drawWarfareFront(DynamicCampaignSim campaign, Graphics2D g){
        Map<FactionSide, List<Point2D.Double>> warfareFront = campaign.getWarfareFront();

        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        Polygon p = new Polygon();
        for(Map.Entry<FactionSide, List<Point2D.Double>> entry : warfareFront.entrySet()) {
            Color mainColor = entry.getKey().equals(FactionSide.BLUEFOR) ? BLUEFOR_COLOR : REDFOR_COLOR;
            for(Point2D.Double pt : entry.getValue()) {
                double pointX = pt.getX();
                double pointY = pt.getY() - GUTTER_HEIGHT;

                p.addPoint((int) (pointX * scaleX), (int) (pointY * scaleY));
            }
            g.setStroke(WIDE);
            g.setColor(mainColor);
            g.draw(p);
        }
    }

    public static void drawCampaignUnitGroups(DynamicCampaignSim campaign, Graphics2D g) {
    }

    private static class Triangle extends Path2D.Double {
        Triangle(Point2D... points) {
            moveTo(points[0].getX(), points[0].getY());
            lineTo(points[1].getX(), points[1].getY());
            lineTo(points[2].getX(), points[2].getY());
            closePath();
        }
    }

    public static void setNormalStroke(Stroke stroke) {
        normalStroke = stroke;
    }
}
