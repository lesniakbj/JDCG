package ui.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import sim.domain.Coalition;
import sim.domain.statics.Faction;
import sim.domain.statics.FactionSide;
import sim.domain.statics.SquadronType;
import sim.main.CampaignState;

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
public class CoalitionItemListener implements ItemListener {
    private JComboBox<String> squadronBox;

    private FactionSide selectedSide;

    public CoalitionItemListener(JComboBox<String> squadronBox) {
        this.squadronBox = squadronBox;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Get the faction side that was selected, as well as the factions associated with that side
        FactionSide side = FactionSide.valueOf(((JRadioButton)e.getSource()).getText());
        Coalition coalition = CampaignState.getCoalitionBySide(side);
        List<Faction> coalitionFactions = coalition.getFactionList();
        selectedSide = side;

        // Go through the Factions and get the selectable squadrons
        List<SquadronType> validSquadrons = new ArrayList<>();
        for(SquadronType sqd : SquadronType.values()) {
            for(Faction faction : coalitionFactions) {
                if(sqd.getFactionUser().equals(faction) && sqd.getEra().contains(CampaignState.getSelectedEra())) {
                    validSquadrons.add(sqd);
                }
            }
        }

        // Assign the squadrons to the drop down box
        DefaultComboBoxModel model = (DefaultComboBoxModel)squadronBox.getModel();
        model.removeAllElements();
        for(SquadronType squadronType : validSquadrons) {
            model.addElement(squadronType.getSquadronName());
        }
        squadronBox.setModel(model);
    }

    public FactionSide getSelectedSide() {
        if(selectedSide != null) {
            return selectedSide;
        }
        return FactionSide.BLUEFOR;
    }
}
