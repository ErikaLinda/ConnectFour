package test.unit.njw275;

import api.View;
import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.view.Console;
import impl.game.ConnectFour;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class ConnectFourGameTest {
    @Test
    public void connectFourDiangonalLeftWinTest() throws GameStateException,
                                                         IllegalMoveException {
        Game game = new ConnectFour();
        int order[] = {0,1,1,2,2,3,2,3,3,5,3};
        for (int i=0;i<order.length;i++){
            game.placeDisk(order[i]);
        }
        assertTrue(game.isGameOver());
    }

    @Test
    public void connectFourDiangonalRightWinTest()
        throws GameStateException,
               IllegalMoveException {
        Game game = new ConnectFour();
        int order[] = {0,0,0,0,
                       1,1,2,1,
                       4,2,4,3};
        for (int i=0;i<order.length;i++){
            game.placeDisk(order[i]);
        }
        assertTrue(game.isGameOver());
    }

    @Test
    public void connectFourHorizontalWinTest() throws GameStateException,
                                                      IllegalMoveException {
        Game game = new ConnectFour();
        int order[] = {0,0,1,1,2,2,3};
        for (int i=0;i<order.length;i++){
            game.placeDisk(order[i]);
        }
        assertTrue(game.isGameOver());
    }

    public Game makeTieGame() throws GameStateException,
                                     IllegalMoveException {
        Game game = new ConnectFour();
        int order[] = {0,0,0,0,0,0,
                       1,1,1,1,1,1,
                       2,2,2,2,2,2,
                       4,4,4,4,4,4,
                       5,3,3,3,3,3,
                       3,5,5,5,5,5,
                       6,6,6,6,6,6
        };
        
        for (int i=0;i<order.length;i++) {
            game.placeDisk(order[i]);
        }

        return game;
    }
    
    @Test
    public void connectFourTieTestOver() throws GameStateException,
                                                IllegalMoveException {
        Game game = makeTieGame();
        assertTrue(game.isGameOver());
    }

    @Test(expected = GameStateException.class)
    public void connectFourTieTestNoWinner() throws GameStateException,
                                                    IllegalMoveException {
        Game game = makeTieGame();
        game.getWinningPlayer();
    }

    @Test
    public void connectFourVerticalWinTest() throws GameStateException,
                                                    IllegalMoveException {
        Game game = new ConnectFour();
        int order[] = {0,1,0,1,0,1,0};
        for (int i=0;i<order.length;i++){
            game.placeDisk(order[i]);
        }
        assertTrue(game.isGameOver());
    }

    @Test
    public void emptyBoardTest() {
        Game game = new ConnectFour();
        for (int i=0;i<game.getRows();i++){
            for (int j=0;j<game.getColumns();j++){
                Chip chip = game.getBoard()[i][j];
                assertEquals(chip, Chip.EMPTY);
            }
        }
    }

    @Test(expected = IllegalMoveException.class)
    public void outOfBoundsRow() throws GameStateException,
                                        IllegalMoveException {
        Game game = new ConnectFour();
        game.placeDisk(7, 2);		
    }

    @Test(expected = IllegalMoveException.class)
    public void outofBoundsLeftTest() throws GameStateException,
                                             IllegalMoveException {
        Game game = new ConnectFour();
        game.placeDisk(-1);			
    }

    @Test(expected = IllegalMoveException.class)
    public void outofBoundsRightTest() throws GameStateException,
                                              IllegalMoveException {
        Game game = new ConnectFour();
        game.placeDisk(7);			
    }

    @Test
    public void alternativePlayerStartTest() throws GameStateException,
                                                    IllegalMoveException {
        Game game = new ConnectFour();
        Chip chip = game.getCurrentPlayer();
        game.placeDisk(game.getRows() - 1);
        assertNotEquals(chip, game.getCurrentPlayer());
    }

    /*
     * Specification does not mention player ordering
     */

    @Ignore
    public void playerStartTest() {
        Game game = new ConnectFour();
        assertEquals(game.getCurrentPlayer(), Chip.RED);
    }

    @Ignore
    public void getSecondPlayerTest() throws GameStateException,
                                             IllegalMoveException {
        Game game = new ConnectFour();
        game.placeDisk(0);
        assertEquals(game.getCurrentPlayer(), Chip.BLUE);
    }
}
