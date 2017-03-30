package test.unit.elo239;

import api.Game;
import api.Chip;
import impl.game.ConnectFour;

import org.junit.Test;

import exc.GameStateException;
import exc.IllegalMoveException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class ConnectFourTest {
    Game gameGlobal = new ConnectFour();

    @Test
    public void testDefaultConstructor() {
        Game game = new ConnectFour();
        assertNotNull(game);
    }

    @Test
    public void testGetRows(){
	int rows = gameGlobal.getRows();
	assertEquals(rows, 6);
    }

    @Test
    public void testGetColumns(){
	int col = gameGlobal.getColumns();
	assertEquals(col, 7);
    }

    @Test
    public void testGetBoard(){
	boolean b = gameGlobal.getBoard() instanceof Chip[][];
	assertTrue(b);
    }

    @Test
    public void testBoardBounds1() throws GameStateException,
					  IllegalMoveException {
	gameGlobal.placeDisk(1);
	gameGlobal.placeDisk(0);
	gameGlobal.placeDisk(6);
    }

    @Test(expected = IllegalMoveException.class)
    public void testBoardBounds2() throws GameStateException,
					  IllegalMoveException {
	gameGlobal.placeDisk(-1);
    }

    @Test(expected = IllegalMoveException.class)
    public void testBoardBounds3() throws GameStateException,
					  IllegalMoveException {
	gameGlobal.placeDisk(7);
    }

    @Test
    public void testPlayerSwitch() throws GameStateException,
					  IllegalMoveException {
	Chip current = gameGlobal.getCurrentPlayer();
	gameGlobal.placeDisk(5);
	Chip next = gameGlobal.getCurrentPlayer();

	assertNotEquals(current, next);
    }

    @Test(expected = IllegalMoveException.class)
    public void testFullColumnError() throws GameStateException,
					     IllegalMoveException {
	for(int i = 0; i < 7; i++){
	    gameGlobal.placeDisk(2);
	}
    }

    @Test(expected = GameStateException.class)
    public void testNoWinner() throws GameStateException,
				      IllegalMoveException {
	gameGlobal.getWinningPlayer();
    }

    @Test
    public void testRowWinner() throws GameStateException,
                                       IllegalMoveException {
	Game game = new ConnectFour();
	Chip futureWinner = game.getCurrentPlayer();

	game.placeDisk(0);
	game.placeDisk(0);
	game.placeDisk(1);
	game.placeDisk(0);
	game.placeDisk(2);
	game.placeDisk(0);
	game.placeDisk(3);

	Chip winner = game.getWinningPlayer();
	assertEquals(winner, futureWinner);
    }

    @Test
    public void testColumnWinner() throws GameStateException,
                                          IllegalMoveException {
	Game game = new ConnectFour();
	Chip futureWinner = game.getCurrentPlayer();

	game.placeDisk(0);
	game.placeDisk(1);
	game.placeDisk(0);
	game.placeDisk(2);
	game.placeDisk(0);
	game.placeDisk(3);
	game.placeDisk(0);

	Chip winner = game.getWinningPlayer();
	assertEquals(winner, futureWinner);
    }

    @Test
    public void testDiagonalWinner1() throws GameStateException,
                                             IllegalMoveException {
	Game game = new ConnectFour();
	Chip futureWinner = game.getCurrentPlayer();

	game.placeDisk(0);
	game.placeDisk(1);

	game.placeDisk(1);
	game.placeDisk(2);

	game.placeDisk(2);
	game.placeDisk(0);

	game.placeDisk(2);
	game.placeDisk(3);

	game.placeDisk(3);
	game.placeDisk(3);

	game.placeDisk(3);

	Chip winner = game.getWinningPlayer();
	assertEquals(winner, futureWinner);
    }

    @Test
    public void testDiagonalWinner2() throws GameStateException,
                                             IllegalMoveException {
	Game game = new ConnectFour();
	Chip futureWinner = game.getCurrentPlayer();

	game.placeDisk(0);
	game.placeDisk(0);

	game.placeDisk(0);
	game.placeDisk(6);

	game.placeDisk(0);
	game.placeDisk(1);

	game.placeDisk(1);
	game.placeDisk(6);

	game.placeDisk(1);
	game.placeDisk(2);

	game.placeDisk(2);
	game.placeDisk(6);

	game.placeDisk(3);

	Chip winner = game.getWinningPlayer();
	assertEquals(winner, futureWinner);
    }

    private Game getTieGame() throws GameStateException,
				     IllegalMoveException {
	Game game = new ConnectFour();
	//create tie situation
	for(int j = 0; j < 6; j++){
	    for(int i = 0; i < 7; i++){
		if(j % 3 == 0){
		    if(i == 6){
			game.placeDisk(0);
		    }else{
			game.placeDisk(i+1);
		    }
		}
		else{
		    game.placeDisk(i);
		}
	    }
	}
	return game;
    }

    @Test(expected = GameStateException.class)
    public void testTieNoPlacement() throws GameStateException,
					    IllegalMoveException {
		Game game = getTieGame();
		game.placeDisk(0);
    }

    @Test(expected = GameStateException.class)
    public void testTieNoWinner() throws GameStateException,
					 IllegalMoveException {
		Game game = getTieGame();

		// try calling getWinningPlayer
		game.getWinningPlayer();
    }

    @Test
    public void testTieNoCurrent() throws GameStateException,
					  IllegalMoveException {
		Game game = getTieGame();
		// see current player
		Chip player = game.getCurrentPlayer();
		assertEquals(player, Chip.EMPTY);
    }
}