package sim.domain.enums;

import static sim.domain.enums.MunitionSubType.AIR_TO_AIR;
import static sim.domain.enums.MunitionSubType.AIR_TO_SURFACE;
import static sim.domain.enums.MunitionSubType.ANTI_RADIATION;
import static sim.domain.enums.MunitionSubType.BOMBS;
import static sim.domain.enums.MunitionSubType.GUN_POD;
import static sim.domain.enums.MunitionSubType.NONE;
import static sim.domain.enums.MunitionSubType.ROCKETS;

import java.util.Arrays;

public enum MunitionType {
    // =============================================
    //              BLUEFOR WEAPONRY
    // =============================================

    // AIM-7 Family
    AIM7M("AIM-7M", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM7F("AIM-7F", AIR_TO_AIR, FactionSideType.BLUEFOR, true),

    // AIM-9 Family
    CAP9M("CAP-9M", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM9L("AIM-9L", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM9M("AIM-9M", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM9X("AIM-9X", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    CAP9MX2("CAP-9M x 2", AIR_TO_AIR, FactionSideType.BLUEFOR, false),
    AIM9LX2("AIM-9L x 2", AIR_TO_AIR, FactionSideType.BLUEFOR, false),
    AIM9MX2("AIM-9M x 2", AIR_TO_AIR, FactionSideType.BLUEFOR, false),
    AIM9XX2("AIM-9X x 2", AIR_TO_AIR, FactionSideType.BLUEFOR, false),

    // AIM-120 Family
    AIM120B("AIM-120B", AIR_TO_AIR, FactionSideType.BLUEFOR, true),
    AIM120C("AIM-120X", AIR_TO_AIR, FactionSideType.BLUEFOR, true),

    // Mk8X Family
    MK_82("Mk-82", BOMBS, FactionSideType.BLUEFOR, true),
    MK_82X2("Mk-82 x 2", BOMBS, FactionSideType.BLUEFOR, false),
    MK_82X3("Mk-82 x 3", BOMBS, FactionSideType.BLUEFOR, false),
    MK_82SE("Mk-82 SnakeEye", BOMBS, FactionSideType.BLUEFOR, true),
    MK_82SEX2("Mk-82 SnakeEye x 2", BOMBS, FactionSideType.BLUEFOR, false),
    MK_83("Mk-83", BOMBS, FactionSideType.BLUEFOR, true),
    MK_83X2("Mk-83 x 2", BOMBS, FactionSideType.BLUEFOR, false),
    MK_83X3("Mk-83 x 3", BOMBS, FactionSideType.BLUEFOR, false),
    MK_84("Mk-84", BOMBS, FactionSideType.BLUEFOR, true),
    MK_84X2("Mk-84 x 2", BOMBS, FactionSideType.BLUEFOR, false),
    MK_84X3("Mk-84 x 3", BOMBS, FactionSideType.BLUEFOR, false),

    // Cluster Munitions
    MK_20("Mk-20 RockEye", BOMBS, FactionSideType.BLUEFOR, true),
    MK_20X2("Mk-20 RockEye x 2", BOMBS, FactionSideType.BLUEFOR, false),
    CBU_99("CBU-99", BOMBS, FactionSideType.BLUEFOR, true),
    CBU_99X2("CBU-99 x 2", BOMBS, FactionSideType.BLUEFOR, false),

    // Rockets
    ZUNI_MK_71("ZUNI Mk-71", ROCKETS, FactionSideType.BLUEFOR, true),
    ZUNI_MK_71X2("ZUNI Mk-71 x 2", ROCKETS, FactionSideType.BLUEFOR, false),
    MK_151_HE_19("19 Mk-151 HE", ROCKETS, FactionSideType.BLUEFOR, true),
    MK_151_HE_19X2("19 Mk-151 HE x 2", ROCKETS, FactionSideType.BLUEFOR, false),
    MK_151_HE_7("7 Mk-151 HE", ROCKETS, FactionSideType.BLUEFOR, true),
    MK_151_HE_7X2("7 Mk-151 HE x 2", ROCKETS, FactionSideType.BLUEFOR, false),
    MK_5_HE_7("7 Mk-5 HE", ROCKETS, FactionSideType.BLUEFOR, true),
    MK_5_HE_7X2("7 Mk-5 HE x 2", ROCKETS, FactionSideType.BLUEFOR, false),

    // =============================================
    //              REDFOR WEAPONRY
    // =============================================

    // R27 Family
    R27R("R-27R", AIR_TO_AIR, FactionSideType.REDFOR, true),
    R27ER("R-27ER", AIR_TO_AIR, FactionSideType.REDFOR, true),
    R27T("R-27T", AIR_TO_AIR, FactionSideType.REDFOR, true),
    R27ET("R-27ET", AIR_TO_AIR, FactionSideType.REDFOR, true),

    // R73 Family
    R73("R-73", AIR_TO_AIR, FactionSideType.REDFOR, true),

    // Bombs
    FAB100("FAB-100", BOMBS, FactionSideType.REDFOR, true),
    FAB100X4("FAB-100 x 4", BOMBS, FactionSideType.REDFOR, false),
    FAB250("FAB-250", BOMBS, FactionSideType.REDFOR, true),
    FAB500("FAB-500", BOMBS, FactionSideType.REDFOR, true),
    FAB1500("FAB-1500", BOMBS, FactionSideType.REDFOR, true),

    // Guided Bombs
    KAB500KR("KAB-500kr", BOMBS, FactionSideType.REDFOR, true),

    // Anti-Runway / Concrete
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

    // Rockets
    S8("S-8", ROCKETS, FactionSideType.REDFOR, true),
    S13("S-13", ROCKETS, FactionSideType.REDFOR, true),
    S24B("S-24B", ROCKETS, FactionSideType.REDFOR, true),
    S25OFM("S-25 OMF", ROCKETS, FactionSideType.REDFOR, true),

    // Air to Surface Missile
    KH25ML("Kh-25ML", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    KH29T("Kh-29T", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    KH29L("Kh-29L", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    S25L("S-25L", AIR_TO_SURFACE, FactionSideType.REDFOR, true),
    A4172("9A4172 Vikhr", AIR_TO_SURFACE, FactionSideType.REDFOR, true),

    // Anti-Radiation
    KH58U("Kh-58U", ANTI_RADIATION, FactionSideType.REDFOR, true),
    KH25MPU("Kh-25MPU", ANTI_RADIATION, FactionSideType.REDFOR, true),

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
