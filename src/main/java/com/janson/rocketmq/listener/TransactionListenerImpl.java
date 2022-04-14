package com.janson.rocketmq.listener;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 事务监听的实现
 * @Author: shanjian
 * @Date: 2022/4/12 5:36 下午
 */
public class TransactionListenerImpl implements TransactionListener {
    /**
     * 类似订单id
     */
    private AtomicInteger transactionIndex = new AtomicInteger(0);
    /**
     * 保存本地事务结果
     */
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 当发送Half消息成功时，执行本地事务
     *
     * @param msg Half消息[半消息]
     * @param arg 自定义的业务参数
     * @return LocalTransactionState 本地事务的状态
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
    }


    /**
     * 当生产者发送Half消息到broker没有响应的时候，broker会发送检查的消息到生产者，检查本地事务的结果。
     *
     * @param msg 检查的消息
     * @return LocalTransactionState 本地事务的状态
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
