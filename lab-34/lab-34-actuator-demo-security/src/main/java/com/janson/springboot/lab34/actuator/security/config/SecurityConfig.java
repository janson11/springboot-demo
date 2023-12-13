package com.janson.springboot.lab34.actuator.security.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/8 7:16 PM
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 访问 EndPoint 地址，需要经过认证，并且拥有ADMIN角色
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((request) -> request.anyRequest().hasRole("ADMIN"));
        // 开启 Basic Auth
        http.httpBasic();
    }
}
