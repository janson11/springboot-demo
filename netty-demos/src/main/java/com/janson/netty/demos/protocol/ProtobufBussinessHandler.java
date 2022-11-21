package com.janson.netty.demos.protocol;

import com.janson.netty.common.util.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description: 服务端业务处理器
 * @Author: shanjian
 * @Date: 2022/11/21 11:02 上午
 */
public class ProtobufBussinessHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgProtos.Msg protoMsg = (MsgProtos.Msg) msg;
        Logger.info("收到一个MsgProtos.Msg 数据包=》");
        Logger.info("protoMsg.getId()=" + protoMsg.getId());
        Logger.info("protoMsg.getContent()=" + protoMsg.getContent());
//        super.channelRead(ctx, msg);
    }
}
