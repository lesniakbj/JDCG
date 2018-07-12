package sim.domain;

import gen.domain.enums.AirfieldType;
import gen.domain.enums.MunitionType;

import java.util.Map;

public class Airfield {
    private AirfieldType airfieldType;
    private Map<MunitionType, Integer> munitionStockpile;
}
