package sim.domain;

import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSide;

import java.util.List;

public class Airfield {
    private FactionSide ownerSide;
    private AirfieldType airfieldType;
    private List<Munition> munitionStockpile;
    private List<SimUnit> criticalStructures;
    private List<UnitGroup<Aircraft>> stationedAircraft;

    public FactionSide getOwnerSide() {
        return ownerSide;
    }

    public void setOwnerSide(FactionSide ownerSide) {
        this.ownerSide = ownerSide;
    }

    public AirfieldType getAirfieldType() {
        return airfieldType;
    }

    public void setAirfieldType(AirfieldType airfieldType) {
        this.airfieldType = airfieldType;
    }

    public List<Munition>  getMunitionStockpile() {
        return munitionStockpile;
    }

    public void setMunitionStockpile(List<Munition> munitionStockpile) {
        this.munitionStockpile = munitionStockpile;
    }

    public List<SimUnit> getCriticalStructures() {
        return criticalStructures;
    }

    public void setCriticalStructures(List<SimUnit> criticalStructures) {
        this.criticalStructures = criticalStructures;
    }

    public List<UnitGroup<Aircraft>> getStationedAircraft() {
        return stationedAircraft;
    }

    public void setStationedAircraft(List<UnitGroup<Aircraft>> stationedAircraft) {
        this.stationedAircraft = stationedAircraft;
    }
}
