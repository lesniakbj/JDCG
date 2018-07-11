package gen.domain;

import java.util.List;

import static gen.domain.StaticLists.AIR_FORCE_AIR_SUPERIORITY_AVAILABLE_AIRCRAFT;
import static gen.domain.StaticLists.AIR_SUPERIORITY;
import static gen.domain.StaticLists.ALL_TASKS;
import static gen.domain.StaticLists.COMPLETE_ERAS;
import static gen.domain.StaticLists.EXTENDED_ERAS;
import static gen.domain.StaticLists.FLEET_DEFENSE;
import static gen.domain.StaticLists.GROUND_STRIKE;
import static gen.domain.StaticLists.GROUND_STRIKE_COMPLETE_ERAS;
import static gen.domain.StaticLists.HIGH_RISK_GROUND_STRIKE;
import static gen.domain.StaticLists.MARINE_FIGHTER_COMPLETE_ERAS;
import static gen.domain.StaticLists.MARINE_GROUND_STRIKE_COMPLETE_ERAS;
import static gen.domain.StaticLists.MARINE_STRIKE_FIGHTER_COMPLETE_ERAS;
import static gen.domain.StaticLists.MODERN_ERAS;
import static gen.domain.StaticLists.NAVY_COMPLETE_ERA_AIRCRAFT;
import static gen.domain.StaticLists.NAVY_MODERN_ERA_AIRCRAFT;
import static gen.domain.StaticLists.STRIKE_FIGHTER;
import static gen.domain.StaticLists.USER_AVAILABLE_AIRCRAFT;

public enum SquadronType {
    // USA Air Force Squadrons
    FSQD25("25th FS - 25th Fighter Squadron - Assam Draggins", COMPLETE_ERAS, GROUND_STRIKE, GROUND_STRIKE_COMPLETE_ERAS, Faction.USA),
    FSQD104("104th FS - 104th Fighter Squadron - Fighting Orioles", COMPLETE_ERAS, GROUND_STRIKE, GROUND_STRIKE_COMPLETE_ERAS, Faction.USA),
    FSQD44("44th FS - 44th Fighter Squadron - Vampires", COMPLETE_ERAS, AIR_SUPERIORITY, AIR_FORCE_AIR_SUPERIORITY_AVAILABLE_AIRCRAFT, Faction.USA),

    // USA Navy Squadrons
    VFA14("VFA-14 - Strike Fighter Squadron 14 - Tophatters", COMPLETE_ERAS, GROUND_STRIKE, NAVY_COMPLETE_ERA_AIRCRAFT, Faction.USA),
    VFA31("VFA-31 - Strike Fighter Squadron 31 - Tomcatters", COMPLETE_ERAS, STRIKE_FIGHTER, NAVY_COMPLETE_ERA_AIRCRAFT, Faction.USA),
    VFA154("VFA-154 - Strike Fighter Squadron 154 - Black Knights", COMPLETE_ERAS, FLEET_DEFENSE, NAVY_COMPLETE_ERA_AIRCRAFT, Faction.USA),
    VFA103("VFA-103 - Strike Fighter Squadron 154 - Jolly Rogers", EXTENDED_ERAS, STRIKE_FIGHTER, NAVY_COMPLETE_ERA_AIRCRAFT, Faction.USA),
    VFA94("VFA-94 - Strike Fighter Squadron 94 - Mighty Shrikes", EXTENDED_ERAS, HIGH_RISK_GROUND_STRIKE, NAVY_COMPLETE_ERA_AIRCRAFT, Faction.USA),
    VFA2("VFA-2 - Strike Fighter Squadron 2 - Bounty Hunters", MODERN_ERAS, FLEET_DEFENSE, NAVY_MODERN_ERA_AIRCRAFT, Faction.USA),

    // USA Marine Squadrons
    VMA214("VMA-214 - Marine Attack Squadron 214 - The Black Sheep", COMPLETE_ERAS, HIGH_RISK_GROUND_STRIKE, MARINE_GROUND_STRIKE_COMPLETE_ERAS, Faction.USA),
    VMA311("VMA-311 - Marine Attack Squadron 311 - Tomcats", COMPLETE_ERAS, HIGH_RISK_GROUND_STRIKE, MARINE_GROUND_STRIKE_COMPLETE_ERAS, Faction.USA),
    VMFA232("VMFA-232 - Marine Fighter Attack Squadron 232 - Red Devils", COMPLETE_ERAS, STRIKE_FIGHTER, MARINE_FIGHTER_COMPLETE_ERAS, Faction.USA),
    VMFA211("VMFA-211 - Marine Fighter Attack Squadron 211 - Wake Island Avengers", COMPLETE_ERAS, STRIKE_FIGHTER, MARINE_STRIKE_FIGHTER_COMPLETE_ERAS, Faction.USA),
    VMFA251("VMFA-251 - Marine Fighter Attack Squadron 251 - Thunderbolts", COMPLETE_ERAS, FLEET_DEFENSE, MARINE_FIGHTER_COMPLETE_ERAS, Faction.USA),
    VMFA115("VMFA-115 - Marine Fighter Attack Squadron 115 - Silver Eagles", COMPLETE_ERAS, FLEET_DEFENSE, MARINE_FIGHTER_COMPLETE_ERAS, Faction.USA),

    // No Attached Squadron
    NONE("None", MODERN_ERAS, ALL_TASKS, USER_AVAILABLE_AIRCRAFT, Faction.USA);

    private String squadronName;
    private List<ConflictEra> era;
    private List<Task> tasks;
    private List<AircraftType> aircraft;
    private Faction factionUser;

    SquadronType(String squadronName, List<ConflictEra> era, List<Task> tasks, List<AircraftType> aircraft, Faction factionUser) {
        this.squadronName = squadronName;
        this.era = era;
        this.tasks = tasks;
        this.aircraft = aircraft;
        this.factionUser = factionUser;
    }

    public static SquadronType fromName(String squadronName) {
        for(SquadronType squadronType : SquadronType.values()) {
            if(squadronType.getSquadronName().equalsIgnoreCase(squadronName)) {
                return squadronType;
            }
        }
        return null;
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

    public Faction getFactionUser() {
        return factionUser;
    }
}
