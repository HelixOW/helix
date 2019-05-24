package io.github.whoisalphahelix.helix;

public interface Cache {

	boolean clear();
	
	default String log() {return "";}
	default void save() {}

}
