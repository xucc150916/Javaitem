package com.xucc.spring2.instantiation;

/**
 * 静态工厂实例化bean
 */

public class ClientService {

    public static final ClientService clientService = new ClientService();

    private ClientService(){
        System.out.println("xml构造方法实例化bean");
    }

    public static ClientService getInstance() {
        return clientService;
    }

    @Override
    public String toString() {
        return "静态工厂实例化bean";
    }
}
