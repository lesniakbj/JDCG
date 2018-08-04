package sim.gen.ground;

import sim.domain.enums.GroundUnitType;
import sim.domain.unit.ground.ArmedShipGroundUnit;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.UnarmedShipGroundUnit;

import java.util.ArrayList;
import java.util.List;

import static sim.domain.enums.GroundUnitType.ARMED_SPEEDBOAT;
import static sim.domain.enums.GroundUnitType.BULK_CARGO_SHIP;
import static sim.domain.enums.GroundUnitType.CG_1174;
import static sim.domain.enums.GroundUnitType.CG_60_NORMANDY;
import static sim.domain.enums.GroundUnitType.FFG_11540;
import static sim.domain.enums.GroundUnitType.FFG_7;
import static sim.domain.enums.GroundUnitType.FF_1135M;
import static sim.domain.enums.GroundUnitType.MOLNIYA;

public class ShipFleetGenerator {
    public static List<GroundUnit> generateLargeCombatFleet(List<GroundUnitType> validTypes) {
        boolean hasBlueCruiser = validTypes.contains(CG_60_NORMANDY);
        boolean hasRedCruiser = validTypes.contains(FFG_11540);

        if(hasBlueCruiser) {
            return blueCombatFleet(validTypes);
        }

        if(hasRedCruiser) {
            return redCombatFleet(validTypes);
        }

        return new ArrayList<>();
    }

    private static List<GroundUnit> redCombatFleet(List<GroundUnitType> validTypes) {
        List<GroundUnit> units = new ArrayList<>();

        units.add(new ArmedShipGroundUnit(CG_1174));
        units.add(new ArmedShipGroundUnit(MOLNIYA));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(FFG_11540));
        units.add(new UnarmedShipGroundUnit(BULK_CARGO_SHIP));

        return units;
    }

    private static List<GroundUnit> blueCombatFleet(List<GroundUnitType> validTypes) {
        List<GroundUnit> units = new ArrayList<>();

        units.add(new ArmedShipGroundUnit(CG_60_NORMANDY));
        units.add(new ArmedShipGroundUnit(FFG_7));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(FFG_7));
        units.add(new UnarmedShipGroundUnit(BULK_CARGO_SHIP));

        return units;
    }

    public static List<GroundUnit> generateScoutFleet(List<GroundUnitType> validTypes) {
        boolean hasBlueCruiser = validTypes.contains(CG_60_NORMANDY);
        boolean hasRedCruiser = validTypes.contains(FFG_11540);

        if(hasBlueCruiser) {
            return blueScoutFleet();
        }

        if(hasRedCruiser) {
            return redScoutFleet();
        }

        return new ArrayList<>();
    }

    private static List<GroundUnit> blueScoutFleet() {
        List<GroundUnit> units = new ArrayList<>();

        units.add(new ArmedShipGroundUnit(CG_60_NORMANDY));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new UnarmedShipGroundUnit(BULK_CARGO_SHIP));

        return units;
    }

    private static List<GroundUnit> redScoutFleet() {
        List<GroundUnit> units = new ArrayList<>();

        units.add(new ArmedShipGroundUnit(FFG_11540));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new UnarmedShipGroundUnit(BULK_CARGO_SHIP));

        return units;
    }
}
