package com.bitongchong.zk;

import com.bitongchong.config.ZkInfoConfig;
import com.bitongchong.zk.loadbalancing.RandomLoadBanlance;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * @author liuyuehe
 * @date 2020/3/30 14:56
 */
public class ServiceDiscoveryImpl implements IServiceDiscovery {
    CuratorFramework zkServer;
    List<String> serviceList;

    public ServiceDiscoveryImpl() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        zkServer = CuratorFrameworkFactory.builder()
                .connectString(ZkInfoConfig.CONNECT_INFO)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace(ZkInfoConfig.CONNECT_NAMESPACE)
                .build();
        zkServer.start();
    }

    @Override
    public String discovery(String serviceName) {
        String servicePath = "/" + serviceName;
        try {
            serviceList = zkServer.getChildren().forPath(servicePath);
            serviceList = zkServer.getChildren().forPath(servicePath);
            RandomLoadBanlance balance = new RandomLoadBanlance();
            return balance.selectHost(serviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
