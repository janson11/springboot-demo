package com.janson.netty.demos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/1 10:28 上午
 */
public class IntegerAddDecoder extends ReplayingDecoder<IntegerAddDecoder.Status> {

    private int first;
    private int second;

    enum Status {
        PARSE_1,PARSE_2;
    }

    /**
     * 构造函数中，需要初始化父类的state属性，表示当前阶段
     */
    public IntegerAddDecoder() {
        super(Status.PARSE_1);
    }


    /**
     * 第一个阶段：解码出前一个整数
     * 第二个阶段：解码出后一个整数，然后求和。
     * 每一个阶段的完成，就通过checkpoint(Status)方法，把当前的状态设置为新的Status.
     *
     * 严格来说，checkpoint(Status)方法有两个作用
     * （1）设置state属性的值，更新一下当前的状态。
     * （2）还有一个非常大的作用，就是设置"读端点指针"
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        switch (state()){
            case PARSE_1:
                // 从装饰器ByteBuf中读取数据
                first = in.readInt();
                //第一步解析成功
                //进入第二步，并且设置"读指针断点"为当前的读取位置
                checkpoint(Status.PARSE_2);
                break;

            case PARSE_2:
                second = in.readInt();
                Integer sum = first + second;
                out.add(sum);
                checkpoint(Status.PARSE_1);
                break;//第二步解
            default:
                break;

        }

    }

}
