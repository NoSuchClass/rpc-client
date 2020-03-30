package com.bitongchong.rpc;

import com.bitongchong.zk.IServiceDiscovery;
import com.bitongchong.zk.ServiceDiscoveryImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liuyuehe
 * @date 2020/3/25 15:11
 */
public class RemoteInvocationHandler implements InvocationHandler {
    private String version;

    public RemoteInvocationHandler(String version) {
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        rpcRequest.setVersion(version);
        IServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl();
        String servicePath = serviceDiscovery.discovery(rpcRequest.getClassName() + "-" + rpcRequest.getVersion());

        System.out.println("发现服务地址为： " + servicePath);

        String[] serviceUrl = servicePath.split(":");
        RpcNetTransport transport = new RpcNetTransport(serviceUrl[0], Integer.parseInt(serviceUrl[1]));
        return transport.send(rpcRequest);
    }
}
