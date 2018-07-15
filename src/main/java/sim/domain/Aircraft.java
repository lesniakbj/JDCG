package sim.domain;

import sim.domain.enums.AircraftType;

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
 * Created by Brendan.Lesniak on 7/11/2018.
 */
public class Aircraft extends SimUnit {
    private AircraftType aircraftType;

    public Aircraft(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public void updateStep() {
        // Update here
    }

    @Override
    public void setCurrentCampaignDate(Date date) {

    }

    @Override
    public boolean shouldGenerateMission() {
        return false;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftType=" + aircraftType +
                '}';
    }
}
