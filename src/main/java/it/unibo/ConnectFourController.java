package it.unibo;

public final class ConnectFourController {
	private final ConnectFour game;
	private final ConnectFourView view;
	private ConnectFour.Player currentPlayer;

	public ConnectFourController(final ConnectFour game, final ConnectFourView view) {
		this.game = game;
		this.view = view;
		this.currentPlayer = ConnectFour.Player.RED;
	}

	public void play() {
		while (true) {
			this.view.printBoard();
			if (this.tryPlayTurn()) {
				return;
			}
		}
	}

	private boolean tryPlayTurn() {
		final char currentDisc = this.toDisc(this.currentPlayer);
		try {
			final int column = this.view.readColumn(this.currentPlayer);
			this.game.dropDisc(column, currentDisc);
			this.view.placeDisc(column, currentDisc);
		} catch (final IllegalArgumentException ex) {
			this.view.showInvalidMove(ex.getMessage());
			return false;
		}

		if (this.game.checkWin(this.currentPlayer)) {
			this.view.printBoard();
			this.view.showWinner(this.currentPlayer);
			return true;
		}
		if (this.game.isBoardFull()) {
			this.view.printBoard();
			this.view.showDraw();
			return true;
		}

		this.currentPlayer = this.currentPlayer == ConnectFour.Player.RED
				? ConnectFour.Player.YELLOW
				: ConnectFour.Player.RED;
		return false;
	}

	private char toDisc(final ConnectFour.Player player) {
		if (player == ConnectFour.Player.RED) {
			return 'R';
		}
		return 'Y';
	}
}
