package sim.mission;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSideType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.ArmorGroundUnit;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.domain.unit.ground.defence.ArtilleryAirDefenceUnit;
import sim.domain.unit.ground.defence.MissileAirDefenceUnit;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

public class MissionSimulator {
    private static final Logger log = LogManager.getLogger(MissionSimulator.class);

    public void simulateMission(Mission mission, CampaignSettings campaignSettings, CoalitionManager blueforCoalitionManager, CoalitionManager redforCoalitionManager) {
        log.debug("Simulating mission....");

        UnitGroup<AirUnit> attackingAircraft = mission.getMissionAircraft();
        FactionSideType missionSide = attackingAircraft.getSide();
        CoalitionManager enemyManager = missionSide.equals(campaignSettings.getPlayerSelectedSide()) ? redforCoalitionManager : blueforCoalitionManager;
        Point2D.Double missionLocation = new Point2D.Double(mission.getLastWaypoint().getLocationX(), mission.getLastWaypoint().getLocationY());
        List<UnitGroup<AirDefenceUnit>> missionEnemyAirDefence = searchForAirDefences(missionLocation, enemyManager, 50);

        switch (mission.getMissionType()) {
            case AIRLIFT:
            case TRANSPORT:
                simulateTransportMission(mission, attackingAircraft, enemyManager);
                break;
            case BDA:
                break;
            case CAS:
            case GROUND_STRIKE:
            case INTERDICTION:
            case LOW_LEVEL_STRIKE:
                simulateGroundMission(mission, attackingAircraft, missionEnemyAirDefence, enemyManager);
                break;
            case CAP:
            case ESCORT:
            case INTERCEPT:
                simulateAirMission(mission, attackingAircraft, enemyManager);
                break;
            case FAC:
                break;
            case RECON:
                break;
            case SEAD:
            case DEAD:
                simulateSEADMission(mission, attackingAircraft, missionEnemyAirDefence, enemyManager);
                break;
            case BOMBER:
                break;
        }
    }

    private List<UnitGroup<AirDefenceUnit>> searchForAirDefences(Point2D.Double missionLocation, CoalitionManager enemyManager, int radiusToSearch) {
        List<UnitGroup<AirDefenceUnit>> enemyAirDefences = enemyManager.getCoalitionAirDefences();
        List<UnitGroup<AirDefenceUnit>> returnDefences;
        Ellipse2D.Double bounds = new Ellipse2D.Double(missionLocation.getX() - (radiusToSearch / 2), missionLocation.getY() - (radiusToSearch / 2), radiusToSearch, radiusToSearch);
        returnDefences = enemyAirDefences.stream().filter(g -> bounds.contains(g.getMapXLocation(), g.getMapYLocation())).collect(Collectors.toList());
        return returnDefences;
    }

    private void simulateTransportMission(Mission mission, UnitGroup<AirUnit> attackingAircraft, CoalitionManager enemyManager) {
        log.debug("Simulating a Transport mission....");
        // If we're near the mission start, pick up a unit/muntions and transport them either
        // to the cell we were told to go to, or the front.

        // If we're neat the mission point, drop the unit off
    }

    private void simulateGroundMission(Mission mission, UnitGroup<AirUnit> attackingAircraft, List<UnitGroup<AirDefenceUnit>> missionEnemyAirDefence, CoalitionManager enemyManager) {
        log.debug("Simulating a ground attack mission....");

        // If we're doing ground strike missions, check the various types we can do
        if(mission.getMissionType().equals(SubTaskType.GROUND_STRIKE)) {
            log.debug("Simulating ground strike, checking airfield first; then checking for nearby units");

            // First, check if we are attacking an airfield
            AirfieldType airfield = AirfieldType.searchByCoordinates(attackingAircraft.getMapXLocation(), attackingAircraft.getMapYLocation());
            if (airfield != null) {
                simulateAttackOnAirfield(airfield, attackingAircraft, missionEnemyAirDefence, enemyManager);
            }

            // Check to see if there is an airfield within the threat grid

            // Otherwise, check to see if there are ground units / structures
        }

        if(mission.getMissionType().equals(SubTaskType.CAS) || mission.getMissionType().equals(SubTaskType.INTERDICTION)) {
            log.debug("Simulating ground unit attack");
        }
    }

    private void simulateAirMission(Mission mission, UnitGroup<AirUnit> attackingAircraft, CoalitionManager enemyManager) {
        log.debug("Simulating an Air Mission...");
        // Air missions are continuous, IE. run during the entire mission length. Thus,
        // we need to check for targets and abort if we do not find any.
    }

    private void simulateSEADMission(Mission mission, UnitGroup<AirUnit> attackingAircraft, List<UnitGroup<AirDefenceUnit>> missionEnemyAirDefence, CoalitionManager enemyManager) {
        log.debug("Simulating a SEAD Mission...");
    }

