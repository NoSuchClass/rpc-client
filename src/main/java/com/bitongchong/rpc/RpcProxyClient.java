package com.bitongchong.rpc;

import java.lang.reflect.Proxy;

/**
 * @author liuyuehe
 * @date 2020/3/25 15:07
 */
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceClass, String version) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new RemoteInvocationHandler(version));
    }
    
}
