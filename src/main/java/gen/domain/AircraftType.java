package gen.domain;

import static gen.domain.StaticLists.CLASSIC_ERAS;
import static gen.domain.StaticLists.COLD_WAR_ERAS;
import static gen.domain.StaticLists.EARLY_COLD_WAR_ERAS;
import static gen.domain.StaticLists.EXTENDED_COLD_WAR;
import static gen.domain.StaticLists.EXTENDED_ERAS;
import static gen.domain.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static gen.domain.StaticLists.LATE_COLD_WAR_ERAS;
import static gen.domain.StaticLists.MODERN_ERAS;

import java.util.List;

public enum AircraftType {
    A_4("A-4", EXTENDED_ERAS),
    A_7("A-7", LATE_COLD_WAR_ERAS),
    A_10_A("A-10A", MODERN_ERAS),
    A_10_C("A-10C", MODERN_ERAS),
    AV_8_NA("AV-8NA", MODERN_ERAS),
    B_52("B-52", EXTENDED_ERAS),
    F_4("F-4", EXTENDED_COLD_WAR),
    F_14A("F-14A", LATE_COLD_WAR_ERAS),
    F_14D("F-14D", LATE_COLD_WAR_ERAS),
    F_15C("F-15C", MODERN_ERAS),
    F_86("F-86", EARLY_COLD_WAR_ERAS),
    FA_18A("F/A-18A", MODERN_ERAS),
    FA_18C("F/A-18C", MODERN_ERAS),
    FA_18E("F/A-18E", MODERN_ERAS),
    MIG_15("MiG-15", EARLY_COLD_WAR_ERAS),
    MIG_17("MiG-17", COLD_WAR_ERAS),
    MIG_19("MiG-19", COLD_WAR_ERAS),
    MIG_21("MiG-21", LATE_COLD_EXTENDED_WAR_ERAS),
    MIG_29("MiG-29", MODERN_ERAS),
    MIG_31("MiG-31", MODERN_ERAS),
    P_51("P-51", CLASSIC_ERAS),
    SU_24("Su-24", MODERN_ERAS),
    SU_25("Su-25", MODERN_ERAS),
    SU_27("Su-27", MODERN_ERAS),
    SU_30("Su-30", MODERN_ERAS),
    SU_33("Su-33", MODERN_ERAS),
    SU_34("Su-34", MODERN_ERAS),
    TU_22M("Tu-22M", MODERN_ERAS);

    private String aircraftName;
    private List<ConflictEra> aircraftEras;

    AircraftType(String aircraftName, List<ConflictEra> aircraftEras) {
        this.aircraftName = aircraftName;
        this.aircraftEras = aircraftEras;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public List<ConflictEra> getAircraftEras() {
        return aircraftEras;
    }
}
