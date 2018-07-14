package sim.util;

import java.awt.geom.Point2D;

public class MathUtil {
    public static double getDistance(Point2D.Double destPair, Point2D.Double sourcePair) {
        double xDiff = destPair.getX() - sourcePair.getX();
        double yDiff = destPair.getY() - sourcePair.getY();
        return Math.sqrt((Math.pow(xDiff, 2)) +  (Math.pow(yDiff, 2)));
    }

    public static double getAngleNorthFace(Point2D.Double destPair, Point2D.Double sourcePair) {
        double xDiff = destPair.getX() - sourcePair.getX();
        double yDiff = destPair.getY() - sourcePair.getY();
        double angle = Math.atan2(yDiff, xDiff);
        // Make angle point North
        angle += Math.PI / 2.0;
        angle = Math.toDegrees(angle);
        if(angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public static double getDistance(double currentX, double currentY, double locationX, double locationY) {
        return getDistance(new Point2D.Double(currentX, currentY), new Point2D.Double(locationX, locationY));
    }
}
