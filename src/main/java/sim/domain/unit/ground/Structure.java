package sim.domain.unit.ground;

import java.util.Date;

public class Structure extends GroundUnit {
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
