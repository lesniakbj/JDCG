package sim.domain.enums;

import java.util.Arrays;

import static sim.domain.enums.MunitionSubType.AIR_TO_AIR;
import static sim.domain.enums.MunitionSubType.AIR_TO_SURFACE;
import static sim.domain.enums.MunitionSubType.ANTI_RADIATION;
import static sim.domain.enums.MunitionSubType.BOMBS;
import static sim.domain.enums.MunitionSubType.GUN_INTERNAL;
import static sim.domain.enums.MunitionSubType.GUN_POD;
import static sim.domain.enums.MunitionSubType.NONE;
import static sim.domain.enums.MunitionSubType.ROCKETS;
import static sim.domain.enums.MunitionSubType.SENSOR_PODS;

public enum MunitionType {
    // =============================================
    //              BLUEFOR WEAPONRY
    // =============================================

    // Internal Cannons
    M61A1_VULCAN("M61A1 Vulcan", GUN_INTERNAL, FactionSideType.BLUEFOR, false),
    GAU8A_AVENGER("GAU-8/A Avenger", GUN_INTERNAL, FactionSideType.BLUEFOR, false),
    M134_MINIGUN("M-134 Minigun", GUN_INTERNAL, FactionSideType.BLUEFOR, false),
    M60_MG("M-60 Machine Gun", GUN_INTERNAL, FactionSideType.BLUEFOR, false),

