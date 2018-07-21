package sim.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class StaticLists {
    // Commonly used era lists
    static final List<ConflictEraType> COMPLETE_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> EXTENDED_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> MODERN_ERAS = Arrays.asList(ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> CLASSIC_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA);
    static final List<ConflictEraType> COLD_WAR_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM);
    static final List<ConflictEraType> LATE_COLD_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);
    static final List<ConflictEraType> LATE_COLD_EXTENDED_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> EARLY_COLD_WAR_ERAS = Collections.singletonList(ConflictEraType.KOREA);
    static final List<ConflictEraType> EXTENDED_COLD_WAR = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);

    // Commonly used task lists
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

    // Aircraft Loadouts (has to be initialized before AircraftType)
    static final Map<Integer, List<MunitionType>> FA_18_MUNITIONS = new HashMap<Integer, List<MunitionType>>() {{
        put(1, Arrays.asList(MunitionType.AIM9L, MunitionType.AIM9M, MunitionType.AIM9X, MunitionType.CAP9M));
        put(2, Arrays.asList(MunitionType.AIM9LX2, MunitionType.AIM9MX2, MunitionType.AIM9XX2, MunitionType.CAP9MX2, MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.MK_5_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.MK_5_HE_7X2, MunitionType.REMOVE_PYLON));
        put(3, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.REMOVE_PYLON, MunitionType.FUEL_TANK));
        put(4, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M));
        put(5, Arrays.asList(MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.FUEL_TANK));
        put(6, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M));
        put(7, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.REMOVE_PYLON, MunitionType.FUEL_TANK));
        put(8, Arrays.asList(MunitionType.AIM9LX2, MunitionType.AIM9MX2, MunitionType.AIM9XX2, MunitionType.CAP9MX2, MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.MK_5_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.MK_5_HE_7X2, MunitionType.REMOVE_PYLON));
        put(9, Arrays.asList(MunitionType.AIM9L, MunitionType.AIM9M, MunitionType.AIM9X, MunitionType.CAP9M));
    }};

    // Commonly used Aircraft Lists
    static final List<AircraftType> AIR_FORCE_AIR_SUPERIORITY_AVAILABLE_AIRCRAFT = Collections.singletonList(AircraftType.F_15C);
    static final List<AircraftType> NAVY_COMPLETE_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_14A, AircraftType.FA_18C_LOT20);
    static final List<AircraftType> NAVY_MODERN_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_14A, AircraftType.FA_18C_LOT20);
    static final List<AircraftType> GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.P_51D, AircraftType.F_86F, AircraftType.A_10C);
    static final List<AircraftType> MARINE_GROUND_STRIKE_COMPLETE_ERAS = Collections.singletonList(AircraftType.AV_8NA);
    static final List<AircraftType> MARINE_FIGHTER_COMPLETE_ERAS = Collections.singletonList(AircraftType.FA_18C_LOT20);
    static final List<AircraftType> MARINE_STRIKE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.AV_8NA, AircraftType.FA_18C_LOT20);

    // Default Loadouts
    public static final Map<AircraftType, Map<SubTaskType, Map<Integer, MunitionType>>> DEFAULT_LOADOUTS = new HashMap<AircraftType, Map<SubTaskType, Map<Integer, MunitionType>>>() {{
        // F-18C Loadouts
        Map<SubTaskType, Map<Integer, MunitionType>> f18TaskLoadouts = new HashMap<>();
        Map<Integer, MunitionType> f18CASLoadout = new HashMap<>();
        f18CASLoadout.put(1, MunitionType.AIM9M);
        f18CASLoadout.put(2, MunitionType.MK_82);
        f18CASLoadout.put(3, MunitionType.MK_82);
        f18CASLoadout.put(5, MunitionType.FUEL_TANK);
        f18CASLoadout.put(7, MunitionType.MK_82);
        f18CASLoadout.put(8, MunitionType.MK_82);
        f18CASLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.CAS, f18CASLoadout);

        Map<Integer, MunitionType> f18CAPLoadout = new HashMap<>();
        f18CAPLoadout.put(1, MunitionType.AIM9M);
        f18CAPLoadout.put(2, MunitionType.AIM9MX2);
        f18CAPLoadout.put(4, MunitionType.AIM7M);
        f18CAPLoadout.put(5, MunitionType.FUEL_TANK);
        f18CAPLoadout.put(6, MunitionType.AIM7M);
        f18CAPLoadout.put(8, MunitionType.AIM9MX2);
        f18CAPLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.CAP, f18CAPLoadout);

        Map<Integer, MunitionType> f18SEADLoadout = new HashMap<>();
        f18SEADLoadout.put(1, MunitionType.AIM9M);
        f18SEADLoadout.put(5, MunitionType.FUEL_TANK);
        f18SEADLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.SEAD, f18SEADLoadout);

        Map<Integer, MunitionType> f18DEADLoadout = new HashMap<>();
        f18DEADLoadout.put(1, MunitionType.AIM9M);
        f18DEADLoadout.put(5, MunitionType.FUEL_TANK);
        f18DEADLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.DEAD, f18DEADLoadout);

        Map<Integer, MunitionType> f18EscortLoadout = new HashMap<>();
        f18EscortLoadout.put(1, MunitionType.AIM9M);
        f18EscortLoadout.put(2, MunitionType.AIM7M);
        f18EscortLoadout.put(3, MunitionType.FUEL_TANK);
        f18EscortLoadout.put(5, MunitionType.FUEL_TANK);
        f18EscortLoadout.put(7, MunitionType.FUEL_TANK);
        f18EscortLoadout.put(8, MunitionType.AIM7M);
        f18EscortLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.ESCORT, f18EscortLoadout);

        Map<Integer, MunitionType> f18GroundStrikeLoadout = new HashMap<>();
        f18GroundStrikeLoadout.put(1, MunitionType.AIM9M);
        f18GroundStrikeLoadout.put(2, MunitionType.MK_83X2);
        f18GroundStrikeLoadout.put(5, MunitionType.FUEL_TANK);
        f18GroundStrikeLoadout.put(8, MunitionType.MK_83X2);
        f18GroundStrikeLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.GROUND_STRIKE, f18GroundStrikeLoadout);

        Map<Integer, MunitionType> f18InterceptLoadout = new HashMap<>();
        f18InterceptLoadout.put(1, MunitionType.AIM9M);
        f18InterceptLoadout.put(2, MunitionType.AIM9MX2);
        f18InterceptLoadout.put(4, MunitionType.AIM7M);
        f18InterceptLoadout.put(5, MunitionType.FUEL_TANK);
        f18InterceptLoadout.put(6, MunitionType.AIM7M);
        f18InterceptLoadout.put(8, MunitionType.AIM9MX2);
        f18InterceptLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.INTERCEPT, f18InterceptLoadout);

        Map<Integer, MunitionType> f18LowLevelStrikeLoadout = new HashMap<>();
        f18LowLevelStrikeLoadout.put(1, MunitionType.AIM9M);
        f18LowLevelStrikeLoadout.put(2, MunitionType.MK_82SEX2);
        f18LowLevelStrikeLoadout.put(5, MunitionType.FUEL_TANK);
        f18LowLevelStrikeLoadout.put(8, MunitionType.MK_82SEX2);
        f18LowLevelStrikeLoadout.put(9, MunitionType.AIM9M);
        f18TaskLoadouts.put(SubTaskType.LOW_LEVEL_STRIKE, f18LowLevelStrikeLoadout);
        put(AircraftType.FA_18C_LOT20, f18TaskLoadouts);

        // A-10C Loadouts

        // A-10A Loadouts
    }};
}
