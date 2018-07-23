package sim.ai.command;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.actions.GenerationResult;
import sim.ai.threat.ThreatGrid;
import sim.campaign.DynamicCampaignSim;
import sim.manager.CoalitionManager;

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
 * Created by Brendan.Lesniak on 7/23/2018.
 */
public class RandomAICommander implements AICommander {
    private static final Logger log = LogManager.getLogger(RandomAICommander.class);

    private Date lastUpdateDate;
    private int timeBetweenPlanningInMinutes;

    public RandomAICommander(int timeBetweenPlanningInMinutes) {
        this.timeBetweenPlanningInMinutes = timeBetweenPlanningInMinutes;
    }

    @Override
    public List<AIAction> generateCommanderActions(ThreatGrid currentThreatGrid, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {
        List<GenerationResult> generatedMoves = ThreatGrid.generateAllPossibleMoves(currentThreatGrid, friendlyCoalitionManager, enemyCoalitionManager);
        if(!generatedMoves.isEmpty()) {
            return generatedMoves.get(DynamicCampaignSim.getRandomGen().nextInt(generatedMoves.size())).getActionsTaken();
        }
        return null;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
