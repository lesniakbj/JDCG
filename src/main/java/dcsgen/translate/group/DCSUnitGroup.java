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

    public int getModulation() {
        return modulation;
    }

    public void setModulation(int modulation) {
        this.modulation = modulation;
    }

    public List<DCSTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<DCSTask> tasks) {
        this.tasks = tasks;
    }

    public DCSTask getTask() {
        return task;
    }

    public void setTask(DCSTask task) {
        this.task = task;
    }

    public boolean isControlled() {
        return isControlled;
    }

    public void setControlled(boolean controlled) {
        isControlled = controlled;
    }

    public boolean isTaskSelected() {
        return taskSelected;
    }

    public void setTaskSelected(boolean taskSelected) {
        this.taskSelected = taskSelected;
    }

    public List<DCSWaypoint> getPoints() {
        return points;
    }

    public void setPoints(List<DCSWaypoint> points) {
        this.points = points;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public List<DCSUnit> getUnits() {
        return units;
    }

    public void setUnits(List<DCSUnit> units) {
        this.units = units;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public String getDictNameKey() {
        return dictNameKey;
    }

    public void setDictNameKey(String dictNameKey) {
        this.dictNameKey = dictNameKey;
    }

    public boolean isCommunication() {
        return communication;
    }

    public void setCommunication(boolean communication) {
        this.communication = communication;
    }

    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(long startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
