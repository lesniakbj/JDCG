package sim.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.domain.Airfield;
import sim.domain.GroundUnit;
import sim.domain.Mission;
import sim.domain.UnitGroup;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.ArmorGroundUnit;
import sim.domain.enums.FactionSide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MissionSimulator {
    private static final Logger log = LogManager.getLogger(MissionSimulator.class);

    public static void simulateMission(Mission mission, CampaignSettings campaignSettings, CoalitionManager blueforCoalitionManager, CoalitionManager redforCoalitionManager) {
        log.debug("Simulating mission....");

        FactionSide missionSide = mission.getMissionAircraft().getSide();
        CoalitionManager enemyManager = missionSide.equals(FactionSide.BLUEFOR) ? redforCoalitionManager : blueforCoalitionManager;

        switch (mission.getMissionType()) {
            case CAS:
            case GROUND_STRIKE:
                simulateGroundMission(mission, enemyManager);
                break;
            case CAP:
            case ESCORT:
            case INTERCEPT:
                break;
            case SEAD:
            case DEAD:
                break;
            case LOW_LEVEL_STRIKE:
                break;
        }
    }

    private static void simulateGroundMission(Mission mission, CoalitionManager enemyManager) {
        log.debug("Simulating a ground attack mission....");

        // First, check if we are attacking an airfield
        AirfieldType airfield = AirfieldType.searchByCoordinates(mission.getMissionAircraft().getMapXLocation(), mission.getMissionAircraft().getMapYLocation());
        if(airfield != null) {
            simulateAttackOnAirfield(airfield, enemyManager);
        }
    }

    private static void simulateAttackOnAirfield(AirfieldType airfield, CoalitionManager enemyManager) {
        log.debug("Simulating a ground attack mission on an airfield...");
        Map<Airfield, List<UnitGroup<GroundUnit>>> groundUnits = enemyManager.getCoalitionPointDefenceGroundUnits();
        Airfield airfieldUnderAttack = groundUnits.entrySet().stream().filter(a -> a.getKey().getAirfieldType().equals(airfield)).map(Map.Entry::getKey).findFirst().orElse(null);
        List<UnitGroup<GroundUnit>> groupsUnderAttack = groundUnits.entrySet().stream().filter(a -> a.getKey().getAirfieldType().equals(airfield)).map(Map.Entry::getValue).findFirst().orElse(null);

        // Choose a random group that we actually will attack
        int rndIdx = DynamicCampaignSim.getRandomGen().nextInt(groupsUnderAttack.size());
        UnitGroup<GroundUnit> group = groupsUnderAttack.get(rndIdx);

        log.debug("Group before destroy");
        log.debug(group);
        log.debug(groupsUnderAttack);
        log.debug(groundUnits);

        List<GroundUnit> units = group.getGroupUnits();
        List<GroundUnit> destroyedUnits = new ArrayList<>();
        double chancePlaneDestroy = 0;
        for(GroundUnit unit : units) {
            log.debug("Checking a unit for destruction...");
            double chanceDestroy = 20;
            if(unit instanceof ArmorGroundUnit) {
                chancePlaneDestroy += 1;
                chanceDestroy = 5;
            }

            // Check the munitions, and see scale chance based on that
            // mission.getMissionMunitions();
            int diceRoll = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1);
            boolean didDestroy = (diceRoll < chanceDestroy);
            if(didDestroy) {
                log.debug("DESTROYED A VEHICLE...");
                log.debug(unit);
                destroyedUnits.add(unit);
            } else {
                log.debug("Nothing destroyed...");
            }
        }
        units.removeAll(destroyedUnits);

        // Check to see if there will be any damage done to us

        // Set the group back to the manager
        group.setGroupUnits(units);
        groupsUnderAttack.set(rndIdx, group);
        groundUnits.put(airfieldUnderAttack, groupsUnderAttack);
        log.debug("Group set with destroyed units");
        log.debug(group);
        log.debug(groupsUnderAttack);
        log.debug(groundUnits);
        enemyManager.setCoalitionPointDefenceGroundUnits(groundUnits);
    }
}
