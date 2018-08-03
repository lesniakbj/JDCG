package sim.domain.unit.ground;

import sim.domain.enums.GroundUnitType;

import java.util.Date;

public class UnarmedGroundUnit extends GroundUnit {

    public UnarmedGroundUnit(GroundUnitType type) {
        setType(type);
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
