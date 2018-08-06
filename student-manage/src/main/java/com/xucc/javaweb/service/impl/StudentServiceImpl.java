package com.xucc.javaweb.service.impl;

import com.xucc.javaweb.dao.StudentDao;
import com.xucc.javaweb.entity.Student;
import com.xucc.javaweb.model.PageBean;
import com.xucc.javaweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentDao studentDao;
    
    @Override
    public int saveStudent(Student student) {
        return studentDao.studentAdd(student);
    }
    
    @Override
    public int modifyStudent(Student student) {
        return studentDao.studentModify(student);
    }
    
    @Override
    public int removeStudent(String delIds) {
        return studentDao.studentDelete(delIds);
    }
    
    @Override
    public List<Student> queryStudent(PageBean pageBean, Student student, String bbirthday, String ebirthday) {
        return studentDao.studentList(pageBean, student, bbirthday, ebirthday);
    }
    
    @Override
    public int queryCount(Student student, String bbirthday, String ebirthday) {
        return studentDao.studentCount(student, bbirthday, ebirthday);
    }
    
    @Override
    public boolean queryStudentByGradeId(String s) {
        return studentDao.getStudentByGradeId(s);
    }
}