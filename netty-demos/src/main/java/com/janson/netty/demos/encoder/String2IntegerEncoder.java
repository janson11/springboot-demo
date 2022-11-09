package com.janson.netty.demos.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/9 10:27 上午
 */
@Slf4j
public class String2IntegerEncoder extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        log.info("msg={}",msg);

        char[] chars = msg.toCharArray();
        for (char a : chars) {
            // 48是0的编码，57是9的编码
            if (a>=48 && a<=57) {
                out.add(new Integer(a));
            }
        }
    }
}
