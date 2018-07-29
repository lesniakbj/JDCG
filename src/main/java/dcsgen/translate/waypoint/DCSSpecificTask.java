package dcsgen.translate.waypoint;

import dcsgen.translate.DCSTask;

import java.util.List;

public class DCSSpecificTask {
    private boolean enabled;
    private DCSTask taskKey;
    private int taskId;
    private boolean auto;
    private List<DCSTaskParameter> params;
}
