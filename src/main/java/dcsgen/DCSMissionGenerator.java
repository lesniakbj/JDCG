package dcsgen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Mission;
import sim.domain.Waypoint;
import sim.domain.enums.MissionStartType;
import sim.main.CoalitionManager;

public class DCSMissionGenerator {
    private static final Logger log = LogManager.getLogger(DCSMissionGenerator.class);

    public void generateMission(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("I am supposed to generate a mission!");

        // Find all of the missions/ground units within a certain radius of this mission's final WP
        Waypoint missionWp = mission.getMissionWaypoint();
    }
}
