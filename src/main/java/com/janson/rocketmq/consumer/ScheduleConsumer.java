package com.janson.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Description: 延时消费消息
 * @Author: shanjian
 * @Date: 2022/3/21 6:14 下午
 */
@Slf4j
public class ScheduleConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("SCHEDULE_CONSUMER_GROUP");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("ScheduleTopic", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                log.info("Receive new Message threadName:{} msg:{}", Thread.currentThread().getName(), list);
                if (list != null) {
                    for (MessageExt messageExt : list) {
                        String msgId = messageExt.getMsgId();
                        byte[] body = messageExt.getBody();
                        String bodyStr = new String(body, StandardCharsets.UTF_8);
                        log.info("msgId:{} body:{}", msgId, bodyStr);
                    }
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        log.info("Schedule Consumer started");

    }
}
