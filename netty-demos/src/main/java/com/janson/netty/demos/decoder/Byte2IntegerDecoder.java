
package com.janson.netty.demos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description: 自定义整数解码器实现：负责解码
 * @Author: shanjian
 * @Date: 2022/10/31 10:29 上午
 */
@Slf4j
public class Byte2IntegerDecoder  extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes()>=4){
            Integer anInt = in.readInt();
            log.info("解码出一个整数：{}",anInt);
            out.add(anInt);
        }
    }
}
