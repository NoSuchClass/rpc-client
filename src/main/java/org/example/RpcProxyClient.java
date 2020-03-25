package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author liuyuehe
 * @date 2020/3/25 15:07
 */
public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceClass, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new RemoteInvocationHandler(host, port));
    }
}
