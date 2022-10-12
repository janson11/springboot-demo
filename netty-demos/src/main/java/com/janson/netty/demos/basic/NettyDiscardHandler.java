package com.janson.netty.demos.basic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 业务处理器
 * 所有的业务处理都在Handler处理器中完成。
 * 入站：输入
 * 出站: 输出
 * @Author: shanjian
 * @Date: 2022/10/12 4:40 下午
 */
@Slf4j
public class NettyDiscardHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        try {
            log.info("收到消息，丢弃如下:");
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
            }
            System.out.println();
        }finally {
            ReferenceCountUtil.release(msg);
        }
//        super.channelRead(ctx, msg);
    }
}
