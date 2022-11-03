import java.awt.*;

/***********************************************************************************
 *
 *  Board
 *
 *  @author       Sanaa Kapur
 *  @version      May 28 2021
 *  Description:       
 *      
 *      Board is an object that holds the following instance variables:
 *         - int rows             This holds the number of rows in the board
 *         - int cols             This holds the number of columns in the board
 *         - Square[][] board     This 2-D array contains all the squares of the board.
 *
 *
 *The checkerboard that the game is played on which is made up of squares and checkers. 
 *Manages simple things related to board like adding or removing checkers.
 *NEW FANCY METHOD: cloning the board
 *
 ***********************************************************************************/

public class Board {

    private int rows;//board width
    private int cols;//board height
    private Square[][] board;
    private int lightCheckersLeftOnBoard = -100;
    private int darkCheckersLeftOnBoard = -100;
    private int lightKingCheckersOnBoard = -100;
    private int darkKingCheckersOnBoard = -100;

    public Board(int numRows, int numCols, Color darkColor, Color lightColor)
    {	
	Color colorType = lightColor;    
	rows = numRows;
	cols = numCols;
	board = new Square[rows][cols];
	for(int r = 0; r<rows; r++) {
	    for(int c = 0; c<cols; c++) {
		board[r][c] = new Square(r,c, colorType);
		if(colorType == lightColor) {colorType = darkColor;}
		else {colorType = lightColor;}
	    }
	    if(colorType == lightColor) {colorType = darkColor;}
	    else {colorType = lightColor;}
	}
	// Sets the values of the instance variables. It also creates all of the Squares. c
    } 

    public void draw(Graphics g, int width, int height, int border, int titleBar)
    {
	for(int row = 0; row < rows; row ++) {
	    for(int col = 0; col< cols; col++) {
		board[row][col].draw(g, width, height, border, titleBar);
	    }
	}
    }



    //initializes the checkers to their correct u starting positions
    public void setUp(Color t1, Color t2)
    {

	// row 1 black checker
	board[0][0].addChecker(new Checker(Checker.darkChecker));
	board[0][2].addChecker(new Checker(Checker.darkChecker));
	board[0][4].addChecker(new Checker(Checker.darkChecker));
	board[0][6].addChecker(new Checker(Checker.darkChecker));

	// row 2 black checker
	board[1][1].addChecker(new Checker(Checker.darkChecker));
	board[1][3].addChecker(new Checker(Checker.darkChecker));
	board[1][5].addChecker(new Checker(Checker.darkChecker));
	board[1][7].addChecker(new Checker(Checker.darkChecker));

	// row 3 black checker
	board[2][0].addChecker(new Checker(Checker.darkChecker));
	board[2][2].addChecker(new Checker(Checker.darkChecker));
	board[2][4].addChecker(new Checker(Checker.darkChecker));
	board[2][6].addChecker(new Checker(Checker.darkChecker));

	// row 1 red checker
	board[5][1].addChecker(new Checker(Checker.lightChecker));
	board[5][3].addChecker(new Checker(Checker.lightChecker));
	board[5][5].addChecker(new Checker(Checker.lightChecker));
	board[5][7].addChecker(new Checker(Checker.lightChecker));
	// row 2 red checker
	board[6][0].addChecker(new Checker(Checker.lightChecker));
	board[6][2].addChecker(new Checker(Checker.lightChecker));
	board[6][4].addChecker(new Checker(Checker.lightChecker));
	board[6][6].addChecker(new Checker(Checker.lightChecker));
	// row 3 red checker
	board[7][1].addChecker(new Checker(Checker.lightChecker));
	board[7][3].addChecker(new Checker(Checker.lightChecker));
	board[7][5].addChecker(new Checker(Checker.lightChecker));
	board[7][7].addChecker(new Checker(Checker.lightChecker));

	lightCheckersLeftOnBoard = 12;
	darkCheckersLeftOnBoard = 12;
	lightKingCheckersOnBoard = 0;
	darkKingCheckersOnBoard = 0;

    }


