/*AI Game
* @author       Sanaa Kapur
* @version      May 28 2021
 * Description:       
* Pits 2 AI Players against each other. 
* */
public class AIGame {
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	try {
	    Checkers c = new Checkers();
	    Player player1 = new AIPlayer(c, 1, Checker.lightChecker);
	    Player player2 = new AIPlayer(c, 2 , Checker.darkChecker);

	    if(player1 != null && player2 != null) {
		while(!c.isGameOver(c.getBoard()) && player1.playTurn() && player2.playTurn()){
		    Thread.sleep(1000);
		}

	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println("Game Over!");
    }
    
}


