package sim.util.mask.exclusion;

import sim.util.mask.Mask;

public class NormandyExclusionMask extends Mask {
    private static int[][] maskPoints = new int[][] {
            {854, 95}, {870, 495}, {1035, 520}, {1164, 690},
            {1443, 694}, {1444, 81}
    };

    public NormandyExclusionMask() {
        super(maskPoints);
    }

    public NormandyExclusionMask(double scaleX, double scaleY, int gutterHeight) {
        super(maskPoints, scaleX, scaleY, gutterHeight);
    }
}
