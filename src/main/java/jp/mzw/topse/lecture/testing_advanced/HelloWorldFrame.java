package jp.mzw.topse.lecture.testing_advanced;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class HelloWorldFrame extends JFrame {

	private JTextField textField;
	private JButton button;

	public HelloWorldFrame() {
		textField = new JTextField();
		textField.setName("textField");
		
		button = new JButton("Click");
		button.setName("button");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				textField.setText(textField.getText() + "Hello World!");
			}
		});
		
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().add(textField);
		this.getContentPane().add(button);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new HelloWorldFrame();
	}

}
