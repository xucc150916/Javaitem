package com.xucc.code;

/**
 * 通过jdbc对bit_student数据库进行查询操作
 * 最基本操作
 */


import java.sql.*;

public class Demo1 {
    public static final String driverClass = "com.mysql.jdbc.Driver";
    public static final String url =
            "jdbc:mysql://localhost:3306/" +
                    "bit_student?user=root&password=xucc&useSSL=true";

    public static void jdbcStep() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 加载数据库驱动
            Class.forName(driverClass);

            // 建立数据库连接
            connection = DriverManager.getConnection(url);

            // 创建操作命令
            statement = connection.createStatement();

            // 执行SQL
            resultSet = statement.executeQuery("select * from student");

            // 处理结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                float chinese = resultSet.getFloat("chinese");
                float math = resultSet.getFloat("math");
                float english = resultSet.getFloat("english");
                String msg = String.format(
                        "id: %d, name: %s, gender: %s, chinese: %f, math: %f, english: %f",
                        id, name, gender, chinese, math, english
                );
                System.out.println(msg);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 从内至外依次关闭资源
            if(resultSet != null) {
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

    public static void main(String[] args) {
        jdbcStep();
    }
}
