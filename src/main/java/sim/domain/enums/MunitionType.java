package sim.domain.enums;

import static sim.domain.enums.MunitionSubType.AIR_TO_AIR;
import static sim.domain.enums.MunitionSubType.BOMBS;
import static sim.domain.enums.MunitionSubType.NONE;
import static sim.domain.enums.MunitionSubType.ROCKETS;

import java.util.Arrays;
import java.util.stream.Stream;
import sim.domain.Munition;

public enum MunitionType {
    // AIM-7 Family
    AIM7M("AIM-7M", AIR_TO_AIR, null),
    AIM7F("AIM-7F", AIR_TO_AIR, null),

    // AIM-9 Family
    CAP9M("CAP-9M", AIR_TO_AIR, null),
    AIM9L("AIM-9L", AIR_TO_AIR, null),
    AIM9M("AIM-9M", AIR_TO_AIR, null),
    AIM9X("AIM-9X", AIR_TO_AIR, null),
    CAP9MX2("CAP-9M x 2", AIR_TO_AIR, null),
    AIM9LX2("AIM-9L x 2", AIR_TO_AIR, null),
    AIM9MX2("AIM-9M x 2", AIR_TO_AIR, null),
    AIM9XX2("AIM-9X x 2", AIR_TO_AIR, null),

    // AIM-120 Family
    AIM120B("AIM-120B", AIR_TO_AIR, null),
    AIM120C("AIM-120X", AIR_TO_AIR, null),

    // Mk8X Family
    MK_82("Mk-82", BOMBS, null),
    MK_82X2("Mk-82 x 2", BOMBS, null),
    MK_82X3("Mk-82 x 3", BOMBS, null),
    MK_82SE("Mk-82 SnakeEye", BOMBS, null),
    MK_82SEX2("Mk-82 SnakeEye x 2", BOMBS, null),
    MK_83("Mk-83", BOMBS, null),
    MK_83X2("Mk-83 x 2", BOMBS, null),
    MK_83X3("Mk-83 x 3", BOMBS, null),
    MK_84("Mk-84", BOMBS, null),
    MK_84X2("Mk-84 x 2", BOMBS, null),
    MK_84X3("Mk-84 x 3", BOMBS, null),

    // Cluster Munitions
    MK_20("Mk-20 RockEye", BOMBS, null),
    MK_20X2("Mk-20 RockEye x 2", BOMBS, null),
    CBU_99("CBU-99", BOMBS, null),
    CBU_99X2("CBU-99 x 2", BOMBS, null),

    // Rockets
    ZUNI_MK_71("ZUNI Mk-71", ROCKETS, null),
    ZUNI_MK_71X2("ZUNI Mk-71 x 2", ROCKETS, null),
    MK_151_HE_19("19 Mk-151 HE", ROCKETS, null),
    MK_151_HE_19X2("19 Mk-151 HE x 2", ROCKETS, null),
    MK_151_HE_7("7 Mk-151 HE", ROCKETS, null),
    MK_151_HE_7X2("7 Mk-151 HE x 2", ROCKETS, null),
    MK_5_HE_7("7 Mk-5 HE", ROCKETS, null),
    MK_5_HE_7X2("7 Mk-5 HE x 2", ROCKETS, null),

    // Removed Pylons
    REMOVE_PYLON("Remove Pylon", NONE, null),

    // Fuel Tank
    FUEL_TANK("Fuel Tank", MunitionSubType.FUEL_TANK, null);

    private MunitionSubType subType;
    private FactionSide side;
    private String munitionName;

    MunitionType(String munitionName, MunitionSubType subType, FactionSide side) {
        this.munitionName = munitionName;
        this.subType = subType;
        this.side = side;
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
}
