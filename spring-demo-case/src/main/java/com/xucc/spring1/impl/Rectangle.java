package com.xucc.spring1.impl;

import com.xucc.spring1.common.Shape;

/**
 * 矩形
 */
public class Rectangle implements Shape {

    // 长
    private final double width;

    // 宽
    private final double height;

    /**
     * 通过构造传入矩形的长宽
     * @param width
     * @param height
     */
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 返回矩形面积
     * S = w*h
     * @return
     */
    @Override
    public double computerArea() {
        return width * height;
    }

    /**
     * 返回矩形周长
     * C = 2*(w+h)
     * @return
     */
    @Override
    public double computerSide() {
        return 2 * (width+height);
    }

    /**
     * get方法
     * @return
     */
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", area=" + this.computerArea() +
                ", side=" + this.computerSide() +
                '}';
    }
}
