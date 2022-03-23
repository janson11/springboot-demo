package com.janson.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 顺序消费消息，带事务方式
 * @Author: Janson
 * @Date: 2022/3/23 7:34
 **/
@Slf4j
public class OrderConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ORDER_CONSUMER_GROUP");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("OrderTopic", "TagA || TagC || TagD");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                Random random = new Random();
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    log.info("consumerThread={} queueId={} content={}  msg={}", Thread.currentThread().getName(), msg.getQueueId(), new String(msg.getBody()), msg);
                }
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        log.info("consumer started");
    }
}
