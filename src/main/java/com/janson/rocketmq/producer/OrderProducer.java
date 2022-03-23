package com.janson.rocketmq.producer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 发送顺序消息
 * @Author: Janson
 * @Date: 2022/3/22 21:54
 **/
@Slf4j
public class OrderProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ORDER_PRODUCER_GROUP");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        String[] tags = new String[]{"TagA", "TagC", "TagD"};

        List<OrderStep> orderSteps = new OrderProducer().buildOrders();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        for (int i = 0; i < 8; i++) {
            String body = dateStr + " Hello RocketMQ " + orderSteps.get(i);
            Message msg = new Message("OrderTopic", tags[i % tags.length], "KEY" + i, body.getBytes());
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    long id = (long) arg;
                    long index = id % mqs.size();
                    return mqs.get((int) index);
                }
            }, orderSteps.get(i).getOrderId());
            log.info("status:{} queueId:{} body:{}", sendResult.getSendStatus(), sendResult.getMessageQueue().getQueueId(), body);
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
