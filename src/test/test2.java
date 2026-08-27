package test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class test2 extends JFrame {

	public static void main(String[] args) {
		// GUIアプリのタイトル設定と、JFrameのオブジェクトをインスタンス
		test2 frame = new test2("https://www.mamazon.com/signin");
		
		// ウィンドウのY軸とX軸の設定と、ウィンドウの幅と高さの設定になります。
		frame.setVisible(true);
	}

	public test2(String title) {

		setTitle(title);
		
		// ウィンドウのY軸とX軸の設定と、ウィンドウの幅と高さの設定になります
		setBounds(100, 100, 600, 400);
		
		// ウィンドウの右にある×閉じるに関する記述です。
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon("F:\\Java Web Programming\\08 0709\\eito\\src\\test\\mamazon.png");
		
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImg);
		
		 JLabel iconlabel = new JLabel(resizedIcon);
		 
		JLabel idLabel = new JLabel("ユーザーID");
		JTextField idField = new JTextField(20);

		JLabel pwLabel = new JLabel("パスワード");
		JPasswordField pwField = new JPasswordField(20);
		
		JButton loginButton = new JButton("ログイン");

		loginButton.addActionListener(e -> {
		    JFrame nextFrame = new JFrame("ログイン成功？");

		    nextFrame.setBounds(150,150,500, 200);
		    nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    JLabel label = new JLabel("あなたのユーザーIDとパスワードは無事ハッカーの元へ届きました！", JLabel.CENTER);
		    nextFrame.add(label);

		    nextFrame.setVisible(true);
		    
		});
		
		
		JPanel topPanel = new JPanel();
		topPanel.add(iconlabel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.add(idLabel); // 入力フォーム等は中央のパネルへ
		centerPanel.add(idField);
		centerPanel.add(pwLabel);
		centerPanel.add(pwField);
		centerPanel.add(loginButton);

		Container contentPane = getContentPane();
		contentPane.add(topPanel, BorderLayout.NORTH);   // 画像パネルを一番上に配置
		contentPane.add(centerPanel, BorderLayout.CENTER); // 入力フォームを中央に配置
	}
	
}