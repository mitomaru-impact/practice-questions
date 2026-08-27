package test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
		// アプリのタイトルを設定
		test2 frame = new test2("http://www.mamazon.com/login");

		// ウィンドウに関する設定
		frame.setVisible(true);
	}

	public test2(String title) {

		setTitle(title);

		// ウィンドウの大きさ設定
		setBounds(100, 100, 600, 400);

		//ウィンドウの右にある×で閉じる
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// アプリのアイコンを設定する
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/test/mamazon_icon.png"));
		setIconImage(icon1.getImage());

		// 写真を設定する
		ImageIcon icon = new ImageIcon(getClass().getResource("/test/mamazon.png"));

		// 写真の大きさを設定する
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImg);

		// 写真をiconlabelというクラスに入れる
		JLabel iconlabel = new JLabel(resizedIcon);

		// ユーザーID文字と枠を設定
		JLabel idLabel = new JLabel("ユーザーID");
		JTextField idField = new JTextField(20);

		// パスワードの文字と枠を設定
		JLabel pwLabel = new JLabel("パスワード");
		JPasswordField pwField = new JPasswordField(20);

		// ログインのボタン設定 
		JButton loginButton = new JButton("ログイン");

		// ログイン時に出てくるウィンドウの設定
		// アプリのタイトルの設定
		loginButton.addActionListener(e -> {
			JFrame nextFrame = new JFrame("http://www.mamazon.com/login-success");
			
			// アプリのアイコンを設定する
			ImageIcon icon2 = new ImageIcon(getClass().getResource("/test/dokuro.png"));
			nextFrame.setIconImage(icon2.getImage());

			//ウィンドウの大きさを設定
			nextFrame.setBounds(150,150,500, 200);

			//ウィンドウの右にある×で閉じる
			nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			//中のメッセージを表示する設定
			JLabel label = new JLabel("あなたのユーザーIDとパスワードは無事ハッカーの元へ届きました！", JLabel.CENTER);
			nextFrame.add(label);

			nextFrame.setVisible(true);

		});

		// アプリ内にアイコンを配置する設定
		JPanel topPanel = new JPanel();
		topPanel.add(iconlabel);

		// アプリ内にメッセージを配置する設定
		JPanel idPanel = new JPanel();
		idPanel.add(idLabel);
		idPanel.add(idField);

		JPanel pwPanel = new JPanel();
		pwPanel.add(pwLabel);
		pwPanel.add(pwField);

		JPanel logPanel = new JPanel();
		logPanel.add(loginButton);

		// アプリ内のログインID、パスワード、ログインをまとめる設定
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3, 1));
		centerPanel.add(idPanel);
		centerPanel.add(pwPanel);
		centerPanel.add(logPanel);

		JPanel wrapperPanel = new JPanel(new GridBagLayout());
		wrapperPanel.add(centerPanel);

		// 中央揃えに関する設定
		Container contentPane = getContentPane();
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(wrapperPanel, BorderLayout.CENTER);
	}

}

// <先週の授業から追加した内容>

// 1. mamazonのロゴを追加しました。 
//    → 流石にamazonはマズいと思ったので、mamazonにしてみました。

// 2. 画像を相対パスで参照するようにしました。
//    → 友達から相対パスにしたほうが他のPCからでも画像が参照されていいと教えてもらったので変えてみました！

// 3. アプリのアイコンをオリジナルのものに変更しました。(一番苦戦した)
//    → 最初に左上のアイコンとタスクバーに出てくるアイコンを変えてみました。また、ログイン後のアプリアイコンも変わるようになっています。(いかにも詐欺サイト風ですね)
//		 ログイン後の画面のアイコンの変更が難しく、「nextFrame.」をつけ忘れていたせいだと気づいたので付け足しました。

// 4. アプリのタイトルを変更しました。
//    → 本物のWebサイトのようなURLに変更してみました。ちなみにログイン時とログイン後でタイトルが変わります。

// 5. ログインID、パスワード、ログインボタンがウィンドウサイズを変更しても中心に来るようにしました。
//    → 先週までは画面の大きさを変更してしまうと横一列に並んでしまっていたのですが、ウィンドウサイズを変更しても中心になるように変更しました。

// 6. 後で見返しても見やすいように動作に関するコメントを追加しました。
//	  → 誰が見てもわかりやすくを意識して書きました。分かりにくい所があれば修正したいのでFBお願いします。

// <今後やりたいこと>
// ・ウィンドウサイズを変更したときにmamazonのロゴがユーザーIDの入力欄と同じ幅で動くように変更したい。
// ・アプリ起動時に画面の中央に起動するように変更したい。

// <今回の感想>
// 初めて自分で一からアプリケーションを作ってみました。
// 今回の制作では分からない部分が多かったので、自分で調べたり、詳しい友達に聞いてみたり、AIに作り方を教えてもらったりで楽しく制作できました。
// 今回制作で使用したAIはGeminiを使用しました。コードを書かせるというよりは、分からないところを聞くという使い方ができた点は良かったと思います。
// 今回の制作で一番難しかった部分はアイコンやmamazon画像の表示です。
// 元々、絶対パスを使って参照をしていたので管理が難しく、スペルミスしていたせいで指定した画像が反映されないといった問題に直面しました。また、他の人の環境でこのサイトを使う際にもパスが違うと画像が反映されないといった問題も起こってしまいました。
// そんなとき、友達から教えてもらった相対パスを使うことで、管理が楽になり、他の人の環境でも正常に動作させることができました。
// 画像の部分が難しくて授業時間に間に合わなかったのは反省です。
// 今後はウィンドウサイズを変更してもmamazonのロゴとパスワード欄などのバランスが良くなるように修正することと、アプリ起動時に画面の中央に起動するようにしていきたいです。



