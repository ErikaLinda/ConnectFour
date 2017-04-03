package test.unit.kt1266;

import api.Chip;
import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;

public class ConnectFourUnitTest {
    Game game;
	
    @Before public void initialize() {
        game = new ConnectFour();
    }
	
    @Test
    public void testDefaultConstructor() {
        assertNotNull(game);
    }

	
    @Test
    public void testGetRows() {
    	int expectedVal = 6;
    	assertEquals(expectedVal, game.getRows());
    }
    
    @Test
    public void testGetColumns() {
    	int expectedVal = 7;
    	assertEquals(expectedVal, game.getColumns());
    }

    @Ignore // Test makes an assumption about row indexing.
    public void testplaceDisk() throws GameStateException, IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(0);
    	
    	// Make sure that the chips are stacking on top of each other
    	Chip[][] board = game.getBoard();
    	assertNotEquals(board[0][0], Chip.EMPTY);
    	assertNotEquals(board[0][1], Chip.EMPTY);
    	
    	// Check player chips are alternating
    	assertNotEquals(board[0][1], board[0][0]);
    	
    }
	
    @Test
    public void testVertical() throws GameStateException, IllegalMoveException {

        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
    	
    	Chip winner;
    	
    	winner = game.getWinningPlayer();

    	// Make sure we have a winner
    	assertNotEquals(winner, Chip.EMPTY);
    }
	
    @Test
    public void testHorizontal() throws GameStateException, IllegalMoveException {

        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(2);
        game.placeDisk(3);
			
    	Chip winner;
    	
    	winner = game.getWinningPlayer();
    	
    	// Make sure we have a winner
    	assertNotEquals(winner, Chip.EMPTY);
    }
	
    @Test
    public void testDiagonalLeftToRight() throws GameStateException, IllegalMoveException {

        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(5);
        game.placeDisk(3);
        game.placeDisk(3);
        game.placeDisk(3);

        Chip winner;
    	
    	winner = game.getWinningPlayer();
    	
    	// Make sure we have a winner
    	assertNotEquals(winner, Chip.EMPTY);
    }
	
    @Test
    public void testDiagonalRightToLeft() throws GameStateException, IllegalMoveException {
        game.placeDisk(6);
        game.placeDisk(5);
        game.placeDisk(5);
        game.placeDisk(4);
        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(4);
        game.placeDisk(3);
        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(3);

        Chip winner;
    	
    	winner = game.getWinningPlayer();
    	
    	// Make sure we have a winner
    	assertNotEquals(winner, Chip.EMPTY);
    }

    // When the rows are full, should get an exception
    @Test(expected=IllegalMoveException.class)
    public void testFillRows() throws GameStateException, IllegalMoveException {
        int i = 0;
        while (i<game.getRows()+1) {
            game.placeDisk(0);
            i++;
        }
    }
	
    // When the columns are full, should get an exception
    @Test(expected=IllegalMoveException.class)
    public void testFillColumns() throws GameStateException, IllegalMoveException {
        int i = 0;
        while (i<game.getColumns()+1) {
            game.placeDisk(i);
            i++;
        }
    }

    // If game not over, should get false
    @Test
    public void testIsGameOverFalse() {
        assertFalse(game.isGameOver());
    }
	
    // If game is won, should get true
    @Test
    public void testIsGameOverTrue() throws GameStateException, IllegalMoveException {
        // From Horizontal Test
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(2);
        game.placeDisk(3);
        assertTrue(game.isGameOver());
    }
}
