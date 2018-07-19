package sim.domain.enums;

import static sim.domain.enums.MunitionSubType.AIR_TO_AIR;
import static sim.domain.enums.MunitionSubType.BOMBS;
import static sim.domain.enums.MunitionSubType.NONE;
import static sim.domain.enums.MunitionSubType.ROCKETS;

import java.util.Arrays;

public enum MunitionType {
    // AIM-7 Family
    AIM7M("AIM-7M", AIR_TO_AIR, FactionSide.BLUEFOR, true),
    AIM7F("AIM-7F", AIR_TO_AIR, FactionSide.BLUEFOR, true),

    // AIM-9 Family
    CAP9M("CAP-9M", AIR_TO_AIR, FactionSide.BLUEFOR, true),
    AIM9L("AIM-9L", AIR_TO_AIR, FactionSide.BLUEFOR, true),
    AIM9M("AIM-9M", AIR_TO_AIR, FactionSide.BLUEFOR, true),
    AIM9X("AIM-9X", AIR_TO_AIR, FactionSide.BLUEFOR, true),
    CAP9MX2("CAP-9M x 2", AIR_TO_AIR, FactionSide.BLUEFOR, false),
    AIM9LX2("AIM-9L x 2", AIR_TO_AIR, FactionSide.BLUEFOR, false),
    AIM9MX2("AIM-9M x 2", AIR_TO_AIR, FactionSide.BLUEFOR, false),
    AIM9XX2("AIM-9X x 2", AIR_TO_AIR, FactionSide.BLUEFOR, false),

    // AIM-120 Family
    AIM120B("AIM-120B", AIR_TO_AIR, FactionSide.BLUEFOR, true),
    AIM120C("AIM-120X", AIR_TO_AIR, FactionSide.BLUEFOR, true),

    // Mk8X Family
    MK_82("Mk-82", BOMBS, FactionSide.BLUEFOR, true),
    MK_82X2("Mk-82 x 2", BOMBS, FactionSide.BLUEFOR, false),
    MK_82X3("Mk-82 x 3", BOMBS, FactionSide.BLUEFOR, false),
    MK_82SE("Mk-82 SnakeEye", BOMBS, FactionSide.BLUEFOR, true),
    MK_82SEX2("Mk-82 SnakeEye x 2", BOMBS, FactionSide.BLUEFOR, false),
    MK_83("Mk-83", BOMBS, FactionSide.BLUEFOR, true),
    MK_83X2("Mk-83 x 2", BOMBS, FactionSide.BLUEFOR, false),
    MK_83X3("Mk-83 x 3", BOMBS, FactionSide.BLUEFOR, false),
    MK_84("Mk-84", BOMBS, FactionSide.BLUEFOR, true),
    MK_84X2("Mk-84 x 2", BOMBS, FactionSide.BLUEFOR, false),
    MK_84X3("Mk-84 x 3", BOMBS, FactionSide.BLUEFOR, false),

    // Cluster Munitions
    MK_20("Mk-20 RockEye", BOMBS, FactionSide.BLUEFOR, true),
    MK_20X2("Mk-20 RockEye x 2", BOMBS, FactionSide.BLUEFOR, false),
    CBU_99("CBU-99", BOMBS, FactionSide.BLUEFOR, true),
    CBU_99X2("CBU-99 x 2", BOMBS, FactionSide.BLUEFOR, false),

    // Rockets
    ZUNI_MK_71("ZUNI Mk-71", ROCKETS, FactionSide.BLUEFOR, true),
    ZUNI_MK_71X2("ZUNI Mk-71 x 2", ROCKETS, FactionSide.BLUEFOR, false),
    MK_151_HE_19("19 Mk-151 HE", ROCKETS, FactionSide.BLUEFOR, true),
    MK_151_HE_19X2("19 Mk-151 HE x 2", ROCKETS, FactionSide.BLUEFOR, false),
    MK_151_HE_7("7 Mk-151 HE", ROCKETS, FactionSide.BLUEFOR, true),
    MK_151_HE_7X2("7 Mk-151 HE x 2", ROCKETS, FactionSide.BLUEFOR, false),
    MK_5_HE_7("7 Mk-5 HE", ROCKETS, FactionSide.BLUEFOR, true),
    MK_5_HE_7X2("7 Mk-5 HE x 2", ROCKETS, FactionSide.BLUEFOR, false),

    // Removed Pylons
    REMOVE_PYLON("Remove Pylon", NONE, FactionSide.BLUEFOR, false),

    // Fuel Tank
    FUEL_TANK("Fuel Tank", MunitionSubType.FUEL_TANK, FactionSide.BLUEFOR, true);

    private MunitionSubType subType;
    private FactionSide side;
    private String munitionName;
    private boolean stockInStockpiles;

    MunitionType(String munitionName, MunitionSubType subType, FactionSide side, boolean stockInStockpiles) {
        this.munitionName = munitionName;
        this.subType = subType;
        this.side = side;
        this.stockInStockpiles = stockInStockpiles;
    }

    public static MunitionType[] getMunitionsForSide(FactionSide side) {
        return Arrays.stream(MunitionType.values()).filter((mt) -> mt.getSide().equals(side) || mt.getSide().equals(FactionSide.NEUTRAL)).toArray(MunitionType[]::new);
    }

    public String getMunitionName() {
        return munitionName;
    }

    public MunitionSubType getSubType() {
        return subType;
    }

    public FactionSide getSide() {
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
