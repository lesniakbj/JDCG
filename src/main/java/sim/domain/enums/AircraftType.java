package sim.domain.enums;

import sim.domain.unit.air.DefaultLoadouts;
import sim.domain.unit.air.StationPossibleMunitions;
import sim.domain.unit.air.Stations;
import sim.util.LogUtil;
import sim.util.save.JSONUtil;
import sun.nio.cs.US_ASCII;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static sim.domain.enums.StaticLists.AIR_SUPERIORITY;
import static sim.domain.enums.StaticLists.AV8_TASKS;
import static sim.domain.enums.StaticLists.AWACS;
import static sim.domain.enums.StaticLists.BOMBER;
import static sim.domain.enums.StaticLists.CLASSIC_ERAS;
import static sim.domain.enums.StaticLists.CLASSIC_TASKS;
import static sim.domain.enums.StaticLists.COLD_WAR_ERAS;
import static sim.domain.enums.StaticLists.EARLY_COLD_WAR_ERAS;
import static sim.domain.enums.StaticLists.EXTENDED_COLD_WAR;
import static sim.domain.enums.StaticLists.EXTENDED_ERAS;
import static sim.domain.enums.StaticLists.GROUND_STRIKE;
import static sim.domain.enums.StaticLists.GROUND_STRIKE_CONTROLLER;
import static sim.domain.enums.StaticLists.HELI_ARMED_TRANSPORT;
import static sim.domain.enums.StaticLists.HELI_UNARMED_TRANSPORT;
import static sim.domain.enums.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static sim.domain.enums.StaticLists.LATE_COLD_WAR_ERAS;
import static sim.domain.enums.StaticLists.MODERN_ERAS;
import static sim.domain.enums.StaticLists.RECON;
import static sim.domain.enums.StaticLists.STEALTH_BOMBER;
import static sim.domain.enums.StaticLists.STRIKE_FIGHTER;
import static sim.domain.enums.StaticLists.SU25_TASKS;
import static sim.domain.enums.StaticLists.TANKER;
import static sim.domain.enums.StaticLists.UNARMED;
import static sim.domain.enums.StaticLists.UNARMED_TRANSPORT;

