package sim.util.mask.exclusion;

import sim.util.mask.Mask;

public class NevadaExclusionMask extends Mask {
    private static int[][] maskPoints = new int[][] {
            {371, 216}, {549, 423}, {615, 519}, {694, 699},
            {232, 699}, {171, 198}
    };

    public NevadaExclusionMask() {
        super(maskPoints);
    }

    public NevadaExclusionMask(double scaleX, double scaleY, int gutterHeight) {
        super(maskPoints, scaleX, scaleY, gutterHeight);
    }
}