package test.unit.av1509;

import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConnectFourGameTest {
    private Game game;
    
    // These unit tests check diagonals
    
    Integer[] sequence = new Integer[] {0,1,1,2,2,3,3,3,3,1,2};
    ArrayList<Integer> sequenceArray =
        new ArrayList<Integer>(Arrays.asList(sequence));

    @Before
    public void setup() {
	game = new ConnectFour();
    }
    
    @Test
    public void testDiagonalUpWinnerFound() throws GameStateException,
                                                   IllegalMoveException {
        for (int i = 0; i < 11; i ++){
            game.placeDisk(sequenceArray.get(i));
        }
        assertTrue(game.isGameOver());
    }
    
    @Test
    public void testDiagonalDownWinnerFound() throws GameStateException,
                                                     IllegalMoveException {
    	Integer[] sequence = new Integer[] {0,1,1,2,2,3,3,3,3,1,2};
    	ArrayList<Integer> sequenceArray =
            new ArrayList<Integer>(Arrays.asList(sequence));
        for (int i = 0; i < 11; i ++){
            game.placeDisk(game.getColumns()-1-sequenceArray.get(i));
        }
        assertTrue(game.isGameOver());
    }
}
