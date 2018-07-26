package sim.domain.unit.air;

import sim.domain.enums.MunitionType;

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
 * Created by Brendan.Lesniak on 7/26/2018.
 */
public class WeaponStation {
    private int stationNumber;
    private Munition munition;

    public WeaponStation(int stationNumber, Munition munition) {
        this.stationNumber = stationNumber;
        this.munition = munition;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public MunitionType getMunitionType() {
        return munition.getMunitionType();
    }

    public void setMunitionType(MunitionType munitionType) {
        this.munition.setMunitionType(munitionType);
    }

    public int getTotalLoaded() {
        return this.munition.getTotalLoaded();
    }

    public void setTotalLoaded(int totalLoaded) {
        this.munition.setTotalLoaded(totalLoaded);
    }
}
