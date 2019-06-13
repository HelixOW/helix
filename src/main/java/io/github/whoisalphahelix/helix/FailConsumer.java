package io.github.whoisalphahelix.helix;

public interface FailConsumer<C> {
	void accept(C c);

    default void failt(String message) {
    }
}
