package com.janson.jvm.objectpool;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/2/25 15:37
 **/
public class Money {

    public Money(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public static Money init() {
        //假设对象new非常耗时
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Money("USD", new BigDecimal("1"));
    }

    private String type;
    private BigDecimal amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
