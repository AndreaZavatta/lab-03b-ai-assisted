package it.unibo;

import java.util.Scanner;

public final class Main {
	private Main() {
	}

	public static void main(final String[] args) {
		final Scanner input = new Scanner(System.in);
		final ConnectFour game = new ConnectFourGame();
		final ConnectFourView view = new ConsoleConnectFourView(input, System.out);
		final ConnectFourController controller = new ConnectFourController(game, view);
		controller.play();
	}
}
