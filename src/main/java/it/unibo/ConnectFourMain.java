package it.unibo;

import java.util.Locale;
import java.util.Scanner;

public final class ConnectFourMain {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY_CELL = '.';

    private ConnectFourMain() {
    }

    public static void main(final String[] args) {
        final ConnectFour game = new ConnectFourImpl();
        final char[][] boardView = createEmptyBoard();

        try (Scanner scanner = new Scanner(System.in)) {
            printWelcome();
            printBoard(boardView);

            while (true) {
                final int column = readColumn(scanner);
                final char disc = readDisc(scanner);

                try {
                    game.dropDisc(column, disc);
                    placeDisc(boardView, column, disc);
                    printBoard(boardView);

                    final ConnectFour.Player player = toPlayer(disc);
                    if (game.checkWin(player)) {
                        System.out.println("Vittoria di " + player + "!");
                        break;
                    }
                    if (game.isBoardFull()) {
                        System.out.println("Pareggio: la board e piena.");
                        break;
                    }
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    System.out.println("Mossa non valida: " + ex.getMessage());
                }
            }
        }
    }

    private static void printWelcome() {
        System.out.println("Connect Four CLI");
        System.out.println("Colonne valide: 0-6");
        System.out.println("Dischi validi: R o Y");
        System.out.println();
    }

    private static int readColumn(final Scanner scanner) {
        while (true) {
            System.out.print("Inserisci colonna (0-6): ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            scanner.next();
            System.out.println("Valore non valido: inserisci un numero intero.");
        }
    }

    private static char readDisc(final Scanner scanner) {
        while (true) {
            System.out.print("Inserisci disco (R/Y): ");
            final String token = scanner.next().toUpperCase(Locale.ROOT);
            if (token.length() == 1) {
                final char disc = token.charAt(0);
                if (disc == 'R' || disc == 'Y') {
                    return disc;
                }
            }
            System.out.println("Valore non valido: usa R o Y.");
        }
    }

    private static char[][] createEmptyBoard() {
        final char[][] board = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                board[row][column] = EMPTY_CELL;
            }
        }
        return board;
    }

    private static void placeDisc(final char[][] board, final int column, final char disc) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][column] == EMPTY_CELL) {
                board[row][column] = disc;
                return;
            }
        }
    }

    private static void printBoard(final char[][] board) {
        System.out.println();
        for (int row = ROWS - 1; row >= 0; row--) {
            for (int column = 0; column < COLUMNS; column++) {
                System.out.print(board[row][column]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
        System.out.println();
    }

    private static ConnectFour.Player toPlayer(final char disc) {
        return disc == 'R' ? ConnectFour.Player.RED : ConnectFour.Player.YELLOW;
    }
}
