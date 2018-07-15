package sim.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.enums.AircraftType;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.MapType;
import sim.domain.enums.TaskType;
import sim.domain.enums.WaypointType;
import sim.gen.WaypointGenerator;
import sim.util.MathUtil;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private Date plannedMissionDate;
    private boolean isInProgress;
    private boolean isClientMission;
    private int playerAircraft;
    private boolean missionComplete;
    private static int minutesPerUpdate;
    private Date currentCampaignDate;
    private boolean shouldGenerate;
    private AirfieldType startingAirfield;

    public Mission() {
        minutesPerUpdate = 0;
        this.mapType = MapType.PERSIAN_GULF;
        this.missionType = TaskType.CAS;

        // Sample for testing
        List<Aircraft> test = new ArrayList<>(Arrays.asList(new Aircraft(AircraftType.FA_18C)));
        this.missionAircraft = new UnitGroup<>(test);
        missionAircraft.setMapXLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getX());
        missionAircraft.setMapYLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getY());

        // Sample for testing
        List<Waypoint> waypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getX(), AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getY(),
                AirfieldType.SIR_ABU_NUAYR.getAirfieldMapPosition().getX(),  AirfieldType.SIR_ABU_NUAYR.getAirfieldMapPosition().getY(), missionType, MapType.PERSIAN_GULF);

        this.missionWaypoints = waypoints;
        this.nextWaypoint = waypoints.get(0);
        this.plannedMissionDate = new Date();
        this.isInProgress = false;
        this.isClientMission = false;
        this.playerAircraft = 0;
        this.missionComplete = false;
        this.shouldGenerate = false;
        this.currentCampaignDate = plannedMissionDate;
        this.startingAirfield = null;
    }

    public Mission(int n) {
        minutesPerUpdate = 0;
        this.mapType = MapType.PERSIAN_GULF;
        this.missionType = TaskType.CAS;

        // Sample for testing
        List<Aircraft> test = new ArrayList<>(Arrays.asList(new Aircraft(AircraftType.FA_18C)));
        this.missionAircraft = new UnitGroup<>(test);
        missionAircraft.setMapXLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getX());
        missionAircraft.setMapYLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getY());

        // Sample for testing
        List<Waypoint> waypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getX(), AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getY(),
                AirfieldType.BANDAR_LENGEH.getAirfieldMapPosition().getX(),  AirfieldType.BANDAR_LENGEH.getAirfieldMapPosition().getY(), missionType, MapType.PERSIAN_GULF);

        this.missionWaypoints = waypoints;
        this.nextWaypoint = waypoints.get(0);
        this.plannedMissionDate = new Date();
        this.isInProgress = false;
        this.isClientMission = false;
        this.playerAircraft = 0;
        this.missionComplete = false;
        this.shouldGenerate = false;
        this.currentCampaignDate = plannedMissionDate;
        this.startingAirfield = null;
    }

    public Mission(Date time) {
        minutesPerUpdate = 0;
        this.mapType = MapType.PERSIAN_GULF;
        this.missionType = TaskType.CAS;

        // Sample for testing
        List<Aircraft> test = new ArrayList<>(Arrays.asList(new Aircraft(AircraftType.FA_18C)));
        this.missionAircraft = new UnitGroup<>(test);
        missionAircraft.setMapXLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getX());
        missionAircraft.setMapYLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getY());

        // Sample for testing
        List<Waypoint> waypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getX(), AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getY(),
                AirfieldType.SIR_ABU_NUAYR.getAirfieldMapPosition().getX(),  AirfieldType.SIR_ABU_NUAYR.getAirfieldMapPosition().getY(), missionType, MapType.PERSIAN_GULF);

        this.missionWaypoints = waypoints;
        this.nextWaypoint = waypoints.get(0);
        this.plannedMissionDate = time;
        this.isInProgress = false;
        this.isClientMission = false;
        this.playerAircraft = 0;
        this.missionComplete = false;
        this.shouldGenerate = false;
        this.currentCampaignDate = plannedMissionDate;
        this.startingAirfield = null;
    }

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

    public boolean isInProgress() {
        return isInProgress;
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
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

    @Override
    public void updateStep() {
        if(currentCampaignDate.before(plannedMissionDate)) {
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

                // If the next Waypoint is going to be the Mission point, tell
                // the generator that we want to generate this mission
                shouldGenerate = getNextWaypoint().getWaypointType().equals(WaypointType.MISSION);
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

        if (isInProgress != mission.isInProgress) return false;
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
        result = 31 * result + (isInProgress ? 1 : 0);
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
                ", isInProgress=" + isInProgress +
                ", isClientMission=" + isClientMission +
                ", missionComplete=" + missionComplete +
                '}';
    }
}