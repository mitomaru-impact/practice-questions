package test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JRadeoButton2 extends JFrame {

    // 結果を表示するラベル
    JLabel resultLabel;

    // コンピューターの手を表示するラベル
    JLabel computerLabel;

    // じゃんけんの手
    String[] hands = {"グー", "チョキ", "パー"};

    // 乱数を作る
    Random random = new Random();

    public JRadeoButton2() {

        // ウィンドウの設定
        setTitle("じゃんけんゲーム");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 結果表示
        resultLabel = new JLabel("あなたの手を選んでください");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        // コンピューターの手
        computerLabel = new JLabel("コンピューター：？");
        computerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        computerLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // ボタン用パネル
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        // グーボタン
        JButton rockButton = new JButton("グー");
        rockButton.setFont(new Font("SansSerif", Font.BOLD, 20));

        // チョキボタン
        JButton scissorsButton = new JButton("チョキ");
        scissorsButton.setFont(new Font("SansSerif", Font.BOLD, 20));

        // パーボタン
        JButton paperButton = new JButton("パー");
        paperButton.setFont(new Font("SansSerif", Font.BOLD, 20));

        // ボタンをパネルに追加
        buttonPanel.add(rockButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(paperButton);

        // グーボタンを押したとき
        rockButton.addActionListener(e -> playGame(0));

        // チョキボタンを押したとき
        scissorsButton.addActionListener(e -> playGame(1));

        // パーボタンを押したとき
        paperButton.addActionListener(e -> playGame(2));

        // ウィンドウに追加
        add(resultLabel, BorderLayout.NORTH);
        add(computerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 表示
        setVisible(true);
    }

    // ゲーム処理
    private void playGame(int playerHand) {

        // コンピューターの手をランダムに決める
        int computerHand = random.nextInt(3);

        // コンピューターの手を表示
        computerLabel.setText(
                "コンピューター：" + hands[computerHand]
        );

        // 勝敗判定
        if (playerHand == computerHand) {

            resultLabel.setText("あいこ！");

        } else if (
                (playerHand == 0 && computerHand == 1) ||
                (playerHand == 1 && computerHand == 2) ||
                (playerHand == 2 && computerHand == 0)
        ) {

            resultLabel.setText("あなたの勝ち！");

        } else {

            resultLabel.setText("あなたの負け！");
        }
    }

    public static void main(String[] args) {

        new JRadeoButton2();
    }
}