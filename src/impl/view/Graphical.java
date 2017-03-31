/***************
Erika Linda Zogla 
Software Engineering
Prof. Jerome White
March 31, 2017

ConnectFour game with GUI

Implementation of ConnectFour GUI.

*************/

package impl.view;

import api.Game;
import api.Chip;
import impl.game.ConnectFour;

import exc.GameStateException;
import exc.IllegalMoveException;

import java.util.Observer;
import java.util.Observable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.collections.ObservableList;
import javafx.application.Platform;

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
        BorderPane.setMargin(mainPane, new Insets(12,12,12,12));
        canvas.setCenter(mainPane);

        // Create the sidebar with game status notifications
    	this.createSidePane();
    	BorderPane.setMargin(sidePane, new Insets(12,12,12,12));
        canvas.setRight(sidePane);
    
        // Create a scene and place it in the stage
        Scene scene = new Scene(canvas);
        primaryStage.setTitle("Connect Four"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage   

    }

    // initialize game grid
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
    		}
    		column.setOnMouseClicked(colClickHandler);
    		mainPane.add(column, i, 0);
    	}
    }

    // initialize and format sidebar 
    private void createSidePane(){
    	plyrsTurnCirc.setStroke(Color.BLACK);
        Label playersTurn = new Label("Current Player");
        playersTurn.setFont(Font.font("Cambria", 24));
        GridPane.setHalignment(playersTurn, HPos.CENTER);
        GridPane.setHalignment(plyrsTurnCirc, HPos.CENTER);

        sidePane.add(playersTurn, 1, 1);
        sidePane.add(plyrsTurnCirc, 1, 2);
        sidePane.setVgap(10);
        addButtons();
		updateSidebarNotifications();
    }

    // places disk in both the Model and the View
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

    // updates current player
    private void updateSidebarNotifications() {
    	Chip currentPlayer = game.getCurrentPlayer();
 
		if(currentPlayer == Chip.BLUE){
			plyrsTurnCirc.setFill(Color.GREEN);
    	}else if(currentPlayer == Chip.RED){
    		plyrsTurnCirc.setFill(Color.ORANGE);
    	}
    }

    // displays game stats when the game is over
    private void gameOverGraphical(){
    	sidePane.getChildren().clear();

    	Label gameOverLbl = new Label("Game Over!");
		gameOverLbl.setFont(Font.font("Cambria", 28));
	   	GridPane.setHalignment(gameOverLbl, HPos.CENTER);
	   	sidePane.add(gameOverLbl, 1, 1);

    	try{
    		// display the winner
			Chip winningPlayer = game.getWinningPlayer();
			if(winningPlayer == Chip.BLUE){
				winningPlyrCirc.setFill(Color.GREEN);
			}else if(winningPlayer == Chip.RED){
				winningPlyrCirc.setFill(Color.ORANGE);
			}
			winningPlyrCirc.setStroke(Color.BLACK);
			GridPane.setHalignment(winningPlyrCirc, HPos.CENTER);
			sidePane.add(winningPlyrCirc, 1, 3);
		   	
		   	Label winningPlayerLabel = new Label("Winning Player");
		   	winningPlayerLabel.setFont(Font.font("Cambria", 20));
		   	GridPane.setHalignment(winningPlayerLabel, HPos.CENTER);
		   	sidePane.add(winningPlayerLabel, 1, 2);
		   
		}catch (GameStateException e) {
    		// display tie notification
    		Label tieLabel = new Label("It is a tie!");
    		tieLabel.setFont(Font.font("Cambria", 24));
    		GridPane.setHalignment(tieLabel, HPos.CENTER);
    		sidePane.add(tieLabel, 1, 2);
		}
		addButtons();

    }

    // adds "Play Again" and "Quit" buttons for convenience
    private void addButtons(){
    	// button to play again
		Button playAgainBtn = new Button("Play Again");
		playAgainBtn.setOnMouseClicked(playAgainHandler);
		GridPane.setMargin(playAgainBtn, new Insets(20, 0, 4, 0));
		playAgainBtn.setMinWidth(70);
		playAgainBtn.setMinHeight(30);
		GridPane.setHalignment(playAgainBtn, HPos.CENTER);
		sidePane.add(playAgainBtn, 1, 4);

		// button to exit the game
		Button quitBtn = new Button("Quit");
		quitBtn.setOnMouseClicked(quitHandler);
		quitBtn.setMinWidth(70);
		quitBtn.setMinHeight(30);
		GridPane.setHalignment(quitBtn, HPos.CENTER);
		sidePane.add(quitBtn, 1, 5);
    }

    /**************************
    Mouse click event handlers
    ***************************/

    private EventHandler<MouseEvent> colClickHandler = event ->{
		if(!game.isGameOver()){
			// determine which column was clicked
			VBox col = (VBox)event.getSource();
			GridPane pane = (GridPane)col.getParent();
			int clickedColumnNum = pane.getColumnIndex(col);
			
			// placeDisk and display the changes  on screen
			placeDiskGraphical(col, clickedColumnNum);
			if(game.isGameOver()) gameOverGraphical();
			else updateSidebarNotifications();
		}
    };

    private EventHandler<MouseEvent> playAgainHandler = event ->{
    	this.game = new ConnectFour();
    	sidePane.getChildren().clear();
    	mainPane.getChildren().clear();
    	createGrid();
		createSidePane();
    };

    private EventHandler<MouseEvent> quitHandler = event ->{
    	Platform.exit();
    };

    
	 /*
     * This is a convenience method for the update implementation
     * required by the Observer interface.
     */

    public void update(Observable o, Object args){
    	
     }
}