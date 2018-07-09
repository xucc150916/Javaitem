package com.xucc.spring.impl;

import com.xucc.spring.common.Shape;

/**
 * 圆
 */
public class Circular implements Shape {
    private final double radius;

    public double getRadius() {
        return radius;
    }

    public Circular(double radius) {
        this.radius = radius;
    }

    public double computeArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double computerSide() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circular{" +
                " radius=" + radius +
                ", area=" + this.computeArea() +
                ", side=" + this.computerSide() +
                '}';
    }

}
