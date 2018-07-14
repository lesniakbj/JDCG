package dcsgen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Mission;
import sim.domain.Waypoint;
import sim.main.CoalitionManager;

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
 * Created by Brendan.Lesniak on 7/13/2018.
 */
public class DCSMissionGenerator {
    private static final Logger log = LogManager.getLogger(DCSMissionGenerator.class);

    public void generateMission(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition) {
        // Find all of the missions/ground units within a certain radius of this mission's final WP
        Waypoint missionWp = mission.getMissionWaypoint();

    }
}
