package sim.domain;

import gen.domain.enums.AircraftType;
import gen.domain.enums.TaskType;
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
    private UnitGroup missionAircraft;
    private List<Waypoint> missionWaypoints;
    private Date plannedMissionDate;
    private boolean isInProgress;
    private boolean isClientMission;

    public Mission() {
        this.missionType = TaskType.CAS;
        List<Aircraft> test = Arrays.asList(new Aircraft(AircraftType.A_10_C));
        this.missionAircraft = new UnitGroup<>(test);
        this.missionWaypoints = new ArrayList<>();
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

    public UnitGroup getMissionAircraft() {
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