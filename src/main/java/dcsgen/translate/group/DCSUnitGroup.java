package dcsgen.translate.group;

import dcsgen.translate.DCSTask;
import dcsgen.translate.unit.DCSUnit;
import dcsgen.translate.waypoint.DCSWaypoint;

import java.util.List;

public class DCSUnitGroup {
    private int modulation;
    private List<DCSTask> tasks;
    private DCSTask task;
    private boolean isControlled;
    private boolean taskSelected;
    private List<DCSWaypoint> points;
    private long groupId;
    private boolean isHidden;
    private List<DCSUnit> units;
    private double locationX;
    private double locationY;
    private String dictNameKey;
    private boolean communication;
    private long startTimeSeconds;
    private int frequency;
}
