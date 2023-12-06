package com.janson.springboot.lab34.actuator.health.actuate;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/6 5:28 PM
 */
@Component
public class DemoHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // 判断是否健康
        boolean success = checkSuccess();

        // 如果健康，则标记状态为UP
        if (success) {
            builder.up().build();
        }

        // 如果不健康，则标记状态为DOWN
        builder.down().withDetail("msg", "我就是做个示例而已");
    }

    private boolean checkSuccess() {
        return false;
    }
}
