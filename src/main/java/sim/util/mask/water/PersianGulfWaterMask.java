package sim.util.mask.water;


import sim.util.mask.Mask;

public class PersianGulfWaterMask extends Mask {
    private static int[][] maskPoints = new int[][] {
            {561, 337}, {551, 685}, {596, 668}, {643, 673},
            {688, 669}, {740, 643}, {850, 541}, {880, 505},
            {896, 477}, {917, 477}, {896, 539}, {904, 586},
            {916, 632}, {942, 669}, {957, 687}, {1035, 687},
            {1015, 544}, {978, 531}, {955, 472}, {942, 421},
            {921, 411}, {893, 408}, {883, 439}, {851, 450},
            {765, 467}, {728, 445}, {693, 444}, {605, 381}
    };

    public PersianGulfWaterMask() {
        super(maskPoints);
    }

    public PersianGulfWaterMask(double scaleX, double scaleY, int gutterHeight) {
        super(maskPoints, scaleX, scaleY, gutterHeight);
    }
}
