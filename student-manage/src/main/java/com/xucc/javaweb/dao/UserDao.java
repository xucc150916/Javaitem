package com.xucc.javaweb.dao;

import com.xucc.javaweb.entity.User;


public interface UserDao {
    
    User login(User user);
    
    int insert(User user);
    
    boolean exist(String username);
}
