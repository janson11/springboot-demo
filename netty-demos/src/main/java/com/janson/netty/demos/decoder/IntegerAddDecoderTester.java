package com.janson.netty.demos.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/1 10:46 上午
 */
public class IntegerAddDecoderTester {

    @Test
    public void testByteToIntegerDecoder() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
                channel.pipeline().addLast(new IntegerAddDecoder());
                channel.pipeline().addLast(new IntegerProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 100; i++) {
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(i);
            channel.writeInbound(buf);
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
