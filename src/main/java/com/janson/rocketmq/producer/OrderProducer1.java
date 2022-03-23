package com.janson.rocketmq.producer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 发送顺序消息1
 * @Author: Janson
 * @Date: 2022/3/22 21:54
 **/
@Slf4j
public class OrderProducer1 {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ORDER_PRODUCER_GROUP1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;
            Message msg = new Message("OrderTopic1", tags[i % tags.length], "KEY" + i, ("Hello Order RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);
            log.info("sendResult:{}", sendResult);
        }
        producer.shutdown();
    }

    /**
     * 订单步骤
     */
    @Data
    private static class OrderStep {
        private long orderId;
        private String desc;
    }


    private List<OrderStep> buildOrders() {
        List<OrderStep> orderSteps = new ArrayList<OrderStep>();

        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(1L);
        orderStep.setDesc("创建1");
        orderSteps.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(2L);
        orderStep.setDesc("创建2");
        orderSteps.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(3L);
        orderStep.setDesc("付款1");
        orderSteps.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(4L);
        orderStep.setDesc("付款2");
        orderSteps.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(5L);
        orderStep.setDesc("完成1");
        orderSteps.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(6L);
        orderStep.setDesc("完成2");
        orderSteps.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(7L);
        orderStep.setDesc("推送1");
        orderSteps.add(orderStep);

        orderStep = new OrderStep();
        orderStep.setOrderId(8L);
        orderStep.setDesc("推送2");
        orderSteps.add(orderStep);
        return orderSteps;
    }


}
