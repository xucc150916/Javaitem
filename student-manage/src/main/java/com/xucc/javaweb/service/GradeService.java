package com.xucc.javaweb.service;

import com.xucc.javaweb.entity.Grade;
import com.xucc.javaweb.model.PageBean;

import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
public interface GradeService {
    
    List<Grade> gradeList(PageBean pageBean, Grade grade);
    
    int gradeCount(Grade grade);
    
    int saveGrade(Grade grade);
    
    int modifyGrade(Grade grade);
    
    int removeGrade(String delIds);
}
