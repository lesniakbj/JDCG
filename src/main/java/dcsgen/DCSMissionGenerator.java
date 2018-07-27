package dcsgen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.MissionStartType;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.manager.CoalitionManager;

public class DCSMissionGenerator {
    private static final Logger log = LogManager.getLogger(DCSMissionGenerator.class);

    public void generateMission(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        log.debug("I am supposed to generate a mission!");

        // Find all of the missions/ground units within a certain radius of this mission's final WP
        Waypoint firstWp = mission.getNextWaypoint();
        Waypoint missionWp = mission.getMissionWaypoint();

        // At the end, zip everything up and call it a .miz file
    }
}
