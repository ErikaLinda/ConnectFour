package test.unit.jsw7;

import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

// import api.View;
// import impl.ConsoleView;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConnectFourGameTest {
    private Game game;

    @Before
    public void setup() {
        game = new ConnectFour();
    }

    private void diagonalWinnerSetup() throws GameStateException,
					      IllegalMoveException {
        for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < game.getColumns(); j++) {
		game.placeDisk(j);
	    }
	}
    }

    private void createTieGame() throws GameStateException,
					IllegalMoveException {
        for (int i = 0; i < 3; i++) {
    	    for (int j = 0; j < game.getColumns(); j++) {
    		game.placeDisk(j);
    	    }
    	}

	for (int j = 1; j < game.getColumns(); j++) {
	    game.placeDisk(j);
    	}

	for (int j = 1; j >= 0; j--) {
	    int column = j * (game.getColumns() - 1);
	    game.placeDisk(column);
	}

	for (int j = 0; j < game.getColumns() - 1; j++) {
	    game.placeDisk(j);
	}

	for (int j = 0; j < game.getColumns(); j++) {
	    game.placeDisk(j);
    	}
    }

    @Test
    public void testInitSetsPlayer() {
        Chip chip = game.getCurrentPlayer();
	assertFalse(chip.isEmpty());
    }

    @Test
    public void testInitBlanksBoard() {
        Chip[][] surface = game.getBoard();

        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getColumns(); col++) {
                Chip chip = surface[row][col];
                assertTrue(chip.isEmpty());
            }
        }
    }

    @Test
    public void testInitSetsRows() {
        assertEquals(game.getRows(), 6);
    }

    @Test
    public void testInitSetsColumns() {
        assertEquals(game.getColumns(), 7);
    }

    @Test
    public void testNextPlayerSetAfterPlacedDisk()
        throws GameStateException,
               IllegalMoveException {
        Chip firstPlayer = game.getCurrentPlayer();
        game.placeDisk(0);
        Chip secondPlayer = game.getCurrentPlayer();

        assertNotEquals(firstPlayer, secondPlayer);
    }

    @Test(expected = IllegalMoveException.class)
    public void testDisksCannotBePlacedOutOfBounds()
        throws GameStateException,
               IllegalMoveException {
        game.placeDisk(game.getColumns() + 1);
    }

    @Test(expected = IllegalMoveException.class)
    public void testDisksCannotBePlacedNegatively()
        throws GameStateException,
               IllegalMoveException {
        game.placeDisk(-1);
    }

    @Test(expected = IllegalMoveException.class)
    public void testColumnPlacementBounded() throws GameStateException,
                                                    IllegalMoveException {
        for (int i = 0; i < game.getRows() + 1; i++) {
            game.placeDisk(0);
        }
    }

    @Test
    public void testVerticalWinnerFound() throws GameStateException,
                                                 IllegalMoveException {
        for (int i = 0; i < 7; i++) {
            game.placeDisk(i % 2);
        }

        assertTrue(game.isGameOver());
    }

    @Test
    public void testWinningPlayerIsSet() throws GameStateException,
                                                 IllegalMoveException {
	Chip player = null;

        for (int i = 0; i < 7; i++) {
	    player = game.getCurrentPlayer();
            game.placeDisk(i % 2);
        }

	assertEquals(game.getWinningPlayer(), player);
    }

    @Test
    public void testHorizontalWinnerFound() throws GameStateException,
                                                   IllegalMoveException {
        for (int i = 0; i < 4; i++) {
            int stop = (i < 3) ? 2 : 1;
            for (int j = 0; j < stop; j++) {
                game.placeDisk(i);
            }
        }

        assertTrue(game.isGameOver());
    }

    @Test
    public void testOutofOrderHorizontalWinnerFound()
        throws GameStateException,
               IllegalMoveException {
        for (int i = 0; i < 4; i++) {
            if (i != 1) {
                for (int j = 0; j < 2; j++) {
                    game.placeDisk(i);
                }
            }
        }
        game.placeDisk(1);

        assertTrue(game.isGameOver());
    }

    @Test
    public void testLeftRightDiagonalWinnerFound()
	throws GameStateException,
	       IllegalMoveException {
	diagonalWinnerSetup();
       	game.placeDisk(0);
        assertTrue(game.isGameOver());
    }

    @Test
    public void testRightLeftDiagonalWinnerFound()
	throws GameStateException,
	       IllegalMoveException {
	diagonalWinnerSetup();
       	game.placeDisk(game.getColumns() - 1);
        assertTrue(game.isGameOver());
    }

    @Test
    public void testTieGameIdentified() throws GameStateException,
    					       IllegalMoveException {
        createTieGame();
        assertTrue(game.isGameOver());
    }

    @Test(expected = GameStateException.class)
    public void testTieHasNoWinner() throws GameStateException,
					    IllegalMoveException {
        createTieGame();
	game.getWinningPlayer();
    }

    public void testTieHasNoCurrentPlayer() throws GameStateException,
						   IllegalMoveException {
        createTieGame();
	assertTrue(game.getCurrentPlayer().isEmpty());
    }
}
