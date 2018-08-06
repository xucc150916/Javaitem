package com.xucc.javaweb.service;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    boolean login(String username, String password);

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return
     */
    boolean register(String username, String password);
    
}
