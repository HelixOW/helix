package io.github.whoisalphahelix.helix;

public interface FailConsumer<C> {
	void accept(C c);
	
	void failt(String message);
}
