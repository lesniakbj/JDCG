package sim.ai.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.threat.ThreatGrid;
import sim.campaign.DynamicCampaignSim;
import sim.manager.CoalitionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public RandomAICommander() {}

    @Override
    public List<AIAction> generateCommanderActions(ThreatGrid currentThreatGrid, Date currentCampaignDate,
            CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {

        // "Plan" new actions
        List<List<AIAction>> generatedMoves = currentThreatGrid.generateAllPossibleMoves(friendlyCoalitionManager, enemyCoalitionManager);
        if(!generatedMoves.isEmpty()) {
            List<AIAction> finalList = new ArrayList<>();
            for(List<AIAction> actionList : generatedMoves) {
                if(!actionList.isEmpty()) {
                    finalList.add(actionList.get(DynamicCampaignSim.getRandomGen().nextInt(actionList.size())));
                }
            }
            return finalList;
        }
        return new ArrayList<>();
    }
}
