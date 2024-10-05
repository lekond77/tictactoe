package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class GameFrame extends JFrame {

	private static JPanel contentPane = new JPanel();
	public static JButton[][] buttons = new JButton[3][3];
	private static String person = "X";
	private boolean gameEnded = false;
	private int counter = 0;

	/**
	 * Create the frame.
	 */
	public GameFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(500, 200, 450, 466);
		contentPane.setLayout(new GridLayout(3, 3));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(192, 192, 192));
		

		setContentPane(contentPane);

		GameFrame.initializeBoad(new ButtonClickListener());

	}

	private static void initializeBoad(ButtonClickListener listener) {

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				JButton button = new JButton();
				buttons[i][j] = button;
				button.addActionListener(listener);
				button.setFont(new Font("Tahoma", Font.PLAIN, 80));
				button.setBackground(new Color(119, 228, 213));
				contentPane.add(button);

			}
		}
		// computerMove();
	}

	private boolean checkWinner() {

		for (int i = 0; i < buttons.length; i++) {

			// check row
			if (buttons[i][0].getText() == person && buttons[i][1].getText() == person
					&& buttons[i][2].getText() == person) {
				return true;
			}

			// check column
			if (buttons[0][i].getText() == person && buttons[1][i].getText() == person
					&& buttons[2][i].getText() == person) {
				return true;
			}

			// check diagonal

			if (buttons[0][0].getText() == person && buttons[1][1].getText() == person
					&& buttons[2][2].getText() == person) {
				return true;
			}

			if (buttons[0][2].getText() == person && buttons[1][1].getText() == person
					&& buttons[2][0].getText() == person) {
				return true;
			}

		}

		return false;
	}

	/*
	 * private static void computerMove() {
	 * 
	 * Random random = new Random(); int row, col;
	 * 
	 * do { row = random.nextInt(buttons.length); col =
	 * random.nextInt(buttons[0].length);
	 * }while(!buttons[row][col].getText().isEmpty());
	 * 
	 * buttons[row][col].setText(person);
	 * 
	 * }
	 */

	private class ButtonClickListener implements ActionListener {

		public ButtonClickListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (gameEnded) {
				System.out.println("Game end");
				return;
			}

			JButton button = (JButton) e.getSource();
			if (!button.getText().equals("")) {
				return;
			}

			button.setText(person);
			counter++;

			boolean isWinner = checkWinner();
			if (isWinner) {
				JOptionPane.showMessageDialog(null, person + " win");
				gameEnded = true;
			} else {
				person = (person.equals("X")) ? "O" : "X";
			}

			if (!gameEnded && counter == buttons[0].length * buttons.length - 1) {
				JOptionPane.showMessageDialog(null, person + "Game end, no winner !");
				return;
			}

		}
	}

}
