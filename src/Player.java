import java.awt.Color;
import java.util.*;
/*Player
* @author       Sanaa Kapur
* @version      May 28 2021
 * Description:       
* Pits 2 AI Players against each other. 
* */
public class Player {
    private Checkers c;
    private int userRow;
    private int userCol;
    private int newRow;
    private int newCol; 
    private Color color;

    public Player(Checkers ch, Color col) throws Exception {
	if(ch == null|| col == null) {
	    throw new Exception("player is null");
	}
	c = ch;
	color = col;
    }
    public Checkers getCheckers() {
	return c;
    }

    public int getUserRow() {
	return userRow;
    }

    public int getUserCol() {
	return userCol;
    }

    public int getNewRow() {
	return newRow;
    }

    public int getNewCol() {
	return newCol;
    }
    public Color getColor() {
	return color;
    }

    public void setUserRow(int ur) {
	userRow = ur;
    }

    public void setUserCol(int uc) {
	userCol = uc;
    }

    public void setNewRow(int nr) {
	newRow = nr;
    }

    public void setNewCol(int nc) {
	newCol = nc;
    }
    public Move think(Board b, Color c) {
	System.out.println("Entered PLAYER think!; returning null");
	return null;
    }

    public boolean playTurn() {
	Move thunk = think(c.getBoard(), color);
	 //test case player can return null when array consumed
	if (thunk == null) return false; 
	//given a move and a board, make that move on given board. void method.  
	System.out.println("thunk:" + thunk.toString());
	return c.makeMove(thunk, c.getBoard());

    }

};
