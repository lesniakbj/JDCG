package sim.gen.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.command.AICommander;
import sim.ai.command.random.RandomAICommander;
import sim.ai.threat.ThreatGrid;
import sim.manager.CoalitionManager;

import java.util.Date;
import java.util.List;

public class ATOGenerator {
    private static final Logger log = LogManager.getLogger(ATOGenerator.class);

    private AICommander aiCommander;

    private static final int TIME_BETWEEN_PLANNING_MINUTES = 5;

    public ATOGenerator() {
        aiCommander = new RandomAICommander();
    }

    public List<AIAction> generateATO(CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager, List<AIAction> lastActionsTaken, Date currentCampaignDate) {
        // Next action...
        ThreatGrid currentThreatGrid = friendlyCoalitionManager.getMissionManager().getThreatGrid();

        // Have our AI commander determine the commands we are to take
        return aiCommander.generateCommanderActions(currentThreatGrid, currentCampaignDate, friendlyCoalitionManager, enemyCoalitionManager);
    }
}
