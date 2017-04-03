package test.unit.hs2973;

import api.Chip;
import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;

public class ConnectFourBasicTest {
	
    Game game;
	
    @Before
    public void initialize() {
	game = new ConnectFour();
    }
    
    /*
     * Test for default constructor
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(game);
    }
	
    /*
     * Check if the board has right number of rows
     */
    @Test
    public void testGetRows() {
	assertEquals(6, game.getRows());
    }
	
    /*
     * Check if the board has right number of columns
     */
    @Test
    public void testGetColumns() {
	assertEquals(7, game.getColumns());
    }
	
    /*
     * Check if the board is empty or not
     */
    @Test
    public void testGetBoard() {	
	Chip[][] board = game.getBoard();
	assertNotNull(board);
		
	// check if the board is correctly initialized with empty chips
	for (int i = 0; i < game.getRows(); i++) {
	    for (int j = 0; j < game.getColumns(); j++) {
		assertTrue(board[i][j].isEmpty()); // it is expected that all chips be empty when game starts
	    }
	}
		
    }
	
    /*
     * Check if the place disk method works (with and without turns)
     */
    @Test
    public void testPlaceDisk() throws GameStateException,
				       IllegalMoveException {
	game.placeDisk(0);
	game.placeDisk(1); // increment turn by placing another disk
		
	Chip chip1 = game.getBoard()[game.getRows() - 1][0];
	Chip chip2 = game.getBoard()[game.getRows() - 1][1];
		
	assertFalse(chip1.isEmpty());
	assertFalse(chip2.isEmpty());
		
	assertNotEquals(chip1, chip2); // chip1 and chip2 should be different, given the turn system works
    }
	
    /*
     * Test if the game handles placeDisk() method exceptions
     */
    @Test(expected = IllegalMoveException.class)
    public void testPlaceDiskExceptions1() throws GameStateException,
						  IllegalMoveException {
	game.placeDisk(game.getColumns() + 2); // out of range column
    }
    
    @Test(expected = IllegalMoveException.class)
    public void testPlaceDiskExceptions2() throws GameStateException,
						  IllegalMoveException {
	for (int i = 0; i < game.getRows(); i++) {
	    game.placeDisk(0);
	}
			
	game.placeDisk(0); // this should fail, as all rows in the 0th
			   // col has been filled
    }
	
    /*
     * Test if the getCurrentPlayer() methods returns a correct player instance
     */
    @Test
    public void testGetCurrentPlayer() {
	assertTrue(game.getCurrentPlayer() instanceof Chip);
    }
	
	
    /*
     * Test if the game is over or not
     */
    @Test
    public void testIsGameOver() {
	assertFalse(game.isGameOver()); // game just started, so should return false
    }
	
    /*
     * Test for winning player - Horizontal check
     */
    @Test
    public void testGetWinningPlayerHorizontal ()
	throws GameStateException,
	       IllegalMoveException {
	Chip firstPlayer = game.getCurrentPlayer();
	game.placeDisk(0);
	game.placeDisk(1);
	game.placeDisk(0);
	game.placeDisk(1);
	game.placeDisk(0);
	game.placeDisk(1);
	game.placeDisk(0); // four chips in a column ->
	// winning move for first player
			
	assertEquals(firstPlayer, game.getWinningPlayer());
    }
	
    /*
     * Test for winning player - Vertical check
     */
    @Test
    public void testGetWinningPlayerVertical ()
	throws GameStateException,
	       IllegalMoveException {
	Chip firstPlayer = game.getCurrentPlayer();
		
	game.placeDisk(1);
	game.placeDisk(1);
	game.placeDisk(2);
	game.placeDisk(2);
	game.placeDisk(3);
	game.placeDisk(3);
	game.placeDisk(4); // four chips in a row -> winning
	// move for first player
			
	assertEquals(firstPlayer, game.getWinningPlayer());
    }
	
    /*
     * Test for winning player - Front diagonal check
     */
    @Test
    public void testGetWinningPlayerFrontDiagonal ()
	throws GameStateException,
	       IllegalMoveException {
	Chip firstPlayer = game.getCurrentPlayer();
		
	game.placeDisk(1); // 1
	game.placeDisk(2);
	game.placeDisk(2); // 2
	game.placeDisk(3);
	game.placeDisk(3);
	game.placeDisk(4);
	game.placeDisk(3); // 3 
	game.placeDisk(4); 
	game.placeDisk(4); 
	game.placeDisk(6); 
	game.placeDisk(4); // 4 four chips in a front diagonal ->
                           // winning move for first player
			
	assertEquals(firstPlayer, game.getWinningPlayer());
    }
	
    /*
     * Test for winning player - Back diagonal check
     */
    @Ignore
    public void testGetWinningPlayerBackDiagonal ()
	throws GameStateException,
	       IllegalMoveException {
	game.placeDisk(1);
			
	Chip secondPlayer = game.getCurrentPlayer();
			
	game.placeDisk(1);
	game.placeDisk(1); 
	game.placeDisk(1); // 1
	game.placeDisk(2);
	game.placeDisk(2);
	game.placeDisk(3); 
	game.placeDisk(2); // 2
	game.placeDisk(4); 
	game.placeDisk(3); // 3
	game.placeDisk(5);
	game.placeDisk(4); // 4 four chips in a back diagonal ->
                           // winning move for second player
			
	assertEquals(secondPlayer, game.getWinningPlayer());
    }
	
    /*
     * Test for getWinningPlayer() method exceptions
     */
    @Test(expected = GameStateException.class)
    public void testGetWinningPlayerException() throws GameStateException,
						       IllegalMoveException {
	Game game = new ConnectFour();
	game.getWinningPlayer(); // game just started, so there can be
	                         // no winning player
    }
}
