package sim.gen;

import sim.domain.enums.MapType;
import sim.domain.enums.TaskType;
import sim.domain.enums.WaypointType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Waypoint;
import sim.main.DynamicCampaignSim;

import java.util.Arrays;
import java.util.List;

public class WaypointGenerator {
    private static final Logger log = LogManager.getLogger(WaypointGenerator.class);

    private static final int MIN_SPEED = 250;
    private static final int LOW_LEVEL_ALT = 1000;
    private static final int MIN_ALT = 8000;
    private static final int MIN_DEVIATION = 20;
    private static final int MIN_NAV_DEVIATION = 35;

    public static List<Waypoint> generateMissionWaypoints(double startX, double startY, double missionObjX, double missionObjY, TaskType taskType, MapType map) {
        boolean generateLeftToRight = DynamicCampaignSim.getRandomGen().nextBoolean();
        int waypointSpeed = MIN_SPEED + DynamicCampaignSim.getRandomGen().nextInt(150);
        int waypointAltitude = (taskType.equals(TaskType.LOW_LEVEL_STRIKE)) ? LOW_LEVEL_ALT : MIN_ALT + DynamicCampaignSim.getRandomGen().nextInt(10000);

        // Create the first and last Waypoint
        Waypoint start = new Waypoint(startX, startY, 0, 0, false, WaypointType.START);
        Waypoint mission = new Waypoint(missionObjX, missionObjY, waypointSpeed, waypointAltitude, true, WaypointType.MISSION);

        // Setup the inbound / outbound points
        Waypoint ip, ob;
        boolean startLower = (startX > missionObjX);
        log.debug(startLower);
        if(startLower) {
            ip = new Waypoint(missionObjX - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.IP);
            ob = new Waypoint(missionObjX + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.OB);
        } else {
            ip = new Waypoint(missionObjX + (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.IP);
            ob = new Waypoint(missionObjX - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY - (MIN_DEVIATION + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, false, WaypointType.OB);
        }

        // If the distance to the target is greater than 70 miles, we will add nav points
        boolean generateNavPoints = false; // map.scaleDistance(MathUtil.getDistance(new Pair<>(startX, startY), new Pair<>(missionObjX, missionObjY))) > 70;
        Waypoint navIb, navOb;
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
            return Arrays.asList(start, navIb, ip, mission, ob, navOb);
        }
        return Arrays.asList(start, ip, mission, ob);
    }
}
