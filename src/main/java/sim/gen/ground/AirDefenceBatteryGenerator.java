package sim.gen.ground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sim.campaign.DynamicCampaignSim;
import sim.domain.enums.AirDefenceUnitType;
import sim.domain.unit.ground.defence.AirDefenceUnit;
import sim.domain.unit.ground.defence.MissileAirDefenceUnit;

import java.util.ArrayList;
import java.util.List;

public class AirDefenceBatteryGenerator {
    private static final Logger log = LogManager.getLogger(AirDefenceBatteryGenerator.class);

    public static List<AirDefenceUnit> getIRBatteryFrom(List<AirDefenceUnitType> validTypes, int numLaunchers) {
        AirDefenceUnitType irType = validTypes.get(DynamicCampaignSim.getRandomGen().nextInt(validTypes.size()));

        List<AirDefenceUnit> units = new ArrayList<>();
        for(int i = 0; i < numLaunchers; i ++) {
            units.add(new MissileAirDefenceUnit(irType));
        }

        return units;
    }

    public static List<AirDefenceUnit> getLongRangeBatteryFrom(List<AirDefenceUnitType> validTypes, int numLaunchers) {
        boolean containsS300 = validTypes.contains(AirDefenceUnitType.SAM_SA10_S300_LN_5P85C) || validTypes.contains(AirDefenceUnitType.SAM_SA10_S300_LN_5P85D);
        boolean containsPatriot = validTypes.contains(AirDefenceUnitType.SAM_PATRIOT_LN_M901);

        if(containsS300) {
            return createS300Battery(numLaunchers);
        }

        if(containsPatriot) {
            return createPatriotBattery(numLaunchers);
        }

        return new ArrayList<>();
    }

    public static List<AirDefenceUnit> getMediumRangeBatteryFrom(List<AirDefenceUnitType> validTypes, int numLaunchers) {
        // Remove any long range battery options from the validTypes
        validTypes.remove(AirDefenceUnitType.SAM_SA10_S300_LN_5P85C);
        validTypes.remove(AirDefenceUnitType.SAM_SA10_S300_LN_5P85D);
        validTypes.remove(AirDefenceUnitType.SAM_PATRIOT_LN_M901);
        AirDefenceUnitType samType = validTypes.get(DynamicCampaignSim.getRandomGen().nextInt(validTypes.size()));

        switch (samType) {
            case SAM_HAWK_LN_M192:
                return createHawkBattery(numLaunchers);
            case SAM_ROLAND_ADS:
                return createRolandBattery(numLaunchers);
            case SAM_SA11_BUK_LN_9A310M1:
                return createBukBattery(numLaunchers);
            case SAM_SA15_TOR:
                return createTorBattery(numLaunchers);
            case SAM_SA8_OSA:
                return createOsaBattery(numLaunchers);
            case SAM_SA6_KUB_LN_2P25:
                return createKubBattery(numLaunchers);
            case SAM_SA3_S125_LN_5P73:
                return createS125Battery(numLaunchers);
        }

        return new ArrayList<>();
    }

    private static List<AirDefenceUnit> createS125Battery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA3_S125_SR_P19));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA3_S125_TR_SNR));
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_SA3_S125_LN_5P73, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createKubBattery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA6_KUB_STR_9S91));
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_SA6_KUB_LN_2P25, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createOsaBattery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_SA8_OSA, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createTorBattery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_SA15_TOR, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createBukBattery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA11_BUK_CC_9S470M1));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA11_BUK_SR_9R18M1));
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_SA11_BUK_LN_9A310M1, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createRolandBattery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_ROLAND_EWR));
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_ROLAND_ADS, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createHawkBattery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_HAWK_SR_ANSPQ50));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_HAWK_TR_ANMPQ46));
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_HAWK_LN_M192, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createPatriotBattery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_PATRIOT_AMG_ANMRC137));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_PATRIOT_ECS_ANMPQ104));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_PATRIOT_EPP_3));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_PATRIOT_STR_ANMPQ53));
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_PATRIOT_LN_M901, numLaunchers);

        return units;
    }

    private static List<AirDefenceUnit> createS300Battery(int numLaunchers) {
        List<AirDefenceUnit> units = new ArrayList<>();

        // Create a standard battery with the requested number of launchers
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA10_S300_CP_54K6));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA10_S300_SR_5N66M));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA10_S300_SR_64H6E));
        units.add(new MissileAirDefenceUnit(AirDefenceUnitType.SAM_SA10_S300_TR_30N6));
        generateLaunchersOfType(units, AirDefenceUnitType.SAM_SA10_S300_LN_5P85C, numLaunchers);

        return units;
    }

    private static void generateLaunchersOfType(List<AirDefenceUnit> units, AirDefenceUnitType type, int numLaunchers) {
        for(int i = 0; i < numLaunchers; i++) {
            units.add(new MissileAirDefenceUnit(type));
        }
    }


}
