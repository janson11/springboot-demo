package com.janson.netty.demos.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/20 10:07 上午
 */
@Slf4j
public class PipelineHotOperateTester {

    static class SimpleHandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器A :被回调channelRead()");
            super.channelRead(ctx, msg);
            //从流水线删除当前Handler
            ctx.pipeline().remove(this);
        }
    }

    static class SimpleHandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器B :被回调channelRead()");
            super.channelRead(ctx, msg);
        }
    }

    static class SimpleHandlerC extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器C :被回调channelRead()");
            super.channelRead(ctx, msg);
        }
    }

    //测试热处理器的热插拔

    /**
     * 2022-10-20 11:15:00.153 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 入站处理器A :被回调channelRead()
     * 2022-10-20 11:15:00.154 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 入站处理器B :被回调channelRead()
     * 2022-10-20 11:15:00.154 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 入站处理器C :被回调channelRead()
     * 2022-10-20 11:15:00.154 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 第一次向通道写入站报文end
     * 2022-10-20 11:15:00.155 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 入站处理器B :被回调channelRead()
     * 2022-10-20 11:15:00.155 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 入站处理器C :被回调channelRead()
     * 2022-10-20 11:15:00.155 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 第二次向通道写入站报文end
     * 2022-10-20 11:15:00.155 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 入站处理器B :被回调channelRead()
     * 2022-10-20 11:15:00.155 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 入站处理器C :被回调channelRead()
     * 2022-10-20 11:15:00.155 [main] INFO  c.j.netty.demos.pipeline.PipelineHotOperateTester - 第三次向通道写入站报文end
     */

    @Test
    public void testPipelineHotOperating() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleHandlerA());
                ch.pipeline().addLast(new SimpleHandlerB());
                ch.pipeline().addLast(new SimpleHandlerC());
            }
        };

        EmbeddedChannel ch = new EmbeddedChannel(channelInitializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        // 第一次向通道写入站报文
        ch.writeInbound(buf);
        log.info("第一次向通道写入站报文end");

        // 第二次向通道写入站报文
        ch.writeInbound(buf);
        log.info("第二次向通道写入站报文end");

        // 第三次向通道写入站报文
        ch.writeInbound(buf);
        log.info("第三次向通道写入站报文end");

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
