package com.kkyeer.lab.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

/**
 * @Author: kkyeer
 * @Description: Echo Client
 * @Date:Created in 下午9:31 2021/1/31
 * @Modified By:
 */
public class EchoClient {
    public void connect(String host,int port) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(host,port)
                .handler(
                        new ChannelInitializer<SocketChannel>() {
                            /**
                             * This method will be called once the {@link Channel} was registered. After the method returns this instance
                             * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
                             *
                             * @param ch the {@link Channel} which was registered.
                             * @throws Exception is thrown if an error occurs. In that case it will be handled by
                             *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
                             *                   the {@link Channel}.
                             */
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new EchoClientHandler());
                            }
                        }
                );
        ChannelFuture future = bootstrap.connect().sync();
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient().connect("localhost",8080);
    }
}
