package com.janson.netty.common.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/1/28 2:39 下午
 */
@Slf4j
public class ClientFactory {

    public static CuratorFramework createSimple(String connectionString) {
        long stime = System.currentTimeMillis();
        //重试策略:第一次重试等待1s，第二次重试等待2s,第三次重试等待4s
        // 第一个参数：等待时间的基础单位，单位为毫秒
        // 第二个参数：最大重试次数。
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3);

        // 获取CuratorFaramework实例的最简单的方式
        // 第一个参数：zk的连接地址
        // 第二个参数:重试策略
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString,retryPolicy);
        log.info("创建连接耗时:{}ms",(System.currentTimeMillis()-stime));
        return client;
    }


    /**
     * 创建CuratorFramework实例选项
     * @param connectionString zk的连接地址
     * @param retryPolicy 重试策略
     * @param connectionTimeoutMs 连接超时时间
     * @param sessionTimeoutMs 会话时间
     * @return
     */
    public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy,int connectionTimeoutMs,int sessionTimeoutMs) {
        // builder 模式创建CuratorFramework实例

        return CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(sessionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }

}
