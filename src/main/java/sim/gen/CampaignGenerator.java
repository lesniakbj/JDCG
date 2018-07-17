package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.Coalition;
import sim.domain.enums.FactionSide;
import sim.domain.enums.FactionType;
import sim.main.CampaignSettings;
import sim.main.DynamicCampaignSim;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
    private AirfieldGenerator airfieldGenerator;

    // Static Generator Data
    private static final double AIRCRAFT_COST = 1;
    private static final double HELICOPTER_COST = .5;
    private static final double MUNITION_COST = .1;
    private static final double GROUND_UNIT_COST = .25;
    private static final double AAA_COST = .5;
    private static final double SAM_COST = 1;
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

        // Create the generators
        airfieldGenerator = new AirfieldGenerator(overallForceStrength, MUNITION_COST);
    }

    public Map<FactionSide,List<Airfield>> generateAirfieldMap() {
        if(generatedAirfields == null) {
            generatedAirfields = airfieldGenerator.generateAirfields(campaignSettings, overallForceStrength, STARTING_NUMBER_OF_AIRBASES);
        }
        return  generatedAirfields;
    }

    public Map<FactionSide,List<Airfield>> adjustAirfieldsIfNeeded(Map<FactionSide, List<Point2D.Double>> warfareFront, Map<FactionSide, List<Airfield>> generatedAirfields) {
        return airfieldGenerator.adjustAirfieldsToGeneratedFront(warfareFront, generatedAirfields);
    }

    public Map<FactionSide,List<Point2D.Double>> generateWarfareFront(Map<FactionSide, List<Airfield>> generatedAirfields) {
        Map<FactionSide, List<Point2D.Double>> retMap = new LinkedHashMap<>();
        List<Airfield> blueforAirfields = generatedAirfields.get(FactionSide.BLUEFOR);
        List<Airfield> redforAirfields = generatedAirfields.get(FactionSide.REDFOR);
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

    public Date generateCampaignDate() {
        int year = 0, mo = 0, day = 0, hour = 0, minute = 0;
        switch(campaignSettings.getSelectedEra()) {
            case WWII:
                year = 1940 + DynamicCampaignSim.getRandomGen().nextInt(5);
                break;
            case KOREA:
                year = 1950 + DynamicCampaignSim.getRandomGen().nextInt(4);
                break;
            case VIETNAM:
                year = 1960 + DynamicCampaignSim.getRandomGen().nextInt(16);
                break;
            case GULF_WAR:
                year = 1990 + DynamicCampaignSim.getRandomGen().nextInt(3);
                break;
            case MODERN:
                year = 2000 + DynamicCampaignSim.getRandomGen().nextInt(19);
                break;
        }
        mo = DynamicCampaignSim.getRandomGen().nextInt(12) + 1;
        day = DynamicCampaignSim.getRandomGen().nextInt(25) + 1;
        hour = DynamicCampaignSim.getRandomGen().nextInt(23) + 1;
        minute = DynamicCampaignSim.getRandomGen().nextInt(59) + 1;

        // Generate the date
        Calendar cal = Calendar.getInstance();
        cal.set(year, mo, day, hour, minute);
        log.debug(cal.getTime());
        return cal.getTime();
    }
}
