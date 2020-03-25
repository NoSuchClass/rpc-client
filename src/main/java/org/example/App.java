package org.example;

import com.bitongchong.rpc.IUserService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RpcProxyClient proxyClient = new RpcProxyClient();
        IUserService userService = proxyClient.clientProxy(IUserService.class, "localhost", 8080);
        System.out.println(userService.addInfo("测试信息"));;
        System.out.println(userService.getInfo("测试"));;
    }
}
