package com.janson.thread.chapter26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/1/8 20:49
 **/
public class LongAccumulatorDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0);
        LongAccumulator result = new LongAccumulator((x, y) -> (x * y), 1);
        LongAccumulator min = new LongAccumulator((x, y) -> Math.min(x, y), 1);
        LongAccumulator max = new LongAccumulator((x, y) -> Math.max(x, y), 0);

        ExecutorService service = Executors.newFixedThreadPool(8);
        IntStream.range(1, 10).forEach(i -> service.submit(() -> accumulator.accumulate(i)));
        Thread.sleep(2000);
        System.out.println(accumulator.getThenReset());
    }


}
