package io.github.whoisalphahelix.helix.handlers;

import com.google.gson.JsonElement;
import io.github.whoisalphahelix.helix.netty.RequestProcessor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class NettyHandler {
	
	private final Map<String, RequestProcessor> reprocessors = new HashMap<>();
	private final Map<String, Consumer<JsonElement>> requests = new HashMap<>();
	
	public void addRequestProcessor(String data, RequestProcessor requestProcessor) {
		this.reprocessors.put(data, requestProcessor);
	}
	
	public RequestProcessor process(String data) {
		return this.reprocessors.get(data);
	}
}
