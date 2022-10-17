package com.janson.netty.demos.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * @Description:
 *
 * 2022-10-17 17:45:03.259 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 handlerAdded()
 * 2022-10-17 17:45:03.260 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 read()
 * 2022-10-17 17:45:03.273 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 write()
 * 2022-10-17 17:45:03.276 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 flush()
 * write is finished
 * 2022-10-17 17:45:03.346 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 close()
 * 2022-10-17 17:45:03.346 [main] INFO  com.janson.netty.demos.handler.OutHandlerDemo - 被调用 handlerRemoved()
 *
 *
 * @Author: shanjian
 * @Date: 2022/10/17 5:35 下午
 */
@ChannelHandler.Sharable
@Slf4j
public class OutHandlerDemo extends ChannelOutboundHandlerAdapter {
    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        log.info("被调用bind()");
        super.bind(ctx, localAddress, promise);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        log.info("被调用 connect()");
        super.connect(ctx, remoteAddress, localAddress, promise);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        log.info("被调用 disconnect()");
        super.disconnect(ctx, promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        log.info("被调用 close()");
        super.close(ctx, promise);
    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        log.info("被调用 deregister()");
        super.deregister(ctx, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用 read()");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("被调用 write()");
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用 flush()");
        super.flush(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用 handlerAdded()");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用 handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
