package com.xucc.spring1.impl;

import com.xucc.spring1.common.Shape;

/**
 * 圆形
 */
public class Circular implements Shape {
    // 圆半径
    private final double radius;

    /**
     * 获得圆的半径
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     * 通过构造方法传入圆的半径
     * @param radius
     */
    public Circular(double radius) {
        this.radius = radius;
    }

    /**
     * 返回圆的面积
     * S = π*r*r
     * @return
     */
    @Override
    public double computerArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * 返回圆的周长
     * C = 2*π*r
     * @return
     */
    @Override
    public double computerSide() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circular{" +
                " radius=" + radius +
                ", area=" + this.computerArea() +
                ", side=" + this.computerSide() +
                '}';
    }

}
