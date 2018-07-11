package sim.main;

import java.util.ArrayList;
import java.util.List;
import sim.domain.SimUnit;

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
public class ObjectiveManager {
    private List<SimUnit> mainObjectiveList;
    private List<SimUnit> secondaryObjectiveList;

    public ObjectiveManager() {
        this.mainObjectiveList = new ArrayList<>();
        this.secondaryObjectiveList = new ArrayList<>();
    }

    public List<SimUnit> getMainObjectiveList() {
        return mainObjectiveList;
    }

    public void setMainObjectiveList(List<SimUnit> mainObjectiveList) {
        this.mainObjectiveList = mainObjectiveList;
    }

    public List<SimUnit> getSecondaryObjectiveList() {
        return secondaryObjectiveList;
    }

    public void setSecondaryObjectiveList(List<SimUnit> secondaryObjectiveList) {
        this.secondaryObjectiveList = secondaryObjectiveList;
    }
}
