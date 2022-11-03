import java.awt.*;
/*KingChecker
* @author       Sanaa Kapur
* @version      May 28 2021
* Description:       
* Different graphics than normal checker, but inherits from it in every other aspect
* */
public class KingChecker extends Checker{
		
	public KingChecker(Color checkerColor, int i) {
		super(checkerColor, i);
		
	}
	
	
    public KingChecker(Color checkerColor) {
    	super(checkerColor);
    	
    }

	public void draw(Graphics g, int x, int y, int width, int height)
	{
		super.draw(g, x, y, width, height);
		g.setColor(Color.CYAN);
		g.setFont(new Font("Serif", Font.PLAIN, 30));	
		g.drawString("K", (int)(x+20), (int)(y+40));
		//drawString("basic", 200, 80);		
	}	

}