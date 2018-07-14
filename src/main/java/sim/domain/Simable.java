package sim.domain;

public interface Simable {
        void updateStep();
        void setMinutesPerUpdate(int minutesPerUpdate);
}
