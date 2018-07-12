package gen.domain.enums;

import java.util.List;
import java.util.Map;

import static gen.domain.enums.StaticLists.CLASSIC_ERAS;
import static gen.domain.enums.StaticLists.COLD_WAR_ERAS;
import static gen.domain.enums.StaticLists.EARLY_COLD_WAR_ERAS;
import static gen.domain.enums.StaticLists.EXTENDED_COLD_WAR;
import static gen.domain.enums.StaticLists.EXTENDED_ERAS;
import static gen.domain.enums.StaticLists.FA_18_MUNITIONS;
import static gen.domain.enums.StaticLists.LATE_COLD_EXTENDED_WAR_ERAS;
import static gen.domain.enums.StaticLists.LATE_COLD_WAR_ERAS;
import static gen.domain.enums.StaticLists.MODERN_ERAS;

public enum AircraftType {
    A_4("A-4", EXTENDED_ERAS, null),
    A_7("A-7", LATE_COLD_WAR_ERAS, null),
    A_10_A("A-10A", MODERN_ERAS, null),
    A_10_C("A-10C", MODERN_ERAS, null),
    AV_8_NA("AV-8NA", MODERN_ERAS, null),
    B_52("B-52", EXTENDED_ERAS, null),
    F_4("F-4", EXTENDED_COLD_WAR, null),
    F_14A("F-14A", LATE_COLD_WAR_ERAS, null),
    F_14D("F-14D", LATE_COLD_WAR_ERAS, null),
    F_15C("F-15C", MODERN_ERAS, null),
    F_86("F-86", EARLY_COLD_WAR_ERAS, null),
    FA_18A("F/A-18A", MODERN_ERAS, null),
    FA_18C("F/A-18C", MODERN_ERAS, FA_18_MUNITIONS),
    FA_18E("F/A-18E", MODERN_ERAS, null),
    MIG_15("MiG-15", EARLY_COLD_WAR_ERAS, null),
    MIG_17("MiG-17", COLD_WAR_ERAS, null),
    MIG_19("MiG-19", COLD_WAR_ERAS, null),
    MIG_21("MiG-21", LATE_COLD_EXTENDED_WAR_ERAS, null),
    MIG_29("MiG-29", MODERN_ERAS, null),
    MIG_31("MiG-31", MODERN_ERAS, null),
    P_51("P-51", CLASSIC_ERAS, null),
    SU_24("Su-24", MODERN_ERAS, null),
    SU_25("Su-25", MODERN_ERAS, null),
    SU_27("Su-27", MODERN_ERAS, null),
    SU_30("Su-30", MODERN_ERAS, null),
    SU_33("Su-33", MODERN_ERAS, null),
    SU_34("Su-34", MODERN_ERAS, null),
    TU_22M("Tu-22M", MODERN_ERAS, null);

    private String aircraftName;
    private List<ConflictEraType> aircraftEras;
    private Map<Integer, List<MunitionType>> stationMunitions;

    AircraftType(String aircraftName, List<ConflictEraType> aircraftEras, Map<Integer, List<MunitionType>> stationMunitions) {
        this.aircraftName = aircraftName;
        this.aircraftEras = aircraftEras;
        this.stationMunitions = stationMunitions;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public List<ConflictEraType> getAircraftEras() {
        return aircraftEras;
    }

    public Map<Integer, List<MunitionType>> getStationMunitions() {
        return stationMunitions;
    }
}
