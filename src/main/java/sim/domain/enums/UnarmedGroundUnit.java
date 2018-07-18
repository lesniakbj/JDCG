package sim.domain.enums;

import sim.domain.GroundUnit;

import java.util.Date;

public class UnarmedGroundUnit extends GroundUnit {
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
