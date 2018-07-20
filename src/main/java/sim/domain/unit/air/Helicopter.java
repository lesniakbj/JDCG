package sim.domain.unit.air;

import java.util.Date;

public class Helicopter extends AirUnit {
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
