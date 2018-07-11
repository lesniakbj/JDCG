package gen.domain.enums;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public enum MapType {
    CAUCASUS("Caucasus", null, null, null, null, null),
    PERSIAN_GULF("Persian Gulf", new Rectangle(-123123.1, -12312313.919, 191930.202, 39193.123), new Pair<>(10, 22), new Pair<>(18, 36),  new Pair<>(22, 39), new Pair<>(12, 30)),
    NEVADA("Nevada", null, null, null, null, null),
    NORMANDY("Normandy", null, null, null, null, null);

    private String mapName;
    private Rectangle mapBounds;
    private Map<Season, Pair<Integer, Integer>> tempMap;

    MapType(String mapName, Rectangle mapBounds, Pair<Integer, Integer> tempBlockWinter, Pair<Integer, Integer> tempBlockSpring, Pair<Integer, Integer> tempBlockSummer, Pair<Integer, Integer> tempBlockFall) {
        this.mapName = mapName;
        this.mapBounds = mapBounds;
        this.tempMap = new HashMap<>();
        tempMap.put(Season.WINTER, tempBlockWinter);
        tempMap.put(Season.SPRING, tempBlockSpring);
        tempMap.put(Season.SUMMER, tempBlockSummer);
        tempMap.put(Season.FALL, tempBlockFall);
    }

    public String getMapName() {
        return mapName;
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
