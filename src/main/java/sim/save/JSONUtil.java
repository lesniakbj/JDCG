package sim.save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {
    private static final Gson transformer;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        transformer = builder.create();
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return transformer.fromJson(json, clazz);
        } catch(Exception e) {
            return null;
        }
    }

    public static String fromDomain(Object domain) {
        try {
            return transformer.toJson(domain);
        } catch(Exception e) {
            return "";
        }
    }
}
