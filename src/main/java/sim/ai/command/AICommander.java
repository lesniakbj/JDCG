package sim.ai.command;

import java.util.List;
import sim.ai.actions.AIAction;
import sim.ai.actions.CommanderAction;
import sim.ai.threat.ThreatGrid;
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
public interface AICommander {
    List<AIAction> generateCommanderActions(ThreatGrid currentThreatGrid, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager);
}
