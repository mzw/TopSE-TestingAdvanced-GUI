package jp.mzw.topse.lecture.testing_advanced;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jp.hishidama.swing.layout.GroupLayoutUtil;

public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField display;

	private List<JButton> numbers;

	private JButton add;
	private JButton sub;
	private JButton mul;
	private JButton div;

	private JButton eq;
	private JButton ac;

	public enum Operator {
		Add, Sub, Mul, Div
	}

	private int value;
	private Operator op;

	private boolean refresh;

	public Calculator() {
		value = 0;
		op = null;
		refresh = false;

		display = new JTextField();
		display.setName("display");
		this.getContentPane().add(display);

		createNumberInputs();
		createOperatorButtons();
		createSpecialOperatorButtons();
		setGrounpLayoutTo(this.getContentPane());

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 175);
		this.setVisible(true);
	}

	private void createNumberInputs() {
		numbers = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			final String num = Integer.toString(i);
			JButton button = new JButton(num);
			button.setName("number" + num);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (refresh) {
						value = Integer.parseInt(display.getText());
						display.setText(num);
						refresh = false;
					} else {
						if ("0".equals(num) && "".equals(display.getText())) {
							// NOP
						} else {
							display.setText(display.getText() + num);
						}
					}
				}
			});
			numbers.add(button);
		}
	}

	private void createOperatorButtons() {
		add = new JButton("+");
		add.setName("add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				op = Operator.Add;
				refresh = true;
			}
		});

		sub = new JButton("-");
		sub.setName("sub");
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				op = Operator.Sub;
				refresh = true;
			}
		});

		mul = new JButton("*");
		mul.setName("mul");
		mul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				op = Operator.Mul;
				refresh = true;
			}
		});

		div = new JButton("/");
		div.setName("div");
		div.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				op = Operator.Div;
				refresh = true;
			}
		});
	}

	private void createSpecialOperatorButtons() {
		eq = new JButton("=");
		eq.setName("eq");
		eq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (op == null) {
					return;
				}
				int num = Integer.parseInt(display.getText());
				int result = calculate(value, num, op);
				display.setText(Integer.toString(result));
				op = null;
				refresh = true;
			}
		});

		ac = new JButton("AC");
		ac.setName("ac");
		ac.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				value = 0;
				op = null;
				refresh = false;
				display.setText("");
			}
		});
	}

	public int calculate(final int a, final int b, final Operator op) {
		switch (op) {
		case Add:
			return a + b;
		case Sub:
			return a - b;
		case Mul:
			return a * b;
		case Div:
			return a / b;
		default:
			throw new IllegalStateException("invalid operands or operator");
		}
	}

	private void setGrounpLayoutTo(final Container container) {
		Component[][] components = { { display, GroupLayoutUtil.SAME_L, GroupLayoutUtil.SAME_L, GroupLayoutUtil.SAME_L },
				{ numbers.get(7), numbers.get(8), numbers.get(9), div }, { numbers.get(4), numbers.get(5), numbers.get(6), mul },
				{ numbers.get(1), numbers.get(2), numbers.get(3), sub }, { numbers.get(0), ac, eq, add }, };
		GroupLayoutUtil util = new GroupLayoutUtil();
		util.setComponents(components);
		util.setGroupLayoutTo(container);
	}

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				ShowDialog.showErrorDialog(e);
			}
		});
		new Calculator();
	}

	private static class ShowDialog {
		static void showErrorDialog(final Throwable t) {
			try {
				t.printStackTrace();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						JOptionPane.showMessageDialog(null, t.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				});
			} catch (Throwable e) {
				// NOP
			}
		}
	}
}
