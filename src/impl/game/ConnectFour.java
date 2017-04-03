/***************
Erika Linda Zogla 
Software Engineering
Prof. Jerome White
March 31, 2017

ConnectFour game with GUI

Implementation of ConnectFour with Observer pattern and Junit testing.

*************/
package impl.game;

import api.Game;
import api.Chip;
import api.View;

import exc.GameStateException;
import exc.IllegalMoveException;


public class ConnectFour extends Game{
	private Chip [][] board;
	private boolean player1Turn = true;
	private static Chip player1 = Chip.RED;
	private static Chip player2 = Chip.BLUE;

	//initialize empty board
	public ConnectFour(){
		board = new Chip [6][7];
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				Chip elem = Chip.EMPTY;
				board[i][j] = elem;
			}
		}
	}

	public int getRows(){
		return board.length;
	}
    public int getColumns(){
    	return board[0].length;
    }
    public Chip[][] getBoard(){
    	return board;
    }

    /*
     * @throws GameStateException if the game is a tie
     * @throws IllegalMoveException if the move is out-of-bounds or is
     *         to a column that is not legal according to the rules of
     *         the concrete game
     */
    public void placeDisk(int row, int col) throws GameStateException, IllegalMoveException{
    	if(this.isTie()){
    		throw new GameStateException("It's a tie.");
    	}
    	if(this.invalidPlacement(row, col)){
    		throw new IllegalMoveException("Invalid placement.");
    	}

    	// find the first free space from the bottom of the board
    	row = 5;
    	while(board[row][col] != Chip.EMPTY){
    		row--;
    	}
    	board[row][col] = this.getCurrentPlayer();

    	// give turn to the other player if game is not over
    	if(!this.isGameOver()){
    		player1Turn = !player1Turn;
    	}

    	// notify observers
    	setChanged();
    	notifyObservers();
 
     }
    
    /*
     * @throws GameStateException if no winner has been established.
     */
    public Chip getWinningPlayer() throws GameStateException{
        // game is not yet over
    	if(!this.isGameOver()) {
    		throw new GameStateException("There is no winner.");
        // game is over but there is no winner
    	}else if(!this.isWinner((player1Turn) ? player1 : player2)){
            throw new GameStateException("There is no winner.");
        }
    	return this.getCurrentPlayer();
    }
    

    public Chip getCurrentPlayer(){
    	if(this.isTie()){
    		return Chip.EMPTY;
    	}
    	else {
    		return (player1Turn) ? player1 : player2;
    	}
    }

    public boolean isGameOver(){
    	return (this.isTie() || this.isWinner(this.getCurrentPlayer()));
    }


    /***************************************
    Private helper functions
    ****************************************/

    /*
     * check if placeDisk arguments are invalid
     */
    private boolean invalidPlacement(int row, int col){
    	if(col < 0 || col > 6 || row < 0 || row > 5) return true;

    	// check if the column has free space
    	for(int i = 0; i < 6; i++){
    		if(board[i][col] == Chip.EMPTY)
    			return false;
    	}
    	
    	// the placement is invalid
    	return true;
    }
    /*
    * checks if there is at least one EMPTY field on the board
    */
    private boolean isTie(){
    	for(Chip[] column : this.board){
    		for(Chip c : column){
    			if(c == Chip.EMPTY) return false;
    		}
    	}
        // check if the last piece did not create a winner
    	return !this.isWinner((player1Turn) ? player1 : player2);
    }

    /*
    * checks if there is a winner
    */
    private boolean isWinner(Chip c){
    	int row = this.getRows();
    	int col = this.getColumns();

    	for(int j = row-1; j > -1; j--){
    		for(int i = 0; i < col; i++){
    			if(c == board[j][i]){
    				if(winRow(c, j, i) || winCol(c, j, i)  
    					|| winDiag1(c, j, i)|| winDiag2(c, j, i)){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
	}

	/*
	* check row winner
	*/
	private boolean winRow(Chip chip, int row, int col){
    	int colNum = this.getColumns();
    	int counter = 0;

    	while(chip == board[row][col]){
    		counter++;
    		if(col == colNum-1) break;
    		col++;
    	}
    	return (counter > 3);
	}

	/*
	* check column winner
	*/
	private boolean winCol(Chip chip, int row, int col){
    	int counter = 0;

    	while(chip == board[row][col]){
    		counter++;
    		if(row == 0) break;
    		row--;
    	}
    	return (counter > 3);
	}
	/*
	* check winner in diagonal /
	*/
	private boolean winDiag1(Chip chip, int row, int col){
		int colNum = this.getColumns();
    	int counter = 0;

    	while(chip == board[row][col]){
    		counter++;
    		if(row == 0 || col == colNum-1) break;
    		row--;
    		col++;
    	}
    	return (counter > 3);
	}

	/*
	* check winner in diagonal \
	*/
	private boolean winDiag2(Chip chip, int row, int col){
    	int counter = 0;

    	while(chip == board[row][col]){
    		counter++;
    		if(row == 0 || col == 0) break;
    		row--;
    		col--;
    	}
    	return (counter > 3);
    }
}