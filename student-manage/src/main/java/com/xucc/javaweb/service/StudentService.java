package com.xucc.javaweb.service;

import com.xucc.javaweb.entity.Student;
import com.xucc.javaweb.model.PageBean;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
public interface StudentService {

    /**
     * 保存学生信息
     * @param student 新的学生信息
     * @return
     */
    int saveStudent(Student student);

    /**
     * 修改学生信息
     * @param student 新的学生信息
     * @return
     */
    int modifyStudent(Student student);

    /**
     * 根据id删除学生信息
     * @param delIds id
     * @return
     */
    int removeStudent(String delIds);

    /**
     * 搜索学生
     * @param pageBean
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     */
    List<Student> queryStudent(PageBean pageBean, Student student, String bbirthday, String ebirthday);

    /**
     *
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     */
    int queryCount(Student student, String bbirthday, String ebirthday);

    /**
     *
     * @param s
     * @return
     */
    boolean queryStudentByGradeId(String s);
}
