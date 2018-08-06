package com.xucc.javaweb.dao.impl;

import com.xucc.javaweb.dao.UserDao;
import com.xucc.javaweb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public User login(User user) {
        String sql = "select id, name,password from t_user where name=? and password=?";
        List<User> userList = jdbcTemplate.query(sql,
                new Object[]{
                        user.getUserName(),
                        user.getPassword()
                },
                new UserRowMapper());
        if (userList.isEmpty() || userList.size() > 1) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    
    @Override
    public int insert(User user) {
        String sql = "insert into t_user (name,password) values (?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getPassword());
    }
    
    @Override
    public boolean exist(String username) {
        String sql = "select count(id) from t_user where name=?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        return count > 0;
    }
    
    public static class UserRowMapper implements RowMapper<User> {
        
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
