package com.janson.rocketmq.consumer;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/6/14 5:41 下午
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {

        final int[] totals = {0};
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CONSUMER_DELAY_TEST_GROUP");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("DelayTestTopic", "TagA");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("接收到消息：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                for (MessageExt m : msgs) {
                    System.out.println(">>>" + new String(m.getBody()));
                }
                totals[0] += 1;
                System.out.println(">>>>>total=" + totals[0]);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.out.println("Consumer Started.");


    }
}
