
package test;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class sample {
	public static void main(String[] args) {
		JFrame frame = new JFrame("MyTitle");
		frame.setBounds(100, 100, 728, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn1 = new JButton("North");
		JButton btn2 = new JButton("South");
		JButton btn3 = new JButton("West");
		JButton btn4 = new JButton("East");
		JTextField nameField = new JTextField(10);
		
		btn4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				JOptionPane.showMessageDialog(null,
						name + " さん、こんにちは！");

			}

			});


		frame.getContentPane().add(btn1, BorderLayout.NORTH);
		frame.getContentPane().add(btn2, BorderLayout.SOUTH);
		frame.getContentPane().add(btn3, BorderLayout.WEST);
		frame.getContentPane().add(btn4, BorderLayout.EAST);
		frame.getContentPane().add(nameField, BorderLayout.CENTER);

		frame.setVisible(true);
	}
}
