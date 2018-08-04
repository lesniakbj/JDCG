package sim.domain.enums;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static sim.domain.enums.StaticLists.CLASSIC_ERAS;
import static sim.domain.enums.StaticLists.COMPLETE_ERAS;
import static sim.domain.enums.StaticLists.EXTENDED_ERAS;
import static sim.domain.enums.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static sim.domain.enums.StaticLists.MODERN_ERAS;

public enum GroundUnitType {
    // Unarmed Units
    WILLYS_MB("Willy's MB", "Willys_MB", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    BEDFORD_MWD("Bedford MWD", "Bedford_MWD", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    CCKW_353("CCKW 353", "CCKW_353", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    APC_M1025_HMMWV("M1025 HMMWV", "Hummer", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    CP_PREDATOR_GCS("CP Predator GCS", "Predator GCS", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    CP_PREDATOR_TROJAN("CP Predator TrojanSpirit", "Predator TrojanSpirit", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    HEMTT_TFFT("HEMTT TFFT", "HEMTT TFFT", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    TANKER_M978_HEMTT("Tanker M978 HEMTT", "M978 HEMTT Tanker", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    TRANSPORT_M818("Transport M818", "M 818", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),

    APC_TIGR("APC Tigr 233036", "Tigr_233036", GroundUnitSubType.UNARMED, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    CP_SKP11_MCP("CP SKP-11 ATC Mobile Command Post", "SKP-11", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    CP_URAL357_PBU("CP Ural-375 PBU", "Ural-375 PBU", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    FUEL_TRUCK_ATMZ5("Fuel Truck ATMZ-5", "ATMZ-5", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    FUEL_TRUCK_ATZ10("Fuel Truck ATZ-10", "ATZ-10", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    GPU_APA5D_URAL4320("GPU APA-5D on Ural-4320", "Ural-4320 APA-5D", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    GPU_APA80_ZIL131("GPU APA-80 on ZIL-131", "ZiL-131 APA-80", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_FIRE_URAL_ATSP6("Transport Fire Engine Ural ATsP-6", "Ural ATsP-6", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_GAZ66("Transport GAZ-66", "GAZ-66", GroundUnitSubType.UNARMED, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_43101("Transport KAMAZ-43101", "KAMAZ Truck", GroundUnitSubType.UNARMED, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_UAZ469("Transport UAZ-469", "UAZ-469", GroundUnitSubType.UNARMED, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_URAL375("Transport Ural-375", "Ural-375", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_URAL4320_ARMORED("Transport Ural-4320-31 Armored", "Ural-4320-31", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_URAL4320T("Transport UAZ-4320T", "Ural-4320T", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TRANSPORT_ZIL131("Transport ZIL-131 KUNG", "ZIL-131 KUNG", GroundUnitSubType.UNARMED, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),

    // Armor Units
    APC_M2A1("APC M2AI", "M2A1_halftrack", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    M30_CC("M30 Cargo Carrier", "M30_CC", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    CROMWELL_4("CT Cromwell IV", "Cromwell_IV", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    M4_SHERMAN("MT M4 Sherman", "M4_Sherman", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    M4_SHERMAN_FIREFLY("MT M4A4 Sherman Firefly", "M4A4_Sherman_FF", GroundUnitSubType.UNARMED, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    APC_M1043_HMMWV("APC M1043 HMMWV Armament", "M1043 HMMWV Armament", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    ATGM_M1045_HMMWV("ATGM M1045 HMMWV TOW", "M1045 HMMWV TOW", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    APC_M113("APC M113", "M-113", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    APC_M1126_STRYKER("APC M1126 Stryker ICV", "M1126 Stryker ICV", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    IFV_LAV25("IFV LAV-25", "LAV-25", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    IFV_M2A2_BRADLEY("IFV M2A2 Bradley", "M-2 Bradley", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    ATGM_M1134_STRYKER("ATGM M1134 Stryker", "M1134 Stryker ATGM", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SPG_M1128_STRYKER("SPG M1128 Stryker MGS", "M1128 Stryker MGS", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    APC_AAV7("APC AAV-7", "AAV7", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    MBT_M1A2_ABRAMS("MBT M1A2 Abrams", "M-1 Abrams", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.USA)),

    APC_BTR_80("APC BTR-80", "BTR-80", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    APC_MTLB("APC MTLB", "MTLB", GroundUnitSubType.ARMOR, COMPLETE_ERAS, Arrays.asList(FactionType.RUSSIA)),
    ARV_BRMD2("ARV BRMD-2", "BRDM-2", GroundUnitSubType.ARMOR, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    ARV_BTR_RD("ARV BTR-RD", "BTR_D", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    ARV_MTLB_U("ARV MTLB-U BOMAN", "Boman", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    IFV_BMD_1("IFV BMD-1", "BMD-1", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    IFV_BMP_1("IFV BMP-1", "BMP-1", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    IFV_BMP_2("IFV BMP-2", "BMP-2", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    IFV_BMP_3("IFV BMP-3", "BMP-3", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    MBT_T55("MBT T-55", "T-55", GroundUnitSubType.ARMOR, COMPLETE_ERAS, Arrays.asList(FactionType.RUSSIA)),
    MBT_T72B("MBT T-72B", "T-72B", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    MBT_T80U("MBT T-80U", "T-80UD", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    MBT_T90("MBT T-90", "T-90", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),


    // Artillery
    M12_GMC("M12 GMC", "M12_GMC", GroundUnitSubType.ARTILLERY, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    MORTAR_2B11("2B11 mortar", "2B11 mortar", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.USA, FactionType.RUSSIA)),
    MLRS_FIRE_CONTROL("MLRS Fire Control", "MLRS FDDM", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    MLRS_M270("MLRS Launcher", "MLRS", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    SPH_M109_PALADIN("SPH M109 Paladin", "M-109", GroundUnitSubType.ARTILLERY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),

    MLRS_9A52_SMERCH("MLRS 9A52 Smerch", "Smerch", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    MLRS_9K57_URAGAN("MLRS 9K57 Uragan BM-27", "Uragan_BM-27", GroundUnitSubType.ARTILLERY, null, Arrays.asList(FactionType.RUSSIA)),
    MLRS_B21_GRAD("MLRS BM-21 Grad", "Grad-URAL", GroundUnitSubType.ARTILLERY, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SPH_2S1_GVODZDIKA("SPH 2S1 Gvozdika", "SAU Gvozdika", GroundUnitSubType.ARTILLERY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SPH_2S19_MSTA("SPH 2S19 Msta", "SAU Msta", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SPH_2S3_AKATSIA("SPH 2S3 Akatsia", "SAU Akatsia", GroundUnitSubType.ARTILLERY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SPH_2S9_NONA("SPH 2S9 Nona", "SAU 2-C9", GroundUnitSubType.ARTILLERY, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),

    // Infantry
    INFANTRY_WWII_US("Infantry M1 Garand", "soldier_wwii_us", GroundUnitSubType.INFANTRY, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    INFANTRY_M4("Infantry M4", "Soldier M4", GroundUnitSubType.INFANTRY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    INFANTRY_M249("Soldier M249", "Soldier M249", GroundUnitSubType.INFANTRY, MODERN_ERAS, Arrays.asList(FactionType.USA)),

    INFANTRY_SOLDIER_RUS("Infantry Soldier Rus", "Infantry AK", GroundUnitSubType.INFANTRY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    PARATROOPER_AKS("Paratrooper AKS", "Paratrooper AKS-74", GroundUnitSubType.INFANTRY, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA)),
    PARATROOPER_RPG16("Paratrooper RPG-16", "Paratrooper RPG-16", GroundUnitSubType.INFANTRY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),

    // Ships
    LS_SAMUEL_CHASE("LS Samuel Chase", "USS_Samuel_Chase", GroundUnitSubType.SHIP, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    LCVP_HIGGINS("LCVP (Higgins Boat)", "Higgins_boat", GroundUnitSubType.SHIP, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    LST_MKII("LST Mk.II", "LST_Mk2", GroundUnitSubType.SHIP, CLASSIC_ERAS, Arrays.asList(FactionType.USA)),
    LHA1_TARAWA("LHA-1 Tarawa", "LHA_Tarawa", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    FFG_7CL_OH_PERRY("FFG-7CL Oliver Hazzard Perry", "PERRY", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    CVN74_STENNIS("CVN-74 John C. Stennis", "Stennis", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),
    ARMED_SPEEDBOAT("Armed Speedboat", "speedboat", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA, FactionType.RUSSIA, FactionType.CHINA)),
    CG_60_NORMANDY("CG-60 Normandy", "TICONDEROG", GroundUnitSubType.SHIP, MODERN_ERAS, Arrays.asList(FactionType.USA)),
    CVN_70("CVN-70 Carl Vinson", "VINSON", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.USA)),

    BULK_CARGO_SHIP("Bulk Cargo Ship Yakushev", "Dry-cargo ship-1", GroundUnitSubType.SHIP, COMPLETE_ERAS, Arrays.asList(FactionType.RUSSIA)),
    DRY_CARGO_SHIP("Dry Cargo Ship Ivanov", "Dry-cargo ship-2", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    FFL_1124_GRISHA("FFL 1124.4 Grisha", "ALBATROS", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    TANKER_ELNYA("Tanker Elnya 160", "ELNYA", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SSK_877("SSK 877", "SOM", GroundUnitSubType.SHIP, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    CV_ADMIRAL_KUZNETSOV("CV 1143.5 Admiral Kuznetsov", "KUZNECOW", GroundUnitSubType.SHIP, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    FSG_1241_MOLNIYA("FSG 1241.1MP Molniya", "MOLNIYA", GroundUnitSubType.SHIP, MODERN_ERAS,Arrays.asList(FactionType.RUSSIA)),
    CG_1164_MOSKVA("CG 1164 Moskva", "MOSCOW", GroundUnitSubType.SHIP, MODERN_ERAS,Arrays.asList(FactionType.RUSSIA)),
    FFG_11540_NEUSTRASHIMY("FFG 11540 Neustrashimy", "NEUSTRASH", GroundUnitSubType.SHIP, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    CGN_1144_PYOTR_VELIKIY("CGN 1144.2 Pyotr Velikiy", "PIOTR", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    FF_1135_REZKY("FF 1135M Rezky", "REZKY", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),
    SSK_641B("SSK 641B", "KILO", GroundUnitSubType.SHIP, MODERN_ERAS, Arrays.asList(FactionType.RUSSIA)),
    CIVIL_BOAT("Civil boat Zvezdny", "ZWEZDNY", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA)),

    DDG_171("DDG-171 Haikou", "052C", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Collections.singletonList(FactionType.CHINA)),
    DDG_168("DDG-168 Guangzhou", "052B", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Collections.singletonList(FactionType.CHINA)),
    FFG_538("FFG-538 Yantai", "054A", GroundUnitSubType.SHIP, LATE_COLD_EXTENDED_WAR_ERAS, Collections.singletonList(FactionType.CHINA)),


    /*

    // Infantry
    // TODO: Check in game
    INFANTRY_AK("Infantry AK", GroundUnitSubType.INFANTRY, EXTENDED_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.USSR)),
    INFANTRY_RPG("Infantry RPG", GroundUnitSubType.INFANTRY, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.RUSSIA, FactionType.USSR)),


    // Armor
    // TODO: Check in game
    APC_LVTP7("APC LVTP-7", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.INDONESIA, FactionType.ITALY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.SPAIN, FactionType.THAILAND, FactionType.SOUTH_KOREA, FactionType.JAPAN, FactionType.PHILIPPINES)),
    APC_TPZ("APC TPZ", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.ALGERIA, FactionType.GERMANY, FactionType.ISRAEL, FactionType.KUWAIT, FactionType.NETHERLANDS, FactionType.SAUDI_ARABIA, FactionType.TURKEY, FactionType.UAE, FactionType.UK, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.VENEZUELA)),
    IFV_MARDER("IFV Marder", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.CHILE, FactionType.GERMANY, FactionType.INDONESIA, FactionType.JORDAN)),
    IFV_MCV80("IFV MCV-80 Warrior", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.UK, FactionType.KUWAIT)),
    MBT_LEOPARD_1A3("MBT Leopard 1A3", GroundUnitSubType.ARMOR, LATE_COLD_EXTENDED_WAR_ERAS, Arrays.asList(FactionType.BRAZIL, FactionType.CHILE, FactionType.GREECE, FactionType.TURKEY, FactionType.AUSTRALIA, FactionType.BELGIUM, FactionType.CANADA, FactionType.DENMARK, FactionType.ITALY, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.UK)),
    MBT_LECLERC("MBT Leclerc", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.FRANCE, FactionType.UAE)),
    MBT_LEOPARD_2("MBT Leopard-2", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.AUSTRIA, FactionType.CANADA, FactionType.CHILE, FactionType.DENMARK, FactionType.FINLAND, FactionType.GERMANY, FactionType.GREECE, FactionType.INDONESIA, FactionType.NETHERLANDS, FactionType.NORWAY, FactionType.POLAND, FactionType.QATAR, FactionType.SPAIN, FactionType.SWEDEN, FactionType.SWITZERLAND, FactionType.TURKEY, FactionType.SAUDI_ARABIA)),
    MBT_M60A3("MBT M-60A3 Patton", GroundUnitSubType.ARMOR, EXTENDED_ERAS, Arrays.asList(FactionType.BAHRAIN, FactionType.BRAZIL, FactionType.EGYPT, FactionType.GREECE, FactionType.IRAN, FactionType.ISRAEL, FactionType.JORDAN, FactionType.MOROCCO, FactionType.OMAN, FactionType.SAUDI_ARABIA, FactionType.SPAIN, FactionType.SUDAN, FactionType.THAILAND, FactionType.TUNISIA, FactionType.TURKEY, FactionType.USA, FactionType.USAF_AGGRESSORS, FactionType.YEMEN, FactionType.AUSTRIA, FactionType.IRAQ, FactionType.ITALY)),
    MBT_CHALLENGER_2("MBT Challenger 2", GroundUnitSubType.ARMOR, MODERN_ERAS, Arrays.asList(FactionType.UK, FactionType.OMAN)),
    */
    ;


    private String unitName;
    private String dcsName;
    private GroundUnitSubType subType;
    private List<ConflictEraType> eras;
    private List<FactionType> factions;


    GroundUnitType(String unitName, String dcsName, GroundUnitSubType subType, List<ConflictEraType> eras, List<FactionType> factions) {
        this.unitName = unitName;
        this.dcsName = dcsName;
        this.subType = subType;
        this.eras = eras;
        this.factions = factions;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getDcsName() {
        return dcsName;
    }

    public GroundUnitSubType getSubType() {
        return subType;
    }

    public List<FactionType> getFactions() {
        return factions;
    }

    public List<ConflictEraType> getEras() {
        return eras;
    }

    public static List<GroundUnitType> getTypesByEra(ConflictEraType selectedEra) {
        return Arrays.stream(GroundUnitType.values()).filter(t -> t.getEras() != null && t.getEras().contains(selectedEra)).collect(Collectors.toList());
    }

    public static List<GroundUnitType> getTypesByEraAndFactionTypes(ConflictEraType selectedEra, List<FactionType> factionTypeList) {
        List<GroundUnitType> validTypes = GroundUnitType.getTypesByEra(selectedEra);
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
