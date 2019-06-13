package io.github.whoisalphahelix.helix;

import java.util.Arrays;
import java.util.HashMap;

public class MultiKeyMap<V> extends HashMap<Object[], V> {

    public boolean containsKey(Object... keys) {
        for (Object[] arr : keySet()) {
            if (Arrays.equals(arr, keys))
                return true;
        }
        return false;
    }

    public V get(Object... keys) {
        for (Object[] arr : keySet()) {
            if (Arrays.equals(arr, keys))
                return get((Object) arr);
        }

        return null;
    }

    public V put(V value, Object... keys) {
        return put(keys, value);
    }
}
