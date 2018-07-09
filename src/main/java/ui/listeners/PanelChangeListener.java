package ui.listeners;

import sim.domain.Coalition;
import sim.domain.statics.Faction;
import sim.domain.statics.FactionSide;
import sim.domain.statics.Squadron;
import sim.main.CampaignState;
import ui.containers.NewCampaignOverviewPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

import static sim.domain.statics.FactionSide.*;

public class PanelChangeListener implements ChangeListener {
    // Listeners used in panels, to make selections
    private MapSelectionListener mapSelector;
    private MoveFactionActionListener factionSelector;
    private EraSelectionListener eraSelectionListener;
    private NewCampaignOverviewPanel overviewPanel;

    public PanelChangeListener(MapSelectionListener mapSelector, MoveFactionActionListener factionSelector, EraSelectionListener eraSelectionListener, NewCampaignOverviewPanel overviewPanel) {
        this.mapSelector = mapSelector;
        this.factionSelector = factionSelector;
        this.eraSelectionListener = eraSelectionListener;
        this.overviewPanel = overviewPanel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Save campaign state
        CampaignState.setMapSelection(mapSelector.getSelectedMap());
        CampaignState.setBlueforCoalition(new Coalition(getCoalitionFactions(factionSelector, BLUFOR)));
        CampaignState.setRedforCoalition(new Coalition(getCoalitionFactions(factionSelector, REDFOR)));
        CampaignState.setNeutralCoalition(new Coalition(getCoalitionFactions(factionSelector, NEUTRAL)));

        if(eraSelectionListener.getSelectedEra() != null) {
            CampaignState.setSelectedEra(eraSelectionListener.getSelectedEra());
        }

        if(eraSelectionListener.getSelectedType() != null) {
            CampaignState.setSelectedCampaignType(eraSelectionListener.getSelectedType());
        }

        //if(squadronSelectionListener.getSelectedCoalition() != null) {
            CampaignState.setPlayerSelectedSide(FactionSide.BLUFOR);
        //}

        //if(squadronSelectionListener.getSelectedSquadron() != null) {
            CampaignState.setSelectedSquadron(Squadron.NONE);
        //}

        // Update the overview panel
        overviewPanel.setMapSelection(CampaignState.getSelectedMap());
        overviewPanel.setSelectedEra(CampaignState.getSelectedEra());
        overviewPanel.setCampaignType(CampaignState.getSelectedCampaignType());
        overviewPanel.setSelectedSide(CampaignState.getPlayerSelectedSide());
        overviewPanel.setSelectedSquadron(CampaignState.getSelectedSquadron());
        overviewPanel.repaint();
    }

    private List<Faction> getCoalitionFactions(MoveFactionActionListener factionSelector, FactionSide side) {
        JTable table = factionSelector.getFactionTable(side);
        List<Faction> factions = new ArrayList<>();
        for(int i = 0; i < table.getModel().getRowCount(); i++) {
            factions.add(Faction.fromName(table.getModel().getValueAt(i, 0).toString()));
        }
        return factions;
    }
}
