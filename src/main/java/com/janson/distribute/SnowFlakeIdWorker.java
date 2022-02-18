package com.janson.distribute;

/**
 * @Description: 分布式雪花算法
 * @Author: shanjian
 * @Date: 2022/2/18 2:54 下午
 */
public class SnowFlakeIdWorker {
    private long workerId;
    private long datacenterId;
    private long sequence;
    private long twepoch = 1288834974657L;
    private long workIdBits = 5L;
    private long datacenterIdBits = 5L;
    // ^(亦或运算) ，针对二进制，相同的为0，不同的为1
    //<<(向左位移) 针对二进制，转换成二进制后向左移动3位，后面用0补齐
    private long maxWorkerId = -1L ^ (-1L << datacenterIdBits);

    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long sequenceBits = 12L;
    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workIdBits;
    private long timestampLeftShift = sequenceBits + workIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public SnowFlakeIdWorker(long workerId, long datacenterId, long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("worker starting.timestamp left shift %d, datacenter id bits %d,worker id bits %d,sequence bits %d,worker id %d", timestampLeftShift, datacenterIdBits, workIdBits, sequenceBits, workerId);
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public synchronized long nextId() {
        long timestamp = getTimestamp();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards .Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMills(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        // 这儿就是将时间戳左移，放到 41 bit那儿；
        // 将机房 id左移放到 5 bit那儿；
        // 将机器id左移放到5 bit那儿；将序号放最后12 bit；
        // 最后拼接起来成一个 64 bit的二进制数字，转换成 10 进制就是个 long 型
        return ((timestamp - twepoch) << timestampLeftShift | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence);


    }

    private long tilNextMills(long lastTimestamp) {
        long timestamp = getTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = getTimestamp();
        }
        return timestamp;
    }


    public static void main(String[] args) {
        System.out.println(":" + (2L ^ 3L));
        System.out.println("2^3运算的结果是 :" + (2 ^ 3));
        System.out.println("11:" + (-1L ^ (-1L << 5L)));

        SnowFlakeIdWorker snowFlakeIdWorker = new SnowFlakeIdWorker(1, 1, 1);
        for (int i = 0; i < 100; i++) {
            System.out.println("i：" + i + " ： " + snowFlakeIdWorker.nextId());
        }
    }
}
