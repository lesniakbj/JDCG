package sim.util;

import javafx.util.Pair;

public class MathUtil {
    public static double getDistance(Pair<Double, Double> destPair, Pair<Double, Double> sourcePair) {
        double xDiff = destPair.getKey() - sourcePair.getKey();
        double yDiff = destPair.getValue() - sourcePair.getValue();
        return Math.sqrt((Math.pow(xDiff, 2)) +  (Math.pow(yDiff, 2)));
    }

    public static double getAngleNorthFace(Pair<Double, Double> destPair, Pair<Double, Double> sourcePair) {
        double xDiff = destPair.getKey() - sourcePair.getKey();
        double yDiff = destPair.getValue() - sourcePair.getValue();
        double angle = Math.atan2(yDiff, xDiff);
        // Make angle point North
        angle += Math.PI / 2.0;
        angle = Math.toDegrees(angle);
        if(angle < 0) {
            angle += 360;
        }
        return angle;
    }
}
