package com.janson.netty.demos.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/20 4:08 下午
 */
@Slf4j
public class WriteReadTest {

    @Test
    public void testWriteRead() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(9, 100);
        log.info("动作：分配buf(9,100) {}", buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        log.info("动作: 写入4个字节(1,2,3,4) {}", buf);
        buf.setBoolean(0, true);
        buf.setInt(4, 1000);

        log.info("start============:get==============");
        getByteBuf(buf);

        log.info("start============:read==============");
        readByteBuf(buf);

        log.info("动作： 读完 ByteBuf {}", buf);


    }

    // 读取一个字节
    private void readByteBuf(ByteBuf buf) {
        while (buf.isReadable()){
            log.info("读取一个字节：{}",buf.readByte());
        }
    }

    //读取一个字节，不改变指针
    private void getByteBuf(ByteBuf buf) {
        for (int i = 0; i < buf.readableBytes(); i++) {
            log.info("读取一个字节getByteBuf:{}", buf.getByte(i));
        }
    }


    @Test
    public void testResize() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10, 100);
        log.info("动作：分配buf(10,100) {}", buf);
        log.info("start=============:写入4个字节===================");
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        log.info("动作：写入4个字节{}",buf);

        log.info("start=============:写入10个字节===================");
        buf.writeBytes(new byte[]{1, 2, 3, 4,5,6,7,8,9,10,11});
        log.info("动作：写入10个字节{}",buf);

        log.info("start=============:写入64个字节===================");
        for (int i = 0; i < 64; i++) {
            buf.writeByte(1);
        }
        log.info("动作：写入64个字节{}",buf);

        log.info("start=============:写入128个字节===================");
        for (int i = 0; i <128; i++) {
            buf.writeByte(1);
        }
        log.info("动作：写入128个字节{}",buf);
    }
}