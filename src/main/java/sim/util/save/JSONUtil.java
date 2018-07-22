package sim.util.save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JSONUtil {
    private static final Logger log = LogManager.getLogger(JSONUtil.class);

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
            log.debug("Error trying to create class from JSON String!");
            log.debug(e);
            return null;
        }
    }

    public static String fromDomain(Object domain) {
        try {
            return transformer.toJson(domain);
        } catch(Exception e) {
            log.debug("Error trying to create JSON String!");
            e.printStackTrace();
            return "";
        }
    }
}
