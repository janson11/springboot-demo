package com.janson.springboot.demo;

import com.janson.springboot.demo.config.InitializingBeanTest;
import com.janson.springboot.demo.listener.ApplicationEnvironmentPreparedEventListener;
import com.janson.springboot.demo.listener.ApplicationFailedEventListener;
import com.janson.springboot.demo.listener.ApplicationPreparedEventListener;
import com.janson.springboot.demo.listener.ApplicationReadyEventListener;
import com.janson.springboot.demo.listener.ApplicationStartedEventListener;
import com.janson.springboot.demo.listener.ApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootDemoApplication.class);
        springApplication.addListeners(new ApplicationEnvironmentPreparedEventListener());
        springApplication.addListeners(new ApplicationFailedEventListener());
        springApplication.addListeners(new ApplicationPreparedEventListener());
        springApplication.addListeners(new ApplicationReadyEventListener());
        springApplication.addListeners(new ApplicationStartedEventListener());
        springApplication.addListeners(new ApplicationStartingEventListener());
        springApplication.run(args);
    }

    @Bean
    public InitializingBeanTest initializingBeanTest() {
        return new InitializingBeanTest();
    }

}
