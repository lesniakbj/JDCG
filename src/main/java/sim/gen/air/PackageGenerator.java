package sim.gen.air;

import java.util.ArrayList;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.actions.AIAction;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.Mission;
import sim.domain.unit.global.Airfield;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.gen.mission.AirUnitMissionGenerator;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

import java.util.Date;
import java.util.List;

public class PackageGenerator {
    private static final Logger log = LogManager.getLogger(PackageGenerator.class);

    private AirUnitMissionGenerator missionGenerator;

    public PackageGenerator() {
        this.missionGenerator = missionGenerator;
    }

    public List<List<Mission>> generateMissionPackages(CampaignSettings campaignSettings, List<AIAction> actions, CoalitionManager friendlyCoalitionManager, CoalitionManager enemyCoalitionManager, Date currentCampaignDate) {
        // Friendly Information
        List<Airfield> friendlyAirfields = friendlyCoalitionManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> friendlyAirDefences = friendlyCoalitionManager.getCoalitionAirDefences();
        List<UnitGroup<GroundUnit>> friendlyGroundUnits = friendlyCoalitionManager.getCoalitionFrontlineGroups();

        // Enemy Information
        List<Airfield> enemyAirfields = enemyCoalitionManager.getCoalitionAirfields();
        List<UnitGroup<AirDefenceUnit>> enemyAirDefences = enemyCoalitionManager.getCoalitionAirDefences();
        List<UnitGroup<GroundUnit>> enemyGroundUnits = enemyCoalitionManager.getCoalitionFrontlineGroups();
        List<UnitGroup<AirUnit>> enemyActiveAirGroups = enemyCoalitionManager.getMissionManager().getPlannedMissions().stream().filter(Mission::isActive).map(Mission::getMissionAircraft).collect(Collectors.toList());

        // For each action, generate a package of missions
        for(AIAction action : actions) {
            UnitGroup<AirUnit> g = action.getActionGroup();
            Airfield startingAirfield = friendlyAirfields.stream().filter(a -> a.getAirfieldType().getAirfieldMapPosition().getX() == g.getMapXLocation() && a.getAirfieldType().getAirfieldMapPosition().getY() == g.getMapYLocation()).findFirst().orElse(null);

            if(startingAirfield == null) {
                continue;
            }

            // Select other aircraft to support this mission
            // If the mission is not Intercept or a Helicopter:
            //     - Select CAP/Escort planes
            //     - Send CAP/Escort to location 5-10 minutes BEFORE mission flight
            //     - Have CAP/Escort stay on station for duration of mission
            List<UnitGroup<AirUnit>> units = friendlyCoalitionManager.getCoalitionAirGroupsMap().get(startingAirfield);
            log.debug("Removing unit: " + g);
        }

        return new ArrayList<>();
    }
}
