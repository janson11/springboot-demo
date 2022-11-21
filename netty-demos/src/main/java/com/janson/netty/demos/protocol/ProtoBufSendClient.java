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
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/21 11:07 上午
 */
public class ProtoBufSendClient {

    public static void main(String[] args) {
        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
        String ip = NettyDemoConfig.SOCKET_SERVER_IP;
        new ProtoBufSendClient(ip,port).runClient();
    }

    static String content = "疯狂创客圈：高性能学习社群";

    private int serverPort;
    private String serverIp;

    Bootstrap b = new Bootstrap();

    public ProtoBufSendClient( String serverIp,int serverPort) {
        this.serverPort = serverPort;
        this.serverIp = serverIp;
    }


    public void runClient() {
        // 创建reactor线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            b.group(workerLoopGroup);

            b.channel(NioSocketChannel.class);

            b.remoteAddress(serverIp,serverPort);

            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 客户端channel流水线添加2个handler处理器
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder());
                }
            });
            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture futureListener) ->{
                if (futureListener.isSuccess()) {
                    Logger.info("ProtoBufSendClient客户端连接成功！");
                } else {
                    Logger.info("ProtoBufSendClient客户端连接失败！");
                }
            });

            //阻塞，直到连接成功
            f.sync();

            Channel channel = f.channel();
            for (int i = 0; i < 1000; i++) {
                MsgProtos.Msg msg =build(i,i+"->"+content);
                channel.writeAndFlush(msg);
                Logger.info("发送报文数:"+i);
            }

            channel.flush();

            ChannelFuture closeFuture = channel.closeFuture();
            closeFuture.sync();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            workerLoopGroup.shutdownGracefully();
        }
    }

    public MsgProtos.Msg build(int id,String content) {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(id);
        builder.setContent(content);
        return builder.build();
    }
}
