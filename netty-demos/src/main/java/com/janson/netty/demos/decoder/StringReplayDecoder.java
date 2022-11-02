package com.janson.netty.demos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Description: 字符串分包解码器
 * @Author: shanjian
 * @Date: 2022/11/2 10:20 上午
 */
public class StringReplayDecoder extends ReplayingDecoder<StringReplayDecoder.Status> {

    enum Status {
        PARSE_1, PARSE_2;
    }


    private int length;
    private byte[] inBytes;

    public StringReplayDecoder() {
        super(Status.PARSE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case PARSE_1:
                // 第一步 从装饰器ByteBuf中读取长度
                length = in.readInt();
                inBytes = new byte[length];
                // 第二步 读取内容，并且设置读指针断点为当前的读取位置
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                // 第二步 从装饰器ByteBuf中读取内容数组
                 in.readBytes(inBytes,0,length);
                 out.add(new String(inBytes,"UTF-8"));
                // 第二步 解析成功
                // 进入第一步，读取下一个字符串的长度
                // 并且设置读指针断点为当前的读取位置
                checkpoint(Status.PARSE_1);
                break;

            default:
                break;
        }

    }
}
