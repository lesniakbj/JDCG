package sim.domain.enums;

import java.awt.image.ByteLookupTable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static sim.domain.enums.StaticLists.CLASSIC_ERAS;
import static sim.domain.enums.StaticLists.COLD_WAR_ERAS;
import static sim.domain.enums.StaticLists.EARLY_COLD_WAR_ERAS;
import static sim.domain.enums.StaticLists.EXTENDED_COLD_WAR;
import static sim.domain.enums.StaticLists.EXTENDED_ERAS;
import static sim.domain.enums.StaticLists.FA_18_MUNITIONS;
import static sim.domain.enums.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static sim.domain.enums.StaticLists.LATE_COLD_WAR_ERAS;
import static sim.domain.enums.StaticLists.MODERN_ERAS;

public enum AircraftType {
    // Traditional Aircraft
    A_50("A-50", false, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.INDIA)),

    AN_26B("An-26B", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.CHINA, FactionType.ETHIOPIA, FactionType.HUNGARY, FactionType.LIBYA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YEMEN)),
    AN_30M("An-30M", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.BULGARIA, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.CZECH_REPUBLIC, FactionType.CHINA, FactionType.USSR, FactionType.VIETNAM)),

    A_10A("A-10A", true, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    A_10C("A-10C", true, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    AV_8NA("AV-8NA", true, MODERN_ERAS, null, Arrays.asList(FactionType.ITALY, FactionType.SPAIN, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    B_1B("B-1B", false, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    B_52H("B-52H", false, EXTENDED_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),

    C_130("C-130", false, EXTENDED_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.BELGIUM, FactionType.BRAZIL, FactionType.CANADA, FactionType.CHILE, FactionType.CHINA, FactionType.DENMARK, FactionType.EGYPT, FactionType.FRANCE, FactionType.GREECE, FactionType.HUNGARY, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAQ, FactionType.IRAN, FactionType.ISRAEL, FactionType.ITALY, FactionType.JAPAN, FactionType.JORDAN, FactionType.KUWAIT, FactionType.LIBYA, FactionType.MALAYSIA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.ROMANIA, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.SPAIN, FactionType.SUDAN, FactionType.SWEDEN, FactionType.THAILAND, FactionType.TUNISIA, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA, FactionType.YEMEN)),
    C_17A("C-17A", false, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.INDIA, FactionType.KUWAIT, FactionType.QATAR, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    E_2D("E-2D", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.FRANCE, FactionType.JAPAN, FactionType.MEXICO, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.ISRAEL)),
    E_3A("E-3A", false, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.SAUDI_ARABIA, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    F_117A("F-117A", false, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_14A("F-14A", false, LATE_COLD_WAR_ERAS, null, Arrays.asList(FactionType.IRAN, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_15A("F-15A", false, MODERN_ERAS, null, Arrays.asList(FactionType.ISRAEL, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_15C("F-15C", true, MODERN_ERAS, null, Arrays.asList(FactionType.ISRAEL, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_15E("F-15E", false, MODERN_ERAS, null, Arrays.asList(FactionType.ISRAEL, FactionType.SOUTH_KOREA, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_16A("F-16A", false, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    F_16AMLU("F-16A MLU", false, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    F_16CBLK52D("F-16C Blk52d", false, MODERN_ERAS, null, Arrays.asList(FactionType.BAHRAIN, FactionType.BELGIUM, FactionType.CHILE, FactionType.CROATIA, FactionType.DENMARK, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.INDONESIA, FactionType.IRAQ, FactionType.JORDAN, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.OMAN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    F_4E("F-4E", false, EXTENDED_COLD_WAR, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.EGYPT, FactionType.GERMANY, FactionType.GREECE, FactionType.IRAN, FactionType.ISRAEL, FactionType.JAPAN, FactionType.SPAIN, FactionType.SOUTH_KOREA, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_5E("F-5E", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.AUSTRIA, FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.CANADA, FactionType.CHILE, FactionType.GREECE, FactionType.HONDURAS, FactionType.INDONESIA, FactionType.IRAN, FactionType.JORDAN, FactionType.SOUTH_KOREA, FactionType.LIBYA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.MALAYSIA, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PHILIPPINES, FactionType.SAUDI_ARABIA, FactionType.VIETNAM, FactionType.SPAIN, FactionType.SUDAN, FactionType.SWITZERLAND, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USSR, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    F_86("F-86", true, EARLY_COLD_WAR_ERAS, null, Arrays.asList(FactionType.BELGIUM, FactionType.CANADA, FactionType.DENMARK, FactionType.ETHIOPIA, FactionType.GERMANY, FactionType.HONDURAS, FactionType.IRAN, FactionType.IRAQ, FactionType.JAPAN, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.PHILIPPINES, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.SPAIN, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA, FactionType.YUGOSLAVIA)),

    FA_18C("F/A-18C", false, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.KUWAIT, FactionType.SPAIN, FactionType.SWITZERLAND, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    FA_18C_LOT20("F/A-18C Lot 20", true, MODERN_ERAS, FA_18_MUNITIONS, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.KUWAIT, FactionType.SPAIN, FactionType.SWITZERLAND, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    FW_190D9("FW-190D-9", false, CLASSIC_ERAS, null, Arrays.asList(FactionType.CZECH_REPUBLIC, FactionType.FRANCE, FactionType.THIRD_REICH, FactionType.GERMANY, FactionType.HUNGARY, FactionType.JAPAN, FactionType.SPAIN, FactionType.ROMANIA, FactionType.TURKEY, FactionType.USAF_AGGRESSORS, FactionType.YUGOSLAVIA)),

    IL_76MD("IL-76MD", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BAHRAIN, FactionType.BELARUS, FactionType.CHINA, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDIA, FactionType.IRAN, FactionType.IRAQ, FactionType.JORDAN, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.USSR, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.UAE, FactionType.YEMEN)),
    IL_78M("IL-78M", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.CHINA, FactionType.INDIA, FactionType.LIBYA, FactionType.PAKISTAN, FactionType.RUSSIA, FactionType.UKRAINE)),

    KC_135("KC-135", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.CHILE, FactionType.FRANCE, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    L_39ZA("L-39ZA", true, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.SYRIA, FactionType.THAILAND, FactionType.TUNISIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN)),

    MIRAGE2_0005("Mirage 2000-5", false, MODERN_ERAS, null, Arrays.asList(FactionType.FRANCE, FactionType.INDIA, FactionType.UAE, FactionType.CHINA, FactionType.GREECE, FactionType.EGYPT, FactionType.BRAZIL, FactionType.QATAR)),

    MQ_1A("MQ-1A Predator", false, MODERN_ERAS, null, Arrays.asList(FactionType.ITALY, FactionType.TURKEY, FactionType.UAE, FactionType.MOROCCO, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    MIG_15("MiG-15", true, EARLY_COLD_WAR_ERAS, null, Arrays.asList(FactionType.NORTH_KOREA, FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.IRAQ, FactionType.MOROCCO, FactionType.VIETNAM, FactionType.YEMEN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR,  FactionType.SUDAN, FactionType.SYRIA, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN)),
    MIG_17("MiG-17", false, COLD_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.HUNGARY, FactionType.IRAQ, FactionType.MOROCCO, FactionType.YEMEN, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.SYRIA,  FactionType.USAF_AGGRESSORS, FactionType.VIETNAM)),
    MIG_19("MiG-19", false, COLD_WAR_ERAS, null, Arrays.asList(FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.INDONESIA, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.SUDAN, FactionType.SYRIA, FactionType.VIETNAM)),
    MIG_21("MiG-21", true, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.CROATIA, FactionType.EGYPT, FactionType.INDIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.ROMANIA, FactionType.SERBIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.FINLAND, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDONESIA, FactionType.IRAN, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.USAF_AGGRESSORS, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YUGOSLAVIA, FactionType.YEMEN)),
    MIG_23MLD("MiG-23MLD", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ETHIOPIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.SUDAN, FactionType.SYRIA, FactionType.ALGERIA, FactionType.BELARUS, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.INDIA, FactionType.IRAQ, FactionType.IRAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    MIG_25PD("MiG-25PD", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.SYRIA, FactionType.LIBYA, FactionType.BULGARIA, FactionType.BELARUS, FactionType.INDIA, FactionType.IRAQ, FactionType.GEORGIA, FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    MIG_25RBT("MiG-25RBT", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.SYRIA, FactionType.LIBYA, FactionType.BULGARIA, FactionType.BELARUS, FactionType.INDIA, FactionType.IRAQ, FactionType.GEORGIA, FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    MIG_27K("MiG-27K", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.INDIA, FactionType.USSR, FactionType.RUSSIA, FactionType.UKRAINE)),
    MIG_29A("MiG-29A", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.HUNGARY, FactionType.IRAQ, FactionType.ISRAEL, FactionType.ROMANIA, FactionType.SERBIA, FactionType.USSR, FactionType.YUGOSLAVIA)),
    MIG_29G("MiG-29G", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.HUNGARY, FactionType.IRAQ, FactionType.ISRAEL, FactionType.ROMANIA, FactionType.SERBIA, FactionType.USSR, FactionType.YUGOSLAVIA)),
    MIG_29S("MiG-29S", true, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USAF_AGGRESSORS, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.HUNGARY, FactionType.IRAQ, FactionType.ISRAEL, FactionType.ROMANIA, FactionType.SERBIA, FactionType.USSR, FactionType.YUGOSLAVIA)),
    MIG_31("MiG-31", false, MODERN_ERAS, null, Arrays.asList(FactionType.KAZAKHSTAN, FactionType.RUSSIA, FactionType.USSR)),

    P_51D("P-51D", true, CLASSIC_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.CHINA, FactionType.FRANCE, FactionType.GERMANY, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.ITALY, FactionType.JAPAN, FactionType.NETHERLANDS, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.SOUTH_KOREA, FactionType.SWEDEN, FactionType.USSR, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    S_3B("S-3B", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    S_3B_TANKER("S-3B", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),

    SU_17M4("Su-17M4", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.LIBYA, FactionType.POLAND, FactionType.SYRIA, FactionType.VIETNAM, FactionType.IRAN, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.EGYPT, FactionType.HUNGARY, FactionType.IRAQ, FactionType.YEMEN, FactionType.SLOVAKIA, FactionType.USSR, FactionType.UKRAINE, FactionType.YEMEN)),
    SU_24M("Su-24M", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.IRAN, FactionType.LIBYA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_24MR("Su-24MR", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.IRAN, FactionType.LIBYA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_25A("Su-2A5", true, MODERN_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAN, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_25T("Su-2A5T", true, MODERN_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAN, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_25TM("Su-2A5TM", false, MODERN_ERAS, null, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAN, FactionType.IRAQ, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.USSR, FactionType.SUDAN, FactionType.UKRAINE)),
    SU_27("Su-27", true, MODERN_ERAS, null, Arrays.asList(FactionType.CHINA, FactionType.INDONESIA, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.USAF_AGGRESSORS, FactionType.BELARUS, FactionType.USSR)),
    SU_30("Su-30", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.CHINA, FactionType.INDIA, FactionType.INDONESIA, FactionType.MALAYSIA, FactionType.RUSSIA, FactionType.VENEZUELA, FactionType.VIETNAM)),
    SU_33("Su-33", true, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USAF_AGGRESSORS)),
    SU_34("Su-34", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.RUSSIA)),

    TORNADO_IDS("Tornado IDS", false, MODERN_ERAS, null, Arrays.asList(FactionType.GERMANY, FactionType.ITALY, FactionType.SAUDI_ARABIA, FactionType.UK)),
    TORNADO_GR4("Tornado GR.4", false, MODERN_ERAS, null, Arrays.asList(FactionType.GERMANY, FactionType.ITALY, FactionType.SAUDI_ARABIA, FactionType.UK)),

    TU_142("Tu-142", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.INDIA, FactionType.USSR, FactionType.UKRAINE)),
    TU_160("Tu-160", false, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    TU_22M3("Tu-22M3", false, MODERN_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),
    TU_95MS("Tu-95MS", false, EXTENDED_ERAS, null, Arrays.asList(FactionType.RUSSIA, FactionType.USSR, FactionType.UKRAINE)),

    // Helicopters
    AH_64A("AH-64A", false, MODERN_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.GREECE, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.JAPAN, FactionType.KUWAIT, FactionType.NETHERLANDS, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    AH_64D("AH-64D", false, MODERN_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.GREECE, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.JAPAN, FactionType.KUWAIT, FactionType.NETHERLANDS, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    CH_47D("CH-47D", false, EXTENDED_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.CANADA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.ITALY, FactionType.JAPAN, FactionType.LIBYA, FactionType.MOROCCO, FactionType.NETHERLANDS, FactionType.OMAN, FactionType.SOUTH_KOREA, FactionType.SAUDI_ARABIA, FactionType.VIETNAM, FactionType.SPAIN, FactionType.THAILAND, FactionType.TURKEY, FactionType.UK, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    CH_53E("CH-53E", false, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.JAPAN)),

    KA_27("Ka-27", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.CHINA, FactionType.INDIA, FactionType.RUSSIA, FactionType.SOUTH_KOREA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM)),
    KA_50("Ka-50", false, MODERN_ERAS, null, Arrays.asList(FactionType.EGYPT, FactionType.RUSSIA)),

    MI_24V("Mi-24V", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BRAZIL, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.INDIA, FactionType.IRAQ, FactionType.INDONESIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.RUSSIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.SERBIA)),
    MI_26("Mi-26", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.CHINA, FactionType.INDONESIA, FactionType.JORDAN, FactionType.MEXICO, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.VENEZUELA)),
    MI_28N("Mi-28N", false, MODERN_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.IRAQ, FactionType.RUSSIA, FactionType.VENEZUELA)),
    MI_8MTV2("MI-8MTv2", true, EXTENDED_ERAS, null, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CANADA, FactionType.CHINA, FactionType.CZECH_REPUBLIC, FactionType.CROATIA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.GERMANY, FactionType.HUNGARY, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAQ, FactionType.IRAN, FactionType.LIBYA, FactionType.MEXICO, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.USSR, FactionType.SUDAN, FactionType.SYRIA, FactionType.TURKEY, FactionType.UKRAINE, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YUGOSLAVIA)),

    OH_58D("OH-58D", false, LATE_COLD_EXTENDED_WAR_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.CANADA, FactionType.CROATIA, FactionType.SAUDI_ARABIA, FactionType.IRAQ, FactionType.GREECE, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    SH_60B("SH-60B", false, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.BRAZIL, FactionType.DENMARK, FactionType.GREECE, FactionType.ISRAEL, FactionType.JAPAN, FactionType.SOUTH_KOREA, FactionType.SAUDI_ARABIA, FactionType.SPAIN, FactionType.THAILAND, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS)),

    UH_1W("UH-1W", false, MODERN_ERAS, null, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS)),
    UH_1H("UH-1H", false, EXTENDED_ERAS, null, Arrays.asList(FactionType.BRAZIL, FactionType.CHILE, FactionType.GEORGIA, FactionType.GERMANY, FactionType.GREECE, FactionType.HONDURAS, FactionType.ITALY, FactionType.JAPAN, FactionType.MOROCCO, FactionType.OMAN, FactionType.PAKISTAN, FactionType.PHILIPPINES, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.SPAIN, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.CANADA, FactionType.ISRAEL, FactionType.ITALY, FactionType.NETHERLANDS, FactionType.NORTH_KOREA)),
    UH_60A("UH-60A", false, MODERN_ERAS, null, Arrays.asList(FactionType.AUSTRALIA, FactionType.AUSTRIA, FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.CHILE, FactionType.CHINA, FactionType.EGYPT, FactionType.ISRAEL, FactionType.JAPAN, FactionType.JORDAN, FactionType.MALAYSIA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.PHILIPPINES, FactionType.SOUTH_KOREA, FactionType.SAUDI_ARABIA, FactionType.SLOVAKIA, FactionType.SWEDEN, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    ;

    private String aircraftName;
    private boolean playerFlyable;
    private List<ConflictEraType> aircraftEras;
    private Map<Integer, List<MunitionType>> stationMunitions;
    private List<FactionType> users;

    AircraftType(String aircraftName, boolean playerFlyable, List<ConflictEraType> aircraftEras, Map<Integer, List<MunitionType>> stationMunitions, List<FactionType> users) {
        this.aircraftName = aircraftName;
        this.playerFlyable = playerFlyable;
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

    public Map<Integer, List<MunitionType>> getStationMunitions() {
        return stationMunitions;
    }

    public boolean isPlayerFlyable() {
        return playerFlyable;
    }

    public List<FactionType> getUsers() {
        return users;
    }

    public static AircraftType fromName(String selectedItem) {
        return Arrays.stream(AircraftType.values()).filter(at -> at.getAircraftName().equalsIgnoreCase(selectedItem)).findFirst().orElse(null);
    }

    public static List<AircraftType> getPlayerAircraftTypes() {
        return Arrays.stream(AircraftType.values()).filter(AircraftType::isPlayerFlyable).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "AircraftType{" +
                "aircraftName='" + aircraftName + '\'' +
                ", aircraftEras=" + aircraftEras +
                ", stationMunitions=" + stationMunitions +
                '}';
    }

    public static List<AircraftType> getAircraftByFactionType(FactionType type) {
        List<AircraftType> types = new ArrayList<>();
        for(AircraftType aircraftType : AircraftType.values()) {
            if(aircraftType.getUsers().contains(type)) {
                types.add(aircraftType);
            }
        }
        return types;
    }
}
