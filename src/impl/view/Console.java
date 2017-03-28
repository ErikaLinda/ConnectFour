package impl.view;

import api.Game;
import api.Chip;
import api.View;

import exc.GameStateException;
import exc.IllegalMoveException;

import java.util.Observer;
import java.util.Observable;

public class Console extends View{
	Observable observable;

	// constructor
	public Console(Observable o){
		this.observable = o;
		observable.addObserver(this);
	}
	 /*
     * This is a convenience method for the update implementation
     * required by the Observer interface.
     */

    public void update(Observable o, Object args){
    	if (o instanceof Game){
    		Game game = (Game) o;
    		this.render(game);
    	}

     }

    public void render(Game game){
    	Chip [][] board = game.getBoard();

    	for(Chip[] row : board){
    		System.out.print("|");
    		for(Chip c : row){
    			if(c == Chip.RED){
    				System.out.print(" X |");
    			}else if(c == Chip.BLUE){
    				System.out.print(" O |");
    			}else{
    				System.out.print("   |");
    			}
    		}
    		System.out.println("");
    		System.out.println("|___|___|___|___|___|___|___|");
    	}
    }

}