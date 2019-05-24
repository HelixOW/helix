package io.github.whoisalphahelix.helix;

import io.github.whoisalphahelix.helix.handlers.*;
import io.github.whoisalphahelix.helix.reflection.Reflection;
import org.reflections.Reflections;

import java.util.logging.Logger;

public class Helix implements IHelix {
	
	private final Logger logger = Logger.getLogger("io.github.whoisalphahelix.helix");
	private final AnnotationHandler annotationHandler;
	private final CacheHandler cacheHandler;
	private final IOHandler ioHandler;
	private final NettyHandler nettyHandler;
	private final UtilHandler utilHandler;
	
	private final Reflections reflections2 = new Reflections();
	private final Reflection reflections;
	
	public Helix() {
		this.annotationHandler = new AnnotationHandler(this);
		this.cacheHandler = new CacheHandler(this.logger);
		this.ioHandler = new IOHandler();
		this.nettyHandler = new NettyHandler();
		this.utilHandler = new UtilHandler();
		
		this.reflections = new Reflection(this);
		
		this.annotationHandler.createSingletons();
		this.annotationHandler.randomizeFields();
	}
	
	@Override
	public Reflection reflections() {
		return this.reflections;
	}
	
	@Override
	public Reflections reflections2() {
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
	
	@Override
	public UtilHandler utilHandler() {
		return this.utilHandler;
	}
}
