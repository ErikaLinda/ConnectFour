// package impl.controller;

// import api.Game;
// import api.Chip;
// import impl.view.Graphical;
// import impl.game.ConnectFour;

// import javafx.application.Application;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.Paint;
// import javafx.scene.shape.Circle;
// // import javafx.beans.property.PaintProperty;

// public class Controller{
// 	private Game game;
// 	private Chip[][] board;
// 	public Circle[][] colorBoard;
// 	private int rowNum;
// 	private int colNum;

// 	public Controller(){
// 		game = new ConnectFour();
// 		board = game.getBoard();
// 		rowNum = getRows();
// 		colNum = getColumns();
// 		colorBoard = new Circle[rowNum][colNum];
// 		setColors();
// 	}

// 	public void setColors(){
// 		Circle circ;
// 		for (int i = 0; i < rowNum; i++)
//             for (int j = 0; j < colNum; j++){
//             	circ = new Circle();
//             	if(board[i][j] == Chip.EMPTY){
//             		circ.setFill( Color.GREEN);
//             	}
//             	else if (board[i][j] == Chip.BLUE){
//             		circ.setFill(Color.BLUE);
//             	}
//             	else{
//             		circ.setFill(Color.RED);
//             	} 
//             	colorBoard[i][j] = circ; 	
//             }
// 	}
// 	public void setColors2(){
// 		Circle circ;
// 		for (int i = 0; i < rowNum; i++)
//             for (int j = 0; j < colNum; j++){
//             	circ = new Circle();
//             	if(board[i][j] == Chip.EMPTY){
//             		circ.setFill( Color.YELLOW);
//             	}
//             	else if (board[i][j] == Chip.BLUE){
//             		circ.setFill(Color.BLUE);
//             	}
//             	else{
//             		circ.setFill(Color.RED);
//             	} 
//             	colorBoard[i][j] = circ; 	
//             }
// 	}

// 	public int getColumns(){
// 		return game.getColumns();
// 	}

// 	public int getRows(){
// 		return game.getRows();
// 	}


// }