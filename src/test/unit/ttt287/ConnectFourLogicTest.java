package test.unit.ttt287;

import api.Chip;
import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class ConnectFourLogicTest {
    @Test
    public void testInitializingEmptyBoard() {
        Game game = new ConnectFour();
        Chip[][] board = game.getBoard();
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getColumns(); col++) {
                assertEquals(board[row][col], Chip.EMPTY);
            }
        }
    }

    @Test
    public void testBoardSize() {
        Game game = new ConnectFour();
        assertEquals(game.getRows(), 6);
        assertEquals(game.getColumns(), 7);
    }
}

