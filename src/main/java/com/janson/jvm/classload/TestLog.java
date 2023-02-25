package com.janson.jvm.classload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2022/12/23 17:52
 **/
public class TestLog {
    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("debug current id is {} and name is {}", 1, "小王");
            logger.trace("trace current id is {} and name is {}", 11, "小王1");
            logger.info("info current id is {} and name is {}", 111, "小王11");
        }
    }
}
