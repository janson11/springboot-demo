package com.janson.netty.demos.protocol;

import com.janson.netty.common.util.Logger;
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
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

import java.util.Optional;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/21 10:45 上午
 */
public class ProtoBufServer {

    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        new ProtoBufServer(port).runServer();
    }

    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public ProtoBufServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {
        // 创建reactor线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            // 1、设置reactor线程组
            b.group(bossLoopGroup,workerLoopGroup);

            // 2、设置nio类型的channel
            b.channel(NioServerSocketChannel.class);

            //3、设置监听端口
            b.localAddress(serverPort);

            //4、设置通道的参数
            b.option(ChannelOption.SO_KEEPALIVE,true);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //5 、装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理子通道channel中的Handler
                    // 向子channel流水线添加3个handler处理器

                    // protobufDecoder仅仅负责编码，并不支持读半包，所以在之前，一定要有读半包的处理器。
                    // 有三种方式可以选择：
                    // 使用netty提供ProtobufVarint32FrameDecoder
                    // 继承netty提供的通用半包处理器 lengthFieldBasedFrameDecoder
                    // 继承ByteToMessageDecoder类，自己处理半包

                    // 半包的处理
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    // 需要解码的目标类
                    ch.pipeline().addLast(new ProtobufDecoder(MsgProtos.Msg.getDefaultInstance()));
                    ch.pipeline().addLast(new ProtobufBussinessHandler());
                }
            });

            // 6、开始绑定server
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = b.bind().sync();
            Logger.info("服务器启动成功，监听端口:"+channelFuture.channel().localAddress());

            // 7、等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8、优雅关闭EventLoopGroup
            // 释放   掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }

    }
}
