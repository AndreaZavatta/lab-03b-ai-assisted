package it.unibo;

import java.util.Scanner;

public class GameMain_WithoutGuidance {
	public static void main(String[] args) {
		ConnectFourImpl_WithoutGuidance game = new ConnectFourImpl_WithoutGuidance();
		Scanner sc = new Scanner(System.in);
		ConnectFour.Player current = ConnectFour.Player.RED;

		while (true) {
			System.out.println(game);
			System.out.println((current == ConnectFour.Player.RED ? "Red (R)" : "Yellow (Y)") + "'s turn.");
			System.out.print("Choose column (1-7) or 'q' to quit: ");
			String line = sc.nextLine();
			if (line == null)
				break;
			line = line.trim();
			if (line.equalsIgnoreCase("q")) {
				System.out.println("Game aborted.");
				break;
			}
			int col;
			try {
				col = Integer.parseInt(line) - 1;
				char disc = current == ConnectFour.Player.RED ? 'R' : 'Y';
				game.dropDisc(col, disc);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number between 1 and 7.");
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid move: " + e.getMessage());
				continue;
			}

			if (game.checkWin(current)) {
				System.out.println(game);
				System.out.println((current == ConnectFour.Player.RED ? "Red (R)" : "Yellow (Y)") + " wins!");
				break;
			}
			if (game.isBoardFull()) {
				System.out.println(game);
				System.out.println("Draw: board full.");
				break;
			}

			current = (current == ConnectFour.Player.RED) ? ConnectFour.Player.YELLOW : ConnectFour.Player.RED;
		}
		sc.close();
	}
}
