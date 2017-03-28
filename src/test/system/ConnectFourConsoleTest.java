package test.system;

import api.View;
import api.Game;
import api.Chip;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.view.Console;
import impl.game.ConnectFour;

import java.util.Scanner;


public class ConnectFourConsoleTest {
    public static void main(String[] args) {

    	// initialize new game
		Game game = new ConnectFour();
		View view = new Console(game);
		System.out.println("Play ConnectFour");
		view.update(game);

		// prompt palyers for input and play
		int plyr, col;
		while(!game.isGameOver()){
			plyr = (game.getCurrentPlayer() == Chip.RED) ? 1 : 2;
			System.out.printf("Player %d make your move: %n", plyr);
			Scanner sc = new Scanner(System.in);
			try{
			    col = sc.nextInt();
			}catch(Exception e){
			    System.out.print("Enter a number. ");
			    continue;
			}
			try{
				game.placeDisk(col);
			}
			catch (GameStateException e) {
	    		System.out.println(e);
			}
			catch (IllegalMoveException e) {
		    	System.out.println(e);
			}
		}

		// determine game's outcome
		try{
			Chip winner = game.getWinningPlayer();
			if(winner == Chip.RED){
				System.out.println("Congratulations! Player 1 (X) won!");
			}
			else{
				System.out.println("Congratulations! Player 2 (O) won!");
			}	
		}
		catch (GameStateException e) {
		    	System.out.println("It is a tie!");
		}
		
		
    }
}
