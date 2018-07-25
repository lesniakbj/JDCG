package sim.ai.command.random;

import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.ai.command.AICommander;
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
        // Before we do our generation, set the cells to ignore (this reduces the state space)
        // Note: Not needed in the random AI commander
        // setThreatCellsToIgnore.ignoreEmptyCells();

        // Get a list of all possible actions per unit
        List<List<AIAction>> generatedMoves = currentThreatGrid.generateAllPossibleMoves(friendlyCoalitionManager, enemyCoalitionManager);

        // "Plan" our next move
        if(!generatedMoves.isEmpty()) {
            // Choose a random move from each unit's movement list
            List<AIAction> finalList = new ArrayList<>();
            for(List<AIAction> actionList : generatedMoves) {
                if(!actionList.isEmpty()) {
                    finalList.add(actionList.get(DynamicCampaignSim.getRandomGen().nextInt(actionList.size())));
                }
            }

            // Finally, choose a random set of 3 units to move MAX
            return pruneList(finalList);
        }
        return new ArrayList<>();
    }

    private List<AIAction> pruneList(List<AIAction> finalList) {
        List<AIAction> pruned = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            pruned.add(finalList.get(DynamicCampaignSim.getRandomGen().nextInt(finalList.size())));
        }
        return pruned.stream().distinct().collect(Collectors.toList());
    }
}
