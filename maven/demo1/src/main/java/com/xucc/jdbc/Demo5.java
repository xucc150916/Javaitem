package com.xucc.jdbc;

import com.xucc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class Demo5 {
    private JdbcUtil jdbcUtil;

    public Demo5(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }


    /**
     * student表查询操作
     * @param idParam 要查询的id，这里以id为例，为了满足不同的查询需求，写一大堆的重载就行了
     * @return 符合条件的数据
     */
    public List<StudentInfo> queryStudent(int idParam) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = jdbcUtil.connection();
            // 使用占位符'?'代表条件
            preparedStatement = jdbcUtil.preparedStatement(connection, "select * from student where id=?");
            if(preparedStatement == null) {
                return new ArrayList<>();
            }
            // 设置占位符的值
            preparedStatement.setInt(1, idParam);

            resultSet = jdbcUtil.resultSet(preparedStatement);
            List<StudentInfo> list = new ArrayList<>();
            while (resultSet.next()) {
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setId(resultSet.getInt("id"));
                studentInfo.setName(resultSet.getString("name"));
                studentInfo.setGender(resultSet.getString("gender"));
                studentInfo.setChinese(resultSet.getFloat("chinese"));
                studentInfo.setMath(resultSet.getFloat("math"));
                studentInfo.setEnglish(resultSet.getFloat("english"));
                list.add(studentInfo);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(resultSet, preparedStatement, connection);
        }
        return new ArrayList<>();
    }

    /**
     * 删除操作
     * @param idParam 删除满足指定id的行
     * @return 删除的行数
     */
    public int deleteStudent(int idParam) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = jdbcUtil.connection();
            preparedStatement = jdbcUtil.preparedStatement(connection, "delete from student where id=?");
            if(preparedStatement == null) {
                return -1;
            }
            preparedStatement.setInt(1, idParam);
            return jdbcUtil.update(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(null, preparedStatement, connection);
        }
        return 0;
    }

    public static final String driverClass = "com.mysql.jdbc.Driver";
    public static final String url =
            "jdbc:mysql://localhost:3306/" +
                    "bit_student?user=root&password=xucc&useSSL=true";

    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil(driverClass, url);
        Demo5 demo5 = new Demo5(jdbcUtil);

        // 查询id为1的数据
        List<StudentInfo> studentInfos = demo5.queryStudent(1);
        System.out.println(studentInfos);

        // 删除id为8的数据
        int ret = demo5.deleteStudent(8);
        System.out.println("changes: "+ret);
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


}
