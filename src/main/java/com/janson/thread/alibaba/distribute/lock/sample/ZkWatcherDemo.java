package com.janson.thread.alibaba.distribute.lock.sample;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/20 9:17 上午
 */
public class ZkWatcherDemo {

    public static void main(String[] args) {
        // 创建一个zk客户端
        ZkClient client = new ZkClient("localhost:2181");

        client.setZkSerializer(new MyZkSerializer());

        client.subscribeDataChanges("/bigTree/a", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("--------------收到节点数据变化：" + data + "--------------");
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("--------------收到节点被删除了--------------");
            }
        });

        try {
            Thread.sleep(1000*60*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
