package it.unibo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ConnectFourTest {

    @Test
    void newBoardIsNotFull() {
        final ConnectFour game = new ConnectFourImpl();

        assertFalse(game.isBoardFull());
    }

    @Test
    void newBoardHasNoWinnerForRedPlayer() {
        final ConnectFour game = new ConnectFourImpl();

        assertFalse(game.checkWin(ConnectFour.Player.RED));
    }

    @Test
    void dropDiscRejectsInvalidColumn() {
        final ConnectFour game = new ConnectFourImpl();

        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(-1, 'R'));
        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(7, 'R'));
    }

    @Test
    void dropDiscRejectsInvalidDiscSymbol() {
        final ConnectFour game = new ConnectFourImpl();

        assertThrows(IllegalArgumentException.class, () -> game.dropDisc(0, 'X'));
    }

    @Test
    void firstMoveMustBeRedThenYellow() {
        final ConnectFour game = new ConnectFourImpl();

        assertThrows(IllegalStateException.class, () -> game.dropDisc(0, 'Y'));

        game.dropDisc(0, 'R');

        assertThrows(IllegalStateException.class, () -> game.dropDisc(1, 'R'));
    }

    @Test
    void cannotDropInFullColumn() {
        final ConnectFour game = new ConnectFourImpl();

        game.dropDisc(0, 'R');
        game.dropDisc(0, 'Y');
        game.dropDisc(0, 'R');
        game.dropDisc(0, 'Y');
        game.dropDisc(0, 'R');
        game.dropDisc(0, 'Y');

        assertThrows(IllegalStateException.class, () -> game.dropDisc(0, 'R'));
    }

    @Test
    void redPlayerWinsHorizontally() {
        final ConnectFour game = new ConnectFourImpl();

        game.dropDisc(0, 'R');
        game.dropDisc(0, 'Y');
        game.dropDisc(1, 'R');
        game.dropDisc(1, 'Y');
        game.dropDisc(2, 'R');
        game.dropDisc(2, 'Y');
        game.dropDisc(3, 'R');

        assertTrue(game.checkWin(ConnectFour.Player.RED));
    }

    @Test
    void yellowPlayerWinsVertically() {
        final ConnectFour game = new ConnectFourImpl();

        game.dropDisc(1, 'R');
        game.dropDisc(0, 'Y');
        game.dropDisc(1, 'R');
        game.dropDisc(0, 'Y');
        game.dropDisc(1, 'R');
        game.dropDisc(0, 'Y');
        game.dropDisc(2, 'R');
        game.dropDisc(0, 'Y');

        assertTrue(game.checkWin(ConnectFour.Player.YELLOW));
    }

    @Test
    void redPlayerWinsDiagonally() {
        final ConnectFour game = new ConnectFourImpl();

        game.dropDisc(0, 'R');
        game.dropDisc(1, 'Y');
        game.dropDisc(1, 'R');
        game.dropDisc(2, 'Y');
        game.dropDisc(5, 'R');
        game.dropDisc(2, 'Y');
        game.dropDisc(2, 'R');
        game.dropDisc(3, 'Y');
        game.dropDisc(5, 'R');
        game.dropDisc(3, 'Y');
        game.dropDisc(5, 'R');
        game.dropDisc(3, 'Y');
        game.dropDisc(3, 'R');

        assertTrue(game.checkWin(ConnectFour.Player.RED));
    }

    @Test
    void gameIsOverAfterWin() {
        final ConnectFour game = new ConnectFourImpl();

        game.dropDisc(0, 'R');
        game.dropDisc(0, 'Y');
        game.dropDisc(1, 'R');
        game.dropDisc(1, 'Y');
        game.dropDisc(2, 'R');
        game.dropDisc(2, 'Y');
        game.dropDisc(3, 'R');

        assertThrows(IllegalStateException.class, () -> game.dropDisc(3, 'Y'));
    }

    @Test
    void boardIsFullWhenAllCellsAreOccupied() {
        final ConnectFour game = new ConnectFourImpl();

        final char[][] columnContent = {
            {'R', 'Y', 'R', 'Y', 'R', 'Y'},
            {'R', 'Y', 'R', 'Y', 'R', 'Y'},
            {'Y', 'R', 'Y', 'R', 'Y', 'R'},
            {'Y', 'R', 'Y', 'R', 'Y', 'R'},
            {'R', 'Y', 'R', 'Y', 'R', 'Y'},
            {'R', 'Y', 'R', 'Y', 'R', 'Y'},
            {'Y', 'R', 'Y', 'R', 'Y', 'R'}
        };

        for (int column = 0; column < columnContent.length; column++) {
            for (int row = 0; row < columnContent[column].length; row++) {
                game.dropDisc(column, columnContent[column][row]);
            }
        }

        assertTrue(game.isBoardFull());
        assertFalse(game.checkWin(ConnectFour.Player.RED));
        assertFalse(game.checkWin(ConnectFour.Player.YELLOW));
    }
}
