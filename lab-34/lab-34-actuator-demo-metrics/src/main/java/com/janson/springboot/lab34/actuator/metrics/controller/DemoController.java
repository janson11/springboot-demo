package com.janson.springboot.lab34.actuator.metrics.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/8 10:56 AM
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Counter METRIC_DEMO_COUNT = Counter.builder("demo.visit.count")// 指标的名字
            .description("demo 访问次数")// 指标的描述
            .baseUnit("次")// 指标的单位
            .tag("test", "nicai") // 自定义标签
            .register(Metrics.globalRegistry); // 注册到全局 MeterRegistry 指标注册表


    @GetMapping("/visit")
    public String visit() {
        METRIC_DEMO_COUNT.increment();
        return "Demo 示例";
    }

}
