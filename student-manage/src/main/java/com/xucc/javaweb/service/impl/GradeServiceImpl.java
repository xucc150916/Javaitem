package com.xucc.javaweb.service.impl;

import com.xucc.javaweb.dao.GradeDao;
import com.xucc.javaweb.entity.Grade;
import com.xucc.javaweb.model.PageBean;
import com.xucc.javaweb.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/7/29
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    
    @Autowired
    private GradeDao gradeDao;
    
    @Override
    public List<Grade> gradeList(PageBean pageBean, Grade grade) {
        try {
            return gradeDao.gradeList(pageBean, grade);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    @Override
    public int gradeCount(Grade grade) {
        try {
            return gradeDao.gradeCount(grade);
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public int saveGrade(Grade grade) {
        try {
            return gradeDao.gradeAdd(grade);
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public int modifyGrade(Grade grade) {
        try {
            return gradeDao.gradeModify(grade);
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public int removeGrade(String delIds) {
        try {
            return gradeDao.gradeDelete(delIds);
        } catch (Exception e) {
            return 0;
        }
    }
}