package com.bitongchong.zk.loadbalancing;

import java.util.List;

/**
 * @author boot liu
 */
public interface ILoadBanalancing {
    /**
     * 做一些基础的常规操作
     * @param serviceList -
     * @return -
     */
    String selectHost(List<String> serviceList);
}
