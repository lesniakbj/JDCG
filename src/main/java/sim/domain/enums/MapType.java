package sim.domain.enums;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public enum MapType {
    CAUCASUS("Caucasus", null, null, null, null, null, 2.1756, (1 / .42), (1 / .43)),
    PERSIAN_GULF("Persian Gulf", new Rectangle(-123123.1, -12312313.919, 191930.202, 39193.123), new Pair<>(10, 22), new Pair<>(18, 36),  new Pair<>(22, 39), new Pair<>(12, 30), 2.218859292829, (1 / .42), (1 / .43)),
    NEVADA("Nevada", null, null, null, null, null, 2.3691, (1 / .42), (1 / .43)),
    NORMANDY("Normandy", null, null, null, null, null, 0.0, (1 / .42), (1 / .43));

    private String mapName;
    private Rectangle mapBounds;
    private Map<Season, Pair<Integer, Integer>> tempMap;
    private double mapScalePixelsPerMile;
    private double mapXScale;
    private double mapYScale;

    MapType(String mapName, Rectangle mapBounds, Pair<Integer, Integer> tempBlockWinter, Pair<Integer, Integer> tempBlockSpring, Pair<Integer, Integer> tempBlockSummer, Pair<Integer, Integer> tempBlockFall, double mapScalePixelsPerMile, double mapXScale, double mapYScale) {
        this.mapName = mapName;
        this.mapBounds = mapBounds;
        this.tempMap = new HashMap<>();
        tempMap.put(Season.WINTER, tempBlockWinter);
        tempMap.put(Season.SPRING, tempBlockSpring);
        tempMap.put(Season.SUMMER, tempBlockSummer);
        tempMap.put(Season.FALL, tempBlockFall);
        this.mapScalePixelsPerMile = mapScalePixelsPerMile;
        this.mapXScale = mapXScale;
        this.mapYScale = mapYScale;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public Rectangle getMapBounds() {
        return mapBounds;
    }

    public void setMapBounds(Rectangle mapBounds) {
        this.mapBounds = mapBounds;
    }

    public Map<Season, Pair<Integer, Integer>> getTempMap() {
        return tempMap;
    }

    public void setTempMap(Map<Season, Pair<Integer, Integer>> tempMap) {
        this.tempMap = tempMap;
    }

    public double getMapScalePixelsPerMile() {
        return mapScalePixelsPerMile;
    }

    public void setMapScalePixelsPerMile(double mapScalePixelsPerMile) {
        this.mapScalePixelsPerMile = mapScalePixelsPerMile;
    }

    public double scaleDistance(double dist) {
        return dist / mapScalePixelsPerMile;
    }

    public double getMapXScale() {
        return mapXScale;
    }

    public double getMapYScale() {
        return mapYScale;
    }

    private static class Rectangle {
        private double top;
        private double left;
        private double bottom;
        private double right;

        public Rectangle(double top, double left, double bottom, double right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }
    }
}
