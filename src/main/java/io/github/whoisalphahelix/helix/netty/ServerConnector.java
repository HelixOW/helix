package io.github.whoisalphahelix.helix.netty;

import com.google.gson.JsonElement;
import io.github.whoisalphahelix.helix.IHelix;
import io.github.whoisalphahelix.helix.netty.client.EchoClient;
import io.github.whoisalphahelix.helix.netty.server.EchoServer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.Consumer;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ServerConnector {
	
	private final IHelix helix;
	private EchoServer server;
	private EchoClient client;
	
	public ServerConnector(IHelix helix, int ownPort, String host, int port) {
		this.helix = helix;
		
		if(ownPort == port) {
			this.helix.logger().warning("Can not connect to own server!");
			return;
		}
		
		this.setServer(new EchoServer(helix, ownPort));
		if(!host.isEmpty())
			this.setClient(new EchoClient(helix, host, port));
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		
		executor.execute(() -> {
			try {
				this.getServer().start();
			} catch(Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void makeRequest(String request, Consumer<JsonElement> callback) {
		this.getClient().request(request, callback);
	}
	
	public void addRequestProcessor(String request, RequestProcessor reprocessor) {
		this.helix.nettyHandler().addRequestProcessor(request, reprocessor);
	}
}
