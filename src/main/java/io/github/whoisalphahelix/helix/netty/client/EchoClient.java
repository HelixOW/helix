package io.github.whoisalphahelix.helix.netty.client;

import com.google.gson.JsonElement;
import io.github.whoisalphahelix.helix.IHelix;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Consumer;

@Getter
@EqualsAndHashCode
@ToString
public class EchoClient {
	
	private final IHelix helix;
	private final EchoClientHandler ech;
	
	public EchoClient(IHelix helix, String host, int port) {
		this.helix = helix;
		this.ech = new EchoClientHandler(helix);
		
		EventLoopGroup worker = new NioEventLoopGroup();
		
		Bootstrap b = new Bootstrap();
		
		b.group(worker)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) {
						socketChannel.pipeline()
								.addLast(new StringDecoder())
								.addLast(new StringEncoder())
								.addLast(ech);
					}
				});
		helix.logger().info("Registered a new Client for " + host + " on " + port);
		
		b.connect(host, port);
	}
	
	public void request(String sentData, Consumer<JsonElement> nettyCallback) {
		this.getEch().requestData(sentData);
		this.helix.nettyHandler().getRequests().put(sentData, nettyCallback);
	}
}
