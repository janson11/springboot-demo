package com.janson.springboot.lab19.datasourcepool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2024/6/27 19:23
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private DataSource dataSource;


    public static void main(String[] args) {
        // 启动springboot 应用
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            LOG.info("[run][获得连接:{}]", conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
