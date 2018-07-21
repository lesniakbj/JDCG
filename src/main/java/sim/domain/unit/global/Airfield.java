package sim.domain.unit.global;

import sim.domain.enums.AirfieldType;
import sim.domain.enums.FactionSideType;
import sim.domain.unit.UnitGroup;
import sim.domain.unit.air.AirUnit;
import sim.domain.unit.air.MunitionStockpile;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.Structure;

import java.util.ArrayList;
import java.util.List;

public class Airfield {
    private FactionSideType ownerSide;
    private AirfieldType airfieldType;
    private List<MunitionStockpile> munitionStockpile;
    private List<Structure> criticalStructures;
    private List<UnitGroup<GroundUnit>> airfieldGroundGroups;
    private List<UnitGroup<AirUnit>> stationedAircraft;
    private boolean isHomeAirfield;

    public FactionSideType getOwnerSide() {
        return ownerSide;
    }

    public void setOwnerSide(FactionSideType ownerSide) {
        this.ownerSide = ownerSide;
    }

    public AirfieldType getAirfieldType() {
        return airfieldType;
    }

    public void setAirfieldType(AirfieldType airfieldType) {
        this.airfieldType = airfieldType;
    }

    public List<MunitionStockpile> getMunitionStockpile() {
        return munitionStockpile;
    }

    public void setMunitionStockpile(List<MunitionStockpile> munitionStockpile) {
        this.munitionStockpile = munitionStockpile;
    }

    public List<Structure> getCriticalStructures() {
        return criticalStructures;
    }

    public void setCriticalStructures(List<Structure> criticalStructures) {
        this.criticalStructures = criticalStructures;
    }

    public List<UnitGroup<AirUnit>> getStationedAircraft() {
        return stationedAircraft;
    }

    public void setStationedAircraft(List<UnitGroup<AirUnit>> stationedAircraft) {
        this.stationedAircraft = stationedAircraft;
    }

    public List<UnitGroup<GroundUnit>> getAirfieldGroundGroups() {
        return airfieldGroundGroups;
    }

    public void setAirfieldGroundGroups(List<UnitGroup<GroundUnit>> airfieldGroundGroups) {
        this.airfieldGroundGroups = airfieldGroundGroups;
    }

    public boolean isHomeAirfield() {
        return isHomeAirfield;
    }

    public void setHomeAirfield(boolean homeAirfield) {
        isHomeAirfield = homeAirfield;
    }

    public void addAircraftGroup(UnitGroup<AirUnit> group) {
        if(stationedAircraft == null) {
            stationedAircraft = new ArrayList<>();
        }
        stationedAircraft.add(group);
    }

    @Override
    public String toString() {
        return "Airfield{" +
                "ownerSide=" + ownerSide +
                ", airfieldType=" + airfieldType +
                ", munitionStockpile=" + munitionStockpile +
                ", criticalStructures=" + criticalStructures +
                ", airfieldGroundGroups=" + airfieldGroundGroups +
                ", stationedAircraft=" + stationedAircraft +
                ", isHomeAirfield=" + isHomeAirfield +
                '}';
    }
}
