package ui.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.ThreatGridCell;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AirDefenceUnitType;
import sim.domain.enums.AirDefenceWeaponType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.MapType;
import sim.domain.enums.WaypointType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.Structure;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.util.mask.MaskFactory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

;

public class DrawUtil {
    private static final Logger log = LogManager.getLogger(DrawUtil.class);

    private static final Color BLUEFOR_COLOR = new Color(52, 138, 236, 225);
    private static final Color REDFOR_COLOR = new Color(255, 0, 0, 225);
    private static final Color NEUTRAL_COLOR = new Color(154,69,118, 0);
    private static final Color ACCENT_COLOR = new Color(229, 225, 24, 225);
    private static final Stroke DASHED = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    private static final Stroke WIDE = new BasicStroke(5);
    private static final Stroke SEMI_WIDE = new BasicStroke(2);

    private static Stroke normalStroke;

    private static final int GUTTER_HEIGHT = 80;

    public static void drawCampaignSelectedMission(DynamicCampaignSim campaign, Graphics2D g) {
        if(campaign.getCurrentlySelectedMission() != null) {
            Mission mission = campaign.getCurrentlySelectedMission();
            UnitGroup<AirUnit> missionGroup = mission.getMissionAircraft();

            // Set our colors and other visual elements up based on the faction
            Color mainColor = missionGroup.getSide().equals(FactionSideType.BLUEFOR) ? BLUEFOR_COLOR : REDFOR_COLOR;

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
                    Line2D.Double line = new Line2D.Double((lastWaypointLocationX * scaleX), (lastWaypointLocationY * scaleY), (waypointX * scaleX), (waypointY * scaleY));
                    g.setColor(ACCENT_COLOR);
                    g.setStroke(DASHED);
                    g.draw(line);
                    g.setStroke(normalStroke);

                    lastWaypointLocationX = waypointX;
                    lastWaypointLocationY = waypointY;
                }
            }
        }
    }

    public static void drawCampaignAirbases(DynamicCampaignSim campaign, Graphics2D g) {
        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        List<Airfield> airfields = new ArrayList<>();
        airfields.addAll(campaign.getBlueforCoalitionManager().getCoalitionAirfields());
        airfields.addAll(campaign.getRedforCoalitionManager().getCoalitionAirfields());
        for(Airfield airfield : airfields) {
            Color color = (airfield.getOwnerSide().equals(FactionSideType.BLUEFOR)) ? BLUEFOR_COLOR : REDFOR_COLOR;

            double pointX = airfield.getAirfieldType().getAirfieldMapPosition().getX();
            double pointY = airfield.getAirfieldType().getAirfieldMapPosition().getY() - GUTTER_HEIGHT;

            g.setStroke(normalStroke);
            g.setColor(color);
            g.fillRect((int)(pointX * scaleX) - 8, (int)(pointY * scaleY) - 8,16, 16);
            g.setColor(Color.black);
            g.drawRect((int)(pointX * scaleX) - 8, (int)(pointY * scaleY) - 8,16, 16);
        }
    }

    public static void drawActiveMissions(DynamicCampaignSim campaign, Graphics2D g) {
        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        List<Mission> missions = new ArrayList<>();
        missions.addAll(campaign.getBlueforCoalitionManager().getCoalitionMissionManager().getPlannedMissions());
        missions.addAll(campaign.getRedforCoalitionManager().getCoalitionMissionManager().getPlannedMissions());
        for(Mission mission : missions) {
            if(!mission.isActive()) {
                continue;
            }

            UnitGroup<AirUnit> missionGroup = mission.getMissionAircraft();
            Color mainColor = missionGroup.getSide().equals(FactionSideType.BLUEFOR) ? BLUEFOR_COLOR : REDFOR_COLOR;

            double pointX = missionGroup.getMapXLocation();
            double pointY = missionGroup.getMapYLocation() - GUTTER_HEIGHT;
            double x = (pointX * scaleX) - 5;
            double y = (pointY * scaleY) - 5;

            // Create the package shape at the correct position
            Triangle triangle = new Triangle(new Point2D.Double(x, y - 10), new Point2D.Double(x + 10, y + 10), new Point2D.Double(x - 10, y + 10));
            Rectangle2D.Double backLine = new Rectangle2D.Double(x, y + 4, 0, 6);

            // Rotate the image to fit the direction of the mission
            double angle = mission.isOnStation() ? mission.getCurrentDir() : mission.getDirectionNextWaypoint();
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
        Map<FactionSideType, List<Point2D.Double>> warfareFront = campaign.getWarfareFront();

        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        Polygon p = new Polygon();
        for(Map.Entry<FactionSideType, List<Point2D.Double>> entry : warfareFront.entrySet()) {
            Color mainColor = entry.getKey().equals(FactionSideType.BLUEFOR) ? BLUEFOR_COLOR : REDFOR_COLOR;
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

    public static void drawWaterMask(DynamicCampaignSim campaign, Graphics2D g) {
        MapType type = campaign.getCampaignSettings().getSelectedMap().getMapType();
        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        Path2D.Double path = MaskFactory.getScaledWaterMask(type, scaleX, scaleY, GUTTER_HEIGHT);

        g.setStroke(WIDE);
        g.setColor(Color.BLUE);
        g.draw(path);
    }

    public static void drawExclusionMask(DynamicCampaignSim campaign, Graphics2D g) {
        MapType type = campaign.getCampaignSettings().getSelectedMap().getMapType();
        double scaleX = type.getMapXScale();
        double scaleY = type.getMapYScale();

        Path2D.Double path = MaskFactory.getScaledExclusionMask(type, scaleX, scaleY, GUTTER_HEIGHT);
        g.setStroke(WIDE);
        g.setColor(Color.BLACK);
        g.draw(path);
    }

    public static void drawThreatGrid(DynamicCampaignSim campaign, Graphics2D g) {
        MapType type = campaign.getCampaignSettings().getSelectedMap().getMapType();
        double scaleX = type.getMapXScale();
        double scaleY = type.getMapYScale();
        ThreatGrid grid = campaign.getPlayerThreatGrid();

        double gridX = (grid.getX() * scaleX);
        double gridY = ((grid.getY() - GUTTER_HEIGHT) * scaleY);
        double gridW = grid.getW() * scaleX;
        double gridH = grid.getH() * scaleY;
        double cellWX = grid.getCellWidth() * scaleX;
        double cellWY = grid.getCellWidth() * scaleY;

        drawGrid(g, grid, gridX, gridY, gridW, gridH, cellWX, cellWY);
    }

    private static void drawGrid(Graphics2D g, ThreatGrid grid, double gridX, double gridY, double gridW, double gridH, double cellWX, double cellWY) {
        Rectangle2D.Double drawGrid = new Rectangle2D.Double(gridX, gridY, gridW, gridH);
        g.setStroke(WIDE);
        for(int x = 0; x < grid.getNumCellsX(); x++) {
            for(int y = 0; y < grid.getNumCellsY(); y++) {
                ThreatGridCell cell = grid.getThreatGrid()[x][y];
                Rectangle2D.Double rect = new Rectangle2D.Double(gridX + (x * cellWX), gridY + (y * cellWY), cellWX, cellWY);
                g.setColor(getThreatColorForCell(cell));
                g.fill(rect);
                if(cell.isIgnoreDuringThreatCalculations()) {
                    g.setColor(Color.BLACK);
                    Line2D.Double line = new Line2D.Double(rect.getX(), rect.getY(), rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
                    g.draw(line);
                }
            }
        }
    }

    private static Color getThreatColorForCell(ThreatGridCell cell) {
        double threatLevel = cell.getThreatLevel();
        if(threatLevel == 0) {
            return NEUTRAL_COLOR;
        }

        int alpha = (int)(threatLevel * 255);
        if(threatLevel < 0) {
            if(threatLevel == -1.0) {
                alpha += 35;
            }
            alpha = alpha * - 1;
            return new Color(REDFOR_COLOR.getRed(), REDFOR_COLOR.getGreen(), REDFOR_COLOR.getBlue(), alpha);
        }

        if(threatLevel > 0) {
            if(threatLevel == 1.0) {
                alpha -= 35;
            }
            return new Color(BLUEFOR_COLOR.getRed(), BLUEFOR_COLOR.getGreen(), BLUEFOR_COLOR.getBlue(), alpha);
        }

        return REDFOR_COLOR;
    }

    public static void drawCampaignUnitGroups(DynamicCampaignSim campaign, Graphics2D g) {
        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        // Draw the airfield unit groups
        Map<Airfield, List<UnitGroup<GroundUnit>>> defences = campaign.getBlueforCoalitionManager().getCoalitionPointDefenceGroundUnits();
        Map<Airfield, List<UnitGroup<GroundUnit>>> defences2 = campaign.getRedforCoalitionManager().getCoalitionPointDefenceGroundUnits();
        List<UnitGroup<GroundUnit>> defencesUnits = defences.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        List<UnitGroup<GroundUnit>> defencesUnit2 = defences2.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        drawUnitGroups(defencesUnits, scaleX, scaleY, g);
        drawUnitGroups(defencesUnit2, scaleX, scaleY, g);

        // Draw the frontline unit groups
        List<UnitGroup<GroundUnit>> frontline = campaign.getBlueforCoalitionManager().getCoalitionFrontlineGroups();
        List<UnitGroup<GroundUnit>> frontline2 = campaign.getRedforCoalitionManager().getCoalitionFrontlineGroups();
        drawUnitGroups(frontline, scaleX, scaleY, g);
        drawUnitGroups(frontline2, scaleX, scaleY, g);
    }

    private static void drawUnitGroups(List<UnitGroup<GroundUnit>> unitGroupList, double scaleX, double scaleY, Graphics2D g) {
        for(UnitGroup<GroundUnit> unitGroup : unitGroupList) {
            double x = unitGroup.getMapXLocation();
            double y = unitGroup.getMapYLocation() - GUTTER_HEIGHT;
            FactionSideType side = unitGroup.getSide();

            g.setStroke(normalStroke);
            g.setColor(side == FactionSideType.BLUEFOR ? BLUEFOR_COLOR : REDFOR_COLOR);
            g.fillRect((int)(x * scaleX) - 4, (int)(y * scaleY) - 4, 8, 8);
            g.setColor(Color.BLACK);
            g.drawRect((int)(x * scaleX) - 4, (int)(y * scaleY) - 4, 8, 8);
        }
    }

    public static void drawCampaignAirbaseStructures(DynamicCampaignSim campaign, Graphics2D g) {
        List<Airfield> airfields = new ArrayList<>();
        airfields.addAll(campaign.getBlueforCoalitionManager().getCoalitionAirfields());
        airfields.addAll(campaign.getRedforCoalitionManager().getCoalitionAirfields());

        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        for(Airfield airfield : airfields) {
            for(Structure s : airfield.getCriticalStructures()) {
                double x = s.getMapXLocation();
                double y = s.getMapYLocation() - GUTTER_HEIGHT;
                FactionSideType side = airfield.getOwnerSide();

                g.setStroke(normalStroke);
                g.setColor(side == FactionSideType.BLUEFOR ? BLUEFOR_COLOR : REDFOR_COLOR);
                g.fillOval((int)(x * scaleX) - 4, (int)(y * scaleY) - 4, 8, 8);
                g.setColor(Color.BLACK);
                g.drawOval((int)(x * scaleX) - 4, (int)(y * scaleY) - 4, 8, 8);
            }
        }
    }

    public static void setNormalStroke(Stroke stroke) {
        normalStroke = stroke;
    }

    public static void drawCampaignAirDefences(DynamicCampaignSim campaign, Graphics2D g) {
        double scaleX = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapXScale();
        double scaleY = campaign.getCampaignSettings().getSelectedMap().getMapType().getMapYScale();

        List<UnitGroup<AirDefenceUnit>> defenceGroups = new ArrayList<>();
        defenceGroups.addAll(campaign.getBlueforCoalitionManager().getCoalitionAirDefences());
        defenceGroups.addAll(campaign.getRedforCoalitionManager().getCoalitionAirDefences());
        for(UnitGroup<AirDefenceUnit> unitGroup : defenceGroups) {
            Color color = (unitGroup.getSide().equals(FactionSideType.BLUEFOR)) ? BLUEFOR_COLOR : REDFOR_COLOR;
            AirDefenceUnitType type = unitGroup.getGroupUnits().get(0).getUnitType();
            boolean isAAA = type.getWeaponType().equals(AirDefenceWeaponType.AAA);
            double range = isAAA ? 20 : type.getRange() * 6;

            double pointX = unitGroup.getMapXLocation();
            double pointY = unitGroup.getMapYLocation() - GUTTER_HEIGHT;
            pointX = (pointX * scaleX) - (range / 2);
            pointY = (pointY * scaleY) - (range / 2);

            // Draw
            g.setStroke(SEMI_WIDE);
            g.setColor(isAAA ? Color.BLACK : color);
            g.drawOval((int)pointX, (int)pointY, (int)range, (int)range);
        }
    }

    private static class Triangle extends Path2D.Double {
        Triangle(Point2D... points) {
            moveTo(points[0].getX(), points[0].getY());
            lineTo(points[1].getX(), points[1].getY());
            lineTo(points[2].getX(), points[2].getY());
            closePath();
        }
    }
}
