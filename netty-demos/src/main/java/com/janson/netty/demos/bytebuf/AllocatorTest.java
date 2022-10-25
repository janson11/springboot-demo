package com.janson.netty.demos.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/24 3:18 下午
 *
 * Direct Memory (直接内存)
 * Direct Memory不属于java堆内存，所分配的内存其实是调用操作系统malloc()函数来获得的，由Netty的本地内存堆Native堆进行管理。
 * Direct Memory容量可以通过-XX:MaxDirectMemorySize来指定，如果不指定，则默认与Java堆的最大值 -Xmx指定一样。
 */
@Slf4j
public class AllocatorTest {

    @Test
    public void showAlloc() {
        ByteBuf buf = null;
        // 方法一：默认分配器，分配初始容量为9,最大容量为100的缓存。
        buf = ByteBufAllocator.DEFAULT.buffer(9,100);
        log.info("方法一",buf);

        // 方法二：默认分配器，分配初始容量为256,最大容量为Integer.MAX_VALUE的缓存。
        buf = ByteBufAllocator.DEFAULT.buffer();
        log.info("方法二",buf.toString());

        // 方法三 非池化分配器，分配基于Java的堆内存缓冲区
        buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer();
        log.info("方法三",buf.toString());

        // 方法四 池化分配器，分配基于操作系统的管理的直接内存缓冲区
        buf = PooledByteBufAllocator.DEFAULT.directBuffer();
        log.info("方法四",buf.toString());



    }
}
