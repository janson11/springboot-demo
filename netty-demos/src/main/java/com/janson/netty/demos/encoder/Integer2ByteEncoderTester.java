package com.janson.netty.demos.encoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/9 10:20 上午
 */
public class Integer2ByteEncoderTester {

    /**
     * 测试整数编码器
     */

    @Test
    public void testIntegerToByteDecoder() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                    ch.pipeline().addLast(new Integer2ByteEncoder());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 100; i++) {
            channel.writeAndFlush(i);
        }
        channel.flush();

        //取得通道的出站数据帧
        channel.readOutbound();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
