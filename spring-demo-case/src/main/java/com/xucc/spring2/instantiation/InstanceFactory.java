package com.xucc.spring2.instantiation;

/**
 * 实例工厂实例化Bean
 */
public class InstanceFactory {
    private static String msg = "msg";

    public String createString() {
        return msg;
    }
}
