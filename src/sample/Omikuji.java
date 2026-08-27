// AIを使用しました
package sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Omikuji {
   public static void main(String[] args) {
	   
	   // おみくじ画面の作成と、サイズや閉じるときの設定
       JFrame frame = new JFrame("おみくじ");
       frame.setSize(400, 300);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // 画面に表示するタイトル、結果、ボタンを作成
       JLabel titleLabel = new JLabel("今日の運勢", JLabel.CENTER);
       JLabel resultLabel = new JLabel("おみくじを引いてください", JLabel.CENTER);
       JButton button = new JButton("おみくじを引く");
       
       // ボタンが押された時の処理を設定
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   // 乱数を作成しその数字によっておみくじの結果を決定し、その結果を表示する
               Random random = new Random();
               int result = random.nextInt(5);
               if (result == 0) {
            	// 大吉が出た時、画面の背景を赤色にして3回点滅させる
            	   resultLabel.setText("大吉");
            	   Timer timer = new Timer(200, null);
            	   final int[] count = {0};
            	   timer.addActionListener(new ActionListener() {
            	      public void actionPerformed(ActionEvent e) {
            	          if (count[0] % 2 == 0) {
            	              frame.getContentPane().setBackground(Color.RED);
            	          } else {
            	              frame.getContentPane().setBackground(Color.WHITE);
            	          }
            	          count[0]++;
            	          if (count[0] >= 6) {
            	              ((Timer) e.getSource()).stop();
            	              frame.getContentPane().setBackground(Color.WHITE);
            	          }
            	      }
            	   });
            	   timer.start();
            	} else if (result == 1) {
            	   resultLabel.setText("中吉");
            	} else if (result == 2) {
            	   resultLabel.setText("小吉");
            	} else if (result == 3) {
            	   resultLabel.setText("吉");
            	} else {
            		// 凶が出た時、画面の背景を1秒間青色にする
            		   resultLabel.setText("凶");
            		   frame.getContentPane().setBackground(Color.BLUE);
            		   Timer timer = new Timer(1000, new ActionListener() {
            		       public void actionPerformed(ActionEvent e) {
            		           frame.getContentPane().setBackground(Color.WHITE);
            		           ((Timer) e.getSource()).stop();
            		       }
            		   });
            		   timer.setRepeats(false);
            		   timer.start();
            	}
           }
       });
       
       // 部品を画面の上・中央・下に配置する
       frame.add(titleLabel, BorderLayout.NORTH);
       frame.add(resultLabel, BorderLayout.CENTER);
       frame.add(button, BorderLayout.SOUTH);
       
       // ウィンドウを画面の中央に表示する
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
   }
}