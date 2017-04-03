package test.unit.kqm1;

import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ConnectFourGameTest {
    private Game game;

    @Before
    public void setup() {
        game = new ConnectFour();
    }

    @Test
    /*Vertical Win Left Bottom
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      |x| | | | | | |
      +-+-+-+-+-+-+-+
      |x|0| | | | | |
      +-+-+-+-+-+-+-+
      |x|0| | | | | |
      +-+-+-+-+-+-+-+
      |x|0| | | | | |
      +-+-+-+-+-+-+-+
    */
    public void testVerticalWinLeftBottom() throws GameStateException,
                                                   IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(1);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(0);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    @Test
    /*Vertical Win Right Bottom
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | |x|
      +-+-+-+-+-+-+-+
      | | | | | |0|x|
      +-+-+-+-+-+-+-+
      | | | | | |0|x|
      +-+-+-+-+-+-+-+
      | | | | | |0|x|
      +-+-+-+-+-+-+-+*/
    public void testVerticalWinRightBottom() throws GameStateException,
                                                    IllegalMoveException {
        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(6);
        game.placeDisk(5);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(6);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    @Test
    /*Vertical Win Left Top
      +-+-+-+-+-+-+-+
      |x| | | | | | |
      +-+-+-+-+-+-+-+
      |x|0| | | | | |
      +-+-+-+-+-+-+-+
      |x|0| | | | | |
      +-+-+-+-+-+-+-+
      |x|0| | | | | |
      +-+-+-+-+-+-+-+
      |0|x| | | | | |
      +-+-+-+-+-+-+-+
      |x|0| | | | | |
      +-+-+-+-+-+-+-+*/
    public void testVerticalLeftTop() throws GameStateException,
                                             IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(1);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(0);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    @Test
    /*Vertical Win Right Top
      +-+-+-+-+-+-+-+
      | | | | | | |x|
      +-+-+-+-+-+-+-+
      | | | | | |0|x|
      +-+-+-+-+-+-+-+
      | | | | | |0|x|
      +-+-+-+-+-+-+-+
      | | | | | |0|x|
      +-+-+-+-+-+-+-+
      | | | | | |x|0|
      +-+-+-+-+-+-+-+
      | | | | | |0|x|
      +-+-+-+-+-+-+-+*/
    public void testVerticalLeftRight() throws GameStateException,
                                               IllegalMoveException {
        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(5);
        game.placeDisk(6);
        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(6);
        game.placeDisk(5);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(6);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }


    @Test
    /*Horizontal Win Left Bottom
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      |0|0|0| | | | |
      +-+-+-+-+-+-+-+
      |x|x|x|x| | | |
      +-+-+-+-+-+-+-+*/
    public void testHorizontalLeftBottom() throws GameStateException,
                                                  IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(2);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(3);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    @Test
    /*Horizontal Win Right Bottom
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | | | | |
      +-+-+-+-+-+-+-+
      | | | | |0|0|0|
      +-+-+-+-+-+-+-+
      | | | |x|x|x|x|
      +-+-+-+-+-+-+-+*/
    public void testHorizontalRightBottom() throws GameStateException,
                                                   IllegalMoveException {
        game.placeDisk(3);
        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(4);
        game.placeDisk(5);
        game.placeDisk(5);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(6);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }
    @Test
    /* Horizontal Win Left Top
       +-+-+-+-+-+-+-+
       |x|x|x|x| | | |
       +-+-+-+-+-+-+-+
       |x|0|x|0| | | |
       +-+-+-+-+-+-+-+
       |0|x|0|x| | | |
       +-+-+-+-+-+-+-+
       |0|x|0|x|0| | |
       +-+-+-+-+-+-+-+
       |x|0|x|0|0| | |
       +-+-+-+-+-+-+-+
       |x|0|x|0|0| | |
       +-+-+-+-+-+-+-+
    */

    public void testHorizontalTopLeft() throws GameStateException,
                                               IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(0);
        game.placeDisk(4);
        game.placeDisk(1);
        game.placeDisk(4);
        game.placeDisk(2);
        game.placeDisk(4);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(3);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    @Test
    /*Horizontal Win Right Top
      +-+-+-+-+-+-+-+
      | | | |x|x|x|x|
      +-+-+-+-+-+-+-+
      | | | |x|0|x|0|
      +-+-+-+-+-+-+-+
      | | | |0|x|0|x|
      +-+-+-+-+-+-+-+
      | | |0|0|x|0|x|
      +-+-+-+-+-+-+-+
      | | |0|x|0|x|0|
      +-+-+-+-+-+-+-+
      | | |0|x|0|x|0|
      +-+-+-+-+-+-+-+ */
    public void testHorizontalTopRight() throws GameStateException,
                                                IllegalMoveException {
        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(5);
        game.placeDisk(6);

        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(5);
        game.placeDisk(6);

        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(4);
        game.placeDisk(3);

        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(4);
        game.placeDisk(3);


        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(5);
        game.placeDisk(6);

        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(4);
        game.placeDisk(2);
        game.placeDisk(5);
        game.placeDisk(2);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(6);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    /* Diagonal Test Left Bottom
       +-+-+-+-+-+-+-+
       | | | | | | | |
       +-+-+-+-+-+-+-+
       | | | | | | | |
       +-+-+-+-+-+-+-+
       | | | |x| | | |
       +-+-+-+-+-+-+-+
       | | |x|x| | | |
       +-+-+-+-+-+-+-+
       |0|x|x|0| | | |
       +-+-+-+-+-+-+-+
       |x|0|0|0| | | |
       +-+-+-+-+-+-+-+
    */
    @Test
    public void testDiagonalLeftBottom() throws GameStateException,
                                                IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(3);
        game.placeDisk(0);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(3);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    /* Test diagonal Right Bottom
       +-+-+-+-+-+-+-+
       | | | | | | | |
       +-+-+-+-+-+-+-+
       | | | | | | | |
       +-+-+-+-+-+-+-+
       | | | |x| | | |
       +-+-+-+-+-+-+-+
       | | | |x|x| | |
       +-+-+-+-+-+-+-+
       | | | |0|x|x|0|
       +-+-+-+-+-+-+-+
       | | | |0|0|0|x|
       +-+-+-+-+-+-+-+ */
    @Test
    public void testDiagonalRightBottom() throws GameStateException,
                                                 IllegalMoveException {
        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(5);
        game.placeDisk(4);
        game.placeDisk(4);
        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(3);
        game.placeDisk(3);
        game.placeDisk(6);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(3);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }
    /* Test Diagonal Left Top
       +-+-+-+-+-+-+-+
       |0| | | | | | |
       +-+-+-+-+-+-+-+
       |x|0|x| | | | |
       +-+-+-+-+-+-+-+
       |0|x|0|x| | | |
       +-+-+-+-+-+-+-+
       |x|0|x|0| | | |
       +-+-+-+-+-+-+-+
       |x|0|x|0| | | |
       +-+-+-+-+-+-+-+
       |x|0|x|0| | | |
       +-+-+-+-+-+-+-+
    */

    @Test
    public void testDiagonalLeftTop() throws GameStateException,
                                             IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(3);

        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(3);

        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(3);


        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(1);
        game.placeDisk(0);

        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(2);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(0);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }

    /* Diagonal Test Right Top
       +-+-+-+-+-+-+-+
       | | | | | | |x|
       +-+-+-+-+-+-+-+
       | | | | | |x|0|
       +-+-+-+-+-+-+-+
       | | | |0|x|0|x|
       +-+-+-+-+-+-+-+
       | | | |x|0|x|0|
       +-+-+-+-+-+-+-+
       | | | |x|0|x|0|
       +-+-+-+-+-+-+-+
       | | | |x|0|x|0|
       +-+-+-+-+-+-+-+ */
    @Test
    public void testDiagonalRightTop() throws GameStateException,
                                              IllegalMoveException {
        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(5);
        game.placeDisk(6);

        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(5);
        game.placeDisk(6);

        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(5);
        game.placeDisk(6);

        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(4);
        game.placeDisk(3);


        game.placeDisk(5);
        game.placeDisk(6);

        Chip chip = game.getCurrentPlayer();
        game.placeDisk(6);
        assertTrue(game.isGameOver());
        assertEquals(game.getWinningPlayer(), chip);
    }
}
