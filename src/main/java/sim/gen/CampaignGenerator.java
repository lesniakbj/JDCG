package sim.gen;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.gen.ThreatGridGenerator;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.FactionType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.global.Coalition;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.gen.air.AirUnitGenerator;
import sim.gen.ground.AirfieldGenerator;
import sim.gen.ground.GroundUnitGenerator;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

public class CampaignGenerator {
    private static final Logger log = LogManager.getLogger(CampaignGenerator.class);

    // Generators
    private CampaignSettings campaignSettings;
    private AirfieldGenerator airfieldGenerator;
    private GroundUnitGenerator groundUnitGenerator;
    private AirUnitGenerator airUnitGenerator;
    private ThreatGridGenerator threatGridGenerator;

    // Date
    private Map<FactionSideType, Double> overallForceStrength;

    // Static Generator Data
    private static final double MAX_ALLOWED_POINTS = 400;
    private static final double AIRCRAFT_COST = .5;
    private static final double HELICOPTER_COST = .25;
    private static final double MUNITION_COST = .005;
    private static final double GROUND_UNIT_COST = .05;
    private static final double AAA_COST = .25;
    private static final double SAM_COST = .5;
    private static final int STARTING_NUMBER_OF_AIRBASES = 5;

    public CampaignGenerator(CampaignSettings campaignSettings) {
        this.campaignSettings = campaignSettings;

        // Populate the overall force strength of the side
        overallForceStrength = new HashMap<>();
        for(FactionSideType side : FactionSideType.values()) {
            double totalStrength = 0;
            Coalition coalition = campaignSettings.getCoalitionBySide(side);
            for(FactionType type : coalition.getFactionTypeList()) {
                switch(type.getOverallStrength()) {
                    case HIGH:
                        totalStrength += 200;
                        break;
                    case MEDIUM:
                        totalStrength += 40;
                        break;
                    case LOW:
                        totalStrength += 10;
                        break;
                }
            }
            overallForceStrength.put(side, Math.min(totalStrength, MAX_ALLOWED_POINTS));
        }

        // Create the generators
        airfieldGenerator = new AirfieldGenerator(overallForceStrength, MUNITION_COST);
        groundUnitGenerator = new GroundUnitGenerator(overallForceStrength, GROUND_UNIT_COST, AAA_COST, SAM_COST);
        airUnitGenerator = new AirUnitGenerator(overallForceStrength, AIRCRAFT_COST, HELICOPTER_COST);
        threatGridGenerator = new ThreatGridGenerator();
    }

    public Map<FactionSideType,List<Airfield>> generateAirfieldMap() {
        return airfieldGenerator.generateAirfields(campaignSettings, STARTING_NUMBER_OF_AIRBASES);
    }

    public Map<FactionSideType,List<Airfield>> adjustAirfieldsIfNeeded(Map<FactionSideType, List<Point2D.Double>> warfareFront, Map<FactionSideType, List<Airfield>> generatedAirfields) {
        return airfieldGenerator.adjustAirfieldsToGeneratedFront(warfareFront, generatedAirfields);
    }

    public Map<Airfield, List<UnitGroup<GroundUnit>>> generatePointDefenceUnits(Map<FactionSideType, List<Airfield>> generatedAirfields, FactionSideType side) {
        return groundUnitGenerator.generatePointDefenceUnits(campaignSettings, generatedAirfields, side);
    }

    public List<UnitGroup<GroundUnit>> generateFrontlineGroundUnits(List<Point2D.Double> frontline, List<Point2D.Double> safeArea, FactionSideType side, List<Airfield> airfields) {
        return groundUnitGenerator.generateFrontlineUnits(campaignSettings, frontline, safeArea, side, airfields);
    }

    public List<UnitGroup<AirDefenceUnit>> generateAirDefences(List<UnitGroup<GroundUnit>> blueGroundUnits, List<Airfield> airfields, FactionSideType side) {
        return groundUnitGenerator.generateAirDefenceUnits(campaignSettings, blueGroundUnits, airfields, side);
    }

    public List<UnitGroup<AirUnit>> generateAirGroups(List<Airfield> airfields, FactionSideType side) {
        return airUnitGenerator.generateAircraftGroups(campaignSettings, airfields, side);
    }

    public ThreatGrid generateThreatGridForCoalition(CoalitionManager friendlyCoalition, CoalitionManager enemyCoalition, Rectangle2D.Double threatGridBounds, FactionSideType side) {
        return threatGridGenerator.generateThreatGridForCoalition(friendlyCoalition, enemyCoalition, threatGridBounds, side);
    }

    public Map<FactionSideType,List<Point2D.Double>> generateWarfareFront(Map<FactionSideType, List<Airfield>> generatedAirfields) {
        log.debug("Generating warfare front based on the generated airfields...");
        Map<FactionSideType, List<Point2D.Double>> retMap = new LinkedHashMap<>();
        List<Airfield> blueforAirfields = generatedAirfields.get(FactionSideType.BLUEFOR);
        List<Airfield> redforAirfields = generatedAirfields.get(FactionSideType.REDFOR);
        List<Airfield> frontFields = (blueforAirfields.size() < redforAirfields.size()) ? blueforAirfields : redforAirfields;
        FactionSideType frontSide = (blueforAirfields.size() < redforAirfields.size()) ? FactionSideType.BLUEFOR : FactionSideType.REDFOR;

        double lowestX = Double.MAX_VALUE, lowestY = Double.MAX_VALUE;
        double highestX = 0, highestY = 0;
        for(Airfield a : frontFields) {
            double x = a.getAirfieldType().getAirfieldMapPosition().getX();
            double y = a.getAirfieldType().getAirfieldMapPosition().getY();

            if(x < lowestX) {
                lowestX = x;
            }

            if(x > highestX) {
                highestX = x;
            }

            if(y < lowestY) {
                lowestY = y;
            }

            if(y > highestY) {
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

    public Map<FactionSideType, Double> getOverallForceStrength() {
        return overallForceStrength;
    }

    public Rectangle2D.Double generateThreatGridBounds(Map<FactionSideType, List<Airfield>> generatedAirfields) {
        List<Airfield> airfields = generatedAirfields.entrySet().stream().flatMap(es -> es.getValue().stream()).collect(Collectors.toList());

        double lowestX = Double.MAX_VALUE, lowestY = Double.MAX_VALUE;
        double highestX = Double.MIN_VALUE, highestY = Double.MIN_VALUE;
        for(Airfield a : airfields) {
            double x = a.getAirfieldType().getAirfieldMapPosition().getX();
            double y = a.getAirfieldType().getAirfieldMapPosition().getY();

            if(x < lowestX) {
                lowestX = x;
            }

            if(x > highestX) {
                highestX = x;
            }

            if(y < lowestY) {
                lowestY = y;
            }

            if(y > highestY) {
                highestY = y;
            }
        }

        lowestX -= 15;
        lowestY -= 15;
        highestX += 15;
        highestY += 15;
        log.debug(lowestX);
        log.debug(lowestY);
        log.debug(highestX);
        log.debug(lowestY);

        return new Rectangle2D.Double(lowestX, lowestY, (highestX - lowestX), (highestY - lowestY));
    }
}