    private void simulateAttackOnAirfield(AirfieldType airfield, UnitGroup<AirUnit> attackingAircraft, List<UnitGroup<AirDefenceUnit>> missionEnemyAirDefence, CoalitionManager enemyManager) {
        log.debug("Simulating a ground attack mission on an airfield...");

        boolean attackOnStructure = DynamicCampaignSim.getRandomGen().nextInt(100) + 1 < 75;
        if(attackOnStructure) {
            simulateAttackOnAirfieldStructure(airfield, attackingAircraft, missionEnemyAirDefence, enemyManager);
        } else {
            simulateAttackOnAirfieldGroundUnitGroups(airfield, attackingAircraft, missionEnemyAirDefence, enemyManager);
        }
    }

    private void simulateAttackOnAirfieldStructure(AirfieldType airfield, UnitGroup<AirUnit> attackingAircraft, List<UnitGroup<AirDefenceUnit>> missionEnemyAirDefence, CoalitionManager enemyManager) {
        log.debug("Simulating an attack on Airfield Structures");
    }

    private void simulateAttackOnAirfieldGroundUnitGroups(AirfieldType airfield, UnitGroup<AirUnit> attackingAircraft, List<UnitGroup<AirDefenceUnit>> missionEnemyAirDefence, CoalitionManager enemyManager) {
        log.debug("Simulating an attack on Airfield Ground Unit Groups");
        // Get the data related to what we're attacking
        Map<Airfield, List<UnitGroup<GroundUnit>>> groundUnits = enemyManager.getCoalitionPointDefenceGroundUnits();
        Airfield airfieldUnderAttack = groundUnits.entrySet().stream().filter(a -> a.getKey().getAirfieldType().equals(airfield)).map(Map.Entry::getKey).findFirst().orElse(null);
        List<UnitGroup<GroundUnit>> groupsUnderAttack = groundUnits.entrySet().stream().filter(a -> a.getKey().getAirfieldType().equals(airfield)).map(Map.Entry::getValue).findFirst().orElse(null);

        // Break out if we didn't find an airfield
        if(airfieldUnderAttack == null || groupsUnderAttack == null) {
            return;
        }

        // Choose a random group that we actually will attack
        int rndIdx = DynamicCampaignSim.getRandomGen().nextInt(groupsUnderAttack.size());
        UnitGroup<GroundUnit> group = groupsUnderAttack.get(rndIdx);

        // Based on the air defence, automatically increase the chance
        // of destruction of a plane
        double adBoost = 0;
        for(UnitGroup<AirDefenceUnit> adGroup : missionEnemyAirDefence) {
            for(AirDefenceUnit u : adGroup.getGroupUnits()) {
                if(u.getClass().isAssignableFrom(ArtilleryAirDefenceUnit.class)) {
                    adBoost += 2.5;
                    continue;
                }

                if(u.getClass().isAssignableFrom(MissileAirDefenceUnit.class)) {
                    adBoost += 10;
                }
            }
        }

        // Attack the units within that group
        List<GroundUnit> units = group.getGroupUnits();
        List<GroundUnit> destroyedUnits = new ArrayList<>();
        for(AirUnit attackingCraft : attackingAircraft.getGroupUnits()) {
            double chancePlaneDestroy = adBoost;
            double targets = DynamicCampaignSim.getRandomGen().nextInt(units.size()) + 1;

            log.debug("Aircraft: " + attackingAircraft + " attacking " + targets + " targets");
            for(int i = 0; i < targets; i++) {
                // Get a random unit from the group
                GroundUnit attackedUnit = units.get(DynamicCampaignSim.getRandomGen().nextInt(units.size()));

                // Set the base chance of destruction
                double chanceDestroy = 20;
                if(attackedUnit instanceof ArmorGroundUnit) {
                    chancePlaneDestroy += 1;
                    chanceDestroy = 5;
                }

                // Check the munitions, and see scale chance based on that
                // mission.getMissionMunitions();

                // Check if we destroyed the unit
                int diceRoll = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1);
                boolean didDestroy = (diceRoll < chanceDestroy);
                if(didDestroy) {
                    log.debug("DESTROYED A VEHICLE...");
                    log.debug(attackedUnit);
                    destroyedUnits.add(attackedUnit);
                } else {
                    log.debug("Nothing destroyed...");
                }
            }
            units.removeAll(destroyedUnits);

            // Check to see if we were destroyed during this attack run
            int diceRoll = (DynamicCampaignSim.getRandomGen().nextInt(100) + 1);
            boolean planeDestroyed = (diceRoll < chancePlaneDestroy);
            if(planeDestroyed) {
                log.debug("DESTROYED THE PLANE...");
            }
        }

        // If we removed all the units, remove that UnitGroup, otherwise update the unit group
        if(units.isEmpty()) {
            log.debug("No units left! Removing group...");
            groupsUnderAttack.remove(rndIdx);
        } else {
            log.debug("Units left, updating group...");
            group.setGroupUnits(units);
            groupsUnderAttack.set(rndIdx, group);
        }

        // Set the group back to the manager
        log.debug(airfieldUnderAttack);
        groundUnits.put(airfieldUnderAttack, groupsUnderAttack);
        log.debug("Point Defences: " + groundUnits);
        enemyManager.setCoalitionPointDefenceGroundUnits(groundUnits);
        log.debug("Point Defences: " + enemyManager.getCoalitionPointDefenceGroundUnits());
    }
}
