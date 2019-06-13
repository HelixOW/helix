package io.github.whoisalphahelix.helix.netty.client;

import com.google.gson.JsonParser;
import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@Setter
public class EchoClientHandler extends ChannelHandlerAdapter {
	
	private static final JsonParser PARSER = new JsonParser();
	private final IHelix helix;
	private ChannelHandlerContext ctx;
	
	public EchoClientHandler(IHelix helix) {
		this.helix = helix;
	}

    public EchoClientHandler() {
        this(Helix.helix());
    }
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		this.setCtx(ctx);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object s) {
		String[] sentDataAndJson = ((String) s).split("-::=::-");
		String data = sentDataAndJson[0];
		String json = sentDataAndJson[1];
		
		if(this.helix.nettyHandler().getRequests().containsKey(data))
			this.helix.nettyHandler().getRequests().get(data).accept(PARSER.parse(json));
	}
	
	public void requestData(String data) {
		if(this.getCtx() != null) {
			try {
				ChannelFuture f = this.getCtx().writeAndFlush(Unpooled.copiedBuffer(data, CharsetUtil.UTF_8)).sync();
				
				if(!f.isSuccess())
					try {
						throw f.cause();
					} catch(Throwable throwable) {
						throwable.printStackTrace();
					}
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
