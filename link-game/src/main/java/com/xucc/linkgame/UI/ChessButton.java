package com.xucc.linkgame.UI;

import com.xucc.linkgame.Setting;
import com.xucc.linkgame.model.ArrayPoint;

import javax.swing.*;
import java.net.URL;

/**
 * 地图上各点图片的设置
 */
public class ChessButton extends JButton {
    protected ArrayPoint point = null;

    /**
     * 无参构造，创建一个默认点传入核心构造方法中
     */
    public ChessButton() {
        this(new ArrayPoint(0, 0, 0));
    }

    /**
     * 核心构造方法，传入指定点对象，与对应的图片文件关联
     * @param point
     */
    public ChessButton(ArrayPoint point) {
        this.point = point;
        // 传入点对应的图片文件名 TODO  这里的photo必须通过maven加载到classpath中
        String name = "photo/"+point.getValue()+Setting.RELEX;
        URL url = Setting.class.getResource(name);
//        System.out.println(url);
        ImageIcon icon = new ImageIcon(url);
        this.setIcon(icon);
    }

    /**
     * 传入点的属性，构建该点，然后传入核心构造中，与对应的图片文件相关联
     * @param row
     * @param col
     * @param value
     */
    public ChessButton(int row, int col, int value) {
        this(new ArrayPoint(row, col, value));
    }

    public ArrayPoint getPoint() {
        return point;
    }

    public void setPoint(ArrayPoint point) {
        this.point = point;
    }
}