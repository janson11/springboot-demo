package com.janson.netty.common.zk;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/1/28 2:39 下午
 */
@Slf4j
@Data
public class ZKClient {

    private CuratorFramework client;

    // zk集群地址
    private static final String ZK_ADDRESS = "localhost:2181";

    public static ZKClient instance = null;


    static {
        instance = new ZKClient();
        instance.init();
    }

    private ZKClient() {

    }

    public void init() {
        if (null != client) {
            return;
        }
        //创建客户端
        client = ClientFactory.createSimple(ZK_ADDRESS);
        //启动客户端实例，连接服务器
        client.start();
    }

    public void destroy() {
        CloseableUtils.closeQuietly(client);
    }

    /**
     * 创建节点
     *
     * @param zkPath
     * @param data
     */
    public void createNode(String zkPath, String data) {
        try {
            //创建一个ZNode节点
            //节点的数据为payload
            byte[] payload = "to set content".getBytes("UTF-8");
            if (data != null) {
                payload = data.getBytes(StandardCharsets.UTF_8);
            }
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(zkPath, payload);
        } catch (Exception e) {

        }
    }

    /**
     * 删除节点
     */
    public void deleteNode(String zkPath) {
        try {
            if (!isNodeExist(zkPath)) {
                return;
            }
            client.delete().forPath(zkPath);
        } catch (Exception e) {
            log.error("deleteNode error", e);
        }
    }


    /**
     * 检查节点
     *
     * @param zkPath
     * @return
     */
    public boolean isNodeExist(String zkPath) {
        try {
            Stat stat = client.checkExists().forPath(zkPath);
            if (null == stat) {
                log.info("节点不存在：{}", zkPath);
                return false;
            } else {
                log.info("节点存在 stat is:{}", stat.toString());
                return true;
            }
        } catch (Exception e) {
            log.error("isNodeExist error", e);
        }
        return false;
    }

    /**
     * 创建 临时 顺序 节点
     *
     * @param srcPath
     * @return
     */
    public String createEphemeralSeqNode(String srcPath) {
        try {
            //创建一个ZNode节点
            String path = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(srcPath);
            return path;
        } catch (Exception e) {
            log.error("createEphemeralSeqNode error", e);
        }
        return null;
    }


}
