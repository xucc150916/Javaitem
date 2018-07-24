package com.xucc.spring3.common;

public class GuessNumber {

    private double number;

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "GuessNumber{" +
                "number=" + number +
                '}';
    }

}
