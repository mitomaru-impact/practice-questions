package REISYO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ReisyoSimulator extends JFrame {

    //管理オブジェクト
    private final Gacha gacha = new Gacha();
    private final Statistics statistics = new Statistics();

    //結果表示
    private JLabel resultLabel;
    private JLabel rarityLabel;
    private JLabel messageLabel;

    //履歴表示
    private JTextArea historyArea;

    //統計表示
    private JLabel totalLabel;
    private JLabel ssrLabel;
    private JLabel srLabel;
    private JLabel normalLabel;
    private JLabel ssrRateLabel;
    private JLabel srRateLabel;
    private JLabel rareRateLabel;

    //履歴番号
    private int historyNumber = 0;

    // アプリ全体で引いた回数
    private int drawCount = 0;
    
    //起動
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ReisyoSimulator frame =
                    new ReisyoSimulator();

            frame.setVisible(true);
        });
    }

    //コンスト
    public ReisyoSimulator() {

        setTitle("冷笑ガチャシミュレーター");

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setSize(650, 800);

        setLocationRelativeTo(null);

        createGUI();
    }

    //gui
    private void createGUI() {


        // メイン

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15));

        add(mainPanel);

        // タイトル

        JLabel titleLabel =
                new JLabel(
                        "冷笑ガチャシミュレーター",
                        SwingConstants.CENTER);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28));

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH);

        // 中央

        JPanel centerPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                10,
                                10));

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER);

        // 操作

        JPanel operationPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                10));

        operationPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "ガチャ"));

        JButton singleButton =
                new JButton("単発");

        JButton tenButton =
                new JButton("10連");

        singleButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20));

        tenButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20));

        operationPanel.add(singleButton);
        operationPanel.add(tenButton);

        centerPanel.add(operationPanel);


        // 結果


        JPanel resultPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                1));

        resultPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "今回の結果"));

        resultLabel =
                new JLabel(
                        "-",
                        SwingConstants.CENTER);

        resultLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        40));

        rarityLabel =
                new JLabel(
                        "-",
                        SwingConstants.CENTER);

        rarityLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30));

        messageLabel =
                new JLabel(
                        "-",
                        SwingConstants.CENTER);

        messageLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20));

        resultPanel.add(resultLabel);
        resultPanel.add(rarityLabel);
        resultPanel.add(messageLabel);

        centerPanel.add(resultPanel);

        //統計

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                7,
                                1));

        statisticsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "統計"));

        totalLabel =
                new JLabel("総試行回数：0回");

        ssrLabel =
                new JLabel("SSR：0回");

        srLabel =
                new JLabel("SR：0回");

        normalLabel =
                new JLabel("その程度のゴミ：0回");

        ssrRateLabel =
                new JLabel("SSR排出率：0.00%");

        srRateLabel =
                new JLabel("SR排出率：0.00%");

        rareRateLabel =
                new JLabel("SR以上排出率：0.00%");

        statisticsPanel.add(totalLabel);
        statisticsPanel.add(ssrLabel);
        statisticsPanel.add(srLabel);
        statisticsPanel.add(normalLabel);
        statisticsPanel.add(ssrRateLabel);
        statisticsPanel.add(srRateLabel);
        statisticsPanel.add(rareRateLabel);

        centerPanel.add(statisticsPanel);


        // 履歴

        JPanel historyPanel =
                new JPanel(
                        new BorderLayout());

        historyPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "ガチャ履歴"));

        historyArea =
                new JTextArea();

        historyArea.setEditable(false);

        historyArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14));

        JScrollPane scrollPane =
                new JScrollPane(
                        historyArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        500,
                        180));

        historyPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        mainPanel.add(
                historyPanel,
                BorderLayout.SOUTH);


        // ボタン

        singleButton.addActionListener(e -> {
            playSingle();
        });

        tenButton.addActionListener(e -> {
            playTen();
        });
    }

  
    // 単発

    private void playSingle() {

        // 1回引く
        GachaResult result =
                gacha.draw();

        // 統計追加
        statistics.add(result);
        
        drawCount++;

        // 結果表示
        showResult(result);

        // 履歴追加
        addHistory(
                result.getCharacters(),
                result.getRarity());

        // 演出
        if (result.isSSR()) {

            showRareEffect(
                    result.getRarity());
        }

        // 統計表示更新
        updateStatisticsDisplay();
        
        checkFiftyDraws();
    }

   
    // 10連

    private void playTen() {

        //引く
        GachaResult[] results =
                gacha.drawTen();

        //統計追加
        for (GachaResult result : results) {

            statistics.add(result);
        }

        drawCount += 10;
        
        //結果表示
        showTenResults(results);

        //レアリティ取得
        String highestRarity =
                getHighestRarity(results);

        //記録
        addHistory(
                "10連",
                highestRarity);

        //演出
        if (highestRarity.equals("SSR")) {

            showRareEffect(
                    highestRarity);
        }

        //統計表示更新
        updateStatisticsDisplay();
        
        checkFiftyDraws();
    }


    // 結果表示
  
    private void showResult(
            GachaResult result) {

        resultLabel.setText(
                result.getCharacters());

        rarityLabel.setText(
                result.getRarity());

        switch (result.getRarity()) {

        case "SSR":

            rarityLabel.setForeground(
                    Color.RED);

            messageLabel.setForeground(
                    Color.ORANGE);

            messageLabel.setText(
                    "流石の冷笑、多分冷蔵庫より寒い");

            break;

        case "SR":

            rarityLabel.setForeground(
                    Color.BLUE);

            messageLabel.setForeground(
                    Color.CYAN);

            messageLabel.setText(
                    "どわーｗって感じ");

            break;

        default:

            rarityLabel.setForeground(
                    Color.BLACK);

            messageLabel.setForeground(
                    Color.BLACK);

            messageLabel.setText(
                    "よええ。雑種が");

            break;
        }
    }

  
    // 10連結果表示
  

    private void showTenResults(
            GachaResult[] results) {

        JDialog dialog =
                new JDialog(
                        this,
                        "10連結果",
                        true);

        dialog.setSize(
                520,
                600);

        dialog.setLocationRelativeTo(this);

    
        // メイン
       

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15));

        
        // タイトル

        JLabel titleLabel =
                new JLabel(
                        "10連結果",
                        SwingConstants.CENTER);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28));

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH);


        // 結果一覧 

        JPanel resultPanel =
                new JPanel(
                        new GridLayout(
                                10,
                                1,
                                5,
                                5));

        for (int i = 0;
                i < results.length;
                i++) {

            GachaResult result =
                    results[i];

            JLabel resultLabel =
                    new JLabel(
                            String.format(
                                    "%2d. %s → %s",
                                    i + 1,
                                    result.getCharacters(),
                                    result.getRarity()));

            resultLabel.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            18));

            // レアリティ色変更
            if (result.isSSR()) {

                resultLabel.setForeground(
                        Color.RED);

            } else if (result.isSR()) {

                resultLabel.setForeground(
                        Color.BLUE);

            } else {

                resultLabel.setForeground(
                        Color.BLACK);
            }

            resultPanel.add(resultLabel);
        }

        mainPanel.add(
                resultPanel,
                BorderLayout.CENTER);


        // OKボタン

        JButton okButton =
                new JButton("おお");

        okButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18));

        okButton.addActionListener(e -> {

            dialog.dispose();
        });

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(okButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH);

        dialog.add(mainPanel);

        dialog.setVisible(true);
    }

  
    // 10連時の最高レア
   
    private String getHighestRarity(
            GachaResult[] results) {

        GachaResult highest =
                results[0];

        for (GachaResult result : results) {

            if (result.getRarityRank()
                    > highest.getRarityRank()) {

                highest = result;
            }
        }

        return highest.getRarity();
    }

   
    // 履歴追加

    private void addHistory(
            String result,
            String rarity) {

        historyNumber++;

        historyArea.append(
                String.format(
                        "%02d. %s → %s%n",
                        historyNumber,
                        result,
                        rarity));
    }

  
    // 統計表示更新
  
    private void updateStatisticsDisplay() {

        totalLabel.setText(
                "総試行回数："
                + statistics.getTotal()
                + "回");

        ssrLabel.setText(
                "SSR："
                + statistics.getSSR()
                + "回");

        srLabel.setText(
                "SR："
                + statistics.getSR()
                + "回");

        normalLabel.setText(
                "その程度のゴミ："
                + statistics.getNormal()
                + "回");

        ssrRateLabel.setText(
                String.format(
                        "SSR排出率：%.2f%%",
                        statistics.getSSRRate()));

        srRateLabel.setText(
                String.format(
                        "SR排出率：%.2f%%",
                        statistics.getSRRate()));

        rareRateLabel.setText(
                String.format(
                        "SR以上排出率：%.2f%%",
                        statistics.getRareRate()));
    }

 
    // SR / SSR演出

    private void showRareEffect(
            String rarity) {

        JDialog dialog =
                new JDialog(
                        this,
                        "大冷笑",
                        true);

        dialog.setSize(
                420,
                280);

        dialog.setLocationRelativeTo(this);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                4,
                                1));

        JLabel starLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER);

        JLabel rarityLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER);

        JLabel messageLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER);

        JButton okButton =
                new JButton("ああ、そういう感じ？（笑）");

        if (rarity.equals("SSR")) {

            starLabel.setText(
                    "★★★★★");

            rarityLabel.setText(
                    "SSR！！");

            messageLabel.setText(
                    "教本の如き冷笑！！どわーｗｗ");

            starLabel.setForeground(
                    Color.ORANGE);

            rarityLabel.setForeground(
                    Color.RED);

        } else {

            starLabel.setText(
                    "☆☆☆☆☆");

            rarityLabel.setText(
                    "SR！！");

            messageLabel.setText(
                    "レア演出！");

            starLabel.setForeground(
                    Color.CYAN);

            rarityLabel.setForeground(
                    Color.BLUE);
        }

        starLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30));

        rarityLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        34));

        messageLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20));

        okButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16));

        okButton.addActionListener(e -> {

            dialog.dispose();
        });

        panel.add(starLabel);
        panel.add(rarityLabel);
        panel.add(messageLabel);
        panel.add(okButton);

        dialog.add(panel);

        dialog.setVisible(true);
    }
    
    //50連して冷笑できなかったら強制終了、要らんなら778行目だけ消すなりしろ
    private void checkFiftyDraws() {
    	
    	if (drawCount >= 50 && statistics.getSSR() == 0) {
    		showGameOver();
    	}
    }
    
    private void showGameOver() {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "50連しても冷笑できないとか終わってら、\n"
                + "頭に鉢巻でも巻いてろよ。",
                "熱血系は帰れ",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );

        System.exit(0);
    }
}

//package test;
//
//public class ExeTest1 {
// 
//	public static void main(String[] args) {
// 
//		String set = "";
// 
//		for (int i = 1; i < 31; i++) {
// 
//			int r = new java.util.Random().nextInt(3);
// 
//			String moji = "";
// 
//			switch (r) {
//			case 0:
//				moji = "う";
//				break;
//			case 1:
//				moji = "お";
//				break;
//			case 2:
//				moji = "ｗ";
//				break;
//			}
// 
//			System.out.print(moji);
// 
//			// 3文字セットに追加
//			set += moji;
// 
//			// 3文字ごとに判定
//			if (i % 3 == 0) {
// 
//				System.out.println();
// 
//				// SSR判定
//				if (set.equals("うおｗ")) {
//					System.out.println("SSR！");
//					System.out.println("激レア演出！");
//				}
// 
//				// SR判定
//				else if (set.equals("おうｗ") || set.equals("ｗｗｗ")) {
//					System.out.println("SR！");
//					System.out.println("レア演出！");
//				}
// 
//				// 次セット用にリセット
//				set = "";
// 
//				System.out.println();
//			}
//		}
//	}
//}