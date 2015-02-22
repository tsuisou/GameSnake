import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

class View extends JPanel implements ActionListener {

	Image head;
	Image dot;
	Image apple;
	Image wall;
	int x;
	int y;
	private final int hight = 350;
	private final int width = 380;
	private final int DELAY = 150;
	private ArrayList<Integer> listOne, listTwo;
	static int number;
	static Timer timer;
	boolean turnLeft = true;
	boolean turnRight = false;
	boolean turnUp = false;
	boolean turnDown = false;
	int temporary = 0;
	static boolean status = true;
	static int result = 0;

	public View() {

		addKeyListener(new KAdapter());

		setBackground(Color.black);

		ImageIcon imageOne = new ImageIcon(this.getClass().getResource(
				"snake.jpg"));
		head = imageOne.getImage();

		ImageIcon imageTwo = new ImageIcon(this.getClass().getResource(
				"dot.jpg"));
		dot = imageTwo.getImage();

		ImageIcon imageThree = new ImageIcon(this.getClass().getResource(
				"apple.jpg"));
		apple = imageThree.getImage();

		ImageIcon imageFour = new ImageIcon(this.getClass().getResource(
				"wall.jpg"));
		wall = imageFour.getImage();

		setFocusable(true);
		startGame();
	}

	public void startGame() {
		listOne = new ArrayList<Integer>();
		listTwo = new ArrayList<Integer>();

		number = 3;

		for (int i = 0; i < number; i++) {
			listOne.add(180 + i * 10);
			listTwo.add(180);
		}

		apple();

		timer = new Timer(DELAY, this);
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (status) {
			g.drawImage(apple, x, y, this);

			for (int i = 0; i < number; i++) {
				if (i == 0)
					g.drawImage(head, listOne.get(i), listTwo.get(i), this);
				else
					g.drawImage(dot, listOne.get(i), listTwo.get(i), this);
			}
		}

		for (int i = 0; i <= 390; i += 10) {
			g.drawImage(wall, i, 0, this);
			g.drawImage(wall, i, 360, this);
		}

		for (int i = 10; i < 360; i += 10) {
			g.drawImage(wall, 0, i, this);
			g.drawImage(wall, 390, i, this);
		}
	}

	public void crash() {

		for (int i = 1; i < number; i++) {
			if ((listOne.get(i).equals(listOne.get(0)))
					&& (listTwo.get(i).equals(listTwo.get(0)))) {
				status = false;
				break;

			}
		}

		if (listOne.get(0) > width)
			status = false;

		if (listOne.get(0) < 10)
			status = false;

		if (listTwo.get(0) > hight)
			status = false;

		if (listTwo.get(0) < 10)
			status = false;
	}

	public void movement() {

		for (int i = number - 1; i > 0; i--) {
			listOne.set(i, listOne.get(i - 1));
			listTwo.set(i, listTwo.get(i - 1));
		}

		if (turnLeft) {
			temporary = listOne.get(0);
			listOne.set(0, temporary - 10);
		}
		if (turnRight) {
			temporary = listOne.get(0);
			listOne.set(0, temporary + 10);
		}
		if (turnUp) {
			temporary = listTwo.get(0);
			listTwo.set(0, temporary - 10);
		}
		if (turnDown) {
			temporary = listTwo.get(0);
			listTwo.set(0, temporary + 10);
		}

	}

	public void apple() {
		boolean bl = true;
		while (bl) {
			int r = (int) (Math.random() * 38 + 1);
			x = r * 10;
			r = (int) (Math.random() * 35 + 1);
			y = r * 10;

			if ((listOne.contains(x)) && (listTwo.contains(y)))
				continue;
			else
				bl = false;
		}
	}

	public void checkApple() {
		if ((listOne.get(0) == x) && (listTwo.get(0) == y)) {
			++number;
			result += 10;
			Snake.result.setText(String.valueOf(result));

			listOne.add(listOne.get(1));
			listTwo.add(listTwo.get(1));

			apple();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (status) {
			checkApple();
			crash();
			movement();
			repaint();
		} else {
			timer.stop();
			listOne.clear();
			listTwo.clear();
			number = 3;

			for (int i = 0; i < number; i++) {
				listOne.add(180 + i * 10);
				listTwo.add(180);
			}

			status = true;
			turnLeft = true;
			turnRight = false;
			turnUp = false;
			turnDown = false;

			Snake.start.setEnabled(true);
			Snake.result.setText("0");
			result = 0;
			repaint();

		}
	}

	private class KAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!turnRight)) {
				turnLeft = true;
				turnUp = false;
				turnDown = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!turnLeft)) {
				turnRight = true;
				turnUp = false;
				turnDown = false;
			}

			if ((key == KeyEvent.VK_UP) && (!turnDown)) {
				turnUp = true;
				turnRight = false;
				turnLeft = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!turnUp)) {
				turnDown = true;
				turnRight = false;
				turnLeft = false;
			}
		}
	}
}
