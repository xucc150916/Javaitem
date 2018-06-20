package jdbc;

import java.sql.*;

public class Demo1 {
    public static void jdbcStep() {
        // 数据库连接
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 加载驱动，驱动程序要先添加到CLASSPATH路径中，通过IDEA就可以添加
            Class.forName("com.mysql.jdbc.Driver");

            // 连接数据库，获取Connection接口的对象
            // 获取Connection对象通常有两种方式：一种是通过DriverManager的静态方法获取，一种是通过DataSource（数据源）对象获取
            String url = "jdbc:mysql://localhost:3306/scott?user=root&password=xucc&useSSL=true";
            connection = DriverManager.getConnection(url);

            // 创建SQL命令
            // 1. 创建语句
            String sqlCommand = "select * from emp";

            // 2. 使用statement对象将SQL语句发送到数据库中
            statement = connection.createStatement();

            statement.executeQuery(sqlCommand);

            // 查询结果
            resultSet = statement.executeQuery(sqlCommand);

            // 结果处理
            while (resultSet.next()) {
                // 接收字段名或列下标，从1开始
                String ename = resultSet.getString("ename");
                int empno = resultSet.getInt("empno");
                double sal = resultSet.getInt("sal");
                String job = resultSet.getString("job");

                String str = String.format(
                        "ename = %s, empno = %d, sal = %f, job = %s",
                        ename, empno, sal, job
                );
                System.out.println(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 从里至外关闭
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
