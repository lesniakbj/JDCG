package sim.gen.air;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.gen.mission.AirUnitMissionGenerator;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

import java.util.Date;
import java.util.List;

public class PackageGenerator {
    private static final Logger log = LogManager.getLogger(PackageGenerator.class);

    private AirUnitMissionGenerator missionGenerator;

    public PackageGenerator() {
        this.missionGenerator = missionGenerator;
    }

    public void generateMissionPackages(CampaignSettings campaignSettings, List<AIAction> actions, CoalitionManager coalitionManager, Date currentCampaignDate) {
        for(AIAction action : actions) {
            log.debug("Generating mission for action: " + action);
        }
    }
}
