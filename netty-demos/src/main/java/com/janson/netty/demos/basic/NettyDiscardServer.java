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
 *
 * @see ChannelOption 通道选项
 * 1、SO_RCVBUF SO_SEDBUF
 * 此为TCP参数，每个TCP socket套接字在内核中都有一个发送缓冲区和一个接收缓冲区，这两个选项就是用来设置TCP连接的这两个缓冲区的大小的。
 * TCP的全双工的工作模式以及TCP的滑动窗口便是依赖于这两个独立的缓冲区及其填充的状态
 *
 * 2、TCP_NODELAY
 * 此为TCP参数，表示立即发送数据。默认值为true（Netty默认为true，而操作系统默认为false）。该值用于设置Nagle算法的启用，该算法将
 * 小的碎片数据连接成更大的报文(或数据包)来最小化所发送报文的数量，如果需要发送一些较小的报文，则需要禁用改算法。Netty默认禁用该
 * 算法，从而最小化报文传输的延时。
 *
 * 3、SO_KEEPALIVE
 * 此为TCP参数，表示底层TCP协议的心跳机制。true为连接保持心跳，默认为false。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此
 * 功能视为TCP的心跳机制。需要注意的是：默认的心跳间隔是7200s即2个小时。Netty默认关闭该功能。
 *
 * 4、SO_REUSEADDR
 * 此为TCP参数，设置true表示地址复用，默认值为flase。
 *
 * 5、SO_LINGER
 * 此为TCP参数，表示关闭socket的延迟时间。默认为-1，表示禁用该功能。
 *
 * 6、SO_BACKLOG
 * 此为TCP参数。表示服务器端接收连接的队列长度，如果队列已满，客户端连接将被拒绝。默认值，在Windows中为200，其他操作系统为128。
 * 如果连接建立频繁，服务器处理新连接较慢，可以适当调大这个参数
 *
 * 7、SO_BROADCAST
 * 此为TCP参数，表示设置广播模式
 *
 *
 *
 * @Author: shanjian
 * @Date: 2022/10/12 4:07 下午
 */
@Slf4j
public class NettyDiscardServer {

    private final int serverPort;

    // 创建一个服务器端的启动器
    ServerBootstrap b = new ServerBootstrap();

    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {
        //创建reactor 线程组【反应器】
        // boos线程组 包工头：负责服务器通道新连接的IO事件的监听
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        // worker线程组 工人：负责传输通道的IO时事件的处理。
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            // 1、设置reactor线程组【反应器】
            b.group(bossLoopGroup, workerLoopGroup);
            // 2、设置nio类型的channel
            b.channel(NioServerSocketChannel.class);
            // 3、设置监听端口
            b.localAddress(serverPort);
            // 4、设置通道的参数
            // ChannelOption.SO_KEEPALIVE 是否开启了TCP底层心跳机制，true为开启
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.TCP_NODELAY, true);

            //5、装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                // 有连接到达时会创建一个通道的子通道，并初始化
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline 管理子通道channel中的Handler
                    // 向子channel流水线添加一个handler业务处理器 ，实现IO事件的业务处理
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
            closeFuture.sync();
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
