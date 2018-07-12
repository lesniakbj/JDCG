package sim.domain;

import gen.domain.enums.AircraftType;
import gen.domain.enums.AirfieldType;
import gen.domain.enums.MapType;
import gen.domain.enums.TaskType;
import gen.main.WaypointGenerator;

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
public class Mission {
    private TaskType missionType;
    private UnitGroup<Aircraft> missionAircraft;
    private List<Waypoint> missionWaypoints;
    private Date plannedMissionDate;
    private boolean isInProgress;
    private boolean isClientMission;

    public Mission() {
        this.missionType = TaskType.CAS;

        // Sample for testing
        List<Aircraft> test = Arrays.asList(new Aircraft(AircraftType.FA_18C));
        this.missionAircraft = new UnitGroup<>(test);
        missionAircraft.setMapXLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getKey());
        missionAircraft.setMapYLocation(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getValue());

        // Sample for testing
        List<Waypoint> waypoints = WaypointGenerator.generateMissionWaypoints(AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getKey(), AirfieldType.AL_DHAFRA_AIRBASE.getAirfieldMapPosition().getValue(),
                AirfieldType.SIR_ABU_NUAYR.getAirfieldMapPosition().getKey(),  AirfieldType.SIR_ABU_NUAYR.getAirfieldMapPosition().getValue(), missionType);
        this.missionWaypoints = waypoints;
        this.plannedMissionDate = new Date();
        this.isInProgress = false;
        this.isClientMission = false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mission mission = (Mission) o;

        if (isInProgress != mission.isInProgress) return false;
        if (isClientMission != mission.isClientMission) return false;
        if (missionType != mission.missionType) return false;
        if (missionAircraft != null ? !missionAircraft.equals(mission.missionAircraft) : mission.missionAircraft != null)
            return false;
        if (missionWaypoints != null ? !missionWaypoints.equals(mission.missionWaypoints) : mission.missionWaypoints != null)
            return false;
        return plannedMissionDate != null ? plannedMissionDate.equals(mission.plannedMissionDate) : mission.plannedMissionDate == null;
    }

    @Override
    public int hashCode() {
        int result = missionType != null ? missionType.hashCode() : 0;
        result = 31 * result + (missionAircraft != null ? missionAircraft.hashCode() : 0);
        result = 31 * result + (missionWaypoints != null ? missionWaypoints.hashCode() : 0);
        result = 31 * result + (plannedMissionDate != null ? plannedMissionDate.hashCode() : 0);
        result = 31 * result + (isInProgress ? 1 : 0);
        result = 31 * result + (isClientMission ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "missionType=" + missionType +
                ", missionAircraft=" + missionAircraft +
                ", missionWaypoints=" + missionWaypoints +
                ", plannedMissionDate=" + plannedMissionDate +
                ", isInProgress=" + isInProgress +
                ", isClientMission=" + isClientMission +
                '}';
    }
}