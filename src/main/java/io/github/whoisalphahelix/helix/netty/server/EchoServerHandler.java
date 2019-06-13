package io.github.whoisalphahelix.helix.netty.server;

import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import io.github.whoisalphahelix.helix.netty.RequestProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class EchoServerHandler extends ChannelHandlerAdapter {
	
	private final IHelix helix;
	
	public EchoServerHandler(IHelix helix) {
		this.helix = helix;
	}

    public EchoServerHandler() {
        this(Helix.helix());
    }
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		String sentData = in.toString(CharsetUtil.UTF_8);
		String returnee = sentData + "-::=::-" + "{}";
		
		RequestProcessor reprocessor = this.helix.nettyHandler().process(sentData);
		
		if(reprocessor != null)
			returnee = sentData + "-::=::-" + reprocessor.getProcessedData();
		
		ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer(returnee, CharsetUtil.UTF_8)).sync();
		
		if(!f.isSuccess())
			try {
				throw f.cause();
			} catch(Throwable throwable) {
				throwable.printStackTrace();
			}
	}
}
