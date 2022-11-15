package com.janson.netty.demos.echoServer;

import com.janson.netty.common.util.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/28 10:14 上午
 */
@ChannelHandler.Sharable
@Slf4j
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {
    public static final NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        int len = in.readableBytes();
        byte[] buf = new byte[len];
        in.getBytes(0, buf);
        Logger.info("client received:{} "+ new String(buf, "UTF-8"));
        in.release();
    }
}
