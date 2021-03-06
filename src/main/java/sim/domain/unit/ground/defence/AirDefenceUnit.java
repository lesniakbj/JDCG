package sim.domain.unit.ground.defence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.AirDefenceUnitType;
import sim.domain.unit.SimUnit;

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
 * Created by Brendan.Lesniak on 7/19/2018.
 */
public abstract class AirDefenceUnit extends SimUnit {
    private AirDefenceUnitType unitType;

    public AirDefenceUnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(AirDefenceUnitType unitType) {
        this.unitType = unitType;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.JSON_STYLE, true, true);
    }
}
