package sim.util.mask.exclusion;

import sim.util.mask.Mask;

public class PersianGulfExclusionMask extends Mask {
    private static int[][] maskPoints = new int[][] {
            {420, 117}, {610, 362}, {630, 400}, {514, 660},
            {743, 683}, {951, 687}, {1007, 517}, {1019, 313},
            {1328, 323}, {1164, 692}, {367, 697}, {238, 675},
            {72, 230}
    };

    public PersianGulfExclusionMask() {
        super(maskPoints);
    }

    public PersianGulfExclusionMask(double scaleX, double scaleY, int gutterHeight) {
        super(maskPoints, scaleX, scaleY, gutterHeight);
    }
}
