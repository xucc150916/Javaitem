package com.xucc.linkgame.UI;

import com.xucc.linkgame.Setting;
import com.xucc.linkgame.model.ArrayPoint;
import com.xucc.linkgame.model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.xucc.linkgame.model.Map.LEFTCOUNT;

/**
 * 棋盘图形化设置
 */
public class MapUI extends JPanel implements ActionListener {

    // 地图上点的数组
    private ChessButton[] chesses = null;

    private Map map = new Map();

    // 当前的点是否为第二次重复选中
    private boolean seccondSelect = false;

    // 第一次选择的点
    private ArrayPoint prePoint;
    // 第二次选择的点
    private ArrayPoint curPoint;

    /**
     * 构造
     */
    public MapUI() {
        super();
        initialize();
    }

    /**
     * 初始化
     */
    private void initialize() {
        // 初始化地图按钮
        initChesses();
        // 网格布局
        GridLayout gridLayout = new GridLayout(Setting.ROWS+2, Setting.COLS+2);
        // 设置网格元素水平间距
        gridLayout.setHgap(2);
        // 设置垂直间距
        gridLayout.setVgap(2);

        // 设置布局模式为网格布局
        this.setLayout(gridLayout);
        // 设置布局大小
        this.setSize(300, 300);

        // 放置网格按钮
        for(int row = 0; row < Setting.ROWS+2; row++) {
            for(int col = 0; col < Setting.COLS+2; col++) {
                add(chesses[row*(Setting.COLS+2)+col]);
            }
        }
    }

    /**
     * 将地图数组对应到图形化的点上，并且添加监视器
     */
    private void  initChesses() {
        int[][] values = map.getMap();

        // 创建图像化地图
        this.chesses = new ChessButton[(Setting.ROWS+2) * (Setting.COLS+2)];

        // 将map中的值对应到按钮中
        for(int row = 0; row < Setting.ROWS+2; row++) {
            for(int col = 0; col < Setting.COLS+2; col++) {
                int index = row*(Setting.COLS+2)+col;
                chesses[index] = new ChessButton(row, col, values[row][col]);
                // 添加监听器
                chesses[index].addActionListener(this);

                // 测试
//                System.out.println("chesses["+index+"]: "+values[row][col]);

                // 设置最外面一圈不可见
                if(row==0 || row==Setting.ROWS+1 || col==0 || col==Setting.COLS+1) {
                    // 测试
//                    System.out.println(row+", "+col);
                    chesses[index].setVisible(false);
                }
            }
        }

    }

    /**
     * 清除棋子
     * @param prePoint
     * @param curPoint
     */
    public void clearCheese(ArrayPoint prePoint, ArrayPoint curPoint) {
        int[][] values = map.getMap();

        // 将两个点的值置零，表示消除
        values[prePoint.getI()][prePoint.getJ()] = 0;
        values[curPoint.getI()][curPoint.getJ()] = 0;

        // 将这两个点不可见
        int index1 = prePoint.getI() * (Setting.COLS+2) + prePoint.getJ();
        int index2 = curPoint.getI() * (Setting.COLS+2) + curPoint.getJ();
        chesses[index1].setVisible(false);
        chesses[index2].setVisible(false);


        // 测试，打印消除的点
//        JOptionPane.showMessageDialog(this, "1th: ["+prePoint.getI()+", "+prePoint.getJ()+"]");
//        JOptionPane.showMessageDialog(this, "2th: ["+curPoint.getI()+", "+curPoint.getJ()+"]");

        // 总棋子数为0，游戏结束
        if (0 == LEFTCOUNT) {
            JOptionPane.showMessageDialog(this, "游戏胜利，请选择");
        }
        System.out.println(LEFTCOUNT);
    }

    /**
     * 监听器处理函数，在这里进行棋子的消除
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获得当前调用监听器的按钮
        ChessButton button = (ChessButton) e.getSource();
        // 获得按钮对应的点
        ArrayPoint point = button.getPoint();

        // 当前点是否为后一个点击到的点
        if(seccondSelect) {
            this.curPoint = point;
            if(map.match(this.prePoint, this.curPoint)) {
                clearCheese(this.prePoint, this.curPoint);
            }
            // 重新将标志置为false
            seccondSelect = false;
        } else {
            // 当前点是第一个被点击的
            this.prePoint = point;
            seccondSelect = true;
        }
    }

    public void bomb() {
        JOptionPane.showMessageDialog(this, "fuck");
        // TODO 使用炸弹
    }


}