package sim.util;

public class IDGenerator {
    private static long currentId;

    public static long generateNextId() {
        return ++currentId;
    }
}
