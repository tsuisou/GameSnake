import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {

	static JButton start;
	private JButton stop;
	private JLabel resultText;
	static JLabel result;

	public Game() {

		setTitle("enjoy");
		setSize(450, 360);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocation(new Point(240, 120));
		setAlwaysOnTop(true);

		start = new JButton("Start");
		start.setMargin(new Insets(1, 1, 1, 1));
		start.setBounds(357, 90, 80, 30);
		start.setFocusable(false);
		start.addActionListener(new Start());

		stop = new JButton("End");
		stop.setBounds(357, 130, 80, 30);
		stop.addActionListener(new Cancel());

		resultText = new JLabel("Result:");
		resultText.setBounds(356, 30, 40, 30);

		result = new JLabel("0");
		result.setBounds(400, 30, 42, 30);

		Engine main = new Engine();
		main.setBounds(0, 0, 350, 332);

		add(main);
		add(start);
		add(resultText);
		add(stop);
		add(result);

		setVisible(true);
	}

	private class Cancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Engine.timer.stop();
			dispose();
		}
	}

	private class Start implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			Engine.timer.start();
		}
	}

	public static void main(String[] args) {
		new Game();
	}
}
