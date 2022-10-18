package com.janson.netty.demos.pipeline;

import com.alibaba.fastjson.JSON;
import com.janson.netty.common.util.ThreadUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @Description: 出站流水线演示
 * @Author: shanjian
 * @Date: 2022/10/18 10:50 上午
 */
@Slf4j
public class OutPipeline {

    public class SimpleOutHandlerA extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("出站处理器A：被回调write() ctx:{} msg:{} promise{}", JSON.toJSONString(ctx), JSON.toJSONString(msg), JSON.toJSONString(promise));
            super.write(ctx, msg, promise);
        }

        @Override
        public void flush(ChannelHandlerContext ctx) throws Exception {
            log.info("出站处理器A：被回调flush() ctx:{} ", ctx);
            super.flush(ctx);
        }
    }

    public class SimpleOutHandlerB extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("出站处理器B：被回调write() ctx:{} msg:{} promise{}", JSON.toJSONString(ctx), JSON.toJSONString(msg), JSON.toJSONString(promise));
            super.write(ctx, msg, promise);
        }

        @Override
        public void flush(ChannelHandlerContext ctx) throws Exception {
            log.info("出站处理器B：被回调flush() ctx:{} ", ctx);
            super.flush(ctx);
        }
    }


    public class SimpleOutHandlerC extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("出站处理器C：被回调write() ctx:{} msg:{} promise{}", JSON.toJSONString(ctx), JSON.toJSONString(msg), JSON.toJSONString(promise));
            super.write(ctx, msg, promise);
        }

        @Override
        public void flush(ChannelHandlerContext ctx) throws Exception {
            log.info("出站处理器C：被回调flush() ctx:{} ", ctx);
            super.flush(ctx);
        }
    }

    /**
     * 2022-10-18 10:59:43.201 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器C：被回调write() ctx:{"removed":false} msg:{"direct":false,"readOnly":false,"readable":true,"writable":true} promise{"cancellable":true,"cancelled":false,"done":false,"success":false,"void":false}
     * 2022-10-18 10:59:43.202 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器B：被回调write() ctx:{"removed":false} msg:{"direct":false,"readOnly":false,"readable":true,"writable":true} promise{"cancellable":true,"cancelled":false,"done":false,"success":false,"void":false}
     * 2022-10-18 10:59:43.203 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器A：被回调write() ctx:{"removed":false} msg:{"direct":false,"readOnly":false,"readable":true,"writable":true} promise{"cancellable":true,"cancelled":false,"done":false,"success":false,"void":false}
     * 2022-10-18 10:59:43.219 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] WRITE: 4B
     *          +-------------------------------------------------+
     *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 00 00 00 58                                     |...X            |
     * +--------+-------------------------------------------------+----------------+
     * 2022-10-18 10:59:43.220 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxCapacityPerThread: 4096
     * 2022-10-18 10:59:43.220 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxSharedCapacityFactor: 2
     * 2022-10-18 10:59:43.220 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.linkCapacity: 16
     * 2022-10-18 10:59:43.220 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.ratio: 8
     * 2022-10-18 10:59:43.221 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器C：被回调flush() ctx:ChannelHandlerContext(OutPipeline$SimpleOutHandlerC#0, [id: 0xembedded, L:embedded - R:embedded])
     * 2022-10-18 10:59:43.221 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器B：被回调flush() ctx:ChannelHandlerContext(OutPipeline$SimpleOutHandlerB#0, [id: 0xembedded, L:embedded - R:embedded])
     * 2022-10-18 10:59:43.222 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器A：被回调flush() ctx:ChannelHandlerContext(OutPipeline$SimpleOutHandlerA#0, [id: 0xembedded, L:embedded - R:embedded])
     * 2022-10-18 10:59:43.222 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] FLUSH
     *
     * 出站执行次数 C ——>B ——>A
     */
    @Test
    public void testPipelineOutBound() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                ch.pipeline().addLast(new SimpleOutHandlerA());
                ch.pipeline().addLast(new SimpleOutHandlerB());
                ch.pipeline().addLast(new SimpleOutHandlerC());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(88);
        channel.writeAndFlush(buffer);
        ThreadUtil.sleepSeconds(10);
    }


    public class SimpleOutHandlerB2 extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("出站处理器B2：被回调write() ctx:{} msg:{} promise{}", JSON.toJSONString(ctx), JSON.toJSONString(msg), JSON.toJSONString(promise));
//            // 不调用基类的write,终止流水线的执行
//            super.write(ctx, msg, promise);
        }

        @Override
        public void flush(ChannelHandlerContext ctx) throws Exception {
            log.info("出站处理器B2：被回调flush() ctx:{} ", ctx);
            super.flush(ctx);
        }
    }


    /**
     * 2022-10-18 11:11:28.537 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] WRITE: 19B
     *          +-------------------------------------------------+
     *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 68 65 6c 6c 6f ef bc 8c 78 69 61 6f 20 79 75 6e |hello...xiao yun|
     * |00000010| 20 7a 69                                        | zi             |
     * +--------+-------------------------------------------------+----------------+
     * 2022-10-18 11:11:28.620 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器C：被回调write() ctx:{"removed":false} msg:{"direct":false,"readOnly":false,"readable":true,"writable":true} promise{"cancellable":true,"cancelled":false,"done":false,"success":false,"void":false}
     * 2022-10-18 11:11:28.621 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器B2：被回调write() ctx:{"removed":false} msg:{"direct":false,"readOnly":false,"readable":true,"writable":true} promise{"cancellable":true,"cancelled":false,"done":false,"success":false,"void":false}
     * 2022-10-18 11:11:28.622 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] FLUSH
     * 2022-10-18 11:11:28.622 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器C：被回调flush() ctx:ChannelHandlerContext(SimpleOutHandlerC, [id: 0xembedded, L:embedded - R:embedded])
     * 2022-10-18 11:11:28.622 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器B2：被回调flush() ctx:ChannelHandlerContext(OutPipeline$SimpleOutHandlerB2#0, [id: 0xembedded, L:embedded - R:embedded])
     * 2022-10-18 11:11:28.622 [main] INFO  com.janson.netty.demos.pipeline.OutPipeline - 出站处理器A：被回调flush() ctx:ChannelHandlerContext(OutPipeline$SimpleOutHandlerA#0, [id: 0xembedded, L:embedded - R:embedded])
     *B2 不调用基类的write,终止流水线的执行
     *
     * 出站执行次数 C ——>B2 ----->A[不会执行A的write操作，但是flush还是会执行的]
     *
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testPipelineOutBoundCutting() throws UnsupportedEncodingException {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleOutHandlerA());
                ch.pipeline().addLast(new SimpleOutHandlerB2());
                ch.pipeline().addLast("SimpleOutHandlerC",new SimpleOutHandlerC());
                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        ByteBuf buffer = Unpooled.buffer();
        String msg = "hello，xiao yun zi";
        byte[] bytes = msg.getBytes("utf-8");
        buffer.writeBytes(bytes);
        channel.writeAndFlush(buffer);
        ThreadUtil.sleepSeconds(10);
    }

}
