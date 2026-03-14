package it.unibo;

public final class ConnectFourImpl implements ConnectFour {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int CONNECT_LENGTH = 4;
    private static final char RED_DISC = 'R';
    private static final char YELLOW_DISC = 'Y';

    private final Player[][] board;
    private Player winner;
    private int movesPlayed;

    public ConnectFourImpl() {
        this.board = new Player[ROWS][COLUMNS];
        this.winner = null;
        this.movesPlayed = 0;
    }

    @Override
    public void dropDisc(final int column, final char disc) {
        validateColumn(column);
        final Player currentPlayer = toPlayer(disc);

        if (this.winner != null) {
            throw new IllegalStateException("The game is already over");
        }
        if (!isValidOpeningMove(currentPlayer)) {
            throw new IllegalStateException("It is not this player's turn");
        }

        final int row = firstAvailableRow(column);
        if (row == -1) {
            throw new IllegalStateException("The selected column is full");
        }

        this.board[row][column] = currentPlayer;
        this.movesPlayed++;
        if (hasConnectFrom(row, column, currentPlayer)) {
            this.winner = currentPlayer;
        }
    }

    @Override
    public boolean checkWin(final Player player) {
        if (player == null) {
            return false;
        }
        if (this.winner == player) {
            return true;
        }

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (this.board[row][column] == player && hasConnectFrom(row, column, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBoardFull() {
        for (int column = 0; column < COLUMNS; column++) {
            if (this.board[ROWS - 1][column] == null) {
                return false;
            }
        }
        return true;
    }

    private void validateColumn(final int column) {
        if (column < 0 || column >= COLUMNS) {
            throw new IllegalArgumentException("Column out of bounds");
        }
    }

    private Player toPlayer(final char disc) {
        final char normalizedDisc = Character.toUpperCase(disc);
        if (normalizedDisc == RED_DISC) {
            return Player.RED;
        }
        if (normalizedDisc == YELLOW_DISC) {
            return Player.YELLOW;
        }
        throw new IllegalArgumentException("Disc must be R or Y");
    }

    private int firstAvailableRow(final int column) {
        for (int row = 0; row < ROWS; row++) {
            if (this.board[row][column] == null) {
                return row;
            }
        }
        return -1;
    }

    private boolean hasConnectFrom(final int row, final int column, final Player player) {
        return hasConnectInDirection(row, column, player, 1, 0)
                || hasConnectInDirection(row, column, player, 0, 1)
                || hasConnectInDirection(row, column, player, 1, 1)
                || hasConnectInDirection(row, column, player, 1, -1);
    }

    private boolean hasConnectInDirection(
            final int row,
            final int column,
            final Player player,
            final int rowStep,
            final int columnStep
    ) {
        final int connectedCount = 1
                + countInDirection(row, column, player, rowStep, columnStep)
                + countInDirection(row, column, player, -rowStep, -columnStep);
        return connectedCount >= CONNECT_LENGTH;
    }

    private int countInDirection(
            final int row,
            final int column,
            final Player player,
            final int rowStep,
            final int columnStep
    ) {
        int count = 0;
        int currentRow = row + rowStep;
        int currentColumn = column + columnStep;

        while (isInsideBoard(currentRow, currentColumn) && this.board[currentRow][currentColumn] == player) {
            count++;
            currentRow += rowStep;
            currentColumn += columnStep;
        }
        return count;
    }

    private boolean isInsideBoard(final int row, final int column) {
        return row >= 0 && row < ROWS && column >= 0 && column < COLUMNS;
    }

    private boolean isValidOpeningMove(final Player currentPlayer) {
        if (this.movesPlayed == 0) {
            return currentPlayer == Player.RED;
        }
        if (this.movesPlayed == 1) {
            return currentPlayer == Player.YELLOW;
        }
        return true;
    }
}
