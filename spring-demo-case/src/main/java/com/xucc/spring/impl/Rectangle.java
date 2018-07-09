package com.xucc.spring.impl;

import com.xucc.spring.common.Shape;

/**
 * 矩形
 */
public class Rectangle implements Shape {

    private final double width;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double computeArea() {
        return width * height;
    }

    public double computerSide() {
        return 2 * (width+height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", area=" + this.computeArea() +
                ", side=" + this.computerSide() +
                '}';
    }
}
