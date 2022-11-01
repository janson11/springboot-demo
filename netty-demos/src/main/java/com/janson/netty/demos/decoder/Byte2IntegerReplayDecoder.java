package com.janson.netty.demos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description: 使用Byte2IntegerDecoder整数解码器会面临一个问题，需要对ByteBuf 的长度进行检查，如果有足够的字节，才能进行整数的读取。
 * 这种长度的判断可以由netty帮忙来完成。使用netty的ReplayingDecoder类可以省去长度的判断
 * ReplayingDecoder的作用，远不止于进行长度的判断，它更重要的作用是分包传输的应用场景。
 *
 * @Author: shanjian
 * @Date: 2022/11/1 9:49 上午
 */
@Slf4j
public class Byte2IntegerReplayDecoder extends ReplayingDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int i = in.readInt();
        log.info("解码出一个整数：{}", i);
        out.add(i);
    }
}
