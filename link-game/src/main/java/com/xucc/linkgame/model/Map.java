package com.xucc.linkgame.model;

import com.xucc.linkgame.Setting;

import java.util.Random;

/**
 * 关于地图的操作
 */

public class Map {
    public static int LEFTCOUNT = Setting.ROWS * Setting.COLS;

    // 地图
    private int[][] map = new int[Setting.ROWS+2][Setting.COLS+2];

    // 出现的不同图片个数
    private  int maxKinds = 4;

    // 创建地图的时候在构造里进行初始化
    public Map() {
        init();
    }

    // 返回地图对象
    public int[][] getMap() {
        return this.map;
    }

    /**
     * 地图初始化
     */
    private void init() {
        int[] tempArr = new int[Setting.ROWS * Setting.COLS];
        int len = tempArr.length;

        // 四个点同一个图片，总共16组
        for(int i = 0; i < len/maxKinds; i++) {
            tempArr[i] = i+1;
            tempArr[i+1] = i+1;
            tempArr[i+2] = i+1;
            tempArr[i+3] = i+1;
        }

        // 打乱数组
        random(tempArr);

        // 将打乱的一位数组值填充到地图中，因为地图比实际数组大一圈，所以这里以1为起始坐标
        for(int i = 1; i <= Setting.ROWS; i++) {
            for(int j = 1; j <= Setting.COLS; j++) {
                this.map[i][j] = tempArr[(i-1)*Setting.COLS + (j-1)];
            }
        }
    }

    /**
     * 将传入的一位数组arr中所有的值进行打乱处理
     * @param arr 要打乱的数组
     */
    private void random(int[] arr) {
        Random random = new Random();
        int len = arr.length;
        for(int i = len; i > 0; i--) {
            // 产生一个0~i的随机数
            int j = random.nextInt(i);
            int tmp = arr[i-1];
            arr[i-1] = arr[j];
            arr[j] = tmp;
        }
    }

    /**
     * 判断两个点是否在一条直线上，斜线不算
     * @param p1
     * @param p2
     * @return true在，false不在
     */
    public boolean oneLineWithoutValue(ArrayPoint p1, ArrayPoint p2) {
        if(horizonMatch(p1, p2)) {
            return true;
        } else if(verticalMatch(p1, p2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断两个点是否在一条直线上，斜线不算，两点的值必须相等
     * @param p1
     * @param p2
     * @return
     */
    public boolean oneLine(ArrayPoint p1, ArrayPoint p2) {
        if(p1.value != p2.value) {
            return false;
        }
        if(oneLineWithoutValue(p1, p2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 垂直方向在一条线，且在垂直方向符合消除规则
     * @param p1
     * @param p2
     * @return
     */
    public boolean verticalMatch(ArrayPoint p1, ArrayPoint p2) {
        if(p1.j != p2.j) {
            return false;
        }

        // 交换两个点是为了计算的统一
        if(p1.value > p2.value) {
            ArrayPoint tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        // 两个点之间间隔的数
        int spaceCount = p2.i-p1.i;

        // 相邻且值相等直接返回true
        if(spaceCount==1 && p1.value==p2.value) {
            return true;
        }

        for(int i = p1.i+1; i < p2.i; i++) {
            ArrayPoint point  =new ArrayPoint(i, p1.j, this.map[i][p1.j]);
            if(point.value != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 水平方线一条线，且符合消除规则
     * @param p1
     * @param p2
     * @return
     */
    public boolean horizonMatch(ArrayPoint p1, ArrayPoint p2) {
        if(p1.i != p2.i) {
            return true;
        }

        if(p1.j > p2.j) {
            ArrayPoint tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        int spaceCount = p2.j-p1.j;
        if(spaceCount==1 && p1.value==p2.value) {
            return true;
        }

        for(int i = p1.j+1; i < p2.j; i++) {
            ArrayPoint point = new ArrayPoint(p1.i, i, map[p1.i][i]);
            if(point.value != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为同一个点
     * @param p1
     * @param p2
     * @return
     */
    private boolean isSameOne(ArrayPoint p1, ArrayPoint p2) {
        if(p1.i==p2.i && p1.j==p2.j) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 带一个拐点，判断是否相邻，不比较值是否相同
     * @param p1
     * @param p2
     * @return
     */
    public boolean oneConnerWithoutValue(ArrayPoint p1, ArrayPoint p2) {
        ArrayPoint p1Horizontal = new ArrayPoint(p1.i, p2.j, map[p1.i][p2.j]);
        ArrayPoint p1Vertical = new ArrayPoint(p2.i, p1.j, map[p2.i][p1.j]);

        if(horizonMatch(p1, p1Horizontal) && p1Horizontal.value==0) {
            // TODO
        }
    }
}
