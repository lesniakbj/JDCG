package sim.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static sim.domain.enums.StaticLists.CLASSIC_ERAS;
import static sim.domain.enums.StaticLists.EXTENDED_ERAS;
import static sim.domain.enums.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static sim.domain.enums.StaticLists.MODERN_ERAS;

public enum AirDefenceUnitType {
    // IR Launchers
    SAM_STINGER_MANPADS("Stinger MANPADS", "Soldier stinger", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.MANPADS, 2.5, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_STINGER_COMMS("SAM Stinger Comm", "Stinger comm", AirDefenceUnitSubType.COMMUNICATION, AirDefenceWeaponType.NONE, 2.5, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_AVENGER_M1097("SAM Avenger M1097", "M1097 Avenger", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, 2.5, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_CHAPARRAL_M48("SAM Chaparral M48", "M48 Chaparral", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, 2.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    SAM_LINEBACKER_M6("SAM Linebacker M6", "M6 Linebacker", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, 2.5, MODERN_ERAS, Arrays.asList(FactionType.USA)),

    SAM_SA9_STRELA("SAM SA-9 Strela-1 9P31", "Strela-1 9P31", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, 3.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA13_STRELA("SAM SA-13 Strela-10M3 9A35M3", "Strela-10M3", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, 3.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA18_IGLA_MANPADS("SAM SA-18 Igla-S MANPADS", "SA-18 Igla-S manpad", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.MANPADS, 2.5, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA18_IGLA_COMMS("SAM SA-18 Igla-S Comms", "SA-18 Igla-S comm", AirDefenceUnitSubType.COMMUNICATION, AirDefenceWeaponType.NONE, 2.5, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA19_TUNGUSKA("SAM SA-19 Tunguska 2S6", "2S6 Tunguska", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.IR_MISSILE, 3.5, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),


    // AAA Systems
    AAA_BOFORS("AAA Bofors 40mm", "bofors40", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, 2.5, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    AAA_VULCAN("AAA Vulcan M163", "Vulcan", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, 3.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),

    AAA_ZU23_CLOSED("AAA ZU-23 Closed", "ZU-23 Emplacement Closed", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, 3.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    AAA_ZU23_EMPLACEMENT("AAA ZU-23 Emplacement", "ZU-23 Emplacement", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, 3.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    AAA_ZU23_URAL375("AAA ZU-23 on Ural-375", "Ural-375 ZU-23", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, 3.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SPAAA_ZSU23_4_SHILKA("AAA ZU-23 Closed", "ZSU-23-4 Shilka", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, 3.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),


    // Radar SAM Systems
    SAM_HAWK_CWAR("SAM Hawk CWAR AN/MPQ-55", "Hawk cwar", AirDefenceUnitSubType.EARLY_WARNING_RADAR, AirDefenceWeaponType.NONE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    SAM_HAWK_LN_M192("SAM Hawk LN M192", "Hawk ln", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    SAM_HAWK_PCP("SAM Hawk PCP", "Hawk pcp", AirDefenceUnitSubType.POWER, AirDefenceWeaponType.NONE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    SAM_HAWK_SR_ANSPQ50("SAM Hawk SR AN/SPQ-50", "Hawk sr", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    SAM_HAWK_TR_ANMPQ46("SAM Hawk TR AN/MPQ-46", "Hawk tr", AirDefenceUnitSubType.TRACK_RADAR, AirDefenceWeaponType.NONE, 45.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    SAM_PATRIOT_AMG_ANMRC137("SAM Patriot AMG AN/MRC-137", "Patriot AMG", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_PATRIOT_ECS_ANMSQ104("SAM Patriot ECS AN/MPQ-104", "Patriot ECS", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_PATRIOT_EPP_3("SAM Patriot EPP III", "Patriot EPP", AirDefenceUnitSubType.POWER, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_PATRIOT_ICC("SAM Patriot ICC", "Patriot cp", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_PATRIOT_LN_M901("SAM Patriot LN M-901", "Patriot ln", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SAM_PATRIOT_STR_ANMPQ53("SAM Patriot STR AN/MPQ-53", "Patriot str", AirDefenceUnitSubType.MULTI_MODE_RADAR, AirDefenceWeaponType.NONE, 80.0, MODERN_ERAS, Arrays.asList(FactionType.USA)),

    CP_9S80M1_SBORKA("CP 9S80M1 Sborka", "Dog Ear radar", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 120.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    EWR_1L13("EWR 1L13", "1L13 EWR", AirDefenceUnitSubType.EARLY_WARNING_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    EWR_55G6("EWR 55G6", "55G6 EWR", AirDefenceUnitSubType.EARLY_WARNING_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA3_S125_LN_5P73("SAM SA-3 S-125 LN 5P73",  "5p73 s-125 ln", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 35.0, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA3_S125_SR_P19("SAM SA-3 S-125 SR P-19", "p-19 s-125 sr", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 35.0, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA3_S125_TR_SNR("SAM SA-3 S-125 TR SNR", "snr s-125 tr", AirDefenceUnitSubType.TRACK_RADAR, AirDefenceWeaponType.NONE, 35.0, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA6_KUB_STR_9S91("SAM SA-6 Kub STR 9S91", "Kub 1S91 str", AirDefenceUnitSubType.MULTI_MODE_RADAR, AirDefenceWeaponType.NONE, 24.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA6_KUB_LN_2P25("SAM SA-6 Kub LN 2P25", "Kub 2P25 ln", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 24.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA8_OSA("SAM SA-8 Osa 9A33", "Osa 9A33 ln", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 15.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA10_S300_CP_54K6("SAM SA-10 S-300PS CP 54K6", "S-300PS 54K6 cp", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    SAM_SA10_S300_LN_5P85C("SAM SA-10 S-300PS LN 5P85C", "S-300PS 5P85C ln", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    SAM_SA10_S300_LN_5P85D("SAM SA-10 S-300PS LN 5P85D", "S-300PS 5P85D ln", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    SAM_SA10_S300_SR_5N66M("SAM SA-10 S-300PS SR 5N66M", "S-300PS 40B6MD sr", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    SAM_SA10_S300_SR_64H6E("SAM SA-10 S-300PS SR 64H6E", "S-300PS 64H6E sr", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    SAM_SA10_S300_TR_30N6("SAM SA-10 S-300PS TR 30N6", "S-300PS 40B6M tr", AirDefenceUnitSubType.TRACK_RADAR, AirDefenceWeaponType.NONE, 200.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),
    SAM_SA11_BUK_CC_9S470M1("SAM SA-11 Buk CC 9S470M1", "SA-11 Buk CC 9S470M1", AirDefenceUnitSubType.COMMAND_POST, AirDefenceWeaponType.NONE, 30.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA11_BUK_LN_9A310M1("SAM SA-11 Buk LN 9A310M1", "SA-11 Buk LN 9A310M1", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 30.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA11_BUK_SR_9R18M1("SAM SA-11 Buk SR 9R18M1", "SA-11 Buk SR 9S18M1", AirDefenceUnitSubType.SEARCH_RADAR, AirDefenceWeaponType.NONE, 30.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SAM_SA15_TOR("SAM SA-15 Tor 9A331", "Tor 9A331", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 12.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.CHINA)),



    /*
    // AAA Systems
    SPAA_GEPARD("SPAAA Gepard", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.AAA, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.GERMANY, FactionType.JORDAN, FactionType.ROMANIA, FactionType.BELGIUM, FactionType.CHILE, FactionType.NETHERLANDS)),

    // IR Launchers


    // SAM Systems
    SAM_ROLAND_ADS("SAM Roland ADS", AirDefenceUnitSubType.LAUNCHER, AirDefenceWeaponType.RADAR_MISSILE, 8.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.FRANCE, FactionType.GERMANY, FactionType.IRAQ, FactionType.QATAR, FactionType.SPAIN, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    SAM_ROLAND_EWR("SAM Roland EWR", AirDefenceUnitSubType.EARLY_WARNING_RADAR, AirDefenceWeaponType.NONE, 8.0, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.FRANCE, FactionType.GERMANY, FactionType.IRAQ, FactionType.QATAR, FactionType.SPAIN, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),








    */
    ;

    private String unitName;
    private String dcsName;
    private AirDefenceUnitSubType subType;
    private AirDefenceWeaponType weaponType;
    private double range;
    private List<ConflictEraType> eras;
    private List<FactionType> factions;

    AirDefenceUnitType(String unitName, String dcsName, AirDefenceUnitSubType subType, AirDefenceWeaponType weaponType, double range, List<ConflictEraType> eras, List<FactionType> factions) {
        this.unitName = unitName;
        this.dcsName = dcsName;
        this.subType = subType;
        this.weaponType = weaponType;
        this.range = range;
        this.eras = eras;
        this.factions = factions;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getDcsName() {
        return dcsName;
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

    public static List<AirDefenceUnitType> getTypesByEraAndFactionTypes(ConflictEraType selectedEra, List<FactionType> factionTypeList) {
        List<AirDefenceUnitType> validTypes = AirDefenceUnitType.getTypesByEra(selectedEra);
        validTypes = validTypes.stream().filter(gut -> {
            for(FactionType ft : factionTypeList) {
                if(gut.getFactions().contains(ft)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return validTypes;
    }
}
