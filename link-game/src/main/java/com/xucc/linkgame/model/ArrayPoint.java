package com.xucc.linkgame.model;

/**
 * 定义地图中点的信息
 */

public class ArrayPoint {
    // 行
    protected int i;
    // 列
    protected int j;
    // 该点的值
    protected int value;

    // 通过构造传入点的各属性
    public ArrayPoint(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ArrayPoint{" +
                "i=" + i +
                ", j=" + j +
                ", value=" + value +
                '}';
    }

    /**
     * 各属性的get/set方法
     */
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
