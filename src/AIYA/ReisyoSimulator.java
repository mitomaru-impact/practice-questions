package AIYA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

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

    // ランダム生成
    private final Random random = new Random();

    // 統計情報
    private int totalCount = 0;
    private int ssrCount = 0;
    private int srCount = 0;
    private int normalCount = 0;

    // GUI部品
    private JLabel resultLabel;
    private JLabel rarityLabel;
    private JLabel messageLabel;

    private JTextArea historyArea;

    private JLabel totalLabel;
    private JLabel ssrLabel;
    private JLabel srLabel;
    private JLabel normalLabel;
    private JLabel ssrRateLabel;
    private JLabel srRateLabel;
    private JLabel highRateLabel;

    private int historyNumber = 0;

    /**
     * 起動
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReisyoSimulator frame = new ReisyoSimulator();
            frame.setVisible(true);
        });
    }

    /**
     * コンストラクタ
     */
    public ReisyoSimulator() {

        setTitle("冷笑シミュレーター");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 750);
        setLocationRelativeTo(null);

        createGUI();
    }

    /**
     * GUIを作成
     */
    private void createGUI() {

        // =========================
        // メインパネル
        // =========================
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(
                15, 15, 15, 15));

        add(mainPanel);

        // =========================
        // タイトル
        // =========================
        JLabel titleLabel = new JLabel(
                "冷笑シミュレーター",
                SwingConstants.CENTER);

        titleLabel.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                28));

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // =========================
        // 中央エリア
        // =========================
        JPanel centerPanel = new JPanel(
                new GridLayout(3, 1, 10, 10));

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // -------------------------
        // 操作エリア
        // -------------------------
        JPanel operationPanel = new JPanel(
                new GridLayout(1, 2, 10, 10));

        JButton singleButton = new JButton("単発");
        JButton tenButton = new JButton("10連");

        singleButton.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                20));

        tenButton.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                20));

        operationPanel.add(singleButton);
        operationPanel.add(tenButton);

        centerPanel.add(operationPanel);

        // -------------------------
        // 結果エリア
        // -------------------------
        JPanel resultPanel = new JPanel(
                new GridLayout(3, 1));

        resultPanel.setBorder(
                BorderFactory.createTitledBorder("結果"));

        resultLabel = new JLabel(
                "-",
                SwingConstants.CENTER);

        resultLabel.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                36));

        rarityLabel = new JLabel(
                "-",
                SwingConstants.CENTER);

        rarityLabel.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                28));

        messageLabel = new JLabel(
                "-",
                SwingConstants.CENTER);

        messageLabel.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                20));

        resultPanel.add(resultLabel);
        resultPanel.add(rarityLabel);
        resultPanel.add(messageLabel);

        centerPanel.add(resultPanel);

        // -------------------------
        // 統計エリア
        // -------------------------
        JPanel statisticsPanel = new JPanel(
                new GridLayout(7, 1));

        statisticsPanel.setBorder(
                BorderFactory.createTitledBorder("統計"));

        totalLabel = new JLabel("総試行回数：0回");
        ssrLabel = new JLabel("SSR：0回");
        srLabel = new JLabel("SR：0回");
        normalLabel = new JLabel("通常：0回");
        ssrRateLabel = new JLabel("SSR排出率：0.00%");
        srRateLabel = new JLabel("SR排出率：0.00%");
        highRateLabel = new JLabel("SR以上排出率：0.00%");

        statisticsPanel.add(totalLabel);
        statisticsPanel.add(ssrLabel);
        statisticsPanel.add(srLabel);
        statisticsPanel.add(normalLabel);
        statisticsPanel.add(ssrRateLabel);
        statisticsPanel.add(srRateLabel);
        statisticsPanel.add(highRateLabel);

        centerPanel.add(statisticsPanel);

        // =========================
        // 履歴
        // =========================
        JPanel historyPanel = new JPanel(
                new BorderLayout());

        historyPanel.setBorder(
                BorderFactory.createTitledBorder("ガチャ履歴"));

        historyArea = new JTextArea();

        historyArea.setEditable(false);

        historyArea.setFont(new Font(
                "Monospaced",
                Font.PLAIN,
                14));

        JScrollPane scrollPane =
                new JScrollPane(historyArea);

        scrollPane.setPreferredSize(
                new Dimension(500, 180));

        historyPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        mainPanel.add(
                historyPanel,
                BorderLayout.SOUTH);

        // =========================
        // ボタン処理
        // =========================

        singleButton.addActionListener(e -> {
            playSingle();
        });

        tenButton.addActionListener(e -> {
            playTen();
        });
    }

    /**
     * 単発ガチャ
     */
    private void playSingle() {

        GachaResult result = generateGacha();

        // 結果表示
        showResult(result);

        // 統計更新
        updateStatistics(result.rarity);

        // 履歴
        historyNumber++;

        historyArea.append(
                String.format(
                        "%02d. %s → %s%n",
                        historyNumber,
                        result.characters,
                        result.rarity));

        // SR以上なら演出
        if (result.rarity.equals("SSR")
                || result.rarity.equals("SR")) {

            showRareEffect(result.rarity);
        }
    }

    /**
     * 10連ガチャ
     */
    private void playTen() {

        GachaResult[] results = new GachaResult[10];

        String highestRarity = "通常";

        // 10回抽選
        for (int i = 0; i < 10; i++) {

            results[i] = generateGacha();

            // 統計は10回分すべて更新
            updateStatistics(results[i].rarity);

            // 最高レアリティを判定
            if (results[i].rarity.equals("SSR")) {

                highestRarity = "SSR";

            } else if (
                    results[i].rarity.equals("SR")
                    && highestRarity.equals("通常")) {

                highestRarity = "SR";
            }
        }

        // 10個の結果を表示
        showTenResults(results);

        // 履歴には最高レアリティだけ記録
        historyNumber++;

        historyArea.append(
                String.format(
                        "%02d. 10連 → %s%n",
                        historyNumber,
                        highestRarity));

        // SR以上なら演出
        if (highestRarity.equals("SSR")
                || highestRarity.equals("SR")) {

            showRareEffect(highestRarity);
        }
    }

    /**
     * ガチャ1回分を生成
     */
    private GachaResult generateGacha() {

        StringBuilder set =
                new StringBuilder();

        for (int i = 0; i < 3; i++) {

            int r = random.nextInt(3);

            switch (r) {

            case 0:
                set.append("う");
                break;

            case 1:
                set.append("お");
                break;

            case 2:
                set.append("ｗ");
                break;
            }
        }

        String characters =
                set.toString();

        String rarity =
                judgeRarity(characters);

        return new GachaResult(
                characters,
                rarity);
    }
    
    private void showTenResults(
            GachaResult[] results) {

        JDialog dialog =
                new JDialog(
                        this,
                        "10連結果",
                        true);

        dialog.setSize(500, 600);

        dialog.setLocationRelativeTo(this);

        // メインパネル
        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15));

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

        // 結果表示用パネル
        JPanel resultPanel =
                new JPanel(
                        new GridLayout(10, 1, 5, 5));

        // 10個の結果を表示
        for (int i = 0; i < results.length; i++) {

            GachaResult result = results[i];

            JLabel resultLabel =
                    new JLabel(
                            String.format(
                                    "%2d. %s → %s",
                                    i + 1,
                                    result.characters,
                                    result.rarity));

            resultLabel.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            18));

            // レアリティによって色を変える
            if (result.rarity.equals("SSR")) {

                resultLabel.setForeground(
                        Color.RED);

            } else if (
                    result.rarity.equals("SR")) {

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
                new JButton("OK");

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

    /**
     * レアリティ判定
     */
    private String judgeRarity(
            String set) {

        // SSR
        if (set.equals("うおｗ")) {
            return "SSR";
        }

        // SR
        if (set.equals("おうｗ")
                || set.equals("おおｗ")
                || set.equals("ｗｗｗ")) {

            return "SR";
        }

        // 通常
        return "通常";
    }

    /**
     * 結果表示
     */
    private void showResult(
            GachaResult result) {

        resultLabel.setText(
                result.characters);

        rarityLabel.setText(
                result.rarity);

        if (result.rarity.equals("SSR")) {

            rarityLabel.setForeground(
                    Color.RED);

            messageLabel.setForeground(
                    Color.ORANGE);

            messageLabel.setText(
                    "激レア演出！");

        } else if (
                result.rarity.equals("SR")) {

            rarityLabel.setForeground(
                    Color.BLUE);

            messageLabel.setForeground(
                    Color.CYAN);

            messageLabel.setText(
                    "レア演出！");

        } else {

            rarityLabel.setForeground(
                    Color.BLACK);

            messageLabel.setForeground(
                    Color.BLACK);

            messageLabel.setText(
                    "通常");
        }
    }

    /**
     * 統計を更新
     */
    private void updateStatistics(
            String rarity) {

        totalCount++;

        switch (rarity) {

        case "SSR":
            ssrCount++;
            break;

        case "SR":
            srCount++;
            break;

        default:
            normalCount++;
            break;
        }

        updateStatisticsDisplay();
    }

    /**
     * 統計表示を更新
     */
    private void updateStatisticsDisplay() {

        totalLabel.setText(
                "総試行回数："
                + totalCount
                + "回");

        ssrLabel.setText(
                "SSR："
                + ssrCount
                + "回");

        srLabel.setText(
                "SR："
                + srCount
                + "回");

        normalLabel.setText(
                "通常："
                + normalCount
                + "回");

        if (totalCount == 0) {
            return;
        }

        double ssrRate =
                (double) ssrCount
                / totalCount * 100;

        double srRate =
                (double) srCount
                / totalCount * 100;

        double highRate =
                (double) (ssrCount + srCount)
                / totalCount * 100;

        ssrRateLabel.setText(
                String.format(
                        "SSR排出率：%.2f%%",
                        ssrRate));

        srRateLabel.setText(
                String.format(
                        "SR排出率：%.2f%%",
                        srRate));

        highRateLabel.setText(
                String.format(
                        "SR以上排出率：%.2f%%",
                        highRate));
    }

    /**
     * SR / SSR演出
     */
    private void showRareEffect(
            String rarity) {

        JDialog dialog =
                new JDialog(
                        this,
                        "レア演出",
                        true);

        dialog.setSize(400, 250);

        dialog.setLocationRelativeTo(
                this);

        JPanel panel =
                new JPanel(
                        new GridLayout(3, 1));

        JLabel starLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER);

        JLabel rarityEffectLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER);

        JLabel messageEffectLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER);

        if (rarity.equals("SSR")) {

            starLabel.setText(
                    "★★★★★");

            rarityEffectLabel.setText(
                    "SSR！！");

            messageEffectLabel.setText(
                    "激レア演出！！");

            starLabel.setForeground(
                    Color.ORANGE);

            rarityEffectLabel.setForeground(
                    Color.RED);

        } else {

            starLabel.setText(
                    "☆☆☆☆☆");

            rarityEffectLabel.setText(
                    "SR！！");

            messageEffectLabel.setText(
                    "レア演出！");

            starLabel.setForeground(
                    Color.CYAN);

            rarityEffectLabel.setForeground(
                    Color.BLUE);
        }

        starLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30));

        rarityEffectLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32));

        messageEffectLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20));

        panel.add(starLabel);
        panel.add(rarityEffectLabel);
        panel.add(messageEffectLabel);

        dialog.add(panel);

        dialog.setVisible(true);
    }

    /**
     * ガチャ結果を保持するクラス
     */
    private static class GachaResult {

        String characters;
        String rarity;

        GachaResult(
                String characters,
                String rarity) {

            this.characters = characters;
            this.rarity = rarity;
        }
    }
}