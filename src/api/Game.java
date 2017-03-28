package api;

import exc.GameStateException;
import exc.IllegalMoveException;

import java.util.Observable;

public abstract class Game extends Observable {
    public abstract int getRows();
    public abstract int getColumns();
    public abstract Chip[][] getBoard();

    /*
     * @throws GameStateException if the game is a tie
     * @throws IllegalMoveException if the move is out-of-bounds or is
     *         to a column that is not legal according to the rules of
     *         the concrete game
     */
    public abstract void placeDisk(int row, int col)
        throws GameStateException,
               IllegalMoveException;
    
    public final void placeDisk(int col) throws GameStateException,
						IllegalMoveException {
	placeDisk(0, col);
    }
    
    /*
     * @throws GameStateException if no winner has been established.
     */
    public abstract Chip getWinningPlayer() throws GameStateException;
    
    public abstract Chip getCurrentPlayer();
    public abstract boolean isGameOver();
}
