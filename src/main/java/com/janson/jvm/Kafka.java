package com.janson.jvm;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/6/9 9:28 上午
 */
public class Kafka {
    public static void main(String[] args) {
        ReplicaManager replicaManager = new ReplicaManager();
        replicaManager.loadReplicasFromDisk();
    }
}
