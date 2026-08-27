package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class JRadeoButton extends JFrame {

    public static void main(String args[]) {

        JRadeoButton frame = new JRadeoButton("MyTitle");

        frame.setVisible(true);
    }

    JRadeoButton(String title) {

        setTitle(title);

        setBounds(100, 100, 600, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JRadioButton radio1 = new JRadioButton("りんご");
        JRadioButton radio2 = new JRadioButton("みかん");
        JRadioButton radio3 = new JRadioButton("ぶどう");

        // 文字の色を設定
        radio1.setForeground(Color.RED);
        radio2.setForeground(Color.BLUE);
        radio3.setForeground(Color.GREEN);

        ButtonGroup bgroup = new ButtonGroup();

        bgroup.add(radio1);
        bgroup.add(radio2);
        bgroup.add(radio3);

        JPanel p = new JPanel();

        p.add(radio1);
        p.add(radio2);
        p.add(radio3);

        Container contentPane = getContentPane();

        contentPane.add(p, BorderLayout.CENTER);
    }
}