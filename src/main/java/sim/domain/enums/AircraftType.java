package sim.domain.enums;

import sim.domain.unit.air.Munition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import static sim.domain.enums.StaticLists.FA_18C_MUNITIONS;
import static sim.domain.enums.StaticLists.FA_18LOT20_MUNITIONS;
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

public enum AircraftType {
    // Playable Aircraft
    A_10C("A-10C", true, false, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    P_51D("P-51D", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.CHINA, FactionType.FRANCE, FactionType.GERMANY, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALY, FactionType.JAPAN, FactionType.NETHERLANDS, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.SOUTH_KOREA, FactionType.SWEDEN, FactionType.USSR, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    TF_51D("TF-51D", true, false, UNARMED, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.CHINA, FactionType.FRANCE, FactionType.GERMANY, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALY, FactionType.JAPAN, FactionType.NETHERLANDS, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.SOUTH_KOREA, FactionType.SWEDEN, FactionType.USSR, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    A_10A("A-10A", true, false, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SU_25T("Su-25A5T", true, false, SU25_TASKS, MODERN_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAN, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_25("Su-25", true, false, SU25_TASKS, MODERN_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAN, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.SUDAN, FactionType.UKRAINE)),
    F_15C("F-15C", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.ISRAEL, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    MIG_29A("MiG-29A", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.HUNGARY, FactionType.IRAQ, FactionType.ISRAEL, FactionType.ROMANIA, FactionType.SERBIA, FactionType.USSR, FactionType.YUGOSLAVIA)),
    MIG_29S("MiG-29S", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.HUNGARY, FactionType.IRAQ, FactionType.ISRAEL, FactionType.ROMANIA, FactionType.SERBIA, FactionType.USSR, FactionType.YUGOSLAVIA)),
    SU_27("Su-27", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.CHINA, FactionType.INDONESIA, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.USAF_AGGRESSORS, FactionType.BELARUS, FactionType.USSR)),
    SU_33("Su-33", true, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USAF_AGGRESSORS)),
    F_86F("F-86F", true, false, STRIKE_FIGHTER, EARLY_COLD_WAR_ERAS, null, Arrays.asList(FactionType.BELGIUM, FactionType.CANADA, FactionType.DENMARK, FactionType.ETHIOPIA, FactionType.GERMANY, FactionType.HONDURAS, FactionType.IRAN, FactionType.IRAQ, FactionType.JAPAN, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.PHILIPPINES, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.SPAIN, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA, FactionType.YUGOSLAVIA)),
    FW_190D9("FW-190D-9", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.CZECH_REPUBLIC, FactionType.FRANCE, FactionType.THIRD_REICH, FactionType.GERMANY, FactionType.HUNGARY, FactionType.JAPAN, FactionType.SPAIN, FactionType.ROMANIA, FactionType.TURKEY, FactionType.USAF_AGGRESSORS, FactionType.YUGOSLAVIA)),
    MIG_21BIS("MiG-21bis", true, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.CROATIA, FactionType.EGYPT, FactionType.INDIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.ROMANIA, FactionType.SERBIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.FINLAND, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDONESIA, FactionType.IRAN, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.USAF_AGGRESSORS, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YUGOSLAVIA, FactionType.YEMEN)),
    BF_109K4("Bf-109-K-4", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.BULGARIA, FactionType.CROATIA, FactionType.CZECH_REPUBLIC, FactionType.FINLAND, FactionType.THIRD_REICH, FactionType.HUNGARY, FactionType.ISRAEL, FactionType.ITALY, FactionType.ITALIAN_SOCIAL_REPUBLIC, FactionType.JAPAN, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SPAIN, FactionType.SWITZERLAND, FactionType.YUGOSLAVIA)),
    C_101("C-101", true, false, UNARMED, MODERN_ERAS, null, Arrays.asList(FactionType.CHILE, FactionType.HONDURAS, FactionType.JORDAN, FactionType.SPAIN)),
    MIG_15BIS("MiG-15bis", true, false, STRIKE_FIGHTER, EARLY_COLD_WAR_ERAS, null, Arrays.asList(FactionType.NORTH_KOREA, FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.IRAQ, FactionType.MOROCCO, FactionType.VIETNAM, FactionType.YEMEN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR,  FactionType.SUDAN, FactionType.SYRIA, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN)),
    HAWK_T1A("Hawk T.1A", true, false, UNARMED, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BAHRAIN, FactionType.CANADA, FactionType.FINLAND, FactionType.INDIA, FactionType.INDONESIA, FactionType.JORDAN, FactionType.KUWAIT, FactionType.MALAYSIA, FactionType.OMAN, FactionType.SAUDI_ARABIA, FactionType.UK, FactionType.UAE, FactionType.SOUTH_KOREA, FactionType.SWITZERLAND)),
    L_39C("L-39C", true, false, UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.SYRIA, FactionType.THAILAND, FactionType.TUNISIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN)),
    L_39ZA("L-39ZA", true, false, GROUND_STRIKE, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.SYRIA, FactionType.THAILAND, FactionType.TUNISIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN)),
    M_2000C("M-2000C", true, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.GREECE, FactionType.EGYPT, FactionType.BRAZIL)),
    F_5E3("F-5E-3", true, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.AUSTRIA, FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.CANADA, FactionType.CHILE, FactionType.GREECE, FactionType.HONDURAS, FactionType.INDONESIA, FactionType.IRAN, FactionType.JORDAN, FactionType.SOUTH_KOREA, FactionType.LIBYA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.MALAYSIA, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PHILIPPINES, FactionType.SAUDI_ARABIA, FactionType.VIETNAM, FactionType.SPAIN, FactionType.SUDAN, FactionType.SWITZERLAND, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USSR, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SPITFIRE_LFMKIX("Spitfire L.F. Mk.IX", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BELGIUM, FactionType.CANADA, FactionType.CHINA, FactionType.DENMARK, FactionType.EGYPT, FactionType.FRANCE, FactionType.GREECE, FactionType.INDIA, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALIAN_SOCIAL_REPUBLIC, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.POLAND, FactionType.USSR, FactionType.SWEDEN, FactionType.SYRIA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.YUGOSLAVIA)),
    SPITFIRE_LFMKIX_CW("Spitfire L.F. Mk.IX Clipped Wing", true, false, CLASSIC_TASKS, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BELGIUM, FactionType.CANADA, FactionType.CHINA, FactionType.DENMARK, FactionType.EGYPT, FactionType.FRANCE, FactionType.GREECE, FactionType.INDIA, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALIAN_SOCIAL_REPUBLIC, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.POLAND, FactionType.USSR, FactionType.SWEDEN, FactionType.SYRIA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.YUGOSLAVIA)),
    AJS_37("AJS-37", true, false, GROUND_STRIKE, LATE_COLD_EXTENDED_WAR_ERAS, null, Collections.singletonList(FactionType.SWEDEN)),
    AV_8NA("AV-8NA", true, false, AV8_TASKS, MODERN_ERAS, null, Arrays.asList(FactionType.ITALY, FactionType.SPAIN, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    FA_18C_LOT20("F/A-18C Lot 20", true, false, STRIKE_FIGHTER, MODERN_ERAS, FA_18LOT20_MUNITIONS, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.KUWAIT, FactionType.SPAIN, FactionType.SWITZERLAND, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    KA_50("Ka-50", true, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.RUSSIA)),
    MI_8MTV2("MI-8MTv2", true, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CANADA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.CROATIA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.GERMANY, FactionType.HUNGARY, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAQ, FactionType.IRAN, FactionType.LIBYA, FactionType.MEXICO, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.USSR, FactionType.SUDAN, FactionType.SYRIA, FactionType.TURKEY, FactionType.UKRAINE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YUGOSLAVIA)),
    UH_1H("UH-1H", true, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, Arrays.asList(FactionType.BRAZIL, FactionType.CHILE, FactionType.GEORGIA, FactionType.GERMANY, FactionType.GREECE, FactionType.HONDURAS, FactionType.ITALY, FactionType.JAPAN, FactionType.MOROCCO, FactionType.OMAN, FactionType.PAKISTAN, FactionType.PHILIPPINES, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.SPAIN, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.CANADA, FactionType.ISRAEL, FactionType.ITALY, FactionType.NETHERLANDS, FactionType.NORTH_KOREA)),
    SA342("SA342 Gazelle", true, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, Arrays.asList(FactionType.CHILE, FactionType.EGYPT, FactionType.FRANCE, FactionType.IRAQ, FactionType.KUWAIT, FactionType.MOROCCO, FactionType.QATAR, FactionType.SERBIA, FactionType.SYRIA, FactionType.TUNISIA, FactionType.UK, FactionType.YUGOSLAVIA)),

    // Traditional Aircraft
    A_50("A-50", false, false, AWACS, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.INDIA)),

    AN_26B("An-26B", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.CHINA, FactionType.ETHIOPIA, FactionType.HUNGARY, FactionType.LIBYA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YEMEN)),
    AN_30M("An-30M", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.BULGARIA, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.CZECH_REPUBLIC, FactionType.CHINA, FactionType.USSR, FactionType.VIETNAM)),

    B_1B("B-1B", false, false, BOMBER, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    B_17G("B-17G", false, false, BOMBER, CLASSIC_ERAS, null, Arrays.asList()),
    B_52H("B-52H", false, false, BOMBER, EXTENDED_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),

    C_130("C-130", false, false, UNARMED_TRANSPORT, EXTENDED_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.BELGIUM, FactionType.BRAZIL, FactionType.CANADA, FactionType.CHILE, FactionType.CHINA, FactionType.DENMARK, FactionType.EGYPT, FactionType.FRANCE, FactionType.GREECE, FactionType.HUNGARY, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAQ, FactionType.IRAN, FactionType.ISRAEL, FactionType.ITALY, FactionType.JAPAN, FactionType.JORDAN, FactionType.KUWAIT, FactionType.LIBYA, FactionType.MALAYSIA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.ROMANIA, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.SPAIN, FactionType.SUDAN, FactionType.SWEDEN, FactionType.THAILAND, FactionType.TUNISIA, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA, FactionType.YEMEN)),
    C_17A("C-17A", false, false, UNARMED_TRANSPORT, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.INDIA, FactionType.KUWAIT, FactionType.QATAR, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    E_2D("E-2D", false, false, AWACS, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.FRANCE, FactionType.JAPAN, FactionType.MEXICO, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.ISRAEL)),
    E_3A("E-3A", false, false, AWACS, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.SAUDI_ARABIA, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    F_117A("F-117A", false, false, STEALTH_BOMBER, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_14A("F-14A", false, false, STRIKE_FIGHTER, LATE_COLD_WAR_ERAS, null, Arrays.asList(FactionType.IRAN, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_15A("F-15A", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.ISRAEL, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_15E("F-15E", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.ISRAEL, FactionType.SOUTH_KOREA, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_16A("F-16A", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    // F_16AMLU("F-16A MLU", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    F_16CBLK52D("F-16C Blk52d", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    F_4E("F-4E", false, false, STRIKE_FIGHTER, EXTENDED_COLD_WAR, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.EGYPT, FactionType.GERMANY, FactionType.GREECE, FactionType.IRAN, FactionType.ISRAEL, FactionType.JAPAN, FactionType.SPAIN, FactionType.SOUTH_KOREA, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_5E("F-5E", false, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.AUSTRIA, FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.CANADA, FactionType.CHILE, FactionType.GREECE, FactionType.HONDURAS, FactionType.INDONESIA, FactionType.IRAN, FactionType.JORDAN, FactionType.SOUTH_KOREA, FactionType.LIBYA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.MALAYSIA, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PHILIPPINES, FactionType.SAUDI_ARABIA, FactionType.VIETNAM, FactionType.SPAIN, FactionType.SUDAN, FactionType.SWITZERLAND, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USSR, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    FA_18C("F/A-18C", false, false, STRIKE_FIGHTER, MODERN_ERAS, FA_18C_MUNITIONS, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.KUWAIT, FactionType.SPAIN, FactionType.SWITZERLAND, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    IL_76MD("IL-76MD", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BAHRAIN, FactionType.BELARUS, FactionType.CHINA, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDIA, FactionType.IRAN, FactionType.IRAQ, FactionType.JORDAN, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.USSR, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.UAE, FactionType.YEMEN)),
    IL_78M("IL-78M", false, false, TANKER, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.CHINA, FactionType.INDIA, FactionType.LIBYA, FactionType.PAKISTAN, FactionType.RUSSIA, FactionType.UKRAINE)),

    KC_130("KC-130", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList()),
    KC_135("KC-135", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.CHILE, FactionType.FRANCE, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    KC_135BDA("KC-135BDA", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.CHILE, FactionType.FRANCE, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    MIRAGE2_0005("Mirage 2000-5", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.INDIA, FactionType.UAE, FactionType.CHINA, FactionType.GREECE, FactionType.EGYPT, FactionType.BRAZIL, FactionType.QATAR)),

    MQ_1A("MQ-1A Predator", false, false, RECON, MODERN_ERAS, null, Arrays.asList(FactionType.ITALY, FactionType.TURKEY, FactionType.UAE, FactionType.MOROCCO, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    MQ_9("MQ-9 Reaper", false, false, RECON, MODERN_ERAS, null, Arrays.asList()),

    MIG_17("MiG-17", false, false, STRIKE_FIGHTER, COLD_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.HUNGARY, FactionType.IRAQ, FactionType.MOROCCO, FactionType.YEMEN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.SYRIA,  FactionType.USAF_AGGRESSORS, FactionType.VIETNAM)),
    MIG_19("MiG-19", false, false, STRIKE_FIGHTER, COLD_WAR_ERAS, null, Arrays.asList(FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.INDONESIA, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.SUDAN, FactionType.SYRIA, FactionType.VIETNAM)),
    MIG_23MLD("MiG-23MLD", false, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ETHIOPIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.SUDAN, FactionType.SYRIA, FactionType.ALGERIA, FactionType.BELARUS, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.INDIA, FactionType.IRAQ, FactionType.IRAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    MIG_25PD("MiG-25PD", false, false, AIR_SUPERIORITY, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.SYRIA, FactionType.LIBYA, FactionType.BULGARIA, FactionType.BELARUS, FactionType.INDIA, FactionType.IRAQ, FactionType.GEORGIA, FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    MIG_25RBT("MiG-25RBT", false, false, AIR_SUPERIORITY, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.SYRIA, FactionType.LIBYA, FactionType.BULGARIA, FactionType.BELARUS, FactionType.INDIA, FactionType.IRAQ, FactionType.GEORGIA, FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    MIG_27K("MiG-27K", false, false, GROUND_STRIKE, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.INDIA, FactionType.USSR, FactionType.RUSSIA, FactionType.UKRAINE)),
    // MIG_29G("MiG-29G", false, false, GROUND_STRIKE, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.HUNGARY, FactionType.IRAQ, FactionType.ISRAEL, FactionType.ROMANIA, FactionType.SERBIA, FactionType.USSR, FactionType.YUGOSLAVIA)),
    MIG_31("MiG-31", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.KAZAKHSTAN, FactionType.RUSSIA, FactionType.USSR)),

    S_3B("S-3B", false, false, UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    S_3B_TANKER("S-3B", false, false, TANKER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),

    SU_17M4("Su-17M4", false, false, STRIKE_FIGHTER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.LIBYA, FactionType.POLAND, FactionType.SYRIA, FactionType.VIETNAM, FactionType.IRAN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.IRAQ, FactionType.YEMEN, FactionType.SLOVAKIA, FactionType.USSR, FactionType.UKRAINE, FactionType.YEMEN)),
    SU_24M("Su-24M", false, false, GROUND_STRIKE, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.IRAN, FactionType.LIBYA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_24MR("Su-24MR", false, false, GROUND_STRIKE, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.IRAN, FactionType.LIBYA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.SUDAN, FactionType.UKRAINE)),
    // SU_25A("Su-2A5", false, false, SU25_TASKS, MODERN_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAN, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_25TM("Su-2A5TM", false, false, SU25_TASKS, MODERN_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAN, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_30("Su-30", false, false, AIR_SUPERIORITY, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.CHINA, FactionType.INDIA, FactionType.INDONESIA, FactionType.MALAYSIA, FactionType.RUSSIA, FactionType.VENEZUELA, FactionType.VIETNAM)),
    SU_34("Su-34", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.RUSSIA)),

    TORNADO_IDS("Tornado IDS", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.GERMANY, FactionType.ITALY, FactionType.SAUDI_ARABIA, FactionType.UK)),
    TORNADO_GR4("Tornado GR.4", false, false, STRIKE_FIGHTER, MODERN_ERAS, null, Arrays.asList(FactionType.GERMANY, FactionType.ITALY, FactionType.SAUDI_ARABIA, FactionType.UK)),

    TU_142("Tu-142", false, false, UNARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.INDIA, FactionType.USSR, FactionType.UKRAINE)),
    TU_160("Tu-160", false, false, BOMBER, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    TU_22M3("Tu-22M3", false, false, BOMBER, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    TU_95MS("Tu-95MS", false, false, BOMBER, EXTENDED_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),

    // Helicopters
    AH_64A("AH-64A", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.GREECE, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.JAPAN, FactionType.KUWAIT, FactionType.NETHERLANDS, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    AH_64D("AH-64D", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.GREECE, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.JAPAN, FactionType.KUWAIT, FactionType.NETHERLANDS, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    CH_47D("CH-47D", false, true, HELI_UNARMED_TRANSPORT, EXTENDED_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.ITALY, FactionType.JAPAN, FactionType.LIBYA, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.OMAN, FactionType.SOUTH_KOREA, FactionType.SAUDI_ARABIA, FactionType.VIETNAM, FactionType.SPAIN, FactionType.THAILAND, FactionType.TURKEY, FactionType.UK, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    CH_53E("CH-53E", false, true, HELI_UNARMED_TRANSPORT, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.JAPAN)),

    KA_27("Ka-27", false, true, HELI_ARMED_TRANSPORT, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.CHINA, FactionType.INDIA, FactionType.RUSSIA, FactionType.SOUTH_KOREA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM)),

    MI_24V("Mi-24V", false, true, HELI_ARMED_TRANSPORT, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BRAZIL, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.INDIA, FactionType.IRAQ, FactionType.INDONESIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.RUSSIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.SERBIA)),
    MI_26("Mi-26", false, true, HELI_UNARMED_TRANSPORT, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.CHINA, FactionType.INDONESIA, FactionType.JORDAN, FactionType.MEXICO, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.VENEZUELA)),
    MI_28N("Mi-28N", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.IRAQ, FactionType.RUSSIA, FactionType.VENEZUELA)),

    OH_58D("OH-58D", false, true, GROUND_STRIKE_CONTROLLER, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.CANADA, FactionType.CROATIA, FactionType.SAUDI_ARABIA, FactionType.IRAQ, FactionType.GREECE, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    SH_60B("SH-60B", false, true, HELI_ARMED_TRANSPORT, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BRAZIL, FactionType.DENMARK, FactionType.GREECE, FactionType.ISRAEL, FactionType.JAPAN, FactionType.SOUTH_KOREA, FactionType.SAUDI_ARABIA, FactionType.SPAIN, FactionType.THAILAND, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    UH_1W("UH-1W", false, true, GROUND_STRIKE_CONTROLLER, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    UH_60A("UH-60A", false, true, HELI_ARMED_TRANSPORT, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.CHILE, FactionType.CHINA, FactionType.EGYPT, FactionType.ISRAEL, FactionType.JAPAN, FactionType.JORDAN, FactionType.MALAYSIA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.PHILIPPINES, FactionType.SOUTH_KOREA, FactionType.SAUDI_ARABIA, FactionType.SLOVAKIA, FactionType.SWEDEN, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    ;

    private String aircraftName;
    private boolean playerFlyable;
    private boolean isHelicopter;
    private List<SubTaskType> possibleTasks;
    private List<ConflictEraType> aircraftEras;
    private Map<Integer, List<Munition>> stationMunitions;
    private List<FactionType> users;

    AircraftType(String aircraftName, boolean playerFlyable, boolean isHelicopter, List<SubTaskType> possibleTasks, List<ConflictEraType> aircraftEras, Map<Integer, List<Munition>> stationMunitions, List<FactionType> users) {
        this.aircraftName = aircraftName;
        this.playerFlyable = playerFlyable;
        this.isHelicopter = isHelicopter;
        this.possibleTasks = possibleTasks;
        this.aircraftEras = aircraftEras;
        this.stationMunitions = stationMunitions;
        this.users = users;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public List<ConflictEraType> getAircraftEras() {
        return aircraftEras;
    }

    public Map<Integer, List<Munition>> getStationMunitions() {
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
}
