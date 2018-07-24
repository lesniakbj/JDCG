package sim.domain.unit.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.MapType;
import sim.domain.enums.MunitionType;
import sim.domain.enums.SubTaskType;
import sim.domain.enums.WaypointType;
import sim.domain.unit.SimUnit;
import sim.domain.unit.Simable;
import sim.domain.unit.UnitGroup;
import sim.util.MathUtil;

import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Mission implements Simable {
    private static final Logger log = LogManager.getLogger(Mission.class);

    private static int minutesPerUpdate;

    private MapType mapType;
    private SubTaskType missionType;
    private AirfieldType startingAirfield;
    private UnitGroup<AirUnit> missionAircraft;
    private Map<Integer,MunitionType> missionMunitions;
    private Date plannedMissionDate;
    private Date currentCampaignDate;

    private Date onStationEndDate;
    private boolean isOnStation;
    private int timeOnStation;

    private List<Waypoint> missionWaypoints;
    private Waypoint nextWaypoint;
    private Waypoint lastWaypoint;
    private SimUnit target;
    private double currentDir;

    private boolean isClientMission;
    private boolean missionComplete;
    private boolean shouldGenerate;

    private int playerAircraft;

    private Mission() {}

    public SubTaskType getMissionType() {
        return missionType;
    }

    public void setMissionType(SubTaskType missionType) {
        this.missionType = missionType;
    }

    public UnitGroup<AirUnit> getMissionAircraft() {
        return missionAircraft;
    }

    public void setMissionAircraft(UnitGroup<AirUnit> missionAircraft) {
        this.missionAircraft = missionAircraft;
    }

    public List<Waypoint> getMissionWaypoints() {
        return missionWaypoints;
    }

    public void setMissionWaypoints(List<Waypoint> missionWaypoints) {
        this.missionWaypoints = missionWaypoints;
    }

    public boolean isClientMission() {
        return isClientMission;
    }

    public void setClientMission(boolean clientMission) {
        isClientMission = clientMission;
    }

    public Date getPlannedMissionDate() {
        return plannedMissionDate;
    }

    public void setPlannedMissionDate(Date plannedMissionDate) {
        this.plannedMissionDate = plannedMissionDate;
    }

    public Waypoint getNextWaypoint() {
        return nextWaypoint;
    }

    public double getDirectionNextWaypoint() {
        Waypoint nextWaypoint = getNextWaypoint();
        if(nextWaypoint == null) {
            return 0.0;
        }
        return MathUtil.getAngleNorthFace(new Point2D.Double(nextWaypoint.getLocationX(), nextWaypoint.getLocationY()), new Point2D.Double(missionAircraft.getMapXLocation(), missionAircraft.getMapYLocation()));
    }

    public void setMinutesPerUpdate(int minutesPerUpdate) {
        Mission.minutesPerUpdate = minutesPerUpdate;
    }

    public void nextWaypoint() {
        if(missionWaypoints != null && !missionWaypoints.isEmpty()) {
            missionWaypoints.remove(0);

            if(!missionWaypoints.isEmpty()) {
                nextWaypoint = missionWaypoints.get(0);
            } else {
                nextWaypoint = null;
            }
        }
    }

    public Waypoint getMissionWaypoint() {
        return missionWaypoints.stream().filter(wp -> wp.getWaypointType().equals(WaypointType.MISSION)).findFirst().orElse(null);
    }

    public boolean isMissionComplete() {
        return missionComplete;
    }

    public int getPlayerAircraft() {
        return playerAircraft;
    }

    public void setPlayerAircraft(int aircraft) {
        playerAircraft = aircraft;
    }

    public void setMissionMunitions(Map<Integer,MunitionType> missionMunitions) {
        this.missionMunitions = missionMunitions;
    }

    public Map<Integer,MunitionType> getMissionMunitions() {
        return missionMunitions;
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    public void setNextWaypoint(Waypoint nextWaypoint) {
        this.nextWaypoint = nextWaypoint;
    }

    public void setMissionComplete(boolean missionComplete) {
        this.missionComplete = missionComplete;
    }

    public static int getMinutesPerUpdate() {
        return minutesPerUpdate;
    }

    public Date getCurrentCampaignDate() {
        return currentCampaignDate;
    }

    public boolean isShouldGenerate() {
        return shouldGenerate;
    }

    public void setShouldGenerate(boolean shouldGenerate) {
        this.shouldGenerate = shouldGenerate;
    }

    public AirfieldType getStartingAirfield() {
        return startingAirfield;
    }

    public void setStartingAirfield(AirfieldType startingAirfield) {
        this.startingAirfield = startingAirfield;
    }

    public boolean isActive() {
        return !currentCampaignDate.before(plannedMissionDate);
    }

    public boolean onObjectiveWaypoint() {
        return lastWaypoint.getLocationX() == missionAircraft.getMapXLocation() && lastWaypoint.getLocationY()
                == missionAircraft.getMapYLocation() && lastWaypoint.getWaypointType().equals(WaypointType.MISSION);
    }

    public void setLastWaypoint(Waypoint lastWaypoint) {
        this.lastWaypoint = lastWaypoint;
    }

    public Waypoint getLastWaypoint() {
        return lastWaypoint;
    }

    public int getTimeOnStation() {
        return timeOnStation;
    }

    public void setTimeOnStation(int timeOnStation) {
        this.timeOnStation = timeOnStation;
    }

    public boolean isOnStation() {
        return isOnStation;
    }

    public void setOnStation(boolean onStation) {
        isOnStation = onStation;
    }

    private void setOnStationEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentCampaignDate);
        cal.add(Calendar.MINUTE, timeOnStation);
        onStationEndDate = cal.getTime();
    }

    public Date getOnStationEndDate() {
        return onStationEndDate;
    }

    public void setOnStationEndDate(Date onStationEndDate) {
        this.onStationEndDate = onStationEndDate;
    }

    public SimUnit getTarget() {
        return target;
    }

    public void setTarget(SimUnit target) {
        this.target = target;
    }

    public double getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(double currentDir) {
        this.currentDir = currentDir;
    }

    @Override
    public void updateStep() {
        double currentX = missionAircraft.getMapXLocation();
        double currentY = missionAircraft.getMapYLocation();
        double minutesPerStep = (60.0 / minutesPerUpdate);

        if(!isActive()) {
            log.debug("Waiting for mission start date...");
            return;
        }

        if(isClientMission) {
            log.debug("Client mission, should generate it...");
            shouldGenerate = true;
            return;
        }

        if(onStationEndDate != null && currentCampaignDate.before(onStationEndDate)) {
            log.debug("We're currently on station...");
            currentDir += 45;
            return;
        }

        if(nextWaypoint != null) {
            currentDir = Math.toRadians(getDirectionNextWaypoint() - 90);
            double currentSpeed = getNextWaypoint().getSpeedMilesPerHour();
            double distance = currentSpeed / (minutesPerStep * mapType.getMapScalePixelsPerMile());
            double newX = (currentX + (distance * Math.cos(currentDir)));
            double newY = (currentY + (distance * Math.sin(currentDir)));
            // If we are going to collide with the next waypoint on this movement,
            // then remove the waypoint, move the group to that location, and set rotation
            // to the next waypoint
            Waypoint nextWaypoint = getNextWaypoint();
            double waypointDistance = MathUtil.getDistance(currentX, currentY, nextWaypoint.getLocationX(), nextWaypoint.getLocationY());
            if (distance > waypointDistance) {
                nextWaypoint();
                missionAircraft.setMapXLocation(nextWaypoint.getLocationX());
                missionAircraft.setMapYLocation(nextWaypoint.getLocationY());
                setLastWaypoint(nextWaypoint);

                // If the next Waypoint is going to be the Mission point, tell
                // the generator that we want to generate this mission
                shouldGenerate = getNextWaypoint() != null && getNextWaypoint().getWaypointType().equals(WaypointType.MISSION);

                // Finally, check to see if we are on station
                if(lastWaypoint.getWaypointType().equals(WaypointType.MISSION)) {
                    if(timeOnStation != 0) {
                        isOnStation = true;
                        setOnStationEndDate();
                    }
                }
            } else {
                missionAircraft.setMapXLocation(newX);
                missionAircraft.setMapYLocation(newY);
            }
        } else {
            missionComplete = true;
        }
    }

    @Override
    public boolean shouldGenerateMission() {
        return shouldGenerate;
    }

    @Override
    public void setCurrentCampaignDate(Date date) {
        this.currentCampaignDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mission mission = (Mission) o;

        if (isClientMission != mission.isClientMission) return false;
        if (missionComplete != mission.missionComplete) return false;
        if (mapType != mission.mapType) return false;
        if (missionType != mission.missionType) return false;
        if (missionAircraft != null ? !missionAircraft.equals(mission.missionAircraft) : mission.missionAircraft != null)
            return false;
        if (missionWaypoints != null ? !missionWaypoints.equals(mission.missionWaypoints) : mission.missionWaypoints != null)
            return false;
        if (nextWaypoint != null ? !nextWaypoint.equals(mission.nextWaypoint) : mission.nextWaypoint != null)
            return false;
        return plannedMissionDate != null ? plannedMissionDate.equals(mission.plannedMissionDate) : mission.plannedMissionDate == null;
    }

    @Override
    public int hashCode() {
        int result = mapType != null ? mapType.hashCode() : 0;
        result = 31 * result + (missionType != null ? missionType.hashCode() : 0);
        result = 31 * result + (missionAircraft != null ? missionAircraft.hashCode() : 0);
        result = 31 * result + (missionWaypoints != null ? missionWaypoints.hashCode() : 0);
        result = 31 * result + (nextWaypoint != null ? nextWaypoint.hashCode() : 0);
        result = 31 * result + (plannedMissionDate != null ? plannedMissionDate.hashCode() : 0);
        result = 31 * result + (isClientMission ? 1 : 0);
        result = 31 * result + (missionComplete ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"Mission\":{"
                + "\"mapType\":\"" + mapType + "\""
                + ", \"missionType\":\"" + missionType + "\""
                + ", \"startingAirfield\":\"" + startingAirfield + "\""
                + ", \"missionAircraft\":" + missionAircraft
                + ", \"missionMunitions\":" + missionMunitions
                + ", \"plannedMissionDate\":" + plannedMissionDate
                + ", \"currentCampaignDate\":" + currentCampaignDate
                + ", \"onStationEndDate\":" + onStationEndDate
                + ", \"isOnStation\":\"" + isOnStation + "\""
                + ", \"timeOnStation\":\"" + timeOnStation + "\""
                + ", \"missionWaypoints\":" + missionWaypoints
                + ", \"nextWaypoint\":" + nextWaypoint
                + ", \"lastWaypoint\":" + lastWaypoint
                + ", \"isClientMission\":\"" + isClientMission + "\""
                + ", \"missionComplete\":\"" + missionComplete + "\""
                + ", \"shouldGenerate\":\"" + shouldGenerate + "\""
                + ", \"playerAircraft\":\"" + playerAircraft + "\""
                + "}}";
    }

    public static class Builder {
        private Mission mission;

        public Builder() {
            this.mission = new Mission();
        }

        public Builder setMissionMap(MapType map) {
            mission.setMapType(map);
            return this;
        }

        public Builder setMissionType(SubTaskType SubTaskType) {
            mission.setMissionType(SubTaskType);
            return this;
        }

        public Builder setMissionAircraft(UnitGroup<AirUnit> missionAircraft) {
            mission.setMissionAircraft(missionAircraft);
            return this;
        }

        public Builder setMissionWaypoints(List<Waypoint> waypoints) {
            mission.setMissionWaypoints(waypoints);
            mission.setNextWaypoint(waypoints.get(0));
            mission.setLastWaypoint(waypoints.get(0));
            return this;
        }

        public Builder setMissionDate(Date missionDate) {
            mission.setPlannedMissionDate(missionDate);
            return this;
        }

        public Builder setUpcomingMissionDate(Date currentDate, int minutesToMission) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.MINUTE, minutesToMission);
            setCurrentCampaignDate(currentDate);
            return setMissionDate(cal.getTime());
        }

        public Builder setIsClientMission(boolean isClientMission) {
            mission.setClientMission(isClientMission);
            return this;
        }

        public Builder setPlayerAircraftNumber(int playerAircraftNumber) {
            mission.setPlayerAircraft(playerAircraftNumber);
            return this;
        }

        public Builder setMissionComplete(boolean missionComplete) {
            mission.setMissionComplete(missionComplete);
            return this;
        }

        public Builder setUpdateRate(int minutesPerUpdate) {
            mission.setMinutesPerUpdate(minutesPerUpdate);
            return this;
        }

        public Builder setCurrentCampaignDate(Date currentCampaignDate) {
            mission.setCurrentCampaignDate(currentCampaignDate);
            return this;
        }

        public Builder setShouldGenerateMission(boolean shouldGenerateMission) {
            mission.setShouldGenerate(shouldGenerateMission);
            return this;
        }

        public Builder setStartingAirfield(AirfieldType startingAirfield) {
            mission.setStartingAirfield(startingAirfield);
            return this;
        }

        public Builder setMissionMunitions(Map<Integer,MunitionType> missionMunitions) {
            mission.setMissionMunitions(missionMunitions);
            return this;
        }

        public Builder setTimeOnStation(int length) {
            mission.setTimeOnStation(length);
            mission.setOnStation(false);
            return this;
        }

        public Builder setTarget(SimUnit target) {
            mission.setTarget(target);
            return this;
        }

        public Mission build() {
            mission = validate() ? mission : null;
            return mission;
        }

        private boolean validate() {
            boolean hasMap = mission.getMapType() != null;
            boolean hasType = mission.getMissionType() != null;
            boolean hasAircraft = mission.getMissionAircraft() != null;
            boolean hasWaypoints = mission.getMissionWaypoints() != null;
            boolean hasAirfield = mission.getStartingAirfield() != null;

            return hasMap && hasType && hasAircraft && hasWaypoints && hasAirfield;
        }
    }
}