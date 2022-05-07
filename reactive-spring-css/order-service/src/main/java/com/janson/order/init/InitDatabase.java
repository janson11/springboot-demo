package com.janson.order.init;

import com.janson.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/5/5 5:02 下午
 */
@Component
public class InitDatabase {

    @Bean
    CommandLineRunner init(@Autowired MongoTemplate mongoTemplate) {
        return args -> {
            mongoTemplate.dropCollection(Order.class);
            mongoTemplate.insert(new Order("0_" + UUID.randomUUID().toString(), "Order001", "deliveryAddress1", ""));
            mongoTemplate.insert(new Order("0_" + UUID.randomUUID().toString(), "Order002", "deliveryAddress2", ""));

            mongoTemplate.findAll(Order.class).forEach(
                    order -> System.out.println(order.getId())
            );
        };
    }

}
