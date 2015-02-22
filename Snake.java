import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Snake extends JFrame {

	static JButton start;
	private JButton stop;
	private JLabel resultText;
	static JLabel result;

	public Snake() {

		super("Snake");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocation(new Point(240, 120));

		Insets insets = new Insets(1, 1, 1, 1);

		start = new JButton("Start");
		start.setMargin(insets);
		start.setFocusable(false);
		start.setBounds(407, 90, 80, 30);
		start.addActionListener(new Start());

		stop = new JButton("End");
		stop.setMargin(insets);
		stop.setFocusable(false);
		stop.setBounds(407, 130, 80, 30);
		stop.addActionListener(new Cancel());

		resultText = new JLabel("Result:");
		resultText.setBounds(406, 30, 40, 30);

		result = new JLabel("0");
		result.setBounds(450, 30, 42, 30);

		View main = new View();
		main.setBounds(0, 0, 400, 372);

		add(main);
		add(start);
		add(resultText);
		add(stop);
		add(result);

		setVisible(true);
	}

	private class Cancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			View.timer.stop();
			dispose();
		}
	}

	private class Start implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
//			result = new JLabel("0");
			View.timer.start();
		}
	}

	public static void main(String[] args) {
		new Snake();
	}
}
