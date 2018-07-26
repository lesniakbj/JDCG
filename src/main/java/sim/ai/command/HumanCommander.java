package sim.ai.command;

import java.util.Date;
import java.util.List;
import sim.ai.actions.AIAction;
import sim.ai.threat.ThreatGrid;
import sim.manager.CoalitionManager;

public class HumanCommander implements AICommander {
    @Override
    public List<AIAction> generateCommanderActions(ThreatGrid currentThreatGrid, Date currentCampaignDate, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {
        // Ask the user what they want to do here....
        return null;
    }
}
