package sim.domain.enums;

import static sim.domain.enums.StaticLists.EXTENDED_ERAS;
import static sim.domain.enums.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static sim.domain.enums.StaticLists.MODERN_ERAS;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum GroundUnitType {
    // Unarmed Units
    APC_M1025_HMMWV("APC M1025 HMMWV", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.BAHRAIN, FactionType.BULGARIA, FactionType.CANADA, FactionType.CZECH_REPUBLIC, FactionType.DENMARK, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.ISRAEL, FactionType.INSURGENTS, FactionType.KUWAIT, FactionType.LIBYA, FactionType.MEXICO, FactionType.OMAN, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.ROMANIA, FactionType.SAUDI_ARABIA, FactionType.SPAIN, FactionType.SERBIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    CP_PREDATOR_GCS("CP Predator GCS", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.ITALY, FactionType.TURKEY, FactionType.UAE, FactionType.MOROCCO, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    CP_PREDATOR_TROJAN("CP Predator TrojanSpirit", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.ITALY, FactionType.TURKEY, FactionType.UAE, FactionType.MOROCCO, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    HEMTT_TFFT("HEMTT TFFT", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.JORDAN, FactionType.KUWAIT, FactionType.MOROCCO, FactionType.OMAN, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    TANKER_M978_HEMTT("Tanker M978 HEMTT", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.EGYPT, FactionType.GREECE, FactionType.ISRAEL, FactionType.JORDAN, FactionType.KUWAIT, FactionType.MOROCCO, FactionType.OMAN, FactionType.QATAR, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.TURKEY, FactionType.UAE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    TRANSPORT_M818("Transport M818", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.EGYPT, FactionType.GEORGIA, FactionType.INDONESIA, FactionType.IRAQ, FactionType.SAUDI_ARABIA, FactionType.MOROCCO, FactionType.MEXICO, FactionType.MALAYSIA, FactionType.PHILIPPINES, FactionType.TURKEY)),

    CP_SKP11_MCP("CP SKP-11 ATC Mobile Command Post", GroundUnitSubType.UNARMED, null, null),
    CP_URAL357_PBU("CP Ural-375 PBU", GroundUnitSubType.UNARMED, null, null),
    FUEL_TRUCK_ATMZ5("Fuel Truck ATMZ-5", GroundUnitSubType.UNARMED, null, null),
    FUEL_TRUCK_ATZ10("Fuel Truck ATZ-10", GroundUnitSubType.UNARMED, null, null),
    GPU_APA50_URAL375("GPU APA-50 on Ural-375", GroundUnitSubType.UNARMED, null, null),
    GPU_APA80_ZIL131("GPU APA-80 on ZIL-131", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_GAZ3307("Transport GAZ-3307", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_GAZ3308("Transport GAZ-3308", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_GAZ66("Transport GAZ-66", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_IKARURS280("Transport IKARUS-280", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_43101("Transport KAZAM-43101", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_LAZ695("Transport LAZ-695", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_MAZ6303("Transport MAZ-6303", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_UAZ469("Transport UAZ-469", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_UAZ375("Transport UAZ-375", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_UAZ4320_ARMORED("Transport UAZ-4320-31 Armored", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_UAZ4321("Transport UAZ-4321", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_VAZ2109("Transport VAZ-2109", GroundUnitSubType.UNARMED, MODERN_ERAS, null),
    TRANSPORT_ZIL131("Transport ZIL-131 KUNG", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, null),
    TRANSPORT_ZIL4331("Transport ZIL-4331", GroundUnitSubType.UNARMED, MODERN_ERAS, null),
    TRANSPORT_ZIL9("Transport ZIL-9", GroundUnitSubType.UNARMED, null, null),
    TRANSPORT_FIRE_URAL_ATSP6("Transport Fire Engine Ural ATsP-6", GroundUnitSubType.UNARMED, null, null),

    // Infantry
    INFANTRY_M249("Infantry M249", GroundUnitSubType.INFANTRY, null, null),
    INFANTRY_M4("Infantry M4", GroundUnitSubType.INFANTRY, null, null),
    INFANTRY_SOLDIER_RUS("Infantry Soldier Rus", GroundUnitSubType.INFANTRY, null, null),
    PARATROOPER_AKS("Paratrooper AKS", GroundUnitSubType.INFANTRY, null, null),
    PARATROOPER_RPG16("Paratrooper RPG-16", GroundUnitSubType.INFANTRY, null, null),
    INFANTRY_AK("Infantry AK", GroundUnitSubType.INFANTRY, null, null),
    INFANTRY_RPG("Infantry RPG", GroundUnitSubType.INFANTRY, null, null),

    // Artillery
    MLRS_M270("MLRS M270", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.EGYPT, FactionType.BAHRAIN, FactionType.FINLAND, FactionType.GERMANY, FactionType.FRANCE, FactionType.GREECE, FactionType.ISRAEL, FactionType.ITALY, FactionType.JAPAN, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.TURKEY, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.DENMARK, FactionType.NETHERLANDS, FactionType.NORWAY)),
    SPH_M109_PALADIN("SPH M109 Paladin", GroundUnitSubType.ARTILLERY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.LIBYA, FactionType.GREECE, FactionType.IRAN, FactionType.KUWAIT, FactionType.MOROCCO, FactionType.OMAN, FactionType.SWITZERLAND, FactionType.UAE, FactionType.BRAZIL, FactionType.DENMARK, FactionType.ITALY, FactionType.JORDAN, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.SPAIN, FactionType.AUSTRIA, FactionType.ISRAEL, FactionType.MALAYSIA, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    MORTAR_2B11("2B11 Mortar", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.EGYPT, FactionType.GEORGIA, FactionType.POLAND, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.USSR)),
    MLRS_9A52_SMERCH("MLRS 9A52 Smerch", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.CHINA, FactionType.INDIA, FactionType.IRAQ, FactionType.KUWAIT, FactionType.MOROCCO, FactionType.PAKISTAN, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.UAE, FactionType.VENEZUELA, FactionType.USSR)),
    MLRS_B21_GRAD("MLRS B-21 Grad", GroundUnitSubType.ARTILLERY, EXTENDED_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CROATIA, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.GREECE, FactionType.HUNGARY, FactionType.INDIA, FactionType.INDONESIA, FactionType.IRAN, FactionType.IRAQ, FactionType.LIBYA, FactionType.MOROCCO, FactionType.NORTH_KOREA, FactionType.PAKISTAN, FactionType.POLAND, FactionType.QATAR, FactionType.ROMANIA, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SYRIA, FactionType.THAILAND, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.VIETNAM, FactionType.YEMEN, FactionType.FINLAND, FactionType.ISRAEL, FactionType.USSR)),
    SPH_2S1_GVODZDIKA("SPH 2S1 Gvozdika", GroundUnitSubType.ARTILLERY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.BULGARIA, FactionType.CROATIA, FactionType.FINLAND, FactionType.GEORGIA, FactionType.INDIA, FactionType.IRAN, FactionType.IRAQ, FactionType.LIBYA, FactionType.POLAND, FactionType.RUSSIA, FactionType.SERBIA, FactionType.SLOVAKIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.YEMEN, FactionType.CZECH_REPUBLIC, FactionType.USSR, FactionType.HUNGARY, FactionType.INSURGENTS, FactionType.ROMANIA, FactionType.YUGOSLAVIA)),
    SPH_2S19_MSTA("SPH 2S19 Msta", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.BELARUS, FactionType.GEORGIA, FactionType.ETHIOPIA, FactionType.RUSSIA, FactionType.UKRAINE, FactionType.VENEZUELA, FactionType.MOROCCO, FactionType.USSR)),
    SPH_2S3_AKATSIA("SPH 2S3 Akatsia", GroundUnitSubType.ARTILLERY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.BELARUS, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.HUNGARY, FactionType.LIBYA, FactionType.RUSSIA, FactionType.SYRIA, FactionType.UKRAINE, FactionType.VIETNAM, FactionType.BULGARIA, FactionType.IRAQ, FactionType.USSR)),
    SPH_2S9_NONA("SPH 2S9 Nona", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.SYRIA, FactionType.VENEZUELA, FactionType.USSR)),

    // Armor
    APC_LVTP7("APC LVTP-7", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.INDONESIA, FactionType.ITALY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.SPAIN, FactionType.THAILAND, FactionType.SOUTH_KOREA, FactionType.JAPAN, FactionType.PHILIPPINES)),
    APC_M1043_HMMWV("APC M1043 HMMWV Armament", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.BAHRAIN, FactionType.BULGARIA, FactionType.CANADA, FactionType.CZECH_REPUBLIC, FactionType.DENMARK, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.ISRAEL, FactionType.INSURGENTS, FactionType.KUWAIT, FactionType.LIBYA, FactionType.MEXICO, FactionType.OMAN, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.ROMANIA, FactionType.SAUDI_ARABIA, FactionType.SPAIN, FactionType.SERBIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    APC_M1126_STRYKER("APC M1126 Stryker ICV", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.CHILE, FactionType.IRAQ, FactionType.CANADA)),
    APC_M113("APC M113", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.AUSTRALIA, FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.CANADA, FactionType.CHILE, FactionType.DENMARK, FactionType.EGYPT, FactionType.GERMANY, FactionType.GREECE, FactionType.IRAN, FactionType.IRAQ, FactionType.INDONESIA, FactionType.ISRAEL, FactionType.JORDAN, FactionType.KUWAIT, FactionType.MOROCCO, FactionType.NORWAY, FactionType.PAKISTAN, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.SAUDI_ARABIA, FactionType.SOUTH_KOREA, FactionType.SPAIN, FactionType.SWITZERLAND, FactionType.THAILAND, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VIETNAM, FactionType.YEMEN, FactionType.ETHIOPIA, FactionType.ITALY, FactionType.INDIA)),
    APC_TPZ("APC TPZ", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.GERMANY, FactionType.ISRAEL, FactionType.KUWAIT, FactionType.NETHERLANDS, FactionType.SAUDI_ARABIA, FactionType.TURKEY, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    ATGM_M1045_HMMWV("ATGM M1045 HMMWV TOW", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.BAHRAIN, FactionType.BULGARIA, FactionType.CANADA, FactionType.CZECH_REPUBLIC, FactionType.DENMARK, FactionType.EGYPT, FactionType.ETHIOPIA, FactionType.GEORGIA, FactionType.IRAQ, FactionType.ISRAEL, FactionType.INSURGENTS, FactionType.KUWAIT, FactionType.LIBYA, FactionType.MEXICO, FactionType.OMAN, FactionType.PHILIPPINES, FactionType.POLAND, FactionType.ROMANIA, FactionType.SAUDI_ARABIA, FactionType.SPAIN, FactionType.SERBIA, FactionType.SUDAN, FactionType.SYRIA, FactionType.UKRAINE, FactionType.USA, FactionType.USAF_AGGRESSORS)),
    ATGM_M1134_STRYKER("ATGM M1134 Stryker", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.CHILE, FactionType.IRAQ, FactionType.CANADA)),
    IFV_LAV25("IFV LAV-25", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.CANADA)),
    IFV_M2A2_BRADLEY("IFV M2A2 Bradley", GroundUnitSubType.ARMOR, null, null),
    IFV_MARDER("IFV Marder", GroundUnitSubType.ARMOR, null, null),
    IFV_MCV80("IFV MCV-80", GroundUnitSubType.ARMOR, null, null),
    MBT_M1A2_ABRAMS("MBT M1A2 Abrams", GroundUnitSubType.ARMOR, null, null),
    MBT_LEOPARD_1A3("MBT Leopard 1A3", GroundUnitSubType.ARMOR, null, null),
    MBT_LECLERC("MBT Leclerc", GroundUnitSubType.ARMOR, null, null),
    MBT_LEOPARD_2("MBT Leopard-2", GroundUnitSubType.ARMOR, null, null),
    MBT_M60A3("MBT M-60A3 Patton", GroundUnitSubType.ARMOR, null, null),
    MBT_CHALLENGER_2("MBT Challenger 2", GroundUnitSubType.ARMOR, null, null),
    SPG_M1128_STRYKER("SPG M1128 Stryker", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.CHILE, FactionType.IRAQ, FactionType.CANADA)),
    APC_BTR_80("APC BTR-80", GroundUnitSubType.ARMOR, null, null),
    APC_MTLB("APC MTLB", GroundUnitSubType.ARMOR, null, null),
    ARV_BRMD2("ARV BRMD-2", GroundUnitSubType.ARMOR, null, null),
    ARV_BTR_RD("ARV BTR-RD", GroundUnitSubType.ARMOR, null, null),
    ARV_MTLB_U("ARV MTLB-U BOMAN", GroundUnitSubType.ARMOR, null, null),
    IFV_BMD_1("IFV BMD-1", GroundUnitSubType.ARMOR, null, null),
    IFV_BMP_1("IFV BMP-1", GroundUnitSubType.ARMOR, null, null),
    IFV_BMP_2("IFV BMP-2", GroundUnitSubType.ARMOR, null, null),
    IFV_BMP_3("IFV BMP-3", GroundUnitSubType.ARMOR, null, null),
    MBT_T55("MBT T-55", GroundUnitSubType.ARMOR, null, null),
    MBT_T72B("MBT T-72B", GroundUnitSubType.ARMOR, null, null),
    MBT_T80U("MBT T-80U", GroundUnitSubType.ARMOR, null, null),
    MBT_T90("MBT T-90", GroundUnitSubType.ARMOR, null, null),

    // Ships
    ARMED_SPEEDBOAT("Armed Speedboat", GroundUnitSubType.SHIP, null, null),
    CG_60_NORMANDY("CG-60 Normandy", GroundUnitSubType.SHIP, null, null),
    CVN_70("CVN-70 Carl Vinson", GroundUnitSubType.SHIP, null, null),
    FFG_7("FFG-7 O.H. Perry", GroundUnitSubType.SHIP, null, null),
    BULK_CARGO_SHIP("Bulk Cargo ship Yakusev", GroundUnitSubType.SHIP, null, null),
    CG_1174("CG 1174 Mskva", GroundUnitSubType.SHIP, null, null),
    CGN_1144("CGN 1144.2 Pyotr Velikly", GroundUnitSubType.SHIP, null, null),
    CV_1143("CV 1143.5 Admiral Kutnesov", GroundUnitSubType.SHIP, null, null),
    CIVIL_BOAT("Civil boat Zvezdny", GroundUnitSubType.SHIP, null, null),
    DRY_CARGO_SHIP("Dry cargo ship Ivanov", GroundUnitSubType.SHIP, null, null),
    FF_1135M("FF 1135M Rezky", GroundUnitSubType.SHIP, null, null),
    FFG_11540("FFG 11540 Neustrashimy", GroundUnitSubType.SHIP, null, null),
    FFL_1124("FFL 1124.4 Grisha", GroundUnitSubType.SHIP, null, null),
    FSG_1141("FSG 1141.1MP Molniya", GroundUnitSubType.SHIP, null, null),
    SSK_641B("SSK 641B", GroundUnitSubType.SHIP, null, null),
    SSK_877("SSK 877", GroundUnitSubType.SHIP, null, null),
    TANKER("Tanker Elinya 160", GroundUnitSubType.SHIP, null, null),
    ;


    private String unitName;
    private GroundUnitSubType subType;
    private List<FactionType> factions;

    GroundUnitType(String unitName, GroundUnitSubType subType, List<ConflictEraType> eras, List<FactionType> factions) {
        this.unitName = unitName;
        this.subType = subType;
        this.factions = factions;
    }

    public String getUnitName() {
        return unitName;
    }

    public GroundUnitSubType getSubType() {
        return subType;
    }

    public List<FactionType> getFactions() {
        return factions;
    }
}
