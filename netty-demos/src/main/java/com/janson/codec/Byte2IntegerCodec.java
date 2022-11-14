package com.janson.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description: 编码器和解码器的结合
 * @Author: shanjian
 * @Date: 2022/11/14 10:18 上午
 */
@Slf4j
public class Byte2IntegerCodec extends ByteToMessageCodec<Integer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) throws Exception {
        out.writeInt(msg);
        log.info("Encode 编码器 write Integer = {}",msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()>=4) {
            int i= in.readInt();
            log.info("Decoder解码 i=：{}",i);
            out.add(i);
        }

    }
}
