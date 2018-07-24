package com.xucc.spring5.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MemoGroup {

    public int id;
    public String name;
    public Date createdTime;
    public Date modifyTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getModifyTime() {
        return modifyTime;
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
