package com.janson.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 顺序消费消息，带事务方式1
 * @Author: Janson
 * @Date: 2022/3/23 7:34
 **/
@Slf4j
public class OrderConsumer1 {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ORDER_CONSUMER_GROUP1");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("OrderTopic1", "TagA || TagC || TagD");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(false);
                log.info("ThreadName:{} Receive New Messages: msgs {} body:{}", Thread.currentThread().getName(), msgs, new String(msgs.get(0).getBody()));
                this.consumeTimes.incrementAndGet();
                if (this.consumeTimes.get() % 2 == 0) {
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if (this.consumeTimes.get() % 3 == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                } else if (this.consumeTimes.get() % 4 == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                } else if (this.consumeTimes.get() % 5 == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        log.info("consumer started");
    }
}
