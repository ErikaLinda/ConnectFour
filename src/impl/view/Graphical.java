package impl.view;

import api.Game;
import api.Chip;
// import impl.controller.Controller;
import impl.game.ConnectFour;

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
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import  javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.List;
import javafx.beans.InvalidationListener;

public class Graphical extends Application implements Observer {
	private Game game = new ConnectFour();
	private int rowNum = game.getRows();
	private int colNum = game.getColumns();
	private GridPane mainPane = new GridPane();
	private GridPane sidePane = new GridPane();
	private Circle plyrsTurnCirc = new Circle(20);
    private Circle winningPlyrCirc = new Circle(20);
    


	@Override
	public void start(Stage primaryStage) {
		// Pane to hold the game's structure  
		BorderPane canvas = new BorderPane();
		
 		// Create main game field
        this.createGrid();
        canvas.setCenter(mainPane);

        // Create the sidebar with game status notifications
    	this.createSidePane();
        canvas.setRight(sidePane);

    
        // Create a scene and place it in the stage
        Scene scene = new Scene(canvas);
        primaryStage.setTitle("Connect Four"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage   

    }

    private void createGrid(){
    	Rectangle rect;
    	VBox column;
    	for(int i = 0; i < colNum; i++){
    		column = new VBox();
    		for(int j = 0; j < rowNum; j++){
    			rect = new Rectangle(40, 40);
    			rect.setFill(Color.WHITE);
    			rect.setStroke(Color.BLACK);
    			column.getChildren().add(rect);
    			column.setOnMouseClicked(colClickHandler);
    		}
    		mainPane.add(column, i, 0);
    	}
    }

    private void createSidePane(){
    	plyrsTurnCirc.setStroke(Color.BLACK);
    	winningPlyrCirc.setStroke(Color.BLACK);
    	winningPlyrCirc.setFill(Color.WHITE);
        Label playersTurn = new Label("Current Player", plyrsTurnCirc);
    	Label winningPlayer = new Label("Winning Player", winningPlyrCirc);
        sidePane.add(playersTurn, 1, 1);
        sidePane.add(winningPlayer, 1, 2);
		updateSideBarNotifications();
    }

    private void placeDiskGraphical(VBox col, int colNum){
    	try{
				game.placeDisk(colNum);
			}
			catch (GameStateException e) {
	    		System.out.println(e);
			}
			catch (IllegalMoveException e) {
		    	System.out.println(e);
			}
		Chip[][] board = game.getBoard();
		ObservableList<Node> currentColumn = col.getChildren();
		// System.out.println(currentColumn);
		
		for(int i = 0; i < rowNum; i++){
			Rectangle currentRect;
			if(board[i][colNum] == Chip.RED){
				currentRect = (Rectangle)currentColumn.get(i);
				currentRect.setFill(Color.ORANGE);
			}else if(board[i][colNum] == Chip.BLUE){
				currentRect = (Rectangle)currentColumn.get(i);
				currentRect.setFill(Color.GREEN);
			}
		}
    }

    private void updateSideBarNotifications() {
    	Chip currentPlayer = game.getCurrentPlayer();
    	Chip winningPlayer = Chip.EMPTY;
    	
    	// while game continues
    	if(!game.isGameOver()){
    		if(currentPlayer == Chip.BLUE){
    			plyrsTurnCirc.setFill(Color.GREEN);
	    	}else if(currentPlayer == Chip.RED){
	    		plyrsTurnCirc.setFill(Color.ORANGE);
	    	}
	    // when game is over
    	}else{
    		try{
				winningPlayer = game.getWinningPlayer();
			}catch (GameStateException e) {
	    		System.out.println(e);
			}
    		if(winningPlayer == Chip.BLUE){
    			winningPlyrCirc.setFill(Color.GREEN);
	    	}else if(winningPlayer == Chip.RED){
	    		winningPlyrCirc.setFill(Color.ORANGE);
	    	}
	    	plyrsTurnCirc.setFill(Color.WHITE);
    	}
    }

    private EventHandler<MouseEvent> colClickHandler = event ->{
    	// determine which column was clicked
		VBox col = (VBox)event.getSource();
		GridPane pane = (GridPane)col.getParent();
		int clickedColumnNum = pane.getColumnIndex(col);
		// System.out.println(clickedColumn);
		
		// placeDisk and display on screen the changes
		placeDiskGraphical(col, clickedColumnNum);
		updateSideBarNotifications();
    };

    
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
     // 	Chip[][] board = game.getBoard();

     // 	// Pane to hold the game's grid
     //    GridPane pane = new GridPane(); 
     //    for (int i = 0; i < 6; i++)
     //        for (int j = 0; j < 7; j++)
     //            pane.add(new Circle(20), j, i);

     //    BorderPane borderPane = new BorderPane();
     //    borderPane.setCenter(pane);

     //    // Create the sidebar with game status notifications
     //    GridPane sidePane = new GridPane();
     //    Label playersTurn = new Label("Current Player");
    	// Label winningPlayer = new Label("Winning Player");
    	// Circle plyrsTurnCirc = new Circle(20);
    	// Circle winningPlyrCirc = new Circle(20);
     //    sidePane.add(playersTurn, 1, 1);
     //    sidePane.add(plyrsTurnCirc, 2, 1);
     //    sidePane.add(winningPlayer, 1, 2);
     //    sidePane.add(winningPlyrCirc, 2, 2);
     //    borderPane.setRight(sidePane);

     //    // Stage primaryStage = new Stage();
    
     //    // Create a scene and place it in the stage
     //    Scene scene = new Scene(borderPane, 450, 300);
     //    primaryStage.setTitle("Connect Four"); // Set the stage title
     //    primaryStage.setScene(scene); // Place the scene in the stage
     //    primaryStage.show(); // Display the stage   
     	
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
