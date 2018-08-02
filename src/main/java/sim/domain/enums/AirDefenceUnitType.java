package sim.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static sim.domain.enums.StaticLists.EXTENDED_ERAS;
import static sim.domain.enums.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static sim.domain.enums.StaticLists.MODERN_ERAS;

public enum AirDefenceUnitType {
    // AAA Systems
    AAA_VULCAN("AAA Vulcan M163", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.CHILE, FactionType.EGYPT, FactionType.IRAN, FactionType.ISRAEL, FactionType.JORDAN, FactionType.MOROCCO, FactionType.SOUTH_KOREA, FactionType.THAILAND, FactionType.TUNISIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SPAA_GEPARD("SPAAA Gepard", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.GERMANY, FactionType.JORDAN, FactionType.ROMANIA, FactionType.BELGIUM, FactionType.CHILE, FactionType.NETHERLANDS)),
    AAA_ZU23_CLOSED("AAA ZU-23 Closed", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.CHINA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.FINLAND, FactionType.GREECE, FactionType.GEORGIA, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAN, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.ISRAEL, FactionType.HUNGARY, FactionType.LIBYA, FactionType.MOROCCO, FactionType.PAKISTAN, FactionType.POLAND, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.USSR)),
    AAA_ZU23_EMPLACEMENT("AAA ZU-23 Emplacement", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.CHINA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.FINLAND, FactionType.GREECE, FactionType.GEORGIA, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAN, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.ISRAEL, FactionType.HUNGARY, FactionType.LIBYA, FactionType.MOROCCO, FactionType.PAKISTAN, FactionType.POLAND, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.USSR)),
    AAA_ZU23_URAL375("AAA ZU-23 on Ural-375", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.CHINA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.FINLAND, FactionType.GREECE, FactionType.GEORGIA, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAN, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.ISRAEL, FactionType.HUNGARY, FactionType.LIBYA, FactionType.MOROCCO, FactionType.PAKISTAN, FactionType.POLAND, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.USSR)),
    SPAAA_ZSU23_4_SHILKA("SPAAA ZSU-23-4 Shilka", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDIA, FactionType.IRAN, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.JORDAN, FactionType.LIBYA, FactionType.MOROCCO, FactionType.PAKISTAN, FactionType.POLAND, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.UK, FactionType.VIETNAM, FactionType.YEMEN, FactionType.GERMANY, FactionType.USSR, FactionType.ISRAEL, FactionType.USAF_AGGRESSORS)),

    // IR Launchers
    SAM_AVENGER_M1097("SAM Avenger M1097", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, MODERN_ERAS, Arrays.asList(FactionType.BAHRAIN, FactionType.EGYPT, FactionType.IRAQ, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SAM_CHAPARRAL_M48("SAM Chaparral M48", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.CHILE, FactionType.EGYPT, FactionType.ISRAEL, FactionType.MOROCCO, FactionType.TUNISIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SAM_LINEBACKER_M6("SAM Linebacker M6", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, MODERN_ERAS, Arrays.asList(FactionType.SAUDI_ARABIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SAM_STINGER_MANPADS("SAM Stinger MANPADS", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.MANPADS, MODERN_ERAS, Arrays.asList(FactionType.CROATIA, FactionType.CHILE, FactionType.DENMARK, FactionType.EGYPT, FactionType.FINLAND, FactionType.GEORGIA, FactionType.GERMANY, FactionType.GREECE, FactionType.IRAN, FactionType.IRAQ, FactionType.ISRAEL, FactionType.INDIA, FactionType.ITALY, FactionType.JAPAN, FactionType.SOUTH_KOREA, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.CHINA, FactionType.SWITZERLAND, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SAM_STINGER_COMMS("SAM Stinger Comms", AirDefenceUnitSubType.COMMUNICATION, AirDefenceWeaponType.NONE, MODERN_ERAS, Arrays.asList(FactionType.CROATIA, FactionType.CHILE, FactionType.DENMARK, FactionType.EGYPT, FactionType.FINLAND, FactionType.GEORGIA, FactionType.GERMANY, FactionType.GREECE, FactionType.IRAN, FactionType.IRAQ, FactionType.ISRAEL, FactionType.INDIA, FactionType.ITALY, FactionType.JAPAN, FactionType.SOUTH_KOREA, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.CHINA, FactionType.SWITZERLAND, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    SAM_SA13_STELA("SAM SA-13 Strela-10M3 9A35M3", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BELARUS, FactionType.BULGARIA, FactionType.CROATIA, FactionType.CZECH_REPUBLIC, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDIA, FactionType.JORDAN, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.IRAQ, FactionType.POLAND, FactionType.USSR)),
    SAM_SA18_IGLA_MANPADS("SAM SA-18 Igla-S MANPADS", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.MANPADS, MODERN_ERAS, Arrays.asList(FactionType.BELARUS, FactionType.BRAZIL, FactionType.BULGARIA, FactionType.EGYPT, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDONESIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.SYRIA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.FINLAND, FactionType.USSR)),
    SAM_SA18_IGLA_COMMS("SAM SA-18 Igla-S Comms", AirDefenceUnitSubType.COMMUNICATION, AirDefenceWeaponType.NONE, MODERN_ERAS, Arrays.asList(FactionType.BELARUS, FactionType.BRAZIL, FactionType.BULGARIA, FactionType.EGYPT, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.INDONESIA, FactionType.INDIA, FactionType.IRAN, FactionType.MALAYSIA, FactionType.MEXICO, FactionType.MOROCCO, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SLOVAKIA, FactionType.SOUTH_KOREA, FactionType.SYRIA, FactionType.THAILAND, FactionType.TURKEY, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.FINLAND, FactionType.USSR)),
    SAM_SA19_TUNGUSKA("SAM SA-19 Tunguska 2S6", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, MODERN_ERAS, Arrays.asList(FactionType.BELARUS, FactionType.INDIA, FactionType.MOROCCO, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.YEMEN, FactionType.USSR)),
    SAM_SA9_STRELA("SAM SA-9 Strela-1 9P31", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CROATIA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.HUNGARY, FactionType.INDIA, FactionType.LIBYA, FactionType.RUSSIA, FactionType.ROMANIA, FactionType.SERBIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.IRAQ, FactionType.POLAND, FactionType.USSR)),


    // SAM Systems
    SAM_HAWK_LN_M192("SAM Hawk LN M192", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.NORWAY, FactionType.GERMANY, FactionType.FRANCE, FactionType.BELGIUM, FactionType.DENMARK, FactionType.IRAN, FactionType.ISRAEL, FactionType.BAHRAIN, FactionType.ITALY, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.SWEDEN, FactionType.TURKEY, FactionType.UAE, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.IRAQ)),
    SAM_HAWK_SR_ANSPQ50("SAM Hawk SR AN/SPQ-50", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.NORWAY, FactionType.GERMANY, FactionType.FRANCE, FactionType.BELGIUM, FactionType.DENMARK, FactionType.IRAN, FactionType.ISRAEL, FactionType.BAHRAIN, FactionType.ITALY, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.SWEDEN, FactionType.TURKEY, FactionType.UAE, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.IRAQ)),
    SAM_HAWK_TR_ANMPQ46("SAM Hawk TR AN/MPQ-46", AirDefenceUnitSubType.TRACK_RADAR, AirDefenceWeaponType.NONE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.NORWAY, FactionType.GERMANY, FactionType.FRANCE, FactionType.BELGIUM, FactionType.DENMARK, FactionType.IRAN, FactionType.ISRAEL, FactionType.BAHRAIN, FactionType.ITALY, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.SWEDEN, FactionType.TURKEY, FactionType.UAE, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.IRAQ)),

    SAM_PATRIOT_AMG_ANMRC137("SAM Patriot AMG AN/MRC-137", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.NETHERLANDS, FactionType.GERMANY, FactionType.JAPAN, FactionType.ISRAEL, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.JORDAN, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.QATAR, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.ROMANIA, FactionType.POLAND, FactionType.SWEDEN)),
    SAM_PATRIOT_ECS_ANMPQ104("SAM Patriot ECS AN/MPQ-104", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.NETHERLANDS, FactionType.GERMANY, FactionType.JAPAN, FactionType.ISRAEL, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.JORDAN, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.QATAR, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.ROMANIA, FactionType.POLAND, FactionType.SWEDEN)),
    SAM_PATRIOT_EPP_3("SAM Patriot EPP III", AirDefenceUnitSubType.POWER, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.NETHERLANDS, FactionType.GERMANY, FactionType.JAPAN, FactionType.ISRAEL, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.JORDAN, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.QATAR, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.ROMANIA, FactionType.POLAND, FactionType.SWEDEN)),
    SAM_PATRIOT_LN_M901("SAM Patriot LN M-901", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.NETHERLANDS, FactionType.GERMANY, FactionType.JAPAN, FactionType.ISRAEL, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.JORDAN, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.QATAR, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.ROMANIA, FactionType.POLAND, FactionType.SWEDEN)),
    SAM_PATRIOT_STR_ANMPQ53("SAM Patriot STR AN/MPQ-53", AirDefenceUnitSubType.MULTI_MODE_RADAR, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.NETHERLANDS, FactionType.GERMANY, FactionType.JAPAN, FactionType.ISRAEL, FactionType.SAUDI_ARABIA, FactionType.KUWAIT, FactionType.JORDAN, FactionType.GREECE, FactionType.SOUTH_KOREA, FactionType.UAE, FactionType.QATAR, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.ROMANIA, FactionType.POLAND, FactionType.SWEDEN)),

    SAM_ROLAND_ADS("SAM Roland ADS", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 8.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.FRANCE, FactionType.GERMANY, FactionType.IRAQ, FactionType.QATAR, FactionType.SPAIN, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    SAM_ROLAND_EWR("SAM Roland EWR", AirDefenceUnitSubType.EARLY_WARNING_RADAR, AirDefenceWeaponType.NONE, 8.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.FRANCE, FactionType.GERMANY, FactionType.IRAQ, FactionType.QATAR, FactionType.SPAIN, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),

    CP_9S80M1_SBORKA("CP 9S80M1 Sborka", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.USSR)),

    EWR_1L13("EWR 1L13", AirDefenceUnitSubType.EARLY_WARNING_RADAR, AirDefenceWeaponType.NONE, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.USSR)),

    EWR_55G6("EWR 55G6", AirDefenceUnitSubType.EARLY_WARNING_RADAR, AirDefenceWeaponType.NONE, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.USSR)),

    SAM_SA10_S300_CP_54K6("SAM SA-10 S-300PS CP 54K6", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.GEORGIA)),
    SAM_SA10_S300_LN_5P85C("SAM SA-10 S-300PS LN 5P85C", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.GEORGIA)),
    SAM_SA10_S300_LN_5P85D("SAM SA-10 S-300PS LN 5P85D", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.GEORGIA)),
    SAM_SA10_S300_SR_5N66M("SAM SA-10 S-300PS SR 5N66M", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.GEORGIA)),
    SAM_SA10_S300_SR_64H6E("SAM SA-10 S-300PS SR 64H6E", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.GEORGIA)),
    SAM_SA10_S300_TR_30N6("SAM SA-10 S-300PS TR 30N6", AirDefenceUnitSubType.TRACK_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CHINA, FactionType.EGYPT, FactionType.GREECE, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.CZECH_REPUBLIC, FactionType.GERMANY, FactionType.GEORGIA)),

    SAM_SA11_BUK_CC_9S470M1("SAM SA-11 Buk CC 9S470M1", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 30.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.EGYPT, FactionType.GEORGIA, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.CHINA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.FINLAND, FactionType.USSR)),
    SAM_SA11_BUK_LN_9A310M1("SAM SA-11 Buk LN 9A310M1", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 30.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.EGYPT, FactionType.GEORGIA, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.CHINA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.FINLAND, FactionType.USSR)),
    SAM_SA11_BUK_SR_9R18M1("SAM SA-11 Buk SR 9R18M1", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 30.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.EGYPT, FactionType.GEORGIA, FactionType.INDIA, FactionType.IRAN, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.CHINA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.FINLAND, FactionType.USSR)),

    SAM_SA15_TOR("SAM SA-15 Tor 9A331", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 12.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BELARUS, FactionType.CHINA, FactionType.EGYPT, FactionType.GREECE, FactionType.IRAN, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.YEMEN, FactionType.MOROCCO, FactionType.SYRIA, FactionType.USSR, FactionType.GEORGIA)),

    SAM_SA8_OSA("SAM SA-8 Osa 9A33", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 15.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.GEORGIA, FactionType.GREECE, FactionType.INDIA, FactionType.JORDAN, FactionType.LIBYA, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.CZECH_REPUBLIC, FactionType.IRAQ, FactionType.KUWAIT, FactionType.USSR)),

    SAM_SA6_KUB_STR_9S91("SAM SA-6 Kub STR 9S91", AirDefenceUnitSubType.MULTI_MODE_RADAR, AirDefenceWeaponType.NONE, 24.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.EGYPT, FactionType.HUNGARY, FactionType.INDIA, FactionType.IRAN, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YEMEN, FactionType.IRAQ, FactionType.USSR)),
    SAM_SA6_KUB_LN_2P25("SAM SA-6 Kub LN 2P25", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 24.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.CZECH_REPUBLIC, FactionType.EGYPT, FactionType.HUNGARY, FactionType.INDIA, FactionType.IRAN, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YEMEN, FactionType.IRAQ, FactionType.USSR)),

    SAM_SA3_S125_LN_5P73("SAM SA-3 S-125 LN 5P73", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 35.0, EXTENDED_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.INDIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.SERBIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.FINLAND, FactionType.HUNGARY, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR)),
    SAM_SA3_S125_SR_P19("SAM SA-3 S-125 SR P-19", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 35.0, EXTENDED_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.INDIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.SERBIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.FINLAND, FactionType.HUNGARY, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR)),
    SAM_SA3_S125_TR_SNR("SAM SA-3 S-125 TR SNR", AirDefenceUnitSubType.TRACK_RADAR, AirDefenceWeaponType.NONE, 35.0, EXTENDED_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BULGARIA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.INDIA, FactionType.LIBYA, FactionType.NORTH_KOREA, FactionType.POLAND, FactionType.SERBIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.FINLAND, FactionType.HUNGARY, FactionType.IRAQ, FactionType.INSURGENTS, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.USSR))
    ;

    private String unitName;
    private AirDefenceUnitSubType subType;
    private AirDefenceWeaponType weaponType;
    private double range;
    private List<ConflictEraType> eras;
    private List<FactionType> factions;

    AirDefenceUnitType(String unitName, AirDefenceUnitSubType subType, AirDefenceWeaponType weaponType, List<ConflictEraType> eras, List<FactionType> factions) {
        this.unitName = unitName;
        this.subType = subType;
        this.weaponType = weaponType;
        this.eras = eras;
        this.factions = factions;
    }

    AirDefenceUnitType(String unitName, AirDefenceUnitSubType subType, AirDefenceWeaponType weaponType, double range, List<ConflictEraType> eras, List<FactionType> factions) {
        this.unitName = unitName;
        this.subType = subType;
        this.weaponType = weaponType;
        this.range = range;
        this.eras = eras;
        this.factions = factions;
    }

    public String getUnitName() {
        return unitName;
    }

    public AirDefenceUnitSubType getSubType() {
        return subType;
    }

    public AirDefenceWeaponType getWeaponType() {
        return weaponType;
    }

    public List<ConflictEraType> getEras() {
        return eras;
    }

    public List<FactionType> getFactions() {
        return factions;
    }

    public double getRange() {
        return range;
    }

    public static List<AirDefenceUnitType> getAirDefenceUnitTypesOfWeaponType(AirDefenceWeaponType type) {
        return Arrays.stream(AirDefenceUnitType.values()).filter(adut -> adut.getWeaponType().equals(type)).collect(Collectors.toList());
    }

    public static List<AirDefenceUnitType> getTypesByEra(ConflictEraType selectedEra) {
        return Arrays.stream(AirDefenceUnitType.values()).filter(t -> t.getEras().contains(selectedEra)).collect(Collectors.toList());
    }
}
