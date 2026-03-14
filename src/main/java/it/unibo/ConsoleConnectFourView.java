package it.unibo;

import java.io.PrintStream;
import java.util.Scanner;

public final class ConsoleConnectFourView implements ConnectFourView {
	private static final int BOARD_WIDTH = 7;
	private static final int BOARD_HEIGHT = 6;
	private final Scanner input;
	private final PrintStream output;
	private final char[][] board;
	private final int[] discsInColumn;

	public ConsoleConnectFourView(final Scanner input, final PrintStream output) {
		this.input = input;
		this.output = output;
		this.board = new char[BOARD_HEIGHT][BOARD_WIDTH];
		this.discsInColumn = new int[BOARD_WIDTH];
	}

	@Override
	public void printBoard() {
		this.output.println();
		for (int row = 0; row < BOARD_HEIGHT; row++) {
			this.output.print("|");
			for (int column = 0; column < BOARD_WIDTH; column++) {
				final char cell = this.board[row][column];
				this.output.print(cell == '\0' ? '.' : cell);
				this.output.print('|');
			}
			this.output.println();
		}
		this.output.println(" 0 1 2 3 4 5 6 ");
		this.output.println();
	}

	@Override
	public void placeDisc(final int column, final char disc) {
		final int row = BOARD_HEIGHT - 1 - this.discsInColumn[column];
		this.board[row][column] = disc;
		this.discsInColumn[column]++;
	}

	@Override
	public int readColumn(final ConnectFour.Player player) {
		this.output.print("Player " + player + ", choose a column (0-6): ");
		this.output.flush();
		final String line = this.input.nextLine();
		try {
			return Integer.parseInt(line.trim());
		} catch (final NumberFormatException ex) {
			throw new IllegalArgumentException("Column must be an integer", ex);
		}
	}

	@Override
	public void showInvalidMove(final String message) {
		this.output.println("Invalid move: " + message);
	}

	@Override
	public void showWinner(final ConnectFour.Player player) {
		this.output.println("Player " + player + " wins!");
	}

	@Override
	public void showDraw() {
		this.output.println("Draw: the board is full.");
	}
}
