package io.github.whoisalphahelix.helix;

public interface TriConsumer<C1, C2, C3> {
	void accept(C1 c1, C2 c2, C3 c3);
}
