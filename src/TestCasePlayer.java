import java.util.ArrayList;
import java.awt.Color;
import java.util.Scanner;
/*TestCasePlayer
* @author       Sanaa Kapur
* @version      May 28 2021
 * Description:       
* Light color movement of automated moving to see kingChecker sequence
* */
public class TestCasePlayer extends Player{
    private  ArrayList<Integer> a = new ArrayList<Integer>();
    int numMoves = 11;

    public TestCasePlayer(Checkers ch) throws Exception {
	super(ch, Checker.lightChecker);
	configureList(a);
	// TODO Auto-generated constructor stub
    }
    public boolean playTurn() {
	return super.playTurn();			
    }
    //gets user input for starting/ending row and column and prevents letters from terminating the game

    public Move think(Board b, Color c) {
	//try {
	    System.out.println("nums size: " + a.size());

	    if(a.size() == 0) {
		return null;
	    }

	    int ur = a.get(0);
	    a.remove(0);
	    int uc = a.get(0);
	    a.remove(0);
	    int nr = a.get(0);
	    a.remove(0);
	    int nc = a.get(0);
	    a.remove(0);

	    Move tempMove = new Move(ur, uc, nr, nc);
	    System.out.println("testcaseplayer: move:" + tempMove.toString());
	    return tempMove;

	    /*
				System.out.println("Enter the (int) row of the checker you want to move");
				setUserRow(a.get(0));
				a.remove(0);
				System.out.println("Enter the (int) column of the checker you want to move");
				setUserCol(a.get(0));
				a.remove(0);
				System.out.println("Enter the (int) row of the square you want to move to");
				setNewRow(a.get(0));
				a.remove(0);
				System.out.println("Enter the (int) column of the square you want to move to");
				setNewCol(a.get(0));
				a.remove(0);
	  
	    System.out.println("nums size: " + a.size());

	}
	catch(Exception e){
	    System.out.println("You messed around with the program by entering a letter, so it will end. Try again next time");
	    return null;
	}
	if(getUserRow() == -1 && getUserCol() == -1) {
	    System.out.println("Thanks for playing. Bye!");
	    return null;
	}
	   */


    }

    public void configureList(ArrayList<Integer> l) {
	l.add(2); //0
	l.add(2);
	l.add(3);
	l.add(1);

	l.add(1);//2
	l.add(1);
	l.add(2);
	l.add(2);

	l.add(0);//4
	l.add(0);
	l.add(1);
	l.add(1);

	l.add(2);//6
	l.add(2);
	l.add(0);
	l.add(0);

	l.add(1);//8
	l.add(1);
	l.add(2);
	l.add(2);

	l.add(3);//10
	l.add(5);
	l.add(4);
	l.add(6);






    }

}
