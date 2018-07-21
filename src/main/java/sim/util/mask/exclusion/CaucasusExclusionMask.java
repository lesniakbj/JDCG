package sim.util.mask.exclusion;

import sim.util.mask.Mask;

public class CaucasusExclusionMask extends Mask {
    private static int[][] maskPoints = new int[][] {
            {831, 298}, {812, 315}, {849, 341}, {905, 360},
            {936, 378}, {983, 394}, {1057, 392}, {1051, 407},
            {1064, 410}, {1111, 399}, {1210, 436}, {1236, 466},
            {1271, 458}, {1305, 476}, {1308, 454}, {1342, 443},
            {1358, 426}, {1283, 427}, {1199, 399}, {1117, 351},
            {1095, 337}, {1065, 346}, {1016, 317}, {940, 310}
    };

    public CaucasusExclusionMask() {
        super(maskPoints);
    }

    public CaucasusExclusionMask(double scaleX, double scaleY, int gutterHeight) {
        super(maskPoints, scaleX, scaleY, gutterHeight);
    }
}
