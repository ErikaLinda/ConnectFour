package test.system;

import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.view.Graphical;
import impl.game.ConnectFour;

import javafx.application.Application;
import javafx.stage.Stage;

public class ConnectFourTest {
    public static void main(String[] args) {
        // initialize new game
		Game game = new ConnectFour();
		Application app = new Graphical(game);
		// System.out.println("Play ConnectFour");
		Application.launch(Graphical.class);
		
    }
}
