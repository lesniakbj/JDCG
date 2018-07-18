package sim.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.MapType;
import sim.domain.enums.MunitionType;
import sim.domain.enums.TaskType;
import sim.domain.enums.WaypointType;
import sim.exception.InvalidMissionException;
import sim.util.MathUtil;

import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (c) Copyright 2018 Calabrio, Inc.
 * All Rights Reserved. www.calabrio.com LICENSED MATERIALS
 * Property of Calabrio, Inc., Minnesota, USA
 * <p>
 * No part of this publication may be reproduced, stored or transmitted,
 * in any form or by any means (electronic, mechanical, photocopying,
 * recording or otherwise) without prior written permission from Calabrio Software.
 * <p>
 *
 * Created by Brendan.Lesniak on 7/11/2018.
 */
public class Mission implements Simable {
    private static final Logger log = LogManager.getLogger(Mission.class);

    private MapType mapType;
    private TaskType missionType;
    private UnitGroup<Aircraft> missionAircraft;
    private List<Waypoint> missionWaypoints;
    private Waypoint nextWaypoint;
    private Waypoint lastWaypoint;
    private Date plannedMissionDate;
    private boolean isClientMission;
    private int playerAircraft;
    private boolean missionComplete;
    private static int minutesPerUpdate;
    private Date currentCampaignDate;
    private boolean shouldGenerate;
    private AirfieldType startingAirfield;
    private Map<Integer,MunitionType> missionMunitions;

    private Mission() {}

    public TaskType getMissionType() {
        return missionType;
    }

    public void setMissionType(TaskType missionType) {
        this.missionType = missionType;
    }

    public UnitGroup<Aircraft> getMissionAircraft() {
        return missionAircraft;
    }

    public void setMissionAircraft(UnitGroup missionAircraft) {
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

    @Override
    public void updateStep() {
        if(!isActive()) {
            log.debug("Waiting for mission start date...");
            return;
        }

        if(isClientMission) {
            log.debug("Client mission, should generate it...");
            shouldGenerate = true;
            return;
        }

        if(nextWaypoint != null) {
            double currentX = missionAircraft.getMapXLocation();
            double currentY = missionAircraft.getMapYLocation();
            double currentDirection = Math.toRadians(getDirectionNextWaypoint() - 90);
            double currentSpeed = getNextWaypoint().getSpeedMilesPerHour();

            double minutesPerStep = (60.0 / minutesPerUpdate);
            double pxDistance = currentSpeed / (minutesPerStep * mapType.getMapScalePixelsPerMile());

            double newX = (currentX + (pxDistance * Math.cos(currentDirection)));
            double newY = (currentY + (pxDistance * Math.sin(currentDirection)));

            // If we are going to collide with the next waypoint on this movement,
            // then remove the waypoint, move the group to that location, and set rotation
            // to the next waypoint
            Waypoint nextWaypoint = getNextWaypoint();
            double waypointDistance = MathUtil.getDistance(currentX, currentY, nextWaypoint.getLocationX(), nextWaypoint.getLocationY());
            log.debug(waypointDistance);
            log.debug(pxDistance);
            if (pxDistance > waypointDistance) {
                nextWaypoint();
                missionAircraft.setMapXLocation(nextWaypoint.getLocationX());
                missionAircraft.setMapYLocation(nextWaypoint.getLocationY());
                setLastWaypoint(nextWaypoint);

                // If the next Waypoint is going to be the Mission point, tell
                // the generator that we want to generate this mission
                shouldGenerate = getNextWaypoint() != null && getNextWaypoint().getWaypointType().equals(WaypointType.MISSION);
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
        return "Mission{" +
                "mapType=" + mapType +
                ", missionType=" + missionType +
                ", missionAircraft=" + missionAircraft +
                ", missionWaypoints=" + missionWaypoints +
                ", nextWaypoint=" + nextWaypoint +
                ", plannedMissionDate=" + plannedMissionDate +
                ", isClientMission=" + isClientMission +
                ", missionComplete=" + missionComplete +
                '}';
    }

    public void setLastWaypoint(Waypoint lastWaypoint) {
        this.lastWaypoint = lastWaypoint;
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

        public Builder setMissionType(TaskType taskType) {
            mission.setMissionType(taskType);
            return this;
        }

        public Builder setMissionAircraft(UnitGroup<Aircraft> missionAircraft) {
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