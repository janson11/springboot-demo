package com.janson.netty.demos.echoServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 服务器端的入站处理器
 *
 * @ChannelHandler.Sharable 这个注解的作用是标注一个Handler实例可以被多个通道安全地共享
 * 什么是Handler共享：就是多个通道的流水线可以加入同一个Handler业务处理器实例。
 * 同一个通道上的所有业务处理器，只能被同一个线程处理。
 *
 * @Author: shanjian
 * @Date: 2022/10/26 11:33 上午
 */
@ChannelHandler.Sharable
@Slf4j
public class NettyEchoServerHandler  extends ChannelInboundHandlerAdapter {
    public static final NettyEchoServerHandler INSTANCE = new NettyEchoServerHandler();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        log.info("msg type: {} 堆内存：直接内存",in.hasArray());
        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0,arr);
        log.info("server received :{}",new String(arr,"UTF-8"));

        // 写数据，异步任务
        log.info("写回前，msg.refCnt :{}",in.refCnt());
        ChannelFuture f = ctx.writeAndFlush(msg);
        f.addListener(future -> {
         log.info("写回后，msg.refCnt :{}",in.refCnt());
        });
//        super.channelRead(ctx, msg);
    }
}
