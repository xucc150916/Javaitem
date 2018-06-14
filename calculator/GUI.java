package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * 实现计算器界面
 */
public class GUI {
    public static void main(String[] args) {


        // 框架
        JFrame jFrame = new JFrame("计算器");
        // 容器
        Container container = jFrame.getContentPane();

        // 创建面板
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();

        // 界面为网格布局4行4列
        jPanel1.setLayout(new GridLayout(4, 4));
        // 流布局
        jPanel2.setLayout(new FlowLayout());

        // 创建标签，内容为 0
        JLabel jLabel = new JLabel("0");
        container.add(jPanel1, BorderLayout.NORTH);

        // 创建按钮
        JButton[] jButtons = new JButton[16];
        String[] jButton_name = {
                "AC", "(", ")", "+",
                "7",  "8", "9", "-",
                "4",  "5", "6", "*",
                "1",  "2", "3", "/"
        };

        // 创建监视器实例
        Monitor  monitor = new Monitor(jLabel);

        for(int i = 0; i < jButtons.length; i++) {
            jButtons[i] = new JButton(jButton_name[i]);
            jButtons[i].addActionListener(monitor);
            jPanel1.add(jButtons[i]);
        }

        JButton jButton_0 = new JButton("0");
        jButton_0.addActionListener(monitor);
        jPanel2.add(jButton_0);

        JButton jButton_eq = new JButton("=");
        jButton_eq.addActionListener(monitor);
        jPanel2.add(jButton_eq);

        container.add(jLabel,BorderLayout.NORTH);
        container.add(jPanel1, BorderLayout.CENTER);
        container.add(jPanel2,BorderLayout.SOUTH);
        jFrame.setBounds(800, 170, 260, 360);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("关闭");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }
}
