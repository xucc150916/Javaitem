package com.xucc.javaweb.dao;

import com.xucc.javaweb.entity.Grade;
import com.xucc.javaweb.model.PageBean;

import java.util.List;

public interface GradeDao {
    
    List<Grade> gradeList(PageBean pageBean, Grade grade) throws Exception;
    
    int gradeCount(Grade grade) throws Exception;

    int gradeDelete(String delIds) throws Exception;
    
    int gradeAdd(Grade grade) throws Exception;
    
    int gradeModify(Grade grade) throws Exception;
    
}
