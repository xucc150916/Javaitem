package com.xucc.spring.impl;

import com.xucc.spring.common.Shape;

/**
 * 三角形
 */
public class Triangle implements Shape {

    private final double a;
    private final double b;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    private final double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double computeArea() {
        double p = (a + b + c) / 2;
        double s = Math.sqrt((p - a) * (p - b) * (p - c)*p);
        return s;
    }
    public double computerSide() {
        return a + b + c;
    }

    @
            Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", ares=" + this.computeArea() +
                ", side" + this.computerSide() +
                '}';
    }

}
