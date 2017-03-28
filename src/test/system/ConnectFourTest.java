package test.system;

import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.view.Graphical;
import impl.game.ConnectFour;

import java.util.Observer;
import java.util.Observable;

import javafx.application.Application;
import javafx.stage.Stage;

public class ConnectFourTest {
    public static void main(String[] args) {
        // initialize new game
		Game game = new ConnectFour();
		Graphical app = new Graphical(game);
		Application.launch(Graphical.class);
		app.update(game);
		try{
				game.placeDisk(1);
			}
			catch (GameStateException e) {
	    		System.out.println(e);
			}
			catch (IllegalMoveException e) {
		    	System.out.println(e);
			}
		
    }
}
