package com.janson.jvm.objectpool.commonspool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/2/25 16:04
 **/
@Slf4j
public class MoneyPooledObjectFactory implements PooledObjectFactory<Money> {
    @Override
    public PooledObject<Money> makeObject() throws Exception {
        DefaultPooledObject<Money> object = new DefaultPooledObject<Money>(
                new Money("USD", new BigDecimal("1"))
        );
        log.info("makeObject ...state = {}", object.getState());
        return object;
    }

    @Override
    public void destroyObject(PooledObject<Money> pooledObject) throws Exception {
        log.info("destroyObject ...state = {}", pooledObject.getState());
    }

    @Override
    public boolean validateObject(PooledObject<Money> pooledObject) {
        return true;
    }

    @Override
    public void activateObject(PooledObject<Money> pooledObject) throws Exception {
        log.info("activateObject ...state = {}", pooledObject.getState());
    }

    @Override
    public void passivateObject(PooledObject<Money> pooledObject) throws Exception {
        log.info("passivateObject ...state = {}", pooledObject.getState());
    }
}
