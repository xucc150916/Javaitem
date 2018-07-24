package com.xucc.spring5.dao;

import com.xucc.spring5.entity.MemoGroup;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * jdbc进行数据库的操作
 */

@Component
public class JdbcOperaation {

    private final DataSource dataSource;

    public JdbcOperaation(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 添加一条记录
     */
    public void insert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 获取数据库连接
            connection = dataSource.getConnection();
            // 创建SQL语句
            preparedStatement = connection.prepareStatement(
                    "insert into memo_group(name, created_time) values(?, ?);"
            );
            // 参数绑定
            preparedStatement.setString(1, "屈贵军");
//            preparedStatement.setDate(2, ); TODO
            // 执行
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
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

    /**
     * 查询
     */
    public void select() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        MemoGroup memoGroup = null;

        try {
            // 获取数据库连接
            connection = dataSource.getConnection();

            // 创建语句
            statement = connection.prepareStatement(
                    "select * from memo_group"
            );

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                memoGroup = new MemoGroup();
                memoGroup.setId(resultSet.getInt("id"));
                memoGroup.setName(resultSet.getString("name"));
                memoGroup.setCreatedTime(resultSet.getDate("created_time"));
                memoGroup.setModifyTime(resultSet.getTime("modify_time"));
                System.out.println(memoGroup);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null) {
                try {
                    statement.close();
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
}
