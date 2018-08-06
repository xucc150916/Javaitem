package com.xucc.javaweb.service.impl;

import com.xucc.javaweb.dao.UserDao;
import com.xucc.javaweb.entity.User;
import com.xucc.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public boolean login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        User user = new User();
        user.setPassword(password);
        user.setUserName(username);
        return userDao.login(user) != null;
    }
    
    @Override
    public boolean register(String username, String password) {
        
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        // 1.用户名是否存在
        if (userDao.exist(username)) {
            return false;
        }
        User user = new User();
        user.setPassword(password);
        user.setUserName(username);
        return userDao.insert(user) == 1;
    }
    
}
