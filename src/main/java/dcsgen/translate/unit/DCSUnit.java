package dcsgen.translate.unit;

import dcsgen.translate.waypoint.DCSAltitidueType;

public class DCSUnit {
    private int altitude;
    private boolean hardpointRacks;
    private DCSAltitidueType altitidueType;
    private String liveryId;
    private DCSSkill skill;
    private double speed;
    private String type;
    private long unitId;
    private double psi;
    private double positionX;
    private double positionY;
    private String dictKeyName;
    private DCSPayload payload;
    private long heading;
    private DCSCallsign callsign;

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public boolean isHardpointRacks() {
        return hardpointRacks;
    }

    public void setHardpointRacks(boolean hardpointRacks) {
        this.hardpointRacks = hardpointRacks;
    }

    public DCSAltitidueType getAltitidueType() {
        return altitidueType;
    }

    public void setAltitidueType(DCSAltitidueType altitidueType) {
        this.altitidueType = altitidueType;
    }

    public String getLiveryId() {
        return liveryId;
    }

    public void setLiveryId(String liveryId) {
        this.liveryId = liveryId;
    }

    public DCSSkill getSkill() {
        return skill;
    }

    public void setSkill(DCSSkill skill) {
        this.skill = skill;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public double getPsi() {
        return psi;
    }

    public void setPsi(double psi) {
        this.psi = psi;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public String getDictKeyName() {
        return dictKeyName;
    }

    public void setDictKeyName(String dictKeyName) {
        this.dictKeyName = dictKeyName;
    }

    public DCSPayload getPayload() {
        return payload;
    }

    public void setPayload(DCSPayload payload) {
        this.payload = payload;
    }

    public long getHeading() {
        return heading;
    }

    public void setHeading(long heading) {
        this.heading = heading;
    }

    public DCSCallsign getCallsign() {
        return callsign;
    }

    public void setCallsign(DCSCallsign callsign) {
        this.callsign = callsign;
    }
}
