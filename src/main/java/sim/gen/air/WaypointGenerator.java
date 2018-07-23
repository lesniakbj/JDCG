package sim.gen.air;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.MapType;
import sim.domain.enums.SubTaskType;
import sim.domain.enums.WaypointType;
import sim.domain.unit.air.Waypoint;
import sim.util.MathUtil;

public class WaypointGenerator {
    private static final Logger log = LogManager.getLogger(WaypointGenerator.class);

    private static final int MIN_SPEED = 250;
    private static final int LOW_LEVEL_ALT = 1000;
    private static final int MIN_ALT = 8000;
    private static final int MIN_DEVIATION = 20;
    private static final int MIN_NAV_DEVIATION = 35;

    public static List<Waypoint> generateMissionWaypoints(Point2D.Double start, Point2D.Double mission, SubTaskType SubTaskType, MapType map) {
        return generateMissionWaypoints(start.getX(), start.getY(), mission.getX(), mission.getY(), SubTaskType, map);
    }

    public static List<Waypoint> generateMissionWaypoints(double startX, double startY, double missionObjX, double missionObjY, SubTaskType SubTaskType, MapType map) {
        boolean generateLeftToRight = DynamicCampaignSim.getRandomGen().nextBoolean();
        int waypointSpeed = MIN_SPEED + DynamicCampaignSim.getRandomGen().nextInt(150);
        int waypointAltitude = (SubTaskType.equals(SubTaskType.LOW_LEVEL_STRIKE)) ? LOW_LEVEL_ALT : MIN_ALT + DynamicCampaignSim.getRandomGen().nextInt(10000);

        // Create the first and last Waypoint
        Waypoint end = new Waypoint(startX, startY, waypointSpeed, waypointAltitude, false, WaypointType.LANDING);
        Waypoint mission = new Waypoint(missionObjX, missionObjY, waypointSpeed, waypointAltitude, true, WaypointType.MISSION);

        // Setup the inbound / outbound points
        Waypoint ip, ob;
        boolean startLower = (startY > missionObjY);
        log.debug(startLower);
        if(startLower) {
            ip = new Waypoint(missionObjX - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.IP);
            ob = new Waypoint(missionObjX + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.OB);
        } else {
            ip = new Waypoint(missionObjX + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.IP);
            ob = new Waypoint(missionObjX - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.OB);
        }

        // If the distance to the target is greater than 70 miles, we will add nav points
        boolean generateNavPoints = map.scaleDistance(MathUtil.getDistance(new Point2D.Double(startX, startY), new Point2D.Double(missionObjX, missionObjY))) > 70;
        Waypoint navIb = null, navOb = null;
        if(generateNavPoints) {
            int xDeviation = (10 + DynamicCampaignSim.getRandomGen().nextInt(10));
            int yDeviation = (MIN_NAV_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(20));
            if(startLower) {
                navIb = new Waypoint(ip.getLocationX() + (xDeviation * -1), ip.getLocationY() + yDeviation, waypointSpeed, waypointAltitude, false, WaypointType.NAV);
                navOb = new Waypoint(ob.getLocationX() + xDeviation, ob.getLocationY() + yDeviation, waypointSpeed, waypointAltitude, false, WaypointType.NAV);
            } else {
                navIb = new Waypoint(ip.getLocationX() + xDeviation, ip.getLocationY() - yDeviation, waypointSpeed, waypointAltitude, false, WaypointType.NAV);
                navOb = new Waypoint(ob.getLocationX() + (xDeviation * -1), ob.getLocationY() - yDeviation, waypointSpeed, waypointAltitude, false, WaypointType.NAV);
            }
        }

        ArrayList<Waypoint> finalPoints;
        if(generateLeftToRight) {
            if(navIb != null) {
                finalPoints = new ArrayList<>(Arrays.asList(navIb, ip, mission, ob, navOb, end));
            } else {
                finalPoints = new ArrayList<>(Arrays.asList(ip, mission, ob, end));
            }
        } else {
            ob.setWaypointType(WaypointType.IP);
            ip.setWaypointType(WaypointType.OB);

            if(navIb != null) {
                finalPoints = new ArrayList<>(Arrays.asList(navOb, ob, mission, ip, navIb, end));
            } else {
                finalPoints = new ArrayList<>(Arrays.asList(ob, mission, ip, end));
            }
        }
        return finalPoints;
    }
}
