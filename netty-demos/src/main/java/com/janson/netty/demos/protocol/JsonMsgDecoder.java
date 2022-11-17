package com.janson.netty.demos.protocol;

import com.janson.netty.common.util.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description: 服务器端业务处理器
 * @Author: shanjian
 * @Date: 2022/11/17 5:42 下午
 */
public class JsonMsgDecoder extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String json = (String)msg;
        JsonMsg jsonMsg = JsonMsg.parseFromJson(json);
        Logger.info("收到一个Json 数据包 =》"+jsonMsg);
        ctx.fireChannelRead(jsonMsg);
    }
}
