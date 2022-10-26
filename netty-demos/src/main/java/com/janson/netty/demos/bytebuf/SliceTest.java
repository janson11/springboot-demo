package com.janson.netty.demos.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static com.janson.netty.demos.bytebuf.PrintAttribute.print;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/26 10:53 上午
 */
@Slf4j
public class SliceTest {

    @Test
    public void testSlice() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("动作：分配ByteBuf(9,100)", buffer);

        buffer.writeBytes(new byte[]{1,2,3,4});
        print("动作：写入四个字节(1，2，3，4)", buffer);

        ByteBuf slice = buffer.slice();
        print("动作：切片", slice);

        byte[] dst = new byte[4];
        slice.readBytes(dst);
        System.out.println("dst= "+dst);

        print("动作:读取之后 slice",slice);
        print("动作:读取之后 buffer",buffer);

        buffer.readByte();
        print("动作:切片 slice1 之前 buffer",buffer);
        ByteBuf slice1 = buffer.slice();
        print("动作: 切片 slice1",slice1);

        buffer.retain();

        log.info("4.0 refCnt():{}",buffer.refCnt());
        log.info("4.0 slice refCnt():{}",slice.refCnt());
        log.info("4.0 slice1 refCnt():{}",slice1.refCnt());
        buffer.release();
        log.info("4.0 refCnt():{}",buffer.refCnt());
        log.info("4.0 slice refCnt():{}",slice.refCnt());
        log.info("4.0 slice1 refCnt():{}",slice1.refCnt());




    }
}
