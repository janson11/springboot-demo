package com.janson.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 发送异步消息
 * @Author: shanjian
 * @Date: 2022/3/21 5:54 下午
 */
@Slf4j
public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("SIMPLE_ASYNC_PRODUCER_GROUP");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        producer.setRetryTimesWhenSendFailed(0);
        int messageCount = 100;

        final CountDownLatch2 countDownLatch2 = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            Message msg = new Message("SimpleAsyncTopic", "TagB", "simpleAsync", ("Hello Simple Async RocketMQ  repeat " + i).getBytes(StandardCharsets.UTF_8));
            final int index = i;
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch2.countDown();
                    log.info("sendResult OK  index:{} msgId:{}", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    countDownLatch2.countDown();
                    log.info("sendResult OK  index:{} ", index, throwable);
                }
            });
        }

        countDownLatch2.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }
}
