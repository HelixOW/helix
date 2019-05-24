package io.github.whoisalphahelix.helix.handlers;

import io.github.whoisalphahelix.helix.Cache;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class CacheHandler {
	
	private final Logger logger;
	private final List<Cache> caches = new ArrayList<>();
	
	private int cacheClearTime = 30;
	private Timer cacheTimer;
	
	public CacheHandler addCache(Cache cache) {
		this.caches.add(cache);
		return this;
	}
	
	public CacheHandler removeCache(Cache cache) {
		this.caches.remove(cache);
		return this;
	}
	
	public CacheHandler startCacheClearing() {
		if(cacheTimer != null)
			return this;
		
		cacheTimer = new Timer();
		
		cacheTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				for(Cache c : CacheHandler.this.caches) {
					c.save();
					c.clear();
					CacheHandler.this.logger.info(c.log());
				}
			}
		}, this.cacheClearTime * 1000 * 60, this.cacheClearTime * 1000 * 60);
		
		return this;
	}
}
