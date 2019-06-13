package io.github.whoisalphahelix.helix;

import io.github.whoisalphahelix.helix.handlers.AnnotationHandler;
import io.github.whoisalphahelix.helix.handlers.CacheHandler;
import io.github.whoisalphahelix.helix.handlers.IOHandler;
import io.github.whoisalphahelix.helix.handlers.NettyHandler;
import io.github.whoisalphahelix.helix.reflection.Reflection;
import org.reflections.Reflections;

import java.util.logging.Logger;

public interface IHelix {

    Reflection reflection();

    Reflections reflections();
	Logger logger();
	AnnotationHandler annotationHandler();
	CacheHandler cacheHandler();
	IOHandler ioHandler();
	NettyHandler nettyHandler();

}
