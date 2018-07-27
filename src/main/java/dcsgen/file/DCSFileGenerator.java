package dcsgen.file;

import sim.domain.enums.MissionStartType;
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;

import java.util.List;

public interface DCSFileGenerator {
    List<String> generateFileString(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType);
}
