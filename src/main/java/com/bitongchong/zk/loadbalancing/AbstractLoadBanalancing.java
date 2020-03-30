package com.bitongchong.zk.loadbalancing;

import java.util.List;

/**
 * @author liuyuehe
 * @date 2020/3/30 15:28
 */
public abstract class AbstractLoadBanalancing implements ILoadBanalancing {
    @Override
    public String selectHost(List<String> serviceList) {
        if (serviceList == null || serviceList.isEmpty()) {
            throw new RuntimeException("请求无对应服务地址！");
        }
        if (serviceList.size() == 1) {
            return serviceList.get(0);
        }
        return doSelect(serviceList);
    }

    /**
     * 这儿做具体的选择操作
     * @param serviceList -
     * @return -
     */
    protected abstract String doSelect(List<String> serviceList);
}
