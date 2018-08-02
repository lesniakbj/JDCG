package sim.domain.unit.ground.defence;

import sim.domain.enums.AirDefenceUnitType;

import java.util.Date;

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
public class MissileAirDefenceUnit extends AirDefenceUnit {

    public MissileAirDefenceUnit(AirDefenceUnitType type) {
        setUnitType(type);
    }

    @Override
    public void updateStep() {

    }

    @Override
    public void setCurrentCampaignDate(Date date) {

    }

    @Override
    public boolean shouldGenerateMission() {
        return false;
    }
}
