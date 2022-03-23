package com.janson.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


/**
 * @Description: 延时消息的生产者
 * @Author: Janson
 * @Date: 2022/3/20 16:43
 **/
@Slf4j
public class ScheduleProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("SCHEDULE_PRODUCER_GROUP");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("ScheduleTopic", "TagA", ("Hello Schedule message RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            msg.setDelayTimeLevel(3);
            SendResult sendResult = producer.send(msg);
            log.info("sendResult:{}", sendResult);
        }
        producer.shutdown();
    }
}
