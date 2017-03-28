package impl.view;

import api.Game;
import api.Chip;

import exc.GameStateException;
import exc.IllegalMoveException;

import java.util.Observer;
import java.util.Observable;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Graphical extends Application implements Observer {
	private Observable observable;

	public Graphical(){
	}

	// constructor
	public Graphical(Observable o){
		this.observable = o;
		observable.addObserver(this);
	}

	public void start(Stage stage) {
		Game game = (Game) observable;
		this.render(game, stage);

    }

    
	 /*
     * This is a convenience method for the update implementation
     * required by the Observer interface.
     */

    public void update(Observable o){
    	this.update(o, null);
    }

    public void update(Observable o, Object args){
    	if (o instanceof Game){
    		Game game = (Game) o;
    		Stage stage = new Stage();
    		this.render(game, stage);
    	}
     }

     public void render(Game game, Stage stage){
     	
     	GridPane root = new GridPane();
     	int col = game.getColumns();

     	// Chip [][] board = game.getBoard();
    	 Circle circ = new Circle(20);
    	GridPane.setConstraints(circ, 1, 1);
    	root.getChildren().add(circ);
    
		

		// Circle circ = new Circle(20);
		// Circle circ1 = new Circle(40, 40, 30);
		// Circle circ2 = new Circle(40, 40, 30);
		// GridPane.setConstraints(circ, 3, 1);
		// GridPane.setConstraints(circ1, 2, 1);
		// GridPane.setConstraints(circ2, 1, 1);

	 //    root.getColumnConstraints().add(column1, column2);
	    
		
       
     	// root = this.addChildren(root, game);
    	

    	// for(int i = 0; i < game.getRows(); i++){
    	// 	for(int j = 0; j < game.getColumns(); j++){
    	// 		Chip chip = board[i][j];
    	// 		Circle circ = new Circle(20);
    	// 		GridPane.setConstraints(circ, j, i);
    	// 		if(chip == Chip.RED){
    	// 			circ.setFill(Color.ORCHID);
    	// 		}else if(chip == Chip.BLUE){
    	// 			circ.setFill(Color.DARKGREEN);
    	// 		}else{
    	// 			circ.setFill(Color.WHITE);
    	// 		}
    	// 		root.getChildren().add(circ);
    	// 	}
    	// }

    	Scene scene = new Scene(root, 400, 300);

        stage.setTitle("ConnectFour");
        stage.setScene(scene);
        stage.show();
    }

    private GridPane addChildren(GridPane root, Game game){
    	Chip [][] board = game.getBoard();
    	 Circle circ = new Circle(20);
    	GridPane.setConstraints(circ, 1, 1);
    	root.getChildren().add(circ);
    	return root;

    }
}
