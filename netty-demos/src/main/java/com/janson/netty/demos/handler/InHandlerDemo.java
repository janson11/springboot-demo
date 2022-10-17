package com.janson.netty.demos.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 *
 * 2022-10-17 17:12:37.142 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：handlerAdded()
 * 2022-10-17 17:12:37.143 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：channelRegistered()
 * 2022-10-17 17:12:37.144 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：channelActive()
 * 2022-10-17 17:12:37.161 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：channelRead()
 * 2022-10-17 17:12:37.162 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：channelReadComplete()
 * 2022-10-17 17:12:37.162 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：channelInactive()
 * 2022-10-17 17:12:37.162 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：channelUnregistered()
 * 2022-10-17 17:12:37.162 [main] INFO  com.janson.netty.demos.handler.InHandlerDemo - 被调用：handlerRemoved()
 *
 * 从测试用例执行结果看到
 * ChannelHandler中的回调方法的执行顺序为
 * handlerAdded() ->channelRegistered() ->channelActive()->channelRead()->channelReadComplete()->channelInactive()->channelUnregistered()->handlerRemoved()
 *
 *
 * @Author: shanjian
 * @Date: 2022/10/17 3:53 下午
 */
@ChannelHandler.Sharable
@Slf4j
public class InHandlerDemo extends ChannelInboundHandlerAdapter {


    /**
     * 当通道成功绑定一个NioEventLoop线程后，会通过流水线回调所有业务处理器的channelRegistered()方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用：channelRegistered()");
        super.channelRegistered(ctx);
    }

    /**
     * 通道和NioEventLoop线程解除绑定，移除掉对这条通道的事件处理之后，回调所有业务处理器的channelUnregistered()方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用：channelUnregistered()");
        super.channelUnregistered(ctx);
    }

    /**
     * 当通道被激活成功后，会通过流水线回调所有业务处理器的channelActive()方法，通道激活成功指的是，所有的业务处理器添加
     * 、注册的异步任务完成，并且NioEventLoop线程绑定的异步任务完成。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用：channelActive()");
        super.channelActive(ctx);
    }

    /**
     * 当通道的底层连接已经部署ESTABLISH状态或者底层连接已经关闭时，会首先回调所有业务处理器的channelInactive()方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用：channelInactive()");
        super.channelInactive(ctx);
    }

    /**
     * 有数据包入站，通道可读。流水线会启动入站处理流程，从前向后，入站处理器的channelRead()会被依次回调到。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("被调用：channelRead()");
        super.channelRead(ctx, msg);
    }

    /**
     * 流水线完成入站处理后，会从前向后，依次回调每个入站处理器的channelReadComplete()方法，表示数据读取完毕。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用：channelReadComplete()");
        super.channelReadComplete(ctx);
    }

    /**
     * 当业务处理器被加入到流水线后，此方法被回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用：handlerAdded()");
        super.handlerAdded(ctx);
    }

    /**
     * Netty会移除掉通道上所有的业务处理器，并且回调所有的业务处理器的handlerRemoved()方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("被调用：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
