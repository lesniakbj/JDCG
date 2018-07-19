package sim.domain.unit;

public abstract class SimUnit implements Simable {
    private boolean isClientUnit;

    private double speedMilesPerHour;
    private double direction;
    private double mapXLocation;
    private double mapYLocation;

    private int minutesPerStep;

    public double convertSpeedToPixelDistance(int deltaTimeMinutes, double conversionFactor) {
        return (speedMilesPerHour / deltaTimeMinutes) * conversionFactor;
    }

    public void isClientUnit(boolean isClientUnit) {
        this.isClientUnit = isClientUnit;
    }
    public boolean isClientUnit() {
        return isClientUnit;
    }

    public double getSpeedMilesPerHour() {
        return speedMilesPerHour;
    }
    public void setSpeedMilesPerHour(double speedMilesPerHour) {
        this.speedMilesPerHour = speedMilesPerHour;
    }

    public double getDirection() {
        return direction;
    }
    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getMapXLocation() {
        return mapXLocation;
    }
    public void setMapXLocation(double mapXLocation) {
        this.mapXLocation = mapXLocation;
    }

    public double getMapYLocation() {
        return mapYLocation;
    }
    public void setMapYLocation(double mapYLocation) {
        this.mapYLocation = mapYLocation;
    }

    public void setMinutesPerUpdate(int minutesPerUpdate) {
        this.minutesPerStep = minutesPerUpdate;
    }
}
