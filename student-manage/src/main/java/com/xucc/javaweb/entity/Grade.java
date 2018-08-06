package com.xucc.javaweb.entity;

import lombok.Data;

@Data
public class Grade {
    
    /**
     * 编号
     */
    private int id;
    
    /**
     * 班级名称
     */
    private String gradeName;
    
    /**
     * 班级备注
     */
    private String gradeDesc;
}
