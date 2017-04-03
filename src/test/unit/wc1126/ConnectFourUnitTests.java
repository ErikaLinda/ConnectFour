package test.unit.wc1126;

import api.Chip;
import api.Game;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.game.ConnectFour;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;

public class ConnectFourUnitTests {
    private Game game;

    @Before
    public void setup() {
        game = new ConnectFour();
    }
    
    @Test
    public void testGetRow() {
        assertEquals(game.getRows(), 6);
    }

    @Test
    public void testGetColumns() {
        assertEquals(game.getColumns(), 7);
    }

    @Test
    public void testGetBoard() {
        assertNotNull(game.getBoard());
    }

    @Test(expected = IllegalMoveException.class)
    public void testPlaceDisk() throws GameStateException,
                                       IllegalMoveException {
        game.placeDisk(-1, 5);
    }

    @Test(expected = GameStateException.class)
    public void testGetWinningPlayer() throws GameStateException {
        game.getWinningPlayer();
    }

    @Test
    public void testGetCurrentPlayer() {
        assertThat(game.getCurrentPlayer(), instanceOf(Chip.class));
    }

    @Test
    public void testIsGameOver() {
        Game game = new ConnectFour();
        assertFalse(game.isGameOver());
    }
}
