package it.unibo;

public class ConnectFourImpl_WithoutGuidance implements ConnectFour {
	private final int ROWS = 6;
	private final int COLS = 7;
	private final char[][] board;

	public ConnectFourImpl_WithoutGuidance() {
		board = new char[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				board[r][c] = ' ';
			}
		}
	}

	@Override
	public void dropDisc(int column, char disc) {
		if (column < 0 || column >= COLS) {
			throw new IllegalArgumentException("Column out of range");
		}
		for (int r = ROWS - 1; r >= 0; r--) {
			if (board[r][column] == ' ') {
				board[r][column] = disc;
				return;
			}
		}
		throw new IllegalArgumentException("Column is full");
	}

	@Override
	public boolean checkWin(Player player) {
		char disc = player == Player.RED ? 'R' : 'Y';
		// horizontal, vertical, diag down-right, diag down-left
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (board[r][c] != disc)
					continue;
				// horizontal
				if (c + 3 < COLS && board[r][c + 1] == disc && board[r][c + 2] == disc && board[r][c + 3] == disc)
					return true;
				// vertical
				if (r + 3 < ROWS && board[r + 1][c] == disc && board[r + 2][c] == disc && board[r + 3][c] == disc)
					return true;
				// diag down-right
				if (r + 3 < ROWS && c + 3 < COLS && board[r + 1][c + 1] == disc && board[r + 2][c + 2] == disc
						&& board[r + 3][c + 3] == disc)
					return true;
				// diag down-left
				if (r + 3 < ROWS && c - 3 >= 0 && board[r + 1][c - 1] == disc && board[r + 2][c - 2] == disc
						&& board[r + 3][c - 3] == disc)
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean isBoardFull() {
		for (int c = 0; c < COLS; c++) {
			if (board[0][c] == ' ')
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < ROWS; r++) {
			sb.append('|');
			for (int c = 0; c < COLS; c++) {
				sb.append(board[r][c]);
				sb.append('|');
			}
			sb.append(System.lineSeparator());
		}
		sb.append(" 1 2 3 4 5 6 7");
		sb.append(System.lineSeparator());
		return sb.toString();
	}
}
