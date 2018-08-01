package sim.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogUtil {
    private static final Logger log = LogManager.getLogger(LogUtil.class);

    public static void log(String msg) {
        log.debug(msg);
    }
}
