package com.janson.jvm.objectpool.commonspool;

import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/2/25 16:03
 **/
public class CommonsPool2Test {
    public static void main(String[] args) throws Exception {
        GenericObjectPool<Money> pool = new GenericObjectPool<>(new MoneyPooledObjectFactory());
        Money money = pool.borrowObject();
        money.setType("RMB");
        pool.returnObject(money);

    }
}
