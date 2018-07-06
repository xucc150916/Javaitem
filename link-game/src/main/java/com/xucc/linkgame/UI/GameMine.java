package com.xucc.linkgame.UI;

import com.xucc.linkgame.Setting;
import com.xucc.linkgame.model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMine extends JFrame {

    //炸弹的次数
    private int bombCount = Setting.BOMBCOUNT;

    private JPanel jContentPane = null;

    private JMenuBar menuBar = null;

    private JMenu fileMenu = null;

    private JMenuItem reloadItem = null;

    private JMenuItem startItem = null;
    //炸弹
    private JMenuItem bombItem = null;

    private JMenuItem exitItem = null;

    private MapUI mapUI = null;
    // 游戏开始时间
    private long startTime;

    // 结束时间
    private long endTime;

    private Timer timer = null;

    // private JMenuItem ti
    private JMenuBar initMenuBar()
    {
        if (menuBar == null)
        {
            menuBar = new JMenuBar();
            fileMenu = new JMenu("文件");

            startItem = new JMenuItem("开始游戏");
            startItem.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    reload();
                }
            });
            reloadItem = new JMenuItem("重来一次");
            reloadItem.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    reload();
                }
            });

            bombItem = new JMenuItem("炸弹");
            bombItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e )
                {
                    if( bombCount==0 )
                    {
                        JOptionPane.showMessageDialog(GameMine.this,"你已经没有炸弹可用了!!!" );
                        bombItem.setEnabled(false);
                        return;
                    }
                    mapUI.bomb();
                    bombCount--;
                }
            });
            exitItem = new JMenuItem("退出");
            exitItem.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });

            fileMenu.add(startItem);
            fileMenu.add(reloadItem);
            fileMenu.add( bombItem );
            fileMenu.add(exitItem);
            menuBar.add(fileMenu);
        }

        return menuBar;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // PAN_TODO 自动生成方法存根
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                GameMine thisClass = new GameMine();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    /**
     * This is the default constructor
     */
    public GameMine()
    {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize()
    {
        this.setSize(900, 900);
        this.setTitle("llk");
        this.setJMenuBar(initMenuBar());
        // this.setContentPane(getJContentPane());
        this.setTitle("连连看");
    }

    private void reload()
    {
        mapUI = new MapUI();
        startTime = System.currentTimeMillis() / 1000;

        endTime = startTime + Setting.PERTIME;

//        ContentPane.setVisible(true);
        jContentPane = new JPanel();
        jContentPane.setLayout(new BorderLayout());
        jContentPane.add(mapUI);

        this.setContentPane(jContentPane);
        this.validate();
        Map.LEFTCOUNT = Setting.ROWS * Setting.COLS;
        initTimer();

        bombItem.setEnabled(true);
        bombCount=Setting.BOMBCOUNT;
    }

    private void initTimer()
    {
        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startTime = System.currentTimeMillis() / 1000;

                if (startTime == endTime)
                {
                    JOptionPane.showMessageDialog(GameMine.this, "时间到了!!");
                    int result = JOptionPane.showConfirmDialog(GameMine.this,
                            "重玩一次?", "Again", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (result == JOptionPane.YES_OPTION)
                    {
                        reload();
                    }
                    else
                    {
                        jContentPane.setVisible(false);
                        validate();
                    }
                }
            }
        };
        timer = new javax.swing.Timer(1000, actionListener);
        timer.start();
        // TODO 倒计时的显示
    }

}



