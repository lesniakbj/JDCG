package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.Coalition;
import sim.domain.enums.FactionSide;
import sim.domain.enums.FactionType;
import sim.main.CampaignSettings;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private Map<FactionSide, List<Airfield>> generatedAirfields;

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
        if(generatedAirfields == null) {
            AirfieldGenerator gen = new AirfieldGenerator(overallForceStrength, MUNITION_COST);
            generatedAirfields = gen.generateAirfields(campaignSettings, overallForceStrength, STARTING_NUMBER_OF_AIRBASES);
        }
        return  generatedAirfields;
    }

    public Map<FactionSide,List<Airfield>> adjustAirfieldsIfNeeded(Map<FactionSide, List<Point2D.Double>> warfareFront, Map<FactionSide, List<Airfield>> generatedAirfields) {
        AirfieldGenerator gen = new AirfieldGenerator(overallForceStrength, MUNITION_COST);
        return gen.adjustAirfieldsToGeneratedFront(warfareFront, generatedAirfields);
    }

    public Map<FactionSide,List<Point2D.Double>> generateWarfareFront() {
        Map<FactionSide, List<Point2D.Double>> retMap = new LinkedHashMap<>();
        Map<FactionSide, List<Airfield>> airfields = generateAirfieldMap();
        List<Airfield> blueforAirfields = airfields.get(FactionSide.BLUEFOR);
        List<Airfield> redforAirfields = airfields.get(FactionSide.REDFOR);
        List<Airfield> frontFields = (blueforAirfields.size() < redforAirfields.size()) ? blueforAirfields : redforAirfields;
        FactionSide frontSide = (blueforAirfields.size() < redforAirfields.size()) ? FactionSide.BLUEFOR : FactionSide.REDFOR;

        double lowestX = Double.MAX_VALUE, lowestY = Double.MAX_VALUE;
        double highestX = 0, highestY = 0;
        for(Airfield a : frontFields) {
            double x = a.getAirfieldType().getAirfieldMapPosition().getX();
            double y = a.getAirfieldType().getAirfieldMapPosition().getY();

            if(x < lowestX) {
                lowestX = x;
            } else if(x > highestX) {
                highestX = x;
            }

            if(y < lowestY) {
                lowestY = y;
            } else if(y > highestY) {
                highestY = y;
            }
        }

        lowestX -= 20;
        lowestY -= 20;
        highestX += 20;
        highestY += 20;

        retMap.put(frontSide, Arrays.asList(new Point2D.Double(lowestX, lowestY), new Point2D.Double(lowestX, highestY), new Point2D.Double(highestX, highestY), new Point2D.Double(highestX, lowestY)));
        return retMap;
    }
}
