package com.janson.jvm;

import com.janson.jvm.common.ReplicaFetcher;
import com.janson.jvm.common.ReplicaManager;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/6/9 9:28 上午
 */
public class Kafka {

    /**
     * 静态变量 fetcher
     */
    private static ReplicaFetcher fetcher = new ReplicaFetcher();

    public static void main(String[] args) throws InterruptedException {
        loadReplicasFromDisk();
        while (true) {
            fetchReplicasFromRemote();
            Thread.sleep(1000);
        }
    }

    /**
     * 栈帧 loadReplicasFromDisk
     * 局部变量 replicaManager
     */
    private static void loadReplicasFromDisk() {
        ReplicaManager replicaManager = new ReplicaManager();
        replicaManager.load();
    }

    /**
     * 栈帧 fetchReplicasFromRemote
     * 局部变量 replicaManager
     */
    private static void fetchReplicasFromRemote() {
        fetcher.fetch();
    }
}