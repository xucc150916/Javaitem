package com.xucc.memoapp.dao.impl;

import com.xucc.memoapp.dao.MemoGroupDao;
import com.xucc.memoapp.entity.MemoGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;



@Repository
public class MemoGroupDaoImpl implements MemoGroupDao {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(MemoGroupDaoImpl.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 给便签组插入一个便签
     */
    public int insertMemoGroup(MemoGroup memoGroup) {
        String sql = "insert into memo_group(name, created_time) values (?, ?)";
        int effect = jdbcTemplate.update(
                sql, memoGroup.getName(), memoGroup.getCreatedTime()
        );
        return effect;
    }

    /**
     * 查询指定名称的便签组数量
     */
    public int queryMemoGroupByNameCount(String name) {
        String sql = "select count(id) from memo_group where name=?";
        int count = jdbcTemplate.queryForObject(
                sql, new Object[]{name}, Integer.class
        );
        return count;
    }

    /**
     * 根据id修改便签组的名称
     */
    public int updateMemoGroup(int id, String name) {
        return 0;
    }

    /**
     * 根据id查询便签组信息
     */
    public List<MemoGroup> queryMemoGroup(int id) {
        String sql = "select id, name, created_time, modify_time from memo_group where id=?";
        List<MemoGroup> memoGroups = jdbcTemplate.query(
                sql, new Object[]{id}, new
                        RowMapper<MemoGroup>() {
                            public MemoGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                                MemoGroup memoGroup = new MemoGroup();

                                memoGroup.setId(rs.getInt("id"));
                                memoGroup.setName(rs.getString("name"));
                                memoGroup.setCreatedTime(rs.getDate("created_time"));
                                memoGroup.setModifyTime(rs.getDate("modify_time"));

                                return memoGroup;
                            }
                        });
        logger.debug("queryMemoGroup: id = {}, result = {}", id, memoGroups);
        return memoGroups;
    }

    public List<MemoGroup> queryMemoGroupByCreatedTime(Date startTime, Date endTime) {
        return null;
    }

    public int deleteMemoGroup(int id) {
        return 0;
    }
}
