package com.xucc.util;

import java.sql.*;

/**
 * jdbc工具类
 */

public class JdbcUtil {
    private String driverClass;
    private String url;


    public JdbcUtil(String driverClass, String url) {
        this.driverClass = driverClass;
        this.url = url;

        try {
            // 加载驱动
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 连接数据库
    public Connection connection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将SQL命令传入数据库，得到预执行命令
    public PreparedStatement preparedStatement(Connection connection, String sql) {
        if(connection != null) {
            try {
                return connection.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 预执行命令，并返回执行结果
    public ResultSet resultSet(PreparedStatement preparedStatement) {
        if(preparedStatement != null) {
            try {
                return preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 执行更新操作
    public int update(PreparedStatement preparedStatement) {
        if(preparedStatement != null) {
            try {
                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    // 关闭资源
    public void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
