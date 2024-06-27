package com.janson.springboot.lab19.datasourcepool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
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


    @Resource(name = "ordersDataSource")
    private DataSource ordersDataSource;

    @Resource(name = "usersDataSource")
    private DataSource usersDataSource;

    public static void main(String[] args) {
        // 启动springboot 应用
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // orders数据源
        try (Connection conn = ordersDataSource.getConnection()) {
            LOG.info("[run][ordersDataSource 获得连接:{}]", conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // users数据源
        try (Connection conn = usersDataSource.getConnection()) {
            LOG.info("[run][usersDataSource 获得连接:{}]", conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
