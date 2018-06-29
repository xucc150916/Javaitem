package com.xucc.code;

import java.sql.*;

/**
 * 使用autoClose优化资源关闭部分
 */

public class Demo4 {
    public static final String driverClass = "com.mysql.jdbc.Driver";
    public static final String url =
            "jdbc:mysql://localhost:3306/" +
                    "bit_student?user=root&password=xucc&useSSL=true";

    public static void jdbcStep() {
        try {
            // 加载驱动程序
            Class.forName(driverClass);

            // 自动关闭操作
            try(Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from student");
            ) {
                // 查询结果处理
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
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jdbcStep();
    }
}
