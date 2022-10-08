package com.janson.netty.nio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * @Description:
 * 使用Buffer类的基本步骤：
 *  1、使用创建子类实例对象的allocate()方法，创建一个Buffer类的实例对象。
 *  2、调用put方法，将数据希尔到缓冲区中。
 *  3、写入完成后，在开始读取数据前，调用Buffer.flip()方法，将缓冲区转换成读模式。
 *  4、调用get方法，从缓冲区读取数据。
 *  5、读取完成后，调用Buffer.clear() 或Buffer.compact()方法，将缓冲区转换为写入模式。
 * @Author: shanjian
 * @Date: 2022/9/16 10:43 上午
 */
@Slf4j
public class UseBuffer {

    static IntBuffer intBuffer = null;

    public static void main(String[] args) {
        allocatTest();
        /**
         * 10:56:02.687 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after allocate--------------
         * 10:56:02.693 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=0
         * 10:56:02.696 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=20
         * 10:56:02.696 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         */

        putTest();
        /**
         * 11:00:30.954 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after put--------------
         * 11:00:30.954 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=5
         * 11:00:30.954 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=20
         * 11:00:30.954 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         */

        flipTest();
        /**
         * 11:03:26.202 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after flip--------------
         * 11:03:26.202 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=0
         * 11:03:26.202 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=5
         * 11:03:26.202 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         */

        get();
        /**
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=0
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=1
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after get 2 int--------------
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=2
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=5
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=2
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=3
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=4
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after get 3 int--------------
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=5
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=5
         * 11:10:01.695 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         */

        rewindTest();
        /**
         * 11:13:51.701 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after rewind--------------
         * 11:13:51.701 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=0
         * 11:13:51.701 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=5
         * 11:13:51.701 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         */

        reRead();
        /**
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=0
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=1
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=2
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=3
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=4
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after reRead--------------
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=5
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=5
         * 11:20:12.192 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         */
        afterReset();
        /**
         * 11:24:27.056 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after reset--------------
         * 11:24:27.056 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=2
         * 11:24:27.056 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=5
         * 11:24:27.056 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         * 11:24:27.056 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=2
         * 11:24:27.056 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=3
         * 11:24:27.056 [main] INFO com.janson.netty.nio.buffer.UseBuffer - j=4
         */
        clearDemo();
        /**
         * 11:26:50.280 [main] INFO com.janson.netty.nio.buffer.UseBuffer - -------------after clear--------------
         * 11:26:50.280 [main] INFO com.janson.netty.nio.buffer.UseBuffer - position=0
         * 11:26:50.280 [main] INFO com.janson.netty.nio.buffer.UseBuffer - limit=20
         * 11:26:50.280 [main] INFO com.janson.netty.nio.buffer.UseBuffer - capacity=20
         */
    }

    public static void clearDemo(){
        log.info("-------------after clear--------------");
        // 清空缓冲区，进入写入模式
        intBuffer.clear();
        //输出缓冲区的主要属性值
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
    }

    public static void afterReset() {
        log.info("-------------after reset--------------");
        // 把前面保存在mark中的值恢复到position
        intBuffer.reset();
        //输出缓冲区的主要属性值
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
        //读取并且输出元素
        for (int i =2;i<5;i++) {
            int j = intBuffer.get();
            log.info("j={}",j);
        }
    }



    /**
     * 重复读取
     */
    public static void reRead() {
        for (int i = 0; i < 5; i++) {
            if (i==2) {
                // 临时保存，标记一下第3个位置
                intBuffer.mark();
            }
            // 读取元素
            int j = intBuffer.get();
            log.info("j={}",j);
        }
        //输出缓冲区的主要属性值
        log.info("-------------after reRead--------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
    }

    /**
     * 倒带：已经读完的数据，如果需要再读一遍，可以采用rewind()方法
     */
    public static void rewindTest() {
        // 倒带
        intBuffer.rewind();
        //输出缓冲区的主要属性值
        log.info("-------------after rewind--------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
    }

    /**
     * 从缓冲区读取get()
     */
    public static void get() {
        // 先读2个
        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();
            log.info("j={}",j);

        }
        //输出缓冲区的主要属性值
        log.info("-------------after get 2 int--------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());

        // 再读3个
        for (int i = 0; i < 3; i++) {
            int j = intBuffer.get();
            log.info("j={}",j);

        }
        //输出缓冲区的主要属性值
        log.info("-------------after get 3 int--------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
    }

    /**
     * flip()翻转,作用是将写入模式翻转成读取模式。
     */
    public static void flipTest(){
        //翻转缓冲区，从写模式翻转成读模式
        intBuffer.flip();
        //输出缓冲区的主要属性值
        log.info("-------------after flip--------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
    }

    /**
     * 写入缓冲区 put()
     */
    public static void putTest() {
        for (int i = 0; i < 5; i++) {
            // 写入一个整数到缓冲区
            intBuffer.put(i);
        }
        //输出缓冲区的主要属性值
        log.info("-------------after put--------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());

    }


    /**
     * 创建缓冲区 allocate()
     */
    public static void allocatTest() {
        // 调用allocate方法，而不是使用new
        intBuffer = IntBuffer.allocate(20);
        //输出buffer的主要属性
        log.info("-------------after allocate--------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
    }

}
