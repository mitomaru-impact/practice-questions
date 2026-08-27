package sample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Omikuji {
   public static void main(String[] args) {
	   
       JFrame frame = new JFrame("おみくじ");
       frame.setSize(400, 300);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       JLabel titleLabel = new JLabel("今日の運勢", JLabel.CENTER);
       JLabel resultLabel = new JLabel("おみくじを引いてください", JLabel.CENTER);
       JButton button = new JButton("おみくじを引く");
       
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               Random random = new Random();
               int result = random.nextInt(5);
               if (result == 0) {
                   resultLabel.setText("大吉");
               } else if (result == 1) {
                   resultLabel.setText("中吉");
               } else if (result == 2) {
                   resultLabel.setText("小吉");
               } else if (result == 3) {
                   resultLabel.setText("吉");
               } else {
                   resultLabel.setText("凶");
               }
           }
       });
       
       frame.add(titleLabel, BorderLayout.NORTH);
       frame.add(resultLabel, BorderLayout.CENTER);
       frame.add(button, BorderLayout.SOUTH);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
   }
}