package com.janson.netty.demos.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/20 4:39 下午
 */
@Slf4j
public class ReferenceTest {

    /**
     * io.netty.util.IllegalReferenceCountException: refCnt: 0, increment: 1
     */
    @Test
    public void testRef(){
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        log.info("after create:{}",buf.refCnt());

        buf.retain();
        log.info("after retain:{}",buf.refCnt());

        buf.release();
        log.info("after release:{}",buf.refCnt());

        buf.release();
        log.info("after release:{}",buf.refCnt());

        buf.retain();
        log.info("after retain:{}",buf.refCnt());


    }
}
