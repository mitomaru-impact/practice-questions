package sample;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * JFrame を使ったヨーロピアンルーレット（0〜36）シミュレーター。
 * SPIN ボタンを押すとホイールが回転し、減速しながら停止して結果を表示する。
 */
public class MawasuGame extends JFrame {

    // ヨーロピアンルーレットの標準的な数字配置（0〜36が1つずつ、計37マス）
    private static final int[] WHEEL_ORDER = {
    		1,3,5,7,9,11,13,15,17,19,21,23,
    		2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22
            //0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11,
            //30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18,
            //29, 7, 28, 12, 35, 3, 26
    };

    private static final Color COLOR_RED = new Color(255, 255, 255);
    private static final Color COLOR_BLACK = new Color(25, 25, 25);
    private static final Color COLOR_GOLD = new Color(212, 175, 55);

    private final WheelPanel wheelPanel;
    private final JButton spinButton;
    private final JLabel resultLabel;
    private final DefaultListModel<String> historyModel;

    public MawasuGame() {
        super("ルーレット");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(255, 255, 255));

        wheelPanel = new WheelPanel();
        wheelPanel.setPreferredSize(new Dimension(520, 520));
        add(wheelPanel, BorderLayout.CENTER);

        // --- 右側の操作パネル ---
        JPanel sidePanel = new JPanel();
        sidePanel.setOpaque(false);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 20));
        sidePanel.setPreferredSize(new Dimension(240, 520));

        JLabel titleLabel = new JLabel("ルーレット");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(COLOR_GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(titleLabel);
        sidePanel.add(Box.createVerticalStrut(20));

        resultLabel = new JLabel("結果: -");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(resultLabel);
        sidePanel.add(Box.createVerticalStrut(15));

        spinButton = new JButton("SPIN");
        spinButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        spinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        spinButton.setMaximumSize(new Dimension(180, 50));
        spinButton.setBackground(COLOR_GOLD);
        spinButton.setFocusPainted(false);
        spinButton.addActionListener(e -> spin());
        sidePanel.add(spinButton);
        sidePanel.add(Box.createVerticalStrut(25));


        historyModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>(historyModel);
        historyList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(scrollPane);

        add(sidePanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
    }

    /** SPIN ボタン押下時の処理。ホイールを回転させ、停止後に結果を表示する。 */
    private void spin() {
        if (wheelPanel.isSpinning()) {
            return;
        }
        spinButton.setEnabled(false);
        resultLabel.setText("回転中...");
        resultLabel.setForeground(Color.WHITE);

        wheelPanel.startSpin(winningIndex -> {
            int number = WHEEL_ORDER[winningIndex];

            resultLabel.setText("結果: " + number + " 発表");
            resultLabel.setForeground(Color.LIGHT_GRAY);
            resultLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            resultLabel.setHorizontalAlignment(JLabel.CENTER);
            
            
            historyModel.add(0, String.format("  %2d ", number));
            if (historyModel.size() > 30) {
                historyModel.remove(historyModel.size() - 1);
            }
            spinButton.setEnabled(true);
        });
    }



    /** 結果を通知するためのシンプルなコールバックインターフェース */
    interface SpinCallback {
        void onFinished(int winningIndex);
    }

    /**
     * ルーレットホイールの描画とアニメーションを担当するパネル。
     */
    private class WheelPanel extends JPanel {

        private double rotationOffsetDeg = 0.0; // Arc2D座標系での回転オフセット（度）
        private double velocity = 0.0;           // 1ティックあたりの回転量（度）
        private boolean spinning = false;
        private Timer timer;
        private SpinCallback callback;
        private final Random random = new Random();

        WheelPanel() {
            setOpaque(true);
        }

        boolean isSpinning() {
            return spinning;
        }

        void startSpin(SpinCallback callback) {
            this.callback = callback;
            this.spinning = true;
            // 初速をランダムに決定し、ある程度のバリエーションを持たせる
            this.velocity = 22.0 + random.nextDouble() * 12.0;

            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
            timer = new Timer(16, e -> tick());
            timer.start();
        }

        private void tick() {
            rotationOffsetDeg = (rotationOffsetDeg + velocity) % 360.0;
            velocity *= 0.985; // 摩擦による減速

            if (velocity < 0.12) {
                velocity = 0.0;
                spinning = false;
                timer.stop();
                int winningIndex = computeWinningIndex();
                if (callback != null) {
                    callback.onFinished(winningIndex);
                }
            }
            repaint();
        }

        /** 現在の回転角度から、ポインター（上部固定）が指しているマスのインデックスを求める */
        private int computeWinningIndex() {
            double anglePerSegment = 360.0 / WHEEL_ORDER.length;
            // ポインターは Arc2D 座標系で 90度(真上)に固定されている
            double effective = (90.0 - rotationOffsetDeg) % 360.0;
            if (effective < 0) {
                effective += 360.0;
            }
            int index = (int) Math.floor(effective / anglePerSegment);
            return Math.floorMod(index, WHEEL_ORDER.length);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int size = Math.min(w, h) - 40;
            int cx = w / 2;
            int cy = h / 2;
            int radius = size / 2;

            // 背景
            g2.setColor(new Color(25, 25, 25));
            g2.fillRect(0, 0, w, h);

            // 外枠（木製リム風）
            g2.setColor(new Color(90, 55, 30));
            g2.fillOval(cx - radius - 18, cy - radius - 18, (radius + 18) * 2, (radius + 18) * 2);

            drawWheel(g2, cx, cy, radius);
            drawPointer(g2, cx, cy, radius);
            drawHub(g2, cx, cy, radius);

            g2.dispose();
        }

        private void drawWheel(Graphics2D g2, int cx, int cy, int radius) {
            int n = WHEEL_ORDER.length;
            double anglePerSegment = 360.0 / n;

            for (int i = 0; i < n; i++) {
                double startAngle = i * anglePerSegment + rotationOffsetDeg;
                int number = WHEEL_ORDER[i];

                Arc2D.Double arc = new Arc2D.Double(
                        cx - radius, cy - radius, radius * 2.0, radius * 2.0,
                        startAngle, anglePerSegment, Arc2D.PIE);

                g2.setColor(COLOR_BLACK);
                g2.fill(arc);
                g2.setColor(new Color(230, 230, 230));
                g2.setStroke(new BasicStroke(1.2f));
                g2.draw(arc);

                // 数字ラベルを描画
                double midAngleDeg = startAngle + anglePerSegment / 2.0;
                double midAngleRad = Math.toRadians(midAngleDeg);
                double textRadius = radius * 0.80;
                int tx = (int) (cx + textRadius * Math.cos(midAngleRad));
                int ty = (int) (cy - textRadius * Math.sin(midAngleRad));

                g2.setColor(COLOR_RED);
                g2.setFont(new Font("SansSerif", Font.BOLD, 13));
                String label = String.valueOf(number);
                FontMetrics fm = g2.getFontMetrics();
                int labelW = fm.stringWidth(label);
                g2.drawString(label, tx - labelW / 2, ty + fm.getAscent() / 2 - 2);
            }

            // 外周の縁取り
            g2.setColor(new Color(210, 210, 210));
            g2.setStroke(new BasicStroke(3f));
            g2.draw(new Ellipse2D.Double(cx - radius, cy - radius, radius * 2.0, radius * 2.0));
        }

        private void drawPointer(Graphics2D g2, int cx, int cy, int radius) {
            // ホイール上部に固定されたポインター（三角形）
            int pointerSize = 22;
            int topY = cy - radius - 6;
            Polygon triangle = new Polygon();
            triangle.addPoint(cx - pointerSize / 2, topY - pointerSize);
            triangle.addPoint(cx + pointerSize / 2, topY - pointerSize);
            triangle.addPoint(cx, topY + 4);

            g2.setColor(COLOR_GOLD);
            g2.fillPolygon(triangle);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawPolygon(triangle);
        }

        private void drawHub(Graphics2D g2, int cx, int cy, int radius) {
            int hubRadius = (int) (radius * 0.14);
            g2.setColor(new Color(60, 60, 60));
            g2.fillOval(cx - hubRadius, cy - hubRadius, hubRadius * 2, hubRadius * 2);
            g2.setColor(COLOR_GOLD);
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(cx - hubRadius, cy - hubRadius, hubRadius * 2, hubRadius * 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	MawasuGame frame = new MawasuGame();
            frame.setVisible(true);
        });
    }
}