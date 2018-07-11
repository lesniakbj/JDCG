package sim.domain;

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
public abstract class SimUnit {
    private boolean isClientUnit;

    private double speedMilesPerHour;
    private double direction;
    private double mapXLocation;
    private double mapYLocation;

    abstract void updateStep();

    public double convertSpeedToPixelDistance(int deltaTimeMinutes, double conversionFactor) {
        return (speedMilesPerHour / deltaTimeMinutes) * conversionFactor;
    }

    public void isClientUnit(boolean isClientUnit) {
        this.isClientUnit = isClientUnit;
    }
    public boolean isClientUnit() {
        return isClientUnit;
    }

    public double getSpeedMilesPerHour() {
        return speedMilesPerHour;
    }
    public void setSpeedMilesPerHour(double speedMilesPerHour) {
        this.speedMilesPerHour = speedMilesPerHour;
    }

    public double getDirection() {
        return direction;
    }
    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getMapXLocation() {
        return mapXLocation;
    }
    public void setMapXLocation(double mapXLocation) {
        this.mapXLocation = mapXLocation;
    }

    public double getMapYLocation() {
        return mapYLocation;
    }
    public void setMapYLocation(double mapYLocation) {
        this.mapYLocation = mapYLocation;
    }
}
