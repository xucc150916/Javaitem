package com.xucc.spring5.dao;

import com.xucc.spring5.entity.MemoGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通过JdbcTemplate操作数据库
 */

@Component
public class JdbcTemplateOperation {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateOperation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 查询
     */
    public void select() {
        MemoGroup memoGroup = jdbcTemplate.queryForObject(
                "select * from memo_group where id = ?",

                new RowMapper<MemoGroup>() {
                    @Override
                    public MemoGroup mapRow(ResultSet resultSet, int i) throws SQLException {
                        MemoGroup memoGroup = new MemoGroup();
                        memoGroup.setId(resultSet.getInt("id"));
                        memoGroup.setName(resultSet.getString("name"));
                        memoGroup.setCreatedTime(resultSet.getDate("created_time"));
                        memoGroup.setModifyTime(resultSet.getDate("modify_time"));
                        return memoGroup;
                    }
                },
                1
        );
        System.out.println(memoGroup);
    }
}
