package sim.domain.enums;

import static sim.domain.enums.MunitionSubType.AIR_TO_AIR;
import static sim.domain.enums.MunitionSubType.BOMBS;
import static sim.domain.enums.MunitionSubType.NONE;
import static sim.domain.enums.MunitionSubType.ROCKETS;

public enum MunitionType {
    // AIM-7 Family
    AIM7M("AIM-7M", AIR_TO_AIR),
    AIM7F("AIM-7F", AIR_TO_AIR),

    // AIM-9 Family
    CAP9M("CAP-9M", AIR_TO_AIR),
    AIM9L("AIM-9L", AIR_TO_AIR),
    AIM9M("AIM-9M", AIR_TO_AIR),
    AIM9X("AIM-9X", AIR_TO_AIR),
    CAP9MX2("CAP-9M x 2", AIR_TO_AIR),
    AIM9LX2("AIM-9L x 2", AIR_TO_AIR),
    AIM9MX2("AIM-9M x 2", AIR_TO_AIR),
    AIM9XX2("AIM-9X x 2", AIR_TO_AIR),

    // AIM-120 Family
    AIM120B("AIM-120B", AIR_TO_AIR),
    AIM120C("AIM-120X", AIR_TO_AIR),

    // Mk8X Family
    MK_82("Mk-82", BOMBS),
    MK_82X2("Mk-82 x 2", BOMBS),
    MK_82X3("Mk-82 x 3", BOMBS),
    MK_82SE("Mk-82 SnakeEye", BOMBS),
    MK_82SEX2("Mk-82 SnakeEye x 2", BOMBS),
    MK_83("Mk-83", BOMBS),
    MK_83X2("Mk-83 x 2", BOMBS),
    MK_83X3("Mk-83 x 3", BOMBS),
    MK_84("Mk-84", BOMBS),
    MK_84X2("Mk-84 x 2", BOMBS),
    MK_84X3("Mk-84 x 3", BOMBS),

    // Cluster Munitions
    MK_20("Mk-20 RockEye", BOMBS),
    MK_20X2("Mk-20 RockEye x 2", BOMBS),
    CBU_99("CBU-99", BOMBS),
    CBU_99X2("CBU-99 x 2", BOMBS),

    // Rockets
    ZUNI_MK_71("ZUNI Mk-71", ROCKETS),
    ZUNI_MK_71X2("ZUNI Mk-71 x 2", ROCKETS),
    MK_151_HE_19("19 Mk-151 HE", ROCKETS),
    MK_151_HE_19X2("19 Mk-151 HE x 2", ROCKETS),
    MK_151_HE_7("7 Mk-151 HE", ROCKETS),
    MK_151_HE_7X2("7 Mk-151 HE x 2", ROCKETS),
    MK_5_HE_7("7 Mk-5 HE", ROCKETS),
    MK_5_HE_7X2("7 Mk-5 HE x 2", ROCKETS),

    // Removed Pylons
    REMOVE_PYLON("Remove Pylon", NONE),

    // Fuel Tank
    FUEL_TANK("Fuel Tank", MunitionSubType.FUEL_TANK);

    private final MunitionSubType subType;
    private String munitionName;

    MunitionType(String munitionName, MunitionSubType subType) {
        this.munitionName = munitionName;
        this.subType = subType;
    }

    public String getMunitionName() {
        return munitionName;
    }

    public static MunitionType fromName(String item) {
        for(MunitionType faction : MunitionType.values()) {
            if(faction.getMunitionName().equalsIgnoreCase(item)) {
                return faction;
            }
        }
        return REMOVE_PYLON;
    }
}
