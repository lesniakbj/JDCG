package gen.main;

import gen.domain.Coalition;
import gen.domain.GameMap;
import gen.domain.enums.AircraftType;
import gen.domain.enums.AirfieldType;
import gen.domain.enums.CampaignType;
import gen.domain.enums.FactionSide;
import gen.domain.enums.FactionStrength;
import gen.domain.enums.FactionType;
import gen.domain.enums.MapType;
import gen.domain.enums.MunitionType;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.Munition;
import sim.main.CampaignSettings;
import sim.util.MathUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static gen.domain.enums.CampaignType.DEFENSIVE;
import static gen.domain.enums.CampaignType.OFFENSIVE;

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
        CampaignType campaignType = campaignSettings.getSelectedCampaignType();
        GameMap map = campaignSettings.getSelectedMap();
        List<AirfieldType> mapAirfields = map.getAirfieldTypes();

        // Assign the home airfields
        AirfieldType blueforHomeAirfield = null;
        AirfieldType redforHomeAirfield = null;
        if(!map.getMapType().equals(MapType.NORMANDY)) {
            // Whichever airfield is furthest south is the bluefor home airfield
            blueforHomeAirfield = mapAirfields.stream().max(Comparator.comparing((a) -> a.getAirfieldMapPosition().getValue())).orElse(null);
            redforHomeAirfield = mapAirfields.stream().min(Comparator.comparing((a) -> a.getAirfieldMapPosition().getValue())).orElse(null);
        } else {
            redforHomeAirfield = mapAirfields.stream().max(Comparator.comparing((a) -> a.getAirfieldMapPosition().getValue())).orElse(null);
            blueforHomeAirfield = mapAirfields.stream().min(Comparator.comparing((a) -> a.getAirfieldMapPosition().getValue())).orElse(null);
        }

        // Assign the rest based on campaign type:
        //      1) DEFENSIVE - Closest 3 airbases to bluefor belong to bluefor, rest to redfor
        //      2) OFFENSIVE - Closest 3 airbases to redfor belong to redfor, rest to bluefor
        //      3) AOW - Closest 3 airbases to bluefor belong to bluefor, rest to neutral
        Map<FactionSide, List<AirfieldType>> factionAirfields = generateFactionAirfields(campaignType, mapAirfields, blueforHomeAirfield, redforHomeAirfield);

        // Before generating Airfields, use the overall force strength to generate all campaign munitions
        Map<AirfieldType, List<Munition>> airfieldMunitions = generateMunitionStockpiles(factionAirfields, overallForceStrength);

        // Using all of the assigned AirfieldTypes, generate actual Airfields that will be used during the campaign
        Map<FactionSide, List<Airfield>> airfieldList = generateRealAirfields(factionAirfields, airfieldMunitions);

        log.debug(airfieldList);

        return airfieldList;
    }

    private Map<FactionSide,List<Airfield>> generateRealAirfields(Map<FactionSide, List<AirfieldType>> factionAirfields, Map<AirfieldType, List<Munition>> airfieldMunitions) {
        Map<FactionSide, List<Airfield>> airfieldList = new HashMap<>();
        for(Map.Entry<FactionSide, List<AirfieldType>> airfieldEntry : factionAirfields.entrySet()) {
            List<Airfield> convertedAirfields = new ArrayList<>();
            for(AirfieldType airfieldType : airfieldEntry.getValue()) {
                Airfield airfield = new Airfield();
                airfield.setOwnerSide(airfieldEntry.getKey());
                airfield.setAirfieldType(airfieldType);
                airfield.setMunitionStockpile(airfieldMunitions.get(airfieldType));
                airfield.setCriticalStructures(null);
                convertedAirfields.add(airfield);
            }
            airfieldList.put(airfieldEntry.getKey(), convertedAirfields);
        }
        return airfieldList;
    }

    private Map<FactionSide,List<AirfieldType>> generateFactionAirfields(CampaignType campaignType, List<AirfieldType> mapAirfields, AirfieldType blueforHomeAirfield, AirfieldType redforHomeAirfield) {
        Map<FactionSide, List<AirfieldType>> factionAirfields = new HashMap<>();
        List<AirfieldType> closestAirfields;
        List<AirfieldType> others;
        switch(campaignType) {
            case OFFENSIVE:
                closestAirfields = findClosestAirfields(STARTING_NUMBER_OF_AIRBASES, mapAirfields, redforHomeAirfield);
                closestAirfields.add(redforHomeAirfield);
                factionAirfields.put(FactionSide.REDFOR, closestAirfields);

                others = new ArrayList<>(mapAirfields);
                others.removeAll(closestAirfields);
                factionAirfields.put(FactionSide.BLUEFOR, others);
                break;
            case DEFENSIVE:
                closestAirfields = findClosestAirfields(STARTING_NUMBER_OF_AIRBASES, mapAirfields, blueforHomeAirfield);
                closestAirfields.add(blueforHomeAirfield);
                factionAirfields.put(FactionSide.BLUEFOR, closestAirfields);

                others = new ArrayList<>(mapAirfields);
                others.removeAll(closestAirfields);
                factionAirfields.put(FactionSide.REDFOR, others);
                break;
            case ALL_OUT_WAR:
                closestAirfields = findClosestAirfields(STARTING_NUMBER_OF_AIRBASES, mapAirfields, blueforHomeAirfield);
                closestAirfields.add(blueforHomeAirfield);
                factionAirfields.put(FactionSide.BLUEFOR, closestAirfields);

                closestAirfields = findClosestAirfields(STARTING_NUMBER_OF_AIRBASES, mapAirfields, redforHomeAirfield);
                closestAirfields.add(redforHomeAirfield);
                factionAirfields.put(FactionSide.REDFOR, closestAirfields);
                break;
        }
        return factionAirfields;
    }

    private List<AirfieldType> findClosestAirfields(int numToFind, List<AirfieldType> mapAirfields, AirfieldType airfield) {
        ArrayList<AirfieldType> closestAirfields = new ArrayList<>();
        List<AirfieldType> sortedByDistance = mapAirfields.stream().filter(ma -> !ma.equals(airfield)).sorted(Comparator.comparing(ma -> ma.getDistanceTo(airfield))).collect(Collectors.toList());
        for(int i = 0; i < numToFind; i++) {
            closestAirfields.add(sortedByDistance.get(i));
        }
        return closestAirfields;
    }

    private Map<AirfieldType,List<Munition>> generateMunitionStockpiles(Map<FactionSide, List<AirfieldType>> factionAirfields, Map<FactionSide, Integer> overallForceStrength) {
        return new HashMap<>();
    }
}
