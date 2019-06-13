package io.github.whoisalphahelix.helix.netty.server;

import io.github.whoisalphahelix.helix.Helix;
import io.github.whoisalphahelix.helix.IHelix;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EchoServer {
	
	private final IHelix helix;
	private final int port;
	private final EventLoopGroup workerGroup = new NioEventLoopGroup();
	private ChannelFuture f;
	
	public EchoServer(IHelix helix, int port) {
		this.helix = helix;
		this.port = port;
	}

    public EchoServer(int port) {
        this(Helix.helix(), port);
    }
	
	public void start() {
		ServerBootstrap b = new ServerBootstrap();
		
		b.group(this.getWorkerGroup())
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) {
						EchoServer.this.helix.logger().info("New client connected on " + socketChannel.localAddress());
						
						socketChannel.pipeline().addLast(new StringEncoder()).addLast(new StringEncoder())
								.addLast(new EchoServerHandler(EchoServer.this.helix));
					}
				});
		
		this.setF(b.bind(port));
	}
	
	public void stop() {
		try {
			this.getF().channel().close().sync();
			this.getWorkerGroup().shutdownGracefully();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
