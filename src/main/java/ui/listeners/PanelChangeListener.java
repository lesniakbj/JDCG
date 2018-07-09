package ui.listeners;

import static sim.domain.statics.FactionSide.BLUEFOR;
import static sim.domain.statics.FactionSide.NEUTRAL;
import static sim.domain.statics.FactionSide.REDFOR;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sim.domain.Coalition;
import sim.domain.statics.Faction;
import sim.domain.statics.FactionSide;
import sim.main.CampaignState;
import ui.containers.NewCampaignOverviewPanel;

public class PanelChangeListener implements ChangeListener {
    // Listeners used in panels, to make selections
    private MapSelectionListener mapSelector;
    private MoveFactionActionListener factionSelector;
    private EraSelectionListener eraSelectionListener;
    private CoalitionItemListener coalitionSelectionListener;
    private SquadronSelectionListener squadronSelectionListener;
    private NewCampaignOverviewPanel overviewPanel;

    public PanelChangeListener(MapSelectionListener mapSelector, MoveFactionActionListener factionSelector,
            EraSelectionListener eraSelectionListener, CoalitionItemListener coalitionSelectionListener,
            SquadronSelectionListener squadronSelectionListener, NewCampaignOverviewPanel overviewPanel) {
        this.mapSelector = mapSelector;
        this.factionSelector = factionSelector;
        this.eraSelectionListener = eraSelectionListener;
        this.coalitionSelectionListener = coalitionSelectionListener;
        this.squadronSelectionListener = squadronSelectionListener;
        this.overviewPanel = overviewPanel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Save campaign state
        CampaignState.setMapSelection(mapSelector.getSelectedMap());
        CampaignState.setBlueforCoalition(new Coalition(getCoalitionFactions(factionSelector, BLUEFOR)));
        CampaignState.setRedforCoalition(new Coalition(getCoalitionFactions(factionSelector, REDFOR)));
        CampaignState.setNeutralCoalition(new Coalition(getCoalitionFactions(factionSelector, NEUTRAL)));
        CampaignState.setSelectedEra(eraSelectionListener.getSelectedEra());
        CampaignState.setSelectedCampaignType(eraSelectionListener.getSelectedType());
        CampaignState.setPlayerSelectedSide(coalitionSelectionListener.getSelectedSide());
        CampaignState.setSelectedSquadron(squadronSelectionListener.getSelectedSquadron());

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
