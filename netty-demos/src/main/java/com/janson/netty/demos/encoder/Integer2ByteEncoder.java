package com.janson.netty.demos.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 自定义编码器实现
 * @Author: shanjian
 * @Date: 2022/11/9 10:14 上午
 */
@Slf4j
public class Integer2ByteEncoder extends MessageToByteEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
        // 原始数据

        // 目标ByteBuf
        out.writeInt(msg);

        log.info("encoder Integer={}",msg);

    }
}
