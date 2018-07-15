package sim.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (c) Copyright 2018 Calabrio, Inc.
 * All Rights Reserved. www.calabrio.com LICENSED MATERIALS
 * Property of Calabrio, Inc., Minnesota, USA
 * <p>
 * No part of this publication may be reproduced, stored or transmitted,
 * in any form or by any means (electronic, mechanical, photocopying,
 * recording or otherwise) without prior written permission from Calabrio Software.
 * <p>
 *
 * Created by Brendan.Lesniak on 7/9/2018.
 */
public class StaticLists {
    // Commonly used era lists
    static final List<ConflictEraType> COMPLETE_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> EXTENDED_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> MODERN_ERAS = Arrays.asList(ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> CLASSIC_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA);
    static final List<ConflictEraType> COLD_WAR_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM);
    static final List<ConflictEraType> LATE_COLD_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);
    static final List<ConflictEraType> LATE_COLD_EXTENDED_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    static final List<ConflictEraType> EARLY_COLD_WAR_ERAS = Collections.singletonList(ConflictEraType.KOREA);
    static final List<ConflictEraType> EXTENDED_COLD_WAR = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);

    // Commonly used task lists
    static final List<TaskType> ALL_TASK_TYPES = Arrays.asList(TaskType.values());
    static final List<TaskType> AIR_SUPERIORITY = Arrays.asList(TaskType.CAP, TaskType.INTERCEPT, TaskType.ESCORT);
    static final List<TaskType> STRIKE_FIGHTER = Arrays.asList(TaskType.CAP, TaskType.INTERCEPT, TaskType.GROUND_STRIKE, TaskType.CAS);
    static final List<TaskType> FLEET_DEFENSE = Arrays.asList(TaskType.CAP, TaskType.INTERCEPT, TaskType.ESCORT);
    static final List<TaskType> GROUND_STRIKE = Arrays.asList(TaskType.CAS, TaskType.GROUND_STRIKE);
    static final List<TaskType> HIGH_RISK_GROUND_STRIKE = Arrays.asList(TaskType.CAS, TaskType.GROUND_STRIKE, TaskType.SEAD, TaskType.DEAD);
    public static final List<TaskType> SEAD_STRIKE = Arrays.asList(TaskType.CAS, TaskType.GROUND_STRIKE, TaskType.SEAD, TaskType.DEAD);

    // Aircraft Loadouts (has to be initialized before AircraftType)
    static final Map<Integer, List<MunitionType>> FA_18_MUNITIONS = new HashMap<Integer, List<MunitionType>>() {{
        put(1, Arrays.asList(MunitionType.AIM9L, MunitionType.AIM9M, MunitionType.AIM9X, MunitionType.CAP9M));
        put(2, Arrays.asList(MunitionType.AIM9LX2, MunitionType.AIM9MX2, MunitionType.AIM9XX2, MunitionType.CAP9MX2, MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.MK_5_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.MK_5_HE_7X2, MunitionType.REMOVE_PYLON));
        put(3, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.REMOVE_PYLON, MunitionType.FUEL_TANK));
        put(4, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M));
        put(5, Arrays.asList(MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.FUEL_TANK));
        put(6, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M));
        put(7, Arrays.asList(MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.REMOVE_PYLON, MunitionType.FUEL_TANK));
        put(8, Arrays.asList(MunitionType.AIM9LX2, MunitionType.AIM9MX2, MunitionType.AIM9XX2, MunitionType.CAP9MX2, MunitionType.AIM7F, MunitionType.AIM7M, MunitionType.MK_82, MunitionType.MK_82SE, MunitionType.MK_83, MunitionType.MK_84, MunitionType.MK_20, MunitionType.CBU_99, MunitionType.MK_82X2, MunitionType.MK_82SEX2, MunitionType.MK_82X2, MunitionType.MK_83X2, MunitionType.MK_20X2, MunitionType.CBU_99X2, MunitionType.ZUNI_MK_71, MunitionType.MK_151_HE_19, MunitionType.MK_151_HE_7, MunitionType.MK_5_HE_7, MunitionType.ZUNI_MK_71X2, MunitionType.MK_151_HE_19X2, MunitionType.MK_151_HE_7X2, MunitionType.MK_5_HE_7X2, MunitionType.REMOVE_PYLON));
        put(9, Arrays.asList(MunitionType.AIM9L, MunitionType.AIM9M, MunitionType.AIM9X, MunitionType.CAP9M));
    }};

    // Commonly used Aircraft Lists
    static final List<AircraftType> USER_AVAILABLE_AIRCRAFT = Arrays.asList(AircraftType.A_10_C, AircraftType.FA_18C_LOT20, AircraftType.F_15C);
    static final List<AircraftType> AIR_FORCE_AIR_SUPERIORITY_AVAILABLE_AIRCRAFT = Collections.singletonList(AircraftType.F_15C);
    static final List<AircraftType> NAVY_COMPLETE_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_4, AircraftType.F_14A, AircraftType.F_14D, AircraftType.FA_18C_LOT20, AircraftType.FA_18E);
    static final List<AircraftType> NAVY_MODERN_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_14A, AircraftType.F_14D, AircraftType.FA_18C_LOT20, AircraftType.FA_18E);
    static final List<AircraftType> GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.P_51, AircraftType.F_86, AircraftType.F_4, AircraftType.A_10_C);
    static final List<AircraftType> MARINE_GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.A_4, AircraftType.AV_8_NA);
    static final List<AircraftType> MARINE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.F_4, AircraftType.FA_18C_LOT20);
    static final List<AircraftType> MARINE_STRIKE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.F_4, AircraftType.AV_8_NA, AircraftType.FA_18C_LOT20);
}
