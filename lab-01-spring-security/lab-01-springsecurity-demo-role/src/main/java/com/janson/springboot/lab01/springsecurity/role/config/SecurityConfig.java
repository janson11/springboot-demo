package com.janson.springboot.lab01.springsecurity.role.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/12/4 2:54 PM
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置请求地址的权限
        http.authorizeRequests()
                .antMatchers("/test/demo").permitAll()// 所有用户可访问
                .antMatchers("/test/admin").hasRole("ADMIN")// 需要 ADMIN 角色
                .antMatchers("/test/normal").access("hasRole('ROLE_NORMAL')") // 需要 NORMAL 角色。
                // 任何请求，访问的用户都需要经过认证
                .anyRequest().authenticated()
                .and()
                // 设置 Form 表单登陆   所有用户可访问
                .formLogin().permitAll()
                .and()
                // 配置退出相关所有用户可访问
                .logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("normal").password("normal").roles("NORMAL");

    }
}
