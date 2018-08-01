package sim.domain.enums;

import sim.domain.unit.air.Munition;
import sim.domain.unit.air.WeaponStation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class StaticLists {
    // ===================================================
    //              AIRCRAFT ERA LISTS
    // ===================================================
    static final List<ConflictEraType> COMPLETE_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> EXTENDED_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> MODERN_ERAS = Arrays.asList(ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> CLASSIC_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA);
    static final List<ConflictEraType> COLD_WAR_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM);
    static final List<ConflictEraType> LATE_COLD_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);
    static final List<ConflictEraType> LATE_COLD_EXTENDED_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> EARLY_COLD_WAR_ERAS = Collections.singletonList(ConflictEraType.KOREA);
    static final List<ConflictEraType> EXTENDED_COLD_WAR = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);

    // ===================================================
    //              AIRCRAFT TASK LISTS
    // ===================================================
    static final List<SubTaskType> ALL_TASK_TYPES = Arrays.asList(SubTaskType.values());
    static final List<SubTaskType> AIR_SUPERIORITY = Arrays.asList(SubTaskType.CAP, SubTaskType.INTERCEPT, SubTaskType.ESCORT, SubTaskType.RECON);
    static final List<SubTaskType> STRIKE_FIGHTER = Arrays.asList(SubTaskType.CAP, SubTaskType.INTERCEPT, SubTaskType.ESCORT, SubTaskType.GROUND_STRIKE, SubTaskType.CAS, SubTaskType.INTERDICTION);
    static final List<SubTaskType> FLEET_DEFENSE = Arrays.asList(SubTaskType.CAP, SubTaskType.INTERCEPT, SubTaskType.ESCORT);
    static final List<SubTaskType> GROUND_STRIKE = Arrays.asList(SubTaskType.CAS, SubTaskType.GROUND_STRIKE, SubTaskType.INTERDICTION, SubTaskType.LOW_LEVEL_STRIKE);
    static final List<SubTaskType> HIGH_RISK_GROUND_STRIKE = Arrays.asList(SubTaskType.CAS, SubTaskType.GROUND_STRIKE, SubTaskType.SEAD, SubTaskType.DEAD);
    static final List<SubTaskType> UNARMED = Arrays.asList(SubTaskType.BDA, SubTaskType.RECON);
    static final List<SubTaskType> GROUND_STRIKE_CONTROLLER = Arrays.asList(SubTaskType.BDA, SubTaskType.CAS, SubTaskType.FAC, SubTaskType.INTERDICTION, SubTaskType.GROUND_STRIKE, SubTaskType.RECON);
    static final List<SubTaskType> CLASSIC_TASKS = Arrays.asList(SubTaskType.BDA, SubTaskType.CAS, SubTaskType.INTERDICTION, SubTaskType.GROUND_STRIKE, SubTaskType.RECON, SubTaskType.CAP, SubTaskType.INTERCEPT, SubTaskType.ESCORT);
    static final List<SubTaskType> SU25_TASKS = Arrays.asList(SubTaskType.BDA, SubTaskType.CAS, SubTaskType.INTERDICTION, SubTaskType.GROUND_STRIKE, SubTaskType.RECON, SubTaskType.SEAD, SubTaskType.DEAD);
    static final List<SubTaskType> AV8_TASKS = Arrays.asList(SubTaskType.BDA, SubTaskType.CAP, SubTaskType.CAS, SubTaskType.DEAD, SubTaskType.ESCORT, SubTaskType.FAC, SubTaskType.INTERDICTION, SubTaskType.GROUND_STRIKE, SubTaskType.LOW_LEVEL_STRIKE, SubTaskType.RECON, SubTaskType.SEAD);
    static final List<SubTaskType> UNARMED_TRANSPORT = Arrays.asList(SubTaskType.AIRLIFT, SubTaskType.TRANSPORT, SubTaskType.RECON);
    static final List<SubTaskType> HELI_UNARMED_TRANSPORT = Arrays.asList(SubTaskType.AIRLIFT, SubTaskType.BDA, SubTaskType.FAC, SubTaskType.RECON, SubTaskType.TRANSPORT);
    static final List<SubTaskType> HELI_ARMED_TRANSPORT = Arrays.asList(SubTaskType.GROUND_STRIKE, SubTaskType.INTERDICTION, SubTaskType.AIRLIFT, SubTaskType.BDA, SubTaskType.FAC, SubTaskType.RECON, SubTaskType.TRANSPORT);
    static final List<SubTaskType> AWACS = Collections.singletonList(SubTaskType.AWACS);
    static final List<SubTaskType> TANKER = Collections.singletonList(SubTaskType.REFUELING);
    static final List<SubTaskType> RECON = Arrays.asList(SubTaskType.FAC, SubTaskType.BDA, SubTaskType.RECON);
    static final List<SubTaskType> BOMBER = Arrays.asList(SubTaskType.GROUND_STRIKE, SubTaskType.INTERDICTION, SubTaskType.BOMBER);
    static final List<SubTaskType> STEALTH_BOMBER = Arrays.asList(SubTaskType.GROUND_STRIKE, SubTaskType.INTERDICTION, SubTaskType.STEALTH);

    // ===================================================
    //             SQUADRON AIRCRAFT TYPES
    // ===================================================
    static final List<AircraftType> AIR_FORCE_AIR_SUPERIORITY_AVAILABLE_AIRCRAFT = Collections.singletonList(AircraftType.F_15C);
    static final List<AircraftType> NAVY_COMPLETE_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_14A, AircraftType.FA_18C_LOT20);
    static final List<AircraftType> NAVY_MODERN_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_14A, AircraftType.FA_18C_LOT20);
    static final List<AircraftType> GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.P_51D, AircraftType.F_86F, AircraftType.A_10C);
    static final List<AircraftType> MARINE_GROUND_STRIKE_COMPLETE_ERAS = Collections.singletonList(AircraftType.AV_8NA);
    static final List<AircraftType> MARINE_FIGHTER_COMPLETE_ERAS = Collections.singletonList(AircraftType.FA_18C_LOT20);
    static final List<AircraftType> MARINE_STRIKE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.AV_8NA, AircraftType.FA_18C_LOT20);
}
