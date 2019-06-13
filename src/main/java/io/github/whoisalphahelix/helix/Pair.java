package io.github.whoisalphahelix.helix;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<K, V> {
    private K k;
    private V v;
}
