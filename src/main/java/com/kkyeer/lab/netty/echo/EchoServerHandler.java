package com.kkyeer.lab.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 下午9:05 2021/1/31
 * @Modified By:
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器收到:" + buf.toString(CharsetUtil.UTF_8));
        ChannelFuture channelFuture = ctx.writeAndFlush(buf);
        channelFuture.get();
        System.out.println("写出完毕");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前流已经读完？？？");
//        下面这个监听器负责关闭Channel
//        ctx.close().addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close().addListener(ChannelFutureListener.CLOSE);
    }
}
