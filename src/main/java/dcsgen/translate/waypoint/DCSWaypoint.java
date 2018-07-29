package dcsgen.translate.waypoint;

import java.util.List;

public class DCSWaypoint {
    private int altitude;
    private DCSWaypointAction action;
    private DCSAltitidueType altitidueType;
    private double speed;
    private List<DCSSpecificTask> tasks;
    private DCSWaypointType type;
    private long eta;
    private boolean etaLocked;
    private double locationX;
    private double locationY;
    private String dictKeyName;
    private DCSFormationTemplate formationTemplate;
    private int airdromeId;
    private boolean speedLocked;
}
