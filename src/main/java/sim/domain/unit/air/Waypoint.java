package sim.domain.unit.air;

import sim.domain.enums.WaypointType;

public class Waypoint {
    private double locationX;
    private double locationY;
    private int speedMilesPerHour;
    private int height;
    private boolean isMissionWaypoint;
    private WaypointType waypointType;

    public Waypoint(double locationX, double locationY, int speedMilesPerHour, int height, boolean isMissionWaypoint, WaypointType waypointType) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedMilesPerHour = speedMilesPerHour;
        this.height = height;
        this.isMissionWaypoint = isMissionWaypoint;
        this.waypointType = waypointType;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public void setSpeedMilesPerHour(int speedMilesPerHour) {
        this.speedMilesPerHour = speedMilesPerHour;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public int getSpeedMilesPerHour() {
        return speedMilesPerHour;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMissionWaypoint() {
        return isMissionWaypoint;
    }

    public void setMissionWaypoint(boolean missionWaypoint) {
        isMissionWaypoint = missionWaypoint;
    }

    public void setWaypointType(WaypointType waypointType) {
        this.waypointType = waypointType;
    }

    public WaypointType getWaypointType() {
        return waypointType;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "locationX=" + locationX +
                ", locationY=" + locationY +
                ", speedMilesPerHour=" + speedMilesPerHour +
                ", height=" + height +
                ", isMissionWaypoint=" + isMissionWaypoint +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Waypoint waypoint = (Waypoint) o;

        if (Double.compare(waypoint.locationX, locationX) != 0) return false;
        if (Double.compare(waypoint.locationY, locationY) != 0) return false;
        if (speedMilesPerHour != waypoint.speedMilesPerHour) return false;
        if (height != waypoint.height) return false;
        return isMissionWaypoint == waypoint.isMissionWaypoint;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(locationX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(locationY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + speedMilesPerHour;
        result = 31 * result + height;
        result = 31 * result + (isMissionWaypoint ? 1 : 0);
        return result;
    }

}
