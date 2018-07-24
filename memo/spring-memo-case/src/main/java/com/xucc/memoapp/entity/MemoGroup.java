package com.xucc.memoapp.entity;

import java.util.Date;

public class MemoGroup {

    /**
     * 便签组id，主键
     */
    private Integer id;

    /**
     * 便签组名称
     */
    private String name;

    /**
     * 便签组创建时间，不能为空
     */
    private Date createdTime;

    /**
     * 便签组最近一次的修改时间
     */
    private Date modifyTime;


    /**
     * get，set方法
     * @return
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "MemoGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdTime=" + createdTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
