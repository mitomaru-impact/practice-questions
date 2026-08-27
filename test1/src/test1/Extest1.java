package test1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Extest1 extends JFrame {

    private JButton button;
    private int popupNumber = 1;

    public static void main(String[] args) {

        Extest1 frame = new Extest1("Popup Menu");

        frame.setVisible(true);
    }

    Extest1(String title) {

        setTitle(title);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("スタート");

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                showPopup();

            }
        });

        add(button);
    }

    private void showPopup() {

        JDialog popup = new JDialog(this, false);

        popup.setTitle("Popup " + popupNumber);
        popup.setSize(200, 100);
        popup.setLayout(null);

        JLabel label = new JLabel("ポップアップ " + popupNumber);

        label.setBounds(20, 10, 150, 25);

        JButton nextButton = new JButton("次へ");

        nextButton.setBounds(20, 40, 150, 30);

        popup.add(label);
        popup.add(nextButton);

        // ランダムな位置を決める
        Random random = new Random();

        int screenWidth =
                Toolkit.getDefaultToolkit().getScreenSize().width;

        int screenHeight =
                Toolkit.getDefaultToolkit().getScreenSize().height;

        int x = random.nextInt(screenWidth - 200);

        int y = random.nextInt(screenHeight - 100);

        popup.setLocation(x, y);

        // 「次へ」をクリック
        nextButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                popupNumber++;

                showPopup();

            }
        });

        popup.setVisible(true);
    }
}