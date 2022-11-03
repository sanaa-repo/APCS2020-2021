import java.awt.*;
/***********************************************************************************
 *
 *  Square
 *
 *  @author       Zach Blickensderfer & Dennis Millstein & Sanaa Kapur
 *  @version      April 13, 2021
 *  Description:       
 *      
 *      Square is an object that holds the following instance variables:
 *         - int row             This is the Square's row, beginning at 0
 *         - int col             This is the Square's column, beginning at 0
 *         - Color c             This is the Square's color
 *         - Checker checker     This Checker object is null if the Square is empty;
 *								 otherwise, it points to a specific Checker.
 *
 * - Graphics for the board that sets/gets/houses the checkers
 * 
 ***********************************************************************************/
public class Square {

    public static final Color darkSquare = Color.blue;
    public static final Color lightSquare = Color.white;
    
	private int row;
	private int col;
	private Color c;
	private Checker checker;
	
	public Square(int rIn, int cIn, Color colorIn)
	{
		row = rIn;
		col = cIn;
		c = colorIn;
		checker = null;
	}
	
	public boolean isFilled()
	{
		return !(checker == null);
	}

	public Checker getChecker()
	{
		return checker;
	}
	
	public void addChecker(Checker chIn)
	{
		checker = chIn;
	}
	
	public void removeChecker()
	{
		checker = null;
	}
	public Color getColor()
	{
		return c;
	}
	public void setRow(int r) {
		if(r<0) {
			System.out.println("ERROR: This is not a valid row");
		}
		else {
		row = r;
		}
	}
	public void setCol(int c) {
		if(c<0) {
			System.out.println("ERROR: This is not a valid column");
		}
		else {
		col = c;
		}
	}
	public Color getCheckerColor() {
		if(checker != null) {
		return checker.getColor();
		}
		return null;
	}
	
	public void draw(Graphics g, int width, int height, int border, int titleBar)
	{
		int x = col*width + border;
		int y = row*height + border + titleBar;
		g.setColor(c);
		g.fillRect(x, y, width, height);
		if(isFilled())
		{	
		    //move 3/4down and 1/2ish to right
			checker.draw(g, x, y, width, height);
		}
	}
	public boolean containsClick() {
		
		return false;
	}
	
}