// NOTE: This has been checked for contents (not factions) with the DCS in game encyclopedia as of 8/2/2018
public enum AircraftType {
    // Playable Aircraft
    A_10A("A-10A", "A-10A", true, false, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    A_10C("A-10C", "A-10C", true, false, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    AV_8NA("AV-8NA", "AV8BNA", true, false, AV8_TASKS, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_15C("F-15C", "F-15C", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_5E3("F-5E-3", "F-5E-3", true, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null,null, Arrays.asList(FactionType.USA)),
    F_86F("F-86F", "F-86F Sabre", true, false, STRIKE_FIGHTER, EARLY_COLD_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    FA_18C_LOT20("F/A-18C Lot 20", "FA-18C_hornet", true, false, STRIKE_FIGHTER, MODERN_ERAS, readPossibleLoadouts("fa_18c_lot20"), readDefaultLoadouts("fa_18c_lot20"), Arrays.asList(FactionType.USA)),
    P_51D("P-51D", "P-51D", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, null, Arrays.asList(FactionType.USA)),

    MIG_15BIS("MiG-15bis", "MiG-15bis", true, false, STRIKE_FIGHTER, EARLY_COLD_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_21BIS("MiG-21bis", "MiG-21Bis", true, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_29A("MiG-29A", "MiG-29A", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_29S("MiG-29S", "MiG-29S", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    J_11("J-11A", "J-11A", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Collections.singletonList(FactionType.CHINA)),
    SU_25("Su-25", "Su-25", true, false, SU25_TASKS, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_25T("Su-25A5T", "Su-25T", true, false, SU25_TASKS, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_27("Su-27", "Su-27", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_33("Su-33", "Su-33", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),

    // Playable Helicopters
    UH_1H("UH-1H", "UH-1H", true, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, null, Arrays.asList(FactionType.USA)),

    KA_50("Ka-50", "Ka-50", true, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    MI_8MTV2("Mi-8MTv2", "Mi-8MT", true, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, null, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),

    // Non-Playable Aircraft
    B_1B("B-1B", "B-1B", false, false, BOMBER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    B_17G("B-17G", "B-17G", false, false, BOMBER, CLASSIC_ERAS, null, null, Arrays.asList(FactionType.USA)),
    B_52H("B-52H", "B-52H", false, false, BOMBER, EXTENDED_ERAS, null, null, Arrays.asList(FactionType.USA)),
    C_130("C-130", "C-130", false, false, UNARMED_TRANSPORT, EXTENDED_ERAS, null, null, Arrays.asList(FactionType.USA)),
    C_17A("C-17A", "C-17A", false, false, UNARMED_TRANSPORT, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    E_2C("E-2C", "E-2C", false, false, AWACS, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    E_3A("E-3A", "E-3A", false, false, AWACS, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_117A("F-117A", "F-117A", false, false, STEALTH_BOMBER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_14A("F-14A", "F-14A", false, false, STRIKE_FIGHTER, LATE_COLD_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_15E("F-15E", "F-15E", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_16A("F-16A", "F-16A", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_16CBLK52D("F-16C Blk52d", "F-16C bl.52d", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    F_5E("F-5E", "F-5E", false, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    FA_18C("F/A-18C", "F/A-18C", false, false, STRIKE_FIGHTER, MODERN_ERAS, readPossibleLoadouts("fa_18_c"), readDefaultLoadouts("fa_18_c"), Arrays.asList(FactionType.USA)),
    KC_130("KC-130", "KC130", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    KC_135("KC-135", "KC-135", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    KC_135BDA("KC-135BDA", "KC135BDA", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    MQ_1A("MQ/RQ-1A Predator", "MQ-9 Reaper", false, false, RECON, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    RQ_9("MQ-9 Reaper", "RQ-1A Predator", false, false, RECON, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    S_3B("S-3B", "S-3B", false, false, UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    S_3B_TANKER("S-3B Tanker", "S-3B Tanker", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),

    A_50("A-50", "A-50", false, false, AWACS, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    AN_26B("An-26B", "An-26B", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    AN_30M("An-30M", "An-30M", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_23MLD("MiG-23MLD", "MiG-23MLD", false, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_25PD("MiG-25PD", "MiG-25PD", false, false, AIR_SUPERIORITY, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_25RBT("MiG-25RBT", "MiG-25RBT", false, false, AIR_SUPERIORITY, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_27K("MiG-27K", "MiG-27K", false, false, GROUND_STRIKE, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MIG_31("MiG-31", "MiG-31", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    IL_76MD("IL-76MD", "IL-76MD", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    IL_78M("IL-78M", "IL-78M", false, false, TANKER, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    KJ_2000("KJ-2000", "KJ-2000", false, false, AWACS, MODERN_ERAS, null, null, Collections.singletonList(FactionType.CHINA)),
    SU_17M4("Su-17M4", "Su-17M4", false, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_24M("Su-24M", "Su-24M", false, false, GROUND_STRIKE, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_24MR("Su-24MR", "Su-24MR", false, false, RECON, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_25TM("Su-2A5TM", "Su-25TM", false, false, SU25_TASKS, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_30("Su-30", "Su-30", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    SU_34("Su-34", "Su-34", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    TU_22M3("Tu-22M3", "Tu-22M3", false, false, BOMBER, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    TU_95MS("Tu-95MS", "Tu-95MS", false, false, BOMBER, EXTENDED_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    TU_142("Tu-142", "Tu-142", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    TU_160("Tu-160", "Tu-160", false, false, BOMBER, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),

    // Non-Playable Helicopters
    AH_1W("AH-1W", "AH-1W", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    AH_64A("AH-64A", "AH-64A", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    AH_64D("AH-64D", "AH-64D", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    CH_47D("CH-47D", "CH-47D", false, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, null, Arrays.asList(FactionType.USA)),
    CH_53E("CH-53E", "CH-53E", false, true, HELI_UNARMED_TRANSPORT, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    OH_58D("OH-58D", "OH-58D", false, true, GROUND_STRIKE_CONTROLLER, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.USA)),
    SH_60B("SH-60B", "SH-60B", false, true, HELI_ARMED_TRANSPORT, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),
    UH_60A("UH-60A", "UH-60A", false, true, HELI_ARMED_TRANSPORT, MODERN_ERAS, null, null, Arrays.asList(FactionType.USA)),

    KA_27("Ka-27", "Ka-27", false, true, HELI_ARMED_TRANSPORT, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    MI_24V("Mi-24V", "Mi-24V", false, true, HELI_ARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),
    MI_26("Mi-26", "Mi-26", false, true, HELI_UNARMED_TRANSPORT, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    MI_28N("Mi-28N", "Mi-28N", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, null, Arrays.asList(FactionType.RUSSIA)),


    /*
    // Playable Aircraft
    TF_51D("TF-51D", true, false, UNARMED, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.CHINA, FactionType.FRANCE, FactionType.GERMANY, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALY, FactionType.JAPAN, FactionType.NETHERLANDS, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.SOUTH_KOREA, FactionType.SWEDEN, FactionType.USSR, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    FW_190D9("FW-190D-9", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.CZECH_REPUBLIC, FactionType.FRANCE, FactionType.THIRD_REICH, FactionType.GERMANY, FactionType.HUNGARY, FactionType.JAPAN, FactionType.SPAIN, FactionType.ROMANIA, FactionType.TURKEY, FactionType.USAF_AGGRESSORS, FactionType.YUGOSLAVIA)),
    BF_109K4("Bf-109-K-4", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.BULGARIA, FactionType.CROATIA, FactionType.CZECH_REPUBLIC, FactionType.FINLAND, FactionType.THIRD_REICH, FactionType.HUNGARY, FactionType.ISRAEL, FactionType.ITALY, FactionType.ITALIAN_SOCIAL_REPUBLIC, FactionType.JAPAN, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SPAIN, FactionType.SWITZERLAND, FactionType.YUGOSLAVIA)),
    C_101("C-101", true, false, UNARMED, MODERN_ERAS, null, Arrays.asList(FactionType.CHILE, FactionType.HONDURAS, FactionType.JORDAN, FactionType.SPAIN)),
    HAWK_T1A("Hawk T.1A", true, false, UNARMED, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BAHRAIN, FactionType.CANADA, FactionType.FINLAND, FactionType.INDIA, FactionType.INDONESIA, FactionType.JORDAN, FactionType.KUWAIT, FactionType.MALAYSIA, FactionType.OMAN, FactionType.SAUDI_ARABIA, FactionType.UK, FactionType.UAE, FactionType.SOUTH_KOREA, FactionType.SWITZERLAND)),
    L_39C("L-39C", true, false, UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.SYRIA, FactionType.THAILAND, FactionType.TUNISIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN)),
    L_39ZA("L-39ZA", true, false, GROUND_STRIKE, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.SYRIA, FactionType.THAILAND, FactionType.TUNISIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN)),
    M_2000C("M-2000C", true, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.GREECE, FactionType.EGYPT, FactionType.BRAZIL)),
    SPITFIRE_LFMKIX("Spitfire L.F. Mk.IX", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BELGIUM, FactionType.CANADA, FactionType.CHINA, FactionType.DENMARK, FactionType.EGYPT, FactionType.FRANCE, FactionType.GREECE, FactionType.INDIA, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALIAN_SOCIAL_REPUBLIC, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.POLAND, FactionType.USSR, FactionType.SWEDEN, FactionType.SYRIA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.YUGOSLAVIA)),
    SPITFIRE_LFMKIX_CW("Spitfire L.F. Mk.IX Clipped Wing", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BELGIUM, FactionType.CANADA, FactionType.CHINA, FactionType.DENMARK, FactionType.EGYPT, FactionType.FRANCE, FactionType.GREECE, FactionType.INDIA, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALIAN_SOCIAL_REPUBLIC, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.POLAND, FactionType.USSR, FactionType.SWEDEN, FactionType.SYRIA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.YUGOSLAVIA)),
    AJS_37("AJS-37", true, false, GROUND_STRIKE, LATE_COLD_EXTENDED_WAR_ERAS, null, Collections.singletonList(FactionType.SWEDEN)),

    SA342("SA342 Gazelle", true, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, Arrays.asList(FactionType.CHILE, FactionType.EGYPT, FactionType.FRANCE, FactionType.IRAQ, FactionType.KUWAIT, FactionType.MOROCCO, FactionType.QATAR, FactionType.SERBIA, FactionType.SYRIA, FactionType.TUNISIA, FactionType.UK, FactionType.YUGOSLAVIA)),

    // Traditional Aircraft



    E_3C("E-3C", false, false, AWACS, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.SAUDI_ARABIA, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    // F_15A("F-15A", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.ISRAEL, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_16C("F-16C", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    // F_16AMLU("F-16A MLU", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.VENEZUELA)),
    F_4E("F-4E", false, false, STRIKE_FIGHTER, EXTENDED_COLD_WAR, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.EGYPT, FactionType.GERMANY, FactionType.GREECE, FactionType.IRAN, FactionType.ISRAEL, FactionType.JAPAN, FactionType.SPAIN, FactionType.SOUTH_KOREA, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),


    MIRAGE_20005("Mirage 2000-5", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.INDIA, FactionType.UAE, FactionType.CHINA, FactionType.GREECE, FactionType.EGYPT, FactionType.BRAZIL, FactionType.QATAR)),


    MIG_17("MiG-17", false, false, STRIKE_FIGHTER, COLD_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.HUNGARY, FactionType.IRAQ, FactionType.MOROCCO, FactionType.YEMEN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.SYRIA,  FactionType.USAF_AGGRESSORS, FactionType.VIETNAM)),
    MIG_19("MiG-19", false, false, STRIKE_FIGHTER, COLD_WAR_ERAS, null, Arrays.asList(FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.INDONESIA, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.SUDAN, FactionType.SYRIA, FactionType.VIETNAM)),

    TORNADO_IDS("Tornado IDS", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.GERMANY, FactionType.ITALY, FactionType.SAUDI_ARABIA, FactionType.UK)),
    // TORNADO_GR4("Tornado GR.4", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.GERMANY, FactionType.ITALY, FactionType.SAUDI_ARABIA, FactionType.UK)),

   */
    ;

    private String aircraftName;
    private String dcsName;
    private boolean playerFlyable;
    private boolean isHelicopter;
    private List<SubTaskType> possibleTasks;
    private List<ConflictEraType> aircraftEras;
    private Stations stationMunitions;
    private DefaultLoadouts defaultLoadouts;
    private List<FactionType> users;

    AircraftType(String aircraftName, String dcsName, boolean playerFlyable, boolean isHelicopter, List<SubTaskType> possibleTasks, List<ConflictEraType> aircraftEras, Stations stationMunitions, DefaultLoadouts defaultLoadouts, List<FactionType> users) {
        this.aircraftName = aircraftName;
        this.dcsName = dcsName;
        this.playerFlyable = playerFlyable;
        this.isHelicopter = isHelicopter;
        this.possibleTasks = possibleTasks;
        this.aircraftEras = aircraftEras;
        this.stationMunitions = stationMunitions;
        this.defaultLoadouts = defaultLoadouts;
        this.users = users;
    }

    /*
    AircraftType(String aircraftName, boolean playerFlyable, boolean isHelicopter, List<SubTaskType> possibleTasks, List<ConflictEraType> aircraftEras, Stations stationMunitions, List<FactionType> users) {
        this.aircraftName = aircraftName;
        this.playerFlyable = playerFlyable;
        this.isHelicopter = isHelicopter;
        this.possibleTasks = possibleTasks;
        this.aircraftEras = aircraftEras;
        this.stationMunitions = stationMunitions;
        this.users = users;
    }

    AircraftType(String aircraftName, boolean playerFlyable, boolean isHelicopter, List<SubTaskType> possibleTasks, List<ConflictEraType> aircraftEras, Stations stationMunitions, DefaultLoadouts defaultLoadouts, List<FactionType> users) {
        this.aircraftName = aircraftName;
        this.playerFlyable = playerFlyable;
        this.isHelicopter = isHelicopter;
        this.possibleTasks = possibleTasks;
        this.aircraftEras = aircraftEras;
        this.stationMunitions = stationMunitions;
        this.defaultLoadouts = defaultLoadouts;
        this.users = users;
    }



    AircraftType(String aircraftName, String dcsName, boolean b, boolean b1, List<SubTaskType> groundStrikeController, List<ConflictEraType> modernEras, Object o, List<FactionType> factionTypes) {
    }
    */

    public String getDcsName() {
        return dcsName;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public List<ConflictEraType> getAircraftEras() {
        return aircraftEras;
    }

    public Stations getStationMunitions() {
        return stationMunitions;
    }

    public boolean isPlayerFlyable() {
        return playerFlyable;
    }

    public List<FactionType> getUsers() {
        return users;
    }

    public boolean isHelicopter() {
        return isHelicopter;
    }

    public List<SubTaskType> getPossibleTasks() {
        return possibleTasks;
    }

    public DefaultLoadouts getDefaultLoadouts() {
        return defaultLoadouts;
    }

    public static AircraftType fromName(String selectedItem) {
        return Arrays.stream(AircraftType.values()).filter(at -> at.getAircraftName().equalsIgnoreCase(selectedItem)).findFirst().orElse(null);
    }

    public static List<AircraftType> getFixedWingPlayable() {
        return Arrays.stream(AircraftType.values()).filter(at -> !at.isHelicopter()).filter(AircraftType::isPlayerFlyable).collect(Collectors.toList());
    }

    public static List<AircraftType> getFixedWing() {
        return Arrays.stream(AircraftType.values()).filter(at -> !at.isHelicopter()).collect(Collectors.toList());
    }

    public static List<AircraftType> getHelicoptersPlayable() {
        return Arrays.stream(AircraftType.values()).filter(AircraftType::isHelicopter).filter(AircraftType::isPlayerFlyable).collect(Collectors.toList());
    }

    public static List<AircraftType> getHelicopters() {
        return Arrays.stream(AircraftType.values()).filter(AircraftType::isHelicopter).collect(Collectors.toList());
    }

    public static List<AircraftType> getAircraftByFactionType(FactionType type) {
        return Arrays.stream(AircraftType.values()).filter(a -> a.getUsers().contains(type)).collect(Collectors.toList());
    }

    public static List<AircraftType> getAircraftByEra(ConflictEraType type) {
        return Arrays.stream(AircraftType.values()).filter(a -> a.getAircraftEras().contains(type)).collect(Collectors.toList());
    }

    public static List<AircraftType> getAircraftByTask(List<AircraftType> list, SubTaskType type) {
        return list.stream().filter(a -> a.getPossibleTasks().contains(type)).collect(Collectors.toList());
    }

    private static DefaultLoadouts readDefaultLoadouts(String aircraftName) {
        try {
            InputStream is = StationPossibleMunitions.class.getResourceAsStream("/loadouts/" + aircraftName + "/default.json");
            if(is == null) {
                return new DefaultLoadouts();
            }

            return JSONUtil.fromJson(convertStreamToString(is), DefaultLoadouts.class);
        } catch (IOException ignored) {
            LogUtil.log("Error when reading a loadout for: " + aircraftName);
        }
        return new DefaultLoadouts();
    }

    private static Stations readPossibleLoadouts(String aircraftName) {
        try {
            InputStream is = StationPossibleMunitions.class.getResourceAsStream("/loadouts/" + aircraftName + "/loadouts.json");
            if(is == null) {
                return new Stations();
            }

            return JSONUtil.fromJson(convertStreamToString(is), Stations.class);
        } catch (IOException ignored) {
            LogUtil.log("Error when reading a loadout for: " + aircraftName);
        }
        return new Stations();
    }

    private static String convertStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }
}
