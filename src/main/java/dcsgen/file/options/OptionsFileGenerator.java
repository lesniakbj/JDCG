package dcsgen.file.options;

import dcsgen.file.DCSFileGenerator;
import sim.domain.enums.MissionStartType;
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;

import java.util.List;

public class OptionsFileGenerator implements DCSFileGenerator {
    @Override
    public List<String> generateFileString(Mission mission, CoalitionManager blueforCoalition, CoalitionManager redforCoalition, MissionStartType missionStartType) {
        return null;
    }
}
