import java.awt.Color;
import java.util.Scanner;

/*KeyboardPlayer
* @author       Sanaa Kapur
* @version      May 28 2021
* Description:       
* Lets the user input where they want their team of checkers to go via keyboard input. 
* Inherits from player.
* */

public class KeyboardPlayer extends Player{
	Scanner scan = new Scanner(System.in);

	public KeyboardPlayer(Checkers ch, Color col) throws Exception {
		super(ch, col);
		// TODO Auto-generated constructor stub
	}

	public Move think(Board b, Color col) {
		try {
		    
			System.out.println("Enter the (int) row of the checker you want to move");
			int userRow = scan.nextInt();
			System.out.println("Enter the (int) column of the checker you want to move");
			int userCol =scan.nextInt();			
			System.out.println("Enter the (int) row of the square you want to move to");
			int newRow = scan.nextInt();
			System.out.println("Enter the (int) column of the square you want to move to");
			int newCol = scan.nextInt();
			
			if(getUserRow() == -1 && getUserCol() == -1) {
				System.out.println("Thanks for playing. Bye!");
				return null;
			}
		
			Move m = new Move(userRow, userCol, newRow, newCol);
			return m;
		}
		catch(Exception e){
			System.out.println("You messed around with the program by entering a letter, so it will end. Try again next time");
			return null;
		}
		

	}

}
