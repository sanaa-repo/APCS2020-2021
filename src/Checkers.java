import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


import java.util.*;
/***********************************************************************************
 *
 *  Checkers
 *
 *  @author       Sanaa Kapur
 *  @version      May 28 2021
 *  Description:  
 *      
 *      Checkers is the client class that implements a game of Checkers. It utilizes
 *		the Board, Square, and Checker classes. The checkers class is like the judge of the game.
 *		It gets user input, validates it, and allows the user to move around the board!!! To exit, 
 *		one has to press -1. 
 *
 *		Extras:
 *      -One can only move diagonally
 *      -One can only enter numbers between -1 and 7 and ends game without error if enter a letter
 *      -If you want to jump another checker, you can

 ***********************************************************************************/
public class Checkers extends JFrame implements MouseListener 
{
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 523;
    private static final int TITLE_BAR = 23;
    private static final int BORDER = 10;
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int HEIGHT = (WINDOW_HEIGHT-2*BORDER-TITLE_BAR)/ROWS;
    private static final int WIDTH = (WINDOW_WIDTH-2*BORDER)/COLS;


    //Checkers internal data
    private int userRow = 0;
    private int userCol = 0;
    private int newRow = 0;
    private int newCol = 0;
    private Checker[] teamDC = new Checker[12];
    private Checker[] teamLC = new Checker[12];
    private Board checkerboards = null;
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
    private int clickNum = 0;

