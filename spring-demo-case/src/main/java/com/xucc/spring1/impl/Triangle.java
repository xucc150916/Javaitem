package com.xucc.spring1.impl;

import com.xucc.spring1.common.Shape;

/**
 * 三角形
 */
public class Triangle implements Shape {

    /**
     * 三角形的三边
     */
    private final double a;
    private final double b;
    private final double c;

    /**
     * 通过构造传入三角形的三边
     * @param a
     * @param b
     * @param c
     */
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * 返回三角形的面积，使用海伦公式
     * S = S=√[p(p-a)(p-b)(p-c)]
     * p = 0.5(a+b+c) 半周长
     * @return
     */
    @Override
    public double computerArea() {
        double p = (a + b + c) / 2;
        double s = Math.sqrt((p - a) * (p - b) * (p - c)*p);
        return s;
    }

    /**
     * 返回三角形周长
     * @return
     */
    @Override
    public double computerSide() {
        return a + b + c;
    }

    /**
     * getter方法
     * @return
     */
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", ares=" + this.computerArea() +
                ", side" + this.computerSide() +
                '}';
    }

}
