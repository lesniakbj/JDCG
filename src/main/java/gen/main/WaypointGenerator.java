package gen.main;

import gen.domain.enums.TaskType;
import gen.domain.enums.WaypointType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Waypoint;
import sim.main.DynamicCampaignSim;

import java.util.Arrays;
import java.util.List;

public class WaypointGenerator {
    private static final Logger log = LogManager.getLogger(WaypointGenerator.class);

    public static List<Waypoint> generateMissionWaypoints(double startX, double startY, double missionObjX, double missionObjY, TaskType taskType) {
        boolean generateLeftToRight = DynamicCampaignSim.getRandomGen().nextBoolean();
        int waypointSpeed = 250 + DynamicCampaignSim.getRandomGen().nextInt(150);
        int waypointAltitude = (taskType.equals(TaskType.LOW_LEVEL_STRIKE)) ? 1000 : 8000 + DynamicCampaignSim.getRandomGen().nextInt(10000);

        // Create the first Waypoint
        Waypoint start = new Waypoint(startX, startY, 0, 0, false, WaypointType.START);

        // If the distance to the target is greater than 75 miles, add some navigational waypoints

        // Setup the inbound / outbound points
        Waypoint ip, ob;
        boolean startLower = (startX > missionObjX);
        log.debug(startLower);
        if(startLower) {
            ip = new Waypoint(missionObjX - (20 + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY + (20 + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, true, WaypointType.IP);
            ob = new Waypoint(missionObjX + (20 + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY + (20 + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, true, WaypointType.OB);
        } else {
            ip = new Waypoint(missionObjX + (20 + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY - (20 + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, true, WaypointType.IP);
            ob = new Waypoint(missionObjX - (20 + DynamicCampaignSim.getRandomGen().nextInt(30)), missionObjY - (20 + DynamicCampaignSim.getRandomGen().nextInt(10)), waypointSpeed, waypointAltitude, true, WaypointType.OB);
        }

        Waypoint mission = new Waypoint(missionObjX, missionObjY, waypointSpeed, waypointAltitude, true, WaypointType.MISSION);
        return Arrays.asList(start, ip, mission, ob);
    }
}
