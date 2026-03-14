package it.unibo;

public interface ConnectFourView {
	void printBoard();

	void placeDisc(int column, char disc);

	int readColumn(ConnectFour.Player player);

	void showInvalidMove(String message);

	void showWinner(ConnectFour.Player player);

	void showDraw();
}
