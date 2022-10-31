
package com.janson.netty.demos.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:自定义整数处理器:负责业务处理
 * @Author: shanjian
 * @Date: 2022/10/31 10:35 上午
 */
@Slf4j
public class IntegerProcessHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer integer = (Integer) msg;
        log.info("打印出一个整数：{}",integer);

    }
}
