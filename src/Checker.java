import java.awt.*;
/***********************************************************************************
 *
 *  Checker
 *
 *  @author       Zach Blickensderfer & Dennis Millstein & Sanaa Kapur
 *  @version      May 28, 2021
 *  Description:       
 *      
 
 *
 *      	A Checker does not know where it is. This is because it is
 *		moved by Board.move(), which implements Square.addChecker() and
 *		Square.removeChecker(). Since the Checker's location is controlled
 *		elsewhere, all the Checker needs to know is its color and how to draw
 *		itself. Checker.draw() is called by Square.draw().
 *
 * Summary: Checker that which is placed on the board. Draws the checker on the board
 ***********************************************************************************/
public class Checker
{
    private Color c;
    private int index;
    public static final Color darkChecker = Color.black;
    public static final Color lightChecker = Color.red;


	public Checker(Color checkerColor, int i)
	{
        c = checkerColor;
        index = i;
        
	}


	public Checker(Color checkerColor)
	{
        c = checkerColor;
        
	}
	
	public void draw(Graphics g, int x, int y, int width, int height)
	{
		g.setColor(c);
		g.fillOval(x, y, width, height);
		
	}
	
	//all objects should have gets and sets
	public Color getColor(){
		return c;
	}
	public int getIndex() {
		return index;
	}
	public void setColor(Color newColor) {
		c = newColor;
	}
	public void setIndex(int newIndex) {
		index = newIndex;
	}
	// working at creating new private attribute of checker- index
	public String toString() {
	    return new String ("Checker: color:" + c); 
	}
}