    public void mousePressed(MouseEvent e){
	clickNum++;

	//b.setColor(colors[clickNum % 3]);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub

    }


    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }


    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }


    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    //gets the checkerboard for when players need to see it
    public Board getBoard() {
	return checkerboards;
    }

    //paints board so players can view
    public void paint(Graphics g)
    {
	this.checkerboards.draw(g, WIDTH, HEIGHT, BORDER, TITLE_BAR);
    }

    //constructor: sets up the checkerboard
    public Checkers() {
	checkerboards = new Board(ROWS, COLS, Square.darkSquare, Square.lightSquare);	
	checkerboards.setUp(Checker.darkChecker, Checker.lightChecker);
	//checkerboards.setUp(Checker.darkChecker, Checker.lightChecker);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	setVisible(true);
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



    //given a team color, returns a list of all possible valid moves on the board for a specific color
    public ArrayList<Move> getAllMoves(Color color, Board checkerboard){
	ArrayList<Move> moves = new ArrayList<Move>();
	for(int r = 0; r< ROWS; r++) {
	    for(int c = 0; c< COLS; c++) {
		Checker ch = checkerboard.getChecker(r, c);
		if(ch!= null && color.equals(ch.getColor())) {
		    ArrayList<Move> temp = getValidMoves(r,c, color, checkerboard);
		    if(temp != null) {
			moves.addAll(temp);
		    }
		}
	    }
	}
	return moves;
    }


    //Given the color of a piece, its row and column,  returns a list of possible moves for that checker
    private ArrayList<Move> getValidMoves(int row, int col, Color color, Board checkerboard){
	Checker ch = checkerboard.getChecker(row, col);
	if(!checkerboard.isFilled(row,col)) {
	    return null;
	}
	else {
	    ArrayList<Move> moves = new ArrayList<Move>();
	    if(color.equals(Checker.lightChecker) || ch instanceof KingChecker) {
		moves.add(new Move(row, col, row -1, col -1));
		moves.add(new Move(row, col, row -1, col + 1));
		moves.add(new Move(row, col, row -2, col -2));
		moves.add(new Move(row, col, row -2, col + 2));
	    }
	    if(color.equals(Checker.darkChecker) || ch instanceof KingChecker) {
		moves.add(new Move(row, col, row + 1, col -1));
		moves.add(new Move(row, col, row + 1, col +1));
		moves.add(new Move(row, col, row + 2, col -2));
		moves.add(new Move(row, col, row + 2, col +2));
	    }

	    for(int i = 0; i< moves.size(); i++) {
		if(!this.isJumpingToValidSquare(moves.get(i), checkerboard)){
		    moves.remove(i);
		    i--;
		}
	    }

	    return moves;
	}
    }

    //moves the checker to the correct location if it is moving diagonally or wants to jump
    public boolean makeMove(Move m, Board checkerboard) {
	int userRow = m.getUserRow();
	int userCol = m.getUserCol();
	int newRow = m.getNewRow();
	int newCol = m.getNewCol();

	if(!isJumpingToValidSquare(m, checkerboard)) {
	    //System.out.println("MakeMove: bad move " + m.toString());
	    return false;
	}

	
	//if the situation calls for a jump then you must have been jumping over a checker
	//if you're jumping over a checker, delete the one you jumped from the board
	if(newRow == userRow -2 && newCol == userCol +2) {
	    Checker c = checkerboard.getChecker(userRow-1, userCol+1);
	    checkerboard.remove(userRow-1, userCol+1);
	}
	else if(newRow == userRow -2 && newCol == userCol -2) {
	    Checker c = checkerboard.getChecker(userRow-1, userCol-1);
	    checkerboard.remove(userRow-1, userCol-1);
	}
	else if(newRow == userRow +2 && newCol == userCol -2) {	
	    Checker c = checkerboard.getChecker(userRow+1, userCol-1);
	    checkerboard.remove(userRow+1, userCol-1);
	}
	else if(newRow == userRow +2 && newCol == userCol +2) {
	    Checker c = checkerboard.getChecker(userRow+1, userCol+1);
	    checkerboard.remove(userRow+1, userCol+1);

	}
	try {
	    checkerboard.move(userRow, userCol, newRow, newCol);}
	catch(Exception e){
	    e.printStackTrace();
	    return false;
	}
	
	repaint();
	return true;
    }

    //prevents invalid user input that cannot be executed on by the board
    //The user can only make the following movements: 1) move forward 1 diagonally and 2) jump over a player of the opposing color
    //Everything else is false
    public boolean isJumpingToValidSquare(Move m, Board checkerboard) {
	int fromRow = m.getUserRow();
	int fromCol = m.getUserCol();
	int endRow = m.getNewRow();
	int endCol = m.getNewCol();

	//are newRow and NewCol moves on the board?
	if(endRow < 0 || endCol < 0 || endRow > 7 || endCol > 7) { 
	    //System.out.println("isJump:check1: "+ m.toString());
	    return false; 
	}  

	//is userRow and userCol moves on the board?
	if (fromRow < 0 || fromCol < 0 ||fromRow > 7 || fromCol > 7) { 
	    //System.out.println("isJump:check2: "+ m.toString());
	    return false; 
	}	

	//is there not a checker on the starting location? 
	if(!checkerboard.isFilled(fromRow, fromCol)) { 
	   // System.out.println("isJump:check3: "+ m.toString());
	    return false; 
	}

	//is there a checker on the ending location? 
	if (checkerboard.isFilled(endRow, endCol)) { 
	   // System.out.println("isJump:check4: "+ m.toString());
	    return false; 
	} 

	//is the move vaid for a checker or kingchecker?
	if(checkerboard.getChecker(fromRow, fromCol) instanceof KingChecker) {
	    //System.out.println("King Checker Detected userRow: " + fromRow + "userCol: " + fromCol);
	    return kingCheckerValidity(m, checkerboard);
	}
	else{
	   // System.out.println("Normal Checker Detected: userRow: " + fromRow + "userCol: " + fromCol + "newRow " + endRow + "newCol: " + endCol);
	    return normalCheckerValidity(m, checkerboard);
	}

    }

    private boolean kingCheckerValidity(Move m, Board checkerboard) {
	int userRow = m.getUserRow();
	int userCol = m.getUserCol();
	int newRow = m.getNewRow();
	int newCol = m.getNewCol();
	
	if(Math.abs(newRow-userRow)== 1 && Math.abs(newCol-userCol)== 1) {
	    return true;
	}
	else if(userCol<7 && userRow<7 && userRow>0&& userCol>0) {
	    Color myC = checkerboard.getCheckerColor(userRow, userCol);
	    if((userCol<7 && userRow>0)&& checkerboard.getCheckerColor(userRow-1, userCol+1)!=myC || (userCol>0 && userRow>0) &&checkerboard.getCheckerColor(userRow-1, userCol-1)!=myC) {
		if(newRow == userRow -2 && (newCol == userCol +2|| newCol == userCol -2)){

		    return true;
		}
	    }
	    else if((userCol<7 && userRow<7&&(checkerboard.getCheckerColor(userRow+1, userCol+1)!=myC))||(userCol>0 && userRow<7&& (checkerboard.getCheckerColor(userRow+1, userCol-1)!=myC))) {
		System.out.println("trois");
		if( newRow == userRow +2 && (newCol == userCol +2|| newCol == userCol -2)){
		    return true;
		}
	    }
	}
	return false;
    }

    private boolean normalCheckerValidity(Move m, Board checkerboard) {
	int userRow = m.getUserRow();
	int userCol = m.getUserCol();
	int newRow = m.getNewRow();
	int newCol = m.getNewCol();
	//If checker is light
	if(checkerboard.getCheckerColor(userRow, userCol)==Checker.lightChecker ) {	
	    //if its moving diagonally in the correct direction
	    if(newRow == userRow -1 && (newCol == userCol +1|| newCol == userCol -1)) {
		try { 
		    //makeKingChecker(Checker.lightChecker, checkerboard);
		    makeKingChecker(Checker.lightChecker, m, checkerboard);
		} catch(Exception e) { e.printStackTrace(); }
		return true;
	    }

	    //if you are jumping a darkchecker
	    System.out.println("ab enter 2");
	    if(((userCol<7 && userRow>0)&& checkerboard.getCheckerColor(userRow-1, userCol+1)==Checker.darkChecker) || (userCol>0 && userRow>0) &&checkerboard.getCheckerColor(userRow-1, userCol-1)==Checker.darkChecker) {
		//and you are jumping over that square
		System.out.println("enter 2");
		if(newRow == userRow -2 && (newCol == userCol +2|| newCol == userCol -2)){
		    try { 
			//makeKingChecker(Checker.lightChecker, checkerboard); 
			makeKingChecker(Checker.lightChecker, m, checkerboard);
		    } catch(Exception e) { e.printStackTrace(); }
		    return true;
		}
	    } else {
		System.out.println("You cannot move to this position");
	    }
	}

	//if the starting pos has a light checker
	if(checkerboard.getCheckerColor(userRow, userCol)==Checker.darkChecker) {
	    System.out.println("Normal Check Validity: Into loop 2");
	    //if its moving diagonally in the correct direction
	    if(newRow == userRow +1 && (newCol == userCol +1|| newCol == userCol -1)) {
		try {
		    //makeKingChecker(Checker.darkChecker, checkerboard);
		    makeKingChecker(Checker.darkChecker, m, checkerboard);
		}
		catch(Exception e) {
		    e.printStackTrace();
		}
		return true;
	    }
	    //if the diagonal square is black
	    if((userCol<7 && userRow<7&&(checkerboard.getCheckerColor(userRow+1, userCol+1)==Checker.lightChecker))||(userCol>0 && userRow<7&& (checkerboard.getCheckerColor(userRow+1, userCol-1)==Checker.lightChecker))) {
		//and you are jumping over that square
		if( newRow == userRow +2 && (newCol == userCol +2|| newCol == userCol -2)){
		    try {
			//makeKingChecker(Checker.darkChecker, checkerboard);
			makeKingChecker(Checker.darkChecker, m, checkerboard);
		    }
		    catch(Exception e) {
			e.printStackTrace();
		    }
		    return true;
		}
	    }
	    else {
		System.out.println("You cannot move to this position");
	    }
	}	

	return false;

    }

/*
    //Turn a checker at the end to a king checker
    //It is being called after each valid move to see if the checker ended up at a place which it could be transformed
    public void makeKingChecker(Color color, Board checkerboard) throws Exception{
	Checker c = checkerboard.getChecker(userRow, userCol);
	if(c == null) {
	    throw new Exception("Make King Checker: Checker Null");

	}
	int endOfBoard = 0;
	Checker[] team = teamLC;
	if(color.equals(Checker.darkChecker)) {
	    endOfBoard = 7;
	    team = teamDC;
	}

	// turn index in checker array from checker to king checker in correct conditions 
	if(newRow == endOfBoard && !(c instanceof KingChecker)){
	    team[c.getIndex()] = new KingChecker(color, c.getIndex());

	    //replacing current checker on board with king checker
	    checkerboard.addChecker(userRow, userCol, team[c.getIndex()]);
	    //checkerboard.remove(userRow, userCol);


	}
    }
*/
    
    public void makeKingChecker(Color color, Move m, Board checkerboard) throws Exception{
	int userRow = m.getUserRow();
	int userCol = m.getUserCol();
	int newRow = m.getNewRow();
	int newCol = m.getNewCol();
	
	Checker c = checkerboard.getChecker(userRow, userCol);
	if(c == null) {
	    throw new Exception("Make King Checker: Checker Null");

	}
	int endOfBoard = 0;
	Checker[] team = teamLC;
	if(color.equals(Checker.darkChecker)) {
	    endOfBoard = 7;
	    team = teamDC;
	}

	// turn index in checker array from checker to king checker in correct conditions 
	if(newRow == endOfBoard && !(c instanceof KingChecker)){
	    team[c.getIndex()] = new KingChecker(color, c.getIndex());

	    //replacing current checker on board with king checker
	    checkerboard.addChecker(userRow, userCol, team[c.getIndex()]);
	    //checkerboard.remove(userRow, userCol);
	    if(color == Checker.darkChecker) {
		checkerboard.setNumDarkKingChecker(checkerboard.getDarkKingCheckers()+1);
	    }
	    else {
		checkerboard.setNumLightKingChecker(checkerboard.getLightKingCheckers()+1);
	    }

	}
    }
    public boolean isGameOver(Board board){

	if(board.getLightCheckersLeftOnBoard() == 0) {
	    System.out.println("black won");
	    return true;
	}
	if(board.getDarkCheckersLeftOnBoard() == 0) {
	    System.out.println("red won");
	    return true;
	}
	return false;
    }



}


