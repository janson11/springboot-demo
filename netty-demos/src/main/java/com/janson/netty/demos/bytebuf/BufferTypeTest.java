package com.janson.netty.demos.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/25 4:49 下午
 */
@Slf4j
public class BufferTypeTest {
    final static Charset UTF_8 = Charset.forName("UTF-8");

    //堆缓冲区
    @Test
    public void testHeapBuffer() {
        // 取得堆内存
        // 取得堆内存--netty4默认直接buffer,而非堆buffer
        ByteBuf heapBuffer = ByteBufAllocator.DEFAULT.heapBuffer();
        heapBuffer.writeBytes("疯狂创客圈：高性能学习社群".getBytes(UTF_8));
        if (heapBuffer.hasArray()) {
            // 取得内部数组
            byte[] array = heapBuffer.array();
            int offset = heapBuffer.arrayOffset() + heapBuffer.readerIndex();
            int length = heapBuffer.readableBytes();
            log.info("{}", new String(array, offset, length, UTF_8));

        }
        heapBuffer.release();
    }

    //直接缓冲区
    @Test
    public void testDirectBuffer() {
        ByteBuf directBuffer = ByteBufAllocator.DEFAULT.directBuffer();
        directBuffer.writeBytes("疯狂创客圈：高性能学习社群".getBytes(UTF_8));
        if (!directBuffer.hasArray()) {
            int length = directBuffer.readableBytes();
            byte[] array = new byte[length];
            //读取数据到堆内存
            directBuffer.getBytes(directBuffer.readerIndex(), array);
            log.info("{}", new String(array, UTF_8));

        }
        directBuffer.release();
    }

}
