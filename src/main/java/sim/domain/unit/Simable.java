package sim.domain.unit;

import java.util.Date;

public interface Simable {
        void updateStep();
        void setMinutesPerUpdate(int minutesPerUpdate);
        void setCurrentCampaignDate(Date date);
        boolean shouldGenerateMission();
}
