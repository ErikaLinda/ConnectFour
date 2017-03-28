package test.unit.elo239;

import api.Game;
import api.Chip;
import impl.game.ConnectFour;

import org.junit.Test;

import exc.GameStateException;
import exc.IllegalMoveException;

import static org.junit.Assert.*;

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
    public void testBoardBounds1(){
		try {
		    gameGlobal.placeDisk(1);
		    gameGlobal.placeDisk(0);
		    gameGlobal.placeDisk(6);
		}
		catch (GameStateException e) {
	    	assertNull(e);
		}
		catch (IllegalMoveException e) {
	    	assertNull(e);
		}
	}

	 @Test
    public void testBoardBounds2(){
		try {
		    gameGlobal.placeDisk(-1);
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		catch (IllegalMoveException e) {
	    	assertNotNull(e);
		}
	}

	 @Test
    public void testBoardBounds3(){
		try {
		    gameGlobal.placeDisk(7);
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		catch (IllegalMoveException e) {
	    	assertNotNull(e);
		}
	}

	@Test
	public void testPlayerSwitch(){
		Chip current = gameGlobal.getCurrentPlayer();
		try {
		    gameGlobal.placeDisk(5);
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		catch (IllegalMoveException e) {
			assertNull(e);
		}
		Chip next = gameGlobal.getCurrentPlayer();

		assertNotEquals(current, next);
	}

	 @Test
    public void testFullColumnError(){
    	try{
    		for(int i = 0; i < 7; i++){
    			gameGlobal.placeDisk(2);
    		}
    	}
    	catch (GameStateException e) {
    		assertNull(e);
		}
		catch (IllegalMoveException e) {
	    	assertNotNull(e);
		}
	}
	@Test
    public void testNoWinner(){
    	try{
    		gameGlobal.getWinningPlayer();
    	}
    	catch (GameStateException e) {
    		assertNotNull(e);
		}
	}

	@Test public void testRowWinner(){
		Game game = new ConnectFour();
		Chip futureWinner = Chip.EMPTY;
		try {
			futureWinner = game.getCurrentPlayer();
			game.placeDisk(0);
			game.placeDisk(0);
		    game.placeDisk(1);
		    game.placeDisk(0);
		    game.placeDisk(2);
		    game.placeDisk(0);
		    game.placeDisk(3);
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		
		catch (IllegalMoveException e) {
			assertNull(e);
		}

		try{
			Chip winner = game.getWinningPlayer();
			assertEquals(winner, futureWinner);
		}
		catch(GameStateException e) {
			assertNull(e);
		}
		
	}

	@Test public void testColumnWinner(){
		Game game = new ConnectFour();
		Chip futureWinner = Chip.EMPTY;
		try {
			futureWinner = game.getCurrentPlayer();
			game.placeDisk(0);
			game.placeDisk(1);
		    game.placeDisk(0);
		    game.placeDisk(2);
		    game.placeDisk(0);
		    game.placeDisk(3);
		    game.placeDisk(0);
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		catch (IllegalMoveException e) {
			assertNull(e);
		}

		try{
			Chip winner = game.getWinningPlayer();
			assertEquals(winner, futureWinner);
		}
		catch(GameStateException e) {
			assertNull(e);
		}
	}

	@Test public void testDiagonalWinner1(){
		Game game = new ConnectFour();
		Chip futureWinner = Chip.EMPTY;
		try {
			futureWinner = game.getCurrentPlayer();
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
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		
		catch (IllegalMoveException e) {
			assertNull(e);
		}

		try{
			Chip winner = game.getWinningPlayer();
			assertEquals(winner, futureWinner);
		}
		catch(GameStateException e) {
			assertNull(e);
		}
		
	}

	@Test public void testDiagonalWinner2(){
		Game game = new ConnectFour();
		Chip futureWinner = Chip.EMPTY;
		try {
			futureWinner = game.getCurrentPlayer();
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
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		
		catch (IllegalMoveException e) {
			assertNull(e);
		}

		try{
			Chip winner = game.getWinningPlayer();
			assertEquals(winner, futureWinner);
		}
		catch(GameStateException e) {
			assertNull(e);
		}
		
	}

	@Test 
	public void testTie(){
		Game game = new ConnectFour();
		Chip futureWinner = Chip.EMPTY;

		//create tie situation
		try {
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
		}
		catch (GameStateException e) {
			assertNull(e);
		}
		catch (IllegalMoveException e) {
			assertNull(e);
		}

		// attempt adding a piece to a full board
		try{
			game.placeDisk(0);
		}
		catch(GameStateException e) {
			assertNotNull(e);
		}
		catch (IllegalMoveException e) {
			assertNull(e);
		}

		// try calling getWinningPlayer
		try{
			game.getWinningPlayer();
		}
		catch(GameStateException e) {
			assertNotNull(e);
		}

		// see current player
		Chip player = game.getCurrentPlayer();
		assertEquals(player, Chip.EMPTY);
		
	}


   }
