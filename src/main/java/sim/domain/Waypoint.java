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
public class Waypoint {
    private double locationX;
    private double locationY;
    private int speedMilesPerHour;
    private int height;

    public Waypoint(double locationX, double locationY, int speedMilesPerHour, int height) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedMilesPerHour = speedMilesPerHour;
        this.height = height;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public void setSpeedMilesPerHour(int speedMilesPerHour) {
        this.speedMilesPerHour = speedMilesPerHour;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public int getSpeedMilesPerHour() {
        return speedMilesPerHour;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Waypoint waypoint = (Waypoint) o;

        if (Double.compare(waypoint.locationX, locationX) != 0) return false;
        if (Double.compare(waypoint.locationY, locationY) != 0) return false;
        if (speedMilesPerHour != waypoint.speedMilesPerHour) return false;
        return height == waypoint.height;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(locationX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(locationY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + speedMilesPerHour;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "locationX=" + locationX +
                ", locationY=" + locationY +
                ", speedMilesPerHour=" + speedMilesPerHour +
                ", height=" + height +
                '}';
    }
}
