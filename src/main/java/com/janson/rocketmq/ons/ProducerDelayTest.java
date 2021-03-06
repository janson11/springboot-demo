/*
package com.janson.rocketmq.ons;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.SendResult;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.SendStatus;
import com.aliyun.openservices.shade.org.apache.commons.lang3.time.DateFormatUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

*/
/**
 * @Description: 延迟消息
 * @Author: shanjian
 * @Date: 2022/6/13 5:55 下午
 *//*

public class ProducerDelayTest {

    public static void main(String[] args) {

        DefaultMQProducer producer = new DefaultMQProducer("PRODUCER_DELAY_TEST_GROUP");
        producer.setNamesrvAddr("localhost:9876");
        LocalDateTime now = LocalDateTime.now();
        Message msg = new Message("DelayTestTopic", "TagA", ("Hello Rocketmq Delay Message" + now).getBytes(StandardCharsets.UTF_8));
        msg.setKey("Delay_Key");
//        msg.setKeys("Delay_Key");

        try {
            producer.start();
            // 原生 rocketmq private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
            // 延时消息，单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递，例如消息在15秒后投递。
            long delayTime = System.currentTimeMillis() + 15 * 1000;
            System.out.println("发送时间>>" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + " delayTime:" + delayTime);
            // 设置消息需要被投递的时间。
            msg.setStartDeliverTime(delayTime);
            SendResult sendResult = producer.send(msg);
            // 同步发送消息，只要不抛异常就是成功
            if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + " Send mq delay message success. Topic is:" + msg.getTopic() + " msgId is: " + sendResult.getMsgId());
            }

        } catch (Exception e) {
            System.out.println(new Date() + " Send mq delay message failed. Topic is :" + msg.getTopic());
            e.printStackTrace();
        }
        producer.shutdown();
    }


}
*/
