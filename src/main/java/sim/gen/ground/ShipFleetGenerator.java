package sim.gen.ground;

import sim.domain.enums.GroundUnitType;
import sim.domain.unit.ground.ArmedShipGroundUnit;
import sim.domain.unit.ground.GroundUnit;
import sim.domain.unit.ground.UnarmedShipGroundUnit;

import java.util.ArrayList;
import java.util.List;

import static sim.domain.enums.GroundUnitType.ARMED_SPEEDBOAT;
import static sim.domain.enums.GroundUnitType.BULK_CARGO_SHIP;
import static sim.domain.enums.GroundUnitType.CGN_1144_PYOTR_VELIKIY;
import static sim.domain.enums.GroundUnitType.CG_60_NORMANDY;
import static sim.domain.enums.GroundUnitType.FFG_11540_NEUSTRASHIMY;
import static sim.domain.enums.GroundUnitType.FFG_7CL_OH_PERRY;
import static sim.domain.enums.GroundUnitType.FSG_1241_MOLNIYA;

public class ShipFleetGenerator {
    public static List<GroundUnit> generateLargeCombatFleet(List<GroundUnitType> validTypes) {
        boolean hasBlueCruiser = validTypes.contains(CG_60_NORMANDY);
        boolean hasRedCruiser = validTypes.contains(FFG_11540_NEUSTRASHIMY);

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

        units.add(new ArmedShipGroundUnit(CGN_1144_PYOTR_VELIKIY));
        units.add(new ArmedShipGroundUnit(FSG_1241_MOLNIYA));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(FFG_11540_NEUSTRASHIMY));
        units.add(new UnarmedShipGroundUnit(BULK_CARGO_SHIP));

        return units;
    }

    private static List<GroundUnit> blueCombatFleet(List<GroundUnitType> validTypes) {
        List<GroundUnit> units = new ArrayList<>();

        units.add(new ArmedShipGroundUnit(CG_60_NORMANDY));
        units.add(new ArmedShipGroundUnit(FFG_7CL_OH_PERRY));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(FFG_7CL_OH_PERRY));
        units.add(new UnarmedShipGroundUnit(BULK_CARGO_SHIP));

        return units;
    }

    public static List<GroundUnit> generateScoutFleet(List<GroundUnitType> validTypes) {
        boolean hasBlueCruiser = validTypes.contains(CG_60_NORMANDY);
        boolean hasRedCruiser = validTypes.contains(FFG_11540_NEUSTRASHIMY);

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

        units.add(new ArmedShipGroundUnit(FFG_11540_NEUSTRASHIMY));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new ArmedShipGroundUnit(ARMED_SPEEDBOAT));
        units.add(new UnarmedShipGroundUnit(BULK_CARGO_SHIP));

        return units;
    }
}
