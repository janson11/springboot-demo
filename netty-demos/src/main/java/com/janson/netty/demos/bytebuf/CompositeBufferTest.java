package com.janson.netty.demos.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/26 9:51 上午
 */
@Slf4j
public class CompositeBufferTest {

    public static Charset utf8Code = Charset.forName("UTF-8");

    @Test
    public void initCompositeBufferComposite() {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer(3);
        compositeByteBuf.addComponent(Unpooled.wrappedBuffer(new byte[]{1, 2, 3}));
        compositeByteBuf.addComponent(Unpooled.wrappedBuffer(new byte[]{4}));
        compositeByteBuf.addComponent(Unpooled.wrappedBuffer(new byte[]{5, 6}));

        log.info("动作：addComponent ", compositeByteBuf);
        showMsg(compositeByteBuf);

        iterateMsg(compositeByteBuf);

        // 合并成一个单独的缓冲区
        ByteBuffer buffer = compositeByteBuf.nioBuffer(0, 6);

        byte[] bytes = buffer.array();
        log.info("bytes=");
        for (byte aByte : bytes) {
            System.out.println(aByte);

        }
        compositeByteBuf.release();
    }

    private void iterateMsg(CompositeByteBuf compositeByteBuf) {
        log.info("iterateMsg ........");
        Iterator<ByteBuf> it = compositeByteBuf.iterator();
        while (it.hasNext()) {
            ByteBuf buf = it.next();
            int length = buf.readableBytes();
            byte[] array = new byte[length];
            buf.getBytes(buf.readerIndex(), array);
            log.info(" iterateMsg conetent:{}", new String(array, utf8Code));
        }

        System.out.println("----------------------------------------------------------------");

        //处理整个消息
        for (ByteBuf buf : compositeByteBuf) {
            int length = buf.readableBytes();
            byte[] array = new byte[length];
            buf.getBytes(buf.readerIndex(), array);
            log.info("iterateMsg conetent for :{}", new String(array, utf8Code));

        }
    }


    private void showMsg(ByteBuf b) {
        log.info("showMsg ........");
        int length = b.readableBytes();
        byte[] array = new byte[length];
        // 将CompositeByteBuf中的数据复制到数组中
        b.getBytes(b.readerIndex(), array);
        log.info("showMsg conetent:{}", new String(array, utf8Code));
    }


    @Test
    public void byteBufComposite() {
        CompositeByteBuf cbuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        //消息头
        ByteBuf headerBuf = Unpooled.copiedBuffer("疯狂创客圈：", utf8Code);
        // 消息体
        ByteBuf bodyBuf = Unpooled.copiedBuffer("高性能Netty", utf8Code);
        cbuf.addComponents(headerBuf, bodyBuf);

        sendMsg(cbuf);
        headerBuf.retain();
        cbuf.release();

        cbuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        bodyBuf = Unpooled.copiedBuffer("高性能学习社群", utf8Code);
        cbuf.addComponents(headerBuf, bodyBuf);
        sendMsg(cbuf);
        cbuf.release();
    }

    private void sendMsg(CompositeByteBuf cbuf) {
        // 处理整个消息
        for (ByteBuf buf : cbuf) {
            int length = buf.readableBytes();
            byte[] array = new byte[length];
            buf.getBytes(buf.readerIndex(), array);
            System.out.println(new String(array, utf8Code));
        }
        System.out.println();
    }

}