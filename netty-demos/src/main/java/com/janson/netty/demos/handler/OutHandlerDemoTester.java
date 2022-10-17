package com.janson.netty.demos.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/17 5:38 下午
 */
public class OutHandlerDemoTester {

    @Test
    public void testliefCircle() {
        final OutHandlerDemo outHandlerDemo = new OutHandlerDemo();
        ChannelInitializer channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(outHandlerDemo);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        // 测试出站写入
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);

        ChannelFuture channelFuture = channel.pipeline().writeAndFlush(buf);
        channelFuture.addListener((future) -> {
            if (future.isSuccess()) {
                System.out.println("write is finished");
            }
            channel.close();
        });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
