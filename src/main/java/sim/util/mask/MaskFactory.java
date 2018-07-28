package sim.util.mask;

import sim.domain.enums.MapType;
import sim.util.mask.exclusion.CaucasusExclusionMask;
import sim.util.mask.exclusion.NevadaExclusionMask;
import sim.util.mask.exclusion.NormandyExclusionMask;
import sim.util.mask.exclusion.PersianGulfExclusionMask;
import sim.util.mask.water.CaucasusWaterMask;
import sim.util.mask.water.NevadaWaterMask;
import sim.util.mask.water.NormandyWaterMask;
import sim.util.mask.water.PersianGulfWaterMask;

import java.awt.geom.Path2D;

public class MaskFactory {

    public static Mask getWaterMask(MapType type) {
        Mask mask;
        switch(type) {
            case PERSIAN_GULF:
                mask = new PersianGulfWaterMask();
                break;
            case CAUCASUS:
                mask = new CaucasusWaterMask();
                break;
            case NORMANDY:
                mask = new NormandyWaterMask();
                break;
            case NEVADA:
                mask = new NevadaWaterMask();
                break;
            default:
                mask = new PersianGulfWaterMask();
        }
        return mask;
    }

    public static Mask getExclusionMask(MapType type) {
        Mask mask;
        switch (type) {
            case PERSIAN_GULF:
                mask = new PersianGulfExclusionMask();
                break;
            case CAUCASUS:
                mask = new CaucasusExclusionMask();
                break;
            case NORMANDY:
                mask = new NormandyExclusionMask();
                break;
            case NEVADA:
                mask = new NevadaExclusionMask();
                break;
            default:
                mask = new CaucasusExclusionMask();
        }
        return mask;
    }

    public static Mask getScaledExclusionMask(MapType type, double scaleX, double scaleY, int gutter) {
        Mask mask;
        switch (type) {
            case PERSIAN_GULF:
                mask = new PersianGulfExclusionMask(scaleX, scaleY, gutter);
                break;
            case CAUCASUS:
                mask = new CaucasusExclusionMask(scaleX, scaleY, gutter);
                break;
            case NORMANDY:
                mask = new NormandyExclusionMask(scaleX, scaleY, gutter);
                break;
            case NEVADA:
                mask = new NevadaExclusionMask(scaleX, scaleY, gutter);
                break;
            default:
                mask = new CaucasusExclusionMask(scaleX, scaleY, gutter);
        }
        return mask;
    }

    public static Path2D.Double getScaledWaterMask(MapType type, double scaleX, double scaleY, int gutter) {
        Mask mask;
        switch (type) {
            case PERSIAN_GULF:
                mask = new PersianGulfWaterMask(scaleX, scaleY, gutter);
                break;
            case CAUCASUS:
                mask = new CaucasusWaterMask(scaleX, scaleY, gutter);
                break;
            case NORMANDY:
                mask = new NormandyWaterMask(scaleX, scaleY, gutter);
                break;
            case NEVADA:
                mask = new NevadaWaterMask(scaleX, scaleY, gutter);
                break;
            default:
                mask = new PersianGulfWaterMask(scaleX, scaleY, gutter);
        }
        return mask;
    }
}
