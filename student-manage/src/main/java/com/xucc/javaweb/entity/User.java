package com.xucc.javaweb.entity;

import lombok.Data;

@Data
public class User {
    
    /**
     * 系统用户编号
     */
    private int id;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码
     */
    private String password;

//    登录标记，当前用户上线，其他用户就不能通过该账号登录
//    public boolean falg;
}
