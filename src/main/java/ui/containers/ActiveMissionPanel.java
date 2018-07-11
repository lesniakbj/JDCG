package ui.containers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sim.domain.Aircraft;
import sim.domain.Mission;

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
class ActiveMissionPanel extends JPanel {
    private Mission plannedMission;

    ActiveMissionPanel(Mission plannedMission) {
        this.plannedMission = plannedMission;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createRaisedBevelBorder()));

        JPanel topHalf = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topHalf.add(new JLabel("Type: " + plannedMission.getMissionType().getTaskName()));
        List<Aircraft> aircraftList = plannedMission.getMissionAircraft().getGroupUnits();
        Map<Aircraft, Integer> aircraftTypes = aircraftList.stream().collect(Collectors.toMap((a) -> a, (a) -> 1, (v1, v2) -> v1 + v2));
        for(Map.Entry<Aircraft, Integer> aircraftIntegerEntry : aircraftTypes.entrySet()) {
            topHalf.add(new JLabel("Aircraft: " + aircraftIntegerEntry.getKey().getAircraftType().getAircraftName() + "(" + aircraftIntegerEntry.getValue() + ")"));
        }

        JPanel bottomHalf = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomHalf.add(new JLabel("Date: " + plannedMission.getPlannedMissionDate()));

        setPreferredSize(new Dimension(300, 70));
        add(topHalf, BorderLayout.NORTH);
        add(bottomHalf, BorderLayout.CENTER);
    }

    public Mission getPlannedMission() {
        return plannedMission;
    }
}
