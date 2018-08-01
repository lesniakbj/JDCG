package sim.gen.mission;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.ai.threat.ThreatGrid;
import sim.ai.threat.ThreatGridCell;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AircraftType;
import sim.domain.enums.SubTaskType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.DefaultLoadouts;
import sim.domain.unit.air.Loadout;
import sim.domain.unit.air.Mission;
import sim.domain.unit.air.Waypoint;
import sim.domain.unit.air.WeaponStation;
import sim.domain.unit.global.Airfield;
import sim.gen.air.WaypointGenerator;
import sim.manager.CoalitionManager;
import sim.settings.CampaignSettings;

import java.awt.geom.Point2D;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class AirUnitMissionGenerator {
    private static final Logger log = LogManager.getLogger(AirUnitMissionGenerator.class);

    public static void generateTestMissionForCoalition(CampaignSettings campaign, CoalitionManager manager, Date date) {
        // Get all available units
        Map<Airfield, List<UnitGroup<AirUnit>>> airUnitStationMap = manager.getCoalitionAirGroupsMap();

        // Choose a random airfield from the map
        Airfield airfield = airUnitStationMap.keySet().stream().sorted(((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))).findAny().get();
        log.debug("Airfield to use: " + airfield.getAirfieldType().name());

        // Choose a random unit from the stationed units
        List<UnitGroup<AirUnit>> airfieldUnits = airUnitStationMap.get(airfield);
        if(airfieldUnits.isEmpty()) {
            return;
        }
        UnitGroup<AirUnit> group = airfieldUnits.remove(DynamicCampaignSim.getRandomGen().nextInt(airUnitStationMap.get(airfield).size()));
        log.debug(group.getGroupUnits().size());

        // Choose a random task the unit is capable of
        AircraftType aircraftType = group.getGroupUnits().get(0).getAircraftType();
        List<SubTaskType> tasks = aircraftType.getPossibleTasks();
        SubTaskType type = tasks.get(DynamicCampaignSim.getRandomGen().nextInt(tasks.size()));
        DefaultLoadouts loadouts = aircraftType.getDefaultLoadouts();
        List<WeaponStation> defaultMissionWeapons = null;
        if(loadouts != null) {
            defaultMissionWeapons = loadouts.getDefaultLoadouts().stream().filter(dl -> dl.getSubTaskType().equals(type)).map(Loadout::getWeaponStations).findFirst().orElse(null);
        }

        // Choose a random enemy airfield from the threat grid
        ThreatGrid grd = manager.getMissionManager().getThreatGrid();
        List<ThreatGridCell> cells = grd.getCellsLessThan(0.0);
        ThreatGridCell cell = cells.get(DynamicCampaignSim.getRandomGen().nextInt(cells.size()));
        Point2D.Double d = new Point2D.Double(cell.getMapX(), cell.getMapY());

        // Generate Waypoints
        List<Waypoint> generatedWaypoints = WaypointGenerator.generateMissionWaypoints(airfield.getAirfieldType().getAirfieldMapPosition(), d, type, campaign.getSelectedMap().getMapType());

        Mission.Builder builder = new Mission.Builder();
        builder.setMissionMap(campaign.getSelectedMap().getMapType())
                .setMissionType(type)
                .setMissionAircraft(group)
                .setMissionWaypoints(generatedWaypoints)
                .setUpcomingMissionDate(date, 10)
                .setIsClientMission(group.isPlayerGeneratedGroup())
                .setPlayerAircraftNumber(group.isPlayerGeneratedGroup() ? 1 : 0)
                .setMissionComplete(false)
                .setUpdateRate(1)
                .setShouldGenerateMission(false)
                .setStartingAirfield(airfield.getAirfieldType())
                .setTimeOnStation(30)
                .setMissionMunitions(defaultMissionWeapons);

        // Update the various parts of the coalition manager
        manager.getCoalitionMissionManager().addMission(builder.build());
        manager.updateCoalitionAirGroups(airfield, airfieldUnits);
    }
}
