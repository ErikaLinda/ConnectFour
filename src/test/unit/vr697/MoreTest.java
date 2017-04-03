package test.unit.vr697;

import api.Game;
import api.Chip;

import impl.game.ConnectFour;

import exc.GameStateException;
import exc.IllegalMoveException;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class MoreTest {
    private Game game;

    @Before
    public void initialize() {
       game = new ConnectFour();
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void test_init_board() {
        Chip[][] board = game.getBoard();
        Chip default_chip = Chip.EMPTY;

        for (Chip[] col : board) {
            for (Chip chip : col) {
                assertEquals(default_chip, chip);
            }
        }
    }

    @Test
    public void test_getRows() {
        int get_row = game.getRows();
        int correct_row = 6;
        assertEquals(correct_row, get_row);
    }

    @Test
    public void test_getColumns() {
        int get_col = game.getColumns();
        int correct_col = 7;
        assertEquals(correct_col, get_col);
    }

    @Test
    public void test_placeDisk_changeCurrentPlayer()
    throws GameStateException, IllegalMoveException {
        game.placeDisk(0, 0);
        assertEquals(game.getCurrentPlayer(), Chip.BLUE);
    }

    @Test
    public void test_placeDisk_returnIllegalMove_lessThanZeroRow()
    throws GameStateException, IllegalMoveException {
        exception.expect(IllegalMoveException.class);
        game.placeDisk(-1, 0);
    }

    @Test
    public void test_placeDisk_returnIllegalMove_lessThanZeroCol()
    throws GameStateException, IllegalMoveException {
        exception.expect(IllegalMoveException.class);
        game.placeDisk(0, -1);
    }

    @Test
    public void test_placeDisk_returnIllegalMove_moreThanMaxRow()
    throws GameStateException, IllegalMoveException {
        exception.expect(IllegalMoveException.class);
        game.placeDisk(6, 0);
    }

    @Test
    public void test_placeDisk_returnIllegalMove_moreThanMaxCol()
    throws GameStateException, IllegalMoveException {
        exception.expect(IllegalMoveException.class);
        game.placeDisk(0, 7);
    }

    @Test
    public void test_isVertical()
    throws GameStateException, IllegalMoveException {
        boolean isRedTurn = true;
        for (int i = 0; i < 7; i++) {
            if (isRedTurn) {
                game.placeDisk(0, 4);
                isRedTurn = false;
            } else {
                game.placeDisk(0, 3);
                isRedTurn = true;
            }
        }
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void test_isHorizontal()
    throws GameStateException, IllegalMoveException {
        for (int i = 0; i < 7; i++) {
            game.placeDisk(0, i/2);
        }
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void test_isPDiagonal()
    throws GameStateException, IllegalMoveException {
        int[] orderChipPlacement = {
            0, 1, 1, 2, 2, 3,
            2, 3, 3, 2, 3
        };
        for (int i : orderChipPlacement) {
             game.placeDisk(0, i);
        }
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void test_isPDiagonal_EdgeCase()
    throws GameStateException, IllegalMoveException {
        int[] orderChipPlacement = {
            0, 0, 0, 1, 1, 1,
            1, 2, 2, 2, 2, 5,
            2, 5, 3, 3, 3, 3,
            6, 3, 3
        };
        for (int i : orderChipPlacement) {
             game.placeDisk(0, i);
        }
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void test_isNDiagonal()
    throws GameStateException, IllegalMoveException {
        int[] orderChipPlacement = {
            0, 0, 0, 0, 2, 2,
            4, 3, 3, 1, 1, 1
        };
        for (int i : orderChipPlacement) {
             game.placeDisk(0, i);
        }
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void test_isNDiagonal_EdgeCase()
    throws GameStateException, IllegalMoveException {
        int[] orderChipPlacement = {
            6, 5, 6, 5, 6, 5,
            5, 6, 4, 3, 4, 3,
            2, 4, 3, 4, 3, 3,
            4, 5, 3
        };
        for (int i : orderChipPlacement) {
             game.placeDisk(0, i);
        }
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void test_isTie()
    throws GameStateException, IllegalMoveException {
        int[] orderChipPlacement = {
            0, 1, 0, 1, 0, 1,
            2, 3, 2, 3, 2, 3,
            4, 5, 4, 5, 4, 5,
            6, 0, 6, 0, 6, 0,
            1, 2, 1, 2, 1, 2,
            3, 4, 3, 4, 3, 4,
            5, 6, 5, 6, 5, 6
        };
        for (int i : orderChipPlacement) {
             game.placeDisk(0, i);
        }
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void test_getWinningPlayer_tie()
    throws GameStateException, IllegalMoveException {
        int[] orderChipPlacement = {
            0, 1, 0, 1, 0, 1,
            2, 3, 2, 3, 2, 3,
            4, 5, 4, 5, 4, 5,
            6, 0, 6, 0, 6, 0,
            1, 2, 1, 2, 1, 2,
            3, 4, 3, 4, 3, 4,
            5, 6, 5, 6, 5, 6
        };
        for (int i : orderChipPlacement) {
             game.placeDisk(0, i);
        }
        game.isGameOver(); // update winning player in this call
        exception.expect(GameStateException.class);
        game.getWinningPlayer();
    }

    /*
     * Specification does not mention player ordering
     */

    @Ignore
    public void test_getCurrentPlayer() {
        Chip curr_player = game.getCurrentPlayer();
        Chip correct_curr_player = Chip.RED;
        assertEquals(correct_curr_player, curr_player);
    }

    @Ignore
    public void test_placeDisk_returnIllegalMove_highRow()
    throws GameStateException, IllegalMoveException {
        exception.expect(IllegalMoveException.class);
        game.placeDisk(1, 0);
    }

    @Ignore
    public void test_placeDisk_stackUp()
    throws GameStateException, IllegalMoveException {
        for (int i = 0; i < 4; i++) {
            game.placeDisk(0, 0);
        }
        Chip[] correct_column = {Chip.RED, Chip.BLUE, Chip.RED, Chip.BLUE, Chip.EMPTY, Chip.EMPTY};
        Chip[] game_column = game.getBoard()[0];
        assertArrayEquals(correct_column, game_column);
    }

    @Ignore
    public void test_getWinningPlayer_red()
    throws GameStateException, IllegalMoveException {
        for (int i = 0; i < 7; i++) {
            game.placeDisk(0, i/2);
        }
        game.isGameOver(); // update winning player in this call
        assertEquals(Chip.RED, game.getWinningPlayer());
    }

    @Ignore
    public void test_getWinningPlayer_blue()
    throws GameStateException, IllegalMoveException {
        game.placeDisk(0, 6);
        for (int i = 0; i < 7; i++) {
            game.placeDisk(0, i/2);
        }
        game.isGameOver(); // update winning player in this call
        assertEquals(Chip.BLUE, game.getWinningPlayer());
    }
}
