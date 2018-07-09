package ui.listeners;

import static ui.util.ImageScaleUtil.NORMAL_IMAGE_RATIO;
import static ui.util.ImageScaleUtil.tryLoadImage;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import sim.domain.statics.AircraftType;
import sim.domain.statics.ConflictEra;
import sim.domain.statics.SquadronType;
import sim.domain.statics.Task;

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
 * Created by Brendan.Lesniak on 7/9/2018.
 */
public class SquadronSelectionListener implements ActionListener {
    private JComboBox<String> squadronBox;
    private JPanel squadronImagePanel;
    private JLabel squadronName;
    private JLabel squadronTasks;
    private JLabel squadronAircraft;
    private JLabel squadronActiveEras;

    private SquadronType selectedSquadron;

    private static final int MAP_WIDTH = 550;

    public SquadronSelectionListener(JComboBox<String> squadronBox, JPanel squadronImagePanel, JLabel squadronName, JLabel squadronTasks, JLabel squadronAircraft, JLabel squadronActiveEras) {
        this.squadronBox = squadronBox;
        this.squadronImagePanel = squadronImagePanel;
        this.squadronName = squadronName;
        this.squadronTasks = squadronTasks;
        this.squadronAircraft = squadronAircraft;
        this.squadronActiveEras = squadronActiveEras;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectedSquadron = SquadronType.fromName((String)squadronBox.getSelectedItem());

        if(selectedSquadron != null) {
            BufferedImage mapImage = tryLoadImage("/squadron/" + selectedSquadron.name().replace(" ", "_") + ".jpg");
            Image scaled = mapImage.getScaledInstance(MAP_WIDTH * 2, (int) ((MAP_WIDTH * 2) * NORMAL_IMAGE_RATIO), Image.SCALE_SMOOTH);
            squadronImagePanel.removeAll();
            squadronImagePanel.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.CENTER);
            squadronImagePanel.repaint();

            // Update the squadron status
            squadronName.setText(selectedSquadron.getSquadronName());
            squadronTasks.setText(selectedSquadron.getTaskList().stream().map(Task::getTaskName).collect(Collectors.joining(", ")));
            squadronAircraft.setText(selectedSquadron.getAircraftTypes().stream().map(AircraftType::getAircraftName).collect(Collectors.joining(", ")));
            squadronActiveEras.setText(selectedSquadron.getEra().stream().map(ConflictEra::getEraName).collect(Collectors.joining(", ")));
        }
    }

    public SquadronType getSelectedSquadron() {
        if(selectedSquadron != null) {
            return selectedSquadron;
        }
        return SquadronType.NONE;
    }
}
