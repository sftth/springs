package sensing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    public static String nullToStr(Object value) {
        if(value == null) {
            return "";
        } else {
            return (String)value;
        }
    }
}
