package sim.domain.statics;

import static sim.domain.statics.util.StaticLists.CLASSIC_ERAS;
import static sim.domain.statics.util.StaticLists.COLD_WAR_ERAS;
import static sim.domain.statics.util.StaticLists.EARLY_COLD_WAR_ERAS;
import static sim.domain.statics.util.StaticLists.EXTENDED_COLD_WAR;
import static sim.domain.statics.util.StaticLists.EXTENDED_ERAS;
import static sim.domain.statics.util.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static sim.domain.statics.util.StaticLists.LATE_COLD_WAR_ERAS;
import static sim.domain.statics.util.StaticLists.MODERN_ERAS;

import java.util.List;

public enum AircraftType {
    A_4("A-4", EXTENDED_ERAS),
    A_7("A-7", LATE_COLD_WAR_ERAS),
    A_10_A("", MODERN_ERAS),
    A_10_C("", MODERN_ERAS),
    AV_8_NA("", MODERN_ERAS),
    B_52("", EXTENDED_ERAS),
    F_4("", EXTENDED_COLD_WAR),
    F_14A("", LATE_COLD_WAR_ERAS),
    F_14D("", LATE_COLD_WAR_ERAS),
    F_15C("", MODERN_ERAS),
    F_86("", EARLY_COLD_WAR_ERAS),
    FA_18A("", MODERN_ERAS),
    FA_18C("", MODERN_ERAS),
    FA_18E("", MODERN_ERAS),
    MIG_15("", EARLY_COLD_WAR_ERAS),
    MIG_17("", COLD_WAR_ERAS),
    MIG_19("", COLD_WAR_ERAS),
    MIG_21("", LATE_COLD_EXTENDED_WAR_ERAS),
    MIG_29("", MODERN_ERAS),
    MIG_31("", MODERN_ERAS),
    P_51("", CLASSIC_ERAS),
    SU_24("", MODERN_ERAS),
    SU_25("", MODERN_ERAS),
    SU_27("", MODERN_ERAS),
    SU_30("", MODERN_ERAS),
    SU_33("", MODERN_ERAS),
    SU_34("", MODERN_ERAS),
    TU_22M("", MODERN_ERAS);

    private String aircraftName;
    private List<ConflictEra> aircraftEras;

    AircraftType(String aircraftName, List<ConflictEra> aircraftEras) {
        this.aircraftName = aircraftName;
        this.aircraftEras = aircraftEras;
    }

    public String getAircraftName() {
        return aircraftName;
    }
}
