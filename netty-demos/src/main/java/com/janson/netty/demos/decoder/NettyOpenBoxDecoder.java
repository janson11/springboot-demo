package com.janson.netty.demos.decoder;

import com.janson.netty.common.util.RandomUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/11/7 10:38 上午
 */
public class NettyOpenBoxDecoder {


    public static final int MAGICCODE = 9999;
    public static final int VERSION = 100;
    public static final String SPLITER = "\r\n";
    public static final String SPLITER_2 = "\t";

    public static final String CONTENT = "疯狂创客圈：高性能学习社群";

    /**
     * 行分割数据包解码器
     *
     * @see io.netty.handler.codec.LineBasedFrameDecoder
     */
    @Test
    public void testLineBasedDecoder() {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 100; i++) {
            int random = RandomUtil.randInMod(3);
            ByteBuf buf = Unpooled.buffer();
            for (int j = 0; j < random; j++) {
                buf.writeBytes(CONTENT.getBytes(StandardCharsets.UTF_8));
            }
            buf.writeBytes(SPLITER.getBytes(StandardCharsets.UTF_8));
            channel.writeInbound(buf);
        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 自定义分隔符数据包解码器
     *
     * @see io.netty.handler.codec.DelimiterBasedFrameDecoder
     */
    @Test
    public void testDelimiterBasedFrameDecoder() {
        ByteBuf delimiter = Unpooled.copiedBuffer(SPLITER_2.getBytes(StandardCharsets.UTF_8));
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, true, delimiter));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 3; i++) {
            int random = RandomUtil.randInMod(3);
            ByteBuf buf = Unpooled.buffer();
            for (int j = 0; j < random; j++) {
                buf.writeBytes(CONTENT.getBytes(StandardCharsets.UTF_8));
            }
            buf.writeBytes(SPLITER_2.getBytes(StandardCharsets.UTF_8));
            channel.writeInbound(buf);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 自定义长度数据包解码器
     *
     * @see io.netty.handler.codec.LengthFieldBasedFrameDecoder
     */
    @Test
    public void testLengthFieldBasedFrameDecoder() throws UnsupportedEncodingException {
        // 定义一个基于长度域解码器
        final LengthFieldBasedFrameDecoder decoder = new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4);
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(decoder);
                ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 0; i < 100; i++) {
            int random = RandomUtil.randInMod(3);
            ByteBuf buf = Unpooled.buffer();
            byte[] bytes = CONTENT.getBytes("UTF-8");
            // 首先，写入头部，head 也就是说后面的数据长度
            buf.writeInt(bytes.length * random);


            // 然后写入content
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


    /**
     * 自定义长度数据包解码器
     *   int maxFrameLength, 发送的数据包的最大长度
     *   int lengthFieldOffset, 长度字段偏移量
     *   int lengthFieldLength,  长度字段所占的字节数
     *   int lengthAdjustment,  长度的矫正值
     *   int initialBytesToStrip 丢弃的起始字节数
     *
     *
     *
     * @see io.netty.handler.codec.LengthFieldBasedFrameDecoder
     */
    @Test
    public void testLengthFieldBasedFrameDecoder1() throws UnsupportedEncodingException {
        // 定义一个基于长度域解码器
        final LengthFieldBasedFrameDecoder decoder = new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4);
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(decoder);
                ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 1; i <= 100; i++) {
            ByteBuf buf = Unpooled.buffer();
            String s = i + "次发送->" + CONTENT;
            byte[] bytes = s.getBytes("UTF-8");
            // 首先，写入头部，head 也就是说后面的数据长度
            buf.writeInt(bytes.length);
            System.out.println("bytes length= " + bytes.length);
            buf.writeBytes(bytes);
            channel.writeInbound(buf);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 自定义长度数据包解码器
     *   int maxFrameLength, 发送的数据包的最大长度
     *   int lengthFieldOffset, 长度字段偏移量
     *   int lengthFieldLength,  长度字段所占的字节数
     *   int lengthAdjustment,  长度的矫正值
     *   int initialBytesToStrip 丢弃的起始字节数
     *
     *
     *
     * @see io.netty.handler.codec.LengthFieldBasedFrameDecoder
     */
    @Test
    public void testLengthFieldBasedFrameDecoder2() throws UnsupportedEncodingException {
        // 定义一个基于长度域解码器
        final LengthFieldBasedFrameDecoder spliter = new LengthFieldBasedFrameDecoder(1024, 0, 4, 2, 6);
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(spliter);
                ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 1; i <= 100; i++) {
            ByteBuf buf = Unpooled.buffer();
            String s = i + "次发送->" + CONTENT;
            byte[] bytes = s.getBytes("UTF-8");
            // 首先，写入头部，head 也就是说后面的数据长度
            buf.writeChar(VERSION);
            buf.writeInt(bytes.length);
            System.out.println("bytes length= " + bytes.length);
            buf.writeBytes(bytes);
            channel.writeInbound(buf);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义长度数据包解码器
     *   int maxFrameLength, 发送的数据包的最大长度
     *   int lengthFieldOffset, 长度字段偏移量
     *   int lengthFieldLength,  长度字段所占的字节数
     *   int lengthAdjustment,  长度的矫正值
     *   int initialBytesToStrip 丢弃的起始字节数
     *
     *
     *
     * @see io.netty.handler.codec.LengthFieldBasedFrameDecoder
     */
    @Test
    public void testLengthFieldBasedFrameDecoder3() throws UnsupportedEncodingException {
        // 定义一个基于长度域解码器
        final LengthFieldBasedFrameDecoder spliter = new LengthFieldBasedFrameDecoder(1024, 2, 4, 4, 10);
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(spliter);
                ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);
        for (int i = 1; i <= 100; i++) {
            ByteBuf buf = Unpooled.buffer();
            String s = i + "次发送->" + CONTENT;
            byte[] bytes = s.getBytes("UTF-8");
            // 首先，写入头部，head 也就是说后面的数据长度
            buf.writeChar(VERSION);
            buf.writeInt(bytes.length);
            buf.writeInt(MAGICCODE);
            System.out.println("bytes length= " + bytes.length);
            buf.writeBytes(bytes);
            channel.writeInbound(buf);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
