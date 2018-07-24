package com.xucc.spring2.assemble;

public class Human {
    public Female female;
    public Male male;

    public Human(Female female, Male male) {
        this.female = female;
        this.male = male;
    }

    @Override
    public String toString() {
        return "Human{" +
                "female=" + female +
                ", male=" + male +
                '}';
    }
}

