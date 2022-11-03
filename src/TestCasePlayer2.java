import java.util.ArrayList;
import java.awt.Color;
/*TestCasePlayer2
* @author       Sanaa Kapur
* @version      May 28 2021
 * Description:       
* Dark color movement of automated moving to see kingChecker sequence
* */
public class TestCasePlayer2 extends Player{
    int numMoves = 11;
    private  ArrayList<Integer> a = new ArrayList<Integer>();

	public TestCasePlayer2(Checkers ch) throws Exception {
		super(ch, Checker.lightChecker);
		configureList(a);
		// TODO Auto-generated constructor stub
	}
	public boolean playTurn() {
		return super.playTurn();			
	}
	//gets user input for starting/ending row and column and prevents letters from terminating the game

		public Move think(Board b, Color c) {
		    
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
		
		}
		
	public void configureList(ArrayList<Integer> l) {
		
		l.add(5);//1
		l.add(5);
		l.add(4);
		l.add(4);
		
		l.add(2);//3
		l.add(2);
		l.add(3);
		l.add(3);
		
		l.add(4);//5
		l.add(4);
		l.add(2);
		l.add(2);
		
		l.add(0);//7
		l.add(0);
		l.add(1);
		l.add(1);
		
		l.add(2);//9
		l.add(4);
		l.add(3);
		l.add(5);
		
		l.add(1);//11
		l.add(3);
		l.add(2);
		l.add(4);
		
		
		
		
		
	}
}
