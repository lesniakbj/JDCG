package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import sim.domain.statics.SquadronType;

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

    private SquadronType selectedSquadron;

    public SquadronSelectionListener(JComboBox<String> squadronBox, JPanel squadronImagePanel) {
        this.squadronBox = squadronBox;
        this.squadronImagePanel = squadronImagePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Changing stuff");
    }

    public SquadronType getSelectedSquadron() {
        if(selectedSquadron != null) {
            return selectedSquadron;
        }
        return SquadronType.NONE;
    }
}
