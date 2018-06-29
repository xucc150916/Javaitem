package com.xucc.code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 创建一个StudentInfo类，存储student表中每行数据，来优化查询结果处理部分
 */

public class Demo3 {
    public static final String driverClass = "com.mysql.jdbc.Driver";
    public static final String url =
            "jdbc:mysql://localhost:3306/" +
                    "bit_student?user=root&password=xucc&useSSL=true";

    public static void jdbcStep() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            DataSourceStudent dataSourceStudent = new DataSourceStudent(driverClass, url);
            // 建立数据库连接
            connection = dataSourceStudent.getConnection();

            // 创建操作命令
            statement = connection.createStatement();

            // 执行SQL
            resultSet = statement.executeQuery("select * from student");

            // 处理结果集
            while (resultSet.next()) {
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setId(resultSet.getInt("id"));
                studentInfo.setName(resultSet.getString("name"));
                studentInfo.setGender(resultSet.getString("gender"));
                studentInfo.setChinese(resultSet.getFloat("chinese"));
                studentInfo.setMath(resultSet.getFloat("math"));
                studentInfo.setEnglish(resultSet.getFloat("english"));
                System.out.println(studentInfo);
            }
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

    private static class StudentInfo {
        private int id;
        private String name;
        private String gender;
        private float chinese;
        private float math;
        private float english;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public float getChinese() {
            return chinese;
        }

        public void setChinese(float chinese) {
            this.chinese = chinese;
        }

        public float getMath() {
            return math;
        }

        public void setMath(float math) {
            this.math = math;
        }

        public float getEnglish() {
            return english;
        }

        public void setEnglish(float english) {
            this.english = english;
        }

        @Override
        public String toString() {
            return  "id: "+id+", name: "+name+", gender: "+gender
                    +", chinese: "+chinese+", math: "+math +", english: "+english;
        }
    }

    public static void main(String[] args) {
        jdbcStep();
    }
}
