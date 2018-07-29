package sim.util;

public class IDGenerator {
    private static long currentId;

    public static long generateNextId() {
        return ++currentId;
    }

    public static long getCurrentId() {
        return currentId;
    }
}
