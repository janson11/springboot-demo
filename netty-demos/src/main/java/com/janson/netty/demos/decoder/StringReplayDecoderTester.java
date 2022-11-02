package com.janson.netty.demos.decoder;

import com.janson.netty.common.util.RandomUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @Description: 字符串分包演示示例
 * @Author: shanjian
 * @Date: 2022/11/2 10:40 上午
 */
public class StringReplayDecoderTester {

    static String context = "疯狂创客圈，高性能学习社群!";

    @Test
    public void testStringReplayDecoder() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new StringReplayDecoder());
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        byte[] bytes = context.getBytes(Charset.forName("UTF-8"));
        for (int i = 0; i < 100; i++) {
            int random = RandomUtil.randInMod(3);
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(bytes.length+random);
            for (int j = 0; j < random; j++) {
                buf.writeBytes(bytes);
            }
            channel.writeInbound(buf);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
