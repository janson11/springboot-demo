package com.janson.netty.demos.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/9 10:31 上午
 */
public class String2IntegerEncoderTester {

    /**
     * 测试字符串到整数编码器
     */
    @Test
    public void testStringToIntegerDecoder() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                // 出站处理的过程是从后向前的次序
                // String2IntegerEncoder 负责将字符串编码成整数。
                // Integer2ByteEncoder 将整数进一步变成ByteBuf数据包
                ch.pipeline().addLast(new Integer2ByteEncoder());
                ch.pipeline().addLast(new String2IntegerEncoder());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 100; i++) {
            String s = "i am "+i;
            channel.write(s);
        }
        channel.flush();

        ByteBuf buf  = (ByteBuf)channel.readOutbound();

        while (null!=buf){
            System.out.println("o = "+buf.readInt());
            buf  = (ByteBuf)channel.readOutbound();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
