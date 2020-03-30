package com.bitongchong.zk.loadbalancing;

import java.util.List;
import java.util.Random;

/**
 * @author liuyuehe
 * @date 2020/3/30 15:38
 */
public class RandomLoadBanlance extends AbstractLoadBanalancing {
    @Override
    protected String doSelect(List<String> serviceList) {
        int len = serviceList.size();
        Random random = new Random();
        int num = random.nextInt(len);
        return serviceList.get(num);
    }
}
