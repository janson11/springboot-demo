package com.janson.netty.demos.pipeline;

import com.janson.netty.common.util.ThreadUtil;
import com.janson.netty.demos.handler.OutHandlerDemo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description: 入站流水线演示
 * @Author: shanjian
 * @Date: 2022/10/17 5:50 下午
 */
@Slf4j
public class InPipeline {

    static class SimpleInHandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器A ：被回调channelRead()");
            super.channelRead(ctx, msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            log.info("入站处理器A：被回调channelReadComplete()");
            super.channelReadComplete(ctx);
            // 入站操作的传播
//            ctx.fireChannelReadComplete();
        }
    }

    static class SimpleInHandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器B ：被回调channelRead()");
            super.channelRead(ctx, msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            log.info("入站处理器B：被回调channelReadComplete()");
            super.channelReadComplete(ctx);
            // 入站操作的传播
//            ctx.fireChannelReadComplete();
        }
    }

    static class SimpleInHandlerC extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器C ：被回调channelRead()");
            super.channelRead(ctx, msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            log.info("入站处理器C：被回调channelReadComplete()");
            super.channelReadComplete(ctx);
            // 入站操作的传播
//            ctx.fireChannelReadComplete();
        }
    }


    /**
     *          +-------------------------------------------------+
     *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 00 00 00 0f                                     |....            |
     * +--------+-------------------------------------------------+----------------+
     * 2022-10-18 10:31:33.938 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器A ：被回调channelRead()
     * 2022-10-18 10:31:33.939 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器B ：被回调channelRead()
     * 2022-10-18 10:31:33.940 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器C ：被回调channelRead()
     * 2022-10-18 10:31:33.940 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ COMPLETE
     * 2022-10-18 10:31:33.940 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器A：被回调channelReadComplete()
     * 2022-10-18 10:31:33.940 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器B：被回调channelReadComplete()
     * 2022-10-18 10:31:33.940 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器C：被回调channelReadComplete()
     *
     *
     * 我们可以看到，入站处理器的流动次序是：从前到后。加在前面的，执行也在前面。
     *
     */
    @Test
    public void testPipelineInBound() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                ch.pipeline().addLast(new SimpleInHandlerA());
                ch.pipeline().addLast(new SimpleInHandlerB());
                ch.pipeline().addLast(new SimpleInHandlerC());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(15);
        // 向通道写一个入站报文
        channel.writeInbound(buf);
        ThreadUtil.sleepSeconds(Integer.MAX_VALUE);
    }



    static class SimpleInHandlerB2 extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器B2 ：被回调channelRead()");
            // 不调用基类的channelRead，终止流水线的执行
//            super.channelRead(ctx, msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            log.info("入站处理器B2：被回调channelReadComplete()");
//            super.channelReadComplete(ctx);
            // 入站操作的传播
            ctx.fireChannelReadComplete();
        }
    }


    /**
     * 测试流水线的截断
     *
     *          +-------------------------------------------------+
     *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 00 00 00 10                                     |....            |
     * +--------+-------------------------------------------------+----------------+
     * 2022-10-18 10:36:15.536 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器A ：被回调channelRead()
     * 2022-10-18 10:36:15.537 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器B2 ：被回调channelRead()
     * 2022-10-18 10:36:15.537 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ COMPLETE
     * 2022-10-18 10:36:15.538 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器A：被回调channelReadComplete()
     * 2022-10-18 10:36:15.538 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器B2：被回调channelReadComplete()
     * 2022-10-18 10:36:15.538 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器C：被回调channelReadComplete()
     *
     *
     */
    @Test
    public void testPipelineCutting() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                ch.pipeline().addLast(new SimpleInHandlerA());
                ch.pipeline().addLast(new SimpleInHandlerB2());
                ch.pipeline().addLast(new SimpleInHandlerC());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(16);
        // 向通道写一个入站报文
        channel.writeInbound(buf);
        ThreadUtil.sleepSeconds(Integer.MAX_VALUE);
    }


    static class SimpleInHandlerB3 extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器B3 ：被回调channelRead()");

            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(17);
            ctx.writeAndFlush(buf);
            // 不调用基类的channelRead，终止流水线的执行
//            super.channelRead(ctx, msg);
        }

    }


    /**
     * 测试不同输出方式，不同出站方式
     * 2022-10-18 10:47:17.583 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 handlerAdded()
     * 2022-10-18 10:47:17.586 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 handlerAdded()
     * 2022-10-18 10:47:17.588 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] REGISTERED
     * 2022-10-18 10:47:17.588 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] ACTIVE
     * 2022-10-18 10:47:17.588 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 read()
     * 2022-10-18 10:47:17.588 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 read()
     * 2022-10-18 10:47:17.596 [main] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkAccessible: true
     * 2022-10-18 10:47:17.596 [main] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkBounds: true
     * 2022-10-18 10:47:17.597 [main] DEBUG io.netty.util.ResourceLeakDetectorFactory - Loaded default ResourceLeakDetector: io.netty.util.ResourceLeakDetector@5cdd8682
     * 2022-10-18 10:47:17.614 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ: 4B
     *          +-------------------------------------------------+
     *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 00 00 00 12                                     |....            |
     * +--------+-------------------------------------------------+----------------+
     * 2022-10-18 10:47:17.614 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器A ：被回调channelRead()
     * 2022-10-18 10:47:17.614 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器B3 ：被回调channelRead()
     * 2022-10-18 10:47:17.614 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 write()
     * 2022-10-18 10:47:17.614 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] WRITE: 4B
     *          +-------------------------------------------------+
     *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 00 00 00 11                                     |....            |
     * +--------+-------------------------------------------------+----------------+
     * 2022-10-18 10:47:17.615 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxCapacityPerThread: 4096
     * 2022-10-18 10:47:17.615 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxSharedCapacityFactor: 2
     * 2022-10-18 10:47:17.615 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.linkCapacity: 16
     * 2022-10-18 10:47:17.615 [main] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.ratio: 8
     * 2022-10-18 10:47:17.616 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 flush()
     * 2022-10-18 10:47:17.616 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] FLUSH
     * 2022-10-18 10:47:17.617 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ COMPLETE
     * 2022-10-18 10:47:17.617 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器A：被回调channelReadComplete()
     * 2022-10-18 10:47:17.617 [main] INFO  com.janson.netty.demos.pipeline.InPipeline - 入站处理器C：被回调channelReadComplete()
     * 2022-10-18 10:47:17.617 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 read()
     * 2022-10-18 10:47:17.617 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 read()
     *
     */
    @Test
    public void testMultiplyOutput() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            OutHandlerDemo outHandlerDemo = new OutHandlerDemo();
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                ch.pipeline().addLast(new SimpleInHandlerA());
                ch.pipeline().addLast(outHandlerDemo);
                ch.pipeline().addLast(new SimpleInHandlerB3());
                ch.pipeline().addLast(new SimpleInHandlerC());
                ch.pipeline().addLast(outHandlerDemo);
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(18);
        // 向通道写一个入站报文
        channel.writeInbound(buf);
        ThreadUtil.sleepSeconds(Integer.MAX_VALUE);
    }







}
