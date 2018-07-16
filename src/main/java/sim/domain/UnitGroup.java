package sim.domain;

import sim.domain.enums.FactionSide;
import sim.util.IDGenerator;

import java.util.Date;
import java.util.List;

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
public class UnitGroup<T extends SimUnit> extends SimUnit {
    private int id;
    private FactionSide side;
    private List<T> groupUnits;
    private boolean shouldGenerate;

    public UnitGroup(List<T> groupUnits, FactionSide side, double xLocation, double yLocation) {
        this.id = IDGenerator.generateNextId(UnitGroup.class);
        this.groupUnits = groupUnits;
        this.side = side;
        this.setMapXLocation(xLocation);
        this.setMapYLocation(yLocation);
        this.setSpeedMilesPerHour(0.0);
        this.setDirection(0.0);
        this.shouldGenerate = false;
    }

    public List<T> getGroupUnits() {
        return groupUnits;
    }

    public int getNumberOfUnits() {
        return groupUnits.size();
    }

    public FactionSide getSide() {
        return side;
    }

    public void setSide(FactionSide side) {
        this.side = side;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean shouldGenerateMission() {
        return shouldGenerate;
    }

    @Override
    public void updateStep() {
        // Update the entire group here
    }

    @Override
    public void setCurrentCampaignDate(Date date) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitGroup<?> unitGroup = (UnitGroup<?>) o;

        if (id != unitGroup.id) return false;
        return side == unitGroup.side;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (side != null ? side.hashCode() : 0);
        result = 31 * result + (groupUnits != null ? groupUnits.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UnitGroup{" +
                "id=" + id +
                ", side=" + side +
                ", groupUnits=" + groupUnits +
                '}';
    }
}
