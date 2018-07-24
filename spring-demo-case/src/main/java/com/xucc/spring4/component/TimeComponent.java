package com.xucc.spring4.component;

/**
 * 记录时间
 */

public class TimeComponent {

    public long startTime() {
        return System.currentTimeMillis();
    }

    public long endTime() {
        return System.currentTimeMillis();
    }

    public void useTime(long start, long end) {
        System.out.println("use "+(end-start)+"ms");
    }

}
