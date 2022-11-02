package com.janson.netty.demos.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 读取上一站的入职数据，把它转换成字符串，并且输出到Console控制台
 * @Author: shanjian
 * @Date: 2022/11/2 10:37 上午
 */
@Slf4j
public class StringProcessHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String s = (String) msg;
            log.info("打印出一个字符串 {}",s);
        } else {
            super.channelRead(ctx, msg);
        }
    }
}
