import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/*AI Player
 * @author       Sanaa Kapur & Han Li(whose online github code helped me structure my class)
 * @version      May 28 2021
 * Description:       
 * AI player does the AI's 'thinking'. Inherits from player with default constructor. 
 * Overridden think algorithm gets all possible moves and calls minMax on it.
 * Minmax method included, as well as evaluate. 
 * */

public class AIPlayer extends Player{
    private Checkers c = getCheckers();
    private int depth = 0;

    public AIPlayer(Checkers ch, int d, Color col) throws Exception {
	super(ch, col);
	depth = d;
	// TODO Auto-generated constructor stub
    }

    //Given a board, a color of a piece, and a depth, return the move that will score the most points 
    public Move think(Board board, Color team) {	 
	Color flipTeam = Checker.darkChecker;
	if(team.equals(Checker.darkChecker)) { flipTeam = Checker.lightChecker;}
	Board temp = board.clone(); 
	ArrayList<Move> possibleMoves = c.getAllMoves(team, temp);
	//System.out.println("AI Player: possible moves:"+possibleMoves);
	ArrayList<Double> evaluations = new ArrayList<Double>();
	if(possibleMoves.size() == 0) {return null;}
	//System.out.println("AI Player; evaluations: " + evaluations);
	for(int i= 0; i< possibleMoves.size(); i++) {
	    temp = board.clone();
	    c.makeMove(possibleMoves.get(i), temp);
	    evaluations.add(minMax(depth - 1, flipTeam, temp));	
	}
	double maxEval = -100.0;
	Random rand = new Random();
	//finding the largest evaluation
	for(int i= evaluations.size()-1; i>=0; i--) {
	    if(evaluations.get(i)>= maxEval) {
		maxEval = evaluations.get(i);
	    }
	}
	//trying to remove all of the non-largest moves/evaluations
	for(int i = 0; i< evaluations.size(); i++) {
	    if(evaluations.get(i)< maxEval) {
		evaluations.remove(i);
		possibleMoves.remove(i);
		i--;
	    }
	}
	if(evaluations.size()==0) {
	    return null;
	}
	return possibleMoves.get(rand.nextInt(evaluations.size()));
    }
    
    //given a colour and board, returns numerical value of the board given which color is maximizing
    public double evaluate(Color col, Board b) {
	Color maxColor = Checker.lightChecker;
	if(maxColor.equals(col) && b.getDarkCheckersLeftOnBoard() == 1) {
	    return 100;
	}
	if(!maxColor.equals(col) && b.getLightCheckersLeftOnBoard() == 1) {
	    return 100;
	}
	if(maxColor.equals(col))
	    return (b.getLightCheckersLeftOnBoard() - b.getDarkCheckersLeftOnBoard());
	return(b.getDarkCheckersLeftOnBoard() - b.getLightCheckersLeftOnBoard());
    }

    //given depth, maximizing team colour, and board return best move
    public double minMax(int depth, Color maxPlayer, Board checkerboard) {
	//Given color of team and a board, return list of all valid moves
	ArrayList<Move> moves = c.getAllMoves(maxPlayer, checkerboard);
	//given a move and a board, return if game ended
	if(depth == 0|| c.isGameOver(checkerboard)) {
	    //given a color and board, return point eval for board
	    return evaluate(maxPlayer, checkerboard);
	}

	if(maxPlayer== Checker.lightChecker) {
	    double maxEvalSoFar = -100.0;
	    for(Move move: moves) {
		//given current board, return a copy 
		Board boardCopy = checkerboard.clone();
		c.makeMove(move, boardCopy);
		//switch POV for next recursive call, as it will be the other team's turn
		double points = minMax(depth - 1, Checker.darkChecker, boardCopy);
		maxEvalSoFar = Math.max(points, maxEvalSoFar);
	    }
	    return maxEvalSoFar;
	}
	else {
	    double maxEvalSoFar = 100.0;
	    for(Move move: moves) {
		//given current board, return a copy 
		Board boardCopy = checkerboard.clone();
		c.makeMove(move, boardCopy);
		//switch POV for next recursive call, as it will be the other team's turn
		double points = minMax(depth - 1, Checker.lightChecker, boardCopy);
		maxEvalSoFar = Math.min(points, maxEvalSoFar);
	    }
	    return maxEvalSoFar;
	}

    }
}
