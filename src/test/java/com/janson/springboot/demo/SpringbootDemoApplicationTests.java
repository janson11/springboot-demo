package com.janson.springboot.demo;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootDemoApplicationTests {

    Logger logger = LoggerFactory.getLogger(SpringbootDemoApplicationTests.class);

    //logback  14231
    //log4j  14469
    //log4j2  19642


}
