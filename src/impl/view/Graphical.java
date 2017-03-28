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
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;

public class Graphical extends Application implements Observer {
	private Observable observable;
	private Scene scene;


	public Graphical(){
	}

	// constructor
	public Graphical(Observable o){
		this.observable = o;
		observable.addObserver(this);
	}

	@Override
	public void start(Stage primaryStage) {
		// Pane to hold the game's grid
        GridPane pane = new GridPane(); 
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                pane.add(new Rectangle(40,40), j, i);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);

        // Create the sidebar with game status notifications
        GridPane sidePane = new GridPane();
        Label playersTurn = new Label("Current Player");
    	Label winningPlayer = new Label("Winning Player");
    	Circle plyrsTurnCirc = new Circle(20);
    	Circle winningPlyrCirc = new Circle(20);
        sidePane.add(playersTurn, 1, 1);
        sidePane.add(plyrsTurnCirc, 2, 1);
        sidePane.add(winningPlayer, 1, 2);
        sidePane.add(winningPlyrCirc, 2, 2);
        borderPane.setRight(sidePane);

    
        // Create a scene and place it in the stage
        scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Connect Four"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage   

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
    		this.render(game);
    	}
     }

     public void render(Game game){
     	Chip[][] board = game.getBoard();

     	// Pane to hold the game's grid
        GridPane pane = new GridPane(); 
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                pane.add(new Circle(20), j, i);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);

        // Create the sidebar with game status notifications
        GridPane sidePane = new GridPane();
        Label playersTurn = new Label("Current Player");
    	Label winningPlayer = new Label("Winning Player");
    	Circle plyrsTurnCirc = new Circle(20);
    	Circle winningPlyrCirc = new Circle(20);
        sidePane.add(playersTurn, 1, 1);
        sidePane.add(plyrsTurnCirc, 2, 1);
        sidePane.add(winningPlayer, 1, 2);
        sidePane.add(winningPlyrCirc, 2, 2);
        borderPane.setRight(sidePane);

        // Stage primaryStage = new Stage();
    
        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Connect Four"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage   
     	
     	// GridPane root = new GridPane();
     	// int col = game.getColumns();

     	// Chip [][] board = game.getBoard();
    	// Circle circ = new Circle(20);
    	// GridPane.setConstraints(circ, 1, 1);
    	// root.getChildren().add(circ);
    
		

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

    // 	Scene scene = new Scene(root, 400, 300);

    //     stage.setTitle("ConnectFour");
    //     stage.setScene(scene);
    //     stage.show();
    // }

    // private GridPane addChildren(GridPane root, Game game){
    // 	Chip [][] board = game.getBoard();
    // 	 Circle circ = new Circle(20);
    // 	GridPane.setConstraints(circ, 1, 1);
    // 	root.getChildren().add(circ);
    // 	return root;

    }
}
