package sim.domain;

import gen.domain.enums.FactionSide;
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
    private FactionSide side;
    private List<T> groupUnits;

    public UnitGroup(List<T> groupUnits) {
        this.groupUnits = groupUnits;
        this.side = FactionSide.BLUEFOR;
        this.setDirection(0.0);
        this.setMapXLocation(0.0);
        this.setMapYLocation(0.0);
        this.setSpeedMilesPerHour(0.0);
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

    @Override
    void updateStep() {
        // Update the entire group here
    }
}
