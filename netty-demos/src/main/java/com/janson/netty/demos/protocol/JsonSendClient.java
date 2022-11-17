package com.janson.netty.demos.protocol;

import com.janson.netty.common.util.Logger;
import com.janson.netty.demos.NettyDemoConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/17 6:09 下午
 */
public class JsonSendClient {
    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        String ip = NettyDemoConfig.SOCKET_SERVER_IP;
        new JsonSendClient(ip, port).runClient();
    }


    static String conetent  = "疯狂创客圈：高性能学习社群";

    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public JsonSendClient(String serverIp,int serverPort) {
        this.serverPort = serverPort;
        this.serverIp = serverIp;
    }

    public void runClient() {
        //创建reactor线程
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            // 1 设置reactor线程组
            b.group(workerLoopGroup);
            //2 、设置nio类型的channel
            b.channel(NioSocketChannel.class);
            // 3、设置监听端口
            b.remoteAddress(serverIp,serverPort);
            // 4、设置通道的参数
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            // 5、装配通道流水线
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //客户端channel流水线添加2个handler处理器
                    ch.pipeline().addLast(new LengthFieldPrepender(4));
                    ch.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new JsonMsgEncoder());
                }
            });

            ChannelFuture f = b.connect();
            f.addListener( (ChannelFuture channelFuture) ->{
               if (channelFuture.isSuccess()){
                   Logger.info("Json Send Client 客户端连接成功！");
               }else {
                   Logger.info("Json Send Client 客户端连接失败！");
               }
            });

            // 阻塞，直到连接完成
            f.sync();

            Channel channel = f.channel();

            // 发送字符串对象
            for (int i = 0; i < 1000; i++) {
                JsonMsg user = build(i, i + "->" + conetent);
                channel.writeAndFlush(user);
            }

            channel.flush();

            //7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channel.closeFuture();
            closeFuture.sync();
        }catch (Exception e) {
            e.printStackTrace();

        } finally {
            //优雅关闭  EventLoopGroup
            // 释放   掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }
    }

    public JsonMsg build(int id,String content) {
        JsonMsg msg = new JsonMsg();
        msg.setId(id);
        msg.setContent(content);
        return msg;
    }
}
