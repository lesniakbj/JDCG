package sim.domain.statics.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import sim.domain.statics.AircraftType;
import sim.domain.statics.ConflictEra;
import sim.domain.statics.Task;

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
    public static final List<ConflictEra> COMPLETE_ERAS = Arrays.asList(ConflictEra.WWII, ConflictEra.KOREA, ConflictEra.VIETNAM, ConflictEra.GULF_WAR, ConflictEra.MODERN);
    public static final List<ConflictEra> EXTENDED_ERAS = Arrays.asList(ConflictEra.KOREA, ConflictEra.VIETNAM, ConflictEra.GULF_WAR, ConflictEra.MODERN);
    public static final List<ConflictEra> MODERN_ERAS = Arrays.asList(ConflictEra.GULF_WAR, ConflictEra.MODERN);
    public static final List<ConflictEra> CLASSIC_ERAS = Arrays.asList(ConflictEra.WWII, ConflictEra.KOREA);
    public static final List<ConflictEra> COLD_WAR_ERAS = Arrays.asList(ConflictEra.KOREA, ConflictEra.VIETNAM);
    public static final List<ConflictEra> LATE_COLD_WAR_ERAS = Arrays.asList(ConflictEra.VIETNAM, ConflictEra.GULF_WAR);
    public static final List<ConflictEra> LATE_COLD_EXTENDED_WAR_ERAS = Arrays.asList(ConflictEra.VIETNAM, ConflictEra.GULF_WAR, ConflictEra.MODERN);
    public static final List<ConflictEra> EARLY_COLD_WAR_ERAS = Collections.singletonList(ConflictEra.KOREA);
    public static final List<ConflictEra> EXTENDED_COLD_WAR = Arrays.asList(ConflictEra.KOREA, ConflictEra.VIETNAM, ConflictEra.GULF_WAR);

    // Commonly used task lists
    public static final List<Task> ALL_TASKS = Arrays.asList(Task.values());
    public static final List<Task> STRIKE_FIGHTER = Arrays.asList(Task.CAP, Task.INTERCEPT, Task.GROUND_STRIKE, Task.CAS);
    public static final List<Task> FLEET_DEFENSE = Arrays.asList(Task.CAP, Task.INTERCEPT, Task.ESCORT);
    public static final List<Task> GROUND_STRIKE = Arrays.asList(Task.CAS, Task.GROUND_STRIKE);
    public static final List<Task> HIGH_RISK_GROUND_STRIKE = Arrays.asList(Task.CAS, Task.GROUND_STRIKE, Task.SEAD, Task.DEAD);
    public static final List<Task> SEAD_STRIKE = Arrays.asList(Task.CAS, Task.GROUND_STRIKE, Task.SEAD, Task.DEAD);

    // Commonly used Aircraft Lists
    public static final List<AircraftType> USER_AVAILABLE_AIRCRAFT = Arrays.asList(AircraftType.A_10_C, AircraftType.FA_18C, AircraftType.F_15C);
    public static final List<AircraftType> NAVY_COMPLETE_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_4, AircraftType.F_14A, AircraftType.F_14D, AircraftType.FA_18C, AircraftType.FA_18E);
    public static final List<AircraftType> NAVY_MODERN_ERA_AIRCRAFT = Arrays.asList(AircraftType.F_14A, AircraftType.F_14D, AircraftType.FA_18C, AircraftType.FA_18E);
    public static final List<AircraftType> GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.P_51, AircraftType.F_86, AircraftType.F_4, AircraftType.A_10_C);
    public static final List<AircraftType> MARINE_GROUND_STRIKE_COMPLETE_ERAS = Arrays.asList(AircraftType.A_4, AircraftType.AV_8_NA);
    public static final List<AircraftType> MARINE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.F_4, AircraftType.FA_18C);
    public static final List<AircraftType> MARINE_STRIKE_FIGHTER_COMPLETE_ERAS = Arrays.asList(AircraftType.F_4, AircraftType.AV_8_NA, AircraftType.FA_18C);
}
