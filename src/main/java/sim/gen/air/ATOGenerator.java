package sim.gen.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.command.AICommander;
import sim.ai.command.random.RandomAICommander;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.ThreatGridCell;
import sim.campaign.DynamicCampaignSim;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

import java.awt.geom.Point2D;
import java.util.ArrayList;
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

    // Free Falcon does the following for tasking generation:
    //  1) Get list of mission requests and iterate them:
    //         a) Filter out the request in invalid conditions
    //         b) Only schedule the task if enough ticks have passed
    //         c) If the request is a diversion, create a divert, otherwise create a package
    //         d) Insert as an immediate or delayed mission

    // Other tasks done include:
    // Update needed info such as available aircraft, tanker tracks, AWACS location,
    // JSTAR location, and available squadrons
    public List<List<Mission>> generateAllMissions(CoalitionManager coalitionManager, CoalitionManager enemyCoalitionManager, CampaignSettings campaignSettings) {
        List<UnitGroup<AirUnit>> availableAircraft = coalitionManager.getCoalitionAirGroups();

        // Only plan if enough time has elapsed

        // Do the actual mission planning here
        ThreatGrid grd = coalitionManager.getMissionManager().getThreatGrid();
        List<ThreatGridCell> enemyCells = grd.getCellsLessThan(0.0);
        List<ThreatGridCell> friendlyCells = grd.getCellsGreaterThan(0.0);

        // Check to see if we need to build any diversions for existing missions

        // Evaluate the mission requests in the Mission Manager

        // Create Immediate or Delayed missions

        // 

        // Plan AWACS if we don't have one active
        if(coalitionManager.getMissionManager().isHasAWACSActive()) {
            log.debug("Generate an AWACS for the current missions");
        }

        // Plan Tankers if we don't have one active
        if(coalitionManager.getMissionManager().isHasTankerActive()) {
            log.debug("Generate a Tanker for the current missions");
        }

        return new ArrayList<>();
    }

}
