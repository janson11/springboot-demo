package com.janson.springboot.demo.disruptor;


import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 事件处理器，也就是消费者，就是讲事件的值打印出来
 * @Author: Janson
 * @Date: 2023/5/11 23:16
 **/
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("Event:{} sequence:{} endOfBatch:{}", event.getValue(), sequence, endOfBatch);
    }
}
