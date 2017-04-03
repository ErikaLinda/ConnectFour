package test.unit.sh2946;

import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
/**
 * Created by SHattori on 3/2/16.
 */
public class ConnectFourGameTest {
    private Game game;

    @Before
    public void setup() {
	game = new ConnectFour();
    }
    
    @Test
    public void testDiagonalCheckWin() throws GameStateException,
                                              IllegalMoveException {
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(2);
        game.placeDisk(3);
        game.placeDisk(3);
        game.placeDisk(4);
        game.placeDisk(3);

        assertTrue(game.isGameOver());
    }
    @Test
    public void anothertestDiagonalCheckWin() throws GameStateException,
                                                     IllegalMoveException {
        game.placeDisk(3);
        game.placeDisk(2);
        game.placeDisk(2);
        game.placeDisk(1);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(1);
        game.placeDisk(0);
        game.placeDisk(0);
        game.placeDisk(4);
        game.placeDisk(0);

        assertTrue(game.isGameOver());
    }

}
