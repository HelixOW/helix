package io.github.whoisalphahelix.helix.handlers;

import io.github.whoisalphahelix.helix.utils.ArrayUtil;
import io.github.whoisalphahelix.helix.utils.JsonUtil;
import io.github.whoisalphahelix.helix.utils.MathUtil;
import io.github.whoisalphahelix.helix.utils.StringUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UtilHandler {

    private static final UtilHandler UTIL_HANDLER = new UtilHandler();

    private final ArrayUtil arrayUtil = new ArrayUtil();
    private final JsonUtil jsonUtil = new JsonUtil();
    private final MathUtil mathUtil = new MathUtil();
    private final StringUtil stringUtil = new StringUtil();

    public static ArrayUtil arrays() {
        return UTIL_HANDLER.getArrayUtil();
    }

    public static JsonUtil json() {
        return UTIL_HANDLER.getJsonUtil();
    }

    public static MathUtil math() {
        return UTIL_HANDLER.getMathUtil();
    }

    public static StringUtil strings() {
        return UTIL_HANDLER.getStringUtil();
    }

    public <T extends ArrayUtil> T getArrayUtil() {
        return (T) arrayUtil;
    }

    public <T extends JsonUtil> T getJsonUtil() {
        return (T) jsonUtil;
    }

    public <T extends MathUtil> T getMathUtil() {
        return (T) mathUtil;
    }

    public <T extends StringUtil> T getStringUtil() {
        return (T) stringUtil;
    }
}
