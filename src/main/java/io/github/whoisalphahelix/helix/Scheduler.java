package io.github.whoisalphahelix.helix;

import java.util.concurrent.*;

public class Scheduler<V> {

	private final Callable<V> task;
	
	public Scheduler(Callable<V> task) {
		this.task = task;
	}
	
	public V runAsync() {
		ExecutorService service = Executors.newFixedThreadPool(1);
		CompletionService<V> completionService = new ExecutorCompletionService<>(service);
		completionService.submit(task);
		
		try {
			V val = completionService.take().get();
			
			service.shutdown();
			
			return val;
		} catch(InterruptedException | ExecutionException e) {
			e.printStackTrace();
			service.shutdown();
			return null;
		}
	}
}
