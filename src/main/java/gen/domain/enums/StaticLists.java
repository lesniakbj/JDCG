package gen.domain.enums;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Collections;
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
    public static final List<ConflictEraType> COMPLETE_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    public static final List<ConflictEraType> EXTENDED_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    public static final List<ConflictEraType> MODERN_ERAS = Arrays.asList(ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    public static final List<ConflictEraType> CLASSIC_ERAS = Arrays.asList(ConflictEraType.WWII, ConflictEraType.KOREA);
    public static final List<ConflictEraType> COLD_WAR_ERAS = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM);
    public static final List<ConflictEraType> LATE_COLD_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);
    public static final List<ConflictEraType> LATE_COLD_EXTENDED_WAR_ERAS = Arrays.asList(ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR, ConflictEraType.MODERN);
    public static final List<ConflictEraType> EARLY_COLD_WAR_ERAS = Collections.singletonList(ConflictEraType.KOREA);
    public static final List<ConflictEraType> EXTENDED_COLD_WAR = Arrays.asList(ConflictEraType.KOREA, ConflictEraType.VIETNAM, ConflictEraType.GULF_WAR);

    // Commonly used task lists
    public static final List<TaskType> ALL_TASK_TYPES = Arrays.asList(TaskType.values());
    public static final List<TaskType> AIR_SUPERIORITY = Arrays.asList(TaskType.CAP, TaskType.INTERCEPT, TaskType.ESCORT);
    public static final List<TaskType> STRIKE_FIGHTER = Arrays.asList(TaskType.CAP, TaskType.INTERCEPT, TaskType.GROUND_STRIKE, TaskType.CAS);
    public static final List<TaskType> FLEET_DEFENSE = Arrays.asList(TaskType.CAP, TaskType.INTERCEPT, TaskType.ESCORT);
    public static final List<TaskType> GROUND_STRIKE = Arrays.asList(TaskType.CAS, TaskType.GROUND_STRIKE);
    public static final List<TaskType> HIGH_RISK_GROUND_STRIKE = Arrays.asList(TaskType.CAS, TaskType.GROUND_STRIKE, TaskType.SEAD, TaskType.DEAD);
    public static final List<TaskType> SEAD_STRIKE = Arrays.asList(TaskType.CAS, TaskType.GROUND_STRIKE, TaskType.SEAD, TaskType.DEAD);

    // Commonly used Aircraft Lists
    public static final List<AircraftType> USER_AVAILABLE_AIRCRAFT = Arrays.asList(AircraftType.A_10_C, AircraftType.FA_18C, AircraftType.F_15C);
    public static final List<AircraftType> AIR_FORCE_AIR_SUPERIORITY_AVAILABLE_AIRCRAFT = Collections.singletonList(AircraftType.F_15C);
    public static final List<AircraftType> NAVY_COMPLETE_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_4, AircraftType.F_14A, AircraftType.F_14D, AircraftType.FA_18C, AircraftType.FA_18E);
    public static final List<AircraftType> NAVY_MODERN_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_14A, AircraftType.F_14D, AircraftType.FA_18C, AircraftType.FA_18E);
    public static final List<AircraftType> GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.P_51, AircraftType.F_86, AircraftType.F_4, AircraftType.A_10_C);
    public static final List<AircraftType> MARINE_GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.A_4, AircraftType.AV_8_NA);
    public static final List<AircraftType> MARINE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.F_4, AircraftType.FA_18C);
    public static final List<AircraftType> MARINE_STRIKE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.F_4, AircraftType.AV_8_NA, AircraftType.FA_18C);

    // Aircraft Loadouts
    public static final Map<Integer, List<MunitionType>> FA_18_MUNITIONS = ImmutableMap.<Integer, List<MunitionType>>builder()
                                                                                            .put(1, Arrays.asList(MunitionType.AIM9M, MunitionType.AIM9X))
                                                                                            .put(2, Arrays.asList(MunitionType.AIM9M, MunitionType.AIM9MX2, MunitionType.AIM9X, MunitionType.AIM9XX2, MunitionType.AIM7M))
                                                                                            .build();
}