    public int getLightCheckersLeftOnBoard() {
	return lightCheckersLeftOnBoard;
    }

    public int getDarkCheckersLeftOnBoard() {
	return darkCheckersLeftOnBoard;
    }
    public void setLightCheckersLeftOnBoard(int lclob) {
	lightCheckersLeftOnBoard = lclob;
    }

    public void setDarkCheckersLeftOnBoard(int dclob) {
	darkCheckersLeftOnBoard = dclob;
    }

    //finds if there is a checker on entered square 
    public boolean isFilled(int r, int c)
    {
	//System.out.println("isFilled r: " + r +  " c: " + c + "fill:" + board[r][c].isFilled());
	if (board[r][c].getChecker()!=null) 
	{ 
	    System.out.println("Checker: " + board[r][c].getChecker().toString()); 
	}
	return board[r][c].isFilled();
    }


    //moves the checker from one location to the other
    public void move(int fromR, int fromC, int toR, int toC) throws Exception
    {
	Checker saved = board[fromR][fromC].getChecker();
	if(saved == null) {
	    throw new Exception("Move: Checker Null");
	}
	board[toR][toC].addChecker(saved);
	board[fromR][fromC].removeChecker();
    }

    //removes a checker from the given square
    public void remove(int r, int c) {
	if(this.getChecker(r, c) != null) {
	    if(board[r][c].getCheckerColor().equals(Checker.lightChecker)) {
		if(this.getChecker(r, c) instanceof KingChecker) {
		    lightKingCheckersOnBoard--;
		}
		
		   lightCheckersLeftOnBoard--;
	    }
	    else {
		if(this.getChecker(r, c) instanceof KingChecker) {
		    darkKingCheckersOnBoard--;
		}
		darkCheckersLeftOnBoard--;
	    }
	    board[r][c].removeChecker();
	}
    }
    public void addChecker(int row, int col, Checker c) {
	board[row][col].addChecker(c);
    }
    public Color getCheckerColor(int r, int c) {
	return board[r][c].getCheckerColor();
    }
    public void setRows(int r) {
	if(r<1) {
	    System.out.println("ERROR: the board is too small");
	}
	else {
	    rows = r;
	}
    }
    public void setCols(int c) {
	if(c<1) {
	    System.out.println("ERROR: the board is too small");
	}
	else {
	    rows = c;
	}
    }
    public Checker getChecker(int row, int col) {

	return board[row][col].getChecker();
    }
    public Square getSquare(int row, int col) {
	return board[row][col];
    }

    //Given a board, returns a duplicate
    public Board clone() {
	Board copy = new Board(this.rows, this.cols, Square.darkSquare, Square.lightSquare);
	copy.setLightCheckersLeftOnBoard(getLightCheckersLeftOnBoard());
	copy.setDarkCheckersLeftOnBoard(getDarkCheckersLeftOnBoard());
	for(int r = 0; r<this.rows; r++) {
	    for(int c = 0; c<this.cols; c++) {
		Square square = board[r][c];
		Checker checker = square.getChecker();
		//System.out.println("Board: r:"+r+ "c:"+c+" Square:" + square + " Checker:" + checker );
		if (checker != null) {
		    if (checker instanceof KingChecker) {
			copy.addChecker(r,c,new KingChecker(checker.getColor()));
		    } else {
			copy.addChecker(r,c,new Checker(checker.getColor()));
		    }
		}
	    }
	}

	return copy;
    }
    public void setNumDarkKingChecker(int val) {
	darkKingCheckersOnBoard = val;
    }
    public void setNumLightKingChecker(int val) {
	lightKingCheckersOnBoard = val;
    }
    public int getDarkKingCheckers() {
	return this.darkKingCheckersOnBoard;
    }
    public int getLightKingCheckers() {
	return this.lightKingCheckersOnBoard;
    }

}

