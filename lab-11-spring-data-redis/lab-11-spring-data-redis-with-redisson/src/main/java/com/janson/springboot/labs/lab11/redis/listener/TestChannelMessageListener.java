package com.janson.springboot.labs.lab11.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/3/12 17:31
 */
public class TestChannelMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("收到 ChannelTopic 消息：");
        System.out.println("线程编号：" + Thread.currentThread().getName());
        System.out.println("message：" + message);
        System.out.println("pattern:" + new String(pattern));
    }


}
