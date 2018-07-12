package gen.main;

import gen.domain.Coalition;
import gen.domain.enums.AircraftType;
import gen.domain.enums.AirfieldType;
import gen.domain.enums.CampaignType;
import gen.domain.enums.FactionSide;
import gen.domain.enums.FactionStrength;
import gen.domain.enums.FactionType;
import gen.domain.enums.MapType;
import sim.domain.Airfield;
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
    private CampaignSettings campaignSettings;
    private Map<FactionSide, Integer> overallForceStrength;

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
        CampaignType type = campaignSettings.getSelectedCampaignType();
        List<AirfieldType> mapAirfields = campaignSettings.getSelectedMap().getAirfieldTypes();


        return null;
    }
}
