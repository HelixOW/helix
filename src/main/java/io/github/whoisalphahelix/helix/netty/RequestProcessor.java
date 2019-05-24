package io.github.whoisalphahelix.helix.netty;

import com.google.gson.JsonElement;

public interface RequestProcessor {
	JsonElement getProcessedData();
}
