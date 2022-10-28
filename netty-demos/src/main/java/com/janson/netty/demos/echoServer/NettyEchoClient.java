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
import org.apache.http.client.utils.DateUtils;
import org.assertj.core.util.DateUtil;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/27 10:40 上午
 */
@Slf4j
public class NettyEchoClient {
    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public NettyEchoClient(String serverIp, int serverPort) {
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

            ChannelFuture f = null;

            boolean connected = false;
            while (!connected) {
                f = b.connect();
                f.addListener((ChannelFuture channelFuture) -> {
                    if (channelFuture.isSuccess()) {
                        log.info("EchoClient客户端连接成功！");
                    } else {
                        log.info("EchoClient客户端连接失！");
                    }

                });

                f.awaitUninterruptibly();
                if (f.isCancelled()) {
                    log.info("用户取消连接：");
                    return;
                } else if (f.isSuccess()) {
                    connected = true;
                }
            }

            Channel channel = f.channel();
            Scanner scanner = new Scanner(System.in);
            log.info("请输入发送内容");
            GenericFutureListener sendCallBack = new GenericFutureListener() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    if (future.isSuccess()) {
                        log.info("发送成功！");
                    } else {
                        log.info("发送失败！");
                    }
                }
            };

            while (scanner.hasNext()) {
                String next = scanner.next();
                byte[] bytes = (Dateutil.getNow() + " >>" + next).getBytes(StandardCharsets.UTF_8);
                //发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                ChannelFuture writeAndFlush = channel.writeAndFlush(buffer);
                writeAndFlush.addListener(sendCallBack);
                log.info("请输入发送内容");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        String ip = NettyDemoConfig.SOCKET_SERVER_IP;
        new NettyEchoClient(ip, port).runClient();
    }
}