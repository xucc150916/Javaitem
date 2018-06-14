package test;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 监视器

public class Monitor implements ActionListener {
    private String bds = "";
    private JLabel jLabel;

    public Monitor(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commend = e.getActionCommand();

        if("AC".equals(commend)) {
            bds = "";
            jLabel.setText("0");
        } else if("=".equals(commend)) {
            InversePolish inversePolish = new InversePolish(bds);
            int result = inversePolish.calculator();

            System.out.println(result);
            bds = String.valueOf(result);
            jLabel.setText(bds);
            System.out.println("-> -> :"+ bds);
        } else {
            bds += commend;
            System.out.println("-> :"+bds);
            jLabel.setText(bds);
        }
    }
}
