package sim.ai.command.minimax;

import sim.ai.actions.AIAction;
import sim.ai.command.AICommander;
import sim.ai.threat.ThreatGrid;
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
public class MiniMaxAICommander implements AICommander {

    @Override
    public List<AIAction> generateCommanderActions(ThreatGrid currentThreatGrid, Date currentCampaignDate,
            CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager) {
        return new ArrayList<>();
    }
}
