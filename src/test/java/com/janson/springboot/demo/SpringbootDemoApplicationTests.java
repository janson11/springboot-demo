package com.janson.springboot.demo;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootDemoApplicationTests {

    Logger logger = LoggerFactory.getLogger(SpringbootDemoApplicationTests.class);

    //logback  14231
    //log4j  14469
    //log4j2  19642
    @Test
    public void testLog() {
        int threadNum = 100;
        final int LOOP_NUM = 100000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            for (int j = 0; j < LOOP_NUM; j++) {
                logger.info("Info Message j:{}", j);
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}
