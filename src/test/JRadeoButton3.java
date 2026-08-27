package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JRadeoButton3 extends JFrame {

    public JRadeoButton3() {

        setTitle("蛇ゲーム");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JRadeoButton3();
    }
}


class GamePanel extends JPanel implements ActionListener, KeyListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int SIZE = 20;

    // リンゴの数
    private static final int APPLE_COUNT = 200;

    // 蛇
    private ArrayList<Point> snake;

    // リンゴ
    private ArrayList<Point> apples;

    // 乱数
    private Random random;

    // タイマー
    private Timer timer;

    // 蛇の進む方向
    private int dx;
    private int dy;

    // スコア
    private int score;

    // ゲームオーバー
    private boolean gameOver;


    public GamePanel() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(Color.BLACK);

        setFocusable(true);

        addKeyListener(this);

        random = new Random();

        startGame();
    }


    // ゲーム開始
    private void startGame() {

        snake = new ArrayList<Point>();

        // 蛇の初期位置
        snake.add(new Point(300, 300));
        snake.add(new Point(280, 300));
        snake.add(new Point(260, 300));

        // 右方向へ進む
        dx = SIZE;
        dy = 0;

        // スコア
        score = 0;

        // ゲームオーバー
        gameOver = false;

        // リンゴを5個作る
        apples = new ArrayList<Point>();

        for (int i = 0; i < APPLE_COUNT; i++) {
            createApple();
        }

        // タイマー
        timer = new Timer(100, this);
        timer.start();

        requestFocusInWindow();
    }


    // リンゴを作る
    private void createApple() {

        int x;
        int y;

        Point apple;

        do {

            x = random.nextInt(WIDTH / SIZE) * SIZE;
            y = random.nextInt(HEIGHT / SIZE) * SIZE;

            apple = new Point(x, y);

        } while (snake.contains(apple) || apples.contains(apple));

        apples.add(apple);
    }


    // タイマー
    @Override
    public void actionPerformed(ActionEvent e) {

        if (!gameOver) {

            moveSnake();

            checkApple();

            checkCollision();

            repaint();
        }
    }


    // 蛇を動かす
    private void moveSnake() {

        Point head = snake.get(0);

        Point newHead = new Point(
                head.x + dx,
                head.y + dy
        );

        snake.add(0, newHead);

        // リンゴを食べていなければ尻尾を削除
        boolean ateApple = false;

        for (Point apple : apples) {

            if (newHead.equals(apple)) {

                ateApple = true;
                break;
            }
        }

        if (!ateApple) {

            snake.remove(snake.size() - 1);
        }
    }


    // リンゴを食べたか確認
    private void checkApple() {

        Point head = snake.get(0);

        for (int i = 0; i < apples.size(); i++) {

            if (head.equals(apples.get(i))) {

                // スコアを1増やす
                score++;

                // 食べたリンゴを削除
                apples.remove(i);

                // 新しいリンゴを1個追加
                createApple();

                break;
            }
        }
    }


    // 衝突判定
    private void checkCollision() {

        Point head = snake.get(0);

        // 壁にぶつかった
        if (head.x < 0 ||
            head.x >= WIDTH ||
            head.y < 0 ||
            head.y >= HEIGHT) {

            gameOver = true;

            timer.stop();
        }

        // 自分自身にぶつかった
        for (int i = 1; i < snake.size(); i++) {

            if (head.equals(snake.get(i))) {

                gameOver = true;

                timer.stop();

                break;
            }
        }
    }


    // 描画
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // 蛇を描く
        for (int i = 0; i < snake.size(); i++) {

            Point part = snake.get(i);

            if (i == 0) {

                // 蛇の頭
                g.setColor(Color.GREEN);

            } else {

                // 蛇の体
                g.setColor(new Color(0, 180, 0));
            }

            g.fillRect(
                    part.x,
                    part.y,
                    SIZE,
                    SIZE
            );
        }


        // リンゴを5個描く
        g.setColor(Color.RED);

        for (Point apple : apples) {

            g.fillOval(
                    apple.x,
                    apple.y,
                    SIZE,
                    SIZE
            );
        }


        // スコア
        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        g.drawString(
                "SCORE : " + score,
                10,
                25
        );


        // ゲームオーバー
        if (gameOver) {

            g.setColor(Color.RED);

            g.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            40
                    )
            );

            String message = "GAME OVER";

            int messageWidth =
                    g.getFontMetrics().stringWidth(message);

            g.drawString(
                    message,
                    (WIDTH - messageWidth) / 2,
                    HEIGHT / 2
            );


            g.setColor(Color.WHITE);

            g.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            20
                    )
            );

            String restart = "ENTERキーで再スタート";

            int restartWidth =
                    g.getFontMetrics().stringWidth(restart);

            g.drawString(
                    restart,
                    (WIDTH - restartWidth) / 2,
                    HEIGHT / 2 + 50
            );
        }
    }


    // キーボード操作
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();


        // 上
        if (key == KeyEvent.VK_UP) {

            if (dy == 0) {

                dx = 0;
                dy = -SIZE;
            }
        }


        // 下
        else if (key == KeyEvent.VK_DOWN) {

            if (dy == 0) {

                dx = 0;
                dy = SIZE;
            }
        }


        // 左
        else if (key == KeyEvent.VK_LEFT) {

            if (dx == 0) {

                dx = -SIZE;
                dy = 0;
            }
        }


        // 右
        else if (key == KeyEvent.VK_RIGHT) {

            if (dx == 0) {

                dx = SIZE;
                dy = 0;
            }
        }


        // Enterで再スタート
        else if (key == KeyEvent.VK_ENTER) {

            if (gameOver) {

                startGame();
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }
}