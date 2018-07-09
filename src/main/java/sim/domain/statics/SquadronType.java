package sim.domain.statics;

import static sim.domain.statics.util.StaticLists.ALL_TASKS;
import static sim.domain.statics.util.StaticLists.COMPELTE_ERAS;
import static sim.domain.statics.util.StaticLists.EXTENDED_ERAS;
import static sim.domain.statics.util.StaticLists.FLEET_DEFENSE;
import static sim.domain.statics.util.StaticLists.GROUND_STRIKE;
import static sim.domain.statics.util.StaticLists.GROUND_STRIKE_COMPLETE_ERAS;
import static sim.domain.statics.util.StaticLists.HIGH_RISK_GROUND_STRIKE;
import static sim.domain.statics.util.StaticLists.MARINE_FIGHTER_COMPLETE_ERAS;
import static sim.domain.statics.util.StaticLists.MARINE_GROUND_STRIKE_COMPLETE_ERAS;
import static sim.domain.statics.util.StaticLists.MARINE_STRIKE_FIGHTER_COMPLETE_ERAS;
import static sim.domain.statics.util.StaticLists.MODERN_ERAS;
import static sim.domain.statics.util.StaticLists.NAVY_COMPLETE_ERA_AIRCRAFT;
import static sim.domain.statics.util.StaticLists.NAVY_MODERN_ERA_AIRCRAFT;
import static sim.domain.statics.util.StaticLists.STRIKE_FIGHTER;
import static sim.domain.statics.util.StaticLists.USER_AVAILABLE_AIRCRAFT;

import java.util.List;

public enum SquadronType {
    // USA Air Force Squadrons
    FSQD25("25th FS - 25th Fighter Squadron - Assam Draggins", COMPELTE_ERAS, GROUND_STRIKE, GROUND_STRIKE_COMPLETE_ERAS),

    // USA Navy Squadrons
    VFA14("VFA-14 - Strike Fighter Squadron 14 - Tophatters", COMPELTE_ERAS, GROUND_STRIKE, NAVY_COMPLETE_ERA_AIRCRAFT),
    VFA31("VFA-31 - Strike Fighter Squadron 31 - Tomcatters", COMPELTE_ERAS, STRIKE_FIGHTER, NAVY_COMPLETE_ERA_AIRCRAFT),
    VFA154("VFA-154 - Strike Fighter Squadron 154 - Black Knights", COMPELTE_ERAS, FLEET_DEFENSE, NAVY_COMPLETE_ERA_AIRCRAFT),
    VFA103("VFA-103 - Strike Fighter Squadron 154 - Jolly Rogers", EXTENDED_ERAS, STRIKE_FIGHTER, NAVY_COMPLETE_ERA_AIRCRAFT),
    VFA94("VFA-94 - Strike Fighter Squadron 94 - Mighty Shrikes", EXTENDED_ERAS, HIGH_RISK_GROUND_STRIKE, NAVY_COMPLETE_ERA_AIRCRAFT),
    VFA2("VFA-2 - Strike Fighter Squadron 2 - Bounty Hunters", MODERN_ERAS, FLEET_DEFENSE, NAVY_MODERN_ERA_AIRCRAFT),

    // USA Marine Squadrons
    VMA214("VMA-214 - Marine Attack Squadron 214 - The Black Sheep", COMPELTE_ERAS, HIGH_RISK_GROUND_STRIKE, MARINE_GROUND_STRIKE_COMPLETE_ERAS),
    VMA311("VMA-311 - Marine Attack Squadron 311 - Tomcats", COMPELTE_ERAS, HIGH_RISK_GROUND_STRIKE, MARINE_GROUND_STRIKE_COMPLETE_ERAS),
    VMA232("VMA-232 - Marine Attack Squadron 232 - Red Devils", COMPELTE_ERAS, STRIKE_FIGHTER, MARINE_FIGHTER_COMPLETE_ERAS),
    VMA211("VMA-211 - Marine Attack Squadron 211 - Wake Island Avengers", COMPELTE_ERAS, STRIKE_FIGHTER, MARINE_STRIKE_FIGHTER_COMPLETE_ERAS),
    VMA251("VMA-251 - Marine Attack Squadron 251 - Thunderbolts", COMPELTE_ERAS, FLEET_DEFENSE, MARINE_FIGHTER_COMPLETE_ERAS),
    VMA115("VMA-115 - Marine Attack Squadron 115 - Silver Eagles", COMPELTE_ERAS, FLEET_DEFENSE, MARINE_FIGHTER_COMPLETE_ERAS),
    NONE("None", MODERN_ERAS, ALL_TASKS, USER_AVAILABLE_AIRCRAFT);

    private String squadronName;
    private List<ConflictEra> era;
    private List<Task> tasks;
    private List<AircraftType> aircraft;

    SquadronType(String squadronName, List<ConflictEra> era, List<Task> tasks, List<AircraftType> aircraft) {
        this.squadronName = squadronName;
        this.era = era;
        this.tasks = tasks;
        this.aircraft = aircraft;
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public List<AircraftType> getAircraftTypes() {
        return aircraft;
    }

    public String getSquadronName() {
        return squadronName;
    }

    public List<ConflictEra> getEra() {
        return era;
    }
}
