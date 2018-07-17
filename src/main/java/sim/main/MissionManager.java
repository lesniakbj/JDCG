package sim.main;

import sim.domain.Mission;

import java.util.ArrayList;
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
public class MissionManager {
    private List<Mission> plannedMissions;

    public MissionManager() {
        this.plannedMissions = new ArrayList<>();
    }

    public void addMission(Mission mission) {
        if(plannedMissions == null) {
            plannedMissions = new ArrayList<>();
        }
        plannedMissions.add(mission);
    }

    public List<Mission> getPlannedMissions() {
        return plannedMissions;
    }

    public void setPlannedMissions(List<Mission> plannedMissions) {
        this.plannedMissions = plannedMissions;
    }
}
