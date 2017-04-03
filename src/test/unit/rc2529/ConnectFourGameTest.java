package test.unit.rc2529;

import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ConnectFourGameTest {
    private Game game;

    @Before
    public void setup() {
	game = new ConnectFour();
    }
    
    @Test
    public void testDiagonalWinnerFound() throws GameStateException,
                                                 IllegalMoveException {
        for (int i = 0; i < 4; i++) {
            for(int col = 0; col < game.getColumns() - 1 ; col += 2) {
                int offset = i % 2;
		try {
		    game.placeDisk(col + offset);
		    game.placeDisk(col + 1 + offset);
		}
		catch (GameStateException e) {
		    break;
		}
	    }
        }

        assertTrue(game.isGameOver());
    }

    @Test (expected = GameStateException.class)
    public void testTieWhenFull() throws GameStateException,
					 IllegalMoveException {
        for (int i = 0; i < 2; i++) {
            for(int col = 0; col < game.getColumns() - 1 ; col += 2){
                game.placeDisk(col );
                game.placeDisk(col + 1 );
                game.placeDisk(col );
                game.placeDisk(col + 1 );
                game.placeDisk(col );
                game.placeDisk(col + 1 );

            }
            game.placeDisk(game.getColumns() - 1);
        }

        for (int i=0; i < 4;i++) {
            game.placeDisk(game.getColumns() - 1);
        }

	game.placeDisk(0);
    }
}
