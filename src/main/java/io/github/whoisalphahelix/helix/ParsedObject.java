package io.github.whoisalphahelix.helix;

public interface ParsedObject<T> {
	
	T fromString(String commandString) throws Exception;
	
}
