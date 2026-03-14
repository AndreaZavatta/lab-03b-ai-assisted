package it.unibo;

public final class ConnectFourGame implements ConnectFour {
	private static final int BOARD_WIDTH = 7;
	private static final int BOARD_HEIGHT = 6;
	private final int[] discsInColumn;
	private final char[][] board;

	public ConnectFourGame() {
		this.discsInColumn = new int[BOARD_WIDTH];
		this.board = new char[BOARD_HEIGHT][BOARD_WIDTH];
	}

	@Override
	public void dropDisc(final int column, final char disc) {
		if (column < 0 || column >= BOARD_WIDTH) {
			throw new IllegalArgumentException("Column index out of range");
		}
		if (this.discsInColumn[column] >= BOARD_HEIGHT) {
			throw new IllegalArgumentException("Column is full");
		}
		final int row = BOARD_HEIGHT - 1 - this.discsInColumn[column];
		this.board[row][column] = disc;
		this.discsInColumn[column]++;
	}

	@Override
	public boolean checkWin(final Player player) {
		final char playerDisc = this.toDisc(player);
		if (this.hasHorizontalWin(playerDisc)) {
			return true;
		}
		if (this.hasVerticalWin(playerDisc)) {
			return true;
		}
		return this.hasDiagonalWin(playerDisc);
	}

	private boolean hasHorizontalWin(final char playerDisc) {
		for (int row = 0; row < BOARD_HEIGHT; row++) {
			for (int column = 0; column <= BOARD_WIDTH - 4; column++) {
				if (this.board[row][column] == playerDisc
						&& this.board[row][column + 1] == playerDisc
						&& this.board[row][column + 2] == playerDisc
						&& this.board[row][column + 3] == playerDisc) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasVerticalWin(final char playerDisc) {
		for (int row = 0; row <= BOARD_HEIGHT - 4; row++) {
			for (int column = 0; column < BOARD_WIDTH; column++) {
				if (this.board[row][column] == playerDisc
						&& this.board[row + 1][column] == playerDisc
						&& this.board[row + 2][column] == playerDisc
						&& this.board[row + 3][column] == playerDisc) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasDiagonalWin(final char playerDisc) {
		for (int row = 0; row <= BOARD_HEIGHT - 4; row++) {
			for (int column = 0; column <= BOARD_WIDTH - 4; column++) {
				if (this.board[row][column] == playerDisc
						&& this.board[row + 1][column + 1] == playerDisc
						&& this.board[row + 2][column + 2] == playerDisc
						&& this.board[row + 3][column + 3] == playerDisc) {
					return true;
				}
			}
		}
		for (int row = 3; row < BOARD_HEIGHT; row++) {
			for (int column = 0; column <= BOARD_WIDTH - 4; column++) {
				if (this.board[row][column] == playerDisc
						&& this.board[row - 1][column + 1] == playerDisc
						&& this.board[row - 2][column + 2] == playerDisc
						&& this.board[row - 3][column + 3] == playerDisc) {
					return true;
				}
			}
		}
		return false;
	}

	private char toDisc(final Player player) {
		if (player == Player.RED) {
			return 'R';
		}
		return 'Y';
	}

	@Override
	public boolean isBoardFull() {
		for (final int discs : this.discsInColumn) {
			if (discs < BOARD_HEIGHT) {
				return false;
			}
		}
		return true;
	}
}
