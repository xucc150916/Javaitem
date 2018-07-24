package com.xucc.memoapp.service.impl;

import com.xucc.memoapp.dao.MemoGroupDao;
import com.xucc.memoapp.entity.MemoGroup;
import com.xucc.memoapp.service.MemoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.List;

/**
 * 表现层实现类
 */

@Service
@Transactional
public class MemoGroupServiceImpl implements MemoGroupService {

    @Autowired
    private MemoGroupDao memoGroupDao;


    /**
     * 通过id查询便签
     * @param id
     * @return
     */
    public MemoGroup queryMemoGroupDetail(int id) {

        // 非法输入
        if (id <= 0) {
            return null;
        }

        List<MemoGroup> memoGroupList = memoGroupDao.queryMemoGroup(id);

        if (memoGroupList.isEmpty()) {
            throw new IllegalArgumentException("id="+id+" not found a MemoGroup");
        }
        if (memoGroupList.size() > 0) {
            throw  new RuntimeException("id="+id+" found more than one MemoGroup");
        }
        return memoGroupList.get(0);
    }

    /**
     * 添加便签组
     * @param memoGroup
     * @return
     */
    public boolean addMemoGroupDetail(MemoGroup memoGroup) {
        // 输入为空
        if(memoGroup == null) {
            throw new IllegalArgumentException("MemoGroup arguments error");
        }
        // 参数为空
        if (StringUtils.isEmpty(memoGroup.getName())
                || StringUtils.isEmpty(memoGroup.getCreatedTime())) {
            throw new IllegalArgumentException("MemoGroup arguments error");
        }

        // 查看传入的name是否已经存在
        int count = memoGroupDao.queryMemoGroupByNameCount(memoGroup.getName());
        if(count > 0) {
            throw new RuntimeException("MemoGroup name already exists");
        } else {
            int effect = memoGroupDao.insertMemoGroup(memoGroup);
            // 插入成功返回true， 插入失败返回false
            return effect == 1;
        }

    }



    /**
     * 查询指定名称的便签组数量
     */
    public int queryMemoGroupCountDetail(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        int count = memoGroupDao.queryMemoGroupByNameCount(name);

        return count;
    }
}
