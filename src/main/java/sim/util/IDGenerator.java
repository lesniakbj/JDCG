package sim.util;

import java.util.HashMap;
import java.util.Map;

public class IDGenerator {
    private static Map<Class, Integer> idMap;

    static {
        idMap = new HashMap<>();
    }

    public static int generateNextId(Class clazz) {
        if(idMap.get(clazz) == null) {
            idMap.put(clazz, 1);
            return 1;
        } else {
            int ret = (idMap.get(clazz) + 1);
            idMap.put(clazz, ret);
            return ret + 1;
        }
    }
}
