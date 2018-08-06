package com.xucc.javaweb.dao.impl;

import com.xucc.javaweb.dao.GradeDao;
import com.xucc.javaweb.entity.Grade;
import com.xucc.javaweb.model.PageBean;
import com.xucc.javaweb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GradeDaoImpl implements GradeDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Grade> gradeList(PageBean pageBean, Grade grade) {
        StringBuffer sb = new StringBuffer("select id,name,`desc` from t_grade");
        if (grade != null && StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and name like '%" + grade.getGradeName() + "%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        
        String sql = sb.toString().replaceFirst("and", "where");
        return jdbcTemplate.query(sql, new GradeRowMapper());
    }
    
    @Override
    public int gradeCount(Grade grade) {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
        if (StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and name like '%" + grade.getGradeName() + "%'");
        }
        String sql = sb.toString().replaceFirst("and", "where");
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    /**
     * delete from tableName where field in (1,3,5)
     */
    @Override
    public int gradeDelete(String delIds) {
        String sql = "delete from t_grade where id in(" + delIds + ")";
        return jdbcTemplate.update(sql);
    }
    
    @Override
    public int gradeAdd(Grade grade) {
        String sql = "insert into t_grade (name,`desc`)values(?,?)";
        return jdbcTemplate.update(sql, grade.getGradeName(), grade.getGradeDesc());
    }
    
    @Override
    public int gradeModify(Grade grade) {
        String sql = "update t_grade set name=?, `desc`=? where id=?";
        return jdbcTemplate.update(sql, grade.getGradeName(), grade.getGradeDesc(), grade.getId());
    }
    
    public static class GradeRowMapper implements RowMapper<Grade> {
        
        @Override
        public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
            Grade grade = new Grade();
            grade.setId(rs.getInt("id"));
            grade.setGradeName(rs.getString("name"));
            grade.setGradeDesc(rs.getString("desc"));
            return grade;
        }
    }
    
}
