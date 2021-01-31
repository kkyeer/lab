package com.kkyeer.lab.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 下午9:32 2021/1/31
 * @Modified By:
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Read From Server:" + msg.toString(CharsetUtil.UTF_8));
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelActive()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new Thread(
                ()->{
                    Scanner scanner = new Scanner(System.in);
                    String consoleInput ;
                    while ((consoleInput = scanner.nextLine()) != null) {
                        System.out.println("命令行:" + consoleInput);
                        ctx.writeAndFlush(Unpooled.copiedBuffer(consoleInput+"\n", CharsetUtil.UTF_8));
                    }
                }
        ).start();
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello netty server", CharsetUtil.UTF_8));
        System.out.println("写出到服务器");
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        cause.printStackTrace();
        ctx.close();
    }
}
