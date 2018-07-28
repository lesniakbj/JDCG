package dcsgen.file.translate;

import dcsgen.file.mission.domain.RequiredModules;
import dcsgen.file.mission.domain.mission.DCSMission;
import dcsgen.file.mission.domain.mission.MissionDate;
import dcsgen.file.mission.domain.mission.MissionResults;
import dcsgen.file.mission.domain.mission.trigger.MissionTriggers;
import sim.domain.unit.air.Mission;
import sim.manager.CoalitionManager;

import java.util.Calendar;
import java.util.Date;

public class DCSMissionTranslator {

    public DCSMission translateSimMissionToDCSMission(Mission mission, CoalitionManager blueforCoalitionManager, CoalitionManager redforColaitionManager) {
        DCSMission translatedMission = new DCSMission();
        translatedMission.setRequiredModules(getRequiredModules(mission));
        translatedMission.setMissionDate(getMissionDate(mission));
        translatedMission.setMissionTriggers(getMissionTriggers(mission));
        translatedMission.setMissionResults(getMissionResults(mission));
        return translatedMission;
    }

    private MissionResults getMissionResults(Mission mission) {
        return new MissionResults();
    }

    private RequiredModules getRequiredModules(Mission mission) {
        return new RequiredModules();
    }

    private MissionDate getMissionDate(Mission mission) {
        // Get the numeric day, year, month
        Calendar cal = Calendar.getInstance();
        // cal.setTime(mission.getCurrentCampaignDate());
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return new MissionDate(day, year, month);
    }

    private MissionTriggers getMissionTriggers(Mission mission) {
        return new MissionTriggers();
    }

}
