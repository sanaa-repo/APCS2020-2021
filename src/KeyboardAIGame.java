/*KeyboardAI Game
* @author       Sanaa Kapur
* @version      May 28 2021
 * Description:       
* Pits a human player(dark checker in the keyboard) against depth 2 AI 
* */

public class KeyboardAIGame {
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	try {
	    Checkers c = new Checkers();
	    
	    Player player1 = new AIPlayer(c, 2, Checker.lightChecker);
	    Player player2 = new KeyboardPlayer(c, Checker.darkChecker);

	    if(player1 != null && player2 != null) {
		while(!c.isGameOver(c.getBoard()) && player2.playTurn() && player1.playTurn()){
		    //Thread.sleep(1000);
		}

	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
