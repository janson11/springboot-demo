package com.janson.netty.demos.echoServer;

import com.janson.netty.common.util.Dateutil;
import com.janson.netty.demos.NettyDemoConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/27 10:40 上午
 */
@Slf4j
public class NettyDumpSendClient {
    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public NettyDumpSendClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void runClient() {
        //创建reactor线程
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        try {
            // 1、设置reactor线程组
            b.group(workerLoopGroup);
            //2.设置nio类型的channel
            b.channel(NioSocketChannel.class);
            //3.设置监听端口
            b.remoteAddress(serverIp, serverPort);
            //4、设置通道的参数
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

            // 5、装配通道流水线
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });

            ChannelFuture f =b.connect();
            f.addListener((ChannelFuture channelFuture) -> {
                if (channelFuture.isSuccess()) {
                    log.info("EchoClient客户端连接成功！");
                } else {
                    log.info("EchoClient客户端连接失！");
                }

            });

            //阻塞 ，直到连接完成
            f.sync();
            Channel channel = f.channel();

            // 6、发送大量的文字
            byte[] bytes = "疯狂创客圈：高性能学习社群!".getBytes(Charset.forName("UTF-8"));
            for (int i = 0; i < 1000; i++) {
                // 发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
            }

            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture channelFuture = channel.closeFuture();
            channelFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        String ip = NettyDemoConfig.SOCKET_SERVER_IP;
        new NettyDumpSendClient(ip, port).runClient();
    }
}