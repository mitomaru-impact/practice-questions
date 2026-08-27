package test;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class test1 extends JFrame {

    public static void main(String[] args) {
        test1 frame = new test1("MyTitle");
        frame.setVisible(true);
    }

    test1(String title) {
        setTitle(title);
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPasswordField pass1 = new JPasswordField();
        pass1.setColumns(20);
        JPasswordField pass2 = new JPasswordField(10);

        JPanel p = new JPanel();
        p.add(pass1);
        p.add(pass2);

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);
    }
}