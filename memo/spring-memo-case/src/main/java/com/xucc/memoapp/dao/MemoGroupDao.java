package com.xucc.memoapp.dao;

import com.xucc.memoapp.entity.MemoGroup;

import java.util.Date;
import java.util.List;

/**
 * 便签组的数据层(DAO)访问接口
 */

public interface MemoGroupDao {

    /**
     * 给便签组插入一个便签
     *
     * @param memoGroup
     * @return
     */
    int insertMemoGroup(MemoGroup memoGroup);

    /**
     * 查询指定名称的便签组数量
     *
     * @param name
     * @return
     */
    int queryMemoGroupByNameCount(String name);

    /**
     * 根据id修改便签组的名称
     *
     * @param id
     * @param name
     * @return
     */
    int updateMemoGroup(int id, String name);

    /**
     * 根据id查询便签组信息
     *
     * @param id
     * @return
     */
    List<MemoGroup> queryMemoGroup(int id);

    /**
     * 根据创建时间范围查询便签信息
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<MemoGroup> queryMemoGroupByCreatedTime(Date startTime, Date endTime);

    /**
     * 根据id删除便签组
     *
     * @param id
     * @return
     */
    int deleteMemoGroup(int id);
}
