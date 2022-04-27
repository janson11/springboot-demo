package com.janson.reactive.controller;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/27 2:48 下午
 */
public class MySubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("initialization MySubscriber");
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println("onNext MySubscriber:" + value);
        request(1);
    }
}
