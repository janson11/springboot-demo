package com.janson.springboot.demo.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 创建生产者，向ringbuffer中填充元素
 * @Author: Janson
 * @Date: 2023/5/11 23:20
 **/
public class DisruptorMain {

    public static void main(String[] args) throws InterruptedException {

        // 1 事件生产工厂
        LongEventFactory longEventFactory = new LongEventFactory();

        // 2 ringBufferSize的大小
        int ringBufferSize = 256;

        // 3 实例化disruptor对象，初始化ringBuffer
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(longEventFactory,
                ringBufferSize,
                new CustomizableThreadFactory("disruptor-"),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        // 4 设置事件的执行者  (单消费者)
        disruptor.handleEventsWith(new LongEventHandler());

        // 5 disruptor启动
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 6 设置事件生产者
        for (int i = 0; i < 256; i++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                LongEvent longEvent = ringBuffer.get(sequence);
                // 设置该位置的元素的值
                longEvent.setValue(i);
            } finally {
                // 发布事件
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }
    }
}