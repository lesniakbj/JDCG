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
import sim.main.CampaignSettings;
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
        CampaignSettings.setMapSelection(mapSelector.getSelectedMap());
        CampaignSettings.setBlueforCoalition(new Coalition(getCoalitionFactions(factionSelector, BLUEFOR)));
        CampaignSettings.setRedforCoalition(new Coalition(getCoalitionFactions(factionSelector, REDFOR)));
        CampaignSettings.setNeutralCoalition(new Coalition(getCoalitionFactions(factionSelector, NEUTRAL)));
        CampaignSettings.setSelectedEra(eraSelectionListener.getSelectedEra());
        CampaignSettings.setSelectedCampaignType(eraSelectionListener.getSelectedType());
        CampaignSettings.setPlayerSelectedSide(coalitionSelectionListener.getSelectedSide());
        CampaignSettings.setSelectedSquadron(squadronSelectionListener.getSelectedSquadron());

        // Update the overview panel
        overviewPanel.setMapSelection(CampaignSettings.getSelectedMap());
        overviewPanel.setSelectedEra(CampaignSettings.getSelectedEra());
        overviewPanel.setCampaignType(CampaignSettings.getSelectedCampaignType());
        overviewPanel.setSelectedSide(CampaignSettings.getPlayerSelectedSide());
        overviewPanel.setSelectedSquadron(CampaignSettings.getSelectedSquadron());
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
