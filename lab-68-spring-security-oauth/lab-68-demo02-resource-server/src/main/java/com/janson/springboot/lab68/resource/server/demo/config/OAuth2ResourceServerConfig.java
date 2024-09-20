package com.janson.springboot.lab68.resource.server.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Description: OAuth2资源服务器配置
 * @Author: Janson
 * @Date: 2024/9/20 17:01
 **/
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置 /login 路径不需要权限认证
                .antMatchers("/login").permitAll()
                // 设置 /client-login 路径不需要权限认证
                .antMatchers("/client-login").permitAll()
                // 设置 /callback 路径不需要权限认证
                .antMatchers("/callback").permitAll()
                // 设置 /callback02 路径不需要权限认证
                .antMatchers("/callback02").permitAll()
                // 设置其他请求,需要权限认证
                .anyRequest().authenticated();
    }
}
