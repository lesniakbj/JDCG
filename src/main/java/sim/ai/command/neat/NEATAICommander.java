package sim.ai.command.neat;

import sim.ai.actions.AIAction;
import sim.ai.command.AICommander;
import sim.ai.threat.ThreatGrid;
import sim.manager.CoalitionManager;

import java.util.Date;
import java.util.List;

public class NEATAICommander implements AICommander {
    @Override
    public List<AIAction> generateCommanderActions(ThreatGrid currentThreatGrid, Date currentCampaignDate, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {
        return null;
    }
}
