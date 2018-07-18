package sim.gen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.Coalition;
import sim.domain.GroundUnit;
import sim.domain.UnitGroup;
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
    private AirfieldGenerator airfieldGenerator;
    private GroundUnitGenerator groundUnitGenerator;

    // Static Generator Data
    private static final double AIRCRAFT_COST = .2;
    private static final double HELICOPTER_COST = .15;
    private static final double MUNITION_COST = .01;
    private static final double GROUND_UNIT_COST = .05;
    private static final double AAA_COST = .05;
    private static final double SAM_COST = .1;
    private static final int STARTING_NUMBER_OF_AIRBASES = 5;

    public CampaignGenerator(CampaignSettings campaignSettings) {
        this.campaignSettings = campaignSettings;

        // Populate the overall force strength of the side
        Map<FactionSide, Double> overallForceStrength = new HashMap<>();
        for(FactionSide side : FactionSide.values()) {
            double totalStrength = 0;
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
        groundUnitGenerator = new GroundUnitGenerator(overallForceStrength, GROUND_UNIT_COST);

    }

    public Map<FactionSide,List<Airfield>> generateAirfieldMap() {
        return airfieldGenerator.generateAirfields(campaignSettings, STARTING_NUMBER_OF_AIRBASES);
    }

    public Map<FactionSide,List<Airfield>> adjustAirfieldsIfNeeded(Map<FactionSide, List<Point2D.Double>> warfareFront, Map<FactionSide, List<Airfield>> generatedAirfields) {
        return airfieldGenerator.adjustAirfieldsToGeneratedFront(warfareFront, generatedAirfields);
    }

    public Map<FactionSide,List<Point2D.Double>> generateWarfareFront(Map<FactionSide, List<Airfield>> generatedAirfields) {
        log.debug("Generating warfare front based on the generated airfields...");
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

        lowestX -= 15;
        lowestY -= 15;
        highestX += 15;
        highestY += 15;

        retMap.put(frontSide, Arrays.asList(new Point2D.Double(lowestX, lowestY), new Point2D.Double(lowestX, highestY), new Point2D.Double(highestX, highestY), new Point2D.Double(highestX, lowestY)));
        log.debug("Created following bounding front: " + retMap);
        return retMap;
    }

    public Map<Airfield, List<UnitGroup<GroundUnit>>> generatePointDefenceUnits(Map<FactionSide, List<Airfield>> generatedAirfields, FactionSide side) {
        return groundUnitGenerator.generatePointDefenceUnits(campaignSettings, generatedAirfields, side);
    }

    public Date generateCampaignDate() {
        log.debug("Generating a new date for " + campaignSettings.getSelectedEra());
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
        log.debug("Date created: " + cal.getTime());
        return cal.getTime();
    }
}
