package com.janson.springboot.lab33.shiro.demo.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/11/30 7:22 PM
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        // 创建SimpleAccountRealm对象
        SimpleAccountRealm realm = new SimpleAccountRealm();
        // 添加两个用户
        realm.addAccount("admin","admin","ADMIN");
        realm.addAccount("normal","normal","NORMAL");
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        // 创建 DefaultWebSecurityManager对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置其使用的 Realm
        securityManager.setRealm(this.realm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        // 创建 ShiroFilterFactoryBean 对象，用于创建ShiroFilter过滤器
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        // 设置 securityManager
        filterFactoryBean.setSecurityManager(this.securityManager());

        //设置URL们
        // 登录URL
        filterFactoryBean.setLoginUrl("/login");
        // 登陆成功URL
        filterFactoryBean.setLoginUrl("/login_success");
        // 无权限URL
        filterFactoryBean.setLoginUrl("/unauthorized");



        return filterFactoryBean;
    }

    private Map<String,String> filterChainDefinitionMap() {
        // 注意 要使用有序的LinkedHashMap ，顺序匹配
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        // 允许匿名访问
        filterMap.put("/test/echo", "anon");
        // 需要ADMIN 角色
        filterMap.put("/test/admin", "roles[ADMIN]");
        // 需要NORMAL 角色
        filterMap.put("/test/normal", "roles[NORMAL]");
        // 退出
        filterMap.put("/logout", "logout");
        // 默认剩余的URL 需要经过认证
        filterMap.put("/**", "authc");
        return filterMap;

    }

}
