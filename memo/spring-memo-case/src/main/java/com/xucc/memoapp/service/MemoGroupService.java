package com.xucc.memoapp.service;

import com.xucc.memoapp.entity.MemoGroup;

/**
 * memo表现层
 */

public interface MemoGroupService {

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    MemoGroup queryMemoGroupDetail(int id);

    /**
     * 添加便签组
     * @param memoGroup
     * @return
     */
    boolean addMemoGroupDetail(MemoGroup memoGroup);

    /**
     * 查询指定便签名称的便签组数量
     */
    int queryMemoGroupCountDetail(String name);

}
