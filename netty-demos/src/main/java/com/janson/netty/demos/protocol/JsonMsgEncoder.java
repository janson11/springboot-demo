package com.janson.netty.demos.protocol;

import com.janson.netty.common.util.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/17 6:17 下午
 */
public class JsonMsgEncoder extends MessageToMessageEncoder<JsonMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, JsonMsg msg, List<Object> out) throws Exception {
        // 原始数据
        String json = msg.convertToJson();
        Logger.info("发送报文: " + json);
        out.add(json);
    }
}
