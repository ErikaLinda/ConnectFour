package test.unit.sa3904;

import api.Game;
import api.Chip;

import impl.game.ConnectFour;

import exc.GameStateException;
import exc.IllegalMoveException;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/* Below is an implementation of five tests to test all the
 * parts of the implementation described for the game. The 
 * tests have been written with keeping generality in mind, and
 * with the least implementation specifics. That is why, the tests
 * should perform fine with other implementation of the game, given
 * all the requirement were fulfilled.
 * All the tests have been written with positive-test in mind, i.e.
 * all the tests should be passed by an implementation. Suppose,
 * an exception was to be thrown, then this is exactly what the tests
 * will check and therefore, all the correct implementations should
 * pass the tests.
 */

public class ConnectFourGameTest{

    /*
     *This is a very simple test to see if there's
     *any error in putting the chip in a valid location.
     *NOTE: This is just a super simple check and must 
     *always pass, but it doesn't guarantee that the game
     *implementation is perfect.
     */
    @Test
    public void testLegit() throws GameStateException,
                                   IllegalMoveException {
        Game game = new ConnectFour();
        game.placeDisk(0);
        assertFalse(game.isGameOver());
    }

    /*
     *Test to check if the board is really putting the
     *right chips in right palce. This test verifies
     *that two different chips are used and that they 
     *are placed in valid columns.
     */
    
    @Ignore // Test assumes a particular row-ordering, which was not
            // specified in the write-up.
    public void testnVerifyMultipleLegit(){
	Game game;
	
	try{
	    game = new ConnectFour();
	    game.placeDisk(0); //of some kind.
	    game.placeDisk(1); //of some other kind.
	    game.placeDisk(0); //of the first kind.
	    game.placeDisk(1); //of the second kind.
	}
	catch(Throwable t){
	    throw new Error("Failed to place multiple chips");
	}

	// now verify that the different chips were indeed put in the place.

	assertNotEquals(game.getBoard()[0][0], game.getBoard()[0][1]);
	assertNotEquals(game.getBoard()[1][0], game.getBoard()[1][1]);

	// also that the on top of each other are same.

	assertEquals(game.getBoard()[0][0], game.getBoard()[1][0]);
    }

    /*
     * Test for checking different invalid values for putting chips.
     * We should expect (correct) errors to be raised.
     */
    @Test(expected = IllegalMoveException.class)
    public void testExceptionsForBadColsLeft() throws GameStateException,
                                                      IllegalMoveException {
	Game game = new ConnectFour();
        game.placeDisk(-1);
    }

    @Test(expected = IllegalMoveException.class)
    public void testExceptionsForBadColsRight() throws GameStateException,
                                                       IllegalMoveException {
	Game game = new ConnectFour();
        game.placeDisk(10);
    }

     /*
     * Test for checking if proper error is raised when an overfull
     * column is filled.
     */
    @Test(expected = IllegalMoveException.class)
    public void testExceptionsColFull() throws GameStateException,
                                               IllegalMoveException {
	Game game = new ConnectFour();

        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(0);
    }

    /*
     *Check if winner is declared in a simple vertical case. Also make
     *sure that GameStateException is thrown if we try to play after a
     *winner is declared. It basically tests the three cases...
     */

    private Game makeAlmostWinner() throws GameStateException,
                                           IllegalMoveException {
        Game game = new ConnectFour();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                game.placeDisk(j);
            }
        }

        return game;
    }

    /*
     * ... 1. No unexpected winner.
     */
    @Test
    public void testNoUnexpectedWinner() throws GameStateException,
                                                IllegalMoveException {
        Game game = makeAlmostWinner();
        assertFalse(game.isGameOver());
    }
    
    /*
     * ... 2. Proper winner is declared.
     */
    @Test
    public void properWinnerIsDeclared() throws GameStateException,
                                                IllegalMoveException {
        Game game = makeAlmostWinner();
        Chip chip = game.getCurrentPlayer();
        game.placeDisk(0);
        assertEquals(chip, game.getWinningPlayer());
    }

    /*
     * ... 3. GameStateException be thrown if winner is already
     *        declared and we try to placeDisk();
     */
    @Test(expected = GameStateException.class)
    public void winnerAlreadyDeclared() throws GameStateException,
                                               IllegalMoveException {
        Game game = makeAlmostWinner();
        for (int i = 0; i < 2; i++) {
            game.placeDisk(0);
        }
    }
}
