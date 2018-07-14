package com.xucc.spring2.demo;

/**
 * 静态方法实例化bean
 */

public class ClientService {

    public static final ClientService clientService = new ClientService();

    private ClientService(){

    }

    public static ClientService getInstance() {
        return clientService;
    }

    @Override
    public String toString() {
        return "ClientService{}";
    }
}
