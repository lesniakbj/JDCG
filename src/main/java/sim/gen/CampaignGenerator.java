package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.Coalition;
import sim.domain.enums.FactionSide;
import sim.domain.enums.FactionType;
import sim.main.CampaignSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Created by Brendan.Lesniak on 7/11/2018.
 */
public class CampaignGenerator {
    private static final Logger log = LogManager.getLogger(CampaignGenerator.class);

    private CampaignSettings campaignSettings;
    private Map<FactionSide, Integer> overallForceStrength;

    // Static Generator Data
    private static final double AIRCRAFT_COST = 2;
    private static final double HELICOPTER_COST = 1;
    private static final double MUNITION_COST = .25;
    private static final double GROUND_UNIT_COST = .5;
    private static final double AAA_COST = 1;
    private static final double SAM_COST = 2;
    private static final int STARTING_NUMBER_OF_AIRBASES = 5;

    public CampaignGenerator(CampaignSettings campaignSettings) {
        this.campaignSettings = campaignSettings;

        // Populate the overall force strength of the side
        overallForceStrength = new HashMap<>();
        for(FactionSide side : FactionSide.values()) {
            int totalStrength = 0;
            Coalition coalition = campaignSettings.getCoalitionBySide(side);
            for(FactionType type : coalition.getFactionTypeList()) {
                switch(type.getOverallStrength()) {
                    case HIGH:
                        totalStrength += 200;
                        break;
                    case MEDIUM:
                        totalStrength += 100;
                        break;
                    case LOW:
                        totalStrength += 50;
                        break;
                }
            }
            overallForceStrength.put(side, totalStrength);
        }
    }

    public Map<FactionSide,List<Airfield>> generateAirfieldMap() {
        AirfieldGenerator gen = new AirfieldGenerator();
        return gen.generateAirfields(campaignSettings, overallForceStrength, STARTING_NUMBER_OF_AIRBASES);
    }
}
