package AIYA;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class FAA {

	public static void main(String[] args) {
		// GUIアプリケーションのタイトル名の設定と、JFrameのオブジェクトをインスタンス
		JFrame frame = new JFrame("MyTitle");
		
		//ウィンドウのx、y軸の設定、ウィンドウの幅、高さの設定
		frame.setBounds(100, 100, 728, 400);
		//ウィンドウ右上の閉じるボタンの設定
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//各ボタン等のオブジェクトの設定
		JButton btn1 = new JButton("North");
		JButton btn2 = new JButton("South");
		JButton btn3 = new JButton("West");
		JButton btn4 = new JButton("East");
		JTextField nameField = new JTextField(10);
		
		//btn4で押した場合呼び出されるメソッドの設定
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			JOptionPane.showMessageDialog(null,
			name + " さん、こんにちは！");
			}
		});

		//ウィンドウのどこにアイテムを配置するかの設定
		//第一引数が対象オブジェクトになり、第二引数が配置場所になる
		frame.getContentPane().add(btn1, BorderLayout.NORTH);
		frame.getContentPane().add(btn2, BorderLayout.SOUTH);
		frame.getContentPane().add(btn3, BorderLayout.WEST);
		frame.getContentPane().add(btn4, BorderLayout.EAST);
		frame.getContentPane().add(nameField, BorderLayout.CENTER);

		//JFrameオブジェクトの表示、非表示の設定
		frame.setVisible(true);

	}
}
