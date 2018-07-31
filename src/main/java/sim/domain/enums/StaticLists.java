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
    //             AIRCRAFT POSSIBLE LOADOUTS
    // ===================================================
    static final Map<Integer, List<Munition>> FA_18C_MUNITIONS = new HashMap<Integer, List<Munition>>() {{
        put(1, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM9L, 1), new Munition(MunitionType.AIM9M, 1), new Munition(MunitionType.AIM9P5, 1)));
        put(2, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM7M, 1), new Munition(MunitionType.AIM9M, 2), new Munition(MunitionType.MK_20, 2), new Munition(MunitionType.MK_82, 3), new Munition(MunitionType.MK_82AIR, 2), new Munition(MunitionType.AGM_62, 1), new Munition(MunitionType.GBU_10, 1), new Munition(MunitionType.GBU_12, 1), new Munition(MunitionType.GBU_16, 1), new Munition(MunitionType.GBU_31, 1), new Munition(MunitionType.GBU_313B, 1), new Munition(MunitionType.GBU_38, 1), new Munition(MunitionType.MK_84, 1), new Munition(MunitionType.AGM_154C, 1), new Munition(MunitionType.AGM_84A, 1), new Munition(MunitionType.AGM_84E, 1), new Munition(MunitionType.AGM_88C, 1), new Munition(MunitionType.AGM_65E, 1), new Munition(MunitionType.AGM_65G, 1), new Munition(MunitionType.ZUNI_MK_71, 1), new Munition(MunitionType.MK_151_HE_7, 1), new Munition(MunitionType.MK_156_WP_7, 1), new Munition(MunitionType.MK_257_7, 1), new Munition(MunitionType.MK_5_HE_7, 1), new Munition(MunitionType.REMOVE_PYLON, 1)));
        put(3, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM7M, 1), new Munition(MunitionType.FUEL_TANK, 1), new Munition(MunitionType.AGM_84A, 1), new Munition(MunitionType.AGM_84E, 1), new Munition(MunitionType.AGM_88C, 1), new Munition(MunitionType.MK_151_HE_7, 1), new Munition(MunitionType.MK_156_WP_7, 1), new Munition(MunitionType.MK_257_7, 1), new Munition(MunitionType.MK_5_HE_7, 1), new Munition(MunitionType.MK_20, 2), new Munition(MunitionType.MK_82, 3), new Munition(MunitionType.MK_82AIR, 3), new Munition(MunitionType.GBU_10, 1), new Munition(MunitionType.GBU_12, 1), new Munition(MunitionType.GBU_16, 1), new Munition(MunitionType.GBU_31, 1), new Munition(MunitionType.GBU_313B, 1), new Munition(MunitionType.GBU_38, 1), new Munition(MunitionType.MK_84, 1)));
        put(4, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM7M, 1), new Munition(MunitionType.ANAAS_38, 1)));
        put(5, Arrays.asList(new Munition(MunitionType.FUEL_TANK, 1), new Munition(MunitionType.ANAAQ_28, 1)));
        put(6, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM7M, 1), new Munition(MunitionType.ANASQ_173, 1)));
        put(7, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM7M, 1), new Munition(MunitionType.FUEL_TANK, 1), new Munition(MunitionType.AGM_84A, 1), new Munition(MunitionType.AGM_84E, 1), new Munition(MunitionType.AGM_88C, 1), new Munition(MunitionType.MK_151_HE_7, 1), new Munition(MunitionType.MK_156_WP_7, 1), new Munition(MunitionType.MK_257_7, 1), new Munition(MunitionType.MK_5_HE_7, 1), new Munition(MunitionType.MK_20, 2), new Munition(MunitionType.MK_82, 3), new Munition(MunitionType.MK_82AIR, 3), new Munition(MunitionType.GBU_10, 1), new Munition(MunitionType.GBU_12, 1), new Munition(MunitionType.GBU_16, 1), new Munition(MunitionType.GBU_31, 1), new Munition(MunitionType.GBU_313B, 1), new Munition(MunitionType.GBU_38, 1), new Munition(MunitionType.MK_84, 1)));
        put(8, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM7M, 1), new Munition(MunitionType.AIM9M, 2), new Munition(MunitionType.MK_20, 2), new Munition(MunitionType.MK_82, 3), new Munition(MunitionType.MK_82AIR, 2), new Munition(MunitionType.AGM_62, 1), new Munition(MunitionType.GBU_10, 1), new Munition(MunitionType.GBU_12, 1), new Munition(MunitionType.GBU_16, 1), new Munition(MunitionType.GBU_31, 1), new Munition(MunitionType.GBU_313B, 1), new Munition(MunitionType.GBU_38, 1), new Munition(MunitionType.MK_84, 1), new Munition(MunitionType.AGM_154C, 1), new Munition(MunitionType.AGM_84A, 1), new Munition(MunitionType.AGM_84E, 1), new Munition(MunitionType.AGM_88C, 1), new Munition(MunitionType.AGM_65E, 1), new Munition(MunitionType.AGM_65G, 1), new Munition(MunitionType.ZUNI_MK_71, 1), new Munition(MunitionType.MK_151_HE_7, 1), new Munition(MunitionType.MK_156_WP_7, 1), new Munition(MunitionType.MK_257_7, 1), new Munition(MunitionType.MK_5_HE_7, 1), new Munition(MunitionType.REMOVE_PYLON, 1)));
        put(9, Arrays.asList(new Munition(MunitionType.AIM120B, 1), new Munition(MunitionType.AIM120C, 1), new Munition(MunitionType.AIM9L, 1), new Munition(MunitionType.AIM9M, 1), new Munition(MunitionType.AIM9P5, 1)));
    }};

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

    // ===================================================
    //             AIRCRAFT DEFAULT LOADOUTS
    // ===================================================
    // TODO: Move these to an external config (JSON)
    public static final Map<AircraftType, Map<SubTaskType, List<WeaponStation>>> DEFAULT_LOADOUTS = new HashMap<AircraftType, Map<SubTaskType, List<WeaponStation>>>() {{
        // F-18CLOT20 Loadouts
        Map<SubTaskType, List<WeaponStation>> f18Lot20TaskLoadouts = new HashMap<>();
        List<WeaponStation> f18Lot20CASLoadout = new ArrayList<>();
        f18Lot20CASLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20CASLoadout.add(new WeaponStation(2, new Munition(MunitionType.MK_82, 1)));
        f18Lot20CASLoadout.add(new WeaponStation(3, new Munition(MunitionType.MK_82, 1)));
        f18Lot20CASLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20CASLoadout.add(new WeaponStation(7, new Munition(MunitionType.MK_82, 1)));
        f18Lot20CASLoadout.add(new WeaponStation(8, new Munition(MunitionType.MK_82, 1)));
        f18Lot20CASLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.CAS, f18Lot20CASLoadout);

        List<WeaponStation> f18Lot20CAPLoadout = new ArrayList<>();
        f18Lot20CAPLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20CAPLoadout.add(new WeaponStation(2, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20CAPLoadout.add(new WeaponStation(4, new Munition(MunitionType.AIM7M, 1)));
        f18Lot20CAPLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20CAPLoadout.add(new WeaponStation(6, new Munition(MunitionType.AIM7M, 1)));
        f18Lot20CAPLoadout.add(new WeaponStation(8, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20CAPLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.CAP, f18Lot20CAPLoadout);

        List<WeaponStation> f18Lot20SEADLoadout = new ArrayList<>();
        f18Lot20SEADLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20SEADLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20SEADLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.SEAD, f18Lot20SEADLoadout);

        List<WeaponStation> f18Lot20DEADLoadout = new ArrayList<>();
        f18Lot20DEADLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20DEADLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20DEADLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.DEAD, f18Lot20DEADLoadout);

        List<WeaponStation> f18Lot20EscortLoadout = new ArrayList<>();
        f18Lot20EscortLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20EscortLoadout.add(new WeaponStation(2, new Munition(MunitionType.AIM7M, 1)));
        f18Lot20EscortLoadout.add(new WeaponStation(3, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20EscortLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20EscortLoadout.add(new WeaponStation(7, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20EscortLoadout.add(new WeaponStation(8, new Munition(MunitionType.AIM7M, 1)));
        f18Lot20EscortLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.ESCORT, f18Lot20EscortLoadout);

        List<WeaponStation> f18Lot20GroundStrikeLoadout = new ArrayList<>();
        f18Lot20GroundStrikeLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20GroundStrikeLoadout.add(new WeaponStation(2, new Munition(MunitionType.MK_83, 1)));
        f18Lot20GroundStrikeLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20GroundStrikeLoadout.add(new WeaponStation(8, new Munition(MunitionType.MK_83, 1)));
        f18Lot20GroundStrikeLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.GROUND_STRIKE, f18Lot20GroundStrikeLoadout);

        List<WeaponStation> f18Lot20InterceptLoadout = new ArrayList<>();
        f18Lot20InterceptLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20InterceptLoadout.add(new WeaponStation(2, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20InterceptLoadout.add(new WeaponStation(4, new Munition(MunitionType.AIM7M, 1)));
        f18Lot20InterceptLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20InterceptLoadout.add(new WeaponStation(6, new Munition(MunitionType.AIM7M, 1)));
        f18Lot20InterceptLoadout.add(new WeaponStation(8, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20InterceptLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.INTERCEPT, f18Lot20InterceptLoadout);

        List<WeaponStation> f18Lot20LowLevelStrikeLoadout = new ArrayList<>();
        f18Lot20LowLevelStrikeLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20LowLevelStrikeLoadout.add(new WeaponStation(2, new Munition(MunitionType.MK_82SE, 1)));
        f18Lot20LowLevelStrikeLoadout.add(new WeaponStation(5, new Munition(MunitionType.FUEL_TANK, 1)));
        f18Lot20LowLevelStrikeLoadout.add(new WeaponStation(8, new Munition(MunitionType.MK_82SE, 1)));
        f18Lot20LowLevelStrikeLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18Lot20TaskLoadouts.put(SubTaskType.LOW_LEVEL_STRIKE, f18Lot20LowLevelStrikeLoadout);
        put(AircraftType.FA_18C_LOT20, f18Lot20TaskLoadouts);

        // F-18C Loadouts
        Map<SubTaskType, List<WeaponStation>> f18TaskLoadouts = new HashMap<>();
        List<WeaponStation> f18CASLoadout = new ArrayList<>();
        f18CASLoadout.add(new WeaponStation(1, new Munition(MunitionType.AIM9M, 1)));
        f18CASLoadout.add(new WeaponStation(2, new Munition(MunitionType.GBU_12, 1)));
        f18CASLoadout.add(new WeaponStation(4, new Munition(MunitionType.ANAAS_38, 1)));
        f18CASLoadout.add(new WeaponStation(5, new Munition(MunitionType.ANAAQ_28, 1)));
        f18CASLoadout.add(new WeaponStation(6, new Munition(MunitionType.ANASQ_173, 1)));
        f18CASLoadout.add(new WeaponStation(8, new Munition(MunitionType.GBU_12, 1)));
        f18CASLoadout.add(new WeaponStation(9, new Munition(MunitionType.AIM9M, 1)));
        f18TaskLoadouts.put(SubTaskType.CAS, f18CASLoadout);
        put(AircraftType.FA_18C, f18Lot20TaskLoadouts);

        // A-10C Loadouts

        // A-10A Loadouts
    }};
}
