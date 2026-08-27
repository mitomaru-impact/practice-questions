package test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // 追加：警告ダイアログ用
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class test1 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("利用規約");
        frame.setBounds(100, 100, 900, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // 利用規約タイトル
        JLabel label = new JLabel("利用規約");
        label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        label.setBounds(20, 20, 200, 30);
        
        // 利用規約サブタイトル
        JLabel label2 = new JLabel("利用を続行するには、利用規約に同意する必要があります。");
        label2.setBounds(20, 45, 800, 30);
        
        JTextArea textArea = new JTextArea("""
                〇〇 サービス利用規約

本利用規約（以下「本規約」といいます。）は、[運営会社/個人名]（以下「当社」といいます。）が提供するサービス「〇〇」（以下「本サービス」といいます。）の利用条件を定めるものです。ユーザーの皆様には、本規約に同意のうえ、本サービスをご利用いただきます。

第1条（適用）
1. 本規約は、ユーザーと当社との間の本サービスの利用に関わる一切の関係に適用されるものとします。
2. 当社が本サービス上で掲載するルール、注意事項等（以下「個別規定」といいます。）は、本規約の一部を構成するものとします。本規約と個別規定の内容が異なる場合は、個別規定が優先して適用されます。

第2条（利用登録）
1. 本サービスの利用を希望する者（以下「登録希望者」といいます。）は、本規約に同意の上、当社の定める方法によって利用登録を申請し、当社がこれを承認することによって、利用登録が完了するものとします。
2. 当社は、登録希望者に以下の事由があると判断した場合、利用登録の申請を承認しないことがあり、その理由については一切の開示義務を負わないものとします。
(1) 虚偽の事項を申請した場合
(2) 本規約に違反したことがある者からの申請である場合
(3) その他、当社が利用登録を相当でないと判断した場合

第3条（アカウント管理）
1. ユーザーは、自己の責任において、本サービスのアカウントおよびパスワードを適切に管理するものとします。
2. ユーザーは、いかなる場合も、アカウントおよびパスワードを第三者に譲渡または貸与し、もしくは第三者と共用することはできません。

第4条（禁止事項）
ユーザーは、本サービスの利用にあたり、以下の行為をしてはならないものとします。
1. 法令または公序良俗に違反する行為
2. 犯罪行為に関連する行為
3. 当社、他のユーザー、または第三者の知的財産権、肖像権、プライバシー、名誉その他の権利を侵害する行為
4. 本サービスのサーバーまたはネットワークの機能を破壊したり、妨害したりする行為
5. 本サービスの運営を妨害するおそれのある行為
6. 他のユーザーになりすます行為
7. その他、当社が不適切と判断する行為

第5条（サービスの提供の停止等）
当社は、以下のいずれかの事由があると判断した場合、ユーザーに事前に通知することなく本サービスの全部または一部の提供を停止または中断することができるものとします。
1. 本サービスにかかるコンピュータシステムの保守点検または更新を行う場合
2. 地震、落雷、火災、停電または天災などの不可抗力により、本サービスの提供が困難となった場合
3. その他、当社が本サービスの提供が困難と判断した場合

第6条（著作権・権利の帰属）
1. 本サービスに関するすべてのコンテンツ（テキスト、画像、動画、プログラム等）の著作権その他の知的財産権は、当社または正当な権利を有する第三者に帰属します。
2. ユーザーが本サービス上に投稿したデータ等の著作権はユーザーに保留されますが、当社は本サービスの円滑な運営および改善のために、これを無償で利用できるものとします。

第7条（免責事項）
1. 当社は、本サービスに事実上または法律上の欠陥（安全性、信頼性、正確性、完全性、有効性、特定の目的への適合性、セキュリティ等に関する欠陥、エラーやバグ、権利侵害等を含みます。）がないことを明示的にも黙示的にも保証しておりません。
2. 当社は、本サービスに起因してユーザーに生じたあらゆる損害について、当社の故意または重過失による場合を除き、一切の責任を負いません。

第8条（利用規約の変更）
当社は、必要と判断した場合には、ユーザーに通知することなくいつでも本規約を変更することができるものとします。変更後の利用規約は、本サービス上に掲載した時点から効力を生じるものとします。

第9条（準拠法・裁判管轄）
1. 本規約の解釈にあたっては、日本法を準拠法とします。
2. 本サービスに関して紛争が生じた場合、当社の本社所在地を管轄する裁判所を専属的合意管轄とします。

第10条（無条件の同意）
この規約に同意すると、サービスの利用に同意したことになり、5000兆円請求されることに同意したことになります。

制定日：202X年X月X日
最終改定日：202X年X月X日
                """);
        
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 90, 840, 700); 
        
        // 利用規約同意box
        JCheckBox checkBox = new JCheckBox("利用規約に同意する");
        checkBox.setBounds(20, 800, 200, 30);
        
        // 同意ボタン
        JButton button = new JButton("同意する");
        button.setBounds(740, 800, 120, 35);
        
        // ボタンのクリックイベント
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // チェックボックスにチェックが入っているか？
                if (checkBox.isSelected()) {
                    // 【チェックあり】 -> 架空請求画面を開く
                    showFakeBillWindow(frame);
                } else {
                    // 【チェックなし】 -> 警告ポップアップを出す
                    JOptionPane.showMessageDialog(frame, 
                            "利用規約に同意してください。（チェックボックスにチェックが必要です）", 
                            "確認", 
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        frame.getContentPane().add(label);
        frame.getContentPane().add(label2);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(checkBox);
        frame.getContentPane().add(button);
        
        frame.setVisible(true);
    }

    // 架空請求するメソッド
    private static void showFakeBillWindow(JFrame parentFrame) {

        JFrame fakeFrame = new JFrame("【緊急】ご請求案内");
        fakeFrame.setBounds(200, 200, 600, 400);
        fakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fakeFrame.getContentPane().setLayout(null);

        JLabel billLabel = new JLabel("<html><center><font size='6' color='red'><b>登録完了・ご請求</b></font><br><br>"
                + "規約第10条に基づき、以下の金額が請求されました。<br><br>"
                + "<font size='7' color='black'><b>請求金額：5,000,000,000,000,000 円</b></font><br><br>"
                + "※24時間以内にお支払いがない場合、法的措置に移行します。</center></html>");
        billLabel.setBounds(20, 20, 540, 200);

        JButton payButton = new JButton("今すぐ支払う（銀行振込）");
        payButton.setBounds(180, 250, 220, 50);
        
        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(fakeFrame, "支払いは不可能です（そんなお金はありません）。", "エラー", JOptionPane.ERROR_MESSAGE);
        });

        fakeFrame.getContentPane().add(billLabel);
        fakeFrame.getContentPane().add(payButton);
        
        fakeFrame.setLocationRelativeTo(parentFrame);
        fakeFrame.setVisible(true);
    }
}