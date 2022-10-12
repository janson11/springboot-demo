package com.janson.netty.demos.basic;

import com.janson.netty.demos.NettyDemoConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/12 4:07 下午
 */
@Slf4j
public class NettyDiscardServer {

    private final int serverPort;

    ServerBootstrap b = new ServerBootstrap();

    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {
        //创建reactor 线程组
        // 包工头：负责服务器通道新连接的IO事件的监听
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        // 工人：负责传输通道的IO时事件的处理。
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            // 1、设置reactor线程组
            b.group(bossLoopGroup, workerLoopGroup);
            // 2、设置nio类型的channel
            b.channel(NioServerSocketChannel.class);
            // 3、设置监听端口
            b.localAddress(serverPort);
            // 4、设置通道的参数
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.TCP_NODELAY, true);

            //5、装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                // 有连接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline 管理子通道channel中的Handler
                    // 向子channel流水线添加一个handler处理器 ，实现IO事件的业务处理
                    ch.pipeline().addLast(new NettyDiscardHandler());
                }
            });

            // 6、开始绑定server
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = b.bind();
            channelFuture.addListener(l -> {
                if (l.isSuccess()) {
                    log.info("服务器端口绑定正常:{}", channelFuture.channel().localAddress());
                } else {
                    log.info("服务器端口绑定失败:{}", channelFuture.channel().localAddress());
                }
            });

            channelFuture.sync();

            log.info("服务器启动成功，监听端口:{}", channelFuture.channel().localAddress());

            //7、等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            channelFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8 优雅关闭EventLoopGroup
            // 释放所有的资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        new NettyDiscardServer(port).runServer();
    }


}
