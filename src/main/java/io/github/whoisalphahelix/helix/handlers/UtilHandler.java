package io.github.whoisalphahelix.helix.handlers;

import io.github.whoisalphahelix.helix.utils.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UtilHandler {
	
	private final ArrayUtil arrayUtil;
	private final JsonUtil jsonUtil;
	private final MathUtil mathUtil;
	private final StringUtil stringUtil;
	
	public UtilHandler() {
		this.arrayUtil = new ArrayUtil(this);
		this.jsonUtil = new JsonUtil();
		this.mathUtil = new MathUtil();
		this.stringUtil = new StringUtil();
	}
	
}