    // AIM-7 Family
    AIM7M("AIM-7M", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM7F("AIM-7F", AIR_TO_AIR, FactionSideType.BLUEFOR, true),

    // AIM-9 Family
    CAP9M("CAP-9M", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM9P5("AIM-9P5", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM9L("AIM-9L", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM9M("AIM-9M", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM9X("AIM-9X", AIR_TO_AIR, FactionSideType.BLUEFOR, true),

    // AIM-120 Family
    AIM120B("AIM-120B", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM120C("AIM-120C", AIR_TO_AIR, FactionSideType.BLUEFOR, true),

    // Mk8X Family
    AN_M64("AN-M64", BOMBS, FactionSideType.BLUEFOR, true),
    MK_82("Mk-82", BOMBS, FactionSideType.BLUEFOR, true),
    MK_82SE("Mk-82 SnakeEye", BOMBS, FactionSideType.BLUEFOR, true),
    MK_82AIR("Mk-82 AIR", BOMBS, FactionSideType.BLUEFOR, true),
    MK_83("Mk-83", BOMBS, FactionSideType.BLUEFOR, true),
    MK_84("Mk-84", BOMBS, FactionSideType.BLUEFOR, true),

    // Illumination Bombs
    LUU_2("LUU-2", BOMBS, FactionSideType.BLUEFOR, true),
    MK_257_7("7 M257 Illumination", ROCKETS, FactionSideType.BLUEFOR, true),

    // Guided Bombs
    GBU_10("GBU-10 Paveway II", BOMBS, FactionSideType.BLUEFOR, true),
    GBU_12("GBU-12 Paveway II", BOMBS, FactionSideType.BLUEFOR, true),
    GBU_16("GBU-16 Paveway II", BOMBS, FactionSideType.BLUEFOR, true),
    GBU_31("GBU-31", BOMBS, FactionSideType.BLUEFOR, true),
    GBU_313B("GBU-31(V)3/B", BOMBS, FactionSideType.BLUEFOR, true),
    GBU_38("GBU-38", BOMBS, FactionSideType.BLUEFOR, true),

    // Cluster Munitions
    MK_20("Mk-20 RockEye", BOMBS, FactionSideType.BLUEFOR, true),
    CBU_87("CBU-87", BOMBS, FactionSideType.BLUEFOR, true),
    CBU_97("CBU-97", BOMBS, FactionSideType.BLUEFOR, true),
    CBU_99("CBU-99", BOMBS, FactionSideType.BLUEFOR, true),

    // Guided Cluster Munitions
    CBU_103("CBU-103", BOMBS, FactionSideType.BLUEFOR, true),
    CBU_105("CBU-105", BOMBS, FactionSideType.BLUEFOR, true),

    // Rockets
    HVAR("HVAR", ROCKETS, FactionSideType.BLUEFOR, true),
    ZUNI_MK_71("ZUNI Mk-71", ROCKETS, FactionSideType.BLUEFOR, true),
    MK_151_HE_19("19 Mk-151 HE Hydra", ROCKETS, FactionSideType.BLUEFOR, true),
    MK_151_HE_7("7 Mk-151 HE Hydra", ROCKETS, FactionSideType.BLUEFOR, true),
    MK_156_WP_7("7 Mk-156 WP Hydra", ROCKETS, FactionSideType.BLUEFOR, true),
    MK_5_HE_7("7 Mk-5 HEAT Hydra", ROCKETS, FactionSideType.BLUEFOR, true),

    // Missiles
    AGM_62("AGM-62", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_65D("AGM-65D", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_65E("AGM-65E", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_65G("AGM-65G", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_65H("AGM-65H", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_65K("AGM-65K", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_84A("AGM-84A", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_84E("AGM-84E", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_88C("AGM-88C", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),
    AGM_154C("AGM-154C", AIR_TO_SURFACE, FactionSideType.BLUEFOR, true),

    // Pods
    ANAAQ_28("AN/AAQ-28 LITENING", SENSOR_PODS, FactionSideType.BLUEFOR, true),
    ANAAS_38("AN/AAS-38 FLIR", SENSOR_PODS, FactionSideType.BLUEFOR, true),
    ANASQ_173("AN/ASQ-173 LST/SCAM", SENSOR_PODS, FactionSideType.BLUEFOR, true),

    // =============================================
    //              REDFOR WEAPONRY
    // =============================================

    // Internal Cannons
    GSH_30("GSh-30 Single Barrel", GUN_INTERNAL, FactionSideType.REDFOR, false),
    GSH_30_2("GSh-30 Dual Barrel", GUN_INTERNAL, FactionSideType.REDFOR, false),
    AC_2A42("2A42 Autocannon", GUN_INTERNAL, FactionSideType.REDFOR, false),

    // R27 Family
    R27R("R-27R", AIR_TO_AIR, FactionSideType.REDFOR, true),
    R27ER("R-27ER", AIR_TO_AIR, FactionSideType.REDFOR, true),
    R27T("R-27T", AIR_TO_AIR, FactionSideType.REDFOR, true),
    R27ET("R-27ET", AIR_TO_AIR, FactionSideType.REDFOR, true),

    // R60 Family
    R60M("R-60M", AIR_TO_AIR, FactionSideType.REDFOR, true),

    // R73 Family
    R73("R-73", AIR_TO_AIR, FactionSideType.REDFOR, true),

    // R77 Family
    R77("R-77", AIR_TO_AIR, FactionSideType.REDFOR, true),

    // Bombs
    FAB100("FAB-100", BOMBS, FactionSideType.REDFOR, true),
    FAB250("FAB-250", BOMBS, FactionSideType.REDFOR, true),
    FAB500("FAB-500", BOMBS, FactionSideType.REDFOR, true),
    FAB1500("FAB-1500", BOMBS, FactionSideType.REDFOR, true),

    // Guided Bombs
    KAB500KR("KAB-500kr", BOMBS, FactionSideType.REDFOR, true),

    // Anti-Runway / Concrete
    BETAB500("BetAB-500", BOMBS, FactionSideType.REDFOR, true),
    BETAB500SHP("BetAB-500ShP", BOMBS, FactionSideType.REDFOR, true),

    // Illumination
    SAB100("SAB-100", BOMBS, FactionSideType.REDFOR, true),

    // Cluster Munitions
    RBK250("RBK-250 PTAB 2.5M", BOMBS, FactionSideType.REDFOR, true),
    RBK500("RBK-500 PTAB 10 5", BOMBS, FactionSideType.REDFOR, true),
    RBK500U("RBK-500U PTAB 1M", BOMBS, FactionSideType.REDFOR, true),
    KMGU2AO("KMGU-2 96x AO 2.5RT", BOMBS, FactionSideType.REDFOR, true),
    KMGU2PTAB("KMGU-2 96x PTAB 2.5KO", BOMBS, FactionSideType.REDFOR, true),

    // Gun Pods
    SPPU22("SPPU-22-1", GUN_POD, FactionSideType.REDFOR, true),
    UPK_23_250("UPK-23-250", GUN_POD, FactionSideType.REDFOR, true),
    GUV_8700_9A800("GUV-8700 9-A-800", GUN_POD, FactionSideType.REDFOR, true),
    GUV_8700_9A624("GUV-8700 9-A-624/622", GUN_POD, FactionSideType.REDFOR, true),

    // Rockets
    S5KO("S-5 KO", ROCKETS, FactionSideType.REDFOR, true),
    S8KOM("S-8 KOM", ROCKETS, FactionSideType.REDFOR, true),
    S8OFP2("S-8O FP2", ROCKETS, FactionSideType.REDFOR, true),
    S130("S-130 F", ROCKETS, FactionSideType.REDFOR, true),
    S24B("S-24 B", ROCKETS, FactionSideType.REDFOR, true),
    S25OFM("S-25 OMF", ROCKETS, FactionSideType.REDFOR, true),
    S8TSM("S-8 TSM Marker", ROCKETS, FactionSideType.REDFOR, true),
    S8OM("S-8 OM Illumination", ROCKETS, FactionSideType.REDFOR, true),

    // Air to Surface Missile
    KH25ML("Kh-25ML", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    KH29T("Kh-29T", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    KH29L("Kh-29L", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    S25L("S-25L", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    A4172("9A4172 Vikhr", AIR_TO_SURFACE, FactionSideType.REDFOR, true),

    // Anti-Radiation
    KH58U("Kh-58U", ANTI_RADIATION, FactionSideType.REDFOR, true),
    KH25MPU("Kh-25MPU", ANTI_RADIATION, FactionSideType.REDFOR, true),

    // Sensor Pods
    L081("L-081 ELINT", SENSOR_PODS, FactionSideType.REDFOR, true),
    MERCURY_LLTV("Mercury LLTV", SENSOR_PODS, FactionSideType.REDFOR, true),

    // =============================================
    //                      BOTH
    // =============================================
    REMOVE_PYLON("Remove Pylon", NONE, FactionSideType.BLUEFOR, false),
    FUEL_TANK("Fuel Tank", MunitionSubType.FUEL_TANK, FactionSideType.BLUEFOR, true);

    private MunitionSubType subType;
    private FactionSideType side;
    private String munitionName;
    private boolean stockInStockpiles;

    MunitionType(String munitionName, MunitionSubType subType, FactionSideType side, boolean stockInStockpiles) {
        this.munitionName = munitionName;
        this.subType = subType;
        this.side = side;
        this.stockInStockpiles = stockInStockpiles;
    }

    public static MunitionType[] getMunitionsForSide(FactionSideType side) {
        return Arrays.stream(MunitionType.values()).filter((mt) -> mt.getSide().equals(side) || mt.getSide().equals(FactionSideType.NEUTRAL)).toArray(MunitionType[]::new);
    }

    public String getMunitionName() {
        return munitionName;
    }

    public MunitionSubType getSubType() {
        return subType;
    }

    public FactionSideType getSide() {
        return side;
    }

    public static MunitionType fromName(String item) {
        for(MunitionType faction : MunitionType.values()) {
            if(faction.getMunitionName().equalsIgnoreCase(item)) {
                return faction;
            }
        }
        return REMOVE_PYLON;
    }

    public boolean isStockInStockpiles() {
        return stockInStockpiles;
    }
}
