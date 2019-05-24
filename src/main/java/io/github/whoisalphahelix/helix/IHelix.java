package io.github.whoisalphahelix.helix;

import io.github.whoisalphahelix.helix.handlers.*;
import io.github.whoisalphahelix.helix.reflection.Reflection;
import org.reflections.Reflections;

import java.util.logging.Logger;

public interface IHelix {
	
	Reflection reflections();
	Reflections reflections2();
	Logger logger();
	AnnotationHandler annotationHandler();
	CacheHandler cacheHandler();
	IOHandler ioHandler();
	NettyHandler nettyHandler();
	UtilHandler utilHandler();
	
}
