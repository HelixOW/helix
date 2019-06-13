package io.github.whoisalphahelix.helix;

import io.github.whoisalphahelix.helix.handlers.AnnotationHandler;
import io.github.whoisalphahelix.helix.handlers.CacheHandler;
import io.github.whoisalphahelix.helix.handlers.IOHandler;
import io.github.whoisalphahelix.helix.handlers.NettyHandler;
import io.github.whoisalphahelix.helix.reflection.Reflection;
import org.reflections.Reflections;

import java.util.logging.Logger;

public class Helix implements IHelix {

	private static final Helix INSTANCE = new Helix();
	private final Logger logger = Logger.getLogger("io.github.whoisalphahelix.helix");
	private final AnnotationHandler annotationHandler;
	private final CacheHandler cacheHandler;
	private final IOHandler ioHandler;
	private final NettyHandler nettyHandler;

	private final Reflections reflections2 = new Reflections();
	private final Reflection reflections;

	Helix() {
		this.annotationHandler = new AnnotationHandler(this);
		this.cacheHandler = new CacheHandler(this.logger);
		this.ioHandler = new IOHandler();
		this.nettyHandler = new NettyHandler();

		this.reflections = new Reflection(this);
		
		this.annotationHandler.createSingletons();
		this.annotationHandler.randomizeFields();
	}

	public static Helix helix() {
		return INSTANCE;
	}

	@Override
	public Reflection reflection() {
		return this.reflections;
	}
	
	@Override
	public Reflections reflections() {
		return this.reflections2;
	}
	
	@Override
	public Logger logger() {
		return this.logger;
	}
	
	@Override
	public AnnotationHandler annotationHandler() {
		return this.annotationHandler;
	}
	
	@Override
	public CacheHandler cacheHandler() {
		return this.cacheHandler;
	}
	
	@Override
	public IOHandler ioHandler() {
		return this.ioHandler;
	}
	
	@Override
	public NettyHandler nettyHandler() {
		return this.nettyHandler;
	}
}